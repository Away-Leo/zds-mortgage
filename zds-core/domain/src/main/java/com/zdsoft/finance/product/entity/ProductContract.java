package com.zdsoft.finance.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ProductContract.java 
 * @ClassName: ProductContract 
 * @Description: 产品合同模板关系表
 * @author gufeng 
 * @date 2017年3月13日 下午4:46:47 
 * @version V1.0
 */
@Entity
@Table(name = "prd_product_contract")
public class ProductContract extends BaseEntity {

	private static final long serialVersionUID = 9050510322788912096L;

	/**
	 * 产品id
	 */
	@Column(length = 32)
	private String productId;
	
	/**
	 * 合同id
	 */
	@Column(length = 32)
	private String contractId;
	
	private transient String[] contractIds;
	
	public String[] getContractIds() {
		return contractIds;
	}

	public void setContractIds(String[] contractIds) {
		this.contractIds = contractIds;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	
}
