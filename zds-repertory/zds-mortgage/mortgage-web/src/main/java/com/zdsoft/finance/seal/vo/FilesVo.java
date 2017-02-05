package com.zdsoft.finance.seal.vo;

import java.util.Date;

public class FilesVo {
	private int id=0;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBelongClass() {
		return belongClass;
	}
	public void setBelongClass(String belongClass) {
		this.belongClass = belongClass;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getPingyingCode() {
		return pingyingCode;
	}
	public void setPingyingCode(String pingyingCode) {
		this.pingyingCode = pingyingCode;
	}
	public int getNumCode() {
		return numCode;
	}
	public void setNumCode(int numCode) {
		this.numCode = numCode;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	private String belongClass = "个人信息类";
	private String className = "保证人基本资料";
	private String pingyingCode = "grx";
	private int numCode = 0;
	private String fileName = "张三房产证正面.jpg";
	private String documentName = "张三房产证正面";
	private String createBy = "王玮";
	private String createDate = "2017-01-11";
	private String from = "面签";

}
