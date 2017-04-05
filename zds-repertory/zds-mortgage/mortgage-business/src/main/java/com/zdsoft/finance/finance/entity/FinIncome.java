package com.zdsoft.finance.finance.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: FinIncome.java
 * @ClassName: FinIncome
 * @Description: 收款单据
 * @author longwei
 * @date 2017年2月15日 上午9:31:54
 * @version V1.0
 */
@Entity
@Table(name = "fin_income")
public class FinIncome extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 流程代码
	 */
	public static final String proceessCode = "YWDM0012612"; 

	/**
	 * 案件id
	 */
	@Column(length = 32)
	private String caseApplyId;

	/**
	 * 案件号
	 */
	@Column(length = 32)
	private String caseApplyCode;

	/**
	 * 单据号
	 */
	@Column(length = 64)
	private String billCode;

	/**
	 * 付款方
	 */
	@Column(length = 32)
	private String payerId;

	/**
	 * 付款方信息
	 */
	@Column(length = 256)
	private String payerInfo;

	/**
	 * 结算方式
	 */
	@Column(length = 80)
	private String settlement;

	/**
	 * 收据号
	 */
	@Column(length = 50)
	private String receiptNo;

	/**
	 * 是否已开票
	 */
	@Column(length = 20)
	private String invoiceFlag;

	/**
	 * 缴款人
	 */
	@Column(length = 100)
	private String payerPersonName;

	/**
	 * 实收金额
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal payerAmount = BigDecimal.ZERO;

  /**
	 * 数据状态 0:草稿1:流程中 2:通过 3：不通过 4：退回
	 */
	@Column(length = 20)
	private String status;

	/**
	 * 摘要
	 */
	@Column(length = 100)
	private String summary;

	/**
	 * 备注
	 */
	@Column(length = 500)
	private String remark;

	/**
	 * 机构id
	 */
	@Column(length = 32)
	private String orgId;

	/**
	 * 机构名称
	 */
	@Column(length = 60)
	private String orgName;
	/**
	 * 收款发生日期
	 */
	@Column(length = 32)
	private Long receiptsDate;
	/**
	 * 付款人
	 */
	@Column(length = 32)
	private String payerCustomerName;

	@Transient
	private String msg;

	/**
	 * 是否提交
	 */
	@Transient
	private Boolean isSubmit = false;
	
	/**
	 * 业务表单id 启动工作流使用
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "busiFromId")
	private BusiForm busiForm;

	public Long getReceiptsDate() {
		return receiptsDate;
	}

	public void setReceiptsDate(Long receiptsDate) {
		this.receiptsDate = receiptsDate;
	}

	public String getPayerCustomerName() {
		return payerCustomerName;
	}

	public void setPayerCustomerName(String payerCustomerName) {
		this.payerCustomerName = payerCustomerName;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public BusiForm getBusiForm() {
		return busiForm;
	}

	public void setBusiForm(BusiForm busiForm) {
		this.busiForm = busiForm;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public String getPayerId() {
		return payerId;
	}

	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}

	public String getPayerInfo() {
		return payerInfo;
	}

	public void setPayerInfo(String payerInfo) {
		this.payerInfo = payerInfo;
	}

	public String getSettlement() {
		return settlement;
	}

	public void setSettlement(String settlement) {
		this.settlement = settlement;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public String getInvoiceFlag() {
		return invoiceFlag;
	}

	public void setInvoiceFlag(String invoiceFlag) {
		this.invoiceFlag = invoiceFlag;
	}

	public String getPayerPersonName() {
		return payerPersonName;
	}

	public void setPayerPersonName(String payerPersonName) {
		this.payerPersonName = payerPersonName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getPayerAmount() {
		return payerAmount;
	}

	public void setPayerAmount(BigDecimal payerAmount) {
		this.payerAmount = payerAmount;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Boolean getIsSubmit() {
		return isSubmit;
	}

	public void setIsSubmit(Boolean isSubmit) {
		this.isSubmit = isSubmit;
	}

}
