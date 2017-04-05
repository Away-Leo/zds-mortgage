package com.zdsoft.finance.cooperator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: Capitalist.java
 * @ClassName: Capitalist
 * @Description: 资方(刘伟二次维护)
 * @author liuwei
 * @date 2017年2月25日 上午11:28:12
 * @version V1.0
 */
@Entity
@Table(name = "cpt_capitalist")
public class Capitalist extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 未停用
	 */
	public static final String ENABLE = "YWDM0049001";
	
	/**
	 * 停用
	 */
	public static final String DISABLE = "YWDM0049002";

	/**
	 * 资方名称
	 */
	@Column(length = 64)
	private String capitalName;

	/**
	 * 资方类型
	 */
	@Column(length = 32)
	private String capitalistType;

	/**
	 * 是否停用
	 */
	@Column(length = 20)
	private String isStop;

	/**
	 * 类别(银行持有)
	 */
	@Column(length = 20)
	private String capitalistCategory;

	/**
	 * 简称
	 */
	@Column(length = 32)
	private String capitalShortName;

	/**
	 * 电话
	 */
	@Column(length = 32)
	private String telephone;

	/**
	 * 地区及详细地址省
	 */
	@Column(length = 32)
	private String addProvince;

	/**
	 * 地区及详细地址市
	 */
	@Column(length = 32)
	private String addCity;

	/**
	 * 地区及详细地址区
	 */
	@Column(length = 32)
	private String addCountry;

	/**
	 * 地区及详细地址
	 */
	@Column(length = 32)
	private String address;

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
	private String dutyParagraph;

	/**
	 * 银行帐号
	 */
	@Column(length = 32)
	private String bankAccount;

	/**
	 * 行业
	 */
	@Column(length = 20)
	private String industry;

	/**
	 * 是否有银行协议
	 */
	@Column(length = 20)
	private String isHasBankAgreement;

	/**
	 * 备注
	 */
	@Column(length = 512)
	private String remark;

	/**
	 * 上级机构
	 */
	@Column(length = 32)
	private String parentOrg;

	public String getCapitalName() {
		return capitalName;
	}

	public void setCapitalName(String capitalName) {
		this.capitalName = capitalName;
	}

	public String getCapitalistType() {
		return capitalistType;
	}

	public void setCapitalistType(String capitalistType) {
		this.capitalistType = capitalistType;
	}

	public String getCapitalistCategory() {
		return capitalistCategory;
	}

	public void setCapitalistCategory(String capitalistCategory) {
		this.capitalistCategory = capitalistCategory;
	}

	public String getCapitalShortName() {
		return capitalShortName;
	}

	public void setCapitalShortName(String capitalShortName) {
		this.capitalShortName = capitalShortName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getDutyParagraph() {
		return dutyParagraph;
	}

	public void setDutyParagraph(String dutyParagraph) {
		this.dutyParagraph = dutyParagraph;
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

	public String getParentOrg() {
		return parentOrg;
	}

	public void setParentOrg(String parentOrg) {
		this.parentOrg = parentOrg;
	}

	public String getIsStop() {
		return isStop;
	}

	public void setIsStop(String isStop) {
		this.isStop = isStop;
	}

	public String getIsHasBankAgreement() {
		return isHasBankAgreement;
	}

	public void setIsHasBankAgreement(String isHasBankAgreement) {
		this.isHasBankAgreement = isHasBankAgreement;
	}

}
