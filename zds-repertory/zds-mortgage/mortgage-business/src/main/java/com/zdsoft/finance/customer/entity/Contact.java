package com.zdsoft.finance.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;


/**
 * 联系方式
 * @author zhangchao
 * 2016/12/21
 */
@Entity
@Table(name = "zf_contact")
public class Contact extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 客户id
     */
    @Column(length = 32)
    private String clientId;
    
    /**
     * 顺序号
     */
    @Column
    private int orderNo;
    
    /**
     * 关系类型
     */
    @Column(length = 15)
    private String relationshipType;
    
    /**
     * 姓名
     */
    @Column(length = 15)
    private String contactName;
    
    /**
     * 联系方式（枚举）
     */
    @Column(length = 15)
    private String contactType;
    
    /**
     * 联系电话
     */
    @Column(length = 15)
    private String contact;
    
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

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

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
