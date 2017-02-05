package com.zdsoft.finance.cooperator.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 评估公司表
 * 
 * @author Hisa
 *
 */
@Entity
@Table(name = "zf_evaluation_company")
public class EvaluationCompany extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 企业类型
	 */
	private String companyType;
	/**
	 * 评估公司名称
	 */
	@Column(length = 64)
	private String companyName;
	/**
	 * 简称
	 */
	@Column(length = 64)
	private String shortName;
	/**
	 * 上级名称
	 */
	@Column(length = 64)
	private String fatherName;
	/**
	 * 邮政编码
	 */
	@Column(length = 16)
	private String postalcode;
	/**
	 * 网站
	 */
	@Column(length = 64)
	private String website;
	/**
	 * 是否停用
	 */
	@Column(length = 10)
	private String isStop;
	/**
	 * 类别
	 */
	@Column(length = 10)
	private String evaluateType;
	/**
	 * 地址
	 */
	@Column(length = 200)
	private String address;

	/**
	 * 地区CODE
	 */
	@Column(length = 32)
	private String regionCode;
	/**
	 * 成立时间
	 */
	private Long foundDate;
	/**
	 * 法人
	 */
	@Column(length = 64)
	private String legalPerson;
	/**
	 * 税号
	 */
	@Column(length = 64)
	private String dutyParagraph;
	/**
	 * 银行帐号
	 */
	@Column(length = 32)
	private String bankAccount;
	
	/**
	 * 行业
	 */
	@Column(length = 15)
	private String industry;
	/**
	 * 备注
	 */
	@Column(length = 3000)
	private String remark;
	// 登录信息
	/**
	 * 登录手机号码
	 */
	@Column(length = 32)
	private String loginPhoneNumber;
	/**
	 * 密码
	 */
	@Column(length = 32)
	private String password;
	/**
	 * 是否允许登录
	 */
	@Column(length = 12)
	private String isAllowLogin;
	/**
	 * 最近登录日期
	 */
	private Long nearestLoginDate;

	@Column(length = 1)
	private String logicDelelte;
	
	@OneToMany(mappedBy = "evaluationCompany")
	private List<EvaluationCompanyContact> evaluationCompanyContact;

	
	public String getLogicDelelte() {
		return logicDelelte;
	}

	public void setLogicDelelte(String logicDelelte) {
		this.logicDelelte = logicDelelte;
	}

	public List<EvaluationCompanyContact> getEvaluationCompanyContact() {
		return evaluationCompanyContact;
	}

	public void setEvaluationCompanyContact(List<EvaluationCompanyContact> evaluationCompanyContact) {
		this.evaluationCompanyContact = evaluationCompanyContact;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
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

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLoginPhoneNumber() {
		return loginPhoneNumber;
	}

	public void setLoginPhoneNumber(String loginPhoneNumber) {
		this.loginPhoneNumber = loginPhoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIsAllowLogin() {
		return isAllowLogin;
	}

	public void setIsAllowLogin(String isAllowLogin) {
		this.isAllowLogin = isAllowLogin;
	}

	public Long getNearestLoginDate() {
		return nearestLoginDate;
	}

	public void setNearestLoginDate(Long nearestLoginDate) {
		this.nearestLoginDate = nearestLoginDate;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getIsStop() {
		return isStop;
	}

	public void setIsStop(String isStop) {
		this.isStop = isStop;
	}

	public String getEvaluateType() {
		return evaluateType;
	}

	public void setEvaluateType(String evaluateType) {
		this.evaluateType = evaluateType;
	}

}
