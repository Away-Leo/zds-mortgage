package com.zdsoft.finance.casemanage.receivablePlan.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
* 版权�?有：重庆正大华日软件有限公司
* @Title:RepayPlanDto.java
* @Package:com.zdsoft.guarantee.dto.plan
* @Description:
* @author:jincheng
* @date:2016�?5�?26�? 下午2:36:33
* @version: V1.0
 */
public class RepayPlan implements Serializable {
	
	private static final long serialVersionUID = 7604828140818154114L;

	/**
	 * 开始日期
	 */
	private Long startDt;

	/**
	 * 结束日期
	 */
	private Long endDt;

	/**
	 * 资金类型
	 */
	private Integer moneyType;
	/**
	 * 计划到期日期
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
	 * 计划金额
	 */
	private BigDecimal planAmount;
	/**
	 * 罚息
	 */
	private BigDecimal penalty;
	/**
	 * 认定金额
	 */
	private BigDecimal affirmAmount;
	/**
	 * 认定罚息
	 */
	private BigDecimal affirmPenalty;
	/**
	 *  结息日期
	 */
	private Long affirmDt;
	/**
	 * 还款计划id
	 */
	private String loanReceivableBillId;
	
	/**
     * 剩余本金
     */
    private BigDecimal residualprincipal;
	
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

	public Integer getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(Integer moneyType) {
		this.moneyType = moneyType;
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

	public BigDecimal getPlanAmount() {
		return planAmount;
	}

	public void setPlanAmount(BigDecimal planAmount) {
		this.planAmount = planAmount;
	}

	public BigDecimal getPenalty() {
		return penalty;
	}

	public void setPenalty(BigDecimal penalty) {
		this.penalty = penalty;
	}

	public BigDecimal getAffirmAmount() {
		return affirmAmount;
	}

	public void setAffirmAmount(BigDecimal affirmAmount) {
		this.affirmAmount = affirmAmount;
	}

	public BigDecimal getAffirmPenalty() {
		return affirmPenalty;
	}

	public void setAffirmPenalty(BigDecimal affirmPenalty) {
		this.affirmPenalty = affirmPenalty;
	}

	public Long getAffirmDt() {
		return affirmDt;
	}

	public void setAffirmDt(Long affirmDt) {
		this.affirmDt = affirmDt;
	}


	public String getLoanReceivableBillId() {
        return loanReceivableBillId;
    }

    public void setLoanReceivableBillId(String loanReceivableBillId) {
        this.loanReceivableBillId = loanReceivableBillId;
    }

    public static long getSerialversionuid() {
		return serialVersionUID;
	}

    public BigDecimal getResidualprincipal() {
        return residualprincipal;
    }

    public void setResidualprincipal(BigDecimal residualprincipal) {
        this.residualprincipal = residualprincipal;
    }

}
