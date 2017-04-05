package com.zdsoft.finance.capital.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.capital.entity.CreditEntrust;
import com.zdsoft.finance.capital.entity.CreditEntrustDebit;
import com.zdsoft.finance.capital.entity.CreditEntrustFeeItem;
import com.zdsoft.finance.capital.entity.CreditEntrustOperationLog;
import com.zdsoft.finance.capital.repository.CreditEntrustDebitRepository;
import com.zdsoft.finance.capital.service.CreditEntrustDebitService;
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
 * @Title: CreditEntrustDebitServiceImpl.java
 * @ClassName: CreditEntrustDebitServiceImpl
 * @Description: 信托计划借方资金ServiceImpl
 * @author liuwei
 * @date 2017年2月8日 上午10:38:43
 * @version V1.0
 */
@Service("creditEntrustDebitService")
public class CreditEntrustDebitServiceImpl
		extends BaseServiceImpl<CreditEntrustDebit, CustomRepository<CreditEntrustDebit, String>>
		implements CreditEntrustDebitService {

	@Autowired
	CreditEntrustDebitRepository creditEntrustDebitRepository;

	@Autowired
	CED CED;

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
	public CreditEntrustDebit saveOrUpdateEntrustDebit(CreditEntrustDebit creditEntrustDebit) throws Exception {
		if (ObjectHelper.isNotEmpty(creditEntrustDebit.getId())) {// id存在
			CreditEntrustDebit oldDebit = creditEntrustDebitRepository.findOne(creditEntrustDebit.getId());

			// 设置基本信息
			oldDebit.setUpdateBy(CED.getLoginUser().getEmpCd());
			oldDebit.setUpdateTime(new Date());
			oldDebit.setUpdateOrgCd(CED.getLoginUser().getOrgCd());

			// 保存借方资金跟踪
			oldDebit.setDebitType(creditEntrustDebit.getDebitType());
			oldDebit.setDebtorType(creditEntrustDebit.getDebtorType());
			oldDebit.setObjectName(creditEntrustDebit.getObjectName());
			oldDebit.setTotalAmount(creditEntrustDebit.getTotalAmount());
			oldDebit.setExpenditureDate(creditEntrustDebit.getExpenditureDate());
			oldDebit.setActualOutDate(creditEntrustDebit.getActualOutDate());
			oldDebit.setCapitalState(creditEntrustDebit.getCapitalState());
			oldDebit.setRemark(creditEntrustDebit.getRemark());

			// 更新费用
			List<CreditEntrustFeeItem> feeItems = creditEntrustDebit.getCreditEntrustFeeItems();

			// 将原有费用逻辑删除
			List<CreditEntrustFeeItem> oldFeeItems = creditEntrustFeeItemService.findByBusinessId(oldDebit.getId());
			for (int i = 0; i < oldFeeItems.size(); i++) {
				creditEntrustFeeItemService.logicDelete(oldFeeItems.get(i));
			}

			// 持久最新的费用
			for (int i = 0; i < feeItems.size(); i++) {
				CreditEntrustFeeItem creditEntrustFeeItem = feeItems.get(i);
				creditEntrustFeeItem.setBusinessId(oldDebit.getId());
				creditEntrustFeeItemService.saveEntity(creditEntrustFeeItem);
			}

			creditEntrustDebit = creditEntrustDebitRepository.updateEntity(oldDebit);

		} else { // id不存在

			// 保存基本信息
			creditEntrustDebit.setCreateBy(CED.getLoginUser().getEmpCd());
			creditEntrustDebit.setCreateTime(new Date());
			creditEntrustDebit.setCreateOrgCd(CED.getLoginUser().getOrgCd());

			creditEntrustDebit.setStatus(StatusNm.SUBMIT.value);

			// 提交人/提交时间
			creditEntrustDebit.setCompleteEmpCd(CED.getLoginUser().getEmpCd());
			creditEntrustDebit.setCompleteEmpName(CED.getLoginUser().getEmpNm());
			creditEntrustDebit.setCompleteDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));

			// 保存应付费用信息
			creditEntrustDebit = creditEntrustDebitRepository.saveEntity(creditEntrustDebit);

			// 保存特有费用
			List<CreditEntrustFeeItem> feeItems = creditEntrustDebit.getCreditEntrustFeeItems();
			for (int i = 0; i < feeItems.size(); i++) {
				CreditEntrustFeeItem creditEntrustFeeItem = feeItems.get(i);
				creditEntrustFeeItem.setBusinessId(creditEntrustDebit.getId());
				creditEntrustFeeItemService.saveEntity(creditEntrustFeeItem);
			}

			CreditEntrustOperationLog operationLog = new CreditEntrustOperationLog();
			operationLog.setOperationType(OperationTypeNm.SUBMIT.value);
			operationLog.setOperationContent("信托计划借方资金");
			operationLog.setOperationEmpCd(CED.getLoginUser().getEmpCd());
			operationLog.setOperationEmpName(CED.getLoginUser().getEmpNm());
			operationLog.setOperationDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
			operationLog.setActualDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
			// operationLog.setStatus();
			operationLog.setBusinessId(creditEntrustDebit.getId());
			creditEntrustOperationLogService.saveEntity(operationLog);
		}

		// 重新填充列表信息
		if (ObjectHelper.isNotEmpty(creditEntrustDebit.getCreditEntrust())) {
			CreditEntrust creditEntrust = creditEntrustToolService.listFill(creditEntrustDebit.getCreditEntrust());
			creditEntrust = creditEntrustService.updateEntity(creditEntrust);
		}

		return creditEntrustDebit;
	}

	@Override
	public List<CreditEntrustDebit> findByTempUuid(String tempUuid) {
		return creditEntrustDebitRepository.findByTempUuid(tempUuid);
	}

	@Override
	public List<CreditEntrustDebit> findByConditions(Map<String, Object> conditions) {
		return creditEntrustDebitRepository.findByConditions(conditions);
	}

}
