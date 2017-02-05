package com.zdsoft.finance.contract.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/*
 * 案件合同表
 * @author huangdongkui
 * @date 2017-1-16
 * */
@Entity
@Table(name = "coact_case_contract")
public class CoactCaseContract extends BaseEntity {
	private static final long serialVersionUID = -3768555669980589668L;



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCaseContractNo() {
		return caseContractNo;
	}

	public void setCaseContractNo(String caseContractNo) {
		this.caseContractNo = caseContractNo;
	}

	public String getContractTplId() {
		return contractTplId;
	}

	public void setContractTplId(String contractTplId) {
		this.contractTplId = contractTplId;
	}

	public String getCaseId() {
		return caseApplyId;
	}

	public void setCaseId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public String getSuppleementId() {
		return suppleementId;
	}

	public void setSuppleementId(String suppleementId) {
		this.suppleementId = suppleementId;
	}

	public Long getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(Long contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public Long getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(Long contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public Long getContractClosedDate() {
		return contractClosedDate;
	}

	public void setContractClosedDate(Long contractClosedDate) {
		this.contractClosedDate = contractClosedDate;
	}

	public BigDecimal getCaseAmount() {
		return caseAmount;
	}

	public void setCaseAmount(BigDecimal caseAmount) {
		this.caseAmount = caseAmount;
	}

	public String getCaseDeadline() {
		return caseDeadline;
	}

	public void setCaseDeadline(String caseDeadline) {
		this.caseDeadline = caseDeadline;
	}

	public BigDecimal getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(BigDecimal contractAmount) {
		this.contractAmount = contractAmount;
	}

	public String getCaseDeadlineUnit() {
		return caseDeadlineUnit;
	}

	public void setCaseDeadlineUnit(String caseDeadlineUnit) {
		this.caseDeadlineUnit = caseDeadlineUnit;
	}

	public BigDecimal getCaseRate() {
		return caseRate;
	}

	public void setCaseRate(BigDecimal caseRate) {
		this.caseRate = caseRate;
	}

	public BigDecimal getNominalRate() {
		return nominalRate;
	}

	public void setNominalRate(BigDecimal nominalRate) {
		this.nominalRate = nominalRate;
	}

	public BigDecimal getRealRate() {
		return realRate;
	}

	public void setRealRate(BigDecimal realRate) {
		this.realRate = realRate;
	}

	public BigDecimal getLoanTotalAmount() {
		return loanTotalAmount;
	}

	public void setLoanTotalAmount(BigDecimal loanTotalAmount) {
		this.loanTotalAmount = loanTotalAmount;
	}

	public Long getLoanEndDate() {
		return loanEndDate;
	}

	public void setLoanEndDate(Long loanEndDate) {
		this.loanEndDate = loanEndDate;
	}

	public Long getLoanStartDate() {
		return loanStartDate;
	}

	public void setLoanStartDate(Long loanStartDate) {
		this.loanStartDate = loanStartDate;
	}

	public String getRepayMethod() {
		return repayMethod;
	}

	public void setRepayMethod(String repayMethod) {
		this.repayMethod = repayMethod;
	}

	public Integer getRepaymentDate() {
		return repaymentDate;
	}

	public void setRepaymentDate(Integer repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	public String getSelectFixRepaymentDate() {
		return selectFixRepaymentDate;
	}

	public void setSelectFixRepaymentDate(String selectFixRepaymentDate) {
		this.selectFixRepaymentDate = selectFixRepaymentDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * ID
	 */
	@Column(length = 32)
	private String id;
	/**
	 * 案件合同编号
	 */
	@Column(length = 32)
	private String caseContractNo;

	/**
	 * 合同模板ID
	 */
	@Column(length = 32)
	private String contractTplId;

	/**
	 * 案件ID
	 */
	@Column(length = 32)
	private String caseApplyId;

	/**
	 * 补充信息ID
	 */
	@Column(length = 32)
	private String suppleementId;

	/**
	 * 合同开始日期
	 */
	@Column
	private Long contractStartDate;

	/**
	 * 合同结束日期
	 */
	@Column
	private Long contractEndDate;

	/**
	 * 合同关闭日期
	 */
	@Column
	private Long contractClosedDate;

	/**
	 * 案件金额
	 */
	@Column(precision = 18, scale = 2)
	private BigDecimal caseAmount;

	/**
	 * 案件金额期限
	 */
	@Column(length = 20)
	private String caseDeadline;

	/**
	 * 合同金额
	 */
	@Column(precision = 18, scale = 2)
	private BigDecimal contractAmount;

	/**
	 * 案件期限单位
	 */
	@Column(length = 32)
	private String caseDeadlineUnit;

	/**
	 * 案件利率
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal caseRate;

	/**
	 * 名义利率
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal nominalRate;

	/**
	 * 实际利率
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal realRate;

	/**
	 * 总放款金额
	 */
	@Column(precision = 18, scale = 2)
	private BigDecimal loanTotalAmount;

	/**
	 * 放款结束时间
	 */
	@Column
	private Long loanEndDate;

	/**
	 * 放款开始时间
	 */
	@Column
	private Long loanStartDate;

	/**
	 * 还款方式
	 */
	@Column(length = 20)
	private String repayMethod;

	/**
	 * 每期还款日
	 */
	private Integer repaymentDate;

	/**
	 * 每期还款日方式
	 */
	@Column(length = 20)
	private String selectFixRepaymentDate;

	/**
	 * 备注
	 */
	@Column(length = 3000)
	private String remark;

}
