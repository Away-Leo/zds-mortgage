package com.zdsoft.finance.finance.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.common.utils.VoUtil;
import com.zdsoft.finance.finance.entity.FinIncome;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: FinIncomeVo.java
 * @ClassName: FinIncomeVo
 * @Description: 费用收费管理vo
 * @author longwei
 * @date 2017年2月15日 上午10:08:10
 * @version V1.0
 */
public class FinIncomeVo extends BaseVo<FinIncome> {

	private static final long serialVersionUID = 1L;

	/**
	 * 案件id
	 */
	private String caseApplyId;

	/**
	 * 案件号
	 */
	private String caseApplyCode;

	// 单据号
	private String billCode;

	// 付款方
	private String payerId;
	private String payerIdName;

	// 付款方信息
	private String payerInfo;

	// 结算方式
	private String settlement;
	private String settlementName;

	// 收据号
	private String receiptNo;

	// 是否已开票
	private String invoiceFlag;

	// 缴款人
	private String payerPersonName;

	// 状态
	private String status;

	// 摘要
	private String summary;

	// 备注
	private String remark;

	/**
	 * 收费金额
	 */
	private BigDecimal payerAmount;

	/**
	 * 机构id
	 */
	private String orgId;

	/**
	 * 机构名称
	 */
	private String orgName;

	/**
	 * 收款发生日期
	 */
	private Long receiptsDate;
	/**
	 * 付款人
	 */
	private String payerCustomerName;

	/**
	 * 是否提交
	 */
	private Boolean isSubmit = false;
	
	public String getPayerIdName() {
		return payerIdName;
	}

	public void setPayerIdName(String payerIdName) {
		this.payerIdName = payerIdName;
	}

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

	public String getSettlementName() {
		return settlementName;
	}

	public void setSettlementName(String settlementName) {
		this.settlementName = settlementName;
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

	public FinIncomeVo() {
		super();
	}

	public FinIncomeVo(FinIncome finIncome) {
		super(finIncome, null, new String[] {"payerId"});
	}

	public FinIncomeVo(FinIncome finIncome, String[] args, String[] simpleArgs) {
		VoUtil.copyPoperties(finIncome, this, false, args, simpleArgs);
	}

	public FinIncome toPO() {
		FinIncome finIncome = new FinIncome();
		super.toPo(this, finIncome);
		return finIncome;
	}
}
