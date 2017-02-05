package com.zdsoft.finance.cedit.api.entity;

//import com.thoughtworks.xstream.annotations.XStreamAlias;

//	@XStreamAlias("NoCAQuasiCreditCardInfoSum")  // 未注销借记卡 信息
public class NoCAQuasiCreditCardInfoSum {

	//未 销 户 准 贷 记 卡 信 息 汇 总
//	SendCardLegalPersonOrgNum 发卡法人机构数 S N 
//	SendCardOrgNum 发卡机构数 S N  
//	AccountNumber 账户数 S N  
//	CreditTotalAmount 授信总额 S  N  
//	AloneBankHighestCreditAmount 单家行最高授信额 S N 
//	AloneBankLowestCreditAmount 单家行最低授信额 S N  
//	OverdraftAmount 透支余额 S N  
//	Recent6MonthAveOverdAmount 最近 6个月平均透支余额 S N   
	//	@XStreamAlias("SendCardLegalPersonOrgNum")
	private String SendCardLegalPersonOrgNum;
	
	//	@XStreamAlias("SendCardOrgNum")
	private String SendCardOrgNum;
	
	//	@XStreamAlias("AccountNumber")
	private String AccountNumber;
	
	//	@XStreamAlias("CreditTotalAmount")
	private String CreditTotalAmount;
	
	//	@XStreamAlias("AloneBankHighestCreditAmount")
	private String AloneBankHighestCreditAmount;
	
	//	@XStreamAlias("AloneBankLowestCreditAmount")
	private String AloneBankLowestCreditAmount;
	
	//	@XStreamAlias("OverdraftAmount")
	private String OverdraftAmount;
	
	//	@XStreamAlias("Recent6MonthAveOverdAmount")
	private String Recent6MonthAveOverdAmount;

	public String getSendCardLegalPersonOrgNum() {
		return SendCardLegalPersonOrgNum;
	}

	public void setSendCardLegalPersonOrgNum(String sendCardLegalPersonOrgNum) {
		SendCardLegalPersonOrgNum = sendCardLegalPersonOrgNum;
	}

	public String getSendCardOrgNum() {
		return SendCardOrgNum;
	}

	public void setSendCardOrgNum(String sendCardOrgNum) {
		SendCardOrgNum = sendCardOrgNum;
	}

	public String getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}

	public String getCreditTotalAmount() {
		return CreditTotalAmount;
	}

	public void setCreditTotalAmount(String creditTotalAmount) {
		CreditTotalAmount = creditTotalAmount;
	}

	public String getAloneBankHighestCreditAmount() {
		return AloneBankHighestCreditAmount;
	}

	public void setAloneBankHighestCreditAmount(String aloneBankHighestCreditAmount) {
		AloneBankHighestCreditAmount = aloneBankHighestCreditAmount;
	}

	public String getAloneBankLowestCreditAmount() {
		return AloneBankLowestCreditAmount;
	}

	public void setAloneBankLowestCreditAmount(String aloneBankLowestCreditAmount) {
		AloneBankLowestCreditAmount = aloneBankLowestCreditAmount;
	}

	public String getOverdraftAmount() {
		return OverdraftAmount;
	}

	public void setOverdraftAmount(String overdraftAmount) {
		OverdraftAmount = overdraftAmount;
	}

	public String getRecent6MonthAveOverdAmount() {
		return Recent6MonthAveOverdAmount;
	}

	public void setRecent6MonthAveOverdAmount(String recent6MonthAveOverdAmount) {
		Recent6MonthAveOverdAmount = recent6MonthAveOverdAmount;
	}
	
	
	
	
}
