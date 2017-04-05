package com.zdsoft.finance.loan.entity;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: LoanApply.java 
 * @ClassName: LoanApply 
 * @Description: 放款申请实体类
 * @author huangwei 
 * @date 2017年2月22日 上午11:07:49 
 * @version V1.0
 */
@Entity
@Table(name = "loan_apply")
public class LoanApply extends BaseEntity {

	private static final long serialVersionUID = 1L;
	public static final String processCode = "YWDM0012605";
	/**
	 * 请款金额
	 */
	@Column(nullable = false, precision = 18, scale = 6)
	private BigDecimal applyAmount = BigDecimal.ZERO;
	/**
	 * 申请时间
	 */
	@Column(nullable = false)
	private Long applyDate;
	/**
	 * 批次号
	 */
	@Column( length = 32)
	private String batchNumber;
	/**
	 * 所在分部
	 */
	@Column(nullable = false, length = 32)
	private String branchId;
	/**
	 * 所在主体
	 */
	@Column(nullable = false, length = 32)
	private String companyId;
	/**
	 * 申请人部门
	 */
	@Column(nullable = false, length = 32)
	private String deptId;
	/**
	 * 申请人
	 */
	@Column(nullable = false, length = 32)
	private String empId;
	/**
	 * 放款金额
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal loanAmount=BigDecimal.ZERO;
	/**
	 * 申请单号
	 */
	@Column(nullable = false, length = 32)
	private String loanApplyCode;
	/**
	 * 准放款金额
	 */
	@Column( precision = 18, scale = 6)
	private BigDecimal quasiLoanAmount = BigDecimal.ZERO;
	/**
	 * 准放款时间
	 */
	@Column
	private Long quasiLoanDate;
	/**
	 * 收款帐号
	 */
	@Column(nullable = false, length = 50)
	private String receiveBankAccount;
	/**
	 * 收款帐户名称
	 */
	@Column(nullable = false, length = 50)
	private String receiveBankAccountName;
	/**
	 * 收款开户行
	 */
	@Column(nullable = false, length = 32)
	private String receiveBankName;
	/**
	 * 放款状态(1.待放款,2.准放款，3.已放款,4.审批中,5.未提交审批,6。审批未通过)
	 */
	@Column(nullable = false, length = 20)
	private String status;
	/**
	 * 放款操作记录表
	 */
	@OneToMany(mappedBy = "loanApply", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<LoanRecord> loanRecords;
	/**
	 * 案件id
	 */
	@Column(nullable = false, length = 32)
	private String caseApplyId;
	/**
	 * 准放款备注
	 */
	@Column(length = 512)
	private String remark;
	
	/**
	 * 流程基础信息
	 */
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name = "busiFormId")
	private BusiForm busiForm ;
	
	public LoanApply() {
	}
	
	
	public BusiForm getBusiForm() {
		return busiForm;
	}

	public void setBusiForm(BusiForm busiForm) {
		this.busiForm = busiForm;
	}


	public BigDecimal getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(BigDecimal applyAmount) {
		this.applyAmount = applyAmount;
	}

	public Long getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Long applyDate) {
		this.applyDate = applyDate;
	}


	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}


	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}


	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getLoanApplyCode() {
		return loanApplyCode;
	}

	public void setLoanApplyCode(String loanApplyCode) {
		this.loanApplyCode = loanApplyCode;
	}


	public BigDecimal getQuasiLoanAmount() {
		return quasiLoanAmount;
	}

	public void setQuasiLoanAmount(BigDecimal quasiLoanAmount) {
		this.quasiLoanAmount = quasiLoanAmount;
	}

	public Long getQuasiLoanDate() {
		return quasiLoanDate;
	}

	public void setQuasiLoanDate(Long quasiLoanDate) {
		this.quasiLoanDate = quasiLoanDate;
	}

	public String getReceiveBankAccount() {
		return receiveBankAccount;
	}

	public void setReceiveBankAccount(String receiveBankAccount) {
		this.receiveBankAccount = receiveBankAccount;
	}

	public String getReceiveBankAccountName() {
		return receiveBankAccountName;
	}

	public void setReceiveBankAccountName(String receiveBankAccountName) {
		this.receiveBankAccountName = receiveBankAccountName;
	}

	public String getReceiveBankName() {
		return receiveBankName;
	}

	public void setReceiveBankName(String receiveBankName) {
		this.receiveBankName = receiveBankName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<LoanRecord> getLoanRecords() {
		return loanRecords;
	}

	public void setLoanRecords(Set<LoanRecord> loanRecords) {
		this.loanRecords = loanRecords;
	}


	public String getCaseApplyId() {
		return caseApplyId;
	}


	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}

	

}
