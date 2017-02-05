package com.zdsoft.finance.cedit.api.entity;

//import com.thoughtworks.xstream.annotations.XStreamAlias;

//	@XStreamAlias("SpouseInfo")
public class SpouseInfo {
	
//	 配偶信息【SpouseInfo】 
//	 标识符 数据项名称 类型 长度 其它 
//	 SpouseName 姓名 S N  
//	 SpouseCertType 证件类型 S N  
//	 SpouseCredNum 证件号码 S N 
//	 SpouseCompany 工作单位 S N  
//	 SpousePhone 联系电话 S N   

	//	@XStreamAlias("SpouseName")
	private String spouseName;
	
	//	@XStreamAlias("SpouseCertType")
	private String spouseCertType;
	
	//	@XStreamAlias("SpouseCredNum")
	private String spouseCredNum;
	
	//	@XStreamAlias("SpouseCompany")
	private String spouseCompany;
	
	//	@XStreamAlias("SpousePhone")
	private String spousePhone;

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public String getSpouseCertType() {
		return spouseCertType;
	}

	public void setSpouseCertType(String spouseCertType) {
		this.spouseCertType = spouseCertType;
	}

	public String getSpouseCredNum() {
		return spouseCredNum;
	}

	public void setSpouseCredNum(String spouseCredNum) {
		this.spouseCredNum = spouseCredNum;
	}

	public String getSpouseCompany() {
		return spouseCompany;
	}

	public void setSpouseCompany(String spouseCompany) {
		this.spouseCompany = spouseCompany;
	}

	public String getSpousePhone() {
		return spousePhone;
	}

	public void setSpousePhone(String spousePhone) {
		this.spousePhone = spousePhone;
	}
	
	


}
