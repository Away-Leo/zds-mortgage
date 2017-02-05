package com.zdsoft.finance.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 贷后客户地址
 * @author <a href="mailto:502549751@qq.com">zhangchao</a>
 * @createTime: 2017-01-10 14::41
 * @version: 1.0
 *
 */
@Entity
@Table(name = "cus_post_loan_address")
public class PostLoanAddress extends BaseEntity{

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
     * 省
     */
    @Column(length = 20)
	private String province;
    
    /**
     * 市
     */
    @Column(length = 20)
	private String city;
    
    /**
     * 区
     */
    @Column(length = 20)
	private String district;
    
    /**
     * 详细地址
     */
    @Column(length = 500)
	private String address;
    
    /**
     * 地址类型(t0930:家庭地址，t0931户籍地址)
     */
    @Column(length = 20)
	private String addressType;
    
    /**
     * 客户id
     */
    @Column(length = 128)
	private String customerId;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
