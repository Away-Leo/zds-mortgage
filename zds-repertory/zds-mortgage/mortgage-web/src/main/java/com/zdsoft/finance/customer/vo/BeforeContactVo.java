package com.zdsoft.finance.customer.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.customer.entity.BeforeContact;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:BeforeContactVo.java
 * @Package:com.zdsoft.finance.customer.vo
 * @Description:贷前客户联系方式
 * @author: xj
 * @date:2017年1月12日 上午11:52:26
 * @version:v1.0
 */
public class BeforeContactVo extends BaseVo<BeforeContact> {
	/**
	 * 用一句话描述这个变量表示什么
	 */
	private static final long serialVersionUID = -2937959485785130151L;
	/**
	 * 姓名
	 */
	private String customerName;
	/**
	 * 联系类型
	 */
	private String contactType;
	private String contactTypeName;//modify by liuhuan 2017-1-21
	/**
	 * 联系类型中文名称
	 */
	private String contactTypeNm;
	/**
	 * 电话号码
	 */
	private String phoneNumber;
	/**
	 * 贷前客户id
	 */
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
	public String getContactTypeName() {
		return contactTypeName;
	}
	public void setContactTypeName(String contactTypeName) {
		this.contactTypeName = contactTypeName;
	}
	public BeforeContactVo(){}
	
	public BeforeContactVo(BeforeContact po){
		super(po,null,new String[]{"contactType"});
	}
	
	public BeforeContact toPO(){
		BeforeContact po = new BeforeContact();
		return super.toPo(this, po);
	}
	public String getContactTypeNm() {
		return contactTypeNm;
	}
	public void setContactTypeNm(String contactTypeNm) {
		this.contactTypeNm = contactTypeNm;
	}
	
}
