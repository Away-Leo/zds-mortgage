package com.zdsoft.finance.customer.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.customer.entity.Contact;

/**
 * 联系方式
 * @author zhangchao
 * 2016/12/21
 */
public class ContactVo extends BaseVo<Contact> {

	
	/**
     * 客户id
     */
    private String clientId;
    
    /**
     * 顺序号
     */
    private int orderNo;
    
    /**
     * 关系类型
     */
    private String relationshipType;
    
    /**
     * 姓名
     */
    private String contactName;
    
    /**
     * 联系方式（枚举）
     */
    private String contactType;
    
    /**
     * 联系电话
     */
    private String contact;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	public String getRelationshipType() {
		return relationshipType;
	}

	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}

	public ContactVo(){}
	
	public ContactVo(Contact po){
		super(po);
	}
	
	public Contact toPO(){
		Contact po = new Contact();
		return super.toPo(this, po);
	}

}
