package com.zdsoft.finance.cooperator.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.cooperator.entity.OtherCooperater;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: OtherCooperaterVo.java
 * @ClassName: OtherCooperaterVo
 * @Description: 其他合作单位Vo
 * @author liuwei
 * @date 2017年3月2日 下午7:59:50
 * @version V1.0
 */
public class OtherCooperaterVo extends BaseVo<OtherCooperater> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 企业类型
	 */
	private String enterpriseType;

	/**
	 * 企业类型Name
	 */
	private String enterpriseTypeName;

	/**
	 * 合作单位名称
	 */
	private String companyName;

	/**
	 * 简称
	 */
	private String shortName;

	/**
	 * 类别
	 */
	private String companyType;

	/**
	 * 类别名称
	 */
	private String companyTypeName;

	/**
	 * 上级机构
	 */
	private String parentOrg;

	/**
	 * 合作单位归属类型
	 */
	private String companyBelong;

	/**
	 * 合作单位归属类型Name
	 */
	private String companyBelongName;

	/**
	 * 合作单位归属关联
	 */
	private String companyBelongRelevanceCode;

	/**
	 * 合作单位归属关联Name
	 */
	private String companyBelongRelevanceName;

	/**
	 * 电话
	 */
	private String telephone;

	/**
	 * 传真
	 */
	private String fax;

	/**
	 * 邮政编码
	 */
	private String zipCode;

	/**
	 * 网站
	 */
	private String website;

	/**
	 * 是否停用
	 */
	private String isStop;

	/**
	 * 是否停用Name
	 */
	private String isStopName;

	/**
	 * 地址省
	 */
	private String addProvince;

	/**
	 * 地址市
	 */
	private String addCity;

	/**
	 * 地址区
	 */
	private String addCountry;

	/**
	 * 地址
	 */
	private String detailedAddress;

	/**
	 * 成立时间
	 */
	private Long foundDate;

	/**
	 * 法人
	 */
	private String legalPerson;

	/**
	 * 税号
	 */
	private String taxNo;

	/**
	 * 银行账号
	 */
	private String bankAccount;

	/**
	 * 行业
	 */
	private String industry;

	/**
	 * 行业name
	 */
	private String industryName;

	/**
	 * 备注
	 */
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

	public String getIsStop() {
		return isStop;
	}

	public void setIsStop(String isStop) {
		this.isStop = isStop;
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

	public String getCompanyTypeName() {
		return companyTypeName;
	}

	public void setCompanyTypeName(String companyTypeName) {
		this.companyTypeName = companyTypeName;
	}

	public String getIsStopName() {
		return isStopName;
	}

	public void setIsStopName(String isStopName) {
		this.isStopName = isStopName;
	}

	public String getEnterpriseTypeName() {
		return enterpriseTypeName;
	}

	public void setEnterpriseTypeName(String enterpriseTypeName) {
		this.enterpriseTypeName = enterpriseTypeName;
	}

	public String getCompanyBelongName() {
		return companyBelongName;
	}

	public void setCompanyBelongName(String companyBelongName) {
		this.companyBelongName = companyBelongName;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
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

	public OtherCooperaterVo() {
		super();
	}

	public OtherCooperaterVo(OtherCooperater t) {
		super(t);
	}

	public OtherCooperaterVo(OtherCooperater t, String[] args, String[] simpleArgs) {
		super(t, args, simpleArgs);
	}

	public OtherCooperater toPO() {
		OtherCooperater po = new OtherCooperater();
		return super.toPo(this, po);
	}
}
