package com.zdsoft.finance.risk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: DaasMordetail.java
 * @Package com.zdsoft.finance.risk.entity
 * @Description: 动产抵押信息
 * @author panshm
 */
@Entity
@Table(name = "T_DAAS_MORDETAIL")
public class DaasMordetail extends BaseEntity {

	private static final long serialVersionUID = 1L;
	  
	/**
	 * 抵押人
	 */
	@Column(length=60)
	private String mortgagor;

	/**
	 * 状态标识
	 */
	@Column(length=64)
	private String mortype;

	/**
	 * 抵押ID
	 */
	@Column(name="morreg_id", length=100)
	private String morregId;

	/**
	 * 履约起始日期
	 */
	@Column(length=10)
	private String pefperform;

	/**
	 * 履约截止日期
	 */
	@Column(length=10)
	private String pefperto;

	/**
	 * 登记日期
	 */
	@Column(length=10)
	private String regidate;

	/**
	 * 登记证号
	 */
	@Column(length=100)
	private String morrengcno;

	/**
	 * 被担保主债权数额（万元）
	 */
	@Column(length=24)
	private String priclasecam;

	/**
	 * 申请抵押原因
	 */
	@Column(length=300)
	private String appregrea;

	/**
	 * 被担保主债权种类
	 */
	@Column(length=64)
	private String priclaseckind;

	/**
	 * 注销日期
	 */
	@Column(length=10)
	private String candate;

	/**
	 * 抵押权人
	 */
	@Column(length=60)
	private String more;

	/**
	 * 登记机关
	 */
	@Column(length=64)
	private String regorg;
	
	/**
	 * 订单id
	 */
	@Column(name="order_id", length=36)
	private String orderId;

	public String getMortgagor() {
		return mortgagor;
	}

	public void setMortgagor(String mortgagor) {
		this.mortgagor = mortgagor;
	}

	public String getMortype() {
		return mortype;
	}

	public void setMortype(String mortype) {
		this.mortype = mortype;
	}

	public String getMorregId() {
		return morregId;
	}

	public void setMorregId(String morregId) {
		this.morregId = morregId;
	}

	public String getPefperform() {
		return pefperform;
	}

	public void setPefperform(String pefperform) {
		this.pefperform = pefperform;
	}

	public String getPefperto() {
		return pefperto;
	}

	public void setPefperto(String pefperto) {
		this.pefperto = pefperto;
	}

	public String getRegidate() {
		return regidate;
	}

	public void setRegidate(String regidate) {
		this.regidate = regidate;
	}

	public String getMorrengcno() {
		return morrengcno;
	}

	public void setMorrengcno(String morrengcno) {
		this.morrengcno = morrengcno;
	}

	public String getPriclasecam() {
		return priclasecam;
	}

	public void setPriclasecam(String priclasecam) {
		this.priclasecam = priclasecam;
	}

	public String getAppregrea() {
		return appregrea;
	}

	public void setAppregrea(String appregrea) {
		this.appregrea = appregrea;
	}

	public String getPriclaseckind() {
		return priclaseckind;
	}

	public void setPriclaseckind(String priclaseckind) {
		this.priclaseckind = priclaseckind;
	}

	public String getCandate() {
		return candate;
	}

	public void setCandate(String candate) {
		this.candate = candate;
	}

	public String getMore() {
		return more;
	}

	public void setMore(String more) {
		this.more = more;
	}

	public String getRegorg() {
		return regorg;
	}

	public void setRegorg(String regorg) {
		this.regorg = regorg;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


}
