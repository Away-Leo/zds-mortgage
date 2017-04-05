package com.zdsoft.finance.customer.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.customer.entity.AfterAddress;

/**
 * 贷后客户地址
 * 版权所有：重庆正大华日软件有限公司
 * @Title: PostLoanAddressVo.java 
 * @ClassName: PostLoanAddressVo 
 * @Description: 
 * @author zhangchao 
 * @date 2017年2月6日 上午11:12:17 
 * @version V1.0
 */
public class AfterAddressVo extends BaseVo<AfterAddress>{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 姓名
     */
	private String customerName;
    
    /**
     * 省
     */
	private String province;
	/**
     * 省名称
     */
	private String provinceName;
    
    /**
     * 市名称
     */
	private String city;
	/**
	 * 市
	 */
	private String cityName;
    
    /**
     * 区
     */
	private String district;
	/**
	 * 区名称
	 */
	private String districtName;
    
    /**
     * 详细地址
     */
	private String address;
    
    /**
     * 地址类型(simplecode维护，存的是fullcode,t0930:家庭地址，t0931户籍地址)
     */
	private String addressType;
    
    /**
     * 客户id
     */
	private String customerId;
	
	/**
	 * 居住地址code
	 */
	private String liveCode;

	/**
	 * 居住地址详细地址
	 */
	private String liveAddress;
	
	/**
	 * 户籍地址code
	 */
	private String domicileCode;
	
	/**
	 * 户籍地址详细地址
	 */
	private String domicileAddress;
	
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

	public String getLiveCode() {
		return liveCode;
	}

	public void setLiveCode(String liveCode) {
		this.liveCode = liveCode;
	}

	public String getLiveAddress() {
		return liveAddress;
	}

	public void setLiveAddress(String liveAddress) {
		this.liveAddress = liveAddress;
	}

	public String getDomicileCode() {
		return domicileCode;
	}

	public void setDomicileCode(String domicileCode) {
		this.domicileCode = domicileCode;
	}

	public String getDomicileAddress() {
		return domicileAddress;
	}

	public void setDomicileAddress(String domicileAddress) {
		this.domicileAddress = domicileAddress;
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

	public AfterAddressVo(){}
	
	public AfterAddressVo(AfterAddress po){
		super(po,null,new String[]{"province","city","district"});
	}
	
	public AfterAddress toPO(){
		AfterAddress po = new AfterAddress();
		return super.toPo(this, po);
	}
	
}
