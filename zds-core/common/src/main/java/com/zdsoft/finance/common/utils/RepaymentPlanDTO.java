package com.zdsoft.finance.common.utils;

/**
 * 还款计划
 * @author pan
 *
 */
public class RepaymentPlanDTO {

	//计划ID
	private String planID;
	//订单编号
	private String billID;
	//资金类型	
	private Long moneyType;
	//计划到期日	
	private Long planDueDt;
	//天数	
	private int days;
	//期数	
	private int periodsNo;
	//计划金额	
	private double planAmount = 0d;
	//备注说明	
	private String mo;
	
	
	public String getBillID() {
		return billID;
	}
	
	public String getPlanID() {
		return planID;
	}

	public void setPlanID(String planID) {
		this.planID = planID;
	}

	public void setBillID(String billID) {
		this.billID = billID;
	}
	
	public Long getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(Long moneyType) {
		this.moneyType = moneyType;
	}

	public Long getPlanDueDt() {
		return planDueDt;
	}

	public void setPlanDueDt(Long planDueDt) {
		this.planDueDt = planDueDt;
	}

	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public int getPeriodsNo() {
		return periodsNo;
	}
	public void setPeriodsNo(int periodsNo) {
		this.periodsNo = periodsNo;
	}
	public double getPlanAmount() {
		return planAmount;
	}
	public void setPlanAmount(double planAmount) {
		this.planAmount = planAmount;
	}
	public String getMo() {
		return mo;
	}
	public void setMo(String mo) {
		this.mo = mo;
	}
	
	
}
