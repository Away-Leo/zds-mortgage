package com.zdsoft.finance.cedit.api.entity;

//import com.thoughtworks.xstream.annotations.XStreamAlias;

//	@XStreamAlias("CompulsoryExecutionRecord") // 强制执行记录
public class CompulsoryExecutionRecord {


	
	//	@XStreamAlias("No") //编号
	private String no;
	
	//	@XStreamAlias("ExecutiveCourt") //执行法院
	private String executiveCourt;
	
	//	@XStreamAlias("ExecutiveCaseReason") //执行案由
	private String executiveCaseReason;
	
	//	@XStreamAlias("FilingDate") //立案日期
	private String filingDate;
	
	//	@XStreamAlias("ClosedWay") //结案方式
	private String closedWay;
	
	//	@XStreamAlias("CaseState") //案件状态
	private String caseState;
	

	//	@XStreamAlias("ClosedDate") //结案日期
	private String closedDate;
	

	//	@XStreamAlias("ApplyExecSubject") //申请执行标的
	private String applyExecSubject;
	

	//	@XStreamAlias("ApplyExecSubjectValue")  //申请执行标的价值
	private String applyExecSubjectValue;
	
	//	@XStreamAlias("AlreadyExecSubject")  //已执行标的
	private String alreadyExecSubject;
	
	//	@XStreamAlias("AlreadyExecSubjectValue")  //已执行标的金额
	private String alreadyExecSubjectValue;

	
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getExecutiveCourt() {
		return executiveCourt;
	}

	public void setExecutiveCourt(String executiveCourt) {
		this.executiveCourt = executiveCourt;
	}

	public String getExecutiveCaseReason() {
		return executiveCaseReason;
	}

	public void setExecutiveCaseReason(String executiveCaseReason) {
		this.executiveCaseReason = executiveCaseReason;
	}

	public String getFilingDate() {
		return filingDate;
	}

	public void setFilingDate(String filingDate) {
		this.filingDate = filingDate;
	}

	public String getClosedWay() {
		return closedWay;
	}

	public void setClosedWay(String closedWay) {
		this.closedWay = closedWay;
	}

	public String getCaseState() {
		return caseState;
	}

	public void setCaseState(String caseState) {
		this.caseState = caseState;
	}

	public String getClosedDate() {
		return closedDate;
	}

	public void setClosedDate(String closedDate) {
		this.closedDate = closedDate;
	}

	public String getApplyExecSubject() {
		return applyExecSubject;
	}

	public void setApplyExecSubject(String applyExecSubject) {
		this.applyExecSubject = applyExecSubject;
	}

	public String getApplyExecSubjectValue() {
		return applyExecSubjectValue;
	}

	public void setApplyExecSubjectValue(String applyExecSubjectValue) {
		this.applyExecSubjectValue = applyExecSubjectValue;
	}

	public String getAlreadyExecSubject() {
		return alreadyExecSubject;
	}

	public void setAlreadyExecSubject(String alreadyExecSubject) {
		this.alreadyExecSubject = alreadyExecSubject;
	}

	public String getAlreadyExecSubjectValue() {
		return alreadyExecSubjectValue;
	}

	public void setAlreadyExecSubjectValue(String alreadyExecSubjectValue) {
		this.alreadyExecSubjectValue = alreadyExecSubjectValue;
	}

	
	 
	

}
