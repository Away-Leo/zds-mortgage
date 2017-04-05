package com.zdsoft.finance.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 贷后联系方式
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterContact.java 
 * @ClassName: AfterContact 
 * @Description: 
 * @author zhangchao 
 * @date 2017年2月6日 上午10:07:46 
 * @version V1.0
 */
@Entity
@Table(name = "cust_after_contact")
public class AfterContact extends BaseEntity{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 姓名
     */
    @Column(length = 128)
	private String customerName;
    
    /**
     * 联系类型(YWDM0011701：手机电话；YWDM0011702：家庭电话)
     */
    @Column(length = 20)
	private String contactType;
    
    /**
     * 电话号码
     */
    @Column(length = 15)
	private String phoneNumber;
    
    /**
     * 客户id
     */
    @Column(length = 32)
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
