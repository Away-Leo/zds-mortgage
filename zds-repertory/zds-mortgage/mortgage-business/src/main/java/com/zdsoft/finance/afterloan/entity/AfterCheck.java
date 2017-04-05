package com.zdsoft.finance.afterloan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfloanCheck.java 
 * @ClassName: AfloanCheck 
 * @Description: 贷后检查实体
 * @author zhoushichao 
 * @date 2017年2月8日 下午4:35:24 
 * @version V1.0 
 */ 
@Entity
@Table(name = "aftloan_after_check")
public class AfterCheck extends BaseEntity {

	private static final long serialVersionUID = -4127455301162412753L;

	/**
     * 案件Id
     */
    @Column(length = 32)
    private String caseApplyId;
    /**
     * 贷后检查动作
     */
    @Column(length = 20)
    private String actions;
    /**
     * （动作）具体说明一
     */
    @Column(length = 3000)
    private String noteA;
    /**
     * 客户联系情况
     */
    @Column(length = 20)
    private String customerCondiction;
    /**
     * （客户联系情况）具体说明二
     */
    @Column(length = 3000)
    private String noteB;
    /**
     * 执行查询结果
     */
    @Column(length = 20)
    private String searchResult;
    /**
     * （执行查询）具体说明三
     */
    @Column(length = 3000)
    private String noteC;
    /**
     * 抵押物情况
     */
    @Column(length = 20)
    private String mortCondiction;
    /**
     * 经营情况
     */
    @Column(length = 3000)
    private String busiCondiction;
    /**
     * 客户还款能力分析
     */
    @Column(length = 3000)
    private String payBackCheck;
    /**
     * 风险分析
     */
    @Column(length = 20)
    private String riskAnalysis;
    /**
     * 风险措施
     */
    @Column(length = 3000)
    private String riskPrecaution;
    /**
     * 货后检查人员ID
     */
    @Column(length = 32)
    private String trackerCode;
    /**
     * 货后检查人员姓名
     */
    @Column(length = 64)
    private String trackerName;
    /**
     * 货后检查时间
     */
    @Column
    private Long checkedDate;
    
	public String getCaseApplyId() {
		return caseApplyId;
	}
	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}
	public String getActions() {
		return actions;
	}
	public void setActions(String actions) {
		this.actions = actions;
	}
	public String getNoteA() {
		return noteA;
	}
	public void setNoteA(String noteA) {
		this.noteA = noteA;
	}
	public String getCustomerCondiction() {
		return customerCondiction;
	}
	public void setCustomerCondiction(String customerCondiction) {
		this.customerCondiction = customerCondiction;
	}
	public String getNoteB() {
		return noteB;
	}
	public void setNoteB(String noteB) {
		this.noteB = noteB;
	}
	public String getSearchResult() {
		return searchResult;
	}
	public void setSearchResult(String searchResult) {
		this.searchResult = searchResult;
	}
	public String getNoteC() {
		return noteC;
	}
	public void setNoteC(String noteC) {
		this.noteC = noteC;
	}
	public String getMortCondiction() {
		return mortCondiction;
	}
	public void setMortCondiction(String mortCondiction) {
		this.mortCondiction = mortCondiction;
	}
	public String getBusiCondiction() {
		return busiCondiction;
	}
	public void setBusiCondiction(String busiCondiction) {
		this.busiCondiction = busiCondiction;
	}
	public String getPayBackCheck() {
		return payBackCheck;
	}
	public void setPayBackCheck(String payBackCheck) {
		this.payBackCheck = payBackCheck;
	}
	public String getRiskAnalysis() {
		return riskAnalysis;
	}
	public void setRiskAnalysis(String riskAnalysis) {
		this.riskAnalysis = riskAnalysis;
	}
	public String getRiskPrecaution() {
		return riskPrecaution;
	}
	public void setRiskPrecaution(String riskPrecaution) {
		this.riskPrecaution = riskPrecaution;
	}

	public String getTrackerCode() {
		return trackerCode;
	}
	public void setTrackerCode(String trackerCode) {
		this.trackerCode = trackerCode;
	}
	public String getTrackerName() {
		return trackerName;
	}
	public void setTrackerName(String trackerName) {
		this.trackerName = trackerName;
	}
	public Long getCheckedDate() {
		return checkedDate;
	}
	public void setCheckedDate(Long checkedDate) {
		this.checkedDate = checkedDate;
	}
}
