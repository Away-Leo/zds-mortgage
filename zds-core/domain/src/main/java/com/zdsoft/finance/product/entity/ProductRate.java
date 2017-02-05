package com.zdsoft.finance.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 产品贷款利率表
 * @author longwei
 * @date 2016/12/22
 * @version 1.0
 */
@Entity
@Table(name="prct_product_rate")
public class ProductRate extends BaseEntity{
	
	private static final long serialVersionUID = 8313266067613529246L;

	/**
	 * 利率
	 */
	@Column
	private Double rate;
	
	/**
	 * 利率单位
	 */
	@Column(length=15)
	private String rateUnit;
	
	/**
	 * 期限范围开始日期
	 */
	@Column
	private Long startDate;
	
	/**
	 * 期限范围开始日期单位
	 */
	@Column(length=15)
	private String startDateUnit;
	
	/**
	 * 期限范围结束日期
	 */
	@Column
	private Long endDate;
	
	/**
	 * 期限范围结束日期单位
	 */
	@Column(length=15)
	private String endDateUnit;

	/**
	 * 所属产品
	 */
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public String getRateUnit() {
		return rateUnit;
	}

	public void setRateUnit(String rateUnit) {
		this.rateUnit = rateUnit;
	}

	public Long getStartDate() {
		return startDate;
	}

	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	public String getStartDateUnit() {
		return startDateUnit;
	}

	public void setStartDateUnit(String startDateUnit) {
		this.startDateUnit = startDateUnit;
	}

	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	public String getEndDateUnit() {
		return endDateUnit;
	}

	public void setEndDateUnit(String endDateUnit) {
		this.endDateUnit = endDateUnit;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
