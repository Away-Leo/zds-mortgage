package com.zdsoft.finance.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ProductRate.java 
 * @ClassName: ProductRate 
 * @Description: 产品贷款利率表
 * @author gufeng 
 * @date 2017年3月6日 下午8:26:46 
 * @version V1.0
 */
@Entity
@Table(name="prd_product_rate")
public class ProductRate extends BaseEntity{
	
	private static final long serialVersionUID = 8313266067613529246L;

	/**
	 * 利率
	 */
	@Column(precision = 18,scale = 6)
	private Double rate;
	
	/**
	 * 利率单位
	 */
	@Column(length=20)
	private String rateUnit;
	
	/**
	 * 期限范围开始日期
	 */
	@Column
	private Long startDate;
	
	/**
	 * 期限范围开始日期单位
	 */
	@Column(length=20)
	private String startDateUnit;
	
	/**
	 * 期限范围结束日期
	 */
	@Column
	private Long endDate;
	
	/**
	 * 期限范围结束日期单位
	 */
	@Column(length=20)
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
