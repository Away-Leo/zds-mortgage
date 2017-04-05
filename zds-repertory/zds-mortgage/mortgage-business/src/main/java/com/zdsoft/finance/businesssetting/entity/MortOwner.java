package com.zdsoft.finance.businesssetting.entity;

import com.zdsoft.framework.core.common.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title MortOwner.java
 * @className MortOwner
 * @description 抵押权人管理域对象
 * @author LiaoGuoWei
 * @create 2017/3/3 14:21
 * @version V1.0
 **/
@Entity
@Table(name = "buss_mortowner")
public class MortOwner extends BaseEntity {

    private static final long serialVersionUID = 928119961537229774L;
    /**
     * 所属机构编号
     */
    @Column(length = 32)
    private String beOrgCode;

    /**
     * 类别编号
     */
    @Column(length = 32)
    private String ownerTypeCode;

    /**
     * 名称
     */
    @Column(length = 64)
    private String ownerName;

    /**
     * 状态
     */
    @Column(length = 1)
    private String status;

    public String getBeOrgCode() {
        return beOrgCode;
    }

    public void setBeOrgCode(String beOrgCode) {
        this.beOrgCode = beOrgCode;
    }

    public String getOwnerTypeCode() {
        return ownerTypeCode;
    }

    public void setOwnerTypeCode(String ownerTypeCode) {
        this.ownerTypeCode = ownerTypeCode;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
