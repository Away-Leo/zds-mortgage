package com.zdsoft.finance.customer.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 贷后客户
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterCustomer.java 
 * @ClassName: AfterCustomer 
 * @Description: 
 * @author zhangchao 
 * @date 2017年2月6日 上午10:08:00 
 * @version V1.0
 */
@Entity
@Table(name = "cust_after_customer")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "customerType", discriminatorType = DiscriminatorType.STRING, length = 4)
public abstract class AfterCustomer extends BaseEntity {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 姓名
     */
    @Column(length = 128)
	private String customerName;
	
    /**
     * 证件类型
     */
    @Column(length = 20)
	private String credentialType;
	
    /**
     * 证件号
     */
    @Column(length = 64)
	private String credentialNo;
	
    /**
     * 创建人姓名
     */
    @Column(length = 128)
	private String creatorName;
	
    /**
     * 公司代码
     */
    @Column(length = 32)
	private String companyCode;
	
    /**
     * 公司名称
     */
    @Column(length = 128)
	private String companyName;
    
    /**
     * 部门代码
     */
    @Column(length = 32)
	private String departmentCode;
	
    /**
     * 部门名称
     */
    @Column(length = 128)
	private String departmentName;
	
    /**
     * 姓名
     */
    @Column
	private Integer createType;
    /**
     * 是否是最新客户
     */
    @Column
	@Type(type = "true_false")
    private Boolean isLatest = Boolean.valueOf(true);


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
		this.credentialNo = credentialNo;
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

	



	public Boolean getIsLatest() {
		return isLatest;
	}

	public void setIsLatest(Boolean isLatest) {
		this.isLatest = isLatest;
	}
	
}
