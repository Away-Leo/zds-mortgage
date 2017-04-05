package com.zdsoft.finance.casemanage.receivablePlan.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: RepayPlan.java
 * @ClassName: RepayPlan
 * @Description: 还款计划
 * @author jincheng
 * @date 2017年2月13日 下午4:54:32
 * @version V1.0
 */
public class RepayPlan implements Serializable {

	private static final long serialVersionUID = 7604828140818154114L;

	/**
	 * 开始日期
	 */
	private Long startDate;

	/**
	 * 结束日期
	 */
	private Long endDate;

	/**
	 * 计划到期日期
	 */
	private Long planRepayDate;

	/**
	 * 天数
	 */
	private Integer days;

	/**
	 * 期数
	 */
	private Integer periods;

	/**
	 * 计划本金
	 */
	private BigDecimal planPrincipalAmount = BigDecimal.ZERO;

	/**
	 * 计划利息
	 */
	private BigDecimal planInterestAmount = BigDecimal.ZERO;

	/**
	 * 服务费
	 */
	private BigDecimal planServiceFee = BigDecimal.ZERO;
	
	/**
	 * 还款总额
	 */
	private BigDecimal planTotalAmount = BigDecimal.ZERO;

	/**
	 * 当期剩余本金
	 */
	private BigDecimal remainPrincipal = BigDecimal.ZERO;

	/**
	 * 所属机构
	 */
	private String orgId;
	
	public RepayPlan(){
		
	}

	/**
	 * @param startDate  当期开始日期
	 * @param endDate  当期结束日期
	 * @param planRepayDate 当期还款日期
	 * @param days 当期天数
	 * @param periods 当期 期数
	 * @param planPrincipalAmount 当期本金
	 * @param planInterestAmount 当期利息
	 * @param planServiceFee 当期服务费
	 * @param remainPrincipal 当期剩余本金
	 * @param orgId 所属机构
	 */
	public RepayPlan(Long startDate, Long endDate, Long planRepayDate, Integer days, Integer periods,
			BigDecimal planPrincipalAmount, BigDecimal planInterestAmount, BigDecimal planServiceFee,
			BigDecimal remainPrincipal, String orgId) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.planRepayDate = planRepayDate;
		this.days = days;
		this.periods = periods;
		this.planPrincipalAmount = planPrincipalAmount;
		this.planInterestAmount = planInterestAmount;
		this.planServiceFee = planServiceFee;
		this.remainPrincipal = remainPrincipal;
		this.planTotalAmount=planPrincipalAmount.add(planInterestAmount).add(planServiceFee);
		this.orgId = orgId;
	}

	public Long getStartDate() {
		return startDate;
	}

	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	public Long getPlanRepayDate() {
		return planRepayDate;
	}

	public void setPlanRepayDate(Long planRepayDate) {
		this.planRepayDate = planRepayDate;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Integer getPeriods() {
		return periods;
	}

	public void setPeriods(Integer periods) {
		this.periods = periods;
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

	public BigDecimal getPlanTotalAmount() {
		return planTotalAmount;
	}

	public void setPlanTotalAmount(BigDecimal planTotalAmount) {
		this.planTotalAmount = planTotalAmount;
	}

	public BigDecimal getRemainPrincipal() {
		return remainPrincipal;
	}

	public void setRemainPrincipal(BigDecimal remainPrincipal) {
		this.remainPrincipal = remainPrincipal;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
