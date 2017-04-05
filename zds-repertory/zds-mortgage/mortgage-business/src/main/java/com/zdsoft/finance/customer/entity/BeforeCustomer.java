package com.zdsoft.finance.customer.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:BeforeCustomer.java
 * @Package:com.zdsoft.finance.customer.entity
 * @Description:贷前客户信息
 * @author: xj
 * @date:2017年1月11日 上午9:43:36
 * @version:v1.0
 */
@Entity
@Table(name = "cust_before_customer")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "customerType", discriminatorType = DiscriminatorType.STRING, length = 4)
public abstract class BeforeCustomer extends BaseEntity{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -5614867858997954291L;
	
	/**
	 * 姓名
	 */
	@Column(length=128)
	private String customerName;
	
	/**
	 * 证件类型
	 */
	@Column(length=20)
	private String credentialType;
	
	/**
	 * 证件号码
	 */
	@Column(length=64)
	private String credentialNo;
	
	/**
	 * 创建人姓名
	 */
	@Column(length=128)
	private String creatorName;
	
	/**
	 * 公司代码
	 */
	@Column(length=32)
	private String companyCode;
	
	/**
	 * 公司名称
	 */
	@Column(length=128)
	private String companyName;
	
	/**
	 * 部门代码
	 */
	@Column(length=32)
	private String departmentCode;
	
	/**
	 * 部门代码
	 */
	@Column(length=128)
	private String departmentName;
	
	/**
	 * 客户创建类型
	 */
	@Column
	private Integer createType;
	
	/**
	 * 联系方式
	 */
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "customerId")
	@LazyCollection(LazyCollectionOption.TRUE)
	private List<BeforeContact> beforeContacts = new ArrayList<BeforeContact>();
	
	/**
	 * 客户地址
	 */
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "customerId")
	@LazyCollection(LazyCollectionOption.TRUE)
	private List<BeforeAddress> beforeAddresss = new ArrayList<BeforeAddress>();
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getCredentialType() {
		return credentialType;
	}
	public void setCredentialType(String credentialType) {
		this.credentialType = credentialType;
	}
	public String getCredentialNo() {
		return credentialNo;
	}
	public void setCredentialNo(String credentialNo) {
		this.credentialNo = credentialNo.toUpperCase();
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public Integer getCreateType() {
		return createType;
	}
	public void setCreateType(Integer createType) {
		this.createType = createType;
	}
	public List<BeforeContact> getBeforeContacts() {
		return beforeContacts;
	}
	public void setBeforeContacts(List<BeforeContact> beforeContacts) {
		this.beforeContacts = beforeContacts;
	}
	public List<BeforeAddress> getBeforeAddresss() {
		return beforeAddresss;
	}
	public void setBeforeAddresss(List<BeforeAddress> beforeAddresss) {
		this.beforeAddresss = beforeAddresss;
	}
    
}
