package com.zdsoft.finance.customer.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.customer.entity.BeforeAddress;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:BeforeAddressVo.java
 * @Package:com.zdsoft.finance.customer.vo
 * @Description:贷前客户地址
 * @author: xj
 * @date:2017年1月12日 上午11:45:32
 * @version:v1.0
 */
public class BeforeAddressVo extends BaseVo<BeforeAddress> {
	/**
	 * 用一句话描述这个变量表示什么
	 */
	private static final long serialVersionUID = -8085352434074730451L;
	/**
	 * 省
	 */
	private String province;
	private String provinceName;//modify by liuhuan 2017-1-21
	/**
	 * 市
	 */
	private String city;
	private String cityName;//modify by liuhuan 2017-1-21
	/**
	 * 区
	 */
	private String district;
	private String districtName;//modify by liuhuan 2017-1-21
	/**
	 * 详细地址
	 */
	private String address;
	/**
	 * 地址类型
	 */
	private String addressType;
	/**
	 * 所属客户id
	 */
	private String customerId;
	/**
	 * 完整地址(省市区+地址)
	 */
	private String fullAddress;
	
	public String getFullAddress() {
		fullAddress = provinceName+" "+cityName+" "+districtName+" "+address;
		return  fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public BeforeAddressVo(){
	}
	
	public BeforeAddressVo(BeforeAddress po){
		super(po,null,new String[]{"province","city","district"});
	}
	
	public BeforeAddress toPO(){
		BeforeAddress po = new BeforeAddress();
		return super.toPo(this, po);
	}
	
	
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
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
