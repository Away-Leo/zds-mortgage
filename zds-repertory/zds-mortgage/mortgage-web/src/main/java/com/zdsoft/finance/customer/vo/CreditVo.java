package com.zdsoft.finance.customer.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.common.utils.VoUtil;
import com.zdsoft.finance.customer.entity.Credit;


/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreditVo.java 
 * @ClassName: CreditVo 
 * @Description: 征信信息vo
 * @author liuhuan 
 * @date 2017年2月23日 下午2:31:40 
 * @version V1.0 
 */ 
public class CreditVo extends BaseVo<Credit> {
    private static final long serialVersionUID = 3056294437627001885L;

    /**
     * 案件id
     */
    private String caseApplyId;

    /**
     * 客户ID
     */
    private String customerId;

    /**
     * 客户名称
     */
    private String customerName;

    /**   
     * 征信环节code   
     */ 
    private String creditLinkCode;
    
    /**
     * 征信环节name (营销录入,资调录入,贷后录入)
     */
    private String creditLinkCodeName;
    
    /**
     * 环节状态code 
     */
    private String linkStatusCode;
    
    /**
     * 环节状态name (未获取征信,已在线授权书,已上传征信授权书,已上传征信,已获取征信)
     */
    private String linkStatusCodeName;
    
    /**
     * 上传附件时间
     */
    private Long uploadDate;
    
    /**
     * 录入状态
     */
    private String inputStatus;
    
    /**
     * 录入时间
     */
    private Long inputDate;
    
    /**
     * 录入人code
     */
    private String inputManCode;
    
    /**
     * 录入人name
     */
    private String inputManName;
    
    /**
	 * 证件类型
	 */
	private String credentialType;
	private String credentialTypeName;
    
	/**
	 * 证件号码
	 */
	private String credentialNo;
	
	 /**
     * 参与类型 共借人、担保人
     */
    private String joinType;
    /**
     * 参与类型 共借人、担保人
     */
    private String joinTypeName;

    /**
     * 是否是实际用款人
     */
    private String actualUsePerson;
    private String actualUsePersonName;
    
    public CreditVo() {
    	super();
    }
    
    public CreditVo(Credit credit) throws Exception {
        super(credit,new String[]{"createTime","updateTime","deleteTime"}, new String[]{"linkStatusCode","creditLinkCode"});
    }

    public CreditVo(Credit credit, String[] args, String[] simpleArgs) throws Exception {
        VoUtil.copyPoperties(credit, this, false, args, simpleArgs);
    }

    public Credit toPo() throws Exception {
        Credit t = new Credit();
        VoUtil.copyPoperties(this, t, true, new String[]{"createTime","updateTime","deleteTime"});
        return t;
    }
    
    public String getJoinTypeName() {
		return joinTypeName;
	}

	public void setJoinTypeName(String joinTypeName) {
		this.joinTypeName = joinTypeName;
	}

	public String getActualUsePersonName() {
		return actualUsePersonName;
	}

	public void setActualUsePersonName(String actualUsePersonName) {
		this.actualUsePersonName = actualUsePersonName;
	}

	public String getCredentialTypeName() {
		return credentialTypeName;
	}

	public void setCredentialTypeName(String credentialTypeName) {
		this.credentialTypeName = credentialTypeName;
	}

	public String getCredentialType() {
		return credentialType;
	}

	public void setCredentialType(String credentialType) {
		this.credentialType = credentialType;
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

	public String getActualUsePerson() {
		return actualUsePerson;
	}

	public void setActualUsePerson(String actualUsePerson) {
		this.actualUsePerson = actualUsePerson;
	}

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
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

	public String getCreditLinkCode() {
		return creditLinkCode;
	}

	public void setCreditLinkCode(String creditLinkCode) {
		this.creditLinkCode = creditLinkCode;
	}

	public String getCreditLinkCodeName() {
		return creditLinkCodeName;
	}

	public void setCreditLinkCodeName(String creditLinkCodeName) {
		this.creditLinkCodeName = creditLinkCodeName;
	}

	public String getLinkStatusCode() {
		return linkStatusCode;
	}

	public void setLinkStatusCode(String linkStatusCode) {
		this.linkStatusCode = linkStatusCode;
	}

	public String getLinkStatusCodeName() {
		return linkStatusCodeName;
	}

	public void setLinkStatusCodeName(String linkStatusCodeName) {
		this.linkStatusCodeName = linkStatusCodeName;
	}

	public Long getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Long uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getInputStatus() {
		return inputStatus;
	}

	public void setInputStatus(String inputStatus) {
		this.inputStatus = inputStatus;
	}

	public Long getInputDate() {
		return inputDate;
	}

	public void setInputDate(Long inputDate) {
		this.inputDate = inputDate;
	}

	public String getInputManCode() {
		return inputManCode;
	}

	public void setInputManCode(String inputManCode) {
		this.inputManCode = inputManCode;
	}

	public String getInputManName() {
		return inputManName;
	}

	public void setInputManName(String inputManName) {
		this.inputManName = inputManName;
	}

}