package com.zdsoft.finance.afterloan.entity;

import java.math.BigDecimal;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title AfterLoanReview.java
 * @className AfterLoanReview
 * @description 贷后监控查询列表数据容器
 * @author LiaoGuoWei
 * @create 2017/3/8 10:05
 * @version V1.0
 **/
public class AfterLoanReview {

    /**
     * ID
     */
    private String id;

    /**
     * 案件编号
     */
    private String caseApplyCode;

    /**
     * 申请金额
     */
    private BigDecimal applyAmount = BigDecimal.ZERO;

    /**
     * 产品父类名称
     */
    private String productTypeName;

    /**
     * 产品子类名称
     */
    private String productSubtypeName;
    /**
     * 产品父类ID
     */
    private String productTypeId;

    /**
     * 产品子类ID
     */
    private String productSubtypeId;

    /**
     * 公司名称
     */
    private String mechanismName;

    /**
     * 案件阶段
     * model by liuhuan 修改为String 用SimpleCode维护
     */
    private String stage;

    /**
     * 案件状态(01.正常，02.退单)
     */
    private String caseApplyStatus;

    /**
     * 客户名称
     */
    private String customerName;
    /**
     * 客户ID
     */
    private String customerId;

    /**
     * 客户类型
     */
    private String customerType;

    /**
     * 联系电话
     */
    private String phoneNumber;

    /**
     * 导入时间
     */
    private Long monitorDate;

    /**
     * 汇法信息
     */
    private String huifa;

    /**
     * 工商
     */
    private String businessStatus;

    /**
     * 上次评估价
     */
    private BigDecimal lastPrice = BigDecimal.ZERO;

    /**
     * 本次评估价
     */
    private BigDecimal thisPrice = BigDecimal.ZERO;

    /**
     * 押品地址
     */
    private String collateralAddress;

    /**
     * 押品省
     */
    private String province;
    /**
     * 押品市
     */
    private String city;
    /**
     * 押品区
     */
    private String district;
    /**
     * 押品详细地址
     */
    private String mailingAddress;

    /**
     * 证件号
     */
    private String credentialNo;

    /**
     * 证件类型
     */
    private String credentialType;

    public String getCredentialNo() {
        return credentialNo;
    }

    public void setCredentialNo(String credentialNo) {
        this.credentialNo = credentialNo;
    }

    public String getCredentialType() {
        return credentialType;
    }

    public void setCredentialType(String credentialType) {
        this.credentialType = credentialType;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public String getCollateralAddress() {
        return collateralAddress;
    }

    public void setCollateralAddress(String collateralAddress) {
        this.collateralAddress = collateralAddress;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaseApplyCode() {
        return caseApplyCode;
    }

    public void setCaseApplyCode(String caseApplyCode) {
        this.caseApplyCode = caseApplyCode;
    }

    public BigDecimal getApplyAmount() {
        return applyAmount;
    }

    public void setApplyAmount(BigDecimal applyAmount) {
        this.applyAmount = applyAmount;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getProductSubtypeName() {
        return productSubtypeName;
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

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getCaseApplyStatus() {
        return caseApplyStatus;
    }

    public void setCaseApplyStatus(String caseApplyStatus) {
        this.caseApplyStatus = caseApplyStatus;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public Long getMonitorDate() {
        return monitorDate;
    }

    public void setMonitorDate(Long monitorDate) {
        this.monitorDate = monitorDate;
    }

    public String getHuifa() {
        return huifa;
    }

    public void setHuifa(String huifa) {
        this.huifa = huifa;
    }

    public String getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(String businessStatus) {
        this.businessStatus = businessStatus;
    }

    public BigDecimal getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(BigDecimal lastPrice) {
        this.lastPrice = lastPrice;
    }

    public BigDecimal getThisPrice() {
        return thisPrice;
    }

    public void setThisPrice(BigDecimal thisPrice) {
        this.thisPrice = thisPrice;
    }
}
