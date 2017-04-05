package com.zdsoft.finance.customer.dto;

import java.io.Serializable;

/** 
* 版权所有：重庆正大华日软件有限公司
* @Title: CaseRelationCustomerDto.java 
* @Package com.zdsoft.finance.customer.dto 
* @Description: 案件关联客户
* @author jingyh 
* @date 2017年2月27日 下午12:26:17 
* @version V1.0 
*/
public class CaseRelationCustomerDto implements Serializable {

	private static final long serialVersionUID = -8367655209370520441L;

	/**
	 * 客户id
	 */
	private String customerId;
	
	/**
	 * 客户姓名
	 */
	private String customerName;
	
	/**
	 * 证件类型
	 */
	private String credentialType;
	
	/**
	 * 证件类型name
	 */
	private String credentialTypeName;
	
	/**
	 * 证件号
	 */
	private String credentialNo;
	
	/**
	 * 参与类型
	 */
	private String joinType;
	/**
	 * 参与类型name
	 */
	private String joinTypeName;
	
	/**
	 * 是否为配偶
	 */
	private Boolean isSpouse;
	
	/**
	 * 配偶参与类型
	 */
	private String spouseJoinType;
	/**
	 * 配偶参与类型name
	 */
	private String spouseJoinTypeName;
	
	/**
	 * 配偶Id
	 */
	private String spouseCustomerId;
	
	/**
	 * 是否实际用款人
	 */
	private String actualUsePerson;
	/**
	 * 是否实际用款人name
	 */
	private String actualUsePersonName;

	
	public String getCredentialTypeName() {
		return credentialTypeName;
	}

	public void setCredentialTypeName(String credentialTypeName) {
		this.credentialTypeName = credentialTypeName;
	}

	public String getJoinTypeName() {
		return joinTypeName;
	}

	public void setJoinTypeName(String joinTypeName) {
		this.joinTypeName = joinTypeName;
	}

	public String getSpouseJoinTypeName() {
		return spouseJoinTypeName;
	}

	public void setSpouseJoinTypeName(String spouseJoinTypeName) {
		this.spouseJoinTypeName = spouseJoinTypeName;
	}

	public String getActualUsePersonName() {
		return actualUsePersonName;
	}

	public void setActualUsePersonName(String actualUsePersonName) {
		this.actualUsePersonName = actualUsePersonName;
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

	public Boolean getIsSpouse() {
		return isSpouse;
	}

	public void setIsSpouse(Boolean isSpouse) {
		this.isSpouse = isSpouse;
	}

	public String getSpouseJoinType() {
		return spouseJoinType;
	}

	public void setSpouseJoinType(String spouseJoinType) {
		this.spouseJoinType = spouseJoinType;
	}

	public String getActualUsePerson() {
		return actualUsePerson;
	}

	public void setActualUsePerson(String actualUsePerson) {
		this.actualUsePerson = actualUsePerson;
	}

	public String getSpouseCustomerId() {
		return spouseCustomerId;
	}

	public void setSpouseCustomerId(String spouseCustomerId) {
		this.spouseCustomerId = spouseCustomerId;
	}
	
	
}
