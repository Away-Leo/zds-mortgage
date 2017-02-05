package com.zdsoft.finance.authgrade.entity;

import com.zdsoft.framework.core.common.domain.BaseEntity;

import javax.persistence.*;

/**
 * 机构评级域对象
 *
 * @author LiaoGuoWei
 * @create 2017-01-06 14:57
 **/
@Entity
@Table(name = "busi_institutiongrade")
public class InstitutionGrade extends BaseEntity {

    /**
     * 机构编号
     */
    @Column(length = 255)
    private String institutionCode;

    /**
     * 机构名称
     */
    @Column(length = 255)
    private String institutionName;

    /**
     * 终评编号
     */
    @Column(length = 255)
    private String finalGradeCode;

    /**
     * 终评名称
     */
    @Column(length = 255)
    private String finalGradeName;

    /**
     * 机构授权等级
     */
    @ManyToOne
    @JoinColumn(name = "authGrade_id")
    private AuthGrade authGrade;

    /**
     * 操作人编号
     */
    @Column(length = 255)
    private String handerCode;

    /**
     * 操作人姓名
     */
    @Column(length = 255)
    private String handerName;

    /**
     * 操作时间
     */
    @Column(length = 36)
    private String handelTime;

    /**
     * 原评级编号
     */
    @Column(length = 255)
    private String originalGradeCode;

    /**
     * 原平级名称
     */
    @Column(length = 255)
    private String originalGradeName;

    /**
     * 冗余字段-授权等级ID
     */
    private transient String authGradeId;

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

    public String getFinalGradeCode() {
        return finalGradeCode;
    }

    public void setFinalGradeCode(String finalGradeCode) {
        this.finalGradeCode = finalGradeCode;
    }

    public String getFinalGradeName() {
        return finalGradeName;
    }

    public void setFinalGradeName(String finalGradeName) {
        this.finalGradeName = finalGradeName;
    }

    public AuthGrade getAuthGrade() {
        return authGrade;
    }

    public void setAuthGrade(AuthGrade authGrade) {
        this.authGrade = authGrade;
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

    public String getOriginalGradeCode() {
        return originalGradeCode;
    }

    public void setOriginalGradeCode(String originalGradeCode) {
        this.originalGradeCode = originalGradeCode;
    }

    public String getOriginalGradeName() {
        return originalGradeName;
    }

    public void setOriginalGradeName(String originalGradeName) {
        this.originalGradeName = originalGradeName;
    }

    public String getAuthGradeId() {
        return authGradeId;
    }

    public void setAuthGradeId(String authGradeId) {
        this.authGradeId = authGradeId;
    }
}
