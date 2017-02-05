package com.zdsoft.finance.marketing.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.marketing.entity.PropertyOwner;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:PropertyOwnerVo.java
 * @Package:com.zdsoft.finance.marketing.vo
 * @Description:产权人Vo
 * @author: zhoushichao
 * @date:2017年1月13日 下午10:15:44
 * @version:v1.0
 */
public class PropertyOwnerVo extends BaseVo<PropertyOwner>{

	private static final long serialVersionUID = 1L;

	/**
	 * 案件ID
	 */
	private String caseApplyId;
	/**
	 * 房产
	 */
	private String housePropertyId;
	/**
	 * 产权人姓名
	 */
	private String ownerName;
	/**
	 * 产权人身份证号码
	 */
	private String credentialNo;
	/**
	 * 证件起始日期
	 */
	private Long identityStartDate;
	/**
	 * 证件结束日期
	 */
	private Long identityEndDate;
	/**
	 * 证件期限
	 */
	private String identityTerm;
	/**
	 * 省
	 */
	private String province;
	/**
	 * 市
	 */
	private String city;
	/**
	 * 区
	 */
	private String district;
	/**
	 * 产权人手机号码
	 */
	private String phoneNumber;
	/**
	 * 产权人邮箱地址
	 */
	private String emailAddress;
	/**
	 * 详细地址
	 */
	private String mailingAddress;
	/**
	 * 综合地址
	 */
	private String comprehensiveAddress;
	
	public String getCaseApplyId() {
		return caseApplyId;
	}
	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}
	public String getHousePropertyId() {
		return housePropertyId;
	}
	public void setHousePropertyId(String housePropertyId) {
		this.housePropertyId = housePropertyId;
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
	public void setComprehensiveAddress(String comprehensiveAddress) {
		this.comprehensiveAddress = comprehensiveAddress;
	}
	public String getComprehensiveAddress() {
		if (ObjectHelper.isNotEmpty(getProvince())) {
			comprehensiveAddress = getProvince();
		}
		if (ObjectHelper.isNotEmpty(getCity())) {
			comprehensiveAddress += "/"+getCity();
		}
		if (ObjectHelper.isNotEmpty(getDistrict())) {
			comprehensiveAddress += "/"+getDistrict();
		}
		if (ObjectHelper.isNotEmpty(getMailingAddress())) {
			comprehensiveAddress += "/"+getMailingAddress();
		}
		return comprehensiveAddress;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public PropertyOwnerVo() {
		super();
	}
	public PropertyOwnerVo(PropertyOwner po){
		super(po);
	}
	public PropertyOwner toPO(){
		PropertyOwner po = new PropertyOwner();
		return super.toPo(this, po);
	}
}
