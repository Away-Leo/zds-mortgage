package com.zdsoft.finance.product.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.product.entity.FeeOption;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

/**
 * 费用项显示VO
 * @author LiaoGuoWei
 * @create 2017-01-03 11:12
 **/
public class FeeOptionVo extends BaseVo<FeeOption> {

    /**
     * 产品编号
     */
    private String productCode;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 收费类型编号
     */
    private String chargeTypeCode;

    /**
     * 收费类型名称
     */
    private String chargeTypeName;

    /**
     * 收费项目编号
     */
    private String chargingItemCode;

    /**
     * 收费项目名称
     */
    private String chargingItemName;

    /**
     * 收款计算方式编号
     */
    private String collectionMethodCode;

    /**
     * 收款计算方式名称
     */
    private String collectionMethodName;

    /**
     * 收款金额
     */
    private BigDecimal collectionAmount;

    /**
     * 收款比例
     */
    private Double collectionRatio;

    /**
     * 付款计算方式编号
     */
    private String paymentMethodCode;
    /**
     * 付款计算方式名称
     */
    private String paymentMethodName;

    /**
     * 付款金额
     */
    private BigDecimal paymentAmount;

    /**
     * 付款比例
     */
    private Double paymentRatio;

    /**
     * 营业收支编号
     */
    private String isBusinessInAndOutCode;
    /**
     * 营业收支编号
     */
    private String isBusinessInAndOutName;
    /**
     * 先请再付编号
     */
    private String isRepayCode;
    /**
     * 先请再付名称
     */
    private String isRepayName;

    /**
     * 支付条件编号
     */
    private String payConditionCode;

    /**
     * 支付条件名称
     */
    private String payConditionName;

    /**
     * 是否支佣编号
     */
    private String isPayCommissionCode;
    /**
     * 是否支佣名称
     */
    private String isPayCommissionName;

    /**
     * 佣金支付条件编号
     */
    private String payCommiCondCode;
    /**
     * 佣金支付条件名称
     */
    private String payCommiCondName;

    /**
     * 支佣节点
     */
    private String payCommiNode;

    /**
     * 是否停用编号
     */
    private String isStopCode;
    /**
     * 是否停用名称
     */
    private String isStopName;

    /**
     * 备注
     */
    private String mo;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getChargeTypeCode() {
        return chargeTypeCode;
    }

    public void setChargeTypeCode(String chargeTypeCode) {
        this.chargeTypeCode = chargeTypeCode;
    }

    public String getChargeTypeName() {
        return chargeTypeName;
    }

    public void setChargeTypeName(String chargeTypeName) {
        this.chargeTypeName = chargeTypeName;
    }

    public String getChargingItemCode() {
        return chargingItemCode;
    }

    public void setChargingItemCode(String chargingItemCode) {
        this.chargingItemCode = chargingItemCode;
    }

    public String getChargingItemName() {
        return chargingItemName;
    }

    public void setChargingItemName(String chargingItemName) {
        this.chargingItemName = chargingItemName;
    }

    public String getCollectionMethodCode() {
        return collectionMethodCode;
    }

    public void setCollectionMethodCode(String collectionMethodCode) {
        this.collectionMethodCode = collectionMethodCode;
    }

    public String getCollectionMethodName() {
        return collectionMethodName;
    }

    public void setCollectionMethodName(String collectionMethodName) {
        this.collectionMethodName = collectionMethodName;
    }

    public BigDecimal getCollectionAmount() {
        return collectionAmount;
    }

    public void setCollectionAmount(BigDecimal collectionAmount) {
        this.collectionAmount = collectionAmount;
    }

    public Double getCollectionRatio() {
        return collectionRatio;
    }

    public void setCollectionRatio(Double collectionRatio) {
        this.collectionRatio = collectionRatio;
    }

    public String getPaymentMethodCode() {
        return paymentMethodCode;
    }

    public void setPaymentMethodCode(String paymentMethodCode) {
        this.paymentMethodCode = paymentMethodCode;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Double getPaymentRatio() {
        return paymentRatio;
    }

    public void setPaymentRatio(Double paymentRatio) {
        this.paymentRatio = paymentRatio;
    }

    public String getIsBusinessInAndOutCode() {
        return isBusinessInAndOutCode;
    }

    public void setIsBusinessInAndOutCode(String isBusinessInAndOutCode) {
        this.isBusinessInAndOutCode = isBusinessInAndOutCode;
    }

    public String getIsBusinessInAndOutName() {
        return isBusinessInAndOutName;
    }

    public void setIsBusinessInAndOutName(String isBusinessInAndOutName) {
        this.isBusinessInAndOutName = isBusinessInAndOutName;
    }

    public String getIsRepayCode() {
        return isRepayCode;
    }

    public void setIsRepayCode(String isRepayCode) {
        this.isRepayCode = isRepayCode;
    }

    public String getIsRepayName() {
        return isRepayName;
    }

    public void setIsRepayName(String isRepayName) {
        this.isRepayName = isRepayName;
    }

    public String getPayConditionCode() {
        return payConditionCode;
    }

    public void setPayConditionCode(String payConditionCode) {
        this.payConditionCode = payConditionCode;
    }

    public String getPayConditionName() {
        return payConditionName;
    }

    public void setPayConditionName(String payConditionName) {
        this.payConditionName = payConditionName;
    }

    public String getIsPayCommissionCode() {
        return isPayCommissionCode;
    }

    public void setIsPayCommissionCode(String isPayCommissionCode) {
        this.isPayCommissionCode = isPayCommissionCode;
    }

    public String getIsPayCommissionName() {
        return isPayCommissionName;
    }

    public void setIsPayCommissionName(String isPayCommissionName) {
        this.isPayCommissionName = isPayCommissionName;
    }

    public String getPayCommiCondCode() {
        return payCommiCondCode;
    }

    public void setPayCommiCondCode(String payCommiCondCode) {
        this.payCommiCondCode = payCommiCondCode;
    }

    public String getPayCommiCondName() {
        return payCommiCondName;
    }

    public void setPayCommiCondName(String payCommiCondName) {
        this.payCommiCondName = payCommiCondName;
    }

    public String getPayCommiNode() {
        return payCommiNode;
    }

    public void setPayCommiNode(String payCommiNode) {
        this.payCommiNode = payCommiNode;
    }

    public String getIsStopCode() {
        return isStopCode;
    }

    public void setIsStopCode(String isStopCode) {
        this.isStopCode = isStopCode;
    }

    public String getIsStopName() {
        return isStopName;
    }

    public void setIsStopName(String isStopName) {
        this.isStopName = isStopName;
    }

    public String getMo() {
        return mo;
    }

    public void setMo(String mo) {
        this.mo = mo;
    }

    public FeeOptionVo(){}
    public FeeOptionVo(FeeOption feeOption){
        BeanUtils.copyProperties(feeOption,this);
    }

    public FeeOption toPo(){
        FeeOption feeOption=new FeeOption();
        BeanUtils.copyProperties(this,feeOption);
        return feeOption;
    }
}
