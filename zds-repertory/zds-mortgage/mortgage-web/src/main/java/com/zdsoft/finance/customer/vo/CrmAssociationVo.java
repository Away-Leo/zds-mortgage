package com.zdsoft.finance.customer.vo;

import java.util.List;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.customer.entity.CrmAssociation;

/**
 * 客户关系
 * @author zhangchao
 * 2016/12/21
 */
public class CrmAssociationVo extends BaseVo<CrmAssociation> {

	/**
     * 所属客户ID
     */
    private String customerId;
    
    /**
     * 参与类型
     */
    private int relationType;
    
    /**
     * 关系类型(与主借人关系)
     */
    private String relationtTpe;
    
    /**
     * 客户id
     */
    private String customer_id;
    
    /**
     * 客户
     */
    private List<CustomerVo> Customers;

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

	public List<CustomerVo> getCustomers() {
		return Customers;
	}

	public void setCustomers(List<CustomerVo> customers) {
		Customers = customers;
	}
	
	public CrmAssociationVo(){}
	
	public CrmAssociationVo(CrmAssociation po){
		super(po);
	}
	
	public CrmAssociation toPO(){
		CrmAssociation po = new CrmAssociation();
		return super.toPo(this, po);
	}

}
