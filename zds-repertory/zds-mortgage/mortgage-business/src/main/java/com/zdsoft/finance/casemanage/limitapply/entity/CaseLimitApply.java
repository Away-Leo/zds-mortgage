package com.zdsoft.finance.casemanage.limitApply.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;


/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CaseLimitApply.java 
 * @ClassName: CaseLimitApply 
 * @Description: 案件额度申请信息
 * @author xj 
 * @date 2017年2月16日 下午6:47:22 
 * @version V1.0
 */
@Entity
@Table(name="case_limit_apply")
public class CaseLimitApply extends BaseEntity{
	/**
	 * 额度未申请
	 */
	public static String NOT_APPLY ="YWDM0051056";
	/**
	 * 已申请未配资金
	 */
	public static String FILED_WITHOUT_MONEY ="YWDM0051057";
	/**
	 * 已配资金
	 */
	public static String ALLOCATED_FUNDS ="YWDM0051058";

	private static final long serialVersionUID = 6296944068509957142L;
	
	/**
	 * 案件id
	 */
	@Column(length=32)
	private String caseApplyId;
	
	/**
	 * 资金计划id
	 */
	@Column(length=32)
	private String fundPlanId;
	
	/**
	 * 资金计划名称
	 */
	@Column(length=64)
	private String fundPlanName;
	
	/**
	 * 贷款发放账户机构名称
	 */
	@Column(length=64)
	private String loanOutAccountBranchName;
	
	/**
	 * 贷款发放账户账户名
	 */
	@Column(length=64)
	private String loanOutAccountName;
	
	/**
	 * 贷款发放账户
	 */
	@Column(length=64)
	private String loanOutAccount;
	
	/**
	 * 贷款回款账户机构名称
	 */
	@Column(length=64)
	private String loanBackAccountBranchName;
	
	/**
	 * 贷款回款账户账户名
	 */
	@Column(length=64)
	private String loanBackAccountName;
	
	/**
	 * 贷款回款账户
	 */
	@Column(length=64)
	private String loanBackAccount;
	
	/**
	 * 状态 YWDM0051056：未申请，YWDM0051057：已申请未配资金，YWDM0051058：已配资金
	 */
	@Column(length=20)
	private String effectiveStatus;
	
	/**
	 * 申请额度
	 */
	@Column(precision=18,scale=6)
	private BigDecimal applyLimitAmount = BigDecimal.ZERO;
	
	/**
	 * 申请人code
	 */
	@Column(length=32)
	private String applyEmpCode;
	
	/**
	 * 申请人姓名
	 */
	@Column(length=64)
	private String applyEmpName;
	
	/**
	 * 分配日期
	 */
	@Column
	private Long allotDate;
	
	/**
	 * 申请时间
	 */
	@Column
	private Long applyDate;
	
	/**
	 * 取消人code
	 */
	@Column(length=32)
	private String cancelEmpCode;
	
	/**
	 * 取消人姓名
	 */
	@Column(length=64)
	private String cancelEmpName;
	
	
	/**
	 * 取消时间
	 */
	@Column
	private Long cancelDate;

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public String getFundPlanId() {
		return fundPlanId;
	}

	public void setFundPlanId(String fundPlanId) {
		this.fundPlanId = fundPlanId;
	}

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

	public String getEffectiveStatus() {
		return effectiveStatus;
	}

	public void setEffectiveStatus(String effectiveStatus) {
		this.effectiveStatus = effectiveStatus;
	}

	public BigDecimal getApplyLimitAmount() {
		return applyLimitAmount;
	}

	public void setApplyLimitAmount(BigDecimal applyLimitAmount) {
		this.applyLimitAmount = applyLimitAmount;
	}

	public String getApplyEmpCode() {
		return applyEmpCode;
	}

	public void setApplyEmpCode(String applyEmpCode) {
		this.applyEmpCode = applyEmpCode;
	}

	public String getApplyEmpName() {
		return applyEmpName;
	}

	public void setApplyEmpName(String applyEmpName) {
		this.applyEmpName = applyEmpName;
	}

	public Long getAllotDate() {
		return allotDate;
	}

	public void setAllotDate(Long allotDate) {
		this.allotDate = allotDate;
	}

	public Long getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Long applyDate) {
		this.applyDate = applyDate;
	}

	public String getCancelEmpCode() {
		return cancelEmpCode;
	}

	public void setCancelEmpCode(String cancelEmpCode) {
		this.cancelEmpCode = cancelEmpCode;
	}

	public String getCancelEmpName() {
		return cancelEmpName;
	}

	public void setCancelEmpName(String cancelEmpName) {
		this.cancelEmpName = cancelEmpName;
	}

	public Long getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Long cancelDate) {
		this.cancelDate = cancelDate;
	}
	
	

	
	
	
	
	

}
