package com.zdsoft.finance.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * @ClassName Credit
 * @Description 征信信息
 * @author Liyb
 * @Date 2017年1月13日 下午5:13:31
 * @version 1.0.0
 */
@Entity
@Table(name = "cus_credit")
public class Credit extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 2006175870147775656L;

    /**
     * 案件id，非持久化字段
     */
    @Transient
    private String caseApplyId;

    /**
     * 关联案件
     */
    @ManyToOne
    @JoinColumn(name = "caseApplyId")
    private CaseApply caseApply;

    /**
     * 客户ID，考虑到贷前、后客户是否统一的问题，暂采用弱关联
     */
    @Column(length = 32)
    private String customerId;

    /**
     * 客户名称(冗余,由于客户逻辑问题)
     */
    @Column(length = 128)
    private String customerName;

    /**
     * 客户证件类型code(冗余,由于客户逻辑问题)
     */
    @Column(length = 64)
    private String credentialType;

    /**
     * 客户证件类型名称(冗余,由于客户逻辑问题)
     */
    @Column(length = 128)
    private String credentialTypeName;

    /**
     * 证件号码(冗余,由于客户逻辑问题)
     */
    @Column(length = 64)
    private String credentialNo;

    /**
     * 参与类型code(冗余,由于客户逻辑问题)
     */
    @Column(length = 32)
    private String joinType;

    /**
     * 参与类型名字(冗余,由于客户逻辑问题)
     */
    @Column(length = 64)
    private String joinTypeName;

    /**
     * 是否实际用款人code(冗余,由于客户逻辑问题)
     */
    @Column(length = 64)
    private String actualUsePerson;

    /**
     * 是否实际用款人中文(冗余,由于客户逻辑问题)
     */
    @Column(length = 64)
    private String actualUsePersonName;

    /**
     * 征信状态
     */
    @Column(length = 32)
    private String creditStatus;

    /**
     * 征信录入时间
     */
    @Column
    private Long recordDate;

    /**
     * 客户征信附件集合
     */
    //修改成与案件资料清单附件关联

    public String getCaseApplyId() {
        return caseApplyId;
    }

    public void setCaseApplyId(String caseApplyId) {
        this.caseApplyId = caseApplyId;
    }

    public CaseApply getCaseApply() {
        return caseApply;
    }

    public void setCaseApply(CaseApply caseApply) {
        this.caseApply = caseApply;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCredentialType() {
        return credentialType;
    }

    public void setCredentialType(String credentialType) {
        this.credentialType = credentialType;
    }

    public String getCredentialTypeName() {
        return credentialTypeName;
    }

    public void setCredentialTypeName(String credentialTypeName) {
        this.credentialTypeName = credentialTypeName;
    }

    public String getCredentialNo() {
        return credentialNo;
    }

    public void setCredentialNo(String credentialNo) {
        this.credentialNo = credentialNo;
    }

    public String getJoinType() {
        return joinType;
    }

    public void setJoinType(String joinType) {
        this.joinType = joinType;
    }

    public String getJoinTypeName() {
        return joinTypeName;
    }

    public void setJoinTypeName(String joinTypeName) {
        this.joinTypeName = joinTypeName;
    }

    public String getActualUsePerson() {
        return actualUsePerson;
    }

    public void setActualUsePerson(String actualUsePerson) {
        this.actualUsePerson = actualUsePerson;
    }

    public String getActualUsePersonName() {
        return actualUsePersonName;
    }

    public void setActualUsePersonName(String actualUsePersonName) {
        this.actualUsePersonName = actualUsePersonName;
    }

    public String getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(String creditStatus) {
        this.creditStatus = creditStatus;
    }

    public Long getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Long recordDate) {
        this.recordDate = recordDate;
    }
}
