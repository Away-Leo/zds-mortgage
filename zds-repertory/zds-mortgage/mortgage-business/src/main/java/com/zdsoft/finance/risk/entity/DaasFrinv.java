package com.zdsoft.finance.risk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: DaasFrinv.java
 * @Package com.zdsoft.finance.risk.entity
 * @Description: 企业法定代表人对外投资信息
 * @author panshm
 */
@Entity
@Table(name = "T_DAAS_FRINV")
public class DaasFrinv extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 企业（机构）类型
	 */
	@Column(length=64)
	private String enttype;

	/**
	 * 注册资本（万元）
	 */
	@Column(length=24)
	private String regcap;

	/**
	 * 注册资本币种
	 */
	@Column(length=64)
	private String regcapcur;

	/**
	 * 出资比例
	 */
	@Column(length=50)
	private String fundedratio;

	/**
	 * 开业日期
	 */
	@Column(length=10)
	private String esdate;

	/**
	 * 企业状态
	 */
	@Column(length=64)
	private String entstatus;

	/**
	 * 出资方式
	 */
	@Column(length=64)
	private String conform;

	/**
	 * 注册号
	 */
	@Column(length=50)
	private String regno;

	/**
	 * 注销日期
	 */
	@Column(length=10)
	private String candate;

	/**
	 * 企业（机构）名称
	 */
	@Column(length=100)
	private String entname;

	/**
	 * 法定代表人姓名
	 */
	@Column(length=100)
	private String name;

	/**
	 * 认缴出资币种
	 */
	@Column(length=64)
	private String currency;

	/**
	 * 吊销日期
	 */
	@Column(length=10)
	private String revdate;

	/**
	 * 登记机关
	 */
	@Column(length=64)
	private String regorg;


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

	public String getEnttype() {
		return enttype;
	}

	public void setEnttype(String enttype) {
		this.enttype = enttype;
	}

	public String getRegcap() {
		return regcap;
	}

	public void setRegcap(String regcap) {
		this.regcap = regcap;
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

	public String getEsdate() {
		return esdate;
	}

	public void setEsdate(String esdate) {
		this.esdate = esdate;
	}

	public String getEntstatus() {
		return entstatus;
	}

	public void setEntstatus(String entstatus) {
		this.entstatus = entstatus;
	}

	public String getConform() {
		return conform;
	}

	public void setConform(String conform) {
		this.conform = conform;
	}

	public String getRegno() {
		return regno;
	}

	public void setRegno(String regno) {
		this.regno = regno;
	}

	public String getCandate() {
		return candate;
	}

	public void setCandate(String candate) {
		this.candate = candate;
	}

	public String getEntname() {
		return entname;
	}

	public void setEntname(String entname) {
		this.entname = entname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getRevdate() {
		return revdate;
	}

	public void setRevdate(String revdate) {
		this.revdate = revdate;
	}

	public String getRegorg() {
		return regorg;
	}

	public void setRegorg(String regorg) {
		this.regorg = regorg;
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
