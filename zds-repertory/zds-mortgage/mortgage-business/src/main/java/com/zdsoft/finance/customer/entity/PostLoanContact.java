package com.zdsoft.finance.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 贷后联系方式
 * @author <a href="mailto:502549751@qq.com">zhangchao</a>
 * @createTime: 2017-01-10 14::41
 * @version: 1.0
 *
 */
@Entity
@Table(name = "cus_post_loan_contact")
public class PostLoanContact extends BaseEntity{

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
     * 联系类型(c01151：手机电话；c01152：家庭电话)
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
