package com.zdsoft.finance.credit.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: HmCompulsoryExecutionRecord.java
 * @ClassName: HmCompulsoryExecutionRecord
 * @Description: 强制执行记录
 * @author gufeng
 * @date 2017年2月23日 上午9:38:42
 * @version V1.0
 */
@Entity
@Table(name = "hm_Compulsory_Execution_Record")
public class HmCompulsoryExecutionRecord extends BaseEntity {

	private static final long serialVersionUID = 3453883673506497383L;
	/**
	 * 查询表ID
	 */
	@Column(name = "query_Id")
	private String queryId;
	/**
	 * 证件号码
	 */
	@Column(name = "id_card")
	private String idCard;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Timestamp createTime;

	/**
	 * 编号
	 */
	@Column(name = "no")
	private Integer no;

	/**
	 * 执行法院
	 */
	@Column(name = "Executive_Court")
	private String executiveCourt;

	/**
	 * 执行案由
	 */
	@Column(name = "Executive_Case_Reason")
	private String executiveCaseReason;

	/**
	 * 立案日期
	 */
	@Column(name = "Filing_Date")
	private String filingDate;

	/**
	 * 结案方式
	 */
	@Column(name = "Closed_Way")
	private String closedWay;

	/**
	 * 案件状态
	 */
	@Column(name = "Case_State")
	private String caseState;

	/**
	 * 结案日期
	 */
	@Column(name = "Closed_Date")
	private String closedDate;

	/**
	 * 申请执行标的
	 */
	@Column(name = "Apply_Exec_Subject")
	private String applyExecSubject;

	/**
	 * 申请执行标的价值
	 */
	@Column(name = "Apply_ExecSubject_Value")
	private String applyExecSubjectValue;

	/**
	 * 已执行标的
	 */
	@Column(name = "Already_Exec_Subject")
	private String alreadyExecSubject;

	/**
	 * 已执行标的金额
	 */
	@Column(name = "Already_ExecSubject_Value")
	private String alreadyExecSubjectValue;

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
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
