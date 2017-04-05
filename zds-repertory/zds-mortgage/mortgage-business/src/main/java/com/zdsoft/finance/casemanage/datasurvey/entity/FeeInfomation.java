package com.zdsoft.finance.casemanage.datasurvey.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
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
	private BigDecimal balanceAmount = BigDecimal.ZERO;
	/**
	 * 预计应收
	 */
	@Column(precision = 18, scale = 2)
	private BigDecimal expectedAmount = BigDecimal.ZERO;
	/**
	 * 预计应付
	 */
	@Column(precision = 18, scale = 2)
	private BigDecimal expectedPayableAmount = BigDecimal.ZERO;
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
	@Column(length = 512)
	private String feeObjectId;

	/**
	 * 收费对象名称
	 */
	@Column(length = 512)
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
	private BigDecimal paidAmount = BigDecimal.ZERO;
	/**
	 * 付费对象
	 */
	@Column(length = 512)
	private String payObjectId;

	/**
	 * 付费对象名称
	 */
	@Column(length = 512)
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
	private BigDecimal receivedAmount = BigDecimal.ZERO;

	/**
	 * 案件
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "caseApplyId", nullable = false)
	private CaseApply caseApply;

	/*************** 案件信息 ***************************/

	@Transient
	private String productTypeName;

	/**
	 * 资金来源
	 */
	@Transient
	private String capitalSource;

	/**
	 * 机构
	 */
	@Transient
	private String mechanismName;

	@Transient
	private String productTypeId;

	@Transient
	private String productSubtypeId;

	/**
	 * 子产品名称
	 */
	@Transient
	private String productSubtypeName;

	/**
	 * 案件状态
	 */
	@Transient
	private String caseApplyStatus;
	/**
	 * 结余
	 */
	@Transient
	private String balance;
	/**
	 * 案件申请日期
	 */
	@Transient
	private String applyDateStr;
	/**
	 * 主借人
	 */
	@Transient
	private String custtomerName;
	@Transient
	private String caseApplyCode;

	public String getCaseApplyCode() {
		return caseApplyCode;
	}

	public void setCaseApplyCode(String caseApplyCode) {
		this.caseApplyCode = caseApplyCode;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public String getCapitalSource() {
		return capitalSource;
	}

	public void setCapitalSource(String capitalSource) {
		this.capitalSource = capitalSource;
	}

	public String getMechanismName() {
		return mechanismName;
	}

	public void setMechanismName(String mechanismName) {
		this.mechanismName = mechanismName;
	}

	public String getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getProductSubtypeId() {
		return productSubtypeId;
	}

	public void setProductSubtypeId(String productSubtypeId) {
		this.productSubtypeId = productSubtypeId;
	}

	public String getProductSubtypeName() {
		return productSubtypeName;
	}

	public void setProductSubtypeName(String productSubtypeName) {
		this.productSubtypeName = productSubtypeName;
	}

	public String getCaseApplyStatus() {
		return caseApplyStatus;
	}

	public void setCaseApplyStatus(String caseApplyStatus) {
		this.caseApplyStatus = caseApplyStatus;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getApplyDateStr() {
		return applyDateStr;
	}

	public void setApplyDateStr(String applyDateStr) {
		this.applyDateStr = applyDateStr;
	}

	public String getCusttomerName() {
		return custtomerName;
	}

	public void setCusttomerName(String custtomerName) {
		this.custtomerName = custtomerName;
	}

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
