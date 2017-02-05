package com.zdsoft.finance.authgrade.entity;

import com.zdsoft.framework.core.common.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 机构中间表
 *
 * @author LiaoGuoWei
 * @create 2017-01-06 17:15
 **/
@Table(name = "busi_orgintermediate")
@Entity
public class OrgIntermediate extends BaseEntity {

    @Column(length = 255)
    private String orgCdRule;

    @Column(length = 255)
    private String orgCdRuleNm;

    @Column(length = 255)
    private String orgType;

    @Column(length = 255)
    private String orgTypeNm;

    @Column(length = 255)
    private String orgCd;

    @Column(length = 255)
    private String orgId;

    @Column(length = 255)
    private String orgNm;

    @Column(length = 255)
    private String shortNm;

    @Column(length = 255)
    private String region;

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
