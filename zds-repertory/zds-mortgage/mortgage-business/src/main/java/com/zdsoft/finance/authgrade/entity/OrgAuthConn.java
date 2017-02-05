package com.zdsoft.finance.authgrade.entity;

import com.zdsoft.framework.core.common.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 机构和授权等级关系表
 * @author LiaoGuoWei
 * @create 2017-01-22 19:14
 **/
@Entity
@Table(name = "busi_orgauthconn")
public class OrgAuthConn extends BaseEntity {

    /**
     * 授权等级code
     */
    @Column(length = 36)
    private String authGradeCode;
    /**
     * 授权等级name
     */
    @Column(length = 36)
    private String authGradeName;

    /**
     * 机构表ID
     */
    @Column(length = 36,unique = true)
    private String orgIntermediateId;
    /**
     * 机构名称
     */
    @Column(length = 255)
    private String orgIntermediateName;

    /**
     * 操作人编号
     */
    @Column(length = 36)
    private String handelerCode;

    /**
     * 操作人姓名
     */
    @Column(length = 255)
    private String handelerName;

    /**
     * 操作时间
     */
    @Column(length = 255)
    private String handelTime;

    /**
     * 原评级编号
     */
    @Column(length = 36)
    private String originalGradeCode;

    /**
     * 原评级名称
     */
    @Column(length = 36)
    private String originalGradeName;


    public String getOrgIntermediateName() {
        return orgIntermediateName;
    }

    public void setOrgIntermediateName(String orgIntermediateName) {
        this.orgIntermediateName = orgIntermediateName;
    }

    public String getAuthGradeName() {
        return authGradeName;
    }

    public void setAuthGradeName(String authGradeName) {
        this.authGradeName = authGradeName;
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

    public String getAuthGradeCode() {
        return authGradeCode;
    }

    public void setAuthGradeCode(String authGradeCode) {
        this.authGradeCode = authGradeCode;
    }

    public String getOrgIntermediateId() {
        return orgIntermediateId;
    }

    public void setOrgIntermediateId(String orgIntermediateId) {
        this.orgIntermediateId = orgIntermediateId;
    }

    public String getHandelerCode() {
        return handelerCode;
    }

    public void setHandelerCode(String handelerCode) {
        this.handelerCode = handelerCode;
    }

    public String getHandelerName() {
        return handelerName;
    }

    public void setHandelerName(String handelerName) {
        this.handelerName = handelerName;
    }

    public String getHandelTime() {
        return handelTime;
    }

    public void setHandelTime(String handelTime) {
        this.handelTime = handelTime;
    }
}
