package com.zdsoft.finance.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;


/**
 * 工作单位
 * @author zhangchao
 * 2016/12/21
 */
@Entity
@Table(name = "zf_workunits")
public class WorkUnits extends BaseEntity {
	
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
     * 姓名
     */
    @Column(length = 15)
    private String unitsName;
    
    /**
     * 单位名称
     */
    @Column(length = 64)
    private String companyName;
    
    /**
     * 单位电话
     */
    @Column(length = 32)
    private String unitPhone;
    
    /**
     * 单位性质
     */
    @Column(length = 15)
    private String unitNature;
    
    /**
     * 工作年限
     */
    @Column
    private int workeYears;
    
    /**
     * 职务
     */
    @Column(length = 15)
    private String position;
    
    /**
     * 行业类型
     */
    @Column(length = 15)
    private String industryType;
    
    /**
     * 行业
     */
    @Column(length = 15)
    private String industry;
    
    /**
     * 单位地址
     */
    @Column(length = 200)
    private String unitAddress;
    
    /**
     * 关系类型
     */
    @Column(length = 15)
    private String relationshipType;
    
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getUnitsName() {
		return unitsName;
	}

	public void setUnitsName(String unitsName) {
		this.unitsName = unitsName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getUnitPhone() {
		return unitPhone;
	}

	public void setUnitPhone(String unitPhone) {
		this.unitPhone = unitPhone;
	}

	public String getUnitNature() {
		return unitNature;
	}

	public void setUnitNature(String unitNature) {
		this.unitNature = unitNature;
	}

	public int getWorkeYears() {
		return workeYears;
	}

	public void setWorkeYears(int workeYears) {
		this.workeYears = workeYears;
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

	public String getUnitAddress() {
		return unitAddress;
	}

	public void setUnitAddress(String unitAddress) {
		this.unitAddress = unitAddress;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getRelationshipType() {
		return relationshipType;
	}

	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
