package com.zdsoft.finance.product.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CompanyProduct.java 
 * @ClassName: CompanyProduct 
 * @Description: 公司产品中间表
 * @author gufeng 
 * @date 2017年3月14日 下午4:30:27 
 * @version V1.0
 */
@Entity
@Table(name="prd_company_product")
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
