package com.zdsoft.finance.cedit.api.entity;

//	@XStreamAlias("LoanInfo") // 贷款信息
public class LoanInfo {


	//	@XStreamAlias("TitleInfo") //标题信
	private String titleInfo;
	
	

	//	@XStreamAlias("AccountStatus") //账户状态 
	private String accountStatus;

	//	@XStreamAlias("Fiveclasscode") //五级分类
	private String fiveclasscode;

	//	@XStreamAlias("PrincipalAmount") //本金余额
	private String principalAmount;

	//	@XStreamAlias("RemainRepayNum") //剩余还款期数
	private String remainRepayNum;

	//	@XStreamAlias("ThisMonthRepayAmount") // 本月应还款金额
	private String ThisMonthRepayAmount;

	//	@XStreamAlias("ThisMonthRepayDay") /// 本月应还款日期
	private String thisMonthRepayDay; 

	//	@XStreamAlias("ThisMonthActualRepayAmount") //本月实际还款金额
	private String thisMonthActualRepayAmount;

	//	@XStreamAlias("TheLastestRepayDay") //最近一次还款日期
	private String theLastestRepayDay;

	//	@XStreamAlias("CurrentOverdueNum")  // 当前逾期期数
	private String currentOverdueNum;

	//	@XStreamAlias("CurrentOverdueAmount") //当前逾期金额
	private String currentOverdueAmount;

	//	@XStreamAlias("Overdue31To60Days") //逾期 31-60 天未还本金
	private String overdue31To60Days;

	//	@XStreamAlias("Overdue61To90Days")  // 逾期 61－90 天未还本金
	private String overdue61To90Days;

	//	@XStreamAlias("Overdue91To180Days") //逾期 91－180 天未还本金
	private String overdue91To180Days;

	//	@XStreamAlias("Overdue180Days") //逾期 180 天以上未还本金
	private String overdue180Days;

	//	@XStreamAlias("Month24RepayTitle") // 24 个月（账户）还款前面的信息
	private String month24RepayTitle;

	//	@XStreamAlias("Month24RepayStatus") //24 个月（账户）还款状态
	private String month24RepayStatus;
	

	
	
	//	@XStreamAlias("OverdueTitle1") //逾期标题1
	private String overdueTitle1;
	

	
	//	@XStreamAlias("OverdueTitle2") //逾期标题2
	private String overdueTitle2;

//TODO:未找到Overdue类
//	@XStreamImplicit(itemFieldName="Overdue")
//	private List<OverDue> overDueList;
	
//	@XStreamImplicit(itemFieldName="SpecTra")
//	private List<SpecTra> specTraList;
	
	
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

	public String getFiveclasscode() {
		return fiveclasscode;
	}

	public void setFiveclasscode(String fiveclasscode) {
		this.fiveclasscode = fiveclasscode;
	}

	public String getPrincipalAmount() {
		return principalAmount;
	}

	public void setPrincipalAmount(String principalAmount) {
		this.principalAmount = principalAmount;
	}

	public String getRemainRepayNum() {
		return remainRepayNum;
	}

	public void setRemainRepayNum(String remainRepayNum) {
		this.remainRepayNum = remainRepayNum;
	}

	public String getThisMonthRepayAmount() {
		return ThisMonthRepayAmount;
	}

	public void setThisMonthRepayAmount(String thisMonthRepayAmount) {
		ThisMonthRepayAmount = thisMonthRepayAmount;
	}

	public String getThisMonthRepayDay() {
		return thisMonthRepayDay;
	}

	public void setThisMonthRepayDay(String thisMonthRepayDay) {
		this.thisMonthRepayDay = thisMonthRepayDay;
	}

	public String getThisMonthActualRepayAmount() {
		return thisMonthActualRepayAmount;
	}

	public void setThisMonthActualRepayAmount(String thisMonthActualRepayAmount) {
		this.thisMonthActualRepayAmount = thisMonthActualRepayAmount;
	}

	public String getTheLastestRepayDay() {
		return theLastestRepayDay;
	}

	public void setTheLastestRepayDay(String theLastestRepayDay) {
		this.theLastestRepayDay = theLastestRepayDay;
	}

	public String getCurrentOverdueNum() {
		return currentOverdueNum;
	}

	public void setCurrentOverdueNum(String currentOverdueNum) {
		this.currentOverdueNum = currentOverdueNum;
	}

	public String getCurrentOverdueAmount() {
		return currentOverdueAmount;
	}

	public void setCurrentOverdueAmount(String currentOverdueAmount) {
		this.currentOverdueAmount = currentOverdueAmount;
	}

	public String getOverdue31To60Days() {
		return overdue31To60Days;
	}

	public void setOverdue31To60Days(String overdue31To60Days) {
		this.overdue31To60Days = overdue31To60Days;
	}

	public String getOverdue61To90Days() {
		return overdue61To90Days;
	}

	public void setOverdue61To90Days(String overdue61To90Days) {
		this.overdue61To90Days = overdue61To90Days;
	}

	public String getOverdue91To180Days() {
		return overdue91To180Days;
	}

	public void setOverdue91To180Days(String overdue91To180Days) {
		this.overdue91To180Days = overdue91To180Days;
	}

	public String getOverdue180Days() {
		return overdue180Days;
	}

	public void setOverdue180Days(String overdue180Days) {
		this.overdue180Days = overdue180Days;
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

	
	public String getOverdueTitle1() {
		return overdueTitle1;
	}

	public void setOverdueTitle1(String overdueTitle1) {
		this.overdueTitle1 = overdueTitle1;
	}


	public String getOverdueTitle2() {
		return overdueTitle2;
	}

	public void setOverdueTitle2(String overdueTitle2) {
		this.overdueTitle2 = overdueTitle2;
	}

	
//	public List<OverDue> getOverDueList() {
//		return overDueList;
//	}
//
//	public void setOverDueList(List<OverDue> overDueList) {
//		this.overDueList = overDueList;
//	}
//
//	public List<SpecTra> getSpecTraList() {
//		return specTraList;
//	}
//
//	public void setSpecTraList(List<SpecTra> specTraList) {
//		this.specTraList = specTraList;
//	}


	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
}
