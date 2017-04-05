package com.zdsoft.finance.finance.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CaseReceivableDay.java
 * @ClassName: CaseReceivableDay
 * @Description: 案件每天应还
 * @author jincheng
 * @date 2017年2月16日 上午9:54:23
 * @version V1.0
 */
@Entity
@Table(name = "fin_case_receivable_day")
public class CaseReceivableDay extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 每日
	 */
	@Column()
	private Long batchDay;
	
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
	 * 是否逾期
	 */
	@Column(name = "isOverDue")
	@org.hibernate.annotations.Type(type="true_false")
	private Boolean isOverDue=false;

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

	public Long getBatchDay() {
		return batchDay;
	}

	public void setBatchDay(Long batchDay) {
		this.batchDay = batchDay;
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

	public BigDecimal getCurrentPlanPenalty() {
		return currentPlanPenalty;
	}

	public void setCurrentPlanPenalty(BigDecimal currentPlanPenalty) {
		this.currentPlanPenalty = currentPlanPenalty;
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

	public Boolean getIsOverDue() {
		return isOverDue;
	}

	public void setIsOverDue(Boolean isOverDue) {
		this.isOverDue = isOverDue;
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

	public BigDecimal getRemainPrincipal() {
		return remainPrincipal;
	}

	public void setRemainPrincipal(BigDecimal remainPrincipal) {
		this.remainPrincipal = remainPrincipal;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
