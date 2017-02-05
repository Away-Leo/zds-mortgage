package com.zdsoft.finance.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 还款计划配置实体
 * @author longwei
 * @date 2016/12/28
 * @version 1.0
 */
@Entity
@Table(name="prct_repay_plan_config")
public class RepayPlanConfig extends BaseEntity{

	/**
	 * 费用项目
	 */
	@Column(length=255)
	private String feeNm;
	
	/**
	 * 费用项目
	 */
	@Column(length=32)
	private String feeCd;
	
	/**
	 * 收款方
	 */
	@Column(length=255)
	private String receiverCd;
	
	/**
	 * 收款方
	 */
	@Column(length=32)
	private String receiverNm;
	
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

	public String getFeeNm() {
		return feeNm;
	}

	public void setFeeNm(String feeNm) {
		this.feeNm = feeNm;
	}

	public String getFeeCd() {
		return feeCd;
	}

	public void setFeeCd(String feeCd) {
		this.feeCd = feeCd;
	}

	public String getReceiverCd() {
		return receiverCd;
	}

	public void setReceiverCd(String receiverCd) {
		this.receiverCd = receiverCd;
	}

	public String getReceiverNm() {
		return receiverNm;
	}

	public void setReceiverNm(String receiverNm) {
		this.receiverNm = receiverNm;
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
