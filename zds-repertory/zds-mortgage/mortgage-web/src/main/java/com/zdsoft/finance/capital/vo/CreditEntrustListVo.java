package com.zdsoft.finance.capital.vo;

import java.math.BigDecimal;

/**
 * 资金信托列表临时Vo
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
public class CreditEntrustListVo {

	/**
	 * 计划名称
	 */
	private String planName;

	/**
	 * 优先本金
	 */
	private BigDecimal priorityPrincipal;

	/**
	 * 劣后本金-货币资金
	 */
	private BigDecimal monetaryFund;

	/**
	 * 劣后本金-债券转入
	 */
	private BigDecimal bondTransfer;

	/**
	 * 客户还款-本金
	 */
	private BigDecimal repaymentPrincipal;

	/**
	 * 客户还款-利息
	 */
	private BigDecimal repaymentInterest;

	/**
	 * 代偿-本金
	 */
	private BigDecimal compensatoryPrincipal;

	/**
	 * 代偿-利息
	 */
	private BigDecimal CompensatoryInterest;

	/**
	 * 回购-本金
	 */
	private BigDecimal backPrincipal;

	/**
	 * 回购-利息
	 */
	private BigDecimal backInterest;

	/**
	 * 其他利息
	 */
	private BigDecimal otherInterest;

	/**
	 * 暂待收款
	 */
	private BigDecimal pendingPayment;

	/**
	 * 信托保证基金
	 */
	private BigDecimal trustGuaranteeFund;

	/**
	 * 流入小计
	 */
	private BigDecimal inflowSubtotal;

	/**
	 * 退款
	 */
	private BigDecimal refund;

	/**
	 * 优先资金-优先本金
	 */
	private BigDecimal fundsPriorityPrincipal;

	/**
	 * 优先资金-优先利息
	 */
	private BigDecimal fundsPriorityInterest;

	/**
	 * 劣后资金-劣后本金
	 */
	private BigDecimal badPrincipal;

	/**
	 * 劣后资金-劣后收益
	 */
	private BigDecimal inferiorPostYield;

	/**
	 * 信托保障基金(1%)
	 */
	private BigDecimal trustGuaranteeFundPercent;

	/**
	 * 信托费用
	 */
	private BigDecimal trustExpense;

	/**
	 * 银行托管费(0.08%)
	 */
	private BigDecimal bankCustodianFee;

	/**
	 * 信托服务费(0.8%)
	 */
	private BigDecimal trustServiceFee;

	/**
	 * 展期服务费
	 */
	private BigDecimal extensionServiceFee;

	/**
	 * 印花税
	 */
	private BigDecimal stampDuty;

	/**
	 * 深泛联管理费
	 */
	private BigDecimal sflManagementFee;

	/**
	 * 代偿
	 */
	private BigDecimal compensatory;

	/**
	 * 回购
	 */
	private BigDecimal buyBack;

	/**
	 * 累计贷款投放
	 */
	private BigDecimal cumulativeLoan;

	/**
	 * 流出小计
	 */
	private BigDecimal outflowSubtotal;

	/**
	 * 截留额度
	 */
	private BigDecimal interceptionAmount;

	/**
	 * 剩余可分配
	 */
	private BigDecimal residualDistribution;

	/**
	 * 已分配待放款
	 */
	private BigDecimal allocatedLoan;

	/**
	 * 未分配备付金
	 */
	private BigDecimal notEquippedPay;

	/**
	 * 账面余额
	 */
	private BigDecimal bookBalance;

	/**
	 * 可到账资金
	 */
	private BigDecimal availableAccountFunds;

	/**
	 * 可用资金总额
	 */
	private BigDecimal totalAvailableFunds;

	/**
	 * 累计收回本金
	 */
	private BigDecimal cumulativeRecoveryPrincipal;

	/**
	 * 累计收回利息
	 */
	private BigDecimal cumulativeRecoveryInterest;

	/**
	 * 资金状态
	 */
	private Integer capitalStatus;

	/**
	 * 转让金额
	 */
	private BigDecimal transferAmount;

	/**
	 * 转让日期
	 */
	private Long transferDate;

	/**
	 * 到期日期
	 */
	private Long maturityDate;

	/**
	 * 创建日期
	 */
	private Long createTime;

	/**
	 * 提交状态
	 */
	private Integer status;

	/**
	 * id
	 */
	private String id;

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public BigDecimal getPriorityPrincipal() {
		return priorityPrincipal;
	}

	public void setPriorityPrincipal(BigDecimal priorityPrincipal) {
		this.priorityPrincipal = priorityPrincipal;
	}

	public BigDecimal getMonetaryFund() {
		return monetaryFund;
	}

	public void setMonetaryFund(BigDecimal monetaryFund) {
		this.monetaryFund = monetaryFund;
	}

	public BigDecimal getBondTransfer() {
		return bondTransfer;
	}

	public void setBondTransfer(BigDecimal bondTransfer) {
		this.bondTransfer = bondTransfer;
	}

	public BigDecimal getRepaymentPrincipal() {
		return repaymentPrincipal;
	}

	public void setRepaymentPrincipal(BigDecimal repaymentPrincipal) {
		this.repaymentPrincipal = repaymentPrincipal;
	}

	public BigDecimal getRepaymentInterest() {
		return repaymentInterest;
	}

	public void setRepaymentInterest(BigDecimal repaymentInterest) {
		this.repaymentInterest = repaymentInterest;
	}

	public BigDecimal getCompensatoryPrincipal() {
		return compensatoryPrincipal;
	}

	public void setCompensatoryPrincipal(BigDecimal compensatoryPrincipal) {
		this.compensatoryPrincipal = compensatoryPrincipal;
	}

	public BigDecimal getCompensatoryInterest() {
		return CompensatoryInterest;
	}

	public void setCompensatoryInterest(BigDecimal compensatoryInterest) {
		CompensatoryInterest = compensatoryInterest;
	}

	public BigDecimal getBackPrincipal() {
		return backPrincipal;
	}

	public void setBackPrincipal(BigDecimal backPrincipal) {
		this.backPrincipal = backPrincipal;
	}

	public BigDecimal getBackInterest() {
		return backInterest;
	}

	public void setBackInterest(BigDecimal backInterest) {
		this.backInterest = backInterest;
	}

	public BigDecimal getOtherInterest() {
		return otherInterest;
	}

	public void setOtherInterest(BigDecimal otherInterest) {
		this.otherInterest = otherInterest;
	}

	public BigDecimal getPendingPayment() {
		return pendingPayment;
	}

	public void setPendingPayment(BigDecimal pendingPayment) {
		this.pendingPayment = pendingPayment;
	}

	public BigDecimal getTrustGuaranteeFund() {
		return trustGuaranteeFund;
	}

	public void setTrustGuaranteeFund(BigDecimal trustGuaranteeFund) {
		this.trustGuaranteeFund = trustGuaranteeFund;
	}

	public BigDecimal getInflowSubtotal() {
		return inflowSubtotal;
	}

	public void setInflowSubtotal(BigDecimal inflowSubtotal) {
		this.inflowSubtotal = inflowSubtotal;
	}

	public BigDecimal getRefund() {
		return refund;
	}

	public void setRefund(BigDecimal refund) {
		this.refund = refund;
	}

	public BigDecimal getFundsPriorityPrincipal() {
		return fundsPriorityPrincipal;
	}

	public void setFundsPriorityPrincipal(BigDecimal fundsPriorityPrincipal) {
		this.fundsPriorityPrincipal = fundsPriorityPrincipal;
	}

	public BigDecimal getFundsPriorityInterest() {
		return fundsPriorityInterest;
	}

	public void setFundsPriorityInterest(BigDecimal fundsPriorityInterest) {
		this.fundsPriorityInterest = fundsPriorityInterest;
	}

	public BigDecimal getBadPrincipal() {
		return badPrincipal;
	}

	public void setBadPrincipal(BigDecimal badPrincipal) {
		this.badPrincipal = badPrincipal;
	}

	public BigDecimal getInferiorPostYield() {
		return inferiorPostYield;
	}

	public void setInferiorPostYield(BigDecimal inferiorPostYield) {
		this.inferiorPostYield = inferiorPostYield;
	}

	public BigDecimal getTrustGuaranteeFundPercent() {
		return trustGuaranteeFundPercent;
	}

	public void setTrustGuaranteeFundPercent(BigDecimal trustGuaranteeFundPercent) {
		this.trustGuaranteeFundPercent = trustGuaranteeFundPercent;
	}

	public BigDecimal getTrustExpense() {
		return trustExpense;
	}

	public void setTrustExpense(BigDecimal trustExpense) {
		this.trustExpense = trustExpense;
	}

	public BigDecimal getBankCustodianFee() {
		return bankCustodianFee;
	}

	public void setBankCustodianFee(BigDecimal bankCustodianFee) {
		this.bankCustodianFee = bankCustodianFee;
	}

	public BigDecimal getTrustServiceFee() {
		return trustServiceFee;
	}

	public void setTrustServiceFee(BigDecimal trustServiceFee) {
		this.trustServiceFee = trustServiceFee;
	}

	public BigDecimal getExtensionServiceFee() {
		return extensionServiceFee;
	}

	public void setExtensionServiceFee(BigDecimal extensionServiceFee) {
		this.extensionServiceFee = extensionServiceFee;
	}

	public BigDecimal getStampDuty() {
		return stampDuty;
	}

	public void setStampDuty(BigDecimal stampDuty) {
		this.stampDuty = stampDuty;
	}

	public BigDecimal getSflManagementFee() {
		return sflManagementFee;
	}

	public void setSflManagementFee(BigDecimal sflManagementFee) {
		this.sflManagementFee = sflManagementFee;
	}

	public BigDecimal getCompensatory() {
		return compensatory;
	}

	public void setCompensatory(BigDecimal compensatory) {
		this.compensatory = compensatory;
	}

	public BigDecimal getBuyBack() {
		return buyBack;
	}

	public void setBuyBack(BigDecimal buyBack) {
		this.buyBack = buyBack;
	}

	public BigDecimal getCumulativeLoan() {
		return cumulativeLoan;
	}

	public void setCumulativeLoan(BigDecimal cumulativeLoan) {
		this.cumulativeLoan = cumulativeLoan;
	}

	public BigDecimal getOutflowSubtotal() {
		return outflowSubtotal;
	}

	public void setOutflowSubtotal(BigDecimal outflowSubtotal) {
		this.outflowSubtotal = outflowSubtotal;
	}

	public BigDecimal getInterceptionAmount() {
		return interceptionAmount;
	}

	public void setInterceptionAmount(BigDecimal interceptionAmount) {
		this.interceptionAmount = interceptionAmount;
	}

	public BigDecimal getResidualDistribution() {
		return residualDistribution;
	}

	public void setResidualDistribution(BigDecimal residualDistribution) {
		this.residualDistribution = residualDistribution;
	}

	public BigDecimal getAllocatedLoan() {
		return allocatedLoan;
	}

	public void setAllocatedLoan(BigDecimal allocatedLoan) {
		this.allocatedLoan = allocatedLoan;
	}

	public BigDecimal getNotEquippedPay() {
		return notEquippedPay;
	}

	public void setNotEquippedPay(BigDecimal notEquippedPay) {
		this.notEquippedPay = notEquippedPay;
	}

	public BigDecimal getBookBalance() {
		return bookBalance;
	}

	public void setBookBalance(BigDecimal bookBalance) {
		this.bookBalance = bookBalance;
	}

	public BigDecimal getAvailableAccountFunds() {
		return availableAccountFunds;
	}

	public void setAvailableAccountFunds(BigDecimal availableAccountFunds) {
		this.availableAccountFunds = availableAccountFunds;
	}

	public BigDecimal getTotalAvailableFunds() {
		return totalAvailableFunds;
	}

	public void setTotalAvailableFunds(BigDecimal totalAvailableFunds) {
		this.totalAvailableFunds = totalAvailableFunds;
	}

	public BigDecimal getCumulativeRecoveryPrincipal() {
		return cumulativeRecoveryPrincipal;
	}

	public void setCumulativeRecoveryPrincipal(BigDecimal cumulativeRecoveryPrincipal) {
		this.cumulativeRecoveryPrincipal = cumulativeRecoveryPrincipal;
	}

	public BigDecimal getCumulativeRecoveryInterest() {
		return cumulativeRecoveryInterest;
	}

	public void setCumulativeRecoveryInterest(BigDecimal cumulativeRecoveryInterest) {
		this.cumulativeRecoveryInterest = cumulativeRecoveryInterest;
	}

	public Integer getCapitalStatus() {
		return capitalStatus;
	}

	public void setCapitalStatus(Integer capitalStatus) {
		this.capitalStatus = capitalStatus;
	}

	public BigDecimal getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(BigDecimal transferAmount) {
		this.transferAmount = transferAmount;
	}

	public Long getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Long transferDate) {
		this.transferDate = transferDate;
	}

	public Long getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Long maturityDate) {
		this.maturityDate = maturityDate;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
