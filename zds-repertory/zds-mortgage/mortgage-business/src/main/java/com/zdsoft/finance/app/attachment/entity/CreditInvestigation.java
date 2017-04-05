package com.zdsoft.finance.app.attachment.entity;

import java.io.Serializable;
import java.util.List;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreditInvestigationVo.java 
 * @ClassName: CreditInvestigationVo 
 * @Description: App-征信信息
 * @author liuhuan 
 * @date 2017年3月18日 下午2:23:24 
 * @version V1.0 
 */ 
public class CreditInvestigation implements Serializable{
	/**   
	 */ 
	private static final long serialVersionUID = -728460757651544162L;
	/**
	 * 征信id
	 */
	private String creditId;
	/**
	 * linkCode
	 */
	private String linkCode;
	/**
	 * 上传文件的类型（报告、授权书）
	 */
	private String creditFileType;
	/**
	 * 征信环节code（资调、营销）
	 */
	private String creditLinkCode;
	/**
	 * 证件号
	 */
	private String creditNo;
	/**
	 * 客户姓名
	 */
	private String creditName;
	/**
	 * 证件类型
	 */
	private String credentialType;
	/**
	 * 身份证-附件id
	 */
	private String cardAttaId;
	/**
	 * 案件id
	 */
	private String caseApplyId;
	/**
	 * 客户id
	 */
	private String customerId;
	/**
	 * 授权书-附件id
	 */
	private String authorizationAttaIds;
	/**
	 * 已保存的附件对象id集
	 */
	private List<String> fileStoreIds;
	
	public String getLinkCode() {
		return linkCode;
	}
	public void setLinkCode(String linkCode) {
		this.linkCode = linkCode;
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
	public String getCreditId() {
		return creditId;
	}
	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}
	public String getCreditFileType() {
		return creditFileType;
	}
	public void setCreditFileType(String creditFileType) {
		this.creditFileType = creditFileType;
	}
	public String getCreditLinkCode() {
		return creditLinkCode;
	}
	public void setCreditLinkCode(String creditLinkCode) {
		this.creditLinkCode = creditLinkCode;
	}
	public String getCreditNo() {
		return creditNo;
	}
	public void setCreditNo(String creditNo) {
		this.creditNo = creditNo;
	}
	public String getCreditName() {
		return creditName;
	}
	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}
	public String getCredentialType() {
		return credentialType;
	}
	public void setCredentialType(String credentialType) {
		this.credentialType = credentialType;
	}
	public String getCardAttaId() {
		return cardAttaId;
	}
	public void setCardAttaId(String cardAttaId) {
		this.cardAttaId = cardAttaId;
	}
	public String getAuthorizationAttaIds() {
		return authorizationAttaIds;
	}
	public void setAuthorizationAttaIds(String authorizationAttaIds) {
		this.authorizationAttaIds = authorizationAttaIds;
	}
	public List<String> getFileStoreIds() {
		return fileStoreIds;
	}
	public void setFileStoreIds(List<String> fileStoreIds) {
		this.fileStoreIds = fileStoreIds;
	}
	
}
