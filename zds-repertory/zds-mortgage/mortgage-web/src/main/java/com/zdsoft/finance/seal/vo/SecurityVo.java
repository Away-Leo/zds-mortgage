package com.zdsoft.finance.seal.vo;

import java.math.BigDecimal;

public class SecurityVo {
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	public String getSecurityAddress() {
		return securityAddress;
	}
	public void setSecurityAddress(String securityAddress) {
		this.securityAddress = securityAddress;
	}
	public int getSecurityYears() {
		return securityYears;
	}
	public void setSecurityYears(int securityYears) {
		this.securityYears = securityYears;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public String getFCType() {
		return FCType;
	}
	public void setFCType(String fCType) {
		FCType = fCType;
	}
	public String getFCProperty() {
		return FCProperty;
	}
	public void setFCProperty(String fCProperty) {
		FCProperty = fCProperty;
	}
	public String getIsSpruce() {
		return isSpruce;
	}
	public void setIsSpruce(String isSpruce) {
		this.isSpruce = isSpruce;
	}
	public BigDecimal getEvaluating() {
		return Evaluating;
	}
	public void setEvaluating(BigDecimal evaluating) {
		Evaluating = evaluating;
	}
	// 小区名称
	private String communityName = "珠江新城";
	// 押品地址
	private String securityAddress = "珠江新城";
	// 楼龄
	private int securityYears = 53;
	// 面积
	private int area = 55;
	// 房产性质
	private String FCType = "商用";
	// 房产权属
	private String FCProperty = "商用";
	// 是否有装修
	private String isSpruce = "是";
	// 综合评估价
	private BigDecimal Evaluating = new BigDecimal("89.123"); 

}
