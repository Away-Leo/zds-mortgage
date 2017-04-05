package com.zdsoft.finance.capital.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.capital.entity.CreditCostTracking;
import com.zdsoft.finance.capital.entity.CreditEntrust;
import com.zdsoft.finance.capital.entity.CreditEntrustFeeItem;
import com.zdsoft.finance.capital.entity.CreditEntrustOperationLog;
import com.zdsoft.finance.capital.repository.CreditCostTrackingRepository;
import com.zdsoft.finance.capital.service.CreditCostTrackingService;
import com.zdsoft.finance.capital.service.CreditEntrustFeeItemService;
import com.zdsoft.finance.capital.service.CreditEntrustOperationLogService;
import com.zdsoft.finance.capital.service.CreditEntrustService;
import com.zdsoft.finance.capital.service.CreditEntrustToolService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.spi.common.dto.OperationTypeNm;
import com.zdsoft.finance.spi.common.dto.StatusNm;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CreditCostTrackingServiceImpl.java
 * @ClassName: CreditCostTrackingServiceImpl
 * @Description: 应付资金跟踪serviceImpl
 * @author liuwei
 * @date 2017年2月8日 上午10:38:07
 * @version V1.0
 */
@Service("creditCostTrackingService")
public class CreditCostTrackingServiceImpl
		extends BaseServiceImpl<CreditCostTracking, CustomRepository<CreditCostTracking, String>>
		implements CreditCostTrackingService {

	@Autowired
	CED CED;

	@Autowired
	CreditCostTrackingRepository creditCostTrackingRepository;

	@Autowired
	CreditEntrustFeeItemService creditEntrustFeeItemService;

	@Autowired
	CreditEntrustOperationLogService creditEntrustOperationLogService;

	@Autowired
	CreditEntrustService creditEntrustService;

	@Autowired
	CreditEntrustToolService creditEntrustToolService;

	@Override
	@Transactional
	public CreditCostTracking saveOrUpdateCostTracking(CreditCostTracking creditCostTracking) throws Exception {
		if (ObjectHelper.isNotEmpty(creditCostTracking.getId())) {// id存在
			CreditCostTracking oldCostTracking = creditCostTrackingRepository.findOne(creditCostTracking.getId());

			// 设置基本信息
			oldCostTracking.setUpdateBy(CED.getLoginUser().getEmpCd());
			oldCostTracking.setUpdateTime(new Date());
			oldCostTracking.setUpdateOrgCd(CED.getLoginUser().getOrgCd());

			// 保存应付费用信息
			oldCostTracking.setExpenditureType(creditCostTracking.getExpenditureType());
			oldCostTracking.setCostName(creditCostTracking.getCostName());
			oldCostTracking.setSummary(creditCostTracking.getSummary());
			oldCostTracking.setTotalAmount(creditCostTracking.getTotalAmount());
			oldCostTracking.setPayDate(creditCostTracking.getPayDate());
			oldCostTracking.setRemark(creditCostTracking.getRemark());

			// 更新费用
			List<CreditEntrustFeeItem> feeItems = creditCostTracking.getCreditEntrustFeeItems();

			// 将原有费用逻辑删除
			List<CreditEntrustFeeItem> oldFeeItems = creditEntrustFeeItemService
					.findByBusinessId(oldCostTracking.getId());

			for (int i = 0; i < oldFeeItems.size(); i++) {
				CreditEntrustFeeItem creditEntrustFeeItem = oldFeeItems.get(i);
				creditEntrustFeeItemService.logicDelete(creditEntrustFeeItem);
			}

			// 持久最新的费用
			for (int i = 0; i < feeItems.size(); i++) {
				CreditEntrustFeeItem feeItem = feeItems.get(i);
				feeItem.setBusinessId(oldCostTracking.getId());
				creditEntrustFeeItemService.saveEntity(feeItem);
			}

			creditCostTracking = creditCostTrackingRepository.updateEntity(oldCostTracking);

		} else { // id不存在

			// 保存基本信息
			creditCostTracking.setCreateBy(CED.getLoginUser().getEmpCd());
			creditCostTracking.setCreateTime(new Date());
			creditCostTracking.setCreateOrgCd(CED.getLoginUser().getOrgCd());

			creditCostTracking.setStatus(StatusNm.SUBMIT.value);

			// 提交人/提交时间
			creditCostTracking.setCompleteEmpCd(CED.getLoginUser().getEmpCd());
			creditCostTracking.setCompleteEmpName(CED.getLoginUser().getEmpNm());
			creditCostTracking.setCompleteDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));

			// 保存应付费用信息
			creditCostTracking = creditCostTrackingRepository.saveEntity(creditCostTracking);

			// 保存特有费用
			List<CreditEntrustFeeItem> feeItems = creditCostTracking.getCreditEntrustFeeItems();
			for (int i = 0; i < feeItems.size(); i++) {
				CreditEntrustFeeItem feeItem = feeItems.get(i);
				feeItem.setBusinessId(creditCostTracking.getId());
				creditEntrustFeeItemService.saveEntity(feeItem);
			}

			CreditEntrustOperationLog operationLog = new CreditEntrustOperationLog();
			operationLog.setOperationType(OperationTypeNm.SUBMIT.value);
			operationLog.setOperationContent("应付资金跟踪");
			operationLog.setOperationEmpCd(CED.getLoginUser().getEmpCd());
			operationLog.setOperationEmpName(CED.getLoginUser().getEmpNm());
			operationLog.setOperationDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
			operationLog.setActualDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
			// operationLog.setStatus();
			operationLog.setBusinessId(creditCostTracking.getId());
			creditEntrustOperationLogService.saveEntity(operationLog);
		}

		// 重新填充列表信息
		if (ObjectHelper.isNotEmpty(creditCostTracking.getCreditEntrust())) {
			CreditEntrust creditEntrust = creditEntrustToolService.listFill(creditCostTracking.getCreditEntrust());
			creditEntrust = creditEntrustService.updateEntity(creditEntrust);
		}

		return creditCostTracking;
	}

	@Override
	public List<CreditCostTracking> findByTempUuid(String tempUuid) {
		return creditCostTrackingRepository.findByTempUuid(tempUuid);
	}

	@Override
	public List<CreditCostTracking> findByConditions(Map<String, Object> conditions) {
		return creditCostTrackingRepository.findByConditions(conditions);
	}

}
