package com.zdsoft.finance.loan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;
/**
 * 放款操作记录
 * @author laijun
 * @create 2017-01-05 20:11
 **/
@Entity
@Table(name = "loan_record")
public class LoanRecord extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 操作时间
	 */
	private Long dealDate;
	/**
	 * 实际发生时间
	 */
	private Long dealRealDate;
	/**
	 * 操作类型
	 */
	@Column(length = 32)
	private String dealTypeName;
	/**
	 * 处理人
	 */
	@Column(length = 32)
	private String dealUser;
	/**
	 * 备注
	 */
	@Column(length = 500)
	private String mo;

	@ManyToOne
	@JoinColumn(name = "loanApplyId", nullable = false)
	private LoanApply loanApply;

	public LoanRecord() {
	}

	public Long getDealDate() {
		return this.dealDate;
	}

	public void setDealDate(Long dealDate) {
		this.dealDate = dealDate;
	}

	public Long getDealRealDate() {
		return this.dealRealDate;
	}

	public void setDealRealDate(Long dealRealDate) {
		this.dealRealDate = dealRealDate;
	}

	public String getDealTypeName() {
		return this.dealTypeName;
	}

	public void setDealTypeName(String dealTypeName) {
		this.dealTypeName = dealTypeName;
	}

	public String getDealUser() {
		return this.dealUser;
	}

	public void setDealUser(String dealUser) {
		this.dealUser = dealUser;
	}

	public String getMo() {
		return this.mo;
	}

	public void setMo(String mo) {
		this.mo = mo;
	}

	public LoanApply getLoanApply() {
		return this.loanApply;
	}

	public void setLoanApply(LoanApply loanApply) {
		this.loanApply = loanApply;
	}

}