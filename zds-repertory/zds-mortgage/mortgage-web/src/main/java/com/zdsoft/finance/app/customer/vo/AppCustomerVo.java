package com.zdsoft.finance.app.customer.vo;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:AppCustomerVo.java
 * @Package:com.zdsoft.finance.app.customer.vo
 * @Description:app贷款申请客户初始数据
 * @author: xj
 * @date:2017年1月13日 上午10:32:49
 * @version:v1.0
 */
public class AppCustomerVo {
	/**
	 * 1.当前会话token值
	 */
	private String token;
	/**
	 * 配偶id
	 */
	private String spouseId;
	/**
	 * 身份证照片
	 */
	private String fileId;
	/**
	 * 申请单ID
	 */
	private String applyId;
	/**
	 * 客户ID（可选）如填写则为提交修改
	 */
	private String customerId;
	/**
	 * 客户名称
	 */
	private String name;
	/**
	 * 证件类型
	 */
	private String credentiaType;
	/**
	 * 生日
	 */
	private String birthdayDate;
	/**
	 * 性别
	 */
	private String gender;
	/**
	 * 证件号码
	 */
	private String credentialNo;
	/**
	 * 工作单位 省
	 */
	private String province;
	/**
	 * 户籍 市
	 */
	private String city;
	/**
	 * 户籍 区
	 */
	private String district;
	/**
	 * 户籍 详细地址
	 */
	private String address;
	/**
	 * 曾用名
	 */
	private String formerName;
	/**
	 * 参与类型
	 */
	private String joinType;
	/**
	 * 婚姻状况
	 */
	private String maritalStatus;
	/**
	 * 是否是实际用款人  (t01021:是;t01022:否;)
	 */
	private String actualUsePerson;
	/**
	 * 职业类型
	 */
	private String careerType;
	/**
	 * 教育程度
	 */
	private String degree;
	/**
	 * 家庭地址 json串 
	 */
	private String homeAddr;
	/**
	 * 居住年限
	 */
	private String liveAge;
	/**
	 * 与主借人关系
	 */
	private String relationship;
	/**
	 * 邮箱地址
	 */
	private String email;
	/**
	 * 工作单位 json串
	 */
	private String workUnits;
	/**
	 * 联系方式 json串
	 */
	private String contacts;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCredentiaType() {
		return credentiaType;
	}
	public void setCredentiaType(String credentiaType) {
		this.credentiaType = credentiaType;
	}
	public String getCredentialNo() {
		return credentialNo;
	}
	public void setCredentialNo(String credentialNo) {
		this.credentialNo = credentialNo;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFormerName() {
		return formerName;
	}
	public void setFormerName(String formerName) {
		this.formerName = formerName;
	}
	public String getJoinType() {
		return joinType;
	}
	public void setJoinType(String joinType) {
		this.joinType = joinType;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getActualUsePerson() {
		return actualUsePerson;
	}
	public void setActualUsePerson(String actualUsePerson) {
		this.actualUsePerson = actualUsePerson;
	}
	public String getCareerType() {
		return careerType;
	}
	public void setCareerType(String careerType) {
		this.careerType = careerType;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getHomeAddr() {
		return homeAddr;
	}
	public void setHomeAddr(String homeAddr) {
		this.homeAddr = homeAddr;
	}
	public String getLiveAge() {
		return liveAge;
	}
	public void setLiveAge(String liveAge) {
		this.liveAge = liveAge;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWorkUnits() {
		return workUnits;
	}
	public void setWorkUnits(String workUnits) {
		this.workUnits = workUnits;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getBirthdayDate() {
		return birthdayDate;
	}
	public void setBirthdayDate(String birthdayDate) {
		this.birthdayDate = birthdayDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSpouseId() {
		return spouseId;
	}
	public void setSpouseId(String spouseId) {
		this.spouseId = spouseId;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	
	
}
