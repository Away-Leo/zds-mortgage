package com.zdsoft.finance.product.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 公司产品中间表
 * @author longwei
 * @date 2016/12/22
 * @version 1.0
 */
@Entity
@Table(name="prct_company_product")
public class CompanyProduct extends BaseEntity{

	private static final long serialVersionUID = -3016387821362129174L;

	/**
	 * 产品
	 */
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product; 
	
	/**
	 * 公司
	 */
	@ManyToOne
	@JoinColumn(name="company_id")
	private Company company;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
