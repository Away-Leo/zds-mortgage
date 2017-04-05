package com.zdsoft.finance.risk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: DaasLiquidation.java
 * @Package com.zdsoft.finance.risk.entity
 * @Description: 清算信息
 * @author panshm
 */
@Entity
@Table(name = "T_DAAS_LIQUIDATION")
public class DaasLiquidation extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 清算完结情况
	 */
	@Column(length=64)
	private String ligst;

	/**
	 * 清算组成员
	 */
	@Column(length=1000)
	private String liqmen;

	/**
	 * 债券承接人
	 */
	@Column(length=100)
	private String claimtranee;

	/**
	 * 清算责任人
	 */
	@Column(length=100)
	private String ligentity;

	/**
	 * 清算负责人
	 */
	@Column(length=100)
	private String ligprincipal;

	/**
	 * 债务承接人
	 */
	@Column(length=100)
	private String debttranee;

	/**
	 * 清算完结日期
	 */
	@Column(length=14)
	private String ligenddate;

	/**
	 * 订单id
	 */
	@Column(name="order_id", length=36)
	private String orderId;

	public String getLigst() {
		return ligst;
	}

	public void setLigst(String ligst) {
		this.ligst = ligst;
	}

	public String getLiqmen() {
		return liqmen;
	}

	public void setLiqmen(String liqmen) {
		this.liqmen = liqmen;
	}

	public String getClaimtranee() {
		return claimtranee;
	}

	public void setClaimtranee(String claimtranee) {
		this.claimtranee = claimtranee;
	}

	public String getLigentity() {
		return ligentity;
	}

	public void setLigentity(String ligentity) {
		this.ligentity = ligentity;
	}

	public String getLigprincipal() {
		return ligprincipal;
	}

	public void setLigprincipal(String ligprincipal) {
		this.ligprincipal = ligprincipal;
	}

	public String getDebttranee() {
		return debttranee;
	}

	public void setDebttranee(String debttranee) {
		this.debttranee = debttranee;
	}

	public String getLigenddate() {
		return ligenddate;
	}

	public void setLigenddate(String ligenddate) {
		this.ligenddate = ligenddate;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


}
