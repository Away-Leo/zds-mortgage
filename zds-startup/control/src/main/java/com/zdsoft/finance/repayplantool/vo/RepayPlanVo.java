package com.zdsoft.finance.repayplantool.vo;

import java.math.BigDecimal;

public class RepayPlanVo {

	/**
	 * 开始日期
	 */
	private Long startDt;

	/**
	 * 结束日期
	 */
	private Long endDt;

	/**
	 * 计划到期日
	 */
	private Long planDueDt;
	/**
	 * 天数
	 */
	private Integer days;
	/**
	 * 期数
	 */
	private String periodsNo;
	/**
	 * 计划本金金额
	 */
	private BigDecimal planPrincipalAmount;
	
	/**
	 * 计划利息金额
	 */
	private BigDecimal planInterestAmount;
	
	/**
	 * 还款计划id
	 */
	private String loanReceivableBillId;
	
	/**
	 * 资金类型
	 */
	private String fundType;
	
	/**
	 * 计划金额
	 */
	private BigDecimal planAmount;
	
	/**
	 * 当前应收
	 */
	private BigDecimal currentAccounts;

	public Long getStartDt() {
		return startDt;
	}

	public void setStartDt(Long startDt) {
		this.startDt = startDt;
	}

	public Long getEndDt() {
		return endDt;
	}

	public void setEndDt(Long endDt) {
		this.endDt = endDt;
	}

	public Long getPlanDueDt() {
		return planDueDt;
	}

	public void setPlanDueDt(Long planDueDt) {
		this.planDueDt = planDueDt;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public String getPeriodsNo() {
		return periodsNo;
	}

	public void setPeriodsNo(String periodsNo) {
		this.periodsNo = periodsNo;
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

	public String getLoanReceivableBillId() {
		return loanReceivableBillId;
	}

	public void setLoanReceivableBillId(String loanReceivableBillId) {
		this.loanReceivableBillId = loanReceivableBillId;
	}

	public String getFundType() {
		return fundType;
	}

	public void setFundType(String fundType) {
		this.fundType = fundType;
	}

	public BigDecimal getCurrentAccounts() {
		return currentAccounts;
	}

	public void setCurrentAccounts(BigDecimal currentAccounts) {
		this.currentAccounts = currentAccounts;
	}

	public BigDecimal getPlanAmount() {
		return planAmount;
	}

	public void setPlanAmount(BigDecimal planAmount) {
		this.planAmount = planAmount;
	}

}
