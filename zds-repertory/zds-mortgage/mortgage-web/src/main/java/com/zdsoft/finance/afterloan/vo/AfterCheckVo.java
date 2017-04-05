package com.zdsoft.finance.afterloan.vo;

import com.zdsoft.finance.afterloan.entity.AfterCheck;
import com.zdsoft.finance.common.base.BaseVo;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterCheckVo.java 
 * @ClassName: AfterCheckVo 
 * @Description: 贷后检查Vo
 * @author zhoushichao 
 * @date 2017年2月8日 下午4:35:24 
 * @version V1.0 
 */ 
public class AfterCheckVo extends BaseVo<AfterCheck> {

	private static final long serialVersionUID = 1L;
	/**
     * 案件Id
     */
    private String caseApplyId;
    /**
     * 贷后检查动作code
     */
    private String actions;
    /**
     * 贷后检查动作name
     */
    private String actionsName;
    /**
     * 具体说明一
     */
    private String noteA;
    /**
     * 客户联系情况code
     */
    private String customerCondiction;
    /**
     * 客户联系情况name
     */
    private String customerCondictionName;
    /**
     * 具体说明二
     */
    private String noteB;
    /**
     * 执行查询结果code
     */
    private String searchResult;
    /**
     * 执行查询结果name
     */
    private String searchResultName;
    /**
     * 具体说明三
     */
    private String noteC;
    /**
     * 抵押物情况code
     */
    private String mortCondiction;
    /**
     * 抵押物情况name
     */
    private String mortCondictionName;
    /**
     * 经营情况
     */
    private String busiCondiction;
    /**
     * 客户还款能力分析
     */
    private String payBackCheck;
    /**
     * 风险分析code
     */
    private String riskAnalysis;
    /**
     * 风险分析name
     */
    private String riskAnalysisName;
    /**
     * 风险措施
     */
    private String riskPrecaution;
    /**
     * 货后检查人员姓名
     */
    private String trackerCode;
    /**
     * 货后检查人员姓名
     */
    private String trackerName;
    /**
     * 货后检查结果汇总时间
     */
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
	public String getActionsName() {
		return actionsName;
	}
	public void setActionsName(String actionsName) {
		this.actionsName = actionsName;
	}
	public String getCustomerCondictionName() {
		return customerCondictionName;
	}
	public void setCustomerCondictionName(String customerCondictionName) {
		this.customerCondictionName = customerCondictionName;
	}
	public String getSearchResultName() {
		return searchResultName;
	}
	public void setSearchResultName(String searchResultName) {
		this.searchResultName = searchResultName;
	}
	public String getMortCondictionName() {
		return mortCondictionName;
	}
	public void setMortCondictionName(String mortCondictionName) {
		this.mortCondictionName = mortCondictionName;
	}
	public String getRiskAnalysisName() {
		return riskAnalysisName;
	}
	public void setRiskAnalysisName(String riskAnalysisName) {
		this.riskAnalysisName = riskAnalysisName;
	}
	public AfterCheckVo() {
	        super();
    }

    public AfterCheckVo(AfterCheck po) {
    	super(po, null, new String[] {"actions","customerCondiction","searchResult","mortCondiction","riskAnalysis" });
    }

    public AfterCheck toPO() {
    	AfterCheck po = new AfterCheck();
        return super.toPo(this, po);
    }
}
