package com.zdsoft.finance.finance.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.finance.entity.RequestCommissionDetail;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: RequestFundsDetailVo.java
 * @ClassName: RequestFundsDetailVo
 * @Description: 收费请款-费用明细Vo
 * @author jincheng
 * @date 2017年2月8日 下午4:57:49
 * @version V1.0
 */
public class RequestCommissionDetailVo extends BaseVo<RequestCommissionDetail> {

	private static final long serialVersionUID = 1L;

	/**
	 * 请款id
	 */
	private String reqCommissionId;

	/**
	 * 案件id
	 */
	private String caseApplyId;

	/**
	 * 案件号
	 */
	private String caseApplyCode;

	/**
	 * 请款类型
	 */
	private String feeId;

	/**
	 * 请款类型
	 */
	private String feeTypeName;

	/**
	 * 请款项目
	 */
	private String feeItemName;

	/**
	 * 付费对象名称
	 */
	private String payObjectName;

	/**
	 * 实收金额
	 */
	private BigDecimal receivedAmount = BigDecimal.ZERO;

	/**
	 * 预计应付
	 */
	private BigDecimal expectedPayableAmount = BigDecimal.ZERO;

	/**
	 * 累计已付
	 */
	private BigDecimal paidAmount = BigDecimal.ZERO;
	
	/**
	 * 请款最大额
	 */
	private BigDecimal reqBigAmount;

	/**
	 * 请款金额
	 */
	private BigDecimal reqFundsAmount = BigDecimal.ZERO;

	/**
	 * 付款金额
	 */
	private BigDecimal paymentAmount = BigDecimal.ZERO;
	
	/**
	 * 是否付款
	 */
	private Boolean isPayment=false;
	
	/**
	 * 实际付款日期
	 */
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

	public BigDecimal getReqBigAmount() {
		return reqBigAmount;
	}

	public void setReqBigAmount(BigDecimal reqBigAmount) {
		this.reqBigAmount = reqBigAmount;
	}

	public BigDecimal getReceivedAmount() {
		return receivedAmount;
	}

	public void setReceivedAmount(BigDecimal receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public BigDecimal getExpectedPayableAmount() {
		return expectedPayableAmount;
	}

	public void setExpectedPayableAmount(BigDecimal expectedPayableAmount) {
		this.expectedPayableAmount = expectedPayableAmount;
	}

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public BigDecimal getReqFundsAmount() {
		return reqFundsAmount;
	}

	public void setReqFundsAmount(BigDecimal reqFundsAmount) {
		this.reqFundsAmount = reqFundsAmount;
	}

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
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
