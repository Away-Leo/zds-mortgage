package com.zdsoft.finance.cedit.api.entity;

//import com.thoughtworks.xstream.annotations.XStreamAlias;

//	@XStreamAlias("IdentityInfo")
public class IdentityInfo {
	//身份信息
//	Gender 性别 S N   
//	DateOfBirth 出生日期 
//	MaritalStatus 婚姻状况 S N  
//	MobilePhone 手机号码 S N  
//	CompanyPhone 单位电话 S N  
//	HomePhone 住宅电话 S N  
//	Education 学历 S N  
//	Degree 学位 S N  
//	ContactAddress 通讯地址 S N  
//	ResidenceAddress 户籍地址 S N   
	
	//	@XStreamAlias("Gender")
	private String gender;
	
	//	@XStreamAlias("DateOfBirth")
	private String dateOfBirth;
	
	//	@XStreamAlias("MaritalStatus")
	private String maritalStatus;
	
	//	@XStreamAlias("mobilePhone")
	private String MobilePhone;
	
	
	//	@XStreamAlias("CompanyPhone")
	private String companyPhone;
	
	

	
	
	
	//	@XStreamAlias("HomePhone")
	private String homePhone;
	
	//	@XStreamAlias("Education")
	private String education;
	
	//	@XStreamAlias("degree")
	private String Degree;
	
	//	@XStreamAlias("ContactAddress")
	private String contactAddress;
	
	//	@XStreamAlias("ResidenceAddress")
	private String residenceAddress;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getMobilePhone() {
		return MobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		MobilePhone = mobilePhone;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getDegree() {
		return Degree;
	}

	public void setDegree(String degree) {
		Degree = degree;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getResidenceAddress() {
		return residenceAddress;
	}

	public void setResidenceAddress(String residenceAddress) {
		this.residenceAddress = residenceAddress;
	}
	
	
	
	
	
}
