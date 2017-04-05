package com.zdsoft.finance.marketing.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.BigDecimalCalculateTwo;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.entity.PledgeInfo;
import com.zdsoft.finance.marketing.repository.HousePropertyRepository;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HousePropertyServiceImpl.java 
 * @ClassName: HousePropertyServiceImpl 
 * @Description: 房产服务实现类
 * @author zhoushichao 
 * @date 2017年3月14日 下午7:32:07 
 * @version V1.0
 */
@Service("housePropertyService")
public class HousePropertyServiceImpl extends BaseServiceImpl<HouseProperty, HousePropertyRepository>implements HousePropertyService {
	@Autowired
	private CaseApplyService caseApplyService;
	
	/**
	 * 重写saveOrUpdateEntity用于计算计算贷款成数，评估抵押成数 by xj
	 * <p>Title: saveOrUpdateEntity</p>   
	 * <p>Description: </p>   
	 * @param t
	 * @return
	 * @throws BusinessException   
	 * @see com.zdsoft.finance.base.service.impl.BaseServiceImpl#saveOrUpdateEntity(com.zdsoft.framework.core.common.domain.BaseEntity)
	 */
	@Override
	public HouseProperty saveOrUpdateEntity(HouseProperty t) throws BusinessException {
		HouseProperty houseProperty = super.saveOrUpdateEntity(t);
		//计算贷款成数，评估抵押成数
		this.calculatePercentage(houseProperty.getCaseApply());
		return houseProperty;
	}

	@Override
	@Transactional
	public HouseProperty saveOrUpdateHouseProperty(HouseProperty houseProperty) throws Exception {
		HouseProperty housePo = new HouseProperty();
		if (ObjectHelper.isNotEmpty(houseProperty.getId())) {
			housePo = this.customReposity.findOne(houseProperty.getId());
		}
		BeanUtils.copyProperties(houseProperty, housePo,new String[] {"id","createTime"});
		// 设置押品类型
		if (ObjectHelper.isEmpty(housePo.getCollateralType())) {
			housePo.setCollateralType(HouseProperty.HOUSE_PROPERTY);
		}
		houseProperty = this.saveOrUpdateEntity(housePo);
		if (ObjectHelper.isNotEmpty(houseProperty.getSearch())) {
			houseProperty.getSearch().setHousePropertyId(houseProperty.getId());
		}
		return houseProperty;
	}
	
	@Override
	public List<HouseProperty> findByCaseApplyId(String caseApplyId) {
		return this.customReposity.findByCaseApplyIdOrderByCreateTime(caseApplyId);
	}

	@Override
	public void deleteHousePropertyById(String housePropertyId) throws BusinessException{
		HouseProperty houseProperty = this.findOne(housePropertyId);
		this.customReposity.delete(houseProperty);
	}
	
	/**
	 * 
	 * @Title: calculatePercentage 
	 * @Description: 计算贷款成数，评估抵押成数 计算规则 贷款成数 = [申请金额+房产余额（普通抵押）+房产抵押额（最高抵押）]/风控核定价之和、抵押成数 = [申请金额+房产余额（普通抵押）+房产抵押额（最高抵押）]/房产评估价之和
	 * @author xj 
	 * @param caseApply 案件
	 * @throws BusinessException 
	 */
	private void calculatePercentage(CaseApply caseApply) throws BusinessException {
		logger.info("开始计算贷款成数，评估抵押成数");
		List<HouseProperty> housePropertys = this.findByCaseApplyId(caseApply.getId());
		//申请金额
		BigDecimal applyAmount = caseApply.getApplyAmount();
		//被除数   计算贷款成数
		BigDecimal allControlPrice = applyAmount;
		//被除数  计算评估抵押成数 
		BigDecimal allSynthesizePrice = applyAmount;
		//除数 风控核定价除数
		BigDecimal dividendControlPrice = BigDecimal.ZERO;
		//除数 综合评估价除数
		BigDecimal dividendSynthesizePrice = BigDecimal.ZERO;
		if(ObjectHelper.isNotEmpty(housePropertys)){
			for (HouseProperty houseProperty : housePropertys) {
				BigDecimal synthesizePrice = houseProperty.getSynthesizePrice();//综合评估价
				BigDecimal controlPrice = houseProperty.getControlPrice();//风控核定价
				List<PledgeInfo> pledgeInfos = houseProperty.getPledgeInfoList();
				
				//计算除数
				//风控核定价除数
				if(ObjectHelper.isNotEmpty(controlPrice) && controlPrice.compareTo(BigDecimal.ZERO) == 1){
					dividendControlPrice = BigDecimalCalculateTwo.add(dividendControlPrice,controlPrice);
				}
				
				//综合评估价除数
				if(ObjectHelper.isNotEmpty(synthesizePrice) && synthesizePrice.compareTo(BigDecimal.ZERO) ==  1){
					dividendSynthesizePrice = BigDecimalCalculateTwo.add(dividendSynthesizePrice,synthesizePrice);
				}
				
				//计算被除数
				if(ObjectHelper.isNotEmpty(pledgeInfos)){
					
					for (PledgeInfo pledgeInfo : pledgeInfos) {
						BigDecimal loanBalance = pledgeInfo.getLoanBalance();
						BigDecimal pledgeAmout = pledgeInfo.getPledgeAmout();
						//普通抵押
						if(PledgeInfo.TYPE_BASE.equals(pledgeInfo.getType())){
							//计算贷款成数
							if(ObjectHelper.isNotEmpty(controlPrice) && controlPrice.compareTo(BigDecimal.ZERO) == 1){
								allControlPrice  =  BigDecimalCalculateTwo.add(allControlPrice,loanBalance);
							}
							//计算评估抵押成数 
							if(ObjectHelper.isNotEmpty(synthesizePrice) && synthesizePrice.compareTo(BigDecimal.ZERO) ==  1){
								allSynthesizePrice = BigDecimalCalculateTwo.add(allSynthesizePrice,loanBalance);
							}
							
						}
						//最高额抵押
						if(PledgeInfo.TYPE_HEIGHEST.equals(pledgeInfo.getType())){
							//计算贷款成数
							if(ObjectHelper.isNotEmpty(controlPrice) && controlPrice.compareTo(BigDecimal.ZERO) ==  1){
								allControlPrice  =  BigDecimalCalculateTwo.add(allControlPrice,pledgeAmout);
							}
							//计算评估抵押成数 
							if(ObjectHelper.isNotEmpty(synthesizePrice) && synthesizePrice.compareTo(BigDecimal.ZERO) ==  1){
								allSynthesizePrice = BigDecimalCalculateTwo.add(allSynthesizePrice,pledgeAmout);
							}
						}
					}
				}
				//end 计算被除数
				
			}
		}
		caseApply.setLoanNumber(null);
		caseApply.setAssessedPriceMortgage(null);
		//贷款成数
		if(dividendControlPrice.compareTo(BigDecimal.ZERO) == 1){
			caseApply.setLoanNumber(BigDecimalCalculateTwo.div(allControlPrice, dividendControlPrice, 4).toString());
		}
		//评估抵押成数
		if(dividendSynthesizePrice.compareTo(BigDecimal.ZERO) ==  1){
			caseApply.setAssessedPriceMortgage(BigDecimalCalculateTwo.div(allSynthesizePrice,dividendSynthesizePrice, 4).toString());
		}
		
		caseApplyService.updateEntity(caseApply);
		logger.info("计算贷款成数，评估抵押成数结束");
	}
}
