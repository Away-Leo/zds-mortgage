package com.zdsoft.finance.finance.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.finance.entity.RepaymentReceipt;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: RepaymentReceiptVo.java
 * @ClassName: RepaymentReceiptVo
 * @Description: 还款-收款单Vo
 * @author jincheng
 * @date 2017年2月23日 下午3:27:37
 * @version V1.0
 */
public class RepaymentReceiptVo extends BaseVo<RepaymentReceipt> {

	private static final long serialVersionUID = 1L;

	/**
	 * 单据号
	 */
	private String billCode;

	/**
	 * 案件id
	 */
	private String caseApplyId;

	/**
	 * 实际收款日期
	 */
	private Long paidRepayDate;
	
	/**
	 * 缴款人
	 */
	private String payer;

	/**
	 * 收据经手人
	 */
	private String receipts;

	/**
	 * 是否提前结清还款
	 */
	private Boolean isRepayAdvance = false;

	/**
	 * 是否收取提前还款违约金
	 */
	private Boolean isRepayDamages = false;
	
	/**
	 * 提前还款违约金比例(%)
	 */
	private BigDecimal breachProportion ;
	
	/**
	 * 提前还款违约罚息
	 */
	private BigDecimal breachPenalty  ;
	

	/**
	 * 收据号
	 */
	private String colloectionCode;

	/**
	 * 是否已开票
	 */
	private Boolean isBill = false;
	
	/**
	 * 集团复核通过日期
	 */
	private Long  succDate;

	/**
	 * 结算方式
	 */
	private String settlementMethod="B";
	
	private String settlementMethodName;
	

	/**
	 * 摘要
	 */
	private String summary;
	/**
	 * 逾期计算方式
	 */
	private String overdueWay;
	private String overdueWayName;

	/**
	 * 录款方式
	 */
	private String recordMethod;
	private String recordMethodName;

	/**
	 * 剩余款项分配
	 */
	private String residual;
	private String residualName;

	/**
	 * 收款金额
	 */
	private BigDecimal collectionAmount;

	/**
	 * 收款单类型:1收款2预收款
	 */
	private Integer receiptType;
	
	/**
	 * 0 草稿 1 审批中 2 通过 3 驳回 4 不通过
	 */
	private Integer status;
	
	/**
	 * 操作类型:1待使用 2占用中 3已使用 4废弃
	 */
	private Integer operType;
	
	/**
	 * 备注
	 */
	private String remark;

	/*************** 案件信息 ***************************/

	private String caseApplyCode;

	private String productTypeName;

	private String capitalSource;

	private String mechanismName;

	private String productTypeId;
	
	private String productSubtypeId;
	
	private String productSubtypeName;

	
	public String getOverdueWayName() {
		return overdueWayName;
	}

	public void setOverdueWayName(String overdueWayName) {
		this.overdueWayName = overdueWayName;
	}

	public String getRecordMethodName() {
		return recordMethodName;
	}

	public void setRecordMethodName(String recordMethodName) {
		this.recordMethodName = recordMethodName;
	}

	public String getResidualName() {
		return residualName;
	}

	public void setResidualName(String residualName) {
		this.residualName = residualName;
	}

	public String getSettlementMethodName() {
		return settlementMethodName;
	}

	public void setSettlementMethodName(String settlementMethodName) {
		this.settlementMethodName = settlementMethodName;
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
 
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setProductSubtypeName(String productSubtypeName) {
		this.productSubtypeName = productSubtypeName;
	}

	public String getMechanismName() {
		return mechanismName;
	}

	public void setMechanismName(String mechanismName) {
		this.mechanismName = mechanismName;
	}

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

	public Integer getReceiptType() {
		return receiptType;
	}

	public void setReceiptType(Integer receiptType) {
		this.receiptType = receiptType;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getOperType() {
		return operType;
	}

	public void setOperType(Integer operType) {
		this.operType = operType;
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

	public RepaymentReceiptVo() {
		super();
	}

	public RepaymentReceiptVo(RepaymentReceipt entity) {
		super(entity, null, new String[] {"overdueWay","recordMethod","residual" });
	}

	public RepaymentReceipt toPo() throws Exception {
		RepaymentReceipt entity = new RepaymentReceipt();
		return super.toPo(this, entity);
	}

}
