package com.zdsoft.finance.finance.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.finance.entity.RequestFundsDetail;
import com.zdsoft.framework.core.common.util.ObjectHelper;

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
public class RequestFundsDetailVo extends BaseVo<RequestFundsDetail> {

	private static final long serialVersionUID = 1L;

	/**
	 * 请款id
	 */
	private String reqFundsId;

	/**
	 * 案件id
	 */
	private String caseApplyId;

	/**
	 * 案件号
	 */
	private String caseApplyCode;
	
	/**
	 * 费用id
	 */
	private String feeId;

	/**
	 * 费用类型
	 */
	private String feeType;

	/**
	 * 费用类型名称
	 */
	private String feeTypeName;
	
	/**
	 * 收费项目
	 */
	private String feeItem;

	/**
	 * 收费项目名称
	 */
	private String feeItemName;
	
	/**
	 * 付费对象
	 */
	private String payObjectId;

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
	private BigDecimal reqBigAmount= BigDecimal.ZERO;

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

	public String getReqFundsId() {
		return reqFundsId;
	}

	public void setReqFundsId(String reqFundsId) {
		this.reqFundsId = reqFundsId;
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


	public BigDecimal getReqBigAmount() {
		return ObjectHelper.isEmpty(reqBigAmount)?BigDecimal.ZERO:reqBigAmount;
	}

	public void setReqBigAmount(BigDecimal reqBigAmount) {
		this.reqBigAmount = reqBigAmount;
	}

	public BigDecimal getReceivedAmount() {
		return ObjectHelper.isEmpty(receivedAmount)?BigDecimal.ZERO:receivedAmount;
	}

	public void setReceivedAmount(BigDecimal receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public BigDecimal getExpectedPayableAmount() {
		return ObjectHelper.isEmpty(expectedPayableAmount)?BigDecimal.ZERO:expectedPayableAmount;
	}

	public void setExpectedPayableAmount(BigDecimal expectedPayableAmount) {
		this.expectedPayableAmount = expectedPayableAmount;
	}

	public BigDecimal getPaidAmount() {
		return ObjectHelper.isEmpty(paidAmount)?BigDecimal.ZERO:paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public BigDecimal getReqFundsAmount() {
		return ObjectHelper.isEmpty(reqFundsAmount)?BigDecimal.ZERO:reqFundsAmount;
	}

	public void setReqFundsAmount(BigDecimal reqFundsAmount) {
		this.reqFundsAmount = reqFundsAmount;
	}

	public BigDecimal getPaymentAmount() {
		return ObjectHelper.isEmpty(paymentAmount)?BigDecimal.ZERO:paymentAmount;
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

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getFeeItem() {
		return feeItem;
	}

	public void setFeeItem(String feeItem) {
		this.feeItem = feeItem;
	}

	public String getPayObjectId() {
		return payObjectId;
	}

	public void setPayObjectId(String payObjectId) {
		this.payObjectId = payObjectId;
	}

	public String getPayObjectName() {
		return payObjectName;
	}

	public void setPayObjectName(String payObjectName) {
		this.payObjectName = payObjectName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
