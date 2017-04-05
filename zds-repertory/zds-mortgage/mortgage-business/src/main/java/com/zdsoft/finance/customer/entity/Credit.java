package com.zdsoft.finance.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;


/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: Credit.java 
 * @ClassName: Credit 
 * @Description: 案件客户征信信息
 * @author liuhuan 
 * @date 2017年2月23日 上午10:37:45 
 * @version V1.0 
 */ 
@Entity
@Table(name = "cust_credit")
public class Credit extends BaseEntity {

    private static final long serialVersionUID = 2006175870147775656L;
    
    /**
     *  未获取征信
     */
    public static final String LINK_STATUS_UNSUCCESSFUL = "YWDM008501";
    /**
     * 已在线授权
     */
    public static final String LINK_STATUS_ONLINE = "YWDM008502";
    /**
     * 已上传征信授权书
     */
    public static final String LINK_STATUS_AUTHORIZATION = "YWDM008503";
    /**
     * 已上传征信(征信报告)
     */
    public static final String LINK_STATUS_CREDIT = "YWDM008504";
    /**
     * 已获取征信
     */
    public static final String LINK_STATUS_SUCCESSFUL = "YWDM008505";
    /**
     * 已上传征信身份证
     */
    public static final String LINK_STATUS_CARD = "YWDM008506";
    
    /**
     * 环节代码：营销录入
     */
    public static final String LINK_CODE_APPLY = "YWDM009101";
    
    /**
     * 环节代码：资调录入
     */
    public static final String LINK_CODE_SURVEY = "YWDM009102";
    
    /**
     * 贷后录入
     */
    public static final String LINK_CODE_AFTER_LOAN = "YWDM009103";

    /**
     * 案件id
     */
    @Column(length = 32)
    private String caseApplyId;

    /**
     * 客户ID
     */
    @Column(length = 32)
    private String customerId;

    /**   
     * 征信环节code (营销录入,资调录入,贷后录入)  
     */ 
    @Column(length = 20)
    private String creditLinkCode;
    
    
    /**
     * 环节状态code (未获取征信,已在线授权书,已上传征信授权书,已上传征信,已获取征信)
     */
    @Column(length = 32)
    private String linkStatusCode;
    
    /**
     * 上传附件时间
     */
    @Column(length = 16)
    private Long uploadDate;
    
    /**
     * 录入状态 ( "0":未录入   "1":录入 )
     */
    @Column(length = 1)
    private String inputStatus;
    
    /**
     * 录入时间
     */
    @Column(length = 16)
    private Long inputDate;
    
    /**
     * 录入人code
     */
    @Column(length = 32)
    private String inputManCode;
    
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


	public String getCreditLinkCode() {
		return creditLinkCode;
	}

	public void setCreditLinkCode(String creditLinkCode) {
		this.creditLinkCode = creditLinkCode;
	}


	public String getLinkStatusCode() {
		return linkStatusCode;
	}

	public void setLinkStatusCode(String linkStatusCode) {
		this.linkStatusCode = linkStatusCode;
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

}
