package com.zdsoft.finance.spi.receivablePlan;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CaseReceivableDto.java
 * @ClassName: CaseReceivableDto
 * @Description: 案件应还Dto
 * @author jincheng
 * @date 2017年2月17日 下午2:54:26
 * @version V1.0
 */
public class CaseReceivableDto implements Serializable {

	private static final long serialVersionUID = 1L;

	// --------------------------------------------当期还款计划-----------------------------------------------
	
	/**
	 * 还款计划id
	 */
	private String planId;

	/**
	 * 还款日期
	 */
	private Long planRepayDate;
	
	/**
	 * 当前开始日期
	 */
	private Long startDate;

	/**
	 * 当前结束日期
	 */
	private Long endDate;

	/**
	 * 期数
	 */
	private Integer periods;

	/**
	 * 还款方式
	 */
	private String repayMethod;
	
	  /**
     * 逾期利率
     */
    private BigDecimal overdueRate=BigDecimal.ZERO;
    
    /**
     * 逾期利率单位
     */
    private String overdueRateUnit;
    
	
	/**
	 * 是否逾期
	 */
	private Boolean isOverDue=false;
	
	/**
	 * 当期是否还清
	 */
	private Boolean settlement=false;
	
	/**
	 * 逾期天数
	 */
	private Integer days=0;

	/**
	 * 案件余额
	 */
	private BigDecimal caseApplyBalance = BigDecimal.ZERO;

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

	/**
	 * 罚息
	 */

	private BigDecimal planPenalty = BigDecimal.ZERO;

	/**
	 * 当期罚息
	 */

	private BigDecimal currentPlanPenalty = BigDecimal.ZERO;

	/**
	 * 违约金
	 */

	private BigDecimal planDamages = BigDecimal.ZERO;

	// -----------------------------待还---------------------------------

	/**
	 * 当期本金
	 */

	private BigDecimal dplanPrincipalAmount = BigDecimal.ZERO;

	/**
	 * 当期利息
	 */

	private BigDecimal dplanInterestAmount = BigDecimal.ZERO;

	/**
	 * 当期服务费
	 */

	private BigDecimal dplanServiceFee = BigDecimal.ZERO;

	/**
	 * 罚息
	 */

	private BigDecimal dplanPenalty = BigDecimal.ZERO;

	/**
	 * 当期罚息
	 */

	private BigDecimal dcurrentPlanPenalty = BigDecimal.ZERO;

	/**
	 * 违约金
	 */

	private BigDecimal dplanDamages = BigDecimal.ZERO;

	// -----------------------------本次实收(可操作)---------------------------
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
	 * 罚息
	 */

	private BigDecimal paidPenalty = BigDecimal.ZERO;

	/**
	 * 当期罚息
	 */

	private BigDecimal currentPaidPenalty = BigDecimal.ZERO;

	/**
	 * 违约金
	 */
	private BigDecimal paidDamages = BigDecimal.ZERO;
	
	
	/**
	 * 实付本金
	 */
	private BigDecimal paidTotalPrincipalAmount = BigDecimal.ZERO;
	
	/**
	 * 实付利息
	 */
	private BigDecimal paidTotalInterestAmount = BigDecimal.ZERO;
	
	/**
	 * 实付服务费
	 */
	private BigDecimal paidTotalServiceFee = BigDecimal.ZERO;
	
	/**
	 * 实付罚息
	 */
	private BigDecimal paidTotalPenalty = BigDecimal.ZERO;
	
	/**
	 * 实付当前罚息
	 */
	private BigDecimal currentPaidTotalPenalty = BigDecimal.ZERO;
	
	/**
	 * 实付违约金
	 */
	private BigDecimal paidTotalDamages = BigDecimal.ZERO;

	/**
	 * 减免当期罚息
	 */
	private BigDecimal currentReductionPenalty = BigDecimal.ZERO;

	/**
	 * 减免罚息
	 */
	private BigDecimal reductionPenalty = BigDecimal.ZERO;

	/**
	 * 减免违约金
	 */
	private BigDecimal reductionDamages = BigDecimal.ZERO;
	
	/**
	 * 本息结清日
	 */
	private Long  piSettlementDate;

	public Long getPlanRepayDate() {
		return planRepayDate;
	}

	public void setPlanRepayDate(Long planRepayDate) {
		this.planRepayDate = planRepayDate;
	}

	public Integer getPeriods() {
		return periods;
	}

	public void setPeriods(Integer periods) {
		this.periods = periods;
	}

	public String getRepayMethod() {
		return repayMethod;
	}

	public void setRepayMethod(String repayMethod) {
		this.repayMethod = repayMethod;
	}

	public BigDecimal getCaseApplyBalance() {
		return caseApplyBalance;
	}

	public void setCaseApplyBalance(BigDecimal caseApplyBalance) {
		this.caseApplyBalance = caseApplyBalance;
	}

	public BigDecimal getPlanPrincipalAmount() {
		return planPrincipalAmount;
	}

	public void setPlanPrincipalAmount(BigDecimal planPrincipalAmount) {
		this.planPrincipalAmount = planPrincipalAmount;
	}

	public BigDecimal getOverdueRate() {
		return overdueRate;
	}

	public void setOverdueRate(BigDecimal overdueRate) {
		this.overdueRate = overdueRate;
	}

	public String getOverdueRateUnit() {
		return overdueRateUnit;
	}

	public void setOverdueRateUnit(String overdueRateUnit) {
		this.overdueRateUnit = overdueRateUnit;
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

	public Boolean getIsOverDue() {
		return isOverDue;
	}

	public void setIsOverDue(Boolean isOverDue) {
		this.isOverDue = isOverDue;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
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

	public Boolean getSettlement() {
		return settlement;
	}

	public void setSettlement(Boolean settlement) {
		this.settlement = settlement;
	}

	public BigDecimal getReductionDamages() {
		return reductionDamages;
	}

	public void setReductionDamages(BigDecimal reductionDamages) {
		this.reductionDamages = reductionDamages;
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

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public BigDecimal getPaidTotalPrincipalAmount() {
		return paidTotalPrincipalAmount;
	}

	public void setPaidTotalPrincipalAmount(BigDecimal paidTotalPrincipalAmount) {
		this.paidTotalPrincipalAmount = paidTotalPrincipalAmount;
	}

	public BigDecimal getPaidTotalInterestAmount() {
		return paidTotalInterestAmount;
	}

	public void setPaidTotalInterestAmount(BigDecimal paidTotalInterestAmount) {
		this.paidTotalInterestAmount = paidTotalInterestAmount;
	}

	public BigDecimal getPaidTotalServiceFee() {
		return paidTotalServiceFee;
	}

	public void setPaidTotalServiceFee(BigDecimal paidTotalServiceFee) {
		this.paidTotalServiceFee = paidTotalServiceFee;
	}

	public BigDecimal getPaidTotalPenalty() {
		return paidTotalPenalty;
	}

	public void setPaidTotalPenalty(BigDecimal paidTotalPenalty) {
		this.paidTotalPenalty = paidTotalPenalty;
	}

	public BigDecimal getCurrentPaidTotalPenalty() {
		return currentPaidTotalPenalty;
	}

	public void setCurrentPaidTotalPenalty(BigDecimal currentPaidTotalPenalty) {
		this.currentPaidTotalPenalty = currentPaidTotalPenalty;
	}

	public BigDecimal getPaidTotalDamages() {
		return paidTotalDamages;
	}

	public void setPaidTotalDamages(BigDecimal paidTotalDamages) {
		this.paidTotalDamages = paidTotalDamages;
	}

	public Long getPiSettlementDate() {
		return piSettlementDate;
	}

	public void setPiSettlementDate(Long piSettlementDate) {
		this.piSettlementDate = piSettlementDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}