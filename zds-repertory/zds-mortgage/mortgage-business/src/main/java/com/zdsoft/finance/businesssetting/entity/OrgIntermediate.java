package com.zdsoft.finance.businesssetting.entity;

import com.zdsoft.framework.core.common.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title OrgIntermediate.java
 * @className OrgIntermediate
 * @description 机构中间表
 * @author LiaoGuoWei
 * @create 2017/3/3 14:22
 * @version V1.0
 **/
@Table(name = "buss_orgintermediate")
@Entity
public class OrgIntermediate extends BaseEntity {

    private static final long serialVersionUID = -6329361967354436403L;
    /**
     * 机构编号规则
     */
    @Column(length = 255)
    private String orgCdRule;

    /**
     * 规则名称
     */
    @Column(length = 255)
    private String orgCdRuleNm;

    /**
     * 机构类型
     */
    @Column(length = 255)
    private String orgType;

    /**
     * 机构类型名称
     */
    @Column(length = 255)
    private String orgTypeNm;

    /**
     * 机构编号
     */
    @Column(length = 255)
    private String orgCd;

    /**
     * 机构ID
     */
    @Column(length = 255)
    private String orgId;

    /**
     * 机构名称
     */
    @Column(length = 255)
    private String orgNm;

    /**
     * 排序名称
     */
    @Column(length = 255)
    private String shortNm;

    /**
     * 注册
     */
    @Column(length = 255)
    private String region;

    /**
     * 备注
     */
    @Column(length = 255)
    private String remark;

    public String getOrgCdRule() {
        return orgCdRule;
    }

    public void setOrgCdRule(String orgCdRule) {
        this.orgCdRule = orgCdRule;
    }

    public String getOrgCdRuleNm() {
        return orgCdRuleNm;
    }

    public void setOrgCdRuleNm(String orgCdRuleNm) {
        this.orgCdRuleNm = orgCdRuleNm;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getOrgTypeNm() {
        return orgTypeNm;
    }

    public void setOrgTypeNm(String orgTypeNm) {
        this.orgTypeNm = orgTypeNm;
    }

    public String getOrgCd() {
        return orgCd;
    }

    public void setOrgCd(String orgCd) {
        this.orgCd = orgCd;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgNm() {
        return orgNm;
    }

    public void setOrgNm(String orgNm) {
        this.orgNm = orgNm;
    }

    public String getShortNm() {
        return shortNm;
    }

    public void setShortNm(String shortNm) {
        this.shortNm = shortNm;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
