package com.zdsoft.finance.casemanage.receivablePlan.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 还款计划
 * 
 * @author wangrongwei
 * @create 2017-01-05 20:11
 **/
@Entity
@Table(name = "case_receivable_plan")
public class ReceivablePlan extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 期数
	 */
	@Column(nullable = false)
	private Integer periodsNo;

	/**
	 * 应还日期
	 */
	@Column(nullable = false)
	private Long repaymentDate;

	/**
	 * 本金
	 */
	@Column(nullable = false, precision = 18, scale = 6)
	private BigDecimal repaymentAmount = new BigDecimal(0);

	/**
	 * 利息
	 */
	@Column(nullable = false, precision = 18, scale = 6)
	private BigDecimal interestAmount = new BigDecimal(0);
	
	/**
	 * 罚息
	 */
	@Column(nullable = false, precision = 18, scale = 6)
	private BigDecimal affirmPenalty = new BigDecimal(0);
	
	/**
	 * 服务费
	 */
	@Column(nullable = false, precision = 18, scale = 6)
	private BigDecimal serviceChange = new BigDecimal(0);
	
	/**
	 * 剩余本金
	 */
	@Column(nullable = false, precision = 18, scale = 6)
	private BigDecimal surplusRepaymentAmount;

	/**
	 * 还款计划基本信息ID
	 */
	@Column(length = 32)
	private String receivableInfoId;
	
	/**
	 * 放款ID
	 */
	@Column(length = 32)
	private String loanApplyId;

	public Integer getPeriodsNo() {
		return periodsNo;
	}

	public void setPeriodsNo(Integer periodsNo) {
		this.periodsNo = periodsNo;
	}

	public Long getRepaymentDate() {
		return repaymentDate;
	}

	public void setRepaymentDate(Long repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	public BigDecimal getRepaymentAmount() {
		return repaymentAmount;
	}

	public void setRepaymentAmount(BigDecimal repaymentAmount) {
		this.repaymentAmount = repaymentAmount;
	}

	public BigDecimal getInterestAmount() {
		return interestAmount;
	}

	public void setInterestAmount(BigDecimal interestAmount) {
		this.interestAmount = interestAmount;
	}

	public BigDecimal getServiceChange() {
		return serviceChange;
	}

	public void setServiceChange(BigDecimal serviceChange) {
		this.serviceChange = serviceChange;
	}

	public String getReceivableInfoId() {
		return receivableInfoId;
	}

	public void setReceivableInfoId(String receivableInfoId) {
		this.receivableInfoId = receivableInfoId;
	}

	public String getLoanApplyId() {
		return loanApplyId;
	}

	public void setLoanApplyId(String loanApplyId) {
		this.loanApplyId = loanApplyId;
	}

	public BigDecimal getAffirmPenalty() {
		return affirmPenalty;
	}

	public void setAffirmPenalty(BigDecimal affirmPenalty) {
		this.affirmPenalty = affirmPenalty;
	}

	public BigDecimal getSurplusRepaymentAmount() {
		return surplusRepaymentAmount;
	}

	public void setSurplusRepaymentAmount(BigDecimal surplusRepaymentAmount) {
		this.surplusRepaymentAmount = surplusRepaymentAmount;
	}
	
}
