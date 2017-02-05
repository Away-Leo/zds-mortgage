package com.zdsoft.finance.cedit.api.entity;

//	@XStreamAlias("QuasiCreditCard")
public class QuasiCreditCard {
	//	@XStreamAlias("TitleInfo")
	private String titleInfo;

	
	//	@XStreamAlias("AccountStatus") //账户状态
	private String accountStatus;
	
	//	@XStreamAlias("SharedCreditLimit") //共享额度
	private String sharedCreditLimit;
	 
	//	@XStreamAlias("OverdraftBalance") //透支余额
	private String overdraftBalance;
	
	//	@XStreamAlias("Last6MonthsAveOverBalan") //最近 6个月平均透支余额
	private String last6MonthsAveOverBalan;
	
	//	@XStreamAlias("MaxOverBalan") //最大透支余额
	private String maxOverBalan;
	
	//	@XStreamAlias("BillingDate")// 账单日
	private String billingDate;
	

	
	//	@XStreamAlias("TheLastestRepayDay") //最近一次还款日期
	private String theLastestRepayDay;
	
	//	@XStreamAlias("OverOver180UnpaidBalan")  //透支 180 天以上未付余额
	private String overOver180UnpaidBalan;
	
	//	@XStreamAlias("Month24RepayTitle") //24 个月（账户）还款记录前面的信 息
	private String month24RepayTitle ;
	
	//	@XStreamAlias("Month24RepayStatus") //24 个月（账户）还款记录状态
	private String month24RepayStatus;
	
	//	@XStreamAlias("ThisMonthActualRepayAmount")
	private String thisMonthActualRepayAmount;
	
	//	@XStreamAlias("OverdueTile") //逾期的前面的信息（和贷记卡一样可 能有
	private String overdueTile;
	
	
//	@XStreamImplicit(itemFieldName="Overdue")
//    private List<OverDue> overDueList;	
	
	

	public String getTitleInfo() {
		return titleInfo;
	}

	public void setTitleInfo(String titleInfo) {
		this.titleInfo = titleInfo;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getSharedCreditLimit() {
		return sharedCreditLimit;
	}

	public void setSharedCreditLimit(String sharedCreditLimit) {
		this.sharedCreditLimit = sharedCreditLimit;
	}

	public String getOverdraftBalance() {
		return overdraftBalance;
	}

	public void setOverdraftBalance(String overdraftBalance) {
		this.overdraftBalance = overdraftBalance;
	}

	public String getLast6MonthsAveOverBalan() {
		return last6MonthsAveOverBalan;
	}

	public void setLast6MonthsAveOverBalan(String last6MonthsAveOverBalan) {
		this.last6MonthsAveOverBalan = last6MonthsAveOverBalan;
	}

	public String getMaxOverBalan() {
		return maxOverBalan;
	}

	public void setMaxOverBalan(String maxOverBalan) {
		this.maxOverBalan = maxOverBalan;
	}

	public String getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(String billingDate) {
		this.billingDate = billingDate;
	}

	

	public String getTheLastestRepayDay() {
		return theLastestRepayDay;
	}

	public void setTheLastestRepayDay(String theLastestRepayDay) {
		this.theLastestRepayDay = theLastestRepayDay;
	}

	public String getOverOver180UnpaidBalan() {
		return overOver180UnpaidBalan;
	}

	public void setOverOver180UnpaidBalan(String overOver180UnpaidBalan) {
		this.overOver180UnpaidBalan = overOver180UnpaidBalan;
	}

	public String getMonth24RepayTitle() {
		return month24RepayTitle;
	}

	public void setMonth24RepayTitle(String month24RepayTitle) {
		this.month24RepayTitle = month24RepayTitle;
	}

	public String getMonth24RepayStatus() {
		return month24RepayStatus;
	}

	public void setMonth24RepayStatus(String month24RepayStatus) {
		this.month24RepayStatus = month24RepayStatus;
	}

	public String getOverdueTile() {
		return overdueTile;
	}

	public void setOverdueTile(String overdueTile) {
		this.overdueTile = overdueTile;
	}

    

//	public List<OverDue> getOverDueList() {
//		return overDueList;
//	}
//
//	public void setOverDueList(List<OverDue> overDueList) {
//		this.overDueList = overDueList;
//	}

	public String getThisMonthActualRepayAmount() {
		return thisMonthActualRepayAmount;
	}

	public void setThisMonthActualRepayAmount(String thisMonthActualRepayAmount) {
		this.thisMonthActualRepayAmount = thisMonthActualRepayAmount;
	}
	
	
	
}
