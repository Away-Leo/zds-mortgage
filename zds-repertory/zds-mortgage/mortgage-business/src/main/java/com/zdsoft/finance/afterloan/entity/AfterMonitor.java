package com.zdsoft.finance.afterloan.entity;

import java.math.BigDecimal;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title AfterMonitor.java
 * @className AfterMonitor
 * @description 贷后监控列表实体
 * @author LiaoGuoWei
 * @create 2017/3/6 15:19
 * @version V1.0
 **/
public class AfterMonitor {

    /**
     * 案件ID
     */
    private String id;

    /**
     * 客户ID
     */
    private String customerId;

    /**
     * 申请金额
     */
    private BigDecimal applyAmount = BigDecimal.ZERO;

    /**
     * 案件编号
     */
    private String caseApplyCode;

    /**
     * 机构名称
     */
    private String mechanismName;

    /**
     * 主借人
     */
    private String mainBorrower;

    /**
     * 电话
     */
    private String phoneNumber;

    /**
     * 产品大类名称
     */
    private String productTypeName;

    /**
     * 产品大类ID
     */
    private String productTypeId;

    /**
     * 产品子类名称
     */
    private String productSubtypeName;

    /**
     * 产品子类ID
     */
    private String productSubtypeId;

    /**
     * 楼龄
     */
    private String floorAge;

    /**
     * 逾期金额
     */
    private BigDecimal overdueAmount = BigDecimal.ZERO;

    /**
     * 逾期开始日期
     */
    private Long overdueDate;

    /**
     * 逾期天数
     */
    private Long overdueDay;

    /**
     * 合同金额
     */
    private BigDecimal contractAmount = BigDecimal.ZERO;

    /**
     * 上次重估时间
     */
    private Long lastMonitorDate;

    /**
     * 案件状态
     */
    private String stage;

    /**
     * 是否出险
     */
    private String isLoss;


    public String getIsLoss() {
        return isLoss;
    }

    public void setIsLoss(String isLoss) {
        this.isLoss = isLoss;
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

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getFloorAge() {
        return floorAge;
    }

    public void setFloorAge(String floorAge) {
        this.floorAge = floorAge;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getApplyAmount() {
        return applyAmount;
    }

    public void setApplyAmount(BigDecimal applyAmount) {
        this.applyAmount = applyAmount;
    }

    public String getCaseApplyCode() {
        return caseApplyCode;
    }

    public void setCaseApplyCode(String caseApplyCode) {
        this.caseApplyCode = caseApplyCode;
    }

    public String getMechanismName() {
        return mechanismName;
    }

    public void setMechanismName(String mechanismName) {
        this.mechanismName = mechanismName;
    }

    public String getMainBorrower() {
        return mainBorrower;
    }

    public void setMainBorrower(String mainBorrower) {
        this.mainBorrower = mainBorrower;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public BigDecimal getOverdueAmount() {
        return overdueAmount;
    }

    public void setOverdueAmount(BigDecimal overdueAmount) {
        this.overdueAmount = overdueAmount;
    }

    public Long getOverdueDate() {
        return overdueDate;
    }

    public void setOverdueDate(Long overdueDate) {
        this.overdueDate = overdueDate;
    }

    public Long getOverdueDay() {
        return overdueDay;
    }

    public void setOverdueDay(Long overdueDay) {
        this.overdueDay = overdueDay;
    }

    public BigDecimal getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(BigDecimal contractAmount) {
        this.contractAmount = contractAmount;
    }

    public Long getLastMonitorDate() {
        return lastMonitorDate;
    }

    public void setLastMonitorDate(Long lastMonitorDate) {
        this.lastMonitorDate = lastMonitorDate;
    }
}
