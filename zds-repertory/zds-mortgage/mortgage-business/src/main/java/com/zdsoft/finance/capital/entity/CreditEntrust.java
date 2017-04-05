package com.zdsoft.finance.capital.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.zdsoft.finance.cooperator.entity.Capitalist;
import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CreditEntrust.java
 * @ClassName: CreditEntrust
 * @Description: 信托计划
 * @author liuwei
 * @date 2017年2月8日 上午10:23:02
 * @version V1.0
 */
@Entity
@Table(name = "cptl_credit_entrust")
public class CreditEntrust extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2006175870147776982L;

	/**
	 * 资方
	 */
	@ManyToOne
	@JoinColumn(name = "capitalistId", nullable = false)
	private Capitalist capitallist;

	/**
	 * 成立时间
	 */
	@Column
	private Long establishmentDate;

	/**
	 * 是否主计划
	 */
	@Column
	@Type(type = "true_false")
	private Boolean isMasterPlan = Boolean.valueOf(false);

	/**
	 * 信托类型
	 */
	@Column(length = 32)
	private String trustType;

	/**
	 * 合同费率
	 */
	@Column(precision = 18, scale = 6)
	private Double contractRate;

	/**
	 * 信托计划名称
	 */
	@Column(length = 64)
	private String creditEntrustName;

	/**
	 * 本金规模
	 */
	@Column(length = 16)
	private String principaScale;

	/**
	 * 转让状态
	 */
	@Column(length = 32)
	private String assignmentState;

	/**
	 * 转让规模
	 */
	@Column(length = 32)
	private String assignmentScale;

	/**
	 * 最低限额
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal minQuota = BigDecimal.ZERO;

	/**
	 * 服务费率
	 */
	@Column(precision = 18, scale = 6)
	private Double severRate;

	/**
	 * 保管费率
	 */
	@Column(precision = 18, scale = 6)
	private Double keepRate;

	/**
	 * 管理费率
	 */
	@Column(precision = 18, scale = 6)
	private Double managerRate;

	/**
	 * 待拨户开户行
	 */
	@Column(length = 64)
	private String waitApprBank;

	/**
	 * 待拨户账户名
	 */
	@Column(length = 32)
	private String waitApprName;

	/**
	 * 待拨户账号
	 */
	@Column(length = 32)
	private String waitApprNo;

	/**
	 * 归集户开户行
	 */
	@Column(length = 64)
	private String collectionAccountBank;

	/**
	 * 归集户账户名
	 */
	@Column(length = 32)
	private String collectionAccountName;

	/**
	 * 归集户账号
	 */
	@Column(length = 32)
	private String collectionAccountNo;

	/**
	 * 专户开户行
	 */
	@Column(length = 64)
	private String specialAccountBank;

	/**
	 * 专户账户名
	 */
	@Column(length = 32)
	private String specialAccountName;

	/**
	 * 专户账号
	 */
	@Column(length = 32)
	private String specialAccountNo;

	/**
	 * 备付户开户行
	 */
	@Column(length = 64)
	private String spareAccountBank;

	/**
	 * 备付户账户名
	 */
	@Column(length = 32)
	private String spareAccountName;

	/**
	 * 备付户账号
	 */
	@Column(length = 32)
	private String spareAccountNo;

	/**
	 * 是否开放申请额度
	 */
	@Column
	@Type(type = "true_false")
	private Boolean isOpenApply = Boolean.valueOf(false);

	/**
	 * 是否资金自动匹配
	 */
	@Column
	@Type(type = "true_false")
	private Boolean isAutoMatch = Boolean.valueOf(false);

	/**
	 * 截留额度
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal retain = BigDecimal.ZERO;

	/**
	 * 备注
	 */
	@Column(length = 500)
	private String remark;

	/**
	 * 临时id
	 */
	private transient String tempUuid;

	/**
	 * 备付金余额
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal paymentBalance = BigDecimal.ZERO;

	/**
	 * 收入余额
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal incomeBalance = BigDecimal.ZERO;

	/**
	 * 清分账号
	 */
	@Column(length = 32)
	private String clearingAccount;

	/**
	 * 商户id
	 */
	@Column(length = 32)
	private String merchantID;

	/**
	 * 转让计划id
	 */
	@Column(length = 32)
	private String transferPlanId;

	/**
	 * 优先本金
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal priorityPrincipal = BigDecimal.ZERO;

	/**
	 * 劣后本金-货币
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal currency = BigDecimal.ZERO;

	/**
	 * 劣后本金-债券转入
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal bondTransfer = BigDecimal.ZERO;

	/**
	 * 客户还款-本金
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal repaymentPrincipal = BigDecimal.ZERO;

	/**
	 * 客户还款-利息
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal repaymentInterest = BigDecimal.ZERO;

	/**
	 * 代偿-本金
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal compensatoryPrincipal = BigDecimal.ZERO;

	/**
	 * 代偿-利息
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal compensatoryInterest = BigDecimal.ZERO;

	/**
	 * 回购-本金
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal backPrincipal = BigDecimal.ZERO;

	/**
	 * 回购-利息
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal backInterest = BigDecimal.ZERO;

	/**
	 * 其他利息
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal otherInterest = BigDecimal.ZERO;

	/**
	 * 暂待收款
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal pendingPayment = BigDecimal.ZERO;

	/**
	 * 信托保证基金
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal trustGuaranteeFund = BigDecimal.ZERO;

	/**
	 * 流入小计
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal inflowSubtotal = BigDecimal.ZERO;

	/**
	 * 退客户款
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal refund = BigDecimal.ZERO;

	/**
	 * 优先资金-优先本金
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal fundsPriorityPrincipal = BigDecimal.ZERO;

	/**
	 * 优先资金-优先利息
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal fundsPriorityInterest = BigDecimal.ZERO;

	/**
	 * 劣后资金-劣后本金
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal badPrincipal = BigDecimal.ZERO;

	/**
	 * 劣后资金-劣后收益
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal inferiorPostYield = BigDecimal.ZERO;

	/**
	 * 信托保障基金(1%)
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal trustGuaranteeFundPercent = BigDecimal.ZERO;

	/**
	 * 信托费用
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal trustExpense = BigDecimal.ZERO;

	/**
	 * 银行托管费(0.08%)
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal bankCustodianFee = BigDecimal.ZERO;

	/**
	 * 信托服务费(0.8%)
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal trustServiceFee = BigDecimal.ZERO;

	/**
	 * 展期服务费(0.15%)
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal extensionServiceFee = BigDecimal.ZERO;

	/**
	 * 印花税(0.05%)
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal stampDuty = BigDecimal.ZERO;

	/**
	 * 深泛联管理费(8%)
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal sflManagementFee = BigDecimal.ZERO;

	/**
	 * 代偿退回
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal compensatory = BigDecimal.ZERO;

	/**
	 * 回购退回
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal buyBack = BigDecimal.ZERO;

	/**
	 * 累计贷款投放
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal cumulativeLoan = BigDecimal.ZERO;

	/**
	 * 流出小计
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal outflowSubtotal = BigDecimal.ZERO;

	/**
	 * 剩余可分配
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal residualDistribution = BigDecimal.ZERO;

	/**
	 * 已分配待放款
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal allocatedLoan = BigDecimal.ZERO;

	/**
	 * 未分配备付金
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal notEquippedPay = BigDecimal.ZERO;

	/**
	 * 账面余额
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal bookBalance = BigDecimal.ZERO;

	/**
	 * 累计收回本金
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal cumulativeRecoveryPrincipal = BigDecimal.ZERO;

	/**
	 * 累计收回利息
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal cumulativeRecoveryInterest = BigDecimal.ZERO;

	/**
	 * 累计收回罚息
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal cumulativeRecoveryPenalty = BigDecimal.ZERO;

	/**
	 * 累计收回违约金
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal cumulativeRecoveryLiqDamages = BigDecimal.ZERO;

	/**
	 * 资金状态
	 */
	@Column(length = 32)
	private String capitalStatus;

	/**
	 * 信托本金
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal trustPrincipal = BigDecimal.ZERO;

	/**
	 * 转让金额
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal transferAmount = BigDecimal.ZERO;

	/**
	 * 转让日期
	 */
	@Column
	private Long transferDate;

	/**
	 * 到期日期
	 */
	@Column
	private Long maturityDate;

	/**
	 * 创建日期
	 */
	@Column
	private Long createDate;

	/**
	 * 提交状态
	 */
	@Column(length = 32)
	private String status;

	public Long getEstablishmentDate() {
		return establishmentDate;
	}

	public void setEstablishmentDate(Long establishmentDate) {
		this.establishmentDate = establishmentDate;
	}

	public Boolean getIsMasterPlan() {
		return isMasterPlan;
	}

	public void setIsMasterPlan(Boolean isMasterPlan) {
		this.isMasterPlan = isMasterPlan;
	}

	public Double getContractRate() {
		return contractRate;
	}

	public void setContractRate(Double contractRate) {
		this.contractRate = contractRate;
	}

	public String getCreditEntrustName() {
		return creditEntrustName;
	}

	public void setCreditEntrustName(String creditEntrustName) {
		this.creditEntrustName = creditEntrustName;
	}

	public String getPrincipaScale() {
		return principaScale;
	}

	public void setPrincipaScale(String principaScale) {
		this.principaScale = principaScale;
	}

	public String getAssignmentState() {
		return assignmentState;
	}

	public void setAssignmentState(String assignmentState) {
		this.assignmentState = assignmentState;
	}

	public String getAssignmentScale() {
		return assignmentScale;
	}

	public void setAssignmentScale(String assignmentScale) {
		this.assignmentScale = assignmentScale;
	}

	public BigDecimal getMinQuota() {
		return minQuota;
	}

	public void setMinQuota(BigDecimal minQuota) {
		this.minQuota = minQuota;
	}

	public Double getSeverRate() {
		return severRate;
	}

	public void setSeverRate(Double severRate) {
		this.severRate = severRate;
	}

	public Double getKeepRate() {
		return keepRate;
	}

	public void setKeepRate(Double keepRate) {
		this.keepRate = keepRate;
	}

	public Double getManagerRate() {
		return managerRate;
	}

	public void setManagerRate(Double managerRate) {
		this.managerRate = managerRate;
	}

	public String getWaitApprBank() {
		return waitApprBank;
	}

	public void setWaitApprBank(String waitApprBank) {
		this.waitApprBank = waitApprBank;
	}

	public String getWaitApprName() {
		return waitApprName;
	}

	public void setWaitApprName(String waitApprName) {
		this.waitApprName = waitApprName;
	}

	public String getWaitApprNo() {
		return waitApprNo;
	}

	public void setWaitApprNo(String waitApprNo) {
		this.waitApprNo = waitApprNo;
	}

	public String getCollectionAccountBank() {
		return collectionAccountBank;
	}

	public void setCollectionAccountBank(String collectionAccountBank) {
		this.collectionAccountBank = collectionAccountBank;
	}

	public String getCollectionAccountName() {
		return collectionAccountName;
	}

	public void setCollectionAccountName(String collectionAccountName) {
		this.collectionAccountName = collectionAccountName;
	}

	public String getCollectionAccountNo() {
		return collectionAccountNo;
	}

	public void setCollectionAccountNo(String collectionAccountNo) {
		this.collectionAccountNo = collectionAccountNo;
	}

	public String getSpecialAccountBank() {
		return specialAccountBank;
	}

	public void setSpecialAccountBank(String specialAccountBank) {
		this.specialAccountBank = specialAccountBank;
	}

	public String getSpecialAccountName() {
		return specialAccountName;
	}

	public void setSpecialAccountName(String specialAccountName) {
		this.specialAccountName = specialAccountName;
	}

	public String getSpecialAccountNo() {
		return specialAccountNo;
	}

	public void setSpecialAccountNo(String specialAccountNo) {
		this.specialAccountNo = specialAccountNo;
	}

	public String getSpareAccountBank() {
		return spareAccountBank;
	}

	public void setSpareAccountBank(String spareAccountBank) {
		this.spareAccountBank = spareAccountBank;
	}

	public String getSpareAccountName() {
		return spareAccountName;
	}

	public void setSpareAccountName(String spareAccountName) {
		this.spareAccountName = spareAccountName;
	}

	public String getSpareAccountNo() {
		return spareAccountNo;
	}

	public void setSpareAccountNo(String spareAccountNo) {
		this.spareAccountNo = spareAccountNo;
	}

	public Boolean getIsOpenApply() {
		return isOpenApply;
	}

	public void setIsOpenApply(Boolean isOpenApply) {
		this.isOpenApply = isOpenApply;
	}

	public Boolean getIsAutoMatch() {
		return isAutoMatch;
	}

	public void setIsAutoMatch(Boolean isAutoMatch) {
		this.isAutoMatch = isAutoMatch;
	}

	public BigDecimal getRetain() {
		return retain;
	}

	public void setRetain(BigDecimal retain) {
		this.retain = retain;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTrustType() {
		return trustType;
	}

	public void setTrustType(String trustType) {
		this.trustType = trustType;
	}

	public String getTempUuid() {
		return tempUuid;
	}

	public void setTempUuid(String tempUuid) {
		this.tempUuid = tempUuid;
	}

	public Capitalist getCapitallist() {
		return capitallist;
	}

	public void setCapitallist(Capitalist capitallist) {
		this.capitallist = capitallist;
	}

	public BigDecimal getPaymentBalance() {
		return paymentBalance;
	}

	public void setPaymentBalance(BigDecimal paymentBalance) {
		this.paymentBalance = paymentBalance;
	}

	public BigDecimal getIncomeBalance() {
		return incomeBalance;
	}

	public void setIncomeBalance(BigDecimal incomeBalance) {
		this.incomeBalance = incomeBalance;
	}

	public String getClearingAccount() {
		return clearingAccount;
	}

	public void setClearingAccount(String clearingAccount) {
		this.clearingAccount = clearingAccount;
	}

	public String getMerchantID() {
		return merchantID;
	}

	public void setMerchantID(String merchantID) {
		this.merchantID = merchantID;
	}

	public BigDecimal getPriorityPrincipal() {
		return priorityPrincipal;
	}

	public void setPriorityPrincipal(BigDecimal priorityPrincipal) {
		this.priorityPrincipal = priorityPrincipal;
	}

	public BigDecimal getCurrency() {
		return currency;
	}

	public void setCurrency(BigDecimal currency) {
		this.currency = currency;
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
		return compensatoryInterest;
	}

	public void setCompensatoryInterest(BigDecimal compensatoryInterest) {
		this.compensatoryInterest = compensatoryInterest;
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

	public BigDecimal getCumulativeRecoveryPenalty() {
		return cumulativeRecoveryPenalty;
	}

	public void setCumulativeRecoveryPenalty(BigDecimal cumulativeRecoveryPenalty) {
		this.cumulativeRecoveryPenalty = cumulativeRecoveryPenalty;
	}

	public BigDecimal getCumulativeRecoveryLiqDamages() {
		return cumulativeRecoveryLiqDamages;
	}

	public void setCumulativeRecoveryLiqDamages(BigDecimal cumulativeRecoveryLiqDamages) {
		this.cumulativeRecoveryLiqDamages = cumulativeRecoveryLiqDamages;
	}

	public String getCapitalStatus() {
		return capitalStatus;
	}

	public void setCapitalStatus(String capitalStatus) {
		this.capitalStatus = capitalStatus;
	}

	public BigDecimal getTrustPrincipal() {
		return trustPrincipal;
	}

	public void setTrustPrincipal(BigDecimal trustPrincipal) {
		this.trustPrincipal = trustPrincipal;
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

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTransferPlanId() {
		return transferPlanId;
	}

	public void setTransferPlanId(String transferPlanId) {
		this.transferPlanId = transferPlanId;
	}

}
