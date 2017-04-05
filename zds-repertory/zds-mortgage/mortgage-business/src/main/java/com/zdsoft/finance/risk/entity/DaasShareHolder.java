package com.zdsoft.finance.risk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: DaasShareHolder.java
 * @Package com.zdsoft.finance.risk.entity
 * @Description: 企业股东及出资信息
 * @author panshm
 */
@Entity
@Table(name = "T_DAAS_SHAREHOLDER")
public class DaasShareHolder extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 出资日期
	 */
	@Column(length=10)
	private String condate;

	/**
	 * 出资方式
	 */
	@Column(length=64)
	private String conform;

	/**
	 * 国别
	 */
	@Column(length=100)
	private String country;

	/**
	 * 股东名称
	 */
	@Column(length=300)
	private String shaname;

	/**
	 * 认缴出资币种
	 */
	@Column(length=64)
	private String regcapcur;

	/**
	 * 出资比例
	 */
	@Column(length=50)
	private String fundedratio;

	/**
	 * 认缴出资额（万元）
	 */
	@Column(length=24)
	private String subconam;

	/**
	 * 订单id
	 */
	@Column(name="order_id", length=36)
	private String orderId;

	public String getCondate() {
		return condate;
	}

	public void setCondate(String condate) {
		this.condate = condate;
	}

	public String getConform() {
		return conform;
	}

	public void setConform(String conform) {
		this.conform = conform;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getShaname() {
		return shaname;
	}

	public void setShaname(String shaname) {
		this.shaname = shaname;
	}

	public String getRegcapcur() {
		return regcapcur;
	}

	public void setRegcapcur(String regcapcur) {
		this.regcapcur = regcapcur;
	}

	public String getFundedratio() {
		return fundedratio;
	}

	public void setFundedratio(String fundedratio) {
		this.fundedratio = fundedratio;
	}

	public String getSubconam() {
		return subconam;
	}

	public void setSubconam(String subconam) {
		this.subconam = subconam;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


}
