package com.zdsoft.finance.casemanage.datasurvey.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.datasurvey.entity.FeeInfomation;
import com.zdsoft.finance.casemanage.datasurvey.repository.FeeInfomationRepository;
import com.zdsoft.finance.casemanage.datasurvey.service.FeeInfomationService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.CaseApply;
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
public class FeeInfomationServiceImpl extends BaseServiceImpl<FeeInfomation,FeeInfomationRepository>
		implements FeeInfomationService {
	
	@Resource
	private CaseApplyService caseApplyService;
	
	@Resource
	private FeeOptionService feeOptionService;
	
	@Resource
	private CED CED;

	@Override
	public List<FeeInfomation> findFeeInfomationByCaseApplyId(String caseApplyId) throws BusinessException {
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
					info.setFeeType(option.getChargeTypeCode());
					info.setFeeTypeName(option.getChargeTypeName());
					// 费用项
					info.setFeeItem(option.getChargingItemCode());
					info.setFeeItemName(option.getChargingItemName());
					// 预计应收 通过配置计算出来
					if (FEE_CALCULATE_FIXED.equals(option.getCollectionMethodCode()) && ObjectHelper.isNotEmpty(option.getCollectionAmount())) {
						info.setExpectedAmount(option.getCollectionAmount());
					} else if (FEE_CALCULATE_RATIO.equals(option.getCollectionMethodCode()) && ObjectHelper.isNotEmpty(option.getCollectionRatio())) {
						info.setExpectedAmount(applyAmount.multiply(BigDecimal.valueOf(option.getCollectionRatio())));
					}
					// 预计应付 通过配置计算出来
					if (FEE_PAY_CALCULATE_FIXED.equals(option.getPaymentMethodCode()) && ObjectHelper.isNotEmpty(option.getPaymentAmount())) {
						info.setExpectedPayableAmount(option.getPaymentAmount());
					} else if (FEE_PAY_CALCULATE_RATIO.equals(option.getPaymentMethodCode()) && ObjectHelper.isNotEmpty(option.getPaymentRatio())) {
						info.setExpectedPayableAmount(applyAmount.multiply(BigDecimal.valueOf(option.getPaymentRatio())));
					}
					resultList.add(info);
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
		CaseApply apply = this.caseApplyService.findOne(caseApplyId);
		if (ObjectHelper.isEmpty(apply)) {
			throw new BusinessException("10010002", "案件Id为:" + caseApplyId);
		}
		// 解除关系
		apply.setFeeInfoList(null);
		apply = this.caseApplyService.updateEntity(apply);
		// 旧数据全部删除
		List<FeeInfomation> oldInfo = this.findFeeInfomationByCaseApplyId(caseApplyId);
		if (ObjectHelper.isNotEmpty(oldInfo)) {
			this.customReposity.delete(oldInfo);
		}
		List<FeeInfomation> newInfo = new ArrayList<FeeInfomation>();
		EmpDto loginUser = CED.getLoginUser();
		Date currentDate = new Date();
		// 循环保存新数据
		for (FeeInfomation info : feeInfos) {
			info.setId(null);
			info.setCreateBy(loginUser.getEmpCd());
			info.setCreateOrgCd(loginUser.getOrgCd());
			info.setCreateTime(currentDate);
			// 关联案件
			info.setCaseApply(apply);
			info = this.customReposity.saveEntity(info);
			newInfo.add(info);
		}
		// 更新关系
		apply.setFeeInfoList(newInfo);
		this.caseApplyService.updateEntity(apply);
	}
	
	@Override
	public Page<Map<String, Object>> findReceiveCustomer(String caseApplyId, Pageable pageInfo) {
		return this.customReposity.findReceiveCustomer(caseApplyId,pageInfo);
	}
	
	@Override
	public Page<Map<String, Object>> findEvaluationInfos(List<QueryObj> queryInfo,PageRequest pageReq) {
		return this.customReposity.findEvaluationInfos(queryInfo,pageReq);
	}
}