package com.zdsoft.finance.customer.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.customer.entity.PostLoanContact;

/**
 * 贷后联系方式
 * @author <a href="mailto:502549751@qq.com">zhangchao</a>
 * @createTime: 2017-01-10 14::41
 * @version: 1.0
 *
 */
public class PostLoanContactVo extends BaseVo<PostLoanContact>{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 姓名
     */
	private String customerName;
    
    /**
     * 联系类型(c01151：手机电话；c01152：家庭电话)
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

	public PostLoanContactVo(){}
	
	public PostLoanContactVo(PostLoanContact po){
		super(po);
	}
	
	public PostLoanContact toPO(){
		PostLoanContact po = new PostLoanContact();
		return super.toPo(this, po);
	}
}
