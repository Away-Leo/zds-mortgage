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
import com.zdsoft.finance.capital.service.CreditEntrustToolService;
import com.zdsoft.finance.capital.service.FeeItemService;
import com.zdsoft.finance.capital.service.LoanCapitalService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.spi.common.dto.OperationTypeNm;
import com.zdsoft.finance.spi.common.dto.StatusNm;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: LoanCapitalServiceImpl.java
 * @ClassName: LoanCapitalServiceImpl
 * @Description: 专户贷方资金跟踪ServiceImpl
 * @author liuwei
 * @date 2017年2月8日 上午10:40:43
 * @version V1.0
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

	@Autowired
	CreditEntrustToolService creditEntrustToolService;

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
			for (int i = 0; i < oldFeeItems.size(); i++) {
				creditEntrustFeeItemService.logicDelete(oldFeeItems.get(i));
			}

			// 持久最新的费用
			for (int i = 0; i < feeItems.size(); i++) {
				CreditEntrustFeeItem creditEntrustFeeItem = feeItems.get(i);
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
			loanCapital.setCompleteDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));

			// 保存贷方资金跟踪信息
			loanCapital = loanCapitalRepository.saveEntity(loanCapital);

			// 保存特有费用
			List<CreditEntrustFeeItem> feeItems = loanCapital.getCreditEntrustFeeItems();
			for (int i = 0; i < feeItems.size(); i++) {
				CreditEntrustFeeItem creditFeeItem = feeItems.get(i);
				creditFeeItem.setBusinessId(loanCapital.getId());
				creditEntrustFeeItemService.saveEntity(creditFeeItem);
			}

			CreditEntrustOperationLog operationLog = new CreditEntrustOperationLog();
			operationLog.setOperationType(OperationTypeNm.SUBMIT.value);
			operationLog.setOperationContent("专户贷方资金跟踪");
			operationLog.setOperationEmpCd(CED.getLoginUser().getEmpCd());
			operationLog.setOperationEmpName(CED.getLoginUser().getEmpNm());
			operationLog.setOperationDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
			operationLog.setActualDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
			// operationLog.setStatus();
			operationLog.setBusinessId(loanCapital.getId());
			creditEntrustOperationLogService.saveEntity(operationLog);

			totalAmount = BigDecimal.ZERO;
		}

		// 修改收入金额
		if (ObjectHelper.isNotEmpty(loanCapital.getCreditEntrust())) {
			CreditEntrust creditEntrust = loanCapital.getCreditEntrust();
			BigDecimal incomeBalance = creditEntrust.getIncomeBalance();
			incomeBalance = (incomeBalance == null ? BigDecimal.ZERO : incomeBalance)
					.subtract(totalAmount == null ? BigDecimal.ZERO : totalAmount)
					.add(loanCapital.getTotalAmount() == null ? BigDecimal.ZERO : loanCapital.getTotalAmount());

			creditEntrust.setIncomeBalance(incomeBalance);
			creditEntrust = creditEntrustService.updateEntity(creditEntrust);

			// 重新填充列表统计信息
			creditEntrust = creditEntrustToolService.listFill(creditEntrust);
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
