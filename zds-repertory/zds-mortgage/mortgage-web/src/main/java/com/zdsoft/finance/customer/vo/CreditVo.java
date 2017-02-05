package com.zdsoft.finance.customer.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.common.utils.VoUtil;
import com.zdsoft.finance.customer.entity.Credit;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:BeforeCustomerCreditVo.java
 * @Package:com.zdsoft.finance.customer.vo
 * @Description:客户征信Vo
 * @author: gonght
 * @date:2017年1月16日 下午7:56:32
 * @version:v1.0
 */
public class CreditVo extends BaseVo<Credit> {

    private static final long serialVersionUID = 3056294437627001885L;

    /**
     * 客户id
     */
    private String customerId;

    /**
     * 案件id
     */
    private String caseApplyId;

    /**
     * 附件类型
     */
    private String attaType;

    /**
     * 附件类型名称
     */
    private String attaTypeNm;

    /**
     * 附件Id,多个以,号分隔开
     */
    private String attachmentIds;

    /**
     * 附件名称,多个以,号分隔开
     */
    private String attachmentLabels;

    /**
     * 客户名称(冗余,由于客户逻辑问题)
     */
    private String customerName;

    /**
     * 客户证件类型code(冗余,由于客户逻辑问题)
     */
    private String credentialType;

    /**
     * 客户证件类型名称(冗余,由于客户逻辑问题)
     */
    private String credentialTypeName;

    /**
     * 证件号码(冗余,由于客户逻辑问题)
     */
    private String credentialNo;

    /**
     * 参与类型code(冗余,由于客户逻辑问题)
     */
    private String joinType;

    /**
     * 参与类型名字(冗余,由于客户逻辑问题)
     */
    private String joinTypeName;

    /**
     * 是否实际用款人code(冗余,由于客户逻辑问题)
     */
    private String actualUsePerson;

    /**
     * 是否实际用款人中文(冗余,由于客户逻辑问题)
     */
    private String actualUsePersonName;

    /**
     * 征信状态
     */
    private String creditStatus;

    /**
     * 征信录入时间
     */
    private Long recordDate;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCaseApplyId() {
        return caseApplyId;
    }

    public void setCaseApplyId(String caseApplyId) {
        this.caseApplyId = caseApplyId;
    }

    public String getAttaType() {
        return attaType;
    }

    public void setAttaType(String attaType) {
        this.attaType = attaType;
    }

    public String getAttaTypeNm() {
        return attaTypeNm;
    }

    public void setAttaTypeNm(String attaTypeNm) {
        this.attaTypeNm = attaTypeNm;
    }

    public String getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(String attachmentIds) {
        this.attachmentIds = attachmentIds;
    }

    public String getAttachmentLabels() {
        return attachmentLabels;
    }

    public void setAttachmentLabels(String attachmentLabels) {
        this.attachmentLabels = attachmentLabels;
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

    public CreditVo() {

    }

    public CreditVo(Credit credit) throws Exception {
        VoUtil.copyPoperties(credit, this, false);
    }

    public CreditVo(Credit credit, String[] args, String[] simpleArgs) throws Exception {
        VoUtil.copyPoperties(credit, this, false, args, simpleArgs);
    }

    public Credit toPo() throws Exception {
        Credit t = new Credit();
        VoUtil.copyPoperties(this, t, true);
        return t;
    }
}