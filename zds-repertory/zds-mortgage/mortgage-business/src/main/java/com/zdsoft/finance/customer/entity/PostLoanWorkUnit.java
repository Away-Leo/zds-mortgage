package com.zdsoft.finance.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 贷后工作单位
 * @author <a href="mailto:502549751@qq.com">zhangchao</a>
 * @createTime: 2017-01-10 14::41
 * @version: 1.0
 *
 */
@Entity
@Table(name = "cus_post_loan_work_unit")
public class PostLoanWorkUnit extends BaseEntity{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 客户id
     */
    @Column(length = 32)
	private String customerId;
    
    /**
     * 姓名
     */
    @Column(length = 128)
	private String workUnitName;
    
    /**
     * 单位名称
     */
    @Column(length = 128)
	private String companyName;
    
    /**
     * 单位电话
     */
    @Column(length = 15)
	private String phoneNumber;
    
    /**
     * 单位性质(w01211：国有企业；w01212：国有控股企业；w01213：外资企业；w01214：合资企业；w01215：私营企业；w01216：事业企业；w01217：国家行政机关；w01218：政府；)
     */
    @Column(length = 20)
	private String workUnitNature;
    
    /**
     * 工作年限
     */
    @Column
	private Integer workYears;
    
    /**
     * 职务(p01261：高管；)
     */
    @Column(length = 20)
	private String position;
    
    /**
     * 行业类型(一级行业类型)
     */
    @Column(length = 20)
	private String industryType;
    
    /**
     * 行业(二级行业类型)
     */
    @Column(length = 20)
	private String industry;
    
    /**
     * 省
     */
    @Column(length = 20)
	private String province;
    
    /**
     * 市
     */
    @Column(length = 20)
	private String city;
    
    /**
     * 区
     */
    @Column(length = 20)
	private String district;
    
    /**
     * 单位地址
     */
    @Column(length = 500)
	private String workUnitAddress;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

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

	public String getWorkUnitAddress() {
		return workUnitAddress;
	}

	public void setWorkUnitAddress(String workUnitAddress) {
		this.workUnitAddress = workUnitAddress;
	}
    
}
