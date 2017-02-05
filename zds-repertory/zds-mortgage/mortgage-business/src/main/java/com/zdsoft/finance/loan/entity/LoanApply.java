package com.zdsoft.finance.loan.entity;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 放款申请
 * 
 * @author laijun
 * @create 2017-01-05 20:11
 **/
@Entity
@Table(name = "loan_apply")
public class LoanApply extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 请款金额
	 */
	@Column(nullable = false, precision = 18, scale = 6)
	private BigDecimal applyAmount;
	/**
	 * 申请时间
	 */
	@Column(nullable = false)
	private Long applyDate;
	/**
	 * 贷款金额
	 */
	@Column(nullable = false, precision = 18, scale = 6)
	private BigDecimal applyLoanAmount;
	/**
	 * 批次号
	 */
	@Column(nullable = false, length = 32)
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
	 * 合同Id
	 */
	@Column(nullable = false, length = 32)
	private String contractId;
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
	 * 预计回收时间
	 */
	@Column(nullable = false)
	private Long expectedRecyclingDate;
	/**
	 * 放款金额
	 */
	@Column(nullable = false, precision = 18, scale = 6)
	private BigDecimal loanAmount;
	/**
	 * 申请单
	 */
	@Column(nullable = false, length = 32)
	private String loanApplyCode;
	/**
	 * 贷款时间
	 */
	@Column(nullable = false)
	private Long loanDate;
	/**
	 * 贷款天数
	 */
	@Column(nullable = false)
	private Integer loanDays;
	/**
	 * 贷款收款金额
	 */
	@Column(nullable = false, precision = 18, scale = 6)
	private BigDecimal loanReceiveAmount;
	/**
	 * 收益率
	 */
	@Column(nullable = false, precision = 18, scale = 6)
	private BigDecimal productRate;
	/**
	 * 贷款类型
	 */
	@Column(nullable = false, length = 32)
	private String productType;
	/**
	 * 准放款金额
	 */
	@Column(nullable = false, precision = 18, scale = 6)
	private BigDecimal quasiLoanAmount;
	/**
	 * 准放款时间
	 */
	@Column(nullable = false)
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
	 * 放款状态
	 */
	@Column(nullable = false, length = 20)
	private String status;
	/**
	 * 放款操作记录表
	 */
	@OneToMany(mappedBy = "loanApply", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<LoanRecord> loanRecords;

	public LoanApply() {
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

	public BigDecimal getApplyLoanAmount() {
		return applyLoanAmount;
	}

	public void setApplyLoanAmount(BigDecimal applyLoanAmount) {
		this.applyLoanAmount = applyLoanAmount;
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

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
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

	public Long getExpectedRecyclingDate() {
		return expectedRecyclingDate;
	}

	public void setExpectedRecyclingDate(Long expectedRecyclingDate) {
		this.expectedRecyclingDate = expectedRecyclingDate;
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

	public Long getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Long loanDate) {
		this.loanDate = loanDate;
	}

	public Integer getLoanDays() {
		return loanDays;
	}

	public void setLoanDays(Integer loanDays) {
		this.loanDays = loanDays;
	}

	public BigDecimal getLoanReceiveAmount() {
		return loanReceiveAmount;
	}

	public void setLoanReceiveAmount(BigDecimal loanReceiveAmount) {
		this.loanReceiveAmount = loanReceiveAmount;
	}

	public BigDecimal getProductRate() {
		return productRate;
	}

	public void setProductRate(BigDecimal productRate) {
		this.productRate = productRate;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
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

}
