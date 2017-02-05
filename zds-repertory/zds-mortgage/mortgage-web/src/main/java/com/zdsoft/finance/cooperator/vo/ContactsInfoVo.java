package com.zdsoft.finance.cooperator.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.cooperator.entity.ContactsInfo;

/**
 * 合作方联系人
 * 
 * @author Hisa
 *
 */
public class ContactsInfoVo extends BaseVo<ContactsInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 联系人姓名
	 */
	private String contactName;
	/**
	 * 联系类型
	 */
	private String contactType;
	private String contactTypeName;
	/**
	 * 电话号码
	 */
	private String contactTelNumber;
	/**
	 * 职务
	 */
	private String role;
	/**
	 * 关联的终端
	 */
	private String terminalId;
	/**
	 * 关联资方信息
	 */
	private String capitalistId;

	private String foxNumber;
	/**
	 * 邮编
	 */
	private String postalCode;

	public String getContactTypeName() {
		return contactTypeName;
	}

	public void setContactTypeName(String contactTypeName) {
		this.contactTypeName = contactTypeName;
	}

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

	public String getCapitalistId() {
		return capitalistId;
	}

	public void setCapitalistId(String capitalistId) {
		this.capitalistId = capitalistId;
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

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public ContactsInfoVo() {
		super();
	}

	public ContactsInfoVo(ContactsInfo contactsInfo) {
		super(contactsInfo);
	}

	public ContactsInfoVo(ContactsInfo contactsInfo, String[] args, String[] simpleArgs) {
		super(contactsInfo, args, simpleArgs);
	}

	public ContactsInfo toPO() {
		ContactsInfo po = new ContactsInfo();
		return super.toPo(this, po);
	}

}
