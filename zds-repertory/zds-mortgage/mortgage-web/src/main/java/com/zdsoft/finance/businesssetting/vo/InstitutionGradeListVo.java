package com.zdsoft.finance.businesssetting.vo;

import com.zdsoft.finance.businesssetting.entity.AuthGrade;
import com.zdsoft.finance.common.base.BaseVo;

import java.math.BigDecimal;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title InstitutionGradeListVo.java
 * @className InstitutionGradeListVo
 * @description 机构评级列表VO
 * @author LiaoGuoWei
 * @create 2017/3/3 15:23
 * @version V1.0
 **/
public class InstitutionGradeListVo extends BaseVo<AuthGrade> {

    /**
     * 授权等级编号
     */
    private String gradeCode;
    /**
     * 授权等级名称
     */
    private String gradeName;

    /**
     * 产品大类ID
     */
    private String productParentId;

    /**
     * 产品大类名称
     */
    private String productParentName;

    /**
     * 子产品ID
     */
    private String productChildId;

    /**
     * 子产品名称
     */
    private String productChildName;

    /**
     * 额度
     */
    private BigDecimal authAmount;

    /**
     * 创建人名称
     */
    private String createByName;

    /**
     * 更新人（操作人）名称
     */
    private String updateByName;

    /**
     * 操作字符串类型时间
     */
    private String updateTimeStr;

    /**
     * 机构评级ID
     */
    private String institutionGradeId;

    /**
     * 机构编号
     */
    private String institutionCode;
    /**
     * 机构名称
     */
    private String institutionName;

    /**
     * 终评编号
     */
    private String insGradeCode;
    /**
     * 终评名称
     */
    private String insGradeName;

    /**
     * 操作人编号
     */
    private String handerCode;

    /**
     * 操作人姓名
     */
    private String handerName;

    /**
     * 操作时间
     */
    private String handelTime;

    public String getGradeCd() {
        return gradeCode;
    }

    public void setGradeCd(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getGradeNm() {
        return gradeName;
    }

    public void setGradeNm(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getProductParentId() {
        return productParentId;
    }

    public void setProductParentId(String productParentId) {
        this.productParentId = productParentId;
    }

    public String getProductParentNm() {
        return productParentName;
    }

    public void setProductParentNm(String productParentName) {
        this.productParentName = productParentName;
    }

    public String getProductChildId() {
        return productChildId;
    }

    public void setProductChildId(String productChildId) {
        this.productChildId = productChildId;
    }

    public String getProductChildNm() {
        return productChildName;
    }

    public void setProductChildNm(String productChildName) {
        this.productChildName = productChildName;
    }

    public BigDecimal getAuthAmount() {
        return authAmount;
    }

    public void setAuthAmount(BigDecimal authAmount) {
        this.authAmount = authAmount;
    }

    public String getCreateByNm() {
        return createByName;
    }

    public void setCreateByNm(String createByName) {
        this.createByName = createByName;
    }

    public String getUpdateByNm() {
        return updateByName;
    }

    public void setUpdateByNm(String updateByName) {
        this.updateByName = updateByName;
    }

    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    public String getInstitutionGradeId() {
        return institutionGradeId;
    }

    public void setInstitutionGradeId(String institutionGradeId) {
        this.institutionGradeId = institutionGradeId;
    }

    public String getInstitutionCd() {
        return institutionCode;
    }

    public void setInstitutionCd(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getProductParentName() {
        return productParentName;
    }

    public void setProductParentName(String productParentName) {
        this.productParentName = productParentName;
    }

    public String getProductChildName() {
        return productChildName;
    }

    public void setProductChildName(String productChildName) {
        this.productChildName = productChildName;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public String getUpdateByName() {
        return updateByName;
    }

    public void setUpdateByName(String updateByName) {
        this.updateByName = updateByName;
    }

    public String getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getInsGradeCode() {
        return insGradeCode;
    }

    public void setInsGradeCode(String insGradeCode) {
        this.insGradeCode = insGradeCode;
    }

    public String getInsGradeName() {
        return insGradeName;
    }

    public void setInsGradeName(String insGradeName) {
        this.insGradeName = insGradeName;
    }

    public String getHanderCode() {
        return handerCode;
    }

    public void setHanderCode(String handerCode) {
        this.handerCode = handerCode;
    }

    public String getHanderName() {
        return handerName;
    }

    public void setHanderName(String handerName) {
        this.handerName = handerName;
    }

    public String getHandelTime() {
        return handelTime;
    }

    public void setHandelTime(String handelTime) {
        this.handelTime = handelTime;
    }
}
