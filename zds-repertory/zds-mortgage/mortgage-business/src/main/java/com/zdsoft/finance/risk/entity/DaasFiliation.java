package com.zdsoft.finance.risk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: DaasFiliation.java
 * @Package com.zdsoft.finance.risk.entity
 * @Description: 企业分支机构信息
 * @author panshm
 */
@Entity
@Table(name = "T_DAAS_FILIATION")
public class DaasFiliation extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 分支机构企业注册号
	 */
	@Column(length=50)
	private String brregno;

	/**
	 * 分支机构名称
	 */
	@Column(length=100)
	private String brname;

	/**
	 * 一般经营项目
	 */
	@Column(length=4000)
	private String cbuitem;

	/**
	 * 分支机构地址
	 */
	@Column(length=100)
	private String braddr;

	/**
	 * 分支机构负责人
	 */
	@Column(length=30)
	private String brprincipal;
	
	/**
	 * 订单id
	 */
	@Column(name="order_id", length=36)
	private String orderId;

	public String getBrregno() {
		return brregno;
	}

	public void setBrregno(String brregno) {
		this.brregno = brregno;
	}

	public String getBrname() {
		return brname;
	}

	public void setBrname(String brname) {
		this.brname = brname;
	}

	public String getCbuitem() {
		return cbuitem;
	}

	public void setCbuitem(String cbuitem) {
		this.cbuitem = cbuitem;
	}

	public String getBraddr() {
		return braddr;
	}

	public void setBraddr(String braddr) {
		this.braddr = braddr;
	}

	public String getBrprincipal() {
		return brprincipal;
	}

	public void setBrprincipal(String brprincipal) {
		this.brprincipal = brprincipal;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


}
