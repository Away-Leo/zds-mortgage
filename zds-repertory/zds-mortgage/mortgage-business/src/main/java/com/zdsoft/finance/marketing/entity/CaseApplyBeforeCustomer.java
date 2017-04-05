package com.zdsoft.finance.marketing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.finance.customer.entity.BeforeCustomer;
import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:CaseApplyBeforeCustomer.java
 * @Package:com.zdsoft.finance.marketing.entity
 * @Description:案件客户关系表（贷前案件申请人）
 * @author: xj
 * @date:2017年1月11日 下午12:06:30
 * @version:v1.0
 */
@Entity
@Table(name = "case_before_customer")
public class CaseApplyBeforeCustomer extends BaseEntity {

    /**
     * 主借人
     */
    public static String MAIN_BORROW = "YWDM0051036";
    /**
     * 共借人 added by caixiekang 2017/02/15
     */
    public static String JOINTLY_PARTY = "YWDM0051037";
    /**
     * 担保人 add by caixiekang 2017/02/15
     */
    public static String GUARANTEE_PARTY = "YWDM0051038";
    /**
     * 序列化
     */
    private static final long serialVersionUID = 7571013779413761584L;
    /**
     * 案件
     */
    @ManyToOne
    @JoinColumn(name = "caseApplyId")
    private CaseApply caseApply;
    /**
     * 贷前客户
     */
    @ManyToOne
    @JoinColumn(name = "customerId")
    private BeforeCustomer beforeCustomer;
    /**
     * 参与类型
     */
    @Column(length = 20)
    private String joinType;

    public CaseApply getCaseApply() {
        return caseApply;
    }

    public void setCaseApply(CaseApply caseApply) {
        this.caseApply = caseApply;
    }

    public BeforeCustomer getBeforeCustomer() {
        return beforeCustomer;
    }

    public void setBeforeCustomer(BeforeCustomer beforeCustomer) {
        this.beforeCustomer = beforeCustomer;
    }

    public String getJoinType() {
        return joinType;
    }

    public void setJoinType(String joinType) {
        this.joinType = joinType;
    }

}
