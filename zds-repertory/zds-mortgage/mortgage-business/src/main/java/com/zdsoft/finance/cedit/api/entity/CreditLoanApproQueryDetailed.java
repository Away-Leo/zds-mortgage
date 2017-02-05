package com.zdsoft.finance.cedit.api.entity;

//import com.thoughtworks.xstream.annotations.XStreamAlias;

//	@XStreamAlias("CreditLoanApproQueryDetailed") 
public class CreditLoanApproQueryDetailed {
//信贷审批查询记录明细

	//	@XStreamAlias("No")
	private String No;
	
	//	@XStreamAlias("QueryDate")
	private String QueryDate;
	
	//	@XStreamAlias("QueryOperator")
	private String QueryOperator;
	
	//	@XStreamAlias("QueryReason")
	private String QueryReason;

	
	public String getNo() {
		return No;
	}

	public void setNo(String no) {
		No = no;
	}

	public String getQueryDate() {
		return QueryDate;
	}

	public void setQueryDate(String queryDate) {
		QueryDate = queryDate;
	}

	public String getQueryOperator() {
		return QueryOperator;
	}

	public void setQueryOperator(String queryOperator) {
		QueryOperator = queryOperator;
	}

	public String getQueryReason() {
		return QueryReason;
	}

	public void setQueryReason(String queryReason) {
		QueryReason = queryReason;
	}
	
   
	
	
}
