package com.zdsoft.finance.finance.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.finance.entity.FinIncomeDetail;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: FinIncomeDetailVo.java
 * @ClassName: FinIncomeDetailVo
 * @Description: 收款单明细Vo
 * @author longwei
 * @date 2017年2月23日 上午11:25:40
 * @version V1.0
 */
public class FinIncomeDetailVo extends BaseVo<FinIncomeDetail> {

	/**
	 * 描述
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 收款单id
	 */
	private String finIncomeId;
	
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
	 * 收款主体
	 */
	private String incomeSubject;
	
	/**
	 * 收款主体姓名
	 */
	private String incomeSubjectText;
	
	/**
	 * 应收金额
	 */
	private BigDecimal expectedAmount;
	
	/**
	 * 已收金额
	 */
	private BigDecimal receivedAmount;
	
	/**
	 * 预计应付
	 */
	private BigDecimal expectedPayableAmount ;
	
	
	/**
	 * 实收金额
	 */
	private BigDecimal paidAmount;
	
	/**
	 * 收费对象
	 */
	private String feeObjectId;

	/**
	 * 收费对象名称
	 */
	private String feeeObjectName;

	/**
	 * 收费对象类别
	 */
	private String feeObjectType;

	/**
	 * 收费对象类别名称
	 */
	private String feeObjectTypeName;

	/**
	 * 付费对象
	 */
	private String payObjectId;

	/**
	 * 付费对象名称
	 */
	private String payObjectName;

	/**
	 * 付费对象类别
	 */
	private String payObjectType;

	/**
	 * 付费对象类别名称
	 */
	private String payObjectTypeName;
	
	public String getIncomeSubjectText() {
		return incomeSubjectText;
	}

	public void setIncomeSubjectText(String incomeSubjectText) {
		this.incomeSubjectText = incomeSubjectText;
	}

	public String getFinIncomeId() {
		return finIncomeId;
	}

	public void setFinIncomeId(String finIncomeId) {
		this.finIncomeId = finIncomeId;
	}

	public String getIncomeSubject() {
		return incomeSubject;
	}

	public void setIncomeSubject(String incomeSubject) {
		this.incomeSubject = incomeSubject;
	}

	public BigDecimal getPaidAmount() {
		return ObjectHelper.isNotEmpty(paidAmount)?paidAmount:BigDecimal.ZERO;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFeeItem() {
		return feeItem;
	}

	public void setFeeItem(String feeItem) {
		this.feeItem = feeItem;
	}

	public BigDecimal getExpectedAmount() {
		return ObjectHelper.isNotEmpty(expectedAmount)?expectedAmount:BigDecimal.ZERO;
	}

	public void setExpectedAmount(BigDecimal expectedAmount) {
		this.expectedAmount = expectedAmount;
	}

	public BigDecimal getReceivedAmount() {
		return ObjectHelper.isNotEmpty(receivedAmount)?receivedAmount:BigDecimal.ZERO;
	}

	public void setReceivedAmount(BigDecimal receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
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

	public String getFeeId() {
		return feeId;
	}

	public void setFeeId(String feeId) {
		this.feeId = feeId;
	}

	public BigDecimal getExpectedPayableAmount() {
		return ObjectHelper.isNotEmpty(expectedPayableAmount)?expectedPayableAmount:BigDecimal.ZERO;
	}

	public void setExpectedPayableAmount(BigDecimal expectedPayableAmount) {
		this.expectedPayableAmount = expectedPayableAmount;
	}

	public String getFeeObjectId() {
		return feeObjectId;
	}

	public void setFeeObjectId(String feeObjectId) {
		this.feeObjectId = feeObjectId;
	}

	public String getFeeeObjectName() {
		return feeeObjectName;
	}

	public void setFeeeObjectName(String feeeObjectName) {
		this.feeeObjectName = feeeObjectName;
	}

	public String getFeeObjectType() {
		return feeObjectType;
	}

	public void setFeeObjectType(String feeObjectType) {
		this.feeObjectType = feeObjectType;
	}

	public String getFeeObjectTypeName() {
		return feeObjectTypeName;
	}

	public void setFeeObjectTypeName(String feeObjectTypeName) {
		this.feeObjectTypeName = feeObjectTypeName;
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

	public String getPayObjectType() {
		return payObjectType;
	}

	public void setPayObjectType(String payObjectType) {
		this.payObjectType = payObjectType;
	}

	public String getPayObjectTypeName() {
		return payObjectTypeName;
	}

	public void setPayObjectTypeName(String payObjectTypeName) {
		this.payObjectTypeName = payObjectTypeName;
	}

	public FinIncomeDetailVo() {
	}

	public FinIncomeDetailVo(FinIncomeDetail finIncomeDetail) {
		super(finIncomeDetail);
	}

	public FinIncomeDetail toPO() {
		FinIncomeDetail finIncomeDetail = new FinIncomeDetail();
		super.toPo(this, finIncomeDetail);
		return finIncomeDetail;
	}
}
