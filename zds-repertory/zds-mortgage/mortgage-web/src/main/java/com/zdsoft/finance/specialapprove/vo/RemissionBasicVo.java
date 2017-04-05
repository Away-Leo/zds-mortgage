package com.zdsoft.finance.specialapprove.vo;

import java.math.BigDecimal;

import javax.persistence.Column;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RemissionBasicVo.java 
 * @ClassName: RemissionBasicVo 
 * @Description: 费用减免特批基本信息Vo
 * @author wangrongwei
 * @date 2017年2月27日 下午2:45:31 
 * @version V1.0 
 */ 
public class RemissionBasicVo {
	
	/**   
	 * @Fields caseApplyCode : 案件号   
	 */ 
	private String caseApplyCode;
	
	/**   
	 * @Fields remissionCode : 费用减免编号   
	 */ 
	private String remissionCode;
	
	/**   
	 * @Fields loanAmount : 放款金额   
	 */ 
	private BigDecimal loanAmount;
	
	/**   
	 * @Fields applyUserName : 申请人名称   
	 */ 
	private String applyUserName;
	
	/**   
	 * @Fields applyDeptName : 申请部门名称   
	 */ 
	private String applyDeptName;
	
	/**   
	 * @Fields applyDate : 申请日期   
	 */ 
	private String applyDate;
	
	/**   
	 * @Fields orgName : 机构名称  
	 */ 
	private String orgName;
	
	/**   
	 * @Fields mainBorrowName : 主借人   
	 */ 
	private String mainBorrowName;
	
	/**   
	 * @Fields productSubtypeName : 子产品  
	 */ 
	private String productSubtypeName;
	
	/**   
	 * @Fields principalBalance : 本金余额   
	 */ 
	private BigDecimal principalBalance;
	
	/**   
	 * @Fields loaningDays : 垫资天数  
	 */ 
	private Integer loaningDays;
	
	/**   
	 * @Fields overdueDays : 逾期天数   
	 */ 
	private Integer overdueDays;
	
	/**   
	 * @Fields loanDate : 放款日期   
	 */ 
	private String loanDate;
	
	/**   
	 * @Fields currentReceiveAmount : 本次还款日期   
	 */ 
	private String currentReceiveDate;
	
	/**   
	 * @Fields penaltyUseStandard : 罚息挂钩标准
	 */ 
	@Column(length=20)
	private String penaltyUseStandard;

	public RemissionBasicVo() {
	}

	public String getCaseApplyCode() {
		return caseApplyCode;
	}

	public void setCaseApplyCode(String caseApplyCode) {
		this.caseApplyCode = caseApplyCode;
	}

	public String getRemissionCode() {
		return remissionCode;
	}

	public void setRemissionCode(String remissionCode) {
		this.remissionCode = remissionCode;
	}

	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getApplyUserName() {
		return applyUserName;
	}

	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}

	public String getApplyDeptName() {
		return applyDeptName;
	}

	public void setApplyDeptName(String applyDeptName) {
		this.applyDeptName = applyDeptName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getMainBorrowName() {
		return mainBorrowName;
	}

	public void setMainBorrowName(String mainBorrowName) {
		this.mainBorrowName = mainBorrowName;
	}

	public String getProductSubtypeName() {
		return productSubtypeName;
	}

	public void setProductSubtypeName(String productSubtypeName) {
		this.productSubtypeName = productSubtypeName;
	}

	public BigDecimal getPrincipalBalance() {
		return principalBalance;
	}

	public void setPrincipalBalance(BigDecimal principalBalance) {
		this.principalBalance = principalBalance;
	}

	public Integer getLoaningDays() {
		return loaningDays;
	}

	public void setLoaningDays(Integer loaningDays) {
		this.loaningDays = loaningDays;
	}

	public Integer getOverdueDays() {
		return overdueDays;
	}

	public void setOverdueDays(Integer overdueDays) {
		this.overdueDays = overdueDays;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}

	public String getCurrentReceiveDate() {
		return currentReceiveDate;
	}

	public void setCurrentReceiveDate(String currentReceiveDate) {
		this.currentReceiveDate = currentReceiveDate;
	}

	public String getPenaltyUseStandard() {
		return penaltyUseStandard;
	}

	public void setPenaltyUseStandard(String penaltyUseStandard) {
		this.penaltyUseStandard = penaltyUseStandard;
	}
}
