package com.zdsoft.finance.product.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.product.entity.ProductRate;

/**
 * 产品利率vo
 * @author longwei
 * @date 2016/12/26
 * @version 1.0
 */
public class ProductRateVo extends BaseVo<ProductRate>{

	// 利率
	private Double rate;
	
	// 利率单位
	private String rateUnit;
	
	// 期限范围开始日期
	private Long startDate;
	
	// 期限范围开始日期单位
	private String startDateUnit;
	
	// 期限范围结束日期
	private Long endDate;
	
	// 期限范围结束日期单位
	private String endDateUnit;

	// 所属产品
	private ProductVo productVo;

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

	public ProductVo getProductVo() {
		return productVo;
	}

	public void setProductVo(ProductVo productVo) {
		this.productVo = productVo;
	}
	
	public ProductRateVo(){}
	
	public ProductRateVo(ProductRate productRate){
		super(productRate, new String[]{"productVo"});
	}
	
	public ProductRate toPo(){
		ProductRate productRate=new ProductRate();
		super.toPo(this, productRate, new String[]{"productVo"});
		return productRate;
	}
}
