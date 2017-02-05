package com.zdsoft.finance.app.repaymenttrial.vo;

import java.math.BigDecimal;

public class TrialCalcProidVo {
    /**
     * 分页当前页
     */
    private Integer  pageIndex;
    /**
     * 每页数据量
     */
    private Integer  pageSize;
    /**
     * 本金
     */
	private BigDecimal loanAmount;
	/**
	 * 期限
	 */
	private String periodNum;
	/**
	 * 还款方式
	 */
	private String repaymentType;
	/**
	 * 年利率
	 */
	private String annualRate;
	/**
	 * 期限单位
	 */
	private String periodNumUnit;

	
    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getPeriodNum() {
        return periodNum;
    }

    public void setPeriodNum(String periodNum) {
        this.periodNum = periodNum;
    }

    public String getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(String repaymentType) {
        this.repaymentType = repaymentType;
    }

    public String getAnnualRate() {
        return annualRate;
    }

    public void setAnnualRate(String annualRate) {
        this.annualRate = annualRate;
    }

    public String getPeriodNumUnit() {
        return periodNumUnit;
    }

    public void setPeriodNumUnit(String periodNumUnit) {
        this.periodNumUnit = periodNumUnit;
    }
}
