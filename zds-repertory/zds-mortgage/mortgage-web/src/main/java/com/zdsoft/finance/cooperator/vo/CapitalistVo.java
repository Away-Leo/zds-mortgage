package com.zdsoft.finance.cooperator.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.cooperator.entity.Capitalist;

/**
 * 资方表
 * 
 * @author Hisa
 *
 */
public class CapitalistVo extends BaseVo<Capitalist> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 合作方名称
	 */
	private String cooperatorName;
	/**
	 * 合作方简称
	 */
	private String cooperatorShortName;
	/**
	 * 上级名称
	 */
	private String parentName;
	/**
	 * 合作方类型
	 */
	private String cooperatorType;
	/**
	 * 合作方地址
	 */
	private String cooperatorAddress;
	/**
	 * 地区CODE
	 */
	private String regionCode;
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
	 * 行业
	 */
	private String industry;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 是否停用
	 */
	private String isStop;
	/**
	 * 网站
	 */
	private String website;

	/**
	 * 电话
	 */
	private String contactTelNumber;

	/**
	 * 资方类型
	 */
	private String capitalistType;
	private String capitalistTypeName;
	/**
	 * 资方类别
	 */
	private String capitalistCategory;
	/**
	 * 是否有银行协议
	 */
	private String isHasBankAgreement;
	/**
	 * 协议名称
	 */
	private String agreementName;
	/**
	 * 逻辑删除
	 */
	private String logicDelelte;
	private String bankAccountShow;
	/**
	 * 银行卡帐号
	 */
	private CooperatorBankVo cooperatorBankVo;
	/**
	 * 信托计划
	 */
	private CapitalistTrustVo trustVo;

	public String getBankAccountShow() {
		return bankAccountShow;
	}

	public void setBankAccountShow(String bankAccountShow) {
		this.bankAccountShow = bankAccountShow;
	}

	public CapitalistTrustVo getTrustVo() {
		return trustVo;
	}

	public void setTrustVo(CapitalistTrustVo trustVo) {
		this.trustVo = trustVo;
	}

	public CooperatorBankVo getCooperatorBankVo() {
		return cooperatorBankVo;
	}

	public void setCooperatorBankVo(CooperatorBankVo cooperatorBankVo) {
		this.cooperatorBankVo = cooperatorBankVo;
	}

	public String getCapitalistTypeName() {
		return capitalistTypeName;
	}

	public void setCapitalistTypeName(String capitalistTypeName) {
		this.capitalistTypeName = capitalistTypeName;
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

	public String getDutyParagraph() {
		return dutyParagraph;
	}

	public void setDutyParagraph(String dutyParagraph) {
		this.dutyParagraph = dutyParagraph;
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

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
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

	public String getContactTelNumber() {
		return contactTelNumber;
	}

	public void setContactTelNumber(String contactTelNumber) {
		this.contactTelNumber = contactTelNumber;
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

	public String getIsHasBankAgreement() {
		return isHasBankAgreement;
	}

	public void setIsHasBankAgreement(String isHasBankAgreement) {
		this.isHasBankAgreement = isHasBankAgreement;
	}

	public String getAgreementName() {
		return agreementName;
	}

	public void setAgreementName(String agreementName) {
		this.agreementName = agreementName;
	}

	public String getLogicDelelte() {
		return logicDelelte;
	}

	public void setLogicDelelte(String logicDelelte) {
		this.logicDelelte = logicDelelte;
	}
	public String getFoundDateNm() {
		Long found = this.getFoundDate();
		if (found != null && found > 2000) {
			String t = String.valueOf(found);
			return t.substring(0, 4) + "-" + t.substring(4, 6) + "-" + t.substring(6, 8);
		}
		return "";
	}
	public CapitalistVo() {
		super();
	}

	public CapitalistVo(Capitalist capitalist) {
		super(capitalist, new String[] { "cooperatorBankVo", "trustVo" });
		this.setCooperatorBankVo(new CooperatorBankVo(capitalist.getCooperatorBank()));
		this.setTrustVo(new CapitalistTrustVo(capitalist.getCapitalistTrust()));
	}

	public CapitalistVo(Capitalist capitalist, String[] args, String[] simpleArgs) {
		super(capitalist, args, simpleArgs);
	}

	public Capitalist toPO() {
		Capitalist po = new Capitalist();
		return super.toPo(this, po);
	}

}
