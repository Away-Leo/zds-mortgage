package com.zdsoft.finance.loan.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;
/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: LoanRecord.java 
 * @ClassName: LoanRecord 
 * @Description: 放款操作记录实体
 * @author huangwei 
 * @date 2017年2月28日 下午2:08:19 
 * @version V1.0
 */
@Entity
@Table(name = "loan_record")
public class LoanRecord extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 操作类型
	 */
	@Column(length=32)
	public String operationType;
	/**
	 * 实际放款时间
	 */
	@Column
	public long actualDate;
	/**
	 * 放款金额
	 */
	@Column(precision = 18, scale = 6)
	public BigDecimal loanAmount = BigDecimal.ZERO;
	/**
	 * 备注
	 */
	@Column(length=512)
	public String remark;
	/**
	 * 操作人员
	 */
	@Column(length=512)
	public String operatorCode;
	/**
	 * 操作时间
	 */
	@Column
	public long operationDate;
	/**
	 * 放款申请
	 */
	@ManyToOne
	@JoinColumn(name = "loanApplyId", nullable = false)
	private LoanApply loanApply;
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public long getActualDate() {
		return actualDate;
	}
	public void setActualDate(long actualDate) {
		this.actualDate = actualDate;
	}
	public BigDecimal getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public LoanApply getLoanApply() {
		return loanApply;
	}
	public void setLoanApply(LoanApply loanApply) {
		this.loanApply = loanApply;
	}
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	public long getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(long operationDate) {
		this.operationDate = operationDate;
	}
	
	
}