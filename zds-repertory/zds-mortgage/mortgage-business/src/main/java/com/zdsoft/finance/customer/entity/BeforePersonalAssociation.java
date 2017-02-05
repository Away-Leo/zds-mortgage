package com.zdsoft.finance.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:BeforePersonalAssociation.java
 * @Package:com.zdsoft.finance.customer.entity
 * @Description:客户关联关系表
 * @author: xj
 * @date:2017年1月11日 上午10:38:11
 * @version:v1.0
 */
@Entity
@Table(name = "cus_before_pers_association")
public class BeforePersonalAssociation extends BaseEntity {

    /**
     * 配偶对应的simplecode
     */
    public static String SPOUSE = "r01434";

    /**
     * 用一句话描述这个变量表示什么
     */
    private static final long serialVersionUID = 2711834250311750956L;
    /**
     * 客户id
     */
    @Column(length = 32)
    private String customerId;
    /**
     * 关联客户
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "relationtCustomerId")
    private BeforePersonalCustomer beforePersonalCusomer;
    /**
     * 与主借人关系
     */
    @Column(length = 20)
    private String relationship;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BeforePersonalCustomer getBeforePersonalCusomer() {
        return beforePersonalCusomer;
    }

    public void setBeforePersonalCusomer(BeforePersonalCustomer beforePersonalCusomer) {
        this.beforePersonalCusomer = beforePersonalCusomer;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

}
