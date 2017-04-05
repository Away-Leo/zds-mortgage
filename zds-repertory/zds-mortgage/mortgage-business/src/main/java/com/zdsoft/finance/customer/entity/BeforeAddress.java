package com.zdsoft.finance.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:BeforeAddress.java
 * @Package:com.zdsoft.finance.customer.entity
 * @Description:贷前客户地址
 * @author: xj
 * @date:2017年1月11日 上午10:17:35
 * @version:v1.0
 */
@Entity
@Table(name="cust_before_address")
public class BeforeAddress extends BaseEntity {
	/**
	 * 家庭地址
	 */
	public static String HOME_ADDRESS="t0930";
	/**
	 * 户籍地址
	 */
	public static String HOUSEHOLD_REGISTRATION_ADDRESS="t0931";

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -441545314778791379L;
	
	/**
	 * 省
	 */
	@Column(length=20)
	private String province;
	
	/**
	 * 市
	 */
	@Column(length=20)
	private String city;
	
	/**
	 * 区
	 */
	@Column(length=20)
	private String district;
	
	/**
	 * 详细地址
	 */
	@Column(length=500)
	private String address;
	
	/**
	 * 地址类型
	 */
	@Column(length=20)
	private String addressType;
	
	/**
	 * 客户id
	 */
	private String customerId;
	
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
