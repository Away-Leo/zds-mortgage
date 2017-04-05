package com.zdsoft.finance.casemanage.promotion.otherproperty.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.casemanage.promotion.otherproperty.entity.CustomerHouse;
import com.zdsoft.finance.common.base.BaseVo;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CustomerHouseVo.java 
 * @ClassName: CustomerHouseVo 
 * @Description: 其他资产之房产Vo
 * @author xiongpan
 * @date 2017年2月25日 上午10:50:08 
 * @version V1.0 
 */ 
public class CustomerHouseVo extends BaseVo<CustomerHouse>{

	private static final long serialVersionUID = -4168571677833616572L;
	
	/**
	 * 权属人
	 */
	private String owner;
	
	/**
	 * 房产性质
	 */
	private String houseProperty;
	
	/**
	 * 是否在押
	 */
	private String isPledge;
	
	/**
	 * 抵押权人
	 */
	private String pledgePerson;
	
	/**
	 * 抵押金额(元)
	 */
	private BigDecimal pledgeAmount;
	
	/**
	 * 抵押期限
	 */
	private Integer pledgeDeadLine;
	
	/**
	 * 抵押期限单位
	 */
	private String pledgeDeadLineUnit;
	
	/**
	 * 面积
	 */
	private BigDecimal measureArea;
	
	/**
	 * 价值(元)
	 */
	private BigDecimal worth;
	
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
	 * 地址
	 */
	private String address;
	
	/**
	 * 案件ID
	 */
	private String caseApplyId;

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getHouseProperty() {
		return houseProperty;
	}

	public void setHouseProperty(String houseProperty) {
		this.houseProperty = houseProperty;
	}

	public String getIsPledge() {
		return isPledge;
	}

	public void setIsPledge(String isPledge) {
		this.isPledge = isPledge;
	}

	public String getPledgePerson() {
		return pledgePerson;
	}

	public void setPledgePerson(String pledgePerson) {
		this.pledgePerson = pledgePerson;
	}

	public BigDecimal getPledgeAmount() {
		return pledgeAmount;
	}

	public void setPledgeAmount(BigDecimal pledgeAmount) {
		this.pledgeAmount = pledgeAmount;
	}

	public Integer getPledgeDeadLine() {
		return pledgeDeadLine;
	}

	public void setPledgeDeadLine(Integer pledgeDeadLine) {
		this.pledgeDeadLine = pledgeDeadLine;
	}

	public String getPledgeDeadLineUnit() {
		return pledgeDeadLineUnit;
	}

	public void setPledgeDeadLineUnit(String pledgeDeadLineUnit) {
		this.pledgeDeadLineUnit = pledgeDeadLineUnit;
	}

	public BigDecimal getMeasureArea() {
		return measureArea;
	}

	public void setMeasureArea(BigDecimal measureArea) {
		this.measureArea = measureArea;
	}

	public BigDecimal getWorth() {
		return worth;
	}

	public void setWorth(BigDecimal worth) {
		this.worth = worth;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}
	
	


}
