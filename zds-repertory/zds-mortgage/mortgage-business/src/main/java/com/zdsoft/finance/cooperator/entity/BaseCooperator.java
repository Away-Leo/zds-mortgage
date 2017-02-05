package com.zdsoft.finance.cooperator.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.codehaus.jackson.annotate.JsonIgnoreType;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 合作方
 * 
 * @author Hisa
 *
 */
@MappedSuperclass
@JsonIgnoreType
public abstract class BaseCooperator extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 合作方名称
	 */
	@Column(length = 128)
	private String cooperatorName;
	/**
	 * 合作方简称
	 */
	@Column(length = 32)
	private String cooperatorShortName;
	/**
	 * 上级名称
	 */
	@Column(length = 32)
	private String parentName;
	/**
	 * 合作方类型
	 */
	@Column(length = 20)
	private String cooperatorType;
	/**
	 * 合作方地址
	 */
	@Column(length = 128)
	private String cooperatorAddress;
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
	@Column(length = 32)
	private String legalPerson;
	/**
	 * 税号
	 */
	@Column(length = 32)
	private String dutyParagraph;
	/**
	 * 行业
	 */
	@Column(length = 20)
	private String industry;
	/**
	 * 备注
	 */
	@Column(length = 500)
	private String remark;
	/**
	 * 是否停用
	 */
	@Column(length = 20)
	private String isStop;
	/**
	 * 网站
	 */
	@Column(length = 128)
	private String website;

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getCooperatorName() {
		return cooperatorName;
	}

	public void setCooperatorName(String cooperatorName) {
		this.cooperatorName = cooperatorName;
	}

	public String getCooperatorShortName() {
		return cooperatorShortName;
	}

	public void setCooperatorShortName(String cooperatorShortName) {
		this.cooperatorShortName = cooperatorShortName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getCooperatorType() {
		return cooperatorType;
	}

	public void setCooperatorType(String cooperatorType) {
		this.cooperatorType = cooperatorType;
	}

	public String getCooperatorAddress() {
		return cooperatorAddress;
	}

	public void setCooperatorAddress(String cooperatorAddress) {
		this.cooperatorAddress = cooperatorAddress;
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

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

}
