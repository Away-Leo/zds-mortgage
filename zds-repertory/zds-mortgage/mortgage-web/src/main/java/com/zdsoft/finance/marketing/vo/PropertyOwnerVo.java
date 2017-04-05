package com.zdsoft.finance.marketing.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.marketing.entity.PropertyOwner;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: PropertyOwnerVo.java 
 * @ClassName: PropertyOwnerVo 
 * @Description: 产权人Vo
 * @author zhoushichao 
 * @date 2017年3月14日 下午7:50:13 
 * @version V1.0
 */
public class PropertyOwnerVo extends BaseVo<PropertyOwner>{

	private static final long serialVersionUID = 1L;
	/**
	 * 房产Id
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
	private String identityTermName;
	/**
	 * 省
	 */
	private String province;
	private String provinceName;
	/**
	 * 市
	 */
	private String city;
	private String cityName;
	/**
	 * 区
	 */
	private String district;
	private String districtName;
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
	public String getIdentityTermName() {
		return identityTermName;
	}
	public void setIdentityTermName(String identityTermName) {
		this.identityTermName = identityTermName;
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
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getComprehensiveAddress() {
		if (ObjectHelper.isNotEmpty(getProvince())) {
			comprehensiveAddress = getProvinceName();
		}
		if (ObjectHelper.isNotEmpty(getCity())) {
			comprehensiveAddress += "/"+getCityName();
		}
		if (ObjectHelper.isNotEmpty(getDistrict())) {
			comprehensiveAddress += "/"+getDistrictName();
		}
		if (ObjectHelper.isNotEmpty(getMailingAddress())) {
			comprehensiveAddress += "/"+getMailingAddress();
		}
		return comprehensiveAddress;
	}
	public PropertyOwnerVo() {
		super();
	}
	public PropertyOwnerVo(PropertyOwner po){
		super(po,null,new String[] {"province","city","district","identityTerm"});
	}
	public PropertyOwner toPo(){
		PropertyOwner po = new PropertyOwner();
		return super.toPo(this, po);
	}
}
