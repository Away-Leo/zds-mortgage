package com.zdsoft.finance.cooperator.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.cooperator.entity.Capitalist;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CapitalistVo.java
 * @ClassName: CapitalistVo
 * @Description: 资方vo
 * @author liuwei
 * @date 2017年3月2日 下午2:28:12
 * @version V1.0
 */
public class CapitalistVo extends BaseVo<Capitalist> {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 5275435307894569590L;

	/**
	 * 资方名称
	 */
	private String capitalName;

	/**
	 * 资方类型
	 */
	private String capitalistType;

	/**
	 * 资方类型Name
	 */
	private String capitalistTypeName;

	/**
	 * 是否停用
	 */
	private String isStop;

	/**
	 * 类别(银行持有)
	 */
	private String capitalistCategory;

	/**
	 * 简称
	 */
	private String capitalShortName;

	/**
	 * 电话
	 */
	private String telephone;

	/**
	 * 地区及详细地址省
	 */
	private String addProvince;

	/**
	 * 地区及详细地址市
	 */
	private String addCity;

	/**
	 * 地区及详细地址区
	 */
	private String addCountry;

	/**
	 * 地区及详细地址
	 */
	private String address;

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
	 * 是否有银行协议
	 */
	private String isHasBankAgreement;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 上级机构
	 */
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

	public String getIsStop() {
		return isStop;
	}

	public void setIsStop(String isStop) {
		this.isStop = isStop;
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

	public String getIsHasBankAgreement() {
		return isHasBankAgreement;
	}

	public void setIsHasBankAgreement(String isHasBankAgreement) {
		this.isHasBankAgreement = isHasBankAgreement;
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

	public String getCapitalistTypeName() {
		return capitalistTypeName;
	}

	public void setCapitalistTypeName(String capitalistTypeName) {
		this.capitalistTypeName = capitalistTypeName;
	}

	public CapitalistVo() {
		super();
	}

	public CapitalistVo(Capitalist capitalist) {
		super(capitalist, new String[] { "cooperatorBankVo", "trustVo" });
	}

	public CapitalistVo(Capitalist capitalist, String[] args, String[] simpleArgs) {
		super(capitalist, args, simpleArgs);
	}

	public Capitalist toPO() {
		Capitalist po = new Capitalist();
		return super.toPo(this, po);
	}

}
