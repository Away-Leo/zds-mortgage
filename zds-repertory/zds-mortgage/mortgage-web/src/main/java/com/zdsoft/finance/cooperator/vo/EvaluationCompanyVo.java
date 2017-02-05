package com.zdsoft.finance.cooperator.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.cooperator.entity.EvaluationCompany;

/**
 * 评估公司表
 * 
 * @author Hisa
 *
 */
public class EvaluationCompanyVo extends BaseVo<EvaluationCompany> {
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
	private String companyName;
	/**
	 * 简称
	 */
	private String shortName;
	/**
	 * 上级名称
	 */
	private String fatherName;
	/**
	 * 邮政编码
	 */
	private String postalcode;
	/**
	 * 网站
	 */
	private String website;
	/**
	 * 是否停用
	 */
	private String isStop;
	private String isStopNm;
	/**
	 * 类别
	 */
	private String evaluateType;
	private String evaluateTypeNm;
	/**
	 * 地址
	 */
	private String regionCode;
	private String address;
	/**
	 * 成立时间
	 */
	private String foundDate;
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

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
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

	public String getEvaluateType() {
		return evaluateType;
	}

	public void setEvaluateType(String evaluateType) {
		this.evaluateType = evaluateType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getIsStop() {
		return isStop;
	}

	public void setIsStop(String isStop) {
		this.isStop = isStop;
	}

	public String getFoundDate() {
		return foundDate;
	}

	public void setFoundDate(String foundDate) {
		this.foundDate = foundDate;
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

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getIsStopNm() {
		return isStopNm;
	}

	public void setIsStopNm(String isStopNm) {
		this.isStopNm = isStopNm;
	}

	public String getEvaluateTypeNm() {
		return evaluateTypeNm;
	}

	public void setEvaluateTypeNm(String evaluateTypeNm) {
		this.evaluateTypeNm = evaluateTypeNm;
	}

	public EvaluationCompanyVo() {
		super();
	}

	public EvaluationCompanyVo(EvaluationCompany evaluationCompany) {
		super(evaluationCompany);
	}

	public EvaluationCompanyVo(EvaluationCompany evaluationCompany, String[] args, String[] simpleArgs) {
		super(evaluationCompany, args, simpleArgs);
	}

	public EvaluationCompany toPO() {
		EvaluationCompany po = new EvaluationCompany();
		return super.toPo(this, po);
	}
}
