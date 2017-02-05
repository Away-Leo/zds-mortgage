package com.zdsoft.finance.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:beforeWorkUnit.java
 * @Package:com.zdsoft.finance.customer.entity
 * @Description:贷前工作单位
 * @author: xj
 * @date:2017年1月11日 上午11:56:17
 * @version:v1.0
 */
@Entity
@Table(name = "cus_before_work_unit")
public class BeforeWorkUnit extends BaseEntity {

	/**
	 * 用一句话描述这个变量表示什么
	 */
	private static final long serialVersionUID = 4441967909280862238L;
	/**
	 * 客户
	 */
	@Column(length=32)
	private String customerId;
	/**
	 * 姓名
	 */
	@Column(length=128)
	private  String workUnitName;
	/**
	 * 单位名称
	 */
	@Column(length=128)
	private  String companyName;
	/**
	 * 单位电话
	 */
	@Column(length=15)
	private  String phoneNumber;
	/**
	 * 单位性质
	 */
	@Column(length=20)
	private  String workUnitNature;
	/**
	 * 工作年限
	 */
	@Column
	private  Integer workYears;
	/**
	 * 职务
	 */
	@Column(length=20)
	private  String position;
	/**
	 * 行业类型
	 */
	@Column(length=20)
	private  String industryType;
	/**
	 * 行业
	 */
	@Column(length=20)
	private  String industry;
	/**
	 * 省
	 */
	@Column(length=20)
	private  String province;
	/**
	 * 市
	 */
	@Column(length=20)
	private  String city;
	/**
	 * 区
	 */
	@Column(length=20)
	private  String district;
	
	/**
	 * 单位地址
	 */
	@Column(length=500)
	private  String workUnitAddress;
	public String getWorkUnitName() {
		return workUnitName;
	}
	public void setWorkUnitName(String workUnitName) {
		this.workUnitName = workUnitName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getWorkUnitNature() {
		return workUnitNature;
	}
	public void setWorkUnitNature(String workUnitNature) {
		this.workUnitNature = workUnitNature;
	}
	public Integer getWorkYears() {
		return workYears;
	}
	public void setWorkYears(Integer workYears) {
		this.workYears = workYears;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getIndustryType() {
		return industryType;
	}
	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getWorkUnitAddress() {
		return workUnitAddress;
	}
	public void setWorkUnitAddress(String workUnitAddress) {
		this.workUnitAddress = workUnitAddress;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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
	
	
}
