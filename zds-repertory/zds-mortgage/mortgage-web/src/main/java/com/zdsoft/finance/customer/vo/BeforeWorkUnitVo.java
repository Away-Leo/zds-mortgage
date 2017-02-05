package com.zdsoft.finance.customer.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.entity.BeforeWorkUnit;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:BeforeWorkUnitVo.java
 * @Package:com.zdsoft.finance.customer.vo
 * @Description:贷前客户工作单位
 * @author: xj
 * @date:2017年1月12日 上午11:41:29
 * @version:v1.0
 */
public class BeforeWorkUnitVo extends BaseVo<BeforeWorkUnit>{
	/**
	 * 用一句话描述这个变量表示什么
	 */
	private static final long serialVersionUID = 2947673647436815084L;
	/**
	 * 客户
	 */
	private BeforePersonalCustomer beforeCustomer;
	/**
	 * 姓名
	 */
	private  String workUnitName;
	/**
	 * 单位名称
	 */
	private  String companyName;
	/**
	 * 单位电话
	 */
	private  String phoneNumber;
	/**
	 * 单位性质名称
	 */
	private  String workUnitNature;
	private String workUnitNatureName;//modify by liuhuan 2017-1-21
	/**
	 * 单位性质
	 */
	private  String workUnitNatureNm;
	/**
	 * 工作年限
	 */
	private  Integer workYears;
	/**
	 * 职务
	 */
	private  String position;
	private String positionName;//modify by liuhuan 2017-1-21
	/**
	 * 职务名称
	 */
	private  String positionNm;
	/**
	 * 行业类型
	 */
	private  String industryType;
	private String industryTypeName;//modify by liuhuan 2017-1-21
	/**
	 * 行业类型名称
	 */
	private  String industryTypeNm;
	/**
	 * 行业
	 */
	private  String industry;
	private String industryName;//modify by liuhuan 2017-1-21
	/**
	 * 行业名称
	 */
	private  String industryNm;
	/**
	 * 省
	 */
	private  String province;
	private  String provinceName;//modify by liuhuan 2017-1-21
	/**
	 * 市
	 */
	private  String city;
	private  String cityName;//modify by liuhuan 2017-1-21
	/**
	 * 区
	 */
	private  String district;
	private  String districtName;//modify by liuhuan 2017-1-21
	/**
	 * 单位地址
	 */
	private  String workUnitAddress;
	/**
	 * 单位地址 拼接地址
	 */
	private  String workUnitAddressName;
	/**
	 * 所属客户id
	 */
	private String customerId;
	public BeforeWorkUnitVo(){}
	
	public BeforeWorkUnitVo(BeforeWorkUnit po){
		super(po,null,new String[]{"industryType","industry", "workUnitNature","position","district","city","province"});
	}
	
	public BeforeWorkUnit toPO(){
		BeforeWorkUnit po = new BeforeWorkUnit();
		return  super.toPo(this, po);
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

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getWorkUnitNatureName() {
		return workUnitNatureName;
	}

	public void setWorkUnitNatureName(String workUnitNatureName) {
		this.workUnitNatureName = workUnitNatureName;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getIndustryTypeName() {
		return industryTypeName;
	}

	public void setIndustryTypeName(String industryTypeName) {
		this.industryTypeName = industryTypeName;
	}

	public BeforePersonalCustomer getBeforeCustomer() {
		return beforeCustomer;
	}
	public void setBeforeCustomer(BeforePersonalCustomer beforeCustomer) {
		this.beforeCustomer = beforeCustomer;
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
		return industryType;
	}
	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getWorkUnitAddress() {
		return workUnitAddress;
	}
	public void setWorkUnitAddress(String workUnitAddress) {
		this.workUnitAddress = workUnitAddress;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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

	public String getWorkUnitAddressName() {
		workUnitAddressName = provinceName+" "+cityName+" "+districtName+" "+workUnitAddress;
		return workUnitAddressName;
	}

	public void setWorkUnitAddressName(String workUnitAddressName) {
		this.workUnitAddressName = workUnitAddressName;
	}

	public String getWorkUnitNatureNm() {
		return workUnitNatureNm;
	}

	public void setWorkUnitNatureNm(String workUnitNatureNm) {
		this.workUnitNatureNm = workUnitNatureNm;
	}

	public String getPositionNm() {
		return positionNm;
	}

	public void setPositionNm(String positionNm) {
		this.positionNm = positionNm;
	}

	public String getIndustryTypeNm() {
		return industryTypeNm;
	}

	public void setIndustryTypeNm(String industryTypeNm) {
		this.industryTypeNm = industryTypeNm;
	}

	public String getIndustryNm() {
		return industryNm;
	}

	public void setIndustryNm(String industryNm) {
		this.industryNm = industryNm;
	}
	
}
