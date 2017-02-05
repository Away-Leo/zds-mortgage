package com.zdsoft.finance.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 产品合同模板关系表
 * @createTime 2017年1月10日 下午3:25:08
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @version 1.0
 */
@Entity
@Table(name = "prct_product_contract")
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
