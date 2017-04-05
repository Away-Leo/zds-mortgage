package com.zdsoft.finance.casemanage.datasurvey.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.casemanage.datasurvey.entity.FeeInfomation;
import com.zdsoft.finance.casemanage.datasurvey.service.FeeInfomationService;
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
	 * 序号
	 */
	private String index;

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
	 * 费用id
	 */
	private String feeId;

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
	 * 案件id
	 */
	private String caseApplyId;

	/**
	 * 案件号
	 */
	private String caseApplyCode;

	/**
	 * 序号（用于提交）
	 */
	private Integer serialNum;

	/*************** 案件信息 ***************************/

	private String productTypeName;

	/**
	 * 资金来源
	 */
	private String capitalSource;

	/**
	 * 机构
	 */
	private String mechanismName;

	private String productTypeId;

	private String productSubtypeId;

	/**
	 * 子产品名称
	 */
	private String productSubtypeName;

	/**
	 * 案件状态
	 */
	private String caseApplyStatus;
	/**
	 * 结余
	 */
	private String balance;
	/**
	 * 案件申请日期
	 */
	private String applyDateStr;
	/**
	 * 主借人
	 */
	private String custtomerName;

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

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
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

	public String getFeeId() {
		return feeId;
	}

	public void setFeeId(String feeId) {
		this.feeId = feeId;
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

	public FeeInfomationVo() {
		super();
	}

	public FeeInfomationVo(FeeInfomation po) {
		super(po, null, new String[] { "feeObjectType", "feeType", "payObjectType" });
		if (ObjectHelper.isNotEmpty(po.getCaseApply())) {
			this.setCaseApplyId(po.getCaseApply().getId());
		}
		if (FeeInfomationService.JOIN_TYPE_OTHER_FULLCODE.equals(po.getFeeObjectType())) {
			this.setFeeObjectTypeName(FeeInfomationService.JOIN_TYPE_OTHER_NAME);
		}
		if (FeeInfomationService.JOIN_TYPE_OTHER_FULLCODE.equals(po.getPayObjectType())) {
			this.setPayObjectTypeName(FeeInfomationService.JOIN_TYPE_OTHER_NAME);
		}
	}

	public FeeInfomationVo(FeeInfomation po, Integer serialNumber) {
		super(po, null, new String[] { "feeObjectType", "feeType", "payObjectType" });
		if (ObjectHelper.isNotEmpty(po.getCaseApply())) {
			this.setCaseApplyId(po.getCaseApply().getId());
		}
		if (FeeInfomationService.JOIN_TYPE_OTHER_FULLCODE.equals(po.getPayObjectType())) {
			this.setPayObjectTypeName(FeeInfomationService.JOIN_TYPE_OTHER_NAME);
		}
		if (FeeInfomationService.JOIN_TYPE_OTHER_FULLCODE.equals(po.getFeeObjectType())) {
			this.setFeeObjectTypeName(FeeInfomationService.JOIN_TYPE_OTHER_NAME);
		}
		this.setSerialNum(serialNumber);
	}

	public FeeInfomation toPO() {
		FeeInfomation po = new FeeInfomation();
		VoUtil.copyPoperties(this, po, true, new String[] { "id", "feeTypeName" },
				new String[] { "feeType" });
		return po;
	}

	public BigDecimal getBalanceAmount() {
		return ObjectHelper.isNotEmpty(balanceAmount)?balanceAmount:BigDecimal.ZERO;
	}

	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public BigDecimal getExpectedAmount() {
		return ObjectHelper.isNotEmpty(expectedAmount)?expectedAmount:BigDecimal.ZERO;
	}

	public void setExpectedAmount(BigDecimal expectedAmount) {
		this.expectedAmount = expectedAmount;
	}

	public BigDecimal getExpectedPayableAmount() {
		return ObjectHelper.isNotEmpty(expectedPayableAmount)?expectedPayableAmount:BigDecimal.ZERO;
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
		return ObjectHelper.isNotEmpty(paidAmount)?paidAmount:BigDecimal.ZERO;
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
		return ObjectHelper.isNotEmpty(receivedAmount)?receivedAmount:BigDecimal.ZERO;
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

	public String getCaseApplyCode() {
		return caseApplyCode;
	}

	public void setCaseApplyCode(String caseApplyCode) {
		this.caseApplyCode = caseApplyCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

}
