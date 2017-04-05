package com.zdsoft.finance.product.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.product.entity.FeeOption;

import java.math.BigDecimal;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: FeeOptionVo.java
 * @ClassName: FeeOptionVo
 * @Description: 费用项显示VO
 * @author gufeng
 * @date 2017年3月6日 上午11:18:15
 * @version V1.0
 */
public class FeeOptionVo extends BaseVo<FeeOption> {

	private static final long serialVersionUID = -5256271856016957865L;
	/**
	 * 产品id
	 */
	private String productId;
	/**
	 * 收费类型
	 */
	private String feeType;
	/**
	 * 收费类型名字
	 */
	private String feeTypeName;
	/**
	 * 收费项目
	 */
	private String feeItem;
	/**
	 * 收费项目名字
	 */
	private String feeItemName;
	/**
	 * 收款计算方式
	 */
	private String chargeCalculateWay;
	/**
	 * 名字
	 */
	private String chargeCalculateWayName;
	/**
	 * 收款金额
	 */
	private BigDecimal chargeAmount;
	/**
	 * 收款比例
	 */
	private Double chargeRatio;
	/**
	 * 付款计算方式
	 */
	private String payCalculateWay;
	/**
	 * 付款计算方式
	 */
	private String payCalculateWayName;
	/**
	 * 付款金额
	 */
	private BigDecimal payAmount;
	/**
	 * 付款比例
	 */
	private Double payRatio;
	/**
	 * 是否营业收入
	 */
	private Boolean isOperateIncome;
	/**
	 * 是否先请再付
	 */
	private Boolean isPayFirst;
	/**
	 * 支付条件
	 */
	private String paymentTerms;
	/**
	 * 支付条件名字
	 */
	private String paymentTermsName;
	/**
	 * 是否支佣
	 */
	private Boolean isPayCommission;
	/**
	 * 是否支佣name
	 */
	private String isPayCommissionName;
	/**
	 * 佣金支付条件
	 */
	private String payCondition;
	/**
	 * 佣金支付条件名字
	 */
	private String payConditionName;
	/**
	 * 支佣节点
	 */
	private String point;
	/**
	 * 是否停用
	 */
	private Boolean isEnable;
	/**
	 * 备注
	 */
	private String remark;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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

	public String getFeeItem() {
		return feeItem;
	}

	public void setFeeItem(String feeItem) {
		this.feeItem = feeItem;
	}

	public String getFeeItemName() {
		return feeItemName;
	}

	public void setFeeItemName(String feeItemName) {
		this.feeItemName = feeItemName;
	}

	public String getChargeCalculateWay() {
		return chargeCalculateWay;
	}

	public void setChargeCalculateWay(String chargeCalculateWay) {
		this.chargeCalculateWay = chargeCalculateWay;
	}

	public String getChargeCalculateWayName() {
		return chargeCalculateWayName;
	}

	public void setChargeCalculateWayName(String chargeCalculateWayName) {
		this.chargeCalculateWayName = chargeCalculateWayName;
	}

	public BigDecimal getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(BigDecimal chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	public Double getChargeRatio() {
		return chargeRatio;
	}

	public void setChargeRatio(Double chargeRatio) {
		this.chargeRatio = chargeRatio;
	}

	public String getPayCalculateWay() {
		return payCalculateWay;
	}

	public void setPayCalculateWay(String payCalculateWay) {
		this.payCalculateWay = payCalculateWay;
	}

	public String getPayCalculateWayName() {
		return payCalculateWayName;
	}

	public void setPayCalculateWayName(String payCalculateWayName) {
		this.payCalculateWayName = payCalculateWayName;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public Double getPayRatio() {
		return payRatio;
	}

	public void setPayRatio(Double payRatio) {
		this.payRatio = payRatio;
	}

	public Boolean getIsOperateIncome() {
		return isOperateIncome;
	}

	public void setIsOperateIncome(Boolean isOperateIncome) {
		this.isOperateIncome = isOperateIncome;
	}

	public Boolean getIsPayFirst() {
		return isPayFirst;
	}

	public void setIsPayFirst(Boolean isPayFirst) {
		this.isPayFirst = isPayFirst;
	}

	public String getPaymentTerms() {
		return paymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	public String getPaymentTermsName() {
		return paymentTermsName;
	}

	public void setPaymentTermsName(String paymentTermsName) {
		this.paymentTermsName = paymentTermsName;
	}

	public Boolean getIsPayCommission() {
		return isPayCommission;
	}

	public void setIsPayCommission(Boolean isPayCommission) {
		this.isPayCommission = isPayCommission;
	}

	public String getPayCondition() {
		return payCondition;
	}

	public void setPayCondition(String payCondition) {
		this.payCondition = payCondition;
	}

	public String getPayConditionName() {
		return payConditionName;
	}

	public void setPayConditionName(String payConditionName) {
		this.payConditionName = payConditionName;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsPayCommissionName() {
		return isPayCommissionName;
	}

	public void setIsPayCommissionName(String isPayCommissionName) {
		this.isPayCommissionName = isPayCommissionName;
	}

	public FeeOptionVo() {
	}

	public FeeOptionVo(FeeOption bean) {
		super(bean, null, new String[] { "feeType", "feeItem", "paymentTerms", "payCondition" });
		this.setChargeCalculateWayName(chargeCalculateWayValue(bean.getChargeCalculateWay()));
		this.setPayCalculateWayName(payCalculateWayValue(bean.getPayCalculateWay()));
		if (this.getIsPayCommission()) {
			this.setIsPayCommissionName("是");
		} else {
			this.setIsPayCommissionName("否");
		}
	}

	/**
	 * 
	 * @Title: payCalculateWayValue
	 * @Description: 付款计算方式
	 * @author gufeng
	 * @param payCalculateWay
	 *            付款计算方式
	 * @return 付款计算方式名字
	 */
	private String payCalculateWayValue(String payCalculateWay) {
		String str = "";
		switch (payCalculateWay) {
		case "payCalculateWay1":
			str = "固定";
			break;
		case "payCalculateWay2":
			str = "比例";
			break;
		default:
			break;
		}
		return str;
	}

	/**
	 * @Title: chargeCalculateWayValue
	 * @Description: 收款计算方式
	 * @author gufeng
	 * @param chargeCalculateWay
	 *            收款计算方式
	 * @return 收款计算方式名字
	 */
	private String chargeCalculateWayValue(String chargeCalculateWay) {
		String str = "";
		switch (chargeCalculateWay) {
		case "chargeCalculateWay1":
			str = "固定";
			break;
		case "chargeCalculateWay2":
			str = "比例";
			break;
		default:
			break;
		}
		return str;
	}

	public FeeOption toPO() {
		FeeOption bean = new FeeOption();
		return super.toPo(this, bean);
	}

}
