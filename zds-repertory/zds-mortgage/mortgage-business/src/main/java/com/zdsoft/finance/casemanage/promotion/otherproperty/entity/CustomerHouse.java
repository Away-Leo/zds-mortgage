package com.zdsoft.finance.casemanage.promotion.otherproperty.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:CustomerHouse.java
 * @Package:com.zdsoft.finance.casemanage.promotion.otherproperty.entity
 * @Description:其他资产之房产情况
 * @author: xiongpan
 * @date:2017年2月14日 下午3:38:59
 * @version:v1.0
 */
@Entity
@Table(name = "case_house")
public class CustomerHouse extends BaseEntity{

	private static final long serialVersionUID = -5208856678451772683L;
	
	/**
	 * 权属人
	 */
	@Column(length=128)
	private String owner;
	
	/**
	 * 房产性质
	 */
	@Column(length=30)
	private String houseProperty;
	
	/**
	 * 是否在押
	 */
	@Column(length=20)
	private String isPledge;
	
	/**
	 * 抵押权人
	 */
	@Column(length=128)
	private String pledgePerson;
	
	/**
	 * 抵押金额(元)
	 */
	@Column(precision = 18, scale = 2)
	private BigDecimal pledgeAmount = BigDecimal.ZERO;
	
	/**
	 * 抵押期限
	 */
	@Column
	private Integer pledgeDeadLine = new Integer(0);
	
	/**
	 * 抵押期限单位
	 */
	@Column(length = 20)
	private String pledgeDeadLineUnit;
	
	/**
	 * 面积
	 */
	@Column(precision = 18, scale = 2)
	private BigDecimal measureArea = BigDecimal.ZERO;
	
	/**
	 * 价值(元)
	 */
	@Column(precision = 18, scale = 2)
	private BigDecimal worth = BigDecimal.ZERO;
	
	/**
	 * 地址(省)
	 */
	@Column(length=30)
	private String province;
	
	/**
	 * 地址(市)
	 */
	@Column(length=30)
	private String city;
	
	/**
	 * 地址(区)
	 */
	@Column(length=30)
	private String district;
	
	/**
	 * 地址
	 */
	@Column(length=30)
	private String address;
	
	/**
	 * 案件ID
	 */
	@Column(length=32)
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

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	
}
