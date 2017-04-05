package com.zdsoft.finance.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 贷后客户关系表
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterPersonaAssociation.java 
 * @ClassName: AfterPersonaAssociation 
 * @Description: 
 * @author zhangchao 
 * @date 2017年2月6日 上午10:08:17 
 * @version V1.0
 */
@Entity
@Table(name = "cust_after_pers_association")
public class AfterPersonaAssociation extends BaseEntity{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 主表客户id
     */
    @Column(length = 32)
    private String customerId;
    /**
     * 关联客户
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "relationtCustomerId")
    private AfterPersonalCustomer afterPersonalCustomer;
    
    /**
     * 与主借人关系(YWDM0014801：朋友；YWDM0014802：亲戚；YWDM0014803：同事；YWDM0014804：配偶)
     */
    @Column(length = 20)
	private String relationship;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}



	public AfterPersonalCustomer getAfterPersonalCustomer() {
		return afterPersonalCustomer;
	}

	public void setAfterPersonalCustomer(AfterPersonalCustomer afterPersonalCustomer) {
		this.afterPersonalCustomer = afterPersonalCustomer;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

}
