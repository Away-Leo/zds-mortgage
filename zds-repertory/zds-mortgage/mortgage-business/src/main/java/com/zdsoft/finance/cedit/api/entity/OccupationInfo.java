package com.zdsoft.finance.cedit.api.entity;

//import com.thoughtworks.xstream.annotations.XStreamAlias;

//	@XStreamAlias("OccupationInfo")
public class OccupationInfo {
	
	
//	 职业信息【OccupationInfo】 
//	 标识符 数据项名称 类型 长度 其它 
//	 No 编号 S N  
//	 CompanyName 工作单位 S N  
//	 CompanyAddress 单位地址 S N  
//	 Occupation 职业 S N 
//	 CompanyType 行业 S N 
//	 Position 职务 S N  
//	 PostTitle 职称 S N  
//
//	 EmployedYear 进入本单位年份 S N  
//	 InfoUpdateDate 信息更新日期 S N  
	
	//	@XStreamAlias("No")
	private String no;
	
	//	@XStreamAlias("CompanyName")
	private String companyName;
	
	//	@XStreamAlias("CompanyAddress")
	private String companyAddress;
	
	//	@XStreamAlias("occupation")
	private String Occupation;
	
	//	@XStreamAlias("CompanyType")
	private String companyType;
	
	//	@XStreamAlias("Position")
	private String position;
	
	//	@XStreamAlias("PostTitle")
	private String postTitle;
	
	//	@XStreamAlias("EmployedYear")
	private String employedYear;


	//	@XStreamAlias("InfoUpdateDate")
	private String infoUpdateDate;


	
	
	public String getNo() {
		return no;
	}


	public void setNo(String no) {
		this.no = no;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getCompanyAddress() {
		return companyAddress;
	}


	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}


	public String getOccupation() {
		return Occupation;
	}


	public void setOccupation(String occupation) {
		Occupation = occupation;
	}


	public String getCompanyType() {
		return companyType;
	}


	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public String getPostTitle() {
		return postTitle;
	}


	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}


	public String getEmployedYear() {
		return employedYear;
	}


	public void setEmployedYear(String employedYear) {
		this.employedYear = employedYear;
	}


	public String getInfoUpdateDate() {
		return infoUpdateDate;
	}


	public void setInfoUpdateDate(String infoUpdateDate) {
		this.infoUpdateDate = infoUpdateDate;
	}
	
	
	
}
