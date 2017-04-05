package com.zdsoft.finance.cooperator.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.cooperator.entity.ContactsInfo;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: ContactsInfoVo.java
 * @ClassName: ContactsInfoVo
 * @Description: 联系方式Vo
 * @author liuwei
 * @date 2017年3月2日 上午11:22:53
 * @version V1.0
 */
public class ContactsInfoVo extends BaseVo<ContactsInfo> {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1079362372294550327L;

	/**
	 * 联系人
	 */
	private String linkman;

	/**
	 * 联系类型
	 */
	private String contactType;

	/**
	 * 联系类型名称
	 */
	private String contactTypeName;

	/**
	 * 电话号码
	 */
	private String contactNumber;

	/**
	 * 职务
	 */
	private String duty;

	/**
	 * 合作方id
	 */
	private String partnerId;

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getContactTypeName() {
		return contactTypeName;
	}

	public void setContactTypeName(String contactTypeName) {
		this.contactTypeName = contactTypeName;
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
