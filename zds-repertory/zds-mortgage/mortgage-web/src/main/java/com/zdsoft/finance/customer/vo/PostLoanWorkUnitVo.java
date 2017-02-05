package com.zdsoft.finance.customer.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.customer.entity.PostLoanWorkUnit;

/**
 * 贷后工作单位
 * @author <a href="mailto:502549751@qq.com">zhangchao</a>
 * @createTime: 2017-01-10 14::41
 * @version: 1.0
 *
 */
public class PostLoanWorkUnitVo extends BaseVo<PostLoanWorkUnit>{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 客户id
     */
	private String customerId;
    
    /**
     * 姓名
     */
	private String workUnitName;
    
    /**
     * 单位名称
     */
	private String companyName;
    
    /**
     * 单位电话
     */
	private String phoneNumber;
    
    /**
     * 单位性质(通过simpleCode的fullcode维护，保存fullcode，w01211：国有企业；w01212：国有控股企业；w01213：外资企业；w01214：合资企业；w01215：私营企业；w01216：事业企业；w01217：国家行政机关；w01218：政府；)
     */
	private String workUnitNature;
    
    /**
     * 工作年限
     */
	private Integer workYears;
    
    /**
     * 职务(通过simplecode的fullcode维护，保存的是fullcode，p01261：高管；)
     */
	private String position;
    
    /**
     * 行业类型(通过simplecode的fullcode维护，一级行业类型)
     */
	private String IndustryType;
    
    /**
     * 行业(通过simplecode的fullcode维护，二级行业类型)
     */
	private String Industry;
	
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
     * 单位地址
     */
	private String workUnitAddress;
	
	/**
	 * 工作单位地址code
	 */
	private String workUnitCode;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getWorkUnitName() {
		return workUnitName;
	}

	public void setWorkUnitName(String workUnitName) {
		this.workUnitName = workUnitName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getWorkUnitNature() {
		return workUnitNature;
	}

	public void setWorkUnitNature(String workUnitNature) {
		this.workUnitNature = workUnitNature;
	}

	public Integer getWorkYears() {
		return workYears;
	}

	public void setWorkYears(Integer workYears) {
		this.workYears = workYears;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getIndustryType() {
		return IndustryType;
	}

	public void setIndustryType(String industryType) {
		IndustryType = industryType;
	}

	public String getIndustry() {
		return Industry;
	}

	public void setIndustry(String industry) {
		Industry = industry;
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

	public String getWorkUnitAddress() {
		return workUnitAddress;
	}

	public void setWorkUnitAddress(String workUnitAddress) {
		this.workUnitAddress = workUnitAddress;
	}
    
	public String getWorkUnitCode() {
		return workUnitCode;
	}

	public void setWorkUnitCode(String workUnitCode) {
		this.workUnitCode = workUnitCode;
	}

	public PostLoanWorkUnitVo(){}
	
	public PostLoanWorkUnitVo(PostLoanWorkUnit po){
		super(po);
	}
	
	public PostLoanWorkUnit toPO(){
		PostLoanWorkUnit po = new PostLoanWorkUnit();
		return super.toPo(this, po);
	}

}
