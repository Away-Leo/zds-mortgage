package com.zdsoft.finance.finance.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: RepaymentAmountAllot.java
 * @ClassName: RepaymentAmountAllot
 * @Description: 还款-收款单-金额分配
 * @author jincheng
 * @date 2017年2月17日 下午4:59:40
 * @version V1.0
 */
@Entity
@Table(name = "fin_amount_allot")
public class RepaymentAmountAllot extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 案件id
	 */
	@Column(length = 32)
	private String caseApplyId;

	/**
	 * 收款单id
	 */
	@Column(length = 32)
	private String receiptId;

	/**
	 * 还款计划id
	 */
	@Column(length = 32)
	private String planId;

	/**
	 * 还款类型 1:正常 2:减免
	 */
	@Column()
	private Integer paidType;
	
	/**
	 * 是否复核
	 */
	@Column(name = "isReview")
	@org.hibernate.annotations.Type(type = "true_false")
	private Boolean isReview=false;
	
	// ---------------------------还款计划--------------------------------
	/**
	 * 期数
	 */
	@Column()
	private Integer periods;
	
	/**
	 * 还款日期
	 */
	private Long planRepayDate;

	/**
	 * 当前还款日期
	 */
	@Column()
	private Long paidRepayDate;
	
	/**
	 * 逾期天数
	 */
	@Column
	private Integer ovreDueDay=0;

	/**
	 * 当期本金
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal planPrincipalAmount = BigDecimal.ZERO;

	/**
	 * 当期利息
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal planInterestAmount = BigDecimal.ZERO;

	/**
	 * 当期服务费
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal planServiceFee = BigDecimal.ZERO;

	/**
	 * 当期剩余本金
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal remainPrincipal = BigDecimal.ZERO;

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
	 * 违约金
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal planDamages = BigDecimal.ZERO;

	// -----------------------------待还---------------------------------

	/**
	 * 当期本金
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal dplanPrincipalAmount = BigDecimal.ZERO;

	/**
	 * 当期利息
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal dplanInterestAmount = BigDecimal.ZERO;

	/**
	 * 当期服务费
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal dplanServiceFee = BigDecimal.ZERO;

	/**
	 * 罚息
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal dplanPenalty = BigDecimal.ZERO;

	/**
	 * 当期罚息
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal dcurrentPlanPenalty = BigDecimal.ZERO;

	/**
	 * 违约金
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal dplanDamages = BigDecimal.ZERO;

	// -----------------------------本次实收(可操作)---------------------------
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

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public String getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public Integer getPeriods() {
		return periods;
	}

	public void setPeriods(Integer periods) {
		this.periods = periods;
	}

	public Boolean getIsReview() {
		return isReview;
	}

	public void setIsReview(Boolean isReview) {
		this.isReview = isReview;
	}

	public Integer getPaidType() {
		return paidType;
	}

	public void setPaidType(Integer paidType) {
		this.paidType = paidType;
	}

	public Long getPaidRepayDate() {
		return paidRepayDate;
	}

	public void setPaidRepayDate(Long paidRepayDate) {
		this.paidRepayDate = paidRepayDate;
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

	public BigDecimal getRemainPrincipal() {
		return remainPrincipal;
	}

	public void setRemainPrincipal(BigDecimal remainPrincipal) {
		this.remainPrincipal = remainPrincipal;
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

	public BigDecimal getPlanDamages() {
		return planDamages;
	}

	public void setPlanDamages(BigDecimal planDamages) {
		this.planDamages = planDamages;
	}

	public BigDecimal getDplanPrincipalAmount() {
		return dplanPrincipalAmount;
	}

	public void setDplanPrincipalAmount(BigDecimal dplanPrincipalAmount) {
		this.dplanPrincipalAmount = dplanPrincipalAmount;
	}

	public BigDecimal getDplanInterestAmount() {
		return dplanInterestAmount;
	}

	public void setDplanInterestAmount(BigDecimal dplanInterestAmount) {
		this.dplanInterestAmount = dplanInterestAmount;
	}

	public BigDecimal getDplanServiceFee() {
		return dplanServiceFee;
	}

	public void setDplanServiceFee(BigDecimal dplanServiceFee) {
		this.dplanServiceFee = dplanServiceFee;
	}

	public BigDecimal getDplanPenalty() {
		return dplanPenalty;
	}

	public void setDplanPenalty(BigDecimal dplanPenalty) {
		this.dplanPenalty = dplanPenalty;
	}

	public BigDecimal getDcurrentPlanPenalty() {
		return dcurrentPlanPenalty;
	}

	public void setDcurrentPlanPenalty(BigDecimal dcurrentPlanPenalty) {
		this.dcurrentPlanPenalty = dcurrentPlanPenalty;
	}

	public BigDecimal getDplanDamages() {
		return dplanDamages;
	}

	public void setDplanDamages(BigDecimal dplanDamages) {
		this.dplanDamages = dplanDamages;
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

	public Long getPlanRepayDate() {
		return planRepayDate;
	}

	public void setPlanRepayDate(Long planRepayDate) {
		this.planRepayDate = planRepayDate;
	}

	public void setPaidDamages(BigDecimal paidDamages) {
		this.paidDamages = paidDamages;
	}

	public Integer getOvreDueDay() {
		return ovreDueDay;
	}

	public void setOvreDueDay(Integer ovreDueDay) {
		this.ovreDueDay = ovreDueDay;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
