package com.zdsoft.finance.finance.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CustomerReceivable.java
 * @ClassName: CustomerReceivable
 * @Description: 客户应还
 * @author jincheng
 * @date 2017年2月16日 上午9:54:23
 * @version V1.0
 */
@Entity
@Table(name = "fin_customer_receivable")
public class CustomerReceivable extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 每日
	 */
	@Column()
	private Long batchDay;
	
	/**
	 * 还款日期
	 */
	@Column()
	private Long planRepayDate;
	
	/**
	 * 期数
	 */
	@Column()
	private Integer periods;
	
	/**
	 * 放款金额
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal loanAnount = BigDecimal.ZERO;
	
	/**
	 * 总利息收入
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal interestAmount = BigDecimal.ZERO;
	
	/**
	 * 当期剩余本金
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal remainPrincipal = BigDecimal.ZERO;

	/**
	 * 本金
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal planPrincipalAmount = BigDecimal.ZERO;

	/**
	 * 利息
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal planInterestAmount = BigDecimal.ZERO;

	/**
	 * 服务费
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal planServiceFee = BigDecimal.ZERO;

	/**
	 * 违约金
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal planDamages = BigDecimal.ZERO;

	/**
	 * 罚息
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal planPenalty = BigDecimal.ZERO;
	
	/**
	 * 当期罚息
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal currentPlanPenalty = BigDecimal.ZERO;
	
	/**
	 * 当期本金
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal paidPrincipalAmount = BigDecimal.ZERO;

	/**
	 * 当期利息
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal paidInterestAmount = BigDecimal.ZERO;

	/**
	 * 当期服务费
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal paidServiceFee = BigDecimal.ZERO;

	/**
	 * 罚息
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal paidPenalty = BigDecimal.ZERO;

	/**
	 * 当期罚息
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal currentPaidPenalty = BigDecimal.ZERO;

	/**
	 * 违约金
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal paidDamages = BigDecimal.ZERO;
	
	/**
	 * 减免当期罚息
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal currentReductionPenalty = BigDecimal.ZERO;

	/**
	 * 减免罚息
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal reductionPenalty = BigDecimal.ZERO;

	/**
	 * 减免违约金
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal reductionDamages = BigDecimal.ZERO;
	
	/**
	 * 逾期次数
	 */
	@Column
	private Integer ovreDueTime;
	
	/**
	 * 逾期开始日期
	 */
	@Column
	private Long  ovreDueStartDate;
	
	/**
	 * 逾期天数
	 */
	@Column
	private Integer days;

	/**
	 * 案件id
	 */
	@Column(length = 32)
	private String caseApplyId;

	/**
	 * 借款人id
	 */
	@Column(length = 32)
	private String customerId;
	
	/**
	 * 借款人姓名
	 */
	@Column(length = 100)
	private String customerName;
	
	/**
	 * 是否有效
	 */
	@Column(name = "isEffect")
	@org.hibernate.annotations.Type(type="true_false")
	private Boolean isEffect=true;

	public Long getPlanRepayDate() {
		return planRepayDate;
	}

	public void setPlanRepayDate(Long planRepayDate) {
		this.planRepayDate = planRepayDate;
	}

	public BigDecimal getRemainPrincipal() {
		return remainPrincipal;
	}

	public void setRemainPrincipal(BigDecimal remainPrincipal) {
		this.remainPrincipal = remainPrincipal;
	}

	public BigDecimal getPlanPrincipalAmount() {
		return planPrincipalAmount;
	}

	public void setPlanPrincipalAmount(BigDecimal planPrincipalAmount) {
		this.planPrincipalAmount = planPrincipalAmount;
	}

	public BigDecimal getPlanInterestAmount() {
		return planInterestAmount;
	}

	public Long getOvreDueStartDate() {
		return ovreDueStartDate;
	}

	public void setOvreDueStartDate(Long ovreDueStartDate) {
		this.ovreDueStartDate = ovreDueStartDate;
	}

	public void setPlanInterestAmount(BigDecimal planInterestAmount) {
		this.planInterestAmount = planInterestAmount;
	}

	public BigDecimal getPlanServiceFee() {
		return planServiceFee;
	}

	public void setPlanServiceFee(BigDecimal planServiceFee) {
		this.planServiceFee = planServiceFee;
	}

	public BigDecimal getPlanDamages() {
		return planDamages;
	}

	public void setPlanDamages(BigDecimal planDamages) {
		this.planDamages = planDamages;
	}

	public BigDecimal getPlanPenalty() {
		return planPenalty;
	}

	public void setPlanPenalty(BigDecimal planPenalty) {
		this.planPenalty = planPenalty;
	}

	public BigDecimal getPaidPrincipalAmount() {
		return paidPrincipalAmount;
	}

	public void setPaidPrincipalAmount(BigDecimal paidPrincipalAmount) {
		this.paidPrincipalAmount = paidPrincipalAmount;
	}

	public BigDecimal getPaidInterestAmount() {
		return paidInterestAmount;
	}

	public void setPaidInterestAmount(BigDecimal paidInterestAmount) {
		this.paidInterestAmount = paidInterestAmount;
	}

	public BigDecimal getPaidServiceFee() {
		return paidServiceFee;
	}

	public void setPaidServiceFee(BigDecimal paidServiceFee) {
		this.paidServiceFee = paidServiceFee;
	}

	public BigDecimal getPaidPenalty() {
		return paidPenalty;
	}

	public void setPaidPenalty(BigDecimal paidPenalty) {
		this.paidPenalty = paidPenalty;
	}

	public BigDecimal getCurrentPaidPenalty() {
		return currentPaidPenalty;
	}

	public void setCurrentPaidPenalty(BigDecimal currentPaidPenalty) {
		this.currentPaidPenalty = currentPaidPenalty;
	}

	public BigDecimal getPaidDamages() {
		return paidDamages;
	}

	public void setPaidDamages(BigDecimal paidDamages) {
		this.paidDamages = paidDamages;
	}

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getCurrentPlanPenalty() {
		return currentPlanPenalty;
	}

	public void setCurrentPlanPenalty(BigDecimal currentPlanPenalty) {
		this.currentPlanPenalty = currentPlanPenalty;
	}

	public Integer getOvreDueTime() {
		return ovreDueTime;
	}

	public void setOvreDueTime(Integer ovreDueTime) {
		this.ovreDueTime = ovreDueTime;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public BigDecimal getLoanAnount() {
		return loanAnount;
	}

	public void setLoanAnount(BigDecimal loanAnount) {
		this.loanAnount = loanAnount;
	}

	public BigDecimal getInterestAmount() {
		return interestAmount;
	}

	public void setInterestAmount(BigDecimal interestAmount) {
		this.interestAmount = interestAmount;
	}

	public Long getBatchDay() {
		return batchDay;
	}

	public void setBatchDay(Long batchDay) {
		this.batchDay = batchDay;
	}

	public Boolean getIsEffect() {
		return isEffect;
	}

	public void setIsEffect(Boolean isEffect) {
		this.isEffect = isEffect;
	}

	public Integer getPeriods() {
		return periods;
	}

	public void setPeriods(Integer periods) {
		this.periods = periods;
	}

	public BigDecimal getCurrentReductionPenalty() {
		return currentReductionPenalty;
	}

	public void setCurrentReductionPenalty(BigDecimal currentReductionPenalty) {
		this.currentReductionPenalty = currentReductionPenalty;
	}

	public BigDecimal getReductionPenalty() {
		return reductionPenalty;
	}

	public void setReductionPenalty(BigDecimal reductionPenalty) {
		this.reductionPenalty = reductionPenalty;
	}

	public BigDecimal getReductionDamages() {
		return reductionDamages;
	}

	public void setReductionDamages(BigDecimal reductionDamages) {
		this.reductionDamages = reductionDamages;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
