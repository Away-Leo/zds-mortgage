package com.zdsoft.finance.capital.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.capital.entity.CreditEntrust;
import com.zdsoft.finance.capital.entity.CreditEntrustAttom;
import com.zdsoft.finance.capital.entity.CreditEntrustDebit;
import com.zdsoft.finance.capital.entity.CreditEntrustFeeItem;
import com.zdsoft.finance.capital.entity.CreditEntrustPrincipal;
import com.zdsoft.finance.capital.entity.CreditEntrustRedemPrinci;
import com.zdsoft.finance.capital.entity.CreditEntrustTieOff;
import com.zdsoft.finance.capital.entity.LoanCapital;
import com.zdsoft.finance.capital.entity.SpareCapital;
import com.zdsoft.finance.capital.service.CreditEntrustAttomService;
import com.zdsoft.finance.capital.service.CreditEntrustDebitService;
import com.zdsoft.finance.capital.service.CreditEntrustFeeItemService;
import com.zdsoft.finance.capital.service.CreditEntrustPrincipalService;
import com.zdsoft.finance.capital.service.CreditEntrustRedemPrinciService;
import com.zdsoft.finance.capital.service.CreditEntrustService;
import com.zdsoft.finance.capital.service.CreditEntrustTieOffService;
import com.zdsoft.finance.capital.service.CreditEntrustToolService;
import com.zdsoft.finance.capital.service.LoanCapitalService;
import com.zdsoft.finance.capital.service.SpareCapitalService;
import com.zdsoft.finance.casemanage.limitApply.entity.CaseLimitApply;
import com.zdsoft.finance.casemanage.limitApply.service.CaseLimitApplyService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CreditEntrustToolServiceImpl.java
 * @ClassName: CreditEntrustToolServiceImpl
 * @Description: 信托计划列表统计信息工具类
 * @author liuwei
 * @date 2017年2月16日 下午6:22:28
 * @version V1.0
 */
@Service("creditEntrustToolService")
public class CreditEntrustToolServiceImpl extends
		BaseServiceImpl<CreditEntrust, CustomRepository<CreditEntrust, String>> implements CreditEntrustToolService {

	@Autowired
	CreditEntrustAttomService creditEntrustAttomService;

	@Autowired
	CreditEntrustDebitService creditEntrustDebitService;

	@Autowired
	CreditEntrustPrincipalService creditEntrustPrincipalService;

	@Autowired
	LoanCapitalService loanCapitalService;

	@Autowired
	CreditEntrustRedemPrinciService creditEntrustRedemPrinciService;

	@Autowired
	CreditEntrustFeeItemService creditEntrustFeeItemService;

	@Autowired
	CaseLimitApplyService caseLimitApplyService;

	@Autowired
	CreditEntrustService creditEntrustService;

	@Autowired
	CreditEntrustTieOffService creditEntrustTieOffService;

	@Autowired
	SpareCapitalService spareCapitalService;

	@Override
	public CreditEntrust listFill(CreditEntrust creditEntrust) {

		// 构建查询条件
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("creditEntrustId", creditEntrust.getId());

		// 查询各个子表信息
		// 信托计划转让
		List<CreditEntrustAttom> creditEntrustAttoms = creditEntrustAttomService.findByConditions(conditions);
		// 信托计划借方资金
		List<CreditEntrustDebit> creditEntrustDebits = creditEntrustDebitService.findByConditions(conditions);
		// 信托计划本金投入
		List<CreditEntrustPrincipal> creditEntrustPrincipals = creditEntrustPrincipalService
				.findByConditions(conditions);
		// 专户贷方资金跟踪
		List<LoanCapital> loanCapitals = loanCapitalService.findByConditions(conditions);
		// 本金赎回
		List<CreditEntrustRedemPrinci> redemPrincis = creditEntrustRedemPrinciService.findByConditions(conditions);

		// 计算各单元数据
		// 优先本金 == 信托本金投入，优先-货币
		BigDecimal priorityPrincipal = BigDecimal.ZERO;
		// 货币 == 信托本金投入，劣后-货币
		BigDecimal currency = BigDecimal.ZERO;
		// 债权转入 == 信托本金投入，劣后-债权
		BigDecimal bondTransfer = BigDecimal.ZERO;

		// 单一货币 == 信托本金投入，单一货币
		BigDecimal singleCurrency = BigDecimal.ZERO;
		// 信托本金投入
		for (int i = 0; i < creditEntrustPrincipals.size(); i++) {
			CreditEntrustPrincipal creditEntrustPrincipal = creditEntrustPrincipals.get(i);
			// 判断类型
			switch (creditEntrustPrincipal.getContributionType()) {
			case "YWDM0014101": // 优先-货币
				priorityPrincipal = priorityPrincipal.add(creditEntrustPrincipal.getPrincipalAmount());
				break;
			case "YWDM0014102": // 劣后-货币
				currency = currency.add(creditEntrustPrincipal.getPrincipalAmount());
				break;
			case "YWDM0014103": // 劣后-债权
				bondTransfer = bondTransfer.add(creditEntrustPrincipal.getPrincipalAmount());
				break;
			case "YWDM0014104":
				singleCurrency = singleCurrency.add(creditEntrustPrincipal.getPrincipalAmount());
				break;
			default:
				break;
			}
		}

		// 客户还款-本金 == 专户贷方资金（非本金）跟踪 ，客户还款（本金）
		BigDecimal repaymentPrincipal = BigDecimal.ZERO;
		// 客户还款-利息 == 专户贷方资金（非本金）跟踪，客户还款（利息，罚息，手续费，财务费用）
		BigDecimal repaymentInterest = BigDecimal.ZERO;
		// 代偿-本金 == 专户贷方资金（非本金）跟踪,代偿（本金）
		BigDecimal compensatoryPrincipal = BigDecimal.ZERO;
		// 代偿-利息 == 专户贷方资金（非本金）跟踪 ,代偿（利息、罚息）
		BigDecimal compensatoryInterest = BigDecimal.ZERO;
		// 回购-本金 == 专户贷方资金（非本金）跟踪 ,回购（本金）
		BigDecimal backPrincipal = BigDecimal.ZERO;
		// 回购-利息 == 专户贷方资金（非本金）跟踪 ,回购（利息、罚息）
		BigDecimal backInterest = BigDecimal.ZERO;
		// 其他利息 == 专户贷方资金（非本金）跟踪 信托保障基金利息（利息）+银行利息（利息）
		BigDecimal otherInterest = BigDecimal.ZERO;
		// 暂待收款 == 专户贷方资金（非本金）跟踪 ，暂待收款（本金，利息，罚息，收益）
		BigDecimal pendingPayment = BigDecimal.ZERO;
		// 信托保障基金 == 专户贷方资金（非本金）跟踪 ，信托保障基金（本金 ）
		BigDecimal trustGuaranteeFund = BigDecimal.ZERO;
		// 专户贷方资金跟踪
		for (int i = 0; i < loanCapitals.size(); i++) {
			LoanCapital loanCapital = loanCapitals.get(i);
			// 查询费用项
			List<CreditEntrustFeeItem> creditEntrustFeeItems = creditEntrustFeeItemService
					.findByBusinessId(loanCapital.getId());

			// 将金额分类统计
			Map<String, Object> returnFeeMap = accumulateFeeAmount(creditEntrustFeeItems);

			switch (loanCapital.getLenderType()) {
			case "YWDM0014301": // 客户还款
				repaymentPrincipal = repaymentPrincipal.add((BigDecimal) returnFeeMap.get("capital"));
				repaymentInterest = repaymentInterest.add((BigDecimal) returnFeeMap.get("interest"));
				break;
			case "YWDM0014303": // 代偿
				compensatoryPrincipal = compensatoryPrincipal.add((BigDecimal) returnFeeMap.get("capital"));
				compensatoryInterest = compensatoryInterest.add((BigDecimal) returnFeeMap.get("interest"));
				break;
			case "YWDM0014310": // 回购
				backPrincipal = backPrincipal.add((BigDecimal) returnFeeMap.get("capital"));
				backInterest = backInterest.add((BigDecimal) returnFeeMap.get("interest"));
				break;
			case "YWDM0014307": // 暂待收款
				pendingPayment = pendingPayment.add((BigDecimal) returnFeeMap.get("capital"))
						.add((BigDecimal) returnFeeMap.get("interest")).add((BigDecimal) returnFeeMap.get("penalty"))
						.add((BigDecimal) returnFeeMap.get("profit"));
				break;
			case "YWDM0014311": // 信托保障基金利息
				otherInterest = otherInterest.add((BigDecimal) returnFeeMap.get("interest"));
				break;
			case "YWDM0014306": // 银行利息
				otherInterest = otherInterest.add((BigDecimal) returnFeeMap.get("interest"));
				break;
			case "YWDM0014308": // 信托保障基金
				trustGuaranteeFund = trustGuaranteeFund.add((BigDecimal) returnFeeMap.get("capital"));
				break;
			default:
				break;
			}
		}

		// 流入小计 == 前面所有项合计
		BigDecimal inflowSubtotal = BigDecimal.ZERO;

		// 统计流入小计
		inflowSubtotal = inflowSubtotal.add(priorityPrincipal).add(currency).add(bondTransfer).add(repaymentPrincipal)
				.add(repaymentInterest).add(compensatoryPrincipal).add(compensatoryInterest).add(backPrincipal)
				.add(backInterest).add(otherInterest).add(pendingPayment).add(trustGuaranteeFund);

		// 优先资金-优先本金 == 信托本金赎回，优先-本金
		BigDecimal fundsPriorityPrincipal = BigDecimal.ZERO;
		// 劣后资金-劣后本金 == 信托本金赎回，劣后本金字段
		BigDecimal badPrincipal = BigDecimal.ZERO;

		// 单一-货币 == 信托本金赎回，单一货币
		BigDecimal redemCurrenCy = BigDecimal.ZERO;
		// 本金赎回统计
		for (int i = 0; i < redemPrincis.size(); i++) {
			CreditEntrustRedemPrinci creditEntrustRedemPrinci = redemPrincis.get(i);
			switch (creditEntrustRedemPrinci.getContributionType()) {
			case "YWDM0014201": // 优先-本金
				fundsPriorityPrincipal = fundsPriorityPrincipal.add(creditEntrustRedemPrinci.getRedemptionAmount());
				break;
			case "YWDM0014202": // 劣后本金
				badPrincipal = badPrincipal.add(creditEntrustRedemPrinci.getRedemptionAmount());
				break;
			case "YWDM0014203": // 单一货币
				redemCurrenCy = redemCurrenCy.add(creditEntrustRedemPrinci.getRedemptionAmount());
				break;
			default:
				break;
			}
		}

		// 退客户款 == 专户借方资金（非本金）跟踪，退客户款（本金，利息，罚息）
		BigDecimal refund = BigDecimal.ZERO;
		// 优先资金-优先利息 == 专户借方资金（非本金）跟踪，优先利息-投资方（利息）
		BigDecimal fundsPriorityInterest = BigDecimal.ZERO;
		// 劣后资金-劣后收益 == 专户借方资金（非本金）跟踪，劣后收益-劣后方（收益）
		BigDecimal inferiorPostYield = BigDecimal.ZERO;
		// 信托保障基金（1%) == 专户借方资金（非本金）跟踪，信托保障基金（保证金）
		BigDecimal trustGuaranteeFundPercent = BigDecimal.ZERO;
		// 信托费用 == 专户借方资金（非本金）跟踪，信托费用（杂费）
		BigDecimal trustExpense = BigDecimal.ZERO;
		// 银行托管费（0.08%） == 专户借方资金（非本金）跟踪，银行托管费 （托管费）
		BigDecimal bankCustodianFee = BigDecimal.ZERO;
		// 信托服务费（0.8%） == 专户借方资金（非本金）跟踪，信托服务费（服务费）
		BigDecimal trustServiceFee = BigDecimal.ZERO;
		// 展期服务费（0.15%） == 专户借方资金（非本金）跟踪，展期服务费（ 服务费）
		BigDecimal extensionServiceFee = BigDecimal.ZERO;
		// 印花税（0.05%） == 专户借方资金（非本金）跟踪，印花税（ 印花税 ）
		BigDecimal stampDuty = BigDecimal.ZERO;
		// 深泛联管理费(8%) == 专户借方资金（非本金）跟踪，深泛联管理费（管理费）
		BigDecimal sflManagementFee = BigDecimal.ZERO;
		// 代偿退回 == 专户借方资金（非本金）跟踪，代偿退回（本金，利息，罚息）
		BigDecimal compensatory = BigDecimal.ZERO;
		// 回购退回 == 专户借方资金（非本金）跟踪，回购退回（本金，利息，罚息）
		BigDecimal buyBack = BigDecimal.ZERO;

		// 专户借方资金（非本金）跟踪
		for (int i = 0; i < creditEntrustDebits.size(); i++) {
			CreditEntrustDebit creditEntrustDebit = creditEntrustDebits.get(i);
			// 查询费用项
			List<CreditEntrustFeeItem> creditEntrustFeeItems = creditEntrustFeeItemService
					.findByBusinessId(creditEntrustDebit.getId());
			// 将金额分类统计
			Map<String, Object> returnFeeMap = accumulateFeeAmount(creditEntrustFeeItems);

			switch (creditEntrustDebit.getDebitType()) {
			case "YWDM0013701": // 退客户款（本金，利息，罚息）
				refund = refund.add((BigDecimal) returnFeeMap.get("capital"))
						.add((BigDecimal) returnFeeMap.get("interest")).add((BigDecimal) returnFeeMap.get("penalty"));
				break;
			case "YWDM0013703": // 优先利息-投资方（利息）
				fundsPriorityInterest = fundsPriorityInterest.add((BigDecimal) returnFeeMap.get("interest"));
				break;
			case "YWDM0013704":// 劣后收益-劣后方（收益）
				inferiorPostYield = inferiorPostYield.add((BigDecimal) returnFeeMap.get("profit"));
				break;
			case "YWDM0013705": // 信托保障基金（保证金）
				trustGuaranteeFundPercent = trustGuaranteeFundPercent.add((BigDecimal) returnFeeMap.get("bond"));
				break;
			case "YWDM0013706": // 信托费用（杂费）
				trustExpense = trustExpense.add((BigDecimal) returnFeeMap.get("incidental"));
				break;
			case "YWDM0013707": // 银行托管费 （托管费）
				bankCustodianFee = bankCustodianFee.add((BigDecimal) returnFeeMap.get("custodianFee"));
				break;
			case "YWDM0013708": // 信托服务费（服务费）
				trustServiceFee = trustServiceFee.add((BigDecimal) returnFeeMap.get("serviceCharge"));
				break;
			case "YWDM0013709": // 展期服务费（ 服务费）
				extensionServiceFee = extensionServiceFee.add((BigDecimal) returnFeeMap.get("serviceCharge"));
				break;
			case "YWDM0013710": // 印花税（ 印花税 ）
				stampDuty = stampDuty.add((BigDecimal) returnFeeMap.get("stampDuty"));
				break;
			case "YWDM0013711": // 深泛联管理费（管理费）
				sflManagementFee = sflManagementFee.add((BigDecimal) returnFeeMap.get("managementExpense"));
				break;
			case "YWDM0013712": // 代偿退回（本金，利息，罚息）
				compensatory = compensatory.add((BigDecimal) returnFeeMap.get("capital"))
						.add((BigDecimal) returnFeeMap.get("interest")).add((BigDecimal) returnFeeMap.get("penalty"));
				break;
			case "YWDM0013714": // 回购退回（本金，利息，罚息）
				buyBack = buyBack.add((BigDecimal) returnFeeMap.get("capital"))
						.add((BigDecimal) returnFeeMap.get("interest")).add((BigDecimal) returnFeeMap.get("penalty"));
				break;
			default:
				break;
			}
		}

		// 累计贷款投放 ==
		BigDecimal cumulativeLoan = BigDecimal.ZERO;
		cumulativeLoan = creditEntrustService.cumulativeDischarge(creditEntrust.getId(), null);

		// 流出小计 == 所有流出项合计
		BigDecimal outflowSubtotal = BigDecimal.ZERO;

		// 流出合计
		outflowSubtotal = outflowSubtotal.add(fundsPriorityPrincipal).add(badPrincipal).add(refund)
				.add(fundsPriorityInterest).add(inferiorPostYield).add(trustGuaranteeFundPercent).add(trustExpense)
				.add(bankCustodianFee).add(trustServiceFee).add(extensionServiceFee).add(stampDuty)
				.add(sflManagementFee).add(compensatory).add(buyBack).add(cumulativeLoan);

		// 截留额度 == 信托计划中 截留额度 字段
		BigDecimal retain = creditEntrust.getRetain() == null ? BigDecimal.ZERO : creditEntrust.getRetain();

		// 剩余可分配 == 流入项小计-流出项小计-截留额度
		BigDecimal residualDistribution = BigDecimal.ZERO;
		// 剩余可分配统计
		residualDistribution = residualDistribution.add(inflowSubtotal).subtract(outflowSubtotal).subtract(retain);

		// 已分配待放款 == 额度申请表中已配额度未放款
		BigDecimal allocatedLoan = BigDecimal.ZERO;
		List<CaseLimitApply> caseLimitApplies = caseLimitApplyService.findByCreditEntrustId(creditEntrust.getId(),
				"YWDM0051058");
		for (int i = 0; i < caseLimitApplies.size(); i++) {
			allocatedLoan = allocatedLoan.add(caseLimitApplies.get(i).getApplyLimitAmount());
		}

		// 转让相关信息
		// 资金状态
		String capitalStatus = "";
		// 转让日期
		Long transferDate = 0L;
		// 到期日期
		Long maturityDate = 0L;
		if (ObjectHelper.isNotEmpty(creditEntrustAttoms) && creditEntrustAttoms.size() != 0) {
			capitalStatus = creditEntrustAttoms.get(0).getAttomState();
			transferDate = creditEntrustAttoms.get(0).getCompleteDate();
			maturityDate = creditEntrustAttoms.get(0).getAttomEndDate();
		}
		// 信托本金
		BigDecimal trustPrincipal = BigDecimal.ZERO;
		// 信托本金 == 本金投入(单一货币)-本金赎回（单一本金）
		trustPrincipal = trustPrincipal.add(singleCurrency).subtract(redemCurrenCy);

		// 转让金额
		BigDecimal transferAmount = BigDecimal.ZERO;
		// 转让金额 == 本金投入中的“劣后-债权”
		transferAmount = bondTransfer;

		// 创建日期
		Long createDate = DateHelper.dateToLong(creditEntrust.getCreateTime(), DateHelper.DATE_LONG_SIMPLE_FORMAT);

		// 为信托计划重新设置值
		creditEntrust.setPriorityPrincipal(priorityPrincipal);
		creditEntrust.setCurrency(currency);
		creditEntrust.setBondTransfer(bondTransfer);
		creditEntrust.setRepaymentPrincipal(repaymentPrincipal);
		creditEntrust.setRepaymentInterest(repaymentInterest);
		creditEntrust.setCompensatoryPrincipal(compensatoryPrincipal);
		creditEntrust.setCompensatoryInterest(compensatoryInterest);
		creditEntrust.setBackPrincipal(backPrincipal);
		creditEntrust.setBackInterest(backInterest);
		creditEntrust.setOtherInterest(otherInterest);
		creditEntrust.setPendingPayment(pendingPayment);
		creditEntrust.setTrustGuaranteeFund(trustGuaranteeFund);
		creditEntrust.setInflowSubtotal(inflowSubtotal);
		creditEntrust.setRefund(refund);
		creditEntrust.setFundsPriorityPrincipal(fundsPriorityPrincipal);
		creditEntrust.setFundsPriorityInterest(fundsPriorityInterest);
		creditEntrust.setBadPrincipal(badPrincipal);
		creditEntrust.setInferiorPostYield(inferiorPostYield);
		creditEntrust.setTrustGuaranteeFundPercent(trustGuaranteeFundPercent);
		creditEntrust.setTrustExpense(trustExpense);
		creditEntrust.setBankCustodianFee(bankCustodianFee);
		creditEntrust.setTrustServiceFee(trustServiceFee);
		creditEntrust.setExtensionServiceFee(extensionServiceFee);
		creditEntrust.setStampDuty(stampDuty);
		creditEntrust.setSflManagementFee(sflManagementFee);
		creditEntrust.setCompensatory(compensatory);
		creditEntrust.setBuyBack(buyBack);
		creditEntrust.setCumulativeLoan(cumulativeLoan);
		creditEntrust.setOutflowSubtotal(outflowSubtotal);
		creditEntrust.setResidualDistribution(residualDistribution);
		creditEntrust.setAllocatedLoan(allocatedLoan);

		creditEntrust.setCapitalStatus(capitalStatus);
		creditEntrust.setTrustPrincipal(trustPrincipal);
		creditEntrust.setTransferAmount(transferAmount);
		creditEntrust.setTransferDate(transferDate);
		creditEntrust.setMaturityDate(maturityDate);
		creditEntrust.setCreateDate(createDate);
		return creditEntrust;
	}

	@Override
	public Map<String, Object> accumulateFeeAmount(List<CreditEntrustFeeItem> creditEntrustFeeItems) {

		Map<String, Object> returnMap = new HashMap<String, Object>();

		// 本金
		BigDecimal capital = BigDecimal.ZERO;
		// 利息
		BigDecimal interest = BigDecimal.ZERO;
		// 罚息
		BigDecimal penalty = BigDecimal.ZERO;
		// 手续费
		BigDecimal counterFee = BigDecimal.ZERO;
		// 财务费用
		BigDecimal financialExpenses = BigDecimal.ZERO;
		// 收益
		BigDecimal profit = BigDecimal.ZERO;
		// 服务费
		BigDecimal serviceCharge = BigDecimal.ZERO;
		// 保管费
		BigDecimal custodyFee = BigDecimal.ZERO;
		// 管理费
		BigDecimal managementExpense = BigDecimal.ZERO;
		// 其他
		BigDecimal other = BigDecimal.ZERO;
		// 保证金
		BigDecimal bond = BigDecimal.ZERO;
		// 杂费
		BigDecimal incidental = BigDecimal.ZERO;
		// 托管费
		BigDecimal custodianFee = BigDecimal.ZERO;
		// 印花税
		BigDecimal stampDuty = BigDecimal.ZERO;

		for (int i = 0; i < creditEntrustFeeItems.size(); i++) {
			CreditEntrustFeeItem creditEntrustFeeItem = creditEntrustFeeItems.get(i);
			// 判断数据类型
			switch (creditEntrustFeeItem.getFeeItemCd()) {
			case "capital":
				capital = capital.add(creditEntrustFeeItem.getFeeAmount());
				break;
			case "interest":
				interest = interest.add(creditEntrustFeeItem.getFeeAmount());
				break;
			case "penalty":
				penalty = penalty.add(creditEntrustFeeItem.getFeeAmount());
				break;
			case "counterFee":
				counterFee = counterFee.add(creditEntrustFeeItem.getFeeAmount());
				break;
			case "financialExpenses":
				financialExpenses = financialExpenses.add(creditEntrustFeeItem.getFeeAmount());
				break;
			case "profit":
				profit = profit.add(creditEntrustFeeItem.getFeeAmount());
				break;
			case "serviceCharge":
				serviceCharge = serviceCharge.add(creditEntrustFeeItem.getFeeAmount());
				break;
			case "custodyFee":
				custodyFee = custodyFee.add(creditEntrustFeeItem.getFeeAmount());
				break;
			case "managementExpense":
				managementExpense = managementExpense.add(creditEntrustFeeItem.getFeeAmount());
				break;
			case "other":
				other = other.add(creditEntrustFeeItem.getFeeAmount());
				break;
			case "bond":
				bond = bond.add(creditEntrustFeeItem.getFeeAmount());
				break;
			case "incidental":
				incidental = incidental.add(creditEntrustFeeItem.getFeeAmount());
				break;
			case "custodianFee":
				custodianFee = custodianFee.add(creditEntrustFeeItem.getFeeAmount());
				break;
			case "stampDuty":
				stampDuty = stampDuty.add(creditEntrustFeeItem.getFeeAmount());
				break;
			default:
				break;
			}

		}

		// 构建返回数据
		returnMap.put("capital", capital);
		returnMap.put("interest", interest);
		returnMap.put("penalty", penalty);
		returnMap.put("counterFee", counterFee);
		returnMap.put("financialExpenses", financialExpenses);
		returnMap.put("profit", profit);
		returnMap.put("serviceCharge", serviceCharge);
		returnMap.put("custodyFee", custodyFee);
		returnMap.put("managementExpense", managementExpense);
		returnMap.put("other", other);
		returnMap.put("bond", bond);
		returnMap.put("incidental", incidental);
		returnMap.put("custodianFee", custodianFee);
		returnMap.put("stampDuty", stampDuty);

		return returnMap;
	}

	@Override
	public Map<String, BigDecimal> calculateSpecialFields(CreditEntrust creditEntrust, Date countDate) {
		// 取得该信托计划下的所有扎帐信息
		List<CreditEntrustTieOff> creditEntrustTieOffs = creditEntrustTieOffService
				.findByCreditEntrustIdAndOrderGroup(creditEntrust.getId());

		// 判断是否扎过账
		CreditEntrustTieOff creditEntrustTieOff = null;
		if (ObjectHelper.isNotEmpty(creditEntrustTieOffs)) {
			creditEntrustTieOff = creditEntrustTieOffs.get(0);
		}

		// 1. 判断上日备付金余额：如果扎过账，未分配备付金即为上日备付金余额；如果没有扎过账，则上日备付金余额为0
		BigDecimal yesterdayPaymentBalance = (creditEntrustTieOff == null ? BigDecimal.ZERO
				: creditEntrustTieOff.getNotEquippedPay());
		// 判断上日账面余额
		BigDecimal yesterdayBookBalance = (creditEntrustTieOff == null ? BigDecimal.ZERO
				: creditEntrustTieOff.getBookBalance());
		// 判断上日还款本金
		BigDecimal yesterdayCumulativeRecoveryPrincipal = (creditEntrustTieOff == null ? BigDecimal.ZERO
				: creditEntrustTieOff.getCumulativeRecoveryPrincipal());
		// 判断上日还款利息
		BigDecimal yesterdayCumulativeRecoveryInterest = (creditEntrustTieOff == null ? BigDecimal.ZERO
				: creditEntrustTieOff.getCumulativeRecoveryInterest());
		// 判断上日还款罚息
		BigDecimal yesterdayCumulativeRecoveryPenalty = (creditEntrustTieOff == null ? BigDecimal.ZERO
				: creditEntrustTieOff.getCumulativeRecoveryPenalty());
		// 判断上日还款违约金
		BigDecimal yesterdayCumulativeRecoveryLiqDamages = (creditEntrustTieOff == null ? BigDecimal.ZERO
				: creditEntrustTieOff.getCumulativeRecoveryLiqDamages());

		// 获取指定日期请款额
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("creditEntrustId", creditEntrust.getId());
		conditions.put("countDate", DateHelper.dateToLong(countDate, DateHelper.DATE_SHORT_SIMPLE_FORMAT));
		// 获取备付金数据
		List<SpareCapital> spareCapitals = spareCapitalService.findByConditions(conditions);

		// 2. 指定日期请款额
		BigDecimal beforeDayPayment = BigDecimal.ZERO;
		// 遍历备付金集合
		if (spareCapitals.size() != 0) {
			for (SpareCapital spareCapital : spareCapitals) {
				// 操作类型为请款
				if ("YWDM0014401".equals(spareCapital.getOperationType())
						&& ObjectHelper.isNotEmpty(spareCapital.getActualAmount())) {
					// 累加上实际到账金额
					beforeDayPayment = beforeDayPayment.add(spareCapital.getActualAmount());
				}
			}
		}

		// 3. 指定日期放款额
		BigDecimal cumulativeDischarge = creditEntrustService.cumulativeDischarge(creditEntrust.getId(),
				DateHelper.dateToLong(countDate, DateHelper.DATE_SHORT_SIMPLE_FORMAT));

		// 4. 指定日期退回金额 = 指定日期代偿退回 + 指定日期回购退回
		BigDecimal returnTodayAmount = BigDecimal.ZERO;
		BigDecimal compensatoryReturn = BigDecimal.ZERO;
		BigDecimal repurchaseReturn = BigDecimal.ZERO;
		// 信托计划借方资金
		List<CreditEntrustDebit> creditEntrustDebits = creditEntrustDebitService.findByConditions(conditions);
		for (int i = 0; i < creditEntrustDebits.size(); i++) {
			// 查询费用项
			List<CreditEntrustFeeItem> creditEntrustFeeItems = creditEntrustFeeItemService
					.findByBusinessId(creditEntrustDebits.get(i).getId());
			// 将金额分类统计
			Map<String, Object> returnFeeMap = accumulateFeeAmount(creditEntrustFeeItems);

			if ("YWDM0013712".equals(creditEntrustDebits.get(i).getDebitType())) { // 代偿退回
				compensatoryReturn = compensatoryReturn.add((BigDecimal) returnFeeMap.get("capital"))
						.add((BigDecimal) returnFeeMap.get("interest")).add((BigDecimal) returnFeeMap.get("penalty"));
			} else if ("YWDM0013714".equals(creditEntrustDebits.get(i).getDebitType())) { // 回购退回
				repurchaseReturn = repurchaseReturn.add((BigDecimal) returnFeeMap.get("capital"))
						.add((BigDecimal) returnFeeMap.get("interest")).add((BigDecimal) returnFeeMap.get("penalty"));
			}
		}
		returnTodayAmount = compensatoryReturn.add(repurchaseReturn);

		// 5. 已分配额度（已匹配资金部分）
		List<CaseLimitApply> caseLimitApplies = caseLimitApplyService.findByCreditEntrustId(creditEntrust.getId(),
				"YWDM0051058");
		BigDecimal alreadyAssignedAmount = BigDecimal.ZERO;
		for (int j = 0; j < caseLimitApplies.size(); j++) {
			alreadyAssignedAmount = alreadyAssignedAmount.add(caseLimitApplies.get(j).getApplyLimitAmount());
		}

		// 计算未分配备付金
		BigDecimal todayPaymentAmount = yesterdayPaymentBalance.add(beforeDayPayment).subtract(cumulativeDischarge)
				.subtract(returnTodayAmount).subtract(alreadyAssignedAmount);

		// 计算账面余额
		BigDecimal todayBookBalance = yesterdayBookBalance.add(beforeDayPayment).subtract(cumulativeDischarge)
				.subtract(returnTodayAmount);

		// 获取今日累计还款信息
		Map<String, BigDecimal> paramMap = null;
		try {
			paramMap = creditEntrustService.countTodayPaidAmount(creditEntrust.getId());
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("信托计划为空", e);
		}

		// 昨日还款信息 + 今日还款信息 = 截止信息
		BigDecimal todayCumulativeRecoveryPrincipal = yesterdayCumulativeRecoveryPrincipal
				.add(paramMap == null ? BigDecimal.ZERO : paramMap.get("cumulativeRecoveryPrincipal"));
		BigDecimal todayCumulativeRecoveryInterest = yesterdayCumulativeRecoveryInterest
				.add(paramMap == null ? BigDecimal.ZERO : paramMap.get("cumulativeRecoveryInterest"));
		BigDecimal todayCumulativeRecoveryPenalty = yesterdayCumulativeRecoveryPenalty
				.add(paramMap == null ? BigDecimal.ZERO : paramMap.get("cumulativeRecoveryPenalty"));
		BigDecimal todayCumulativeRecoveryLiqDamages = yesterdayCumulativeRecoveryLiqDamages
				.add(paramMap == null ? BigDecimal.ZERO : paramMap.get("cumulativeRecoveryLiqDamages"));

		// 已分配待放款
		BigDecimal allocatedLoan = BigDecimal.ZERO;
		List<CaseLimitApply> caseLimits = caseLimitApplyService.findByCreditEntrustId(creditEntrust.getId(),
				"YWDM0051058");
		for (int i = 0; i < caseLimits.size(); i++) {
			allocatedLoan = allocatedLoan.add(caseLimits.get(i).getApplyLimitAmount());
		}

		// 构建返回数据
		Map<String, BigDecimal> returnMap = new HashMap<String, BigDecimal>();
		returnMap.put("allocatedLoan", allocatedLoan);
		returnMap.put("notEquippedPay", todayPaymentAmount);
		returnMap.put("bookBalance", todayBookBalance);
		returnMap.put("cumulativeRecoveryPrincipal", todayCumulativeRecoveryPrincipal);
		returnMap.put("cumulativeRecoveryInterest", todayCumulativeRecoveryInterest);
		returnMap.put("cumulativeRecoveryPenalty", todayCumulativeRecoveryPenalty);
		returnMap.put("cumulativeRecoveryLiqDamages", todayCumulativeRecoveryLiqDamages);

		return returnMap;
	}
}
