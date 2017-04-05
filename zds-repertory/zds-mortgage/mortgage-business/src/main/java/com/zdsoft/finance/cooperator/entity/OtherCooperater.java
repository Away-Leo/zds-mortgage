package com.zdsoft.finance.cooperator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: OtherCooperater.java
 * @ClassName: OtherCooperater
 * @Description: 其他合作单位(刘伟二次维护)
 * @author liuwei
 * @date 2017年2月25日 上午11:35:10
 * @version V1.0
 */
@Entity
@Table(name = "cpt_other_cooperater")
public class OtherCooperater extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 企业类型
	 */
	@Column(length = 20)
	private String enterpriseType;

	/**
	 * 合作单位名称
	 */
	@Column(length = 128)
	private String companyName;

	/**
	 * 简称
	 */
	@Column(length = 64)
	private String shortName;

	/**
	 * 类别
	 */
	@Column(length = 20)
	private String companyType;

	/**
	 * 上级机构
	 */
	@Column(length = 32)
	private String parentOrg;

	/**
	 * 合作单位归属类型
	 */
	@Column(length = 20)
	private String companyBelong;

	/**
	 * 合作单位归属关联Code
	 */
	@Column(length = 32)
	private String companyBelongRelevanceCode;

	/**
	 * 合作单位归属关联Name
	 */
	@Column(length = 128)
	private String companyBelongRelevanceName;

	/**
	 * 电话
	 */
	@Column(length = 32)
	private String telephone;

	/**
	 * 传真
	 */
	@Column(length = 32)
	private String fax;

	/**
	 * 邮政编码
	 */
	@Column(length = 32)
	private String zipCode;

	/**
	 * 网站
	 */
	@Column(length = 32)
	private String website;

	/**
	 * 是否停用
	 */
	@Column(length = 20)
	private String isStop;

	/**
	 * 地址省
	 */
	@Column(length = 32)
	private String addProvince;

	/**
	 * 地址市
	 */
	@Column(length = 32)
	private String addCity;

	/**
	 * 地址区
	 */
	@Column(length = 32)
	private String addCountry;

	/**
	 * 地址
	 */
	@Column(length = 128)
	private String detailedAddress;

	/**
	 * 成立时间
	 */
	@Column
	private Long foundDate;

	/**
	 * 法人
	 */
	@Column(length = 32)
	private String legalPerson;

	/**
	 * 税号
	 */
	@Column(length = 32)
	private String taxNo;

	/**
	 * 银行账号
	 */
	@Column(length = 32)
	private String bankAccount;

	/**
	 * 行业
	 */
	@Column(length = 20)
	private String industry;

	/**
	 * 备注
	 */
	@Column(length = 512)
	private String remark;

	public String getEnterpriseType() {
		return enterpriseType;
	}

	public void setEnterpriseType(String enterpriseType) {
		this.enterpriseType = enterpriseType;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getParentOrg() {
		return parentOrg;
	}

	public void setParentOrg(String parentOrg) {
		this.parentOrg = parentOrg;
	}

	public String getCompanyBelong() {
		return companyBelong;
	}

	public void setCompanyBelong(String companyBelong) {
		this.companyBelong = companyBelong;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getAddProvince() {
		return addProvince;
	}

	public void setAddProvince(String addProvince) {
		this.addProvince = addProvince;
	}

	public String getAddCity() {
		return addCity;
	}

	public void setAddCity(String addCity) {
		this.addCity = addCity;
	}

	public String getAddCountry() {
		return addCountry;
	}

	public void setAddCountry(String addCountry) {
		this.addCountry = addCountry;
	}

	public String getDetailedAddress() {
		return detailedAddress;
	}

	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}

	public Long getFoundDate() {
		return foundDate;
	}

	public void setFoundDate(Long foundDate) {
		this.foundDate = foundDate;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getTaxNo() {
		return taxNo;
	}

	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsStop() {
		return isStop;
	}

	public void setIsStop(String isStop) {
		this.isStop = isStop;
	}

	public String getCompanyBelongRelevanceCode() {
		return companyBelongRelevanceCode;
	}

	public void setCompanyBelongRelevanceCode(String companyBelongRelevanceCode) {
		this.companyBelongRelevanceCode = companyBelongRelevanceCode;
	}

	public String getCompanyBelongRelevanceName() {
		return companyBelongRelevanceName;
	}

	public void setCompanyBelongRelevanceName(String companyBelongRelevanceName) {
		this.companyBelongRelevanceName = companyBelongRelevanceName;
	}

}
