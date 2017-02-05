package com.zdsoft.finance.marketing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:PropertyOwner.java
 * @Package:com.zdsoft.finance.marketing.entity
 * @Description:产权人实体表
 * @author: zhoushichao
 * @date:2017年1月10日 下午9:46:18
 * @version:v1.0
 */
@Entity
@Table(name = "mark_property_owner")
public class PropertyOwner extends BaseEntity{

	private static final long serialVersionUID = 1L;

	/**
	 * 案件ID
	 */
	@Column(length = 32)
	private String caseApplyId;
	
	/**
	 * 房产
	 */
	@Column(length=32)
	private String housePropertyId;
	
	/**
	 * 产权人姓名
	 */
	@Column(length = 128)
	private String ownerName;
	/**
	 * 产权人身份证号码
	 */
	@Column(length = 32)
	private String credentialNo;
	/**
	 * 证件起始日期
	 */
	@Column
	private Long identityStartDate;
	/**
	 * 证件结束日期
	 */
	@Column
	private Long identityEndDate;
	/**
	 * 证件期限
	 */
	@Column(length = 20)
	private String identityTerm;
	/**
	 * 省
	 */
	@Column(length = 32)
	private String province;
	/**
	 * 市
	 */
	@Column(length = 32)
	private String city;
	/**
	 * 区
	 */
	@Column(length = 32)
	private String district;
	/**
	 * 产权人手机号码
	 */
	@Column(length = 15)
	private String phoneNumber;
	/**
	 * 产权人邮箱地址
	 */
	@Column(length = 32)
	private String emailAddress;
	/**
	 * 详细地址
	 */
	@Column(length = 128)
	private String mailingAddress;
	
	public String getCaseApplyId() {
		return caseApplyId;
	}
	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getCredentialNo() {
		return credentialNo;
	}
	public void setCredentialNo(String credentialNo) {
		this.credentialNo = credentialNo;
	}
	public Long getIdentityStartDate() {
		return identityStartDate;
	}
	public void setIdentityStartDate(Long identityStartDate) {
		this.identityStartDate = identityStartDate;
	}
	public Long getIdentityEndDate() {
		return identityEndDate;
	}
	public void setIdentityEndDate(Long identityEndDate) {
		this.identityEndDate = identityEndDate;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getMailingAddress() {
		return mailingAddress;
	}
	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}
	public String getIdentityTerm() {
		return identityTerm;
	}
	public void setIdentityTerm(String identityTerm) {
		this.identityTerm = identityTerm;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getHousePropertyId() {
		return housePropertyId;
	}
	public void setHousePropertyId(String housePropertyId) {
		this.housePropertyId = housePropertyId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
