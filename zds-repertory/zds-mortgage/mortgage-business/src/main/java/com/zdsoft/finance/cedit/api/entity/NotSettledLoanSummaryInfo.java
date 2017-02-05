package com.zdsoft.finance.cedit.api.entity;

//import com.thoughtworks.xstream.annotations.XStreamAlias;
//未结清贷款信息汇总
//	@XStreamAlias("NotSettledLoanSummaryInfo") 
public class NotSettledLoanSummaryInfo {

	//	@XStreamAlias("LoanLegalOrgNum")
	private String loanLegalOrgNum; //贷款法人机构数
	
	//	@XStreamAlias("LoanOrgNum")//贷款机构数 
	private String loanOrgNum;
	
	//	@XStreamAlias("CountNum") //笔数
	private String countNum;
	
	//	@XStreamAlias("ContractProfits") //合同总额
	private String contractProfits;

	
	//	@XStreamAlias("Last6MothsAvgRepayAmount") //最近 6个月平均应还款
	private String last6MothsAvgRepayAmount;
	
	//	@XStreamAlias("Balance") //余额
	private String balance;

	public String getLoanLegalOrgNum() {
		return loanLegalOrgNum;
	}

	public void setLoanLegalOrgNum(String loanLegalOrgNum) {
		this.loanLegalOrgNum = loanLegalOrgNum;
	}

	public String getLoanOrgNum() {
		return loanOrgNum;
	}

	public void setLoanOrgNum(String loanOrgNum) {
		this.loanOrgNum = loanOrgNum;
	}

	public String getCountNum() {
		return countNum;
	}

	public void setCountNum(String countNum) {
		this.countNum = countNum;
	}

	public String getContractProfits() {
		return contractProfits;
	}

	public void setContractProfits(String contractProfits) {
		this.contractProfits = contractProfits;
	}



	public String getLast6MothsAvgRepayAmount() {
		return last6MothsAvgRepayAmount;
	}

	public void setLast6MothsAvgRepayAmount(String last6MothsAvgRepayAmount) {
		this.last6MothsAvgRepayAmount = last6MothsAvgRepayAmount;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}
	
	
	
}
