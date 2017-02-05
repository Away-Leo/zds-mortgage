package com.zdsoft.finance.contract.controller;

public class ContractModel {
private int id;
private String applyName="张三";
private String payFrom="信托";
private String payFromClass="信托类型";
private String contractStatus="已通过";
public int getid() {
	return id;
}
public void setid(int id) {
	this.id = id;
}
public String getapplyName() {
	return applyName;
}
public void setapplyName(String applyName) {
	this.applyName = applyName;
}
public String getpayFrom() {
	return payFrom;
}
public void setpayFrom(String payFrom) {
	this.payFrom = payFrom;
}
public String getpayFromClass() {
	return payFromClass;
}
public void setpayFromClass(String payFromClass) {
	this.payFromClass = payFromClass;
}
public String getContractStatus() {
	return contractStatus;
}
public void setContractStatus(String ContractStatus) {
	this.contractStatus = ContractStatus;
}

}
