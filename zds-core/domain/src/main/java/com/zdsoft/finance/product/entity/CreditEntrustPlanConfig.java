package com.zdsoft.finance.product.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreditEntrustPlanConfig.java 
 * @ClassName: CreditEntrustPlanConfig 
 * @Description: 资金计划配置
 * @author gufeng 
 * @date 2017年3月6日 下午7:48:07 
 * @version V1.0
 */
@Entity
@Table(name="prd_credit_plan_config")
public class CreditEntrustPlanConfig extends BaseEntity{

	private static final long serialVersionUID = 1824649797504722758L;

	/**
	 * 资方id
	 */
	@Column(length=32,name="capitalist_id")
	private String capitalistId;
	
	/**
	 * 资方name
	 */
	@Column(length=64)
	private String capitalistName;
	
	/**
	 * 资金id
	 */
	@Column(length=32)
	private String capitalPlanId;
	
	/**
	 * 资金name
	 */
	@Column(length=64)
	private String capitalPlanName;
	
	/**
	 * 最低评估成数
	 */
	@Column(precision=18,scale=6)
	private BigDecimal minPercentage = BigDecimal.ZERO;
	
	/**
	 * 最高评估成数
	 */
	@Column(precision=18,scale=6)
	private BigDecimal maxPercentage = BigDecimal.ZERO;
	
	/**
	 * 是否启用
	 */
	@Column
	@Type(type="true_false")
	private Boolean isEnable;
	
	/**
	 * 所属产品
	 */
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;

	public String getCapitalistId() {
		return capitalistId;
	}

	public void setCapitalistId(String capitalistId) {
		this.capitalistId = capitalistId;
	}

	public String getCapitalistName() {
		return capitalistName;
	}

	public void setCapitalistName(String capitalistName) {
		this.capitalistName = capitalistName;
	}
	
	public String getCapitalPlanName() {
		return capitalPlanName;
	}

	public void setCapitalPlanName(String capitalPlanName) {
		this.capitalPlanName = capitalPlanName;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getCapitalPlanId() {
		return capitalPlanId;
	}

	public void setCapitalPlanId(String capitalPlanId) {
		this.capitalPlanId = capitalPlanId;
	}

	public BigDecimal getMinPercentage() {
		return minPercentage;
	}

	public void setMinPercentage(BigDecimal minPercentage) {
		this.minPercentage = minPercentage;
	}

	public BigDecimal getMaxPercentage() {
		return maxPercentage;
	}

	public void setMaxPercentage(BigDecimal maxPercentage) {
		this.maxPercentage = maxPercentage;
	}
	
}
