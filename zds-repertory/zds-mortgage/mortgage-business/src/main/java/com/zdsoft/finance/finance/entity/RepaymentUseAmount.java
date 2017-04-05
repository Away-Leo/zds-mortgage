package com.zdsoft.finance.finance.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: RepaymentUseAmount.java
 * @ClassName: RepaymentUseAmount
 * @Description: 收款单资金转供款
 * @author jincheng
 * @date 2017年3月21日 下午10:14:23
 * @version V1.0
 */
@Entity
@Table(name = "fin_receipt_use_amount")
public class RepaymentUseAmount extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 案件id
	 */
	@Column(length = 32)
	private String caseApplyId;

	/**
	 * 收款单id
	 */
	@Column(length = 32)
	private String receiptId;

	/**
	 * 资金id
	 */
	@Column(length = 32)
	private String feeAmountId;
	
	/**
	 * 费用金额
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal feeAmount=BigDecimal.ZERO;

	/**
	 * 资金类型 YWDM009404：预收款 YWDM009403:保证金
	 */
	@Column
	private String amountType;
	
	/**
	 * 状态 0草稿 1通过 
	 */
	@Column
	private Integer status=0;

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public String getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}

	public String getFeeAmountId() {
		return feeAmountId;
	}

	public void setFeeAmountId(String feeAmountId) {
		this.feeAmountId = feeAmountId;
	}

	public BigDecimal getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(BigDecimal feeAmount) {
		this.feeAmount = feeAmount;
	}

	public String getAmountType() {
		return amountType;
	}

	public void setAmountType(String amountType) {
		this.amountType = amountType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
