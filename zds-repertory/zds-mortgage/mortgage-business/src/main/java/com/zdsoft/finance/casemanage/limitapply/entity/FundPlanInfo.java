package com.zdsoft.finance.casemanage.limitapply.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:FundPlanInfo.java
 * @Package:com.zdsoft.finance.casemanage.limitapply.entity
 * @Description:案件资金计划信息
 * @author: xiongpan
 * @date:2017年1月15日 下午10:29:25
 * @version:v1.0
 */

@Entity
@Table(name = "case_fundPlan_info")
public class FundPlanInfo extends BaseEntity{

	private static final long serialVersionUID = 2072542118160864288L;
	
	 /**
     * 资金计划名称
     */
	@Column(length = 32)
    private String fundPlanName;
	
    /**
     * 货款发放账户机构名称
     */
	@Column(length = 32)
	private String loanOutAccountBranchName;
	
	/**
     * 货款发放账户账户名
     */
	@Column(length = 32)
    private String loanOutAccountName;
    
    /**
     * 货款发放账户
     */
	@Column(length = 32)
    private String loanOutAccount;
    
    /**
     * 货款发放账户类型(0.专项账户;1.备付金账户)
     */
	@Column(length = 20)
    private String isPrepareAccount;
    
    /**
     * 货款发放账户余额
     */
	@Column(length = 32)
    private BigDecimal loanOutAccountAmount;
    
    /**
     * 货款回款账户机构名称
     */
	@Column(length = 32)
    private String loanBackAccountBranchName;
    
    /**
     * 货款回款账户账户名
     */
	@Column(length = 32)
    private String loanBackAccountName;
    
    /**
     * 货款回款账户
     */
	@Column(length = 32)
    private String loanBackAccount;
	
	/**
	 * 是否已获得备付资金分配(0.否(默认);1.是)
	 * @author xiongpan
	 * @date 2017-01-10
	 */
	@Column(length = 20)
	private String isGetPrepareLimit = "YWDM0049001";
	
	

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
