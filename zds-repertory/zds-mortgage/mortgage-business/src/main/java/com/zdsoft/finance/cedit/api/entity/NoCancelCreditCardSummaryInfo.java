package com.zdsoft.finance.cedit.api.entity;

//import com.thoughtworks.xstream.annotations.XStreamAlias;

//	@XStreamAlias("NoCancelCreditCardSummaryInfo")  // 未 销 户 贷 记 卡 信 息 汇 总
public class NoCancelCreditCardSummaryInfo {
//	HairpinLegalOrgNum 发卡法人机构数 S N  
//	HairpinOrgNum 发卡机构数 S N  
//	AccountNum 账户数 S N  
//	FinanceProfits 授信总额 S N  
//	SingleBankMaxFinanceLimit 单家行最高授信额 S N  
//	SingleBankMinFinanceLimit 单家行最低授信额 S N  
//	UsedCreditLimit 已用额度 S N  
//	Last6MonthsAvgUseLimit 最近 6个月平均使用额度 S N  
	
	//	@XStreamAlias("HairpinLegalOrgNum")
	private String hairpinLegalOrgNum;
	
	//	@XStreamAlias("HairpinOrgNum")
	private String hairpinOrgNum;
	
	//	@XStreamAlias("AccountNum")
	private String accountNum;
	
	//	@XStreamAlias("FinanceProfits")
	private String financeProfits;
	
	
	
	//	@XStreamAlias("SingleBankMaxFinanceLimit")
	private String singleBankMaxFinanceLimit;
	
	//	@XStreamAlias("SingleBankMinFinanceLimit")
	private String singleBankMinFinanceLimit;
	
	//	@XStreamAlias("UsedCreditLimit")
	private String usedCreditLimit;
	
	//	@XStreamAlias("Last6MonthsAvgUseLimit")
	private String last6MonthsAvgUseLimit;

	public String getHairpinLegalOrgNum() {
		return hairpinLegalOrgNum;
	}

	public void setHairpinLegalOrgNum(String hairpinLegalOrgNum) {
		this.hairpinLegalOrgNum = hairpinLegalOrgNum;
	}

	public String getHairpinOrgNum() {
		return hairpinOrgNum;
	}

	public void setHairpinOrgNum(String hairpinOrgNum) {
		this.hairpinOrgNum = hairpinOrgNum;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getFinanceProfits() {
		return financeProfits;
	}

	public void setFinanceProfits(String financeProfits) {
		this.financeProfits = financeProfits;
	}

	public String getSingleBankMaxFinanceLimit() {
		return singleBankMaxFinanceLimit;
	}

	public void setSingleBankMaxFinanceLimit(String singleBankMaxFinanceLimit) {
		this.singleBankMaxFinanceLimit = singleBankMaxFinanceLimit;
	}

	public String getSingleBankMinFinanceLimit() {
		return singleBankMinFinanceLimit;
	}

	public void setSingleBankMinFinanceLimit(String singleBankMinFinanceLimit) {
		this.singleBankMinFinanceLimit = singleBankMinFinanceLimit;
	}

	public String getUsedCreditLimit() {
		return usedCreditLimit;
	}

	public void setUsedCreditLimit(String usedCreditLimit) {
		this.usedCreditLimit = usedCreditLimit;
	}

	public String getLast6MonthsAvgUseLimit() {
		return last6MonthsAvgUseLimit;
	}

	public void setLast6MonthsAvgUseLimit(String last6MonthsAvgUseLimit) {
		this.last6MonthsAvgUseLimit = last6MonthsAvgUseLimit;
	}
	
	
	
}
