package com.zdsoft.finance.finance.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: RepaymentReceipt.java
 * @ClassName: RepaymentReceipt
 * @Description: 还款-收款单
 * @author jincheng
 * @date 2017年2月17日 下午4:59:40
 * @version V1.0
 */
@Entity
@Table(name = "fin_receipt")
public class RepaymentReceipt extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 单据号
	 */
	@Column(length = 32)
	private String billCode;

	/**
	 * 案件id
	 */
	@Column(length = 32)
	private String caseApplyId;

	/**
	 * 实际收款日期
	 */
	@Column()
	private Long paidRepayDate;
	
	/**
	 * 缴款人
	 */
	@Column(length = 100)
	private String payer;

	/**
	 * 收据经手人
	 */
	@Column(length = 100)
	private String receipts;

	/**
	 * 是否提前结清还款
	 */
	@Column(name = "isRepayAdvance")
	@org.hibernate.annotations.Type(type = "true_false")
	private Boolean isRepayAdvance = false;

	/**
	 * 是否收取提前还款违约金
	 */
	@Column(name = "isRepayDamages")
	@org.hibernate.annotations.Type(type = "true_false")
	private Boolean isRepayDamages = false;

	/**
	 * 提前还款违约金比例(%)
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal breachProportion = BigDecimal.ZERO;

	/**
	 * 提前还款违约罚息
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal breachPenalty = BigDecimal.ZERO;

	/**
	 * 逾期计算方式
	 */
	@Column(length = 32)
	private String overdueWay;

	/**
	 * 收据号
	 */
	@Column(length = 32)
	private String colloectionCode;

	/**
	 * 0 草稿 1 审批中 2 通过 3 驳回 4 不通过
	 */
	@Column()
	private Integer status=0;
	
	/**
	 * 集团复核通过日期
	 */
	@Column()
	private Long  succDate;
	/**
	 * 是否已开票
	 */
	@Column(name = "isBill")
	@org.hibernate.annotations.Type(type = "true_false")
	private Boolean isBill = false;

	/**
	 * 结算方式
	 */
	@Column(length = 32)
	private String settlementMethod;

	/**
	 * 摘要
	 */
	@Column(length = 100)
	private String summary;

	/**
	 * 录款方式
	 */
	@Column(length = 32)
	private String recordMethod;

	/**
	 * 剩余款项分配
	 */
	@Column(length = 32)
	private String residual;

	/**
	 * 收款金额
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal collectionAmount = BigDecimal.ZERO;

	/**
	 * 备注
	 */
	@Column()
	private String remark;

	/**
	 * 收款单类型:1收款2预收款
	 */
	@Column()
	private Integer receiptType;
	

	@Transient
	private Boolean isEdit = false;

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public Long getPaidRepayDate() {
		return paidRepayDate;
	}

	public void setPaidRepayDate(Long paidRepayDate) {
		this.paidRepayDate = paidRepayDate;
	}

	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	public String getReceipts() {
		return receipts;
	}

	public void setReceipts(String receipts) {
		this.receipts = receipts;
	}

	public Boolean getIsRepayAdvance() {
		return isRepayAdvance;
	}

	public void setIsRepayAdvance(Boolean isRepayAdvance) {
		this.isRepayAdvance = isRepayAdvance;
	}

	public Boolean getIsRepayDamages() {
		return isRepayDamages;
	}

	public void setIsRepayDamages(Boolean isRepayDamages) {
		this.isRepayDamages = isRepayDamages;
	}

	public BigDecimal getBreachProportion() {
		return breachProportion;
	}

	public void setBreachProportion(BigDecimal breachProportion) {
		this.breachProportion = breachProportion;
	}

	public BigDecimal getBreachPenalty() {
		return breachPenalty;
	}

	public void setBreachPenalty(BigDecimal breachPenalty) {
		this.breachPenalty = breachPenalty;
	}

	public String getOverdueWay() {
		return overdueWay;
	}

	public void setOverdueWay(String overdueWay) {
		this.overdueWay = overdueWay;
	}

	public String getColloectionCode() {
		return colloectionCode;
	}

	public void setColloectionCode(String colloectionCode) {
		this.colloectionCode = colloectionCode;
	}

	public Boolean getIsBill() {
		return isBill;
	}

	public void setIsBill(Boolean isBill) {
		this.isBill = isBill;
	}

	public String getSettlementMethod() {
		return settlementMethod;
	}

	public void setSettlementMethod(String settlementMethod) {
		this.settlementMethod = settlementMethod;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getRecordMethod() {
		return recordMethod;
	}

	public void setRecordMethod(String recordMethod) {
		this.recordMethod = recordMethod;
	}

	public String getResidual() {
		return residual;
	}

	public void setResidual(String residual) {
		this.residual = residual;
	}

	public BigDecimal getCollectionAmount() {
		return collectionAmount;
	}

	public void setCollectionAmount(BigDecimal collectionAmount) {
		this.collectionAmount = collectionAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getReceiptType() {
		return receiptType;
	}

	public void setReceiptType(Integer receiptType) {
		this.receiptType = receiptType;
	}

	public Boolean getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(Boolean isEdit) {
		this.isEdit = isEdit;
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

	public Long getSuccDate() {
		return succDate;
	}

	public void setSuccDate(Long succDate) {
		this.succDate = succDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
