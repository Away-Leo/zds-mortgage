package com.zdsoft.finance.product.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.product.entity.ProductRate;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ProductRateVo.java 
 * @ClassName: ProductRateVo 
 * @Description: 产品利率
 * @author gufeng 
 * @date 2017年3月6日 下午8:28:20 
 * @version V1.0
 */
public class ProductRateVo extends BaseVo<ProductRate>{

	private static final long serialVersionUID = -6065995866380746919L;

	/**
	 * 利率
	 */
	private Double rate;
	
	/**
	 * 利率单位
	 */
	private String rateUnit;
	
	/**
	 * 期限范围开始日期
	 */
	private Long startDate;
	
	/**
	 * 期限范围开始日期单位
	 */
	private String startDateUnit;
	
	/**
	 * 期限范围结束日期
	 */
	private Long endDate;
	
	/**
	 * 期限范围结束日期单位
	 */
	private String endDateUnit;

	/**
	 * 所属产品
	 */
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
		if(ObjectHelper.isNotEmpty(this.getRateUnit()) && this.getRateUnit().equals("YWDM0011903")){
			this.setRate(this.getRate() * 10);
		}
	}
	
	public ProductRate toPo(){
		ProductRate productRate=new ProductRate();
		super.toPo(this, productRate, new String[]{"productVo"});
		if(ObjectHelper.isNotEmpty(productRate.getRateUnit()) && productRate.getRateUnit().equals("YWDM0011903")){
			productRate.setRate(productRate.getRate() / 10);
		}
		return productRate;
	}
}
