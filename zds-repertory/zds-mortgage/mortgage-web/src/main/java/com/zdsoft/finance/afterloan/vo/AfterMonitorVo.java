package com.zdsoft.finance.afterloan.vo;

import com.zdsoft.finance.afterloan.entity.AfterMonitor;
import com.zdsoft.finance.common.utils.VoUtil;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title AfterMonitorVo.java
 * @className AfterMonitorVo
 * @description 贷后监控VO
 * @author LiaoGuoWei
 * @create 2017/3/6 18:05
 * @version V1.0
 **/
public class AfterMonitorVo {

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
    private BigDecimal applyAmount;

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
    private BigDecimal overdueAmount;

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
    private BigDecimal contractAmount;

    /**
     * 上次重估时间
     */
    private Long lastMonitorDate;

    /**
     * 案件状态
     */
    private String stage;
    /**
     * 案件状态名称
     */
    private String stageName;

    /**
     * 是否出险
     */
    private String isLoss;

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

    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductSubtypeName() {
        return productSubtypeName;
    }

    public void setProductSubtypeName(String productSubtypeName) {
        this.productSubtypeName = productSubtypeName;
    }

    public String getProductSubtypeId() {
        return productSubtypeId;
    }

    public void setProductSubtypeId(String productSubtypeId) {
        this.productSubtypeId = productSubtypeId;
    }

    public String getFloorAge() {
        return floorAge;
    }

    public void setFloorAge(String floorAge) {
        this.floorAge = floorAge;
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

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getIsLoss() {
        return isLoss;
    }

    public void setIsLoss(String isLoss) {
        this.isLoss = isLoss;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public AfterMonitorVo(){}
    public AfterMonitorVo(AfterMonitor after){
        VoUtil.copyPoperties(after,this,false,null,new String[]{"stage"});
    }

    /**
     * @Title: toPo
     * @Description: 返回VO对应的对象
     * @author liaoguowei
     * @param
     * @return com.zdsoft.finance.afterloan.entity.AfterMonitor
     * @throws
     */
    public AfterMonitor toPo(){
        AfterMonitor after=new AfterMonitor();
        BeanUtils.copyProperties(this,after);
        return after;
    }
}
