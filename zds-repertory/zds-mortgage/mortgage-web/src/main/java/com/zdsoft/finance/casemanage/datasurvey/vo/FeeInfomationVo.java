package com.zdsoft.finance.casemanage.datasurvey.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.casemanage.datasurvey.entity.FeeInfomation;
import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.common.utils.VoUtil;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 费用信息VO
 * 
 * @author laijun
 * @create 2017-01-07 19:11
 */
public class FeeInfomationVo extends BaseVo<FeeInfomation> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 结余
	 */
	private BigDecimal balanceAmount;
	
	/**
	 * 预计应收
	 */
	private BigDecimal expectedAmount;
	
	/**
	 * 预计应付
	 */
	private BigDecimal expectedPayableAmount;
	
	/**
	 * 收费项目
	 */
	private String feeItem;
	
	/**
	 * 费用项名称
	 */
	private String feeItemName;
	
	/**
	 * 费用类型名称
	 */
	private String feeTypeName;
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
	 * 费用类型
	 */
	private String feeType;
	/**
	 * 实付
	 */
	private BigDecimal paidAmount;
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
	
	/**
	 * 实收
	 */
	private BigDecimal receivedAmount;
	
	/**
	 * 案件
	 */
	private String caseApplyId;
	
	/**
	 * 序号（用于提交）
	 */
	private Integer serialNum;

	public FeeInfomationVo() {
		super();
	}

	public FeeInfomationVo(FeeInfomation po) {
		super(po,null,new String[]{"feeItem","feeObjectType","feeType","payObjectType"});
		if (ObjectHelper.isNotEmpty(po.getCaseApply())) {
			this.setCaseApplyId(po.getCaseApply().getId());
		}
	}
	
	public FeeInfomationVo(FeeInfomation po, Integer serialNumber) {
		super(po,null,new String[]{"feeItem","feeObjectType","feeType","payObjectType"});
		if (ObjectHelper.isNotEmpty(po.getCaseApply())) {
			this.setCaseApplyId(po.getCaseApply().getId());
		}
		this.setSerialNum(serialNumber);
	}

	public FeeInfomation toPO() {
		FeeInfomation po = new FeeInfomation();
		VoUtil.copyPoperties(this, po, true,new String[]{"id","feeItemName","feeTypeName"}, new String[]{"feeItem","feeType"});
		return po;
	}

	public BigDecimal getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public BigDecimal getExpectedAmount() {
		return expectedAmount;
	}

	public void setExpectedAmount(BigDecimal expectedAmount) {
		this.expectedAmount = expectedAmount;
	}

	public BigDecimal getExpectedPayableAmount() {
		return expectedPayableAmount;
	}

	public void setExpectedPayableAmount(BigDecimal expectedPayableAmount) {
		this.expectedPayableAmount = expectedPayableAmount;
	}

	public String getFeeItem() {
		return feeItem;
	}

	public void setFeeItem(String feeItem) {
		this.feeItem = feeItem;
	}

	public String getFeeObjectId() {
		return feeObjectId;
	}

	public void setFeeObjectId(String feeObjectId) {
		this.feeObjectId = feeObjectId;
	}

	public String getFeeObjectType() {
		return feeObjectType;
	}

	public void setFeeObjectType(String feeObjectType) {
		this.feeObjectType = feeObjectType;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
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

	public BigDecimal getReceivedAmount() {
		return receivedAmount;
	}

	public void setReceivedAmount(BigDecimal receivedAmount) {
		this.receivedAmount = receivedAmount;
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

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
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

	public String getFeeObjectTypeName() {
		return feeObjectTypeName;
	}

	public void setFeeObjectTypeName(String feeObjectTypeName) {
		this.feeObjectTypeName = feeObjectTypeName;
	}

	public String getPayObjectTypeName() {
		return payObjectTypeName;
	}

	public void setPayObjectTypeName(String payObjectTypeName) {
		this.payObjectTypeName = payObjectTypeName;
	}

	public Integer getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(Integer serialNum) {
		this.serialNum = serialNum;
	}

}
