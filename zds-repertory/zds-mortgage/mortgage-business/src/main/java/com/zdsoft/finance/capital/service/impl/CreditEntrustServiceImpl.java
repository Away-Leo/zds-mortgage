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
import com.zdsoft.finance.capital.service.CreditEntrustFeeItemService;
import com.zdsoft.finance.capital.service.CreditEntrustPrincipalService;
import com.zdsoft.finance.capital.service.CreditEntrustRedemPrinciService;
import com.zdsoft.finance.capital.service.CreditEntrustService;
import com.zdsoft.finance.capital.service.CreditEntrustToolService;
import com.zdsoft.finance.capital.service.LoanCapitalService;
import com.zdsoft.finance.capital.service.SpareCapitalService;
import com.zdsoft.finance.casemanage.limitApply.entity.CaseLimitApply;
import com.zdsoft.finance.casemanage.limitApply.service.CaseLimitApplyService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.contract.entity.ConCaseContract;
import com.zdsoft.finance.contract.service.ConCaseContractService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CreditEntrustServiceImpl.java
 * @ClassName: CreditEntrustServiceImpl
 * @Description: 信托计划serviceImpl
 * @author liuwei
 * @date 2017年2月8日 上午10:40:06
 * @version V1.0
 */
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

	@Autowired
	CreditEntrustFeeItemService creditEntrustFeeItemService;

	@Autowired
	CreditEntrustToolService creditEntrustToolService;

	@Autowired
	CaseApplyService caseApplyService;

	@Autowired
	ConCaseContractService conCaseContractService;

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

		BigDecimal incomeBalance = BigDecimal.ZERO;
		// 设置附属表关联主表信息
		for (int i = 0; i < creditCostTrackings.size(); i++) {
			CreditCostTracking costTracking = creditCostTrackings.get(i);
			costTracking.setCreditEntrust(creditEntrust);
			costTracking.setTempUuid(null);
			creditCostTrackingService.updateEntity(costTracking);
		}

		for (int i = 0; i < creditEntrustAttoms.size(); i++) {
			CreditEntrustAttom creditEntrustAttom = creditEntrustAttoms.get(i);
			creditEntrustAttom.setCreditEntrust(creditEntrust);
			creditEntrustAttom.setTempUuid(null);
			creditEntrustAttomService.updateEntity(creditEntrustAttom);
		}

		for (int i = 0; i < creditEntrustDebits.size(); i++) {
			CreditEntrustDebit creditEntrustDebit = creditEntrustDebits.get(i);
			creditEntrustDebit.setCreditEntrust(creditEntrust);
			creditEntrustDebit.setTempUuid(tempUuid);
			creditEntrustDebitService.updateEntity(creditEntrustDebit);
		}

		for (int i = 0; i < creditEntrustPrincipals.size(); i++) {
			CreditEntrustPrincipal creditEntrustPrincipal = creditEntrustPrincipals.get(i);
			creditEntrustPrincipal.setCreditEntrust(creditEntrust);
			creditEntrustPrincipal.setTempUuid(tempUuid);
			creditEntrustPrincipalService.updateEntity(creditEntrustPrincipal);

			if (ObjectHelper.isNotEmpty(creditEntrustPrincipal.getPrincipalAmount())) {
				incomeBalance = incomeBalance.add(creditEntrustPrincipal.getPrincipalAmount());
			}
		}

		for (int i = 0; i < loanCapitals.size(); i++) {
			LoanCapital loanCapital = loanCapitals.get(i);
			loanCapital.setCreditEntrust(creditEntrust);
			loanCapital.setTempUuid(tempUuid);
			loanCapitalService.updateEntity(loanCapital);
			if (ObjectHelper.isNotEmpty(loanCapital.getTotalAmount())) {
				incomeBalance = incomeBalance.add(loanCapital.getTotalAmount());
			}
		}

		BigDecimal paymentBalance = BigDecimal.ZERO;

		for (int i = 0; i < spareCapitals.size(); i++) {
			SpareCapital spareCapital = spareCapitals.get(i);
			spareCapital.setCreditEntrust(creditEntrust);
			spareCapital.setTempUuid(tempUuid);
			spareCapitalService.updateEntity(spareCapital);
			if (ObjectHelper.isNotEmpty(spareCapital.getActualAmount())) {
				paymentBalance = paymentBalance.add(spareCapital.getActualAmount());
			}
		}

		// 设置备付金余额
		creditEntrust.setPaymentBalance(paymentBalance);

		// 设置收入总额
		creditEntrust.setIncomeBalance(incomeBalance);

		for (int i = 0; i < redemPrincis.size(); i++) {
			CreditEntrustRedemPrinci creditEntrustRedemPrinci = redemPrincis.get(i);
			creditEntrustRedemPrinci.setCreditEntrust(creditEntrust);
			creditEntrustRedemPrinci.setTempUuid(tempUuid);
			creditEntrustRedemPrinciService.updateEntity(creditEntrustRedemPrinci);
		}

		creditEntrust = creditEntrustRepository.updateEntity(creditEntrust);

		// 填充统计信息
		creditEntrust = creditEntrustToolService.listFill(creditEntrust);
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
						"creditCostTrackings", "incomeBalance", "paymentBalance" });

		oldCreditEntrust.setUpdateBy(CED.getLoginUser().getEmpCd());
		oldCreditEntrust.setUpdateTime(new Date());
		oldCreditEntrust.setUpdateOrgCd(CED.getLoginUser().getOrgCd());
		oldCreditEntrust = creditEntrustRepository.updateEntity(oldCreditEntrust);

		oldCreditEntrust = creditEntrustToolService.listFill(oldCreditEntrust);
		return creditEntrustRepository.updateEntity(oldCreditEntrust);
	}

	@Override
	public List<CreditEntrust> findByConditions(Map<String, Object> conditions) {
		return creditEntrustRepository.findByConditions(conditions);
	}

	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public synchronized CreditEntrust updateIncomeBalance(CreditEntrust creditEntrust, BigDecimal amount)
			throws BusinessException {
		BigDecimal tempAmount = creditEntrust.getIncomeBalance();
		// 如果分配的金额大于余额，则直接抛出异常
		if (tempAmount.compareTo(amount) < 0) {
			throw new BusinessException("10010013", "分配额度失败，剩余可分配额度不足");
		}

		BigDecimal leaveAmount = tempAmount.subtract(amount);
		creditEntrust.setIncomeBalance(leaveAmount);
		creditEntrust = creditEntrustRepository.updateEntity(creditEntrust);

		// 重新填充列表值
		creditEntrust = creditEntrustToolService.listFill(creditEntrust);
		creditEntrust = creditEntrustRepository.updateEntity(creditEntrust);
		return creditEntrust;
	}

	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public synchronized CreditEntrust updatePaymentBalance(CreditEntrust creditEntrust, BigDecimal amount)
			throws BusinessException {
		BigDecimal tempAmount = creditEntrust.getPaymentBalance();

		if (tempAmount.compareTo(amount) < 0) {
			throw new BusinessException("10010013", "分配备付金失败，备付金余额小于分配金额");
		}

		BigDecimal leaveAmount = tempAmount.subtract(amount);
		creditEntrust.setPaymentBalance(leaveAmount);
		creditEntrust = creditEntrustRepository.updateEntity(creditEntrust);

		// 更新备付金资金信息
		List<SpareCapital> spareCapitals = spareCapitalService
				.findByCreditEntrustIdAndOrderByCreteTime(creditEntrust.getId());

		for (int i = 0; i < spareCapitals.size(); i++) {
			// 判断金额是否足够
			if ((spareCapitals.get(i).getDistributionAmount() == null ? BigDecimal.ZERO
					: spareCapitals.get(i).getDistributionAmount()).compareTo(amount) >= 0) {
				// 足够，则直接减去第一个，并且停止循环
				spareCapitals.get(i)
						.setDistributionAmount(spareCapitals.get(i).getDistributionAmount().subtract(amount));
				spareCapitalService.updateEntity(spareCapitals.get(i));
				break;
			} else {
				// 不足够，则减去第一个，继续循环
				amount = amount.subtract(spareCapitals.get(i).getDistributionAmount() == null ? BigDecimal.ZERO
						: spareCapitals.get(i).getDistributionAmount());
				spareCapitals.get(i).setDistributionAmount(BigDecimal.ZERO);
				spareCapitalService.updateEntity(spareCapitals.get(i));
			}
		}

		// 重新填充列表值
		creditEntrust = creditEntrustToolService.listFill(creditEntrust);
		creditEntrust = creditEntrustRepository.updateEntity(creditEntrust);
		return creditEntrust;
	}

	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public synchronized CreditEntrust reservePaymentBalance(CreditEntrust creditEntrust, BigDecimal amount)
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

		// 重新填充列表值
		creditEntrust = creditEntrustToolService.listFill(creditEntrust);
		creditEntrust = creditEntrustRepository.updateEntity(creditEntrust);
		return creditEntrust;
	}

	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public CreditEntrust confirmInfo(CreditEntrust creditEntrust, CreditEntrust creditEntrustNew, BigDecimal amount,
			String caseLimitApplyId) throws BusinessException {
		// 恢复原有信托计划 收入总计
		updateIncomeBalance(creditEntrust, new BigDecimal("-" + amount.toString()));
		// 恢复原有信托计划 备付金
		reservePaymentBalance(creditEntrust, amount);

		// 修改新计划收入总计
		updateIncomeBalance(creditEntrustNew, amount);
		// 修改新计划备付金
		updatePaymentBalance(creditEntrustNew, amount);

		CaseLimitApply caseLimitApply = caseLimitApplyService.findOne(caseLimitApplyId);
		// 资金计划id
		caseLimitApply.setFundPlanId(creditEntrustNew.getId());
		// 资金计划名称
		caseLimitApply.setFundPlanName(creditEntrustNew.getCreditEntrustName());
		// 贷款发放账户机构名称
		caseLimitApply.setLoanOutAccountBranchName(creditEntrustNew.getWaitApprBank());
		// 贷款发放账户账户名
		caseLimitApply.setLoanOutAccountName(creditEntrustNew.getWaitApprName());
		// 贷款发放账户
		caseLimitApply.setLoanOutAccount(creditEntrustNew.getWaitApprNo());
		// 贷款回款账户机构名称
		caseLimitApply.setLoanBackAccountBranchName(creditEntrustNew.getCollectionAccountBank());
		// 贷款回款账户账户名
		caseLimitApply.setLoanBackAccountName(creditEntrustNew.getCollectionAccountName());
		// 贷款回款账户
		caseLimitApply.setLoanBackAccount(creditEntrustNew.getCollectionAccountNo());

		caseLimitApplyService.updateEntity(caseLimitApply);

		return creditEntrust;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public CreditEntrust oneKeyFundMatching(String creditEntrustId, String state) throws Exception {

		// 查询信托计划
		CreditEntrust creditEntrust = creditEntrustRepository.findOne(creditEntrustId);

		// 查询已分配额度未分配资金的案件信息
		List<CaseLimitApply> caseLimitApplies = caseLimitApplyService.findByCreditEntrustId(creditEntrustId, state);

		BigDecimal tempPayment = creditEntrust.getPaymentBalance();
		for (int i = 0; i < caseLimitApplies.size(); i++) {

			CaseLimitApply caseLimitApply = caseLimitApplies.get(i);
			// 首先剩余额度必须大于0，否则直接跳出循环
			if (tempPayment.compareTo(BigDecimal.ZERO) > 0) {

				// 剩余额度满足案件需求
				if (tempPayment.compareTo(caseLimitApply.getApplyLimitAmount()) > 0) {
					updatePaymentBalance(creditEntrust, caseLimitApply.getApplyLimitAmount());
					tempPayment = tempPayment.subtract(caseLimitApply.getApplyLimitAmount());
				} else if (tempPayment.compareTo(caseLimitApply.getApplyLimitAmount()) == 0) {
					updatePaymentBalance(creditEntrust, caseLimitApply.getApplyLimitAmount());
					break;
				} else {
					updatePaymentBalance(creditEntrust, tempPayment);
					break;
				}
				// 设置分配资金时间
				caseLimitApply.setAllotDate(
						DateHelper.dateToLong(new Date(), DateHelper.DATE_SHORT_SIMPLE_FORMAT_WITHMINUTE));
				// 已配资金
				caseLimitApply.setEffectiveStatus(CaseLimitApply.ALLOCATED_FUNDS);
				// 获取案件信息
				CaseApply caseApply = caseApplyService.findOne(caseLimitApply.getCaseApplyId());
				// 案件已配资金
				caseApply.setActualLimitStatus(CaseLimitApply.ALLOCATED_FUNDS);
				// 案件已分配金额
				caseApply.setActualApplyAmount(caseLimitApply.getApplyLimitAmount());
				// 修改额度信息
				caseLimitApplyService.updateEntity(caseLimitApply);
				// 案件合同合同金额设置

				ConCaseContract conCaseContract = conCaseContractService
						.findByCaseApplyId(caseLimitApply.getCaseApplyId());
				conCaseContract.setContractAmount(caseLimitApply.getApplyLimitAmount());
				conCaseContractService.updateEntity(conCaseContract);
				caseApplyService.updateEntity(caseApply);
			} else {
				break;
			}
		}
		return creditEntrust;
	}

	@Override
	public BigDecimal cumulativeDischarge(String creditEntrustId, Long appointedDate) {
		return creditEntrustRepository.cumulativeDischarge(creditEntrustId, null) == null ? BigDecimal.ZERO
				: creditEntrustRepository.cumulativeDischarge(creditEntrustId, null);
	}

	@Override
	public Map<String, BigDecimal> countTodayPaidAmount(String creditEntrustId) throws BusinessException {
		if (ObjectHelper.isEmpty(creditEntrustId)) {
			throw new BusinessException("10010004", "信托计划id为空");
		}
		return creditEntrustRepository.countTodayPaidAmount(creditEntrustId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public CreditEntrust quotaApply(String caseApplyId, String creditEntrustId) throws Exception {
		CaseApply caseApply = caseApplyService.findOne(caseApplyId);
		CreditEntrust creditEntrust = this.findOne(creditEntrustId);

		// 修改信托计划信息
		creditEntrust = this.updateIncomeBalance(creditEntrust, caseApply.getApplyAmount());

		// 调用案件信息修改案件相关的信息
		caseLimitApplyService.applyLimit(null, creditEntrust.getId(), caseApplyId, caseApply.getApplyAmount());
		return creditEntrust;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public CreditEntrust quotaCancel(String caseApplyId, String creditEntrustId, String caseLimitApplyId)
			throws Exception {
		// 查询信托计划信息
		CreditEntrust creditEntrust = this.findOne(creditEntrustId);

		// 查询额度信息
		CaseLimitApply caseLimitApply = caseLimitApplyService.findOne(caseLimitApplyId);

		// 修改信托计划信息
		creditEntrust = this.updateIncomeBalance(creditEntrust,
				new BigDecimal("-" + caseLimitApply.getApplyLimitAmount().toString()));
		caseLimitApplyService.cancelApplyLimit(caseLimitApplyId);
		return creditEntrust;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public CreditEntrust reserveFund(String caseApplyId, String creditEntrustId, String caseLimitApplyId)
			throws Exception {
		// 查询信托计划信息
		CreditEntrust creditEntrust = this.findOne(creditEntrustId);
		// 查询额度信息
		CaseLimitApply caseLimitApply = caseLimitApplyService.findOne(caseLimitApplyId);

		// 修改信托计划信息，并减去未分配金额
		creditEntrust = this.updatePaymentBalance(creditEntrust, caseLimitApply.getApplyLimitAmount());

		caseLimitApplyService.matchLimit(caseLimitApplyId);
		return creditEntrust;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public CreditEntrust reserveFundCancel(String caseApplyId, String creditEntrustId, String caseLimitApplyId)
			throws Exception {
		// 查询信托信息
		CreditEntrust creditEntrust = this.findOne(creditEntrustId);

		// 查询额度信息
		CaseLimitApply caseLimitApply = caseLimitApplyService.findOne(caseLimitApplyId);

		// 修改信托计划信息，并恢复未分配金额
		creditEntrust = this.reservePaymentBalance(creditEntrust, caseLimitApply.getApplyLimitAmount());

		caseLimitApplyService.cancelMatchLimit(caseLimitApplyId);
		return creditEntrust;
	}

	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public CreditEntrust confirmInfo(CreditEntrust creditEntrust, CreditEntrust creditEntrustNew,
			CaseLimitApply caseLimitApply) throws BusinessException {

		// 已申请额度，未分配资金
		if (CaseLimitApply.FILED_WITHOUT_MONEY.equals(caseLimitApply.getEffectiveStatus())) {
			// 恢复原有信托计划 收入总计
			updateIncomeBalance(creditEntrust, new BigDecimal("-" + caseLimitApply.getApplyLimitAmount().toString()));

			// 修改新计划收入总计
			updateIncomeBalance(creditEntrustNew, caseLimitApply.getApplyLimitAmount());
		}

		// 已申请额度，并已分配资金
		if (CaseLimitApply.ALLOCATED_FUNDS.equals(caseLimitApply.getEffectiveStatus())) {
			// 恢复原有信托计划 收入总计
			updateIncomeBalance(creditEntrust, new BigDecimal("-" + caseLimitApply.getApplyLimitAmount().toString()));
			// 恢复原有信托计划 备付金
			reservePaymentBalance(creditEntrust, caseLimitApply.getApplyLimitAmount());

			// 修改新计划收入总计
			updateIncomeBalance(creditEntrustNew, caseLimitApply.getApplyLimitAmount());
			// 修改新计划备付金
			updatePaymentBalance(creditEntrustNew, caseLimitApply.getApplyLimitAmount());
		}

		// 资金计划id
		caseLimitApply.setFundPlanId(creditEntrustNew.getId());
		// 资金计划名称
		caseLimitApply.setFundPlanName(creditEntrustNew.getCreditEntrustName());
		// 贷款发放账户机构名称
		caseLimitApply.setLoanOutAccountBranchName(creditEntrustNew.getWaitApprBank());
		// 贷款发放账户账户名
		caseLimitApply.setLoanOutAccountName(creditEntrustNew.getWaitApprName());
		// 贷款发放账户
		caseLimitApply.setLoanOutAccount(creditEntrustNew.getWaitApprNo());
		// 贷款回款账户机构名称
		caseLimitApply.setLoanBackAccountBranchName(creditEntrustNew.getCollectionAccountBank());
		// 贷款回款账户账户名
		caseLimitApply.setLoanBackAccountName(creditEntrustNew.getCollectionAccountName());
		// 贷款回款账户
		caseLimitApply.setLoanBackAccount(creditEntrustNew.getCollectionAccountNo());

		caseLimitApplyService.updateEntity(caseLimitApply);

		return creditEntrust;
	}

}
