package com.zdsoft.finance.customer.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;


/**
 * 客户关系
 * @author zhangchao
 * 2016/12/21
 */
@Entity
@Table(name = "zf_crmassociation")
public class CrmAssociation extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 所属客户ID
     */
    @Column(length = 32)
    private String customerId;
    
    /**
     * 参与类型
     */
    @Column
    private int relationType;
    
    /**
     * 关系类型(与主借人关系)
     */
    @Column(length = 16)
    private String relationtTpe;
    
    /**
     * 客户id
     */
    @Column(length = 32)
    private String customer_id;
    
    /**
     * 客户
     */
    @ManyToMany(mappedBy = "crmAssociations")
    private List<Customer> Customers;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public int getRelationType() {
		return relationType;
	}

	public void setRelationType(int relationType) {
		this.relationType = relationType;
	}

	public String getRelationtTpe() {
		return relationtTpe;
	}

	public void setRelationtTpe(String relationtTpe) {
		this.relationtTpe = relationtTpe;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public List<Customer> getCustomers() {
		return Customers;
	}

	public void setCustomers(List<Customer> customers) {
		Customers = customers;
	}

}
