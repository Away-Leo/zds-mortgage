package com.zdsoft.finance.capital.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.capital.entity.CreditEntrust;
import com.zdsoft.finance.capital.entity.CreditEntrustFeeItem;
import com.zdsoft.finance.capital.entity.CreditEntrustOperationLog;
import com.zdsoft.finance.capital.entity.LoanCapital;
import com.zdsoft.finance.capital.repository.LoanCapitalRepository;
import com.zdsoft.finance.capital.service.CreditEntrustFeeItemService;
import com.zdsoft.finance.capital.service.CreditEntrustOperationLogService;
import com.zdsoft.finance.capital.service.CreditEntrustService;
import com.zdsoft.finance.capital.service.FeeItemService;
import com.zdsoft.finance.capital.service.LoanCapitalService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.spi.common.dto.OperationTypeNm;
import com.zdsoft.finance.spi.common.dto.StatusNm;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 专户贷方资金跟踪Service
 * 
 * @author liuwei
 * @date 2016年12月23日
 * @version 1.0
 */
@Service("looanCapitalService")
public class LoanCapitalServiceImpl extends BaseServiceImpl<LoanCapital, CustomRepository<LoanCapital, String>>
		implements LoanCapitalService {

	@Autowired
	CED CED;

	@Autowired
	LoanCapitalRepository loanCapitalRepository;

	@Autowired
	CreditEntrustFeeItemService creditEntrustFeeItemService;

	@Autowired
	FeeItemService feeItemService;

	@Autowired
	CreditEntrustOperationLogService creditEntrustOperationLogService;

	@Autowired
	CreditEntrustService creditEntrustService;

	@Override
	@Transactional
	public LoanCapital saveOrUpdateLoanCapital(LoanCapital loanCapital) throws Exception {

		BigDecimal totalAmount = loanCapital.getTotalAmount();

		if (ObjectHelper.isNotEmpty(loanCapital.getId())) {// id存在
			LoanCapital oldLoanCapital = loanCapitalRepository.findOne(loanCapital.getId());
			totalAmount = oldLoanCapital.getTotalAmount();
			// 设置基本信息
			oldLoanCapital.setUpdateBy(CED.getLoginUser().getEmpCd());
			oldLoanCapital.setUpdateTime(new Date());
			oldLoanCapital.setUpdateOrgCd(CED.getLoginUser().getOrgCd());

			// 保存跟踪信息
			oldLoanCapital.setLenderType(loanCapital.getLenderType());
			oldLoanCapital.setLenderName(loanCapital.getLenderName());
			oldLoanCapital.setTotalAmount(loanCapital.getTotalAmount());
			oldLoanCapital.setHappenDate(loanCapital.getHappenDate());
			oldLoanCapital.setCapitalState(loanCapital.getCapitalState());
			oldLoanCapital.setRemark(loanCapital.getRemark());

			// 更新费用
			List<CreditEntrustFeeItem> feeItems = loanCapital.getCreditEntrustFeeItems();

			// 将原有费用逻辑删除
			List<CreditEntrustFeeItem> oldFeeItems = creditEntrustFeeItemService.findByBusinessId(loanCapital.getId());
			for (CreditEntrustFeeItem creditEntrustFeeItem : oldFeeItems) {
				creditEntrustFeeItemService.logicDelete(creditEntrustFeeItem);
			}

			// 持久最新的费用
			for (CreditEntrustFeeItem creditEntrustFeeItem : feeItems) {
				creditEntrustFeeItem.setBusinessId(loanCapital.getId());
				creditEntrustFeeItemService.saveEntity(creditEntrustFeeItem);
			}

			loanCapital = loanCapitalRepository.updateEntity(oldLoanCapital);

		} else { // id不存在

			// 保存基本信息
			loanCapital.setCreateBy(CED.getLoginUser().getEmpCd());
			loanCapital.setCreateTime(new Date());
			loanCapital.setCreateOrgCd(CED.getLoginUser().getOrgCd());

			loanCapital.setStatus(StatusNm.SUBMIT.value);

			// 提交人/提交时间
			loanCapital.setCompleteEmpCd(CED.getLoginUser().getEmpCd());
			loanCapital.setCompleteEmpName(CED.getLoginUser().getEmpNm());
			loanCapital
					.setCompleteDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_SHORT_SIMPLE_FORMAT_WITHMINUTE));

			// 保存贷方资金跟踪信息
			loanCapital = loanCapitalRepository.saveEntity(loanCapital);

			// 保存特有费用
			List<CreditEntrustFeeItem> feeItems = loanCapital.getCreditEntrustFeeItems();
			for (CreditEntrustFeeItem creditEntrustFeeItem : feeItems) {
				creditEntrustFeeItem.setBusinessId(loanCapital.getId());
				creditEntrustFeeItemService.saveEntity(creditEntrustFeeItem);
			}

			CreditEntrustOperationLog operationLog = new CreditEntrustOperationLog();
			operationLog.setOperationType(OperationTypeNm.SUBMIT.value);
			operationLog.setOperationContent("专户贷方资金跟踪");
			operationLog.setOperationEmpCd(CED.getLoginUser().getEmpCd());
			operationLog.setOperationEmpName(CED.getLoginUser().getEmpNm());
			operationLog.setOperationDate(
					DateHelper.dateToLong(new Date(), DateHelper.DATE_SHORT_SIMPLE_FORMAT_WITHMINUTE));
			operationLog
					.setActualDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_SHORT_SIMPLE_FORMAT_WITHMINUTE));
			// operationLog.setStatus();
			operationLog.setBusinessId(loanCapital.getId());
			creditEntrustOperationLogService.saveEntity(operationLog);

			totalAmount = loanCapital.getTotalAmount();
		}

		if (ObjectHelper.isNotEmpty(loanCapital.getCreditEntrust())) {
			CreditEntrust creditEntrust = loanCapital.getCreditEntrust();
			BigDecimal incomeBalance = creditEntrust.getIncomeBalance();
			incomeBalance = (incomeBalance == null ? BigDecimal.ZERO : incomeBalance)
					.subtract(totalAmount == null ? BigDecimal.ZERO : totalAmount)
					.add(loanCapital.getTotalAmount() == null ? BigDecimal.ZERO : loanCapital.getTotalAmount());

			creditEntrust.setIncomeBalance(incomeBalance);
			creditEntrustService.updateEntity(creditEntrust);
		}

		return loanCapital;
	}

	@Override
	public List<LoanCapital> findByTempUuid(String tempUuid) {
		return loanCapitalRepository.findByTempUuid(tempUuid);
	}

	@Override
	public List<LoanCapital> findByConditions(Map<String, Object> conditions) {
		return loanCapitalRepository.findByConditions(conditions);
	}

}
