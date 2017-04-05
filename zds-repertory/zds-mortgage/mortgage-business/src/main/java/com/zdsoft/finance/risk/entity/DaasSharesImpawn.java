package com.zdsoft.finance.risk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: DaasSharesImpawn.java
 * @Package com.zdsoft.finance.risk.entity
 * @Description: 股权出质历史信息
 * @author panshm
 */
@Entity
@Table(name = "T_DAAS_SHARESIMPAWN")
public class DaasSharesImpawn extends BaseEntity {

	private static final long serialVersionUID = 1L;
	  
	/**
	 * 出质批准日期
	 */
	@Column(length=10)
	private String impsandate;

	/**
	 * 出质截至日期
	 */
	@Column(length=10)
	private String impto;

	/**
	 * 出质金额（万元）
	 */
	@Column(length=24)
	private String impam;

	/**
	 * 质权人姓名
	 */
	@Column(length=100)
	private String imporg;

	/**
	 * 质权人类别
	 */
	@Column(length=64)
	private String imporgtype;

	/**
	 * 出质备案日期
	 */
	@Column(length=10)
	private String imponrecdate;

	/**
	 * 出质审批日期
	 */
	@Column(length=100)
	private String impexaeep;
	
	/**
	 * 订单id
	 */
	@Column(name="order_id", length=36)
	private String orderId;

	public String getImpsandate() {
		return impsandate;
	}

	public void setImpsandate(String impsandate) {
		this.impsandate = impsandate;
	}

	public String getImpto() {
		return impto;
	}

	public void setImpto(String impto) {
		this.impto = impto;
	}

	public String getImpam() {
		return impam;
	}

	public void setImpam(String impam) {
		this.impam = impam;
	}

	public String getImporg() {
		return imporg;
	}

	public void setImporg(String imporg) {
		this.imporg = imporg;
	}

	public String getImporgtype() {
		return imporgtype;
	}

	public void setImporgtype(String imporgtype) {
		this.imporgtype = imporgtype;
	}

	public String getImponrecdate() {
		return imponrecdate;
	}

	public void setImponrecdate(String imponrecdate) {
		this.imponrecdate = imponrecdate;
	}

	public String getImpexaeep() {
		return impexaeep;
	}

	public void setImpexaeep(String impexaeep) {
		this.impexaeep = impexaeep;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


}
