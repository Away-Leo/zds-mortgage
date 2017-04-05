package com.zdsoft.finance.finance.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: RequestFundsDetail.java
 * @ClassName: RequestFundsDetail
 * @Description: 收费请款-费用明细
 * @author jincheng
 * @date 2017年2月16日 下午4:59:40
 * @version V1.0
 */
@Entity
@Table(name = "fin_request_commission_detail")
public class RequestCommissionDetail extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 请款id
	 */
	@Column(length = 32)
	private String reqCommissionId;

	/**
	 * 案件id
	 */
	@Column(length = 32)
	private String caseApplyId;

	/**
	 * 案件号
	 */
	@Column(length = 50)
	private String caseApplyCode;

	/**
	 * 请款类型
	 */
	@Column(length = 32)
	private String feeId;

	/**
	 * 请款类型
	 */
	@Column(length = 100)
	private String feeTypeName;

	/**
	 * 请款项目
	 */
	@Column(length = 100)
	private String feeItemName;

	/**
	 * 付费对象名称
	 */
	@Column(length = 128)
	private String payObjectName;

	/**
	 * 实收金额
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal receivedAmount = BigDecimal.ZERO;

	/**
	 * 预计应付
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal expectedPayableAmount = BigDecimal.ZERO;

	/**
	 * 累计已付
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal paidAmount = BigDecimal.ZERO;

	/**
	 * 请款金额
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal reqFundsAmount = BigDecimal.ZERO;

	/**
	 * 付款金额
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal paymentAmount = BigDecimal.ZERO;

	/**
	 * 是否付款
	 */
	@Column(name = "isPayment")
	@org.hibernate.annotations.Type(type = "true_false")
	private Boolean isPayment = false;

	/**
	 * 实际付款日期
	 */
	@Column
	private Long paymentDate;

	public String getReqCommissionId() {
		return reqCommissionId;
	}

	public void setReqCommissionId(String reqCommissionId) {
		this.reqCommissionId = reqCommissionId;
	}

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public String getCaseApplyCode() {
		return caseApplyCode;
	}

	public void setCaseApplyCode(String caseApplyCode) {
		this.caseApplyCode = caseApplyCode;
	}

	public String getFeeId() {
		return feeId;
	}

	public void setFeeId(String feeId) {
		this.feeId = feeId;
	}

	public String getFeeTypeName() {
		return feeTypeName;
	}

	public void setFeeTypeName(String feeTypeName) {
		this.feeTypeName = feeTypeName;
	}

	public String getFeeItemName() {
		return feeItemName;
	}

	public void setFeeItemName(String feeItemName) {
		this.feeItemName = feeItemName;
	}

	public String getPayObjectName() {
		return payObjectName;
	}

	public void setPayObjectName(String payObjectName) {
		this.payObjectName = payObjectName;
	}

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public BigDecimal getExpectedPayableAmount() {
		return expectedPayableAmount;
	}

	public void setExpectedPayableAmount(BigDecimal expectedPayableAmount) {
		this.expectedPayableAmount = expectedPayableAmount;
	}

	public BigDecimal getReceivedAmount() {
		return receivedAmount;
	}

	public void setReceivedAmount(BigDecimal receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public BigDecimal getReqFundsAmount() {
		return reqFundsAmount;
	}

	public void setReqFundsAmount(BigDecimal reqFundsAmount) {
		this.reqFundsAmount = reqFundsAmount;
	}

	public Boolean getIsPayment() {
		return isPayment;
	}

	public void setIsPayment(Boolean isPayment) {
		this.isPayment = isPayment;
	}

	public Long getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Long paymentDate) {
		this.paymentDate = paymentDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
