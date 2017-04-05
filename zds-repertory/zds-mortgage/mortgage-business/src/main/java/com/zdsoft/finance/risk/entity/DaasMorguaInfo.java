package com.zdsoft.finance.risk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: DaasMorguaInfo.java
 * @Package com.zdsoft.finance.risk.entity
 * @Description: 动产抵押物信息
 * @author panshm
 */
@Entity
@Table(name = "T_DAAS_MORGUAINFO")
public class DaasMorguaInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 抵押物名称
	 */
	@Column(length=200)
	private String guaname;

	/**
	 * 数量
	 */
	@Column(length=24)
	private String quan;

	/**
	 * 价值（万元）
	 */
	@Column(length=24)
	private String value;

	/**
	 * 抵押ID
	 */
	@Column(name="morreg_id", length=100)
	private String morregId;

	/**
	 * 订单id
	 */
	@Column(name="order_id", length=36)
	private String orderId;

	public String getGuaname() {
		return guaname;
	}

	public void setGuaname(String guaname) {
		this.guaname = guaname;
	}

	public String getQuan() {
		return quan;
	}

	public void setQuan(String quan) {
		this.quan = quan;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getMorregId() {
		return morregId;
	}

	public void setMorregId(String morregId) {
		this.morregId = morregId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
