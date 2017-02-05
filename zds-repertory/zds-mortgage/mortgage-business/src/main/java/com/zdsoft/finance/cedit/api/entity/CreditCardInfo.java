package com.zdsoft.finance.cedit.api.entity;

//	@XStreamAlias("CreditCardInfo") // 贷记卡信息
public class CreditCardInfo {
	//	@XStreamAlias("TitleInfo")
	private String TitleInfo;
	
	//	@XStreamAlias("AccountStatus")
	private String AccountStatus;
	
	//	@XStreamAlias("SharedCreditLimit")
	private String sharedCreditLimit;
	
	//	@XStreamAlias("Last6MonthsAve")
	private String last6MonthsAve;
	
	//	@XStreamAlias("UsedCreditLimit")
	private String UsedCreditLimit;
	//	@XStreamAlias("Last6MonthsAveUsedCreditLimit")
	private String last6MonthsAveUsedCreditLimit;
	
	//	@XStreamAlias("MaxUsedCreditLimit")
	private String MaxUsedCreditLimit;
	
	//	@XStreamAlias("ThisMonthRepayAmount")
	private String ThisMonthRepayAmount;
	
	//	@XStreamAlias("BillingDate")
	private String BillingDate;
	
	//	@XStreamAlias("ThisMonthActualRepayAmount")
	private String ThisMonthActualRepayAmount;

	//	@XStreamAlias("TheLastestRepayDay")
	private String TheLastestRepayDay;
	
	//	@XStreamAlias("CurrentOverdueNum")
	private String CurrentOverdueNum;
	
	//	@XStreamAlias("CurrentOverdueAmount")
	private String CurrentOverdueAmount;
	
	//	@XStreamAlias("Month24RepayTitle")
	private String Month24RepayTitle;
	
	//	@XStreamAlias("Month24RepayStatus")
	private String Month24RepayStatus;

	//	@XStreamAlias("OverdueTile")
	private String OverdueTile;
	

//	@XStreamImplicit(itemFieldName="Overdue")
//	private List<OverDue> overDueList;
	
//	//	@XStreamAlias("OverdueMonth1")
//	private String OverdueMonth1;
//	
//	//	@XStreamAlias("OverdueMonthNum1")
//	private String OverdueMonthNum1;
//	
//	//	@XStreamAlias("OverdueAmount1")
//	private String overdueAmount1;
//
//	//	@XStreamAlias("OverdueMonth2")
//	private String OverdueMonth2;
//	
//	//	@XStreamAlias("OverdueMonthNum2")
//	private String OverdueMonthNum2;
//	
//	//	@XStreamAlias("OverdueAmount2")
//	private String OverdueAmount2;

	
	
	public String getTitleInfo() {
		return TitleInfo;
	}

	public void setTitleInfo(String titleInfo) {
		TitleInfo = titleInfo;
	}

	public String getAccountStatus() {
		return AccountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		AccountStatus = accountStatus;
	}

	public String getUsedCreditLimit() {
		return UsedCreditLimit;
	}

	public void setUsedCreditLimit(String usedCreditLimit) {
		UsedCreditLimit = usedCreditLimit;
	}

	public String getLast6MonthsAveUsedCreditLimit() {
		return last6MonthsAveUsedCreditLimit;
	}

	public void setLast6MonthsAveUsedCreditLimit(
			String last6MonthsAveUsedCreditLimit) {
		this.last6MonthsAveUsedCreditLimit = last6MonthsAveUsedCreditLimit;
	}

	public String getMaxUsedCreditLimit() {
		return MaxUsedCreditLimit;
	}

	public void setMaxUsedCreditLimit(String maxUsedCreditLimit) {
		MaxUsedCreditLimit = maxUsedCreditLimit;
	}

	public String getThisMonthRepayAmount() {
		return ThisMonthRepayAmount;
	}

	public void setThisMonthRepayAmount(String thisMonthRepayAmount) {
		ThisMonthRepayAmount = thisMonthRepayAmount;
	}

	public String getBillingDate() {
		return BillingDate;
	}

	public void setBillingDate(String billingDate) {
		BillingDate = billingDate;
	}

	public String getThisMonthActualRepayAmount() {
		return ThisMonthActualRepayAmount;
	}

	public void setThisMonthActualRepayAmount(String thisMonthActualRepayAmount) {
		ThisMonthActualRepayAmount = thisMonthActualRepayAmount;
	}

	public String getTheLastestRepayDay() {
		return TheLastestRepayDay;
	}

	public void setTheLastestRepayDay(String theLastestRepayDay) {
		TheLastestRepayDay = theLastestRepayDay;
	}

	public String getCurrentOverdueNum() {
		return CurrentOverdueNum;
	}

	public void setCurrentOverdueNum(String currentOverdueNum) {
		CurrentOverdueNum = currentOverdueNum;
	}

	public String getCurrentOverdueAmount() {
		return CurrentOverdueAmount;
	}

	public void setCurrentOverdueAmount(String currentOverdueAmount) {
		CurrentOverdueAmount = currentOverdueAmount;
	}

	public String getMonth24RepayTitle() {
		return Month24RepayTitle;
	}

	public void setMonth24RepayTitle(String month24RepayTitle) {
		Month24RepayTitle = month24RepayTitle;
	}

	public String getMonth24RepayStatus() {
		return Month24RepayStatus;
	}

	public void setMonth24RepayStatus(String month24RepayStatus) {
		Month24RepayStatus = month24RepayStatus;
	}

	public String getSharedCreditLimit() {
		return sharedCreditLimit;
	}

	public void setSharedCreditLimit(String sharedCreditLimit) {
		this.sharedCreditLimit = sharedCreditLimit;
	}

	public String getLast6MonthsAve() {
		return last6MonthsAve;
	}

	public void setLast6MonthsAve(String last6MonthsAve) {
		this.last6MonthsAve = last6MonthsAve;
	}

	public String getOverdueTile() {
		return OverdueTile;
	}

	public void setOverdueTile(String overdueTile) {
		OverdueTile = overdueTile;
	}

//	public List<OverDue> getOverDueList() {
//		return overDueList;
//	}
//
//	public void setOverDueList(List<OverDue> overDueList) {
//		this.overDueList = overDueList;
//	}

	


   
	
	
	
	
	
	
}
