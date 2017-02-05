package com.zdsoft.finance.casemanage.datasurvey.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:FeeInfomation.java
 * @Package:com.zdsoft.finance.casemanage.datasurvey.entity
 * @Description:费用信息
 * @author: laijun
 * @date:2017年1月10日 下午9:43:15
 * @version:v1.0
 */
@Entity
@Table(name = "case_fee_infomation")
public class FeeInfomation extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 结余
	 */
	@Column(precision = 18, scale = 2)
	private BigDecimal balanceAmount;
	/**
	 * 预计应收
	 */
	@Column(precision = 18, scale = 2)
	private BigDecimal expectedAmount;
	/**
	 * 预计应付
	 */
	@Column(precision = 18, scale = 2)
	private BigDecimal expectedPayableAmount;
	/**
	 * 收费项目
	 */
	@Column(length = 20)
	private String feeItem;
	
	/**
	 * 收费项目名称
	 */
	@Column(length = 20)
	private String feeItemName;
	
	/**
	 * 收费对象
	 */
	@Column(length = 32)
	private String feeObjectId;
	
	/**
	 * 收费对象名称
	 */
	@Column(length = 128)
	private String feeeObjectName;
	
	/**
	 * 收费对象类别
	 */
	@Column(length = 20)
	private String feeObjectType;
	
	/**
	 * 费用类型
	 */
	@Column(length = 32)
	private String feeType;
	
	/**
	 * 费用类型名称
	 */
	@Column(length = 32)
	private String feeTypeName;
	/**
	 * 实付
	 */
	@Column(precision = 18, scale = 2)
	private BigDecimal paidAmount;
	/**
	 * 付费对象
	 */
	@Column(length = 32)
	private String payObjectId;
	
	/**
	 * 付费对象名称
	 */
	@Column(length = 128)
	private String payObjectName;
	
	/**
	 * 付费对象类别
	 */
	@Column(length = 20)
	private String payObjectType;
	
	/**
	 * 实收
	 */
	@Column(precision = 18, scale = 2)
	private BigDecimal receivedAmount;
	
	/**
	 * 案件
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "caseApplyId", nullable = false)
	private CaseApply caseApply;

	public BigDecimal getBalanceAmount() {
		return this.balanceAmount;
	}

	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public BigDecimal getExpectedAmount() {
		return this.expectedAmount;
	}

	public void setExpectedAmount(BigDecimal expectedAmount) {
		this.expectedAmount = expectedAmount;
	}

	public BigDecimal getExpectedPayableAmount() {
		return this.expectedPayableAmount;
	}

	public void setExpectedPayableAmount(BigDecimal expectedPayableAmount) {
		this.expectedPayableAmount = expectedPayableAmount;
	}

	public String getFeeItem() {
		return this.feeItem;
	}

	public void setFeeItem(String feeItem) {
		this.feeItem = feeItem;
	}

	public String getFeeObjectId() {
		return this.feeObjectId;
	}

	public void setFeeObjectId(String feeObjectId) {
		this.feeObjectId = feeObjectId;
	}

	public String getFeeObjectType() {
		return this.feeObjectType;
	}

	public void setFeeObjectType(String feeObjectType) {
		this.feeObjectType = feeObjectType;
	}

	public String getFeeType() {
		return this.feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public BigDecimal getPaidAmount() {
		return this.paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public BigDecimal getReceivedAmount() {
		return this.receivedAmount;
	}

	public void setReceivedAmount(BigDecimal receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public CaseApply getCaseApply() {
		return caseApply;
	}

	public void setCaseApply(CaseApply caseApply) {
		this.caseApply = caseApply;
	}

	public String getPayObjectId() {
		return payObjectId;
	}

	public void setPayObjectId(String payObjectId) {
		this.payObjectId = payObjectId;
	}

	public String getPayObjectType() {
		return payObjectType;
	}

	public void setPayObjectType(String payObjectType) {
		this.payObjectType = payObjectType;
	}

	public String getFeeItemName() {
		return feeItemName;
	}

	public void setFeeItemName(String feeItemName) {
		this.feeItemName = feeItemName;
	}

	public String getFeeTypeName() {
		return feeTypeName;
	}

	public void setFeeTypeName(String feeTypeName) {
		this.feeTypeName = feeTypeName;
	}

	public String getFeeeObjectName() {
		return feeeObjectName;
	}

	public void setFeeeObjectName(String feeeObjectName) {
		this.feeeObjectName = feeeObjectName;
	}

	public String getPayObjectName() {
		return payObjectName;
	}

	public void setPayObjectName(String payObjectName) {
		this.payObjectName = payObjectName;
	}

	@Override
	public String toString() {
		return "FeeInfomation [balanceAmount=" + balanceAmount + ", expectedAmount=" + expectedAmount
				+ ", expectedPayableAmount=" + expectedPayableAmount + ", feeItem=" + feeItem + ", feeItemName="
				+ feeItemName + ", feeObjectId=" + feeObjectId + ", feeObjectType=" + feeObjectType + ", feeType="
				+ feeType + ", feeTypeName=" + feeTypeName + ", paidAmount=" + paidAmount + ", payObjectId="
				+ payObjectId + ", payObjectType=" + payObjectType + ", receivedAmount=" + receivedAmount
				+ ", caseApply=" + caseApply + "]";
	}

	public FeeInfomation() {
		super();
	}

}
