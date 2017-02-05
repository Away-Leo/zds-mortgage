package com.zdsoft.finance.customer.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.customer.entity.PostLoanAddress;

/**
 * 贷后客户地址
 * @author <a href="mailto:502549751@qq.com">zhangchao</a>
 * @createTime: 2017-01-10 14::41
 * @version: 1.0
 *
 */
public class PostLoanAddressVo extends BaseVo<PostLoanAddress>{

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
     * 市
     */
	private String city;
    
    /**
     * 区
     */
	private String district;
    
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

	public PostLoanAddressVo(){}
	
	public PostLoanAddressVo(PostLoanAddress po){
		super(po);
	}
	
	public PostLoanAddress toPO(){
		PostLoanAddress po = new PostLoanAddress();
		return super.toPo(this, po);
	}
}
