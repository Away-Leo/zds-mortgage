package com.zdsoft.finance.customer.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.customer.entity.AfterContact;

/**
 * 贷后联系方式
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterContactVo.java 
 * @ClassName: AfterContactVo 
 * @Description: 
 * @author zhangchao 
 * @date 2017年2月6日 上午11:12:27 
 * @version V1.0
 */
public class AfterContactVo extends BaseVo<AfterContact>{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 姓名
     */
	private String customerName;
    
    /**
     * 联系类型(YWDM0011701：手机电话；YWDM0011702：家庭电话)
     */
	private String contactType;
    
    /**
     * 电话号码
     */
	private String phoneNumber;
    
    /**
     * 客户id
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

	public AfterContactVo(){}
	
	public AfterContactVo(AfterContact po){
		super(po);
	}
	
	public AfterContact toPO(){
		AfterContact po = new AfterContact();
		return super.toPo(this, po);
	}
}
