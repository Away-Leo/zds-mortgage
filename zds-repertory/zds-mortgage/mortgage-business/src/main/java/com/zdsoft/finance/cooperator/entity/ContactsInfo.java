package com.zdsoft.finance.cooperator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 合作方联系人
 * 
 * @author Hisa
 *
 */
@Entity
@Table(name = "cpt_contacts_info")
public class ContactsInfo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 联系人姓名
	 */
	@Column(length = 64)
	private String contactName;
	/**
	 * 联系类型
	 */
	@Column(length = 64)
	private String contactType;
	/**
	 * 电话号码
	 */
	@Column(length = 15)
	private String contactTelNumber;
	/**
	 * 职务
	 */
	@Column(length = 64)
	private String role;
	/**
	 * 传真
	 */
	@Column(length = 32)
	private String foxNumber;
	/**
	 * 邮编
	 */
	@Column(length = 32)
	private String postalCode;

	/**
	 * 关联的终端
	 */
	@ManyToOne
	@JoinColumn(name = "terminalId")
	private CooperatorTerminal cooperatorTerminal;

	@ManyToOne
	@JoinColumn(name = "capitalistId")
	private Capitalist capitalist;

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
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

	public Capitalist getCapitalist() {
		return capitalist;
	}

	public void setCapitalist(Capitalist capitalist) {
		this.capitalist = capitalist;
	}

	public CooperatorTerminal getCooperatorTerminal() {
		return cooperatorTerminal;
	}

	public void setCooperatorTerminal(CooperatorTerminal cooperatorTerminal) {
		this.cooperatorTerminal = cooperatorTerminal;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactTelNumber() {
		return contactTelNumber;
	}

	public void setContactTelNumber(String contactTelNumber) {
		this.contactTelNumber = contactTelNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
