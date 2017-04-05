package com.zdsoft.finance.specialapprove.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.promotion.otherproperty.entity.AssetsCar;
import com.zdsoft.finance.casemanage.promotion.otherproperty.entity.AssetsSecurities;
import com.zdsoft.finance.casemanage.promotion.otherproperty.entity.CustomerHouse;
import com.zdsoft.finance.casemanage.promotion.otherproperty.service.AssetsCarService;
import com.zdsoft.finance.casemanage.promotion.otherproperty.service.AssetsSecuritiesService;
import com.zdsoft.finance.casemanage.promotion.otherproperty.service.CustomerHouseService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.utils.BigDecimalCalculateTwo;
import com.zdsoft.finance.common.utils.RateUtil;
import com.zdsoft.finance.common.utils.TimeUtil;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.repository.CaseApplyRepository;
import com.zdsoft.finance.specialapprove.entity.OrganizationRisk;
import com.zdsoft.finance.specialapprove.service.OrganizationRiskService;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.util.ObjectHelper;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: OrganizationRiskServiceImpl.java 
 * @ClassName: OrganizationRiskServiceImpl 
 * @Description: 机构风险实现
 * @author jingjiyan 
 * @date 2017年3月6日 下午3:50:18 
 * @version V1.0
 */
@Service("organizationRiskService")
public class OrganizationRiskServiceImpl extends  BaseServiceImpl<CaseApply, CustomRepository<CaseApply, String>> 
	implements OrganizationRiskService{
	
	@Autowired
	private CED CED;
	@Autowired
	private CaseApplyRepository caseApplyRepository;
	@Autowired
	private AssetsCarService assetsCarService;
	@Autowired
	private AssetsSecuritiesService assetsSecuritiesService;
	@Autowired
	private CustomerHouseService customerHouseService;
	
	/**
	 * 机构风险，根据客户提供表结构查询数据
	 * Title: getOrganizationRisk   
	 * @param caseApplyId
	 * 			案件ID
	 * @return   
	 *
	 */
	@Override
	public List<OrganizationRisk> getOrganizationRisk(String caseApplyId){
		List<OrganizationRisk> riskList = new ArrayList<OrganizationRisk>();
	  try {
		CaseApply caseApply = caseApplyRepository.findOne(caseApplyId);
		
		EmpDto empDto =  CED.getLoginUser();
		
		Long nowDt = Long.valueOf(TimeUtil.getCurrentDateInteger());
		Map<String,Object> prodouctMap =new HashMap<String,Object>();
		Map<String,Object> DMMap =new HashMap<String,Object>();
		Map<String,Object> ORGMap =new HashMap<String,Object>();
		prodouctMap.put("PRODUCT_ID", caseApply.getProductTypeId());
		DMMap.put("STAFF_ID", empDto.getId());
		ORGMap.put("ORG_ID", empDto.getOrgId());
		Page<Map<String, Object>> prodouctPage = caseApplyRepository.findPageOrganizationRisk(prodouctMap,nowDt);
		Page<Map<String, Object>> DMPage = caseApplyRepository.findPageOrganizationRisk(DMMap,nowDt);
		Page<Map<String, Object>> ORGPage = caseApplyRepository.findPageOrganizationRisk(ORGMap,nowDt);
		OrganizationRisk prodouctRisk=new OrganizationRisk();
		OrganizationRisk DMRisk=new OrganizationRisk();
		OrganizationRisk ORGRisk=new OrganizationRisk();
		for(Map<String, Object> maps : prodouctPage.getRecords()){//产品
			Object  overdueTotals =	ObjectHelper.isEmpty(maps.get("CON_OVDUE_NUM")) ? "0" : maps.get("CON_OVDUE_NUM");//逾期合同数
			Object  loanAmt =	ObjectHelper.isEmpty(maps.get("LOAN_AMT")) ? "0" : maps.get("LOAN_AMT");//放款金额
			Object  loanBal =	ObjectHelper.isEmpty(maps.get("LOAN_BAL")) ? "0" : maps.get("LOAN_BAL");//贷款余额
			Object  conNum =	ObjectHelper.isEmpty(maps.get("CON_NUM")) ? "0" : maps.get("CON_NUM");//合同数
			prodouctRisk.setRiskType(OrganizationRisk.PRODUCT);
			prodouctRisk.setLoanAmount(BigDecimal.valueOf(Double.valueOf(loanAmt+"")));
			prodouctRisk.setLoanBalance(BigDecimal.valueOf(Double.valueOf(loanBal+"")));
			prodouctRisk.setLoanAmount(BigDecimal.valueOf(Double.valueOf(loanAmt+"")));
			prodouctRisk.setLoansNumber(conNum+"");
			prodouctRisk.setOverdueLoansNumber(overdueTotals+"");
			if(ObjectHelper.isEmpty(maps.get("CON_NUM")) && "0".equals(conNum)){
				prodouctRisk.setOverdueLoansProportion("0");
			}else{
				prodouctRisk.setOverdueLoansProportion(RateUtil.percentRate(Double.valueOf(Integer.valueOf(overdueTotals+"")/Integer.valueOf(conNum+"")+"")) + "");
			}
		}
		riskList.add(prodouctRisk);
		for(Map<String, Object> maps : DMPage.getRecords()){//扩展经理
			Object  overdueTotals =	ObjectHelper.isEmpty(maps.get("CON_OVDUE_NUM")) ? "0" : maps.get("CON_OVDUE_NUM");//逾期合同数
			Object  loanAmt =	ObjectHelper.isEmpty(maps.get("LOAN_AMT")) ? "0" : maps.get("LOAN_AMT");//放款金额
			Object  loanBal =	ObjectHelper.isEmpty(maps.get("LOAN_BAL")) ? "0" : maps.get("LOAN_BAL");//贷款余额
			Object  conNum =	ObjectHelper.isEmpty(maps.get("CON_NUM")) ? "0" : maps.get("CON_NUM");//合同数
			DMRisk.setRiskType(OrganizationRisk.DEVELOPMENTMANAGER);
			DMRisk.setLoanAmount(BigDecimal.valueOf(Double.valueOf(loanAmt+"")));
			DMRisk.setLoanBalance(BigDecimal.valueOf(Double.valueOf(loanBal+"")));
			DMRisk.setLoanAmount(BigDecimal.valueOf(Double.valueOf(loanAmt+"")));
			DMRisk.setLoansNumber(conNum+"");
			DMRisk.setOverdueLoansNumber(overdueTotals+"");
			if(ObjectHelper.isEmpty(maps.get("CON_NUM")) && "0".equals(conNum)){
				DMRisk.setOverdueLoansProportion("0");
			}else{
				DMRisk.setOverdueLoansProportion(RateUtil.percentRate(Double.valueOf(Integer.valueOf(overdueTotals+"")/Integer.valueOf(conNum+"")+"")) + "");
			}
			
		}
		
		for(Map<String, Object> maps : ORGPage.getRecords()){//机构
			Object  overdueTotals =	ObjectHelper.isEmpty(maps.get("CON_OVDUE_NUM")) ? "0" : maps.get("CON_OVDUE_NUM");//逾期合同数
			Object  loanAmt =	ObjectHelper.isEmpty(maps.get("LOAN_AMT")) ? "0" : maps.get("LOAN_AMT");//放款金额
			Object  loanBal =	ObjectHelper.isEmpty(maps.get("LOAN_BAL")) ? "0" : maps.get("LOAN_BAL");//贷款余额
			Object  conNum =	ObjectHelper.isEmpty(maps.get("CON_NUM")) ? "0" : maps.get("CON_NUM");//合同数
			ORGRisk.setRiskType(OrganizationRisk.ORGANIZATION);
			ORGRisk.setLoanAmount(BigDecimal.valueOf(Double.valueOf(loanAmt+"")));
			ORGRisk.setLoanBalance(BigDecimal.valueOf(Double.valueOf(loanBal+"")));
			ORGRisk.setLoanAmount(BigDecimal.valueOf(Double.valueOf(loanAmt+"")));
			ORGRisk.setLoansNumber(conNum+"");
			ORGRisk.setOverdueLoansNumber(overdueTotals+"");
			if(ObjectHelper.isEmpty(maps.get("CON_NUM")) && "0".equals(conNum)){
				ORGRisk.setOverdueLoansProportion("0");
			}else{
				ORGRisk.setOverdueLoansProportion(RateUtil.percentRate(Double.valueOf(Integer.valueOf(overdueTotals+"")/Integer.valueOf(conNum+"")+"")) + "");
			}
			riskList.add(ORGRisk);
			riskList.add(DMRisk);
			
		}
	  	} catch (Exception e) {
			e.printStackTrace();
		}
		return riskList;
	}
	/**
	 * 
	 * Title: getSolvencyInfo  
	 * Description: 根据案件ID查询客户偿债信息
	 * @author jingjiyan  
	 * @param caseApplyId
	 * 			案件ID
	 * @return
	 */
	@Override
	public Map<String,Object> getSolvencyInfo(String caseApplyId){
		Map<String,Object> map = new HashMap<String,Object>();
		List<AssetsCar> assetsCarList = 	assetsCarService.findByCaseApplyId(caseApplyId);//获取汽车信息
		List<AssetsSecurities> assetsSecuritiesList = 	assetsSecuritiesService.findByCaseApplyId(caseApplyId);//获取证券信息
		List<CustomerHouse> customerHouseList = 	customerHouseService.findByCaseApplyId(caseApplyId);//获取房产信息
		Integer total = 0;
		BigDecimal sumAoumt = BigDecimal.ZERO;
			for(AssetsCar assetsCar:assetsCarList){
				sumAoumt = BigDecimalCalculateTwo.add(sumAoumt, assetsCar.getWorth());
				total++;
			}
			for(AssetsSecurities assetsSecurities:assetsSecuritiesList){
				sumAoumt = BigDecimalCalculateTwo.add(sumAoumt, assetsSecurities.getWorth());
				total++;
			}
			for(CustomerHouse assetsCar:customerHouseList){
				sumAoumt = BigDecimalCalculateTwo.add(sumAoumt, assetsCar.getWorth());
				total++;
			}
			map.put("total", total);
			map.put("sumAoumt", sumAoumt);
			return map;
	}

}
