package com.zdsoft.finance.risk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: DaasSharesFrost.java
 * @Package com.zdsoft.finance.risk.entity
 * @Description: 股权冻结历史信息
 * @author panshm
 */
@Entity
@Table(name = "T_DAAS_SHARESFROST")
public class DaasSharesFrost extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 冻结起始日期
	 */
	@Column(length=10)
	private String frofrom;

	/**
	 * 解冻日期
	 */
	@Column(length=10)
	private String thawdate;

	/**
	 * 解冻说明
	 */
	@Column(length=1024)
	private String thawcomment;

	/**
	 * 冻结机关
	 */
	@Column(length=100)
	private String froauth;

	/**
	 * 冻结截至日期
	 */
	@Column(length=10)
	private String froto;

	/**
	 * 解冻文号
	 */
	@Column(length=60)
	private String thawdocno;

	/**
	 * 冻结文号
	 */
	@Column(length=60)
	private String frodocno;

	/**
	 * 冻结金额（万元）
	 */
	@Column(length=24)
	private String froam;

	/**
	 * 解冻机关
	 */
	@Column(length=100)
	private String thawauth;

	/**
	 * 订单id
	 */
	@Column(name="order_id", length=36)
	private String orderId;

	public String getFrofrom() {
		return frofrom;
	}

	public void setFrofrom(String frofrom) {
		this.frofrom = frofrom;
	}

	public String getThawdate() {
		return thawdate;
	}

	public void setThawdate(String thawdate) {
		this.thawdate = thawdate;
	}

	public String getThawcomment() {
		return thawcomment;
	}

	public void setThawcomment(String thawcomment) {
		this.thawcomment = thawcomment;
	}

	public String getFroauth() {
		return froauth;
	}

	public void setFroauth(String froauth) {
		this.froauth = froauth;
	}

	public String getFroto() {
		return froto;
	}

	public void setFroto(String froto) {
		this.froto = froto;
	}

	public String getThawdocno() {
		return thawdocno;
	}

	public void setThawdocno(String thawdocno) {
		this.thawdocno = thawdocno;
	}

	public String getFrodocno() {
		return frodocno;
	}

	public void setFrodocno(String frodocno) {
		this.frodocno = frodocno;
	}

	public String getFroam() {
		return froam;
	}

	public void setFroam(String froam) {
		this.froam = froam;
	}

	public String getThawauth() {
		return thawauth;
	}

	public void setThawauth(String thawauth) {
		this.thawauth = thawauth;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


}
