package com.zdsoft.finance.risk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: DaasAlter.java
 * @Package com.zdsoft.finance.risk.entity
 * @Description: 企业历史变更信息
 * @author panshm
 */
@Entity
@Table(name = "T_DAAS_ALTER")
public class DaasAlter extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 变更前内容
	 */
	@Column(length=4000)
	private String altbe;

	/**
	 * 变更日期
	 */
	@Column(length=10)
	private String altdate;

	/**
	 * 变更事项
	 */
	@Column(length=200)
	private String altitem;

	/**
	 * 变更后内容
	 */
	@Column(length=4000)
	private String altaf;
	
	/**
	 * 订单id
	 */
	@Column(name="order_id", length=36)
	private String orderId;

	public String getAltbe() {
		return altbe;
	}

	public void setAltbe(String altbe) {
		this.altbe = altbe;
	}

	public String getAltdate() {
		return altdate;
	}

	public void setAltdate(String altdate) {
		this.altdate = altdate;
	}

	public String getAltitem() {
		return altitem;
	}

	public void setAltitem(String altitem) {
		this.altitem = altitem;
	}

	public String getAltaf() {
		return altaf;
	}

	public void setAltaf(String altaf) {
		this.altaf = altaf;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


}
