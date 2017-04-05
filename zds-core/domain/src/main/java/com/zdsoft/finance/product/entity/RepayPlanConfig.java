package com.zdsoft.finance.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RepayPlanConfig.java 
 * @ClassName: RepayPlanConfig 
 * @Description: 还款计划配置实体
 * @author gufeng 
 * @date 2017年3月6日 下午4:55:43 
 * @version V1.0
 */
@Entity
@Table(name="prd_repay_plan_config")
public class RepayPlanConfig extends BaseEntity{

	private static final long serialVersionUID = 6406019357082125654L;

	/**
	 * 费用项目
	 */
	@Column(length=64)
	private String feeName;
	
	/**
	 * 费用项目
	 */
	@Column(length=32)
	private String feeCode;
	
	/**
	 * 收款方
	 */
	@Column(length=32)
	private String receiverId;
	/**
	 * 收款方
	 */
	@Column(length=64)
	private String receiverName;
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

	public String getFeeName() {
		return feeName;
	}

	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}

	public String getFeeCode() {
		return feeCode;
	}

	public void setFeeCode(String feeCode) {
		this.feeCode = feeCode;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
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

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	
}
