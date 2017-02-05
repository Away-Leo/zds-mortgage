package com.zdsoft.finance.cedit.api.entity;

//import com.thoughtworks.xstream.annotations.XStreamAlias;

//	@XStreamAlias("CreditPromptInfo")  // 信用提示
public class CreditPromptInfo {

	
	
	
//	2.5.2.6 n信用提示【CreditPromptInfo】 
//	PersonalHouseLoanNum 个人住房贷款笔数 S N 
//	HouseLoanNum 住房贷款笔数 S N  
//	OtherLoanNum 其他贷款笔数 S N 
//	FristLoanMonth 首笔贷款发放月份 S N  
//	CreditCardAccountNum 贷记卡账户数 S N  
//	FristCreditCardMonth 首张贷记卡发放月份 S N 
//	QuasiCreditCardNum 准贷记卡账户数 S N  
//	QuasiCreditCardMonth 首张准贷记卡发放月份 S N  
//	DeclareNum 本人声明数目 S N  
//	DissentNum 异议标注数目 S N 
	
	//	@XStreamAlias("PersonalHouseLoanNum")
	private String personalHouseLoanNum;
	
	//	@XStreamAlias("HouseLoanNum")
	private String houseLoanNum;
	
	//	@XStreamAlias("OtherLoanNum")
	private String otherLoanNum;
	
	//	@XStreamAlias("FristLoanMonth")
	private String fristLoanMonth;
	
	//	@XStreamAlias("CreditCardAccountNum")
	private String creditCardAccountNum;
	
	//	@XStreamAlias("fristCreditCardMonth")
	private String FristCreditCardMonth;
	
	//	@XStreamAlias("QuasiCreditCardNum")
	private String quasiCreditCardNum;
	
	//	@XStreamAlias("QuasiCreditCardMonth")
	private String quasiCreditCardMonth;
	
	//	@XStreamAlias("DeclareNum")
	private String declareNum;
	
	//	@XStreamAlias("DissentNum")
	private String dissentNum;

	public String getPersonalHouseLoanNum() {
		return personalHouseLoanNum;
	}

	public void setPersonalHouseLoanNum(String personalHouseLoanNum) {
		this.personalHouseLoanNum = personalHouseLoanNum;
	}

	public String getHouseLoanNum() {
		return houseLoanNum;
	}

	public void setHouseLoanNum(String houseLoanNum) {
		this.houseLoanNum = houseLoanNum;
	}

	public String getOtherLoanNum() {
		return otherLoanNum;
	}

	public void setOtherLoanNum(String otherLoanNum) {
		this.otherLoanNum = otherLoanNum;
	}

	public String getFristLoanMonth() {
		return fristLoanMonth;
	}

	public void setFristLoanMonth(String fristLoanMonth) {
		this.fristLoanMonth = fristLoanMonth;
	}

	public String getCreditCardAccountNum() {
		return creditCardAccountNum;
	}

	public void setCreditCardAccountNum(String creditCardAccountNum) {
		this.creditCardAccountNum = creditCardAccountNum;
	}

	public String getFristCreditCardMonth() {
		return FristCreditCardMonth;
	}

	public void setFristCreditCardMonth(String fristCreditCardMonth) {
		FristCreditCardMonth = fristCreditCardMonth;
	}

	public String getQuasiCreditCardNum() {
		return quasiCreditCardNum;
	}

	public void setQuasiCreditCardNum(String quasiCreditCardNum) {
		this.quasiCreditCardNum = quasiCreditCardNum;
	}

	public String getQuasiCreditCardMonth() {
		return quasiCreditCardMonth;
	}

	public void setQuasiCreditCardMonth(String quasiCreditCardMonth) {
		this.quasiCreditCardMonth = quasiCreditCardMonth;
	}

	public String getDeclareNum() {
		return declareNum;
	}

	public void setDeclareNum(String declareNum) {
		this.declareNum = declareNum;
	}

	public String getDissentNum() {
		return dissentNum;
	}

	public void setDissentNum(String dissentNum) {
		this.dissentNum = dissentNum;
	}
	
   
	

}
