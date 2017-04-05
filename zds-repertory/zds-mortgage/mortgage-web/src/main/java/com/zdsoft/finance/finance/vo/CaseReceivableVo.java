package com.zdsoft.finance.finance.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CaseReceivableVo.java
 * @ClassName: CaseReceivableVo
 * @Description: 案件应还Vo
 * @author jincheng
 * @date 2017年2月17日 下午2:54:26
 * @version V1.0
 */
public class CaseReceivableVo implements Serializable {

	private static final long serialVersionUID = 1L;

	// --------------------------------------------当期还款计划-----------------------------------------------
	
	/**
	 * 还款日期
	 */
	private Long planRepayDate;

	/**
	 * 当期本金
	 */
	private BigDecimal planPrincipalAmount = BigDecimal.ZERO;

	/**
	 * 当期利息
	 */
	private BigDecimal planInterestAmount = BigDecimal.ZERO;

	/**
	 * 当期服务费
	 */
	private BigDecimal planServiceFee = BigDecimal.ZERO;

	/**
	 * 当期剩余本金
	 */
	private BigDecimal remainPrincipal = BigDecimal.ZERO;

	// --------------------------------------------已还与待还-----------------------------------------------

	/**
	 * 当期本金
	 */
	private BigDecimal paidPrincipalAmount = BigDecimal.ZERO;

	/**
	 * 当期利息
	 */
	private BigDecimal paidInterestAmount = BigDecimal.ZERO;

	/**
	 * 当期服务费
	 */
	private BigDecimal paidServiceFee = BigDecimal.ZERO;

	/**
	 * 结清本息日期
	 */
	private Long paidOverDate;

	/**
	 * 待还
	 */
	private BigDecimal remainAmount = BigDecimal.ZERO;

	// ----------------------------------------------罚息Penalty--------------------------------------
	/**
	 * 逾期罚息
	 */
	private BigDecimal planPenalty = BigDecimal.ZERO;

	/**
	 * 减免
	 */
	private BigDecimal reductionPenalty = BigDecimal.ZERO;

	/**
	 * 实收填充Real fill
	 */
	private BigDecimal realFillPenalty = BigDecimal.ZERO;

	/**
	 * 罚息未收
	 */
	private BigDecimal noPayPenalty = BigDecimal.ZERO;

	// ------------------------------------------------当期罚息参考---------------------------------------------------
	/**
	 * 当期罚息
	 */
	private BigDecimal currentPenalty = BigDecimal.ZERO;

	/**
	 * 实收填充
	 */
	private BigDecimal currentRealFillPenalty = BigDecimal.ZERO;

	/**
	 * 当期未收
	 */
	private BigDecimal currentNoPayPenalty = BigDecimal.ZERO;

	// ----------------------------------------------违约金--------------------------------------

	/**
	 * 应收
	 */
	private BigDecimal planDamages = BigDecimal.ZERO;

	/**
	 * 减免
	 */
	private BigDecimal reductionDamages = BigDecimal.ZERO;

	/**
	 * 已收
	 */
	private BigDecimal paidDamages = BigDecimal.ZERO;

	public Long getPlanRepayDate() {
		return planRepayDate;
	}

	public void setPlanRepayDate(Long planRepayDate) {
		this.planRepayDate = planRepayDate;
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

	public Long getPaidOverDate() {
		return paidOverDate;
	}

	public void setPaidOverDate(Long paidOverDate) {
		this.paidOverDate = paidOverDate;
	}

	public BigDecimal getRemainAmount() {
		return remainAmount;
	}

	public void setRemainAmount(BigDecimal remainAmount) {
		this.remainAmount = remainAmount;
	}

	public BigDecimal getPlanPenalty() {
		return planPenalty;
	}

	public void setPlanPenalty(BigDecimal planPenalty) {
		this.planPenalty = planPenalty;
	}

	public BigDecimal getReductionPenalty() {
		return reductionPenalty;
	}

	public void setReductionPenalty(BigDecimal reductionPenalty) {
		this.reductionPenalty = reductionPenalty;
	}

	public BigDecimal getRealFillPenalty() {
		return realFillPenalty;
	}

	public void setRealFillPenalty(BigDecimal realFillPenalty) {
		this.realFillPenalty = realFillPenalty;
	}

	public BigDecimal getNoPayPenalty() {
		return noPayPenalty;
	}

	public void setNoPayPenalty(BigDecimal noPayPenalty) {
		this.noPayPenalty = noPayPenalty;
	}

	public BigDecimal getCurrentPenalty() {
		return currentPenalty;
	}

	public void setCurrentPenalty(BigDecimal currentPenalty) {
		this.currentPenalty = currentPenalty;
	}

	public BigDecimal getCurrentRealFillPenalty() {
		return currentRealFillPenalty;
	}

	public void setCurrentRealFillPenalty(BigDecimal currentRealFillPenalty) {
		this.currentRealFillPenalty = currentRealFillPenalty;
	}

	public BigDecimal getCurrentNoPayPenalty() {
		return currentNoPayPenalty;
	}

	public void setCurrentNoPayPenalty(BigDecimal currentNoPayPenalty) {
		this.currentNoPayPenalty = currentNoPayPenalty;
	}

	public BigDecimal getPlanDamages() {
		return planDamages;
	}

	public void setPlanDamages(BigDecimal planDamages) {
		this.planDamages = planDamages;
	}

	public BigDecimal getReductionDamages() {
		return reductionDamages;
	}

	public void setReductionDamages(BigDecimal reductionDamages) {
		this.reductionDamages = reductionDamages;
	}

	public BigDecimal getPaidDamages() {
		return paidDamages;
	}

	public void setPaidDamages(BigDecimal paidDamages) {
		this.paidDamages = paidDamages;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
