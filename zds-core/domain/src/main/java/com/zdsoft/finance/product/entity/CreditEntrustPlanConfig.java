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
 * 资金计划配置
 * @author longwei
 * @date 2017/01/17
 * @version 1.0
 */
@Entity
@Table(name="prct_credit_plan_config")
public class CreditEntrustPlanConfig extends BaseEntity{

	/**
	 * 资方id
	 */
	@Column(length=32,name="capitalist_id")
	private String capitalistId;
	
	/**
	 * 资方name
	 */
	@Column(length=255)
	private String capitalistName;
	
	/**
	 * 资金id
	 */
	@Column(length=32)
	private String creditEntrustId;
	
	/**
	 * 资金name
	 */
	@Column(length=255)
	private String creditEntrustName;
	
	/**
	 * 最低评估成数
	 */
	@Column(precision=0,scale=2)
	private BigDecimal minEvaluateNum;
	
	/**
	 * 最高评估成数
	 */
	@Column
	private BigDecimal maxEvaluateNum;
	
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

	public String getCreditEntrustId() {
		return creditEntrustId;
	}

	public void setCreditEntrustId(String creditEntrustId) {
		this.creditEntrustId = creditEntrustId;
	}

	public String getCreditEntrustName() {
		return creditEntrustName;
	}

	public void setCreditEntrustName(String creditEntrustName) {
		this.creditEntrustName = creditEntrustName;
	}

	public BigDecimal getMinEvaluateNum() {
		return minEvaluateNum;
	}

	public void setMinEvaluateNum(BigDecimal minEvaluateNum) {
		this.minEvaluateNum = minEvaluateNum;
	}

	public BigDecimal getMaxEvaluateNum() {
		return maxEvaluateNum;
	}

	public void setMaxEvaluateNum(BigDecimal maxEvaluateNum) {
		this.maxEvaluateNum = maxEvaluateNum;
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
	
}
