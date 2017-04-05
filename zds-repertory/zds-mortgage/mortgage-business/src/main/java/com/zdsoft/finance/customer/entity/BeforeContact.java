package com.zdsoft.finance.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:BeforeContact.java
 * @Package:com.zdsoft.finance.customer.entity
 * @Description:贷前客户联系方式
 * @author: xj
 * @date:2017年1月11日 上午10:09:31
 * @version:v1.0
 */
@Entity
@Table(name="cust_before_contact")
public class BeforeContact extends BaseEntity {
	/**
	 * 电话号码
	 */
	public static final String PHONE_NUMBER="YWDM0011702";
	/**
	 * 手机号码
	 */
	public static final String MOBILE_NUMBER="YWDM0011701";

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 3099031194534928748L;
	
	/**
	 * 姓名
	 */
	@Column(length=128)
	private String customerName;
	
	/**
	 * 联系类型
	 */
	@Column(length=20)
	private String contactType;
	
	/**
	 * 电话号码
	 */
	@Column(length=15)
	private String phoneNumber;
	
	/**
	 * 贷前客户Id
	 */
	@Column(length=32)
	private String customerId;
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getContactType() {
		return contactType;
	}
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	
	
}
