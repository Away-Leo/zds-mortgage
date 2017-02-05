package com.zdsoft.finance.cedit.api.entity;

//import com.thoughtworks.xstream.annotations.XStreamAlias;

//	@XStreamAlias("ResideInfo")
public class ResideInfo {

//	
//	居住信息【ResideInfo】 
//	标识符 数据项名称 类型 长度 其它 
//	No 编号 S N 
//	ResideAddr 居住地址 S N  
//	ResideStatus 居住状况 S N  
//	InfoUpdateDate 信息更新日期 S N 
	//	@XStreamAlias("No")
	private String  no;
	
	//	@XStreamAlias("ResideAddr")
	private String  resideAddr;  // 身份证信息
	
	//	@XStreamAlias("ResideStatus") 
	private String resideStatus; // 配偶信息
	
	
	//	@XStreamAlias("InfoUpdateDate") 
	private String infoUpdateDate; // 配偶信息


	public String getNo() {
		return no;
	}


	public void setNo(String no) {
		this.no = no;
	}


	public String getResideAddr() {
		return resideAddr;
	}


	public void setResideAddr(String resideAddr) {
		this.resideAddr = resideAddr;
	}


	public String getResideStatus() {
		return resideStatus;
	}


	public void setResideStatus(String resideStatus) {
		this.resideStatus = resideStatus;
	}


	public String getInfoUpdateDate() {
		return infoUpdateDate;
	}


	public void setInfoUpdateDate(String infoUpdateDate) {
		this.infoUpdateDate = infoUpdateDate;
	}
	

	
}
