package com.zdsoft.finance.casemanage.datasurvey.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.businesssetting.entity.CostItem;
import com.zdsoft.finance.businesssetting.service.CostItemService;
import com.zdsoft.finance.casemanage.datasurvey.entity.FeeInfomation;
import com.zdsoft.finance.casemanage.datasurvey.repository.FeeInfomationRepository;
import com.zdsoft.finance.casemanage.datasurvey.service.FeeInfomationService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyAfterCustomer;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.product.entity.FeeOption;
import com.zdsoft.finance.product.service.FeeOptionService;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:FeeInfomationServiceImpl.java
 * @Package:com.zdsoft.finance.casemanage.datasurvey.service.impl
 * @Description:费用信息实现类
 * @author: jingyh
 * @date:2017年1月17日 下午10:00:21
 * @version:v1.0
 */
@Service("feeInfomationService")
public class   FeeInfomationServiceImpl extends BaseServiceImpl<FeeInfomation,FeeInfomationRepository>	implements FeeInfomationService {
	
	@Resource
	private CaseApplyService caseApplyService;
	
	@Resource
	private FeeOptionService feeOptionService;
	
	@Resource
	private FeeInfomationRepository feeInfomationRepository;
	
	@Resource
	private CED CED;
	
	@Resource
	private CostItemService costItemService;

	@Override
	public List<FeeInfomation> findFeeInfomationByCaseApplyId(String caseApplyId) throws Exception {
		logger.info("根据案件记录查询费用信息");
		logger.debug("caseApplyId:{}", caseApplyId);
		if (ObjectHelper.isEmpty(caseApplyId)) {
			throw new BusinessException("10010004", "案件Id为空");
		}
		List<FeeInfomation> resultList = this.customReposity.findAllyByCaseApplyId(caseApplyId);
		if (ObjectHelper.isEmpty(resultList)) {
			// 查询结果为空，从产品中获取配置信息
			logger.debug("从产品中获取配置信息");
			CaseApply apply = caseApplyService.findOne(caseApplyId);
			if (ObjectHelper.isEmpty(apply)) {
				throw new BusinessException("10010002", "案件Id为:" + caseApplyId);
			}
			// 申请金额
			BigDecimal applyAmount = apply.getApplyAmount();
			// 取用子类产品的信息
			String productTypeId = apply.getProductSubtypeId();
			logger.debug("产品Id为：{}", productTypeId);
			if (ObjectHelper.isEmpty(productTypeId)) {
				throw new BusinessException("10010002", "产品Id为空！");
			}
			// 通过产品Id查询
			List<FeeOption> productFees = feeOptionService.findByProductId(productTypeId);
			if (ObjectHelper.isNotEmpty(productFees)) {
				// 循环转成FeeInfomation,只同步费用项和费用类型
				for (FeeOption option : productFees) {
					FeeInfomation info = new FeeInfomation();
					// 费用类型
					info.setFeeType(option.getFeeType());
					// 费用项
					info.setFeeItem(option.getFeeItem());
					// 预计应收 通过配置计算出来
					if (FEE_CALCULATE_FIXED.equals(option.getChargeCalculateWay()) && ObjectHelper.isNotEmpty(option.getChargeAmount())) {
						info.setExpectedAmount(option.getChargeAmount());
					} else if (FEE_CALCULATE_RATIO.equals(option.getChargeCalculateWay()) && ObjectHelper.isNotEmpty(option.getChargeRatio())) {
						info.setExpectedAmount(applyAmount.multiply(BigDecimal.valueOf(option.getChargeRatio())));
					}
					// 预计应付 通过配置计算出来
					if (FEE_PAY_CALCULATE_FIXED.equals(option.getPayCalculateWay()) && ObjectHelper.isNotEmpty(option.getPayAmount())) {
						info.setExpectedPayableAmount(option.getPayAmount());
					} else if (FEE_PAY_CALCULATE_RATIO.equals(option.getPayCalculateWay()) && ObjectHelper.isNotEmpty(option.getPayRatio())) {
						info.setExpectedPayableAmount(applyAmount.multiply(BigDecimal.valueOf(option.getPayRatio())));
					}
					resultList.add(info);
				}
			}
		}
		if (ObjectHelper.isNotEmpty(resultList)) {
			for (FeeInfomation entity : resultList) {
				// 费用类型名称
				if (ObjectHelper.isNotEmpty(entity.getFeeType()) && ObjectHelper.isEmpty(entity.getFeeTypeName())) {
					entity.setFeeTypeName(this.CED.loadSimpleCodeNameByFullCode(entity.getFeeType()));
				}
				// 费用项名称
				if (ObjectHelper.isNotEmpty(entity.getFeeItem()) && ObjectHelper.isEmpty(entity.getFeeItemName())) {
					CostItem costItem = this.costItemService.findByCostItemCode(entity.getFeeItem());
					if (ObjectHelper.isNotEmpty(costItem)) {
						entity.setFeeItemName(costItem.getCostItemName());
					}
				}
			}
		}
		
		return resultList;
	}
	
	@Override
	public List<FeeInfomation> findByCaseApplyId(String caseApplyId) throws Exception {
		logger.info("根据案件记录查询费用信息");
		logger.debug("caseApplyId:{}", caseApplyId);
		if (ObjectHelper.isEmpty(caseApplyId)) {
			throw new BusinessException("10010004", "案件Id为空");
		}
		List<FeeInfomation> resultList = this.customReposity.findAllyByCaseApplyId(caseApplyId);
		if (ObjectHelper.isNotEmpty(resultList)) {
			for (FeeInfomation entity : resultList) {
				// 费用类型名称
				if (ObjectHelper.isNotEmpty(entity.getFeeType()) && ObjectHelper.isEmpty(entity.getFeeTypeName())) {
					entity.setFeeTypeName(this.CED.loadSimpleCodeNameByFullCode(entity.getFeeType()));
				}
				// 费用项名称
				if (ObjectHelper.isNotEmpty(entity.getFeeItem()) && ObjectHelper.isEmpty(entity.getFeeItemName())) {
					CostItem costItem = this.costItemService.findByCostItemCode(entity.getFeeItem());
					if (ObjectHelper.isNotEmpty(costItem)) {
						entity.setFeeItemName(costItem.getCostItemName());
					}
				}
			}
		}
		return resultList;
	}
	
	@Override
	@Transactional
	public void saveOrUpdateFeeInfos(String caseApplyId, List<FeeInfomation> feeInfos) throws Exception {
		logger.info("批量保存费用信息");
		logger.debug("caseApplyId:{}", caseApplyId);
		logger.debug("feeInfos:{}", feeInfos);
		if (ObjectHelper.isEmpty(caseApplyId) || ObjectHelper.isEmpty(feeInfos)) {
			throw new BusinessException("10010004", "传入参数为空");
		}
		//获取案件信息
		CaseApply apply = this.caseApplyService.findOne(caseApplyId);
		if (ObjectHelper.isEmpty(apply)) {
			throw new BusinessException("10010002", "案件Id为:" + caseApplyId);
		}
		// 旧数据全部删除
		List<FeeInfomation> oldInfo = this.customReposity.findAllyByCaseApplyId(caseApplyId);
		if (ObjectHelper.isNotEmpty(oldInfo)) {
			this.customReposity.delete(oldInfo);
		}
		EmpDto loginUser = CED.getLoginUser();
		Date currentDate = new Date();
		// 循环保存新数据
		for (FeeInfomation info : feeInfos) {
			FeeInfomation newFeeInfo=new FeeInfomation();
			BeanUtils.copyProperties(info,newFeeInfo,new String[]{"id"});
			newFeeInfo.setCreateBy(loginUser.getEmpCd());
			newFeeInfo.setCreateOrgCd(loginUser.getOrgCd());
			newFeeInfo.setCreateTime(currentDate);
			// 关联案件
			newFeeInfo.setCaseApply(apply);
			this.customReposity.saveEntity(newFeeInfo);
		}
	}
	
	@Override
	public Page<Map<String, Object>> findReceiveCustomer(String caseApplyId, Pageable pageInfo) {
		return this.customReposity.findReceiveCustomer(caseApplyId,pageInfo);
	}
	
	@Override
	public Page<Map<String, Object>> findEvaluationInfos(List<QueryObj> queryInfo,PageRequest pageReq) {
		return this.customReposity.findEvaluationInfos(queryInfo,pageReq);
	}
	
	@Override
	public List<Map<String, Object>> findReviceObjTypeInfosByCaseApplyId(String caseApplyId) throws BusinessException {
		logger.info("查询收费对象类型下拉数据");
		logger.debug("案件Id为：{}", caseApplyId);
		if (ObjectHelper.isEmpty(caseApplyId)) {
			throw new BusinessException("10010004","传入案件Id为空！");
		}
		return this.customReposity.findReviceObjTypeInfosByCaseApplyId(caseApplyId);
	}
	
	@Override
	public List<FeeInfomation> findByCaseApplyIdAndReceiveObjTypes(String caseApplyId, List<String> receiveObjTypes) throws BusinessException {
		logger.info("查询收费明细");
		logger.debug("案件Id为：{}", caseApplyId);
		logger.debug("收费对象类型为：{}", receiveObjTypes);
		if (ObjectHelper.isEmpty(caseApplyId) || ObjectHelper.isEmpty(receiveObjTypes)) {
			throw new BusinessException("10010004","传入案件Id或收费对象类型为空！");
		}
		return this.customReposity.findByCaseApplyIdAndReceiveObjTypes(caseApplyId, receiveObjTypes);
	}

	@Override
	public List<FeeInfomation> findFeeByCaseApplyId(String caseApplyId) throws BusinessException {
		return feeInfomationRepository.findAllyByCaseApplyId(caseApplyId);
	}

	@Override
	public List<FeeInfomation> findByCaseApplyIdAndPayerType(String caseApplyId, String payerType) throws BusinessException {
		if(ObjectHelper.isEmpty(payerType)){
			return feeInfomationRepository.findAllyByCaseApplyId(caseApplyId);
		}
		return feeInfomationRepository.findByCaseApplyIdAndPayerType(caseApplyId, payerType);
	}

	@Override
	public List<FeeInfomation> findByCaseApplyIdAndFeeItemAndFeeType(String caseApplyId,String feeItem, String feeType) {
		return feeInfomationRepository.findByCaseApplyIdAndFeeItemAndFeeType(caseApplyId,feeItem,feeType);
	}

	@Override
	public Page<Map<String, Object>> getCaseApplyAndFeeList(PageRequest pageRequest, List<QueryObj> li) throws Exception { 
		StringBuffer _sql=new StringBuffer();
		_sql.append(" select  ");
		_sql.append("  mca.id,fin.balanceAmount,fin.paidAmount,fin.receivedAmount,cus.customerName,mca.stage, ");
		_sql.append("  mca.caseApplyCode,mca.productSubtypeName,mca.productTypeName, mca.applyAmount,mca.applyDate, mca.developmentDepartmentName ");
		_sql.append("  from mkt_case_apply mca   ");
		_sql.append("  INNER JOIN ( SELECT caseApplyId,SUM (balanceAmount) balanceAmount,SUM (receivedAmount) receivedAmount,SUM (paidAmount) paidAmount  FROM case_fee_infomation  GROUP BY caseApplyId )fin  on fin.caseApplyId=mca.ID   ");
		_sql.append(" LEFT JOIN case_before_customer cbc on cbc.caseApplyId=mca.id  and cbc.joinType='"+ CaseApplyAfterCustomer.MAIN_BORROW + "' ");
		_sql.append(" LEFT JOIN cust_before_customer cus on cus.id=cbc.customerId ");
		_sql.append("  where 1=1 ");
		StringBuffer _extendSql=new StringBuffer();
		_extendSql.append(" order by mca.applyDate desc ");
		Page<Map<String, Object>> page = this.customReposity.getListObjectBySql(pageRequest, li, _sql, _extendSql);
		for(Map<String, Object> map : page.getRecords()){
			if(ObjectHelper.isNotEmpty(map.get("STAGE"))){
				map.put("STAGE",CED.loadSimpleCodeNameByFullCode(String.valueOf(map.get("STAGE"))));
			}
		}
		
		return  page;
	}

}