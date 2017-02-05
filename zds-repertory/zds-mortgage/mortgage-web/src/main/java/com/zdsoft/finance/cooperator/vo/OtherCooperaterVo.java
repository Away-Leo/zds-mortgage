package com.zdsoft.finance.cooperator.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.cooperator.entity.OtherCooperater;
/**
 * 其他合作单位表
 * @author Hisa
 *
 */
public class OtherCooperaterVo extends BaseVo<OtherCooperater>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 企业类型
	 */
	private String companyType;
	/**
	 * 简称
	 */
	private String shortName;
	/**
	 * 类别
	 */
	private String type;
	/**
	 * 往来单位名称
	 */
	private String contactCompanyName;
	/**
	 * 上级名称
	 */
	private String fatherName;
	/**
	 * 往来单位归属
	 */
	private String contactCompanyBelong;
	/**
	 * 往来单位归属
	 */
	private String contactCompanyBelongName;
	/**
	 * 电话
	 */
	private String callNumber;
	/**
	 * 传真
	 */
	private String foxNumber;
	/**
	 * 邮政编码
	 */
	private String postalCode;
	/**
	 * 网站
	 */
	private String website;
	/**
	 * 是否停用
	 */
	private String isStop;
	/**
	 * 地址(省)
	 */
	private String detailedProvince;
	/**
	 * 地址(市)
	 */
	private String detailedCity;
	/**
	 * 地址(区)
	 */
	private String detailedDistrict;
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
	private String dutyParagraph;
	/**
	 * 银行帐号
	 */
	private String bankAccount;
	/**
	 * 行业
	 */
	private String industry;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 地址编码
	 */
	private String detailedCode;
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContactCompanyName() {
		return contactCompanyName;
	}
	public void setContactCompanyName(String contactCompanyName) {
		this.contactCompanyName = contactCompanyName;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getContactCompanyBelong() {
		return contactCompanyBelong;
	}
	public void setContactCompanyBelong(String contactCompanyBelong) {
		this.contactCompanyBelong = contactCompanyBelong;
	}
	public String getCallNumber() {
		return callNumber;
	}
	public void setCallNumber(String callNumber) {
		this.callNumber = callNumber;
	}
	public String getFoxNumber() {
		return foxNumber;
	}
	public void setFoxNumber(String foxNumber) {
		this.foxNumber = foxNumber;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
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
	
	public String getContactCompanyBelongName() {
		return contactCompanyBelongName;
	}
	public void setContactCompanyBelongName(String contactCompanyBelongName) {
		this.contactCompanyBelongName = contactCompanyBelongName;
	}
	public String getDetailedProvince() {
		return detailedProvince;
	}
	public void setDetailedProvince(String detailedProvince) {
		this.detailedProvince = detailedProvince;
	}
	public String getDetailedCity() {
		return detailedCity;
	}
	public void setDetailedCity(String detailedCity) {
		this.detailedCity = detailedCity;
	}
	public String getDetailedDistrict() {
		return detailedDistrict;
	}
	public void setDetailedDistrict(String detailedDistrict) {
		this.detailedDistrict = detailedDistrict;
	}
	
	public String getDetailedCode() {
		return detailedCode;
	}
	public void setDetailedCode(String detailedCode) {
		this.detailedCode = detailedCode;
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
	public OtherCooperater toPO(){
		OtherCooperater po = new OtherCooperater();
		return super.toPo(this, po);
	}
}
