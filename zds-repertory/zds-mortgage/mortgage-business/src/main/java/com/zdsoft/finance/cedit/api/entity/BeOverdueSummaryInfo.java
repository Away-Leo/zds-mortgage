package com.zdsoft.finance.cedit.api.entity;

//import com.thoughtworks.xstream.annotations.XStreamAlias;

//@XStreamAlias("beOverdueSummaryInfo")  // 违约信息
public class BeOverdueSummaryInfo {
//	2.5.2.8 n逾期（透支）信息汇总【BeOverdueSummaryInfo】 
//	其 它 LoanOverdueAccountNum 贷款逾期，账户数 S N  
//	LoanOverdueMonthNum 贷款逾期，月份数 S N  
//	LoanOverdueHighestSingleMothOverdueAmount 	贷款逾期，单月最高逾期总 额 	S N  
//	LoanOverdueLongOverdueMonths 贷款逾期，最长逾期月数 S N 
//	CreditCardOverdueAccountNum 贷记卡逾期，账户数 S N  
//	CreditCardOverdueMothNum 贷记卡逾期，月份数 S N  
//	CreditCardOverdueMaxSingleMothOverdueAmount 	贷记卡逾期，单月最高逾期 总额 	S N  
//	CreditCardOverdraftLongOverdueMonths 贷记卡逾期，最长逾期月数 S N  
	
	
//	QuasiCreditCardOverdraftAccountNum 	准贷记卡 60 天以上透支， 账户数 	S N  
//	QuasiCreditCardOverdraft0DaysMothNum 	准贷记卡 60 天以上透支， 月份数 	S N  
//	QuasiCreditCardMaxSingleMothOverdueAmount 	准贷记卡 60 天以上透支， 单月最高逾期总额 	S N  
//	QuasiCreditCardOverdueLongOverdueMonths 	准贷记卡 60 天以上透支， 最长逾期月数 	S N   
	
	
//	@XStreamAlias("LoanOverdueAccountNum")
	private String loanOverdueAccountNum;
	
//	@XStreamAlias("LoanOverdueMonthNum")
	private String loanOverdueMonthNum;
	
//	@XStreamAlias("LoanOverdueHighestSingleMothOverdueAmount")
	private String loanOverdueHighestSingleMothOverdueAmount;
	
//	@XStreamAlias("LoanOverdueLongOverdueMonths")
	private String loanOverdueLongOverdueMonths;
	
//	@XStreamAlias("CreditCardOverdueAccountNum")
	private String creditCardOverdueAccountNum;
	
//	@XStreamAlias("CreditCardOverdueMothNum")
	private String creditCardOverdueMothNum;

	
	
	
//	@XStreamAlias("CreditCardOverdueMaxSingleMothOverdueAmount")
	private String creditCardOverdueMaxSingleMothOverdueAmount;
	
//	@XStreamAlias("CreditCardOverdraftLongOverdueMonths")
	private String creditCardOverdraftLongOverdueMonths;
	
//	@XStreamAlias("AssetsDisposalInfoSumAmount")
	private String assetsDisposalInfoSumAmount;
	
//	@XStreamAlias("QuasiCreditCardOverdraftAccountNum")
	private String quasiCreditCardOverdraftAccountNum;
	
//	@XStreamAlias("QuasiCreditCardOverdraft0DaysMothNum")
	private String quasiCreditCardOverdraft0DaysMothNum;
	
//	@XStreamAlias("QuasiCreditCardMaxSingleMothOverdueAmount")
	private String quasiCreditCardMaxSingleMothOverdueAmount;
	
//	@XStreamAlias("QuasiCreditCardOverdueLongOverdueMonths")
	private String quasiCreditCardOverdueLongOverdueMonths;

	public String getLoanOverdueAccountNum() {
		return loanOverdueAccountNum;
	}

	public void setLoanOverdueAccountNum(String loanOverdueAccountNum) {
		this.loanOverdueAccountNum = loanOverdueAccountNum;
	}

	public String getLoanOverdueMonthNum() {
		return loanOverdueMonthNum;
	}

	public void setLoanOverdueMonthNum(String loanOverdueMonthNum) {
		this.loanOverdueMonthNum = loanOverdueMonthNum;
	}

	public String getLoanOverdueHighestSingleMothOverdueAmount() {
		return loanOverdueHighestSingleMothOverdueAmount;
	}

	public void setLoanOverdueHighestSingleMothOverdueAmount(
			String loanOverdueHighestSingleMothOverdueAmount) {
		this.loanOverdueHighestSingleMothOverdueAmount = loanOverdueHighestSingleMothOverdueAmount;
	}

	public String getLoanOverdueLongOverdueMonths() {
		return loanOverdueLongOverdueMonths;
	}

	public void setLoanOverdueLongOverdueMonths(String loanOverdueLongOverdueMonths) {
		this.loanOverdueLongOverdueMonths = loanOverdueLongOverdueMonths;
	}

	public String getCreditCardOverdueAccountNum() {
		return creditCardOverdueAccountNum;
	}

	public void setCreditCardOverdueAccountNum(String creditCardOverdueAccountNum) {
		this.creditCardOverdueAccountNum = creditCardOverdueAccountNum;
	}

	public String getCreditCardOverdueMothNum() {
		return creditCardOverdueMothNum;
	}

	public void setCreditCardOverdueMothNum(String creditCardOverdueMothNum) {
		this.creditCardOverdueMothNum = creditCardOverdueMothNum;
	}

	public String getCreditCardOverdueMaxSingleMothOverdueAmount() {
		return creditCardOverdueMaxSingleMothOverdueAmount;
	}

	public void setCreditCardOverdueMaxSingleMothOverdueAmount(
			String creditCardOverdueMaxSingleMothOverdueAmount) {
		this.creditCardOverdueMaxSingleMothOverdueAmount = creditCardOverdueMaxSingleMothOverdueAmount;
	}

	public String getCreditCardOverdraftLongOverdueMonths() {
		return creditCardOverdraftLongOverdueMonths;
	}

	public void setCreditCardOverdraftLongOverdueMonths(
			String creditCardOverdraftLongOverdueMonths) {
		this.creditCardOverdraftLongOverdueMonths = creditCardOverdraftLongOverdueMonths;
	}

	public String getAssetsDisposalInfoSumAmount() {
		return assetsDisposalInfoSumAmount;
	}

	public void setAssetsDisposalInfoSumAmount(String assetsDisposalInfoSumAmount) {
		this.assetsDisposalInfoSumAmount = assetsDisposalInfoSumAmount;
	}

	public String getQuasiCreditCardOverdraftAccountNum() {
		return quasiCreditCardOverdraftAccountNum;
	}

	public void setQuasiCreditCardOverdraftAccountNum(
			String quasiCreditCardOverdraftAccountNum) {
		this.quasiCreditCardOverdraftAccountNum = quasiCreditCardOverdraftAccountNum;
	}

	public String getQuasiCreditCardOverdraft0DaysMothNum() {
		return quasiCreditCardOverdraft0DaysMothNum;
	}

	public void setQuasiCreditCardOverdraft0DaysMothNum(
			String quasiCreditCardOverdraft0DaysMothNum) {
		this.quasiCreditCardOverdraft0DaysMothNum = quasiCreditCardOverdraft0DaysMothNum;
	}

	public String getQuasiCreditCardMaxSingleMothOverdueAmount() {
		return quasiCreditCardMaxSingleMothOverdueAmount;
	}

	public void setQuasiCreditCardMaxSingleMothOverdueAmount(
			String quasiCreditCardMaxSingleMothOverdueAmount) {
		this.quasiCreditCardMaxSingleMothOverdueAmount = quasiCreditCardMaxSingleMothOverdueAmount;
	}

	public String getQuasiCreditCardOverdueLongOverdueMonths() {
		return quasiCreditCardOverdueLongOverdueMonths;
	}

	public void setQuasiCreditCardOverdueLongOverdueMonths(
			String quasiCreditCardOverdueLongOverdueMonths) {
		this.quasiCreditCardOverdueLongOverdueMonths = quasiCreditCardOverdueLongOverdueMonths;
	}
	

	
}
