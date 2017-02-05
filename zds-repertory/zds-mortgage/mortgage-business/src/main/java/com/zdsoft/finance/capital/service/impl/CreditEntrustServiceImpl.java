package com.zdsoft.finance.capital.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.capital.entity.CreditCostTracking;
import com.zdsoft.finance.capital.entity.CreditEntrust;
import com.zdsoft.finance.capital.entity.CreditEntrustAttom;
import com.zdsoft.finance.capital.entity.CreditEntrustDebit;
import com.zdsoft.finance.capital.entity.CreditEntrustPrincipal;
import com.zdsoft.finance.capital.entity.CreditEntrustRedemPrinci;
import com.zdsoft.finance.capital.entity.LoanCapital;
import com.zdsoft.finance.capital.entity.SpareCapital;
import com.zdsoft.finance.capital.repository.CreditEntrustRepository;
import com.zdsoft.finance.capital.service.CreditCostTrackingService;
import com.zdsoft.finance.capital.service.CreditEntrustAttomService;
import com.zdsoft.finance.capital.service.CreditEntrustDebitService;
import com.zdsoft.finance.capital.service.CreditEntrustPrincipalService;
import com.zdsoft.finance.capital.service.CreditEntrustRedemPrinciService;
import com.zdsoft.finance.capital.service.CreditEntrustService;
import com.zdsoft.finance.capital.service.LoanCapitalService;
import com.zdsoft.finance.capital.service.SpareCapitalService;
import com.zdsoft.finance.casemanage.limitapply.entity.CaseLimitApply;
import com.zdsoft.finance.casemanage.limitapply.service.CaseLimitApplyService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

@Service("creditEntrustService")
public class CreditEntrustServiceImpl extends BaseServiceImpl<CreditEntrust, CustomRepository<CreditEntrust, String>>
		implements CreditEntrustService {

	@Autowired
	CreditEntrustRepository creditEntrustRepository;

	@Autowired
	CED CED;

	@Autowired
	CreditCostTrackingService creditCostTrackingService;

	@Autowired
	CreditEntrustAttomService creditEntrustAttomService;

	@Autowired
	CreditEntrustDebitService creditEntrustDebitService;

	@Autowired
	CreditEntrustPrincipalService creditEntrustPrincipalService;

	@Autowired
	LoanCapitalService loanCapitalService;

	@Autowired
	SpareCapitalService spareCapitalService;

	@Autowired
	CreditEntrustRedemPrinciService creditEntrustRedemPrinciService;

	@Autowired
	CaseLimitApplyService caseLimitApplyService;

	@Override
	@Transactional
	public CreditEntrust saveCreditEntrustAndOthers(CreditEntrust creditEntrust) throws Exception {

		// 先保存主表信息
		String tempUuid = creditEntrust.getTempUuid();

		// 设置基本信息
		creditEntrust.setCreateBy(CED.getLoginUser().getEmpCd());
		creditEntrust.setCreateTime(new Date());
		creditEntrust.setCreateOrgCd(CED.getLoginUser().getOrgCd());

		// 保存独有信息
		creditEntrust = creditEntrustRepository.saveEntity(creditEntrust);

		// 关联附属信息
		List<CreditCostTracking> creditCostTrackings = creditCostTrackingService.findByTempUuid(tempUuid);
		List<CreditEntrustAttom> creditEntrustAttoms = creditEntrustAttomService.findByTempUuid(tempUuid);
		List<CreditEntrustDebit> creditEntrustDebits = creditEntrustDebitService.findByTempUuid(tempUuid);
		List<CreditEntrustPrincipal> creditEntrustPrincipals = creditEntrustPrincipalService.findByTempUuid(tempUuid);
		List<LoanCapital> loanCapitals = loanCapitalService.findByTempUuid(tempUuid);
		List<SpareCapital> spareCapitals = spareCapitalService.findByTempUuid(tempUuid);
		List<CreditEntrustRedemPrinci> redemPrincis = creditEntrustRedemPrinciService.findByTempUuid(tempUuid);

		creditEntrust.setCreditCostTrackings(creditCostTrackings);
		creditEntrust.setCreditEntrustAttoms(creditEntrustAttoms);
		creditEntrust.setCreditEntrustDebits(creditEntrustDebits);
		creditEntrust.setCreditEntrustPrincipals(creditEntrustPrincipals);
		creditEntrust.setLoanCapitals(loanCapitals);
		creditEntrust.setSpareCapitals(spareCapitals);
		creditEntrust.setCreditEntrustRedemPrincis(redemPrincis);

		creditEntrust = creditEntrustRepository.updateEntity(creditEntrust);

		BigDecimal incomeBalance = BigDecimal.ZERO;
		// 设置附属表关联主表信息
		for (CreditCostTracking tracking : creditCostTrackings) {
			tracking.setCreditEntrust(creditEntrust);
			tracking.setTempUuid(null);
			creditCostTrackingService.updateEntity(tracking);
		}

		for (CreditEntrustAttom attom : creditEntrustAttoms) {
			attom.setCreditEntrust(creditEntrust);
			attom.setTempUuid(null);
			creditEntrustAttomService.updateEntity(attom);
		}
		for (CreditEntrustDebit debit : creditEntrustDebits) {
			debit.setCreditEntrust(creditEntrust);
			debit.setTempUuid(tempUuid);
			creditEntrustDebitService.updateEntity(debit);
		}

		for (CreditEntrustPrincipal principal : creditEntrustPrincipals) {
			principal.setCreditEntrust(creditEntrust);
			principal.setTempUuid(tempUuid);
			creditEntrustPrincipalService.updateEntity(principal);

			incomeBalance = incomeBalance.add(principal.getPrincipalAmount());
		}

		for (LoanCapital loan : loanCapitals) {
			loan.setCreditEntrust(creditEntrust);
			loan.setTempUuid(tempUuid);
			loanCapitalService.updateEntity(loan);

			incomeBalance = incomeBalance.add(loan.getTotalAmount());
		}
		BigDecimal paymentBalance = BigDecimal.ZERO;
		for (SpareCapital spareCapital : spareCapitals) {
			spareCapital.setCreditEntrust(creditEntrust);
			spareCapital.setTempUuid(tempUuid);
			spareCapitalService.updateEntity(spareCapital);

			paymentBalance = paymentBalance.add(spareCapital.getActualAmount());
		}

		// 设置备付金余额
		creditEntrust.setPaymentBalance(paymentBalance);

		// 设置收入总额
		creditEntrust.setIncomeBalance(incomeBalance);

		for (CreditEntrustRedemPrinci creditEntrustRedemPrinci : redemPrincis) {
			creditEntrustRedemPrinci.setCreditEntrust(creditEntrust);
			creditEntrustRedemPrinci.setTempUuid(tempUuid);
			creditEntrustRedemPrinciService.updateEntity(creditEntrustRedemPrinci);
		}

		creditEntrust = creditEntrustRepository.updateEntity(creditEntrust);
		return creditEntrust;
	}

	@Override
	public Page<Map<String, Object>> reportSql(Pageable pageable, Map<String, Object> conditions) {
		return creditEntrustRepository.getListMapBySql(pageable, conditions);
	}

	@Override
	public Page<Map<String, Object>> reportPlanDistributionSql(Pageable pageable, Map<String, Object> conditions) {
		return creditEntrustRepository.reportPlanDistributionSql(pageable, conditions);
	}

	@Override
	@Transactional
	public CreditEntrust updateCreditEntrust(CreditEntrust creditEntrust) throws Exception {

		// 判断传入参数
		if (ObjectHelper.isEmpty(creditEntrust) || ObjectHelper.isEmpty(creditEntrust.getId())) {
			throw new BusinessException("10010003", "传入参数有错误");
		}

		// 查询原有信托计划
		CreditEntrust oldCreditEntrust = creditEntrustRepository.findOne(creditEntrust.getId());

		// 判断查询结果
		if (ObjectHelper.isEmpty(oldCreditEntrust)) {
			throw new BusinessException("10010004", "传入参数为空");
		}

		// 执行赋值操作
		BeanUtils.copyProperties(creditEntrust, oldCreditEntrust,
				new String[] { "id", "createTime", "createBy", "createOrgCd", "capitallist", "creditEntrustAttoms",
						"creditEntrustPrincipals", "spareCapitals", "creditEntrustDebits", "loanCapitals",
						"creditCostTrackings" });

		oldCreditEntrust.setUpdateBy(CED.getLoginUser().getEmpCd());
		oldCreditEntrust.setUpdateTime(new Date());
		oldCreditEntrust.setUpdateOrgCd(CED.getLoginUser().getOrgCd());
		return creditEntrustRepository.updateEntity(oldCreditEntrust);
	}

	@Override
	public List<CreditEntrust> findByConditions(Map<String, Object> conditions) {
		return creditEntrustRepository.findByConditions(conditions);
	}

	@Override
	@Transactional
	public CreditEntrust updateIncomeBalance(CreditEntrust creditEntrust, BigDecimal amount) throws BusinessException {
		BigDecimal tempAmount = creditEntrust.getIncomeBalance();
		// 如果分配的金额大于余额，则直接抛出异常
		if (tempAmount.compareTo(amount) < 0) {
			// TODO .. 暂定
			throw new BusinessException("", "申请额度失败，收入余额小于申请金额");
		}

		BigDecimal leaveAmount = tempAmount.subtract(amount);
		creditEntrust.setIncomeBalance(leaveAmount);
		return creditEntrustRepository.updateEntity(creditEntrust);
	}

	@Override
	@Transactional
	public CreditEntrust updatePaymentBalance(CreditEntrust creditEntrust, BigDecimal amount) throws BusinessException {
		BigDecimal tempAmount = creditEntrust.getPaymentBalance();

		// 如果分配的金额大于余额，则直接抛出异常
		if (tempAmount.compareTo(amount) < 0) {
			// TODO .. 暂定
			throw new BusinessException("", "分配备付金失败，备付金余额小于分配金额");
		}

		BigDecimal leaveAmount = tempAmount.subtract(amount);
		creditEntrust.setPaymentBalance(leaveAmount);
		creditEntrust = creditEntrustRepository.updateEntity(creditEntrust);

		// 更新备付金资金信息
		List<SpareCapital> spareCapitals = spareCapitalService
				.findByCreditEntrustIdAndOrderByCreteTime(creditEntrust.getId());

		for (int i = 0; i < spareCapitals.size(); i++) {
			// 判断金额是否足够
			if (spareCapitals.get(i).getDistributionAmount().compareTo(amount) >= 0) {
				// 足够，则直接减去第一个，并且停止循环
				spareCapitals.get(i)
						.setDistributionAmount(spareCapitals.get(i).getDistributionAmount().subtract(amount));
				spareCapitalService.updateEntity(spareCapitals.get(i));
				break;
			} else {
				// 不足够，则减去第一个，继续循环
				amount = amount.subtract(spareCapitals.get(i).getDistributionAmount());
				spareCapitals.get(i).setDistributionAmount(BigDecimal.ZERO);
				spareCapitalService.updateEntity(spareCapitals.get(i));
			}
		}

		return creditEntrust;
	}

	@Override
	@Transactional
	public CreditEntrust reservePaymentBalance(CreditEntrust creditEntrust, BigDecimal amount)
			throws BusinessException {
		BigDecimal tempAmount = creditEntrust.getPaymentBalance();

		BigDecimal leaveAmount = tempAmount.add(amount);
		creditEntrust.setPaymentBalance(leaveAmount);
		creditEntrust = creditEntrustRepository.updateEntity(creditEntrust);

		// 更新备付金资金信息
		List<SpareCapital> spareCapitals = spareCapitalService
				.findByCreditEntrustIdAndOrderByCreteTime(creditEntrust.getId());

		for (int i = 0; i < spareCapitals.size(); i++) {
			// 判断金额是否足够 未分配 + 还原金额 <= 实际到账金额
			if ((spareCapitals.get(i).getDistributionAmount().add(amount))
					.compareTo(spareCapitals.get(i).getActualAmount()) <= 0) {
				// 足够，则直接加上第一个，并且停止循环
				spareCapitals.get(i).setDistributionAmount(spareCapitals.get(i).getDistributionAmount().add(amount));
				spareCapitalService.updateEntity(spareCapitals.get(i));
				break;
			} else {
				// 不足够，则减去第一个，继续循环,并设置未分配金额为实际到账金额，继续循环
				amount = amount.subtract(
						spareCapitals.get(i).getActualAmount().subtract(spareCapitals.get(i).getDistributionAmount()));
				spareCapitals.get(i).setDistributionAmount(spareCapitals.get(i).getActualAmount());
				spareCapitalService.updateEntity(spareCapitals.get(i));
			}
		}

		return creditEntrust;
	}

	@Override
	@Transactional
	public CreditEntrust confirmInfo(CreditEntrust creditEntrust, CreditEntrust creditEntrustNew, BigDecimal amount)
			throws BusinessException {
		// 恢复原有信托计划 收入总计
		updateIncomeBalance(creditEntrust, new BigDecimal("-" + amount.toString()));
		// 恢复原有信托计划 备付金
		reservePaymentBalance(creditEntrust, amount);

		// 修改新计划收入总计
		updateIncomeBalance(creditEntrustNew, amount);
		// 修改新计划备付金
		updatePaymentBalance(creditEntrustNew, amount);
		return creditEntrust;
	}

	@Override
	@Transactional
	public CreditEntrust oneKeyFundMatching(String creditEntrustId, String state) throws BusinessException {

		// 查询信托计划
		CreditEntrust creditEntrust = creditEntrustRepository.findOne(creditEntrustId);

		// TODO .. 此处应该查询已申请未分配的案件
		List<CaseLimitApply> caseLimitApplies = caseLimitApplyService.findByCreditEntrustId(creditEntrustId, state);

		BigDecimal tempPayment = creditEntrust.getPaymentBalance();
		for (CaseLimitApply caseLimitApply : caseLimitApplies) {

			// 首先剩余额度必须大于0，否则直接跳出循环
			if (tempPayment.compareTo(BigDecimal.ZERO) > 0) {

				// 剩余额度满足案件需求
				if (tempPayment.compareTo(caseLimitApply.getCurrentApplyLimit()) > 0) {
					updatePaymentBalance(creditEntrust, caseLimitApply.getCurrentApplyLimit());
					tempPayment = tempPayment.subtract(caseLimitApply.getCurrentApplyLimit());
				} else if (tempPayment.compareTo(caseLimitApply.getCurrentApplyLimit()) == 0) {
					updatePaymentBalance(creditEntrust, caseLimitApply.getCurrentApplyLimit());
					break;
				} else {
					updatePaymentBalance(creditEntrust, tempPayment);
					break;
				}
			} else {
				break;
			}

		}
		return creditEntrust;
	}

}
