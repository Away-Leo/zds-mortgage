package com.zdsoft.finance.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 分段还款
 * @createTime 2017年1月10日 下午2:47:28
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @version 1.0
 */
@Entity
@Table(name = "prct_part_repayment")
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
	@Column(length = 32)
	private String timeSection;
	
	/**
	 * 利率
	 */
	@Column(precision = 30,scale = 12)
	private Double rate;
	
	/**
	 * 利率单位
	 */
	@Column(length = 32)
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
