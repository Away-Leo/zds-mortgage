package com.zdsoft.finance.cooperator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: ContactsInfo.java
 * @ClassName: ContactsInfo
 * @Description: 联系人资料(刘伟二次维护)
 * @author liuwei
 * @date 2017年2月25日 下午2:03:04
 * @version V1.0
 */
@Entity
@Table(name = "cpt_contacts_info")
public class ContactsInfo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 联系人
	 */
	@Column(length = 128)
	private String linkman;

	/**
	 * 联系类型
	 */
	@Column(length = 20)
	private String contactType;

	/**
	 * 电话号码
	 */
	@Column(length = 32)
	private String contactNumber;

	/**
	 * 职务
	 */
	@Column(length = 32)
	private String duty;

	/**
	 * 合作方id
	 */
	@Column(length = 32)
	private String partnerId;

	/**
	 * 终端主要联系人临时存放
	 */
	private transient Boolean mainContacts = Boolean.valueOf(false);

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

	public Boolean getMainContacts() {
		return mainContacts;
	}

	public void setMainContacts(Boolean mainContacts) {
		this.mainContacts = mainContacts;
	}

}
