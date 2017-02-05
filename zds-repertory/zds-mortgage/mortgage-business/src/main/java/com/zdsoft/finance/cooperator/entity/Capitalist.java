package com.zdsoft.finance.cooperator.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 资方表
 * 
 * @author Hisa
 *
 */
@Entity
@Table(name = "cpt_capitalist")
public class Capitalist extends BaseCooperator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 资方类型
	 */
	@Column(length = 32)
	private String capitalistType;
	/**
	 * 资方类别
	 */
	@Column(length = 32)
	private String capitalistCategory;
	/**
	 * 是否有银行协议
	 */
	@Column(length = 32)
	private String isHasBankAgreement;
	/**
	 * 协议名称
	 */
	@Column(length = 32)
	private String agreementName;
	/**
	 * 逻辑删除
	 */
	@Column(length = 1)
	private String logicDelelte;

	@Column(length = 15)
	private String contactTelNumber;
	/**
	 * 资方联系方式表
	 */
	@OneToMany(mappedBy = "capitalist")
	private List<ContactsInfo> contactsInfo;
	/**
	 * 合作协议表
	 */
	@OneToMany(mappedBy = "capitalist")
	private List<CooperateIdea> cooperateIdea;

	/**
	 * 信托资方
	 */
	@OneToOne
	@JoinColumn(name = "trustId", insertable = true, unique = true)
	private CapitalistTrust capitalistTrust;

	@OneToOne
	@JoinColumn(name = "bankId", insertable = true, unique = true)
	private CooperatorBank cooperatorBank;

	public String getContactTelNumber() {
		return contactTelNumber;
	}

	public void setContactTelNumber(String contactTelNumber) {
		this.contactTelNumber = contactTelNumber;
	}

	public CooperatorBank getCooperatorBank() {
		return cooperatorBank;
	}

	public void setCooperatorBank(CooperatorBank cooperatorBank) {
		this.cooperatorBank = cooperatorBank;
	}

	public String getAgreementName() {
		return agreementName;
	}

	public void setAgreementName(String agreementName) {
		this.agreementName = agreementName;
	}

	public CapitalistTrust getCapitalistTrust() {
		return capitalistTrust;
	}

	public void setCapitalistTrust(CapitalistTrust capitalistTrust) {
		this.capitalistTrust = capitalistTrust;
	}

	public String getLogicDelelte() {
		return logicDelelte;
	}

	public void setLogicDelelte(String logicDelelte) {
		this.logicDelelte = logicDelelte;
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

	public List<ContactsInfo> getContactsInfo() {
		return contactsInfo;
	}

	public void setContactsInfo(List<ContactsInfo> contactsInfo) {
		this.contactsInfo = contactsInfo;
	}

	public List<CooperateIdea> getCooperateIdea() {
		return cooperateIdea;
	}

	public void setCooperateIdea(List<CooperateIdea> cooperateIdea) {
		this.cooperateIdea = cooperateIdea;
	}

}
