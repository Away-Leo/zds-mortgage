package com.zdsoft.finance.afterloan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: EmergencyContact.java 
 * @ClassName: EmergencyContact 
 * @Description: 紧急联系人的联系方式
 * @author huangwei 
 * @date 2017年3月6日 上午10:39:02 
 * @version V1.0
 */
@Entity
@Table(name = "aftloan_emergency_contact")
public class EmergencyContact extends BaseEntity {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

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
     * 紧急联系人id
     */
    @Column(length = 32)
	private String customerId;


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
