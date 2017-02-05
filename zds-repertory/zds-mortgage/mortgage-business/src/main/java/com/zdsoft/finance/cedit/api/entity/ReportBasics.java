package com.zdsoft.finance.cedit.api.entity;

//import com.thoughtworks.xstream.annotations.XStreamAlias;

//	@XStreamAlias("ReportBasics")
public class ReportBasics {
//报告基础信息【
	
//	ReportId 报告编号 S N  
//	ReportReqTime 查询请求时间 S N 
//	 ReportTime 报告时间 S N 
//	 QueryName 被查询者姓名 S N  
//	 QueryCertType 被查询者证件类型 S N 
//	 QueryCredNum 被查询者证件号码 S N 
//	 QueryOperator 查询操作员 S N
//	 QueryReason 查询原因 S N   
	
	
	
	//	@XStreamAlias("ReportId")
	private String reportId;
	
	//	@XStreamAlias("ReportReqTime")
	private String reportReqTime;
	
	//	@XStreamAlias("ReportTime")
	private String reportTime;
	
	//	@XStreamAlias("QueryName")
	private String queryName;
	
	//	@XStreamAlias("QueryCertType")
	private String queryCertType;
	
	//	@XStreamAlias("QueryCredNum")
	private String queryCredNum;
	
	//	@XStreamAlias("QueryOperator")
	private String queryOperator;
	
	//	@XStreamAlias("QueryReason")
	private String queryReason;

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getReportReqTime() {
		return reportReqTime;
	}

	public void setReportReqTime(String reportReqTime) {
		this.reportReqTime = reportReqTime;
	}

	public String getReportTime() {
		return reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public String getQueryCertType() {
		return queryCertType;
	}

	public void setQueryCertType(String queryCertType) {
		this.queryCertType = queryCertType;
	}

	public String getQueryCredNum() {
		return queryCredNum;
	}

	public void setQueryCredNum(String queryCredNum) {
		this.queryCredNum = queryCredNum;
	}

	public String getQueryOperator() {
		return queryOperator;
	}

	public void setQueryOperator(String queryOperator) {
		this.queryOperator = queryOperator;
	}

	public String getQueryReason() {
		return queryReason;
	}

	public void setQueryReason(String queryReason) {
		this.queryReason = queryReason;
	}
	
	
	

}
