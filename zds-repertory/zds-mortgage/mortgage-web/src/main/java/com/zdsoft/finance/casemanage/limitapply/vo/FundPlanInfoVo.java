package com.zdsoft.finance.casemanage.limitapply.vo;

import java.math.BigDecimal;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:FundPlanInfoVo.java
 * @Package:com.zdsoft.finance.casemanage.limitapply.vo
 * @Description:用户临时存放调用接口返回的资金计划,贷款回款账户信息(包括是不是备付金账户),账户余额
 * @author: xiongpan
 * @date:2017年1月15日 上午9:30:52
 * @version:v1.0
 */
public class FundPlanInfoVo {
	
    /**
     * 资金计划名称
     */
    private String fundPlanName;
	
    /**
     * 货款发放账户机构名称
     */
	private String loanOutAccountBranchName;
	
	/**
     * 货款发放账户账户名
     */
    private String loanOutAccountName;
    
    /**
     * 货款发放账户
     */
    private String loanOutAccount;
    
    /**
     * 货款发放账户类型(0.专项账户;1.备付金账户)
     */
    private String isPrepareAccount;
    
    /**
     * 货款发放账户余额
     */
    private BigDecimal loanOutAccountAmount;
    
    /**
     * 货款回款账户机构名称
     */
    private String loanBackAccountBranchName;
    
    /**
     * 货款回款账户账户名
     */
    private String loanBackAccountName;
    
    /**
     * 货款回款账户
     */
    private String loanBackAccount;
    
	/**
	 * 是否已获得备付资金分配(0.否(默认);1.是)
	 */
	private String isGetPrepareLimit;

	public String getFundPlanName() {
		return fundPlanName;
	}

	public void setFundPlanName(String fundPlanName) {
		this.fundPlanName = fundPlanName;
	}

	public String getLoanOutAccountBranchName() {
		return loanOutAccountBranchName;
	}

	public void setLoanOutAccountBranchName(String loanOutAccountBranchName) {
		this.loanOutAccountBranchName = loanOutAccountBranchName;
	}

	public String getLoanOutAccountName() {
		return loanOutAccountName;
	}

	public void setLoanOutAccountName(String loanOutAccountName) {
		this.loanOutAccountName = loanOutAccountName;
	}

	public String getLoanOutAccount() {
		return loanOutAccount;
	}

	public void setLoanOutAccount(String loanOutAccount) {
		this.loanOutAccount = loanOutAccount;
	}

	public String getIsPrepareAccount() {
		return isPrepareAccount;
	}

	public void setIsPrepareAccount(String isPrepareAccount) {
		this.isPrepareAccount = isPrepareAccount;
	}
	

	public BigDecimal getLoanOutAccountAmount() {
		return loanOutAccountAmount;
	}

	public void setLoanOutAccountAmount(BigDecimal loanOutAccountAmount) {
		this.loanOutAccountAmount = loanOutAccountAmount;
	}

	public String getLoanBackAccountBranchName() {
		return loanBackAccountBranchName;
	}

	public void setLoanBackAccountBranchName(String loanBackAccountBranchName) {
		this.loanBackAccountBranchName = loanBackAccountBranchName;
	}

	public String getLoanBackAccountName() {
		return loanBackAccountName;
	}

	public void setLoanBackAccountName(String loanBackAccountName) {
		this.loanBackAccountName = loanBackAccountName;
	}

	public String getLoanBackAccount() {
		return loanBackAccount;
	}

	public void setLoanBackAccount(String loanBackAccount) {
		this.loanBackAccount = loanBackAccount;
	}

	public String getIsGetPrepareLimit() {
		return isGetPrepareLimit;
	}

	public void setIsGetPrepareLimit(String isGetPrepareLimit) {
		this.isGetPrepareLimit = isGetPrepareLimit;
	}
    
    
}
