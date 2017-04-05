package com.zdsoft.finance.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: PartRepayment.java 
 * @ClassName: PartRepayment 
 * @Description: 分段还款
 * @author gufeng 
 * @date 2017年3月13日 下午4:46:25 
 * @version V1.0
 */
@Entity
@Table(name = "prd_part_repayment")
public class PartRepayment extends BaseEntity {

	private static final long serialVersionUID = -8539960295684534969L;
	
	/**
	 * 产品id
	 */
	@Column(length = 32)
	private String productId;
	
	/**
	 * 时间
	 */
	@Column(length = 20)
	private String timeSection;
	
	/**
	 * 利率
	 */
	@Column(precision = 18,scale = 6)
	private Double rate;
	
	/**
	 * 利率单位
	 */
	@Column(length = 20)
	private String rateUtil;

	public String getRateUtil() {
		return rateUtil;
	}

	public void setRateUtil(String rateUtil) {
		this.rateUtil = rateUtil;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getTimeSection() {
		return timeSection;
	}

	public void setTimeSection(String timeSection) {
		this.timeSection = timeSection;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

}
