package com.zdsoft.finance.risk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: DaasEntinv.java
 * @Package com.zdsoft.finance.risk.entity
 * @Description: 企业对外投资信息
 * @author panshm
 */
@Entity
@Table(name = "T_DAAS_ENTINV")
public class DaasEntinv extends BaseEntity {

	private static final long serialVersionUID = 1L;

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
	 * 注册号
	 */
	@Column(length=50)
	private String regno;

	/**
	 * 出资方式
	 */
	@Column(length=64)
	private String conform;

	/**
	 * 开业日期
	 */
	@Column(length=10)
	private String esdate;

	/**
	 * 出资比例
	 */
	@Column(length=50)
	private String fundedratio;

	/**
	 * 注册资本（万元）
	 */
	@Column(length=24)
	private String regcap;

	/**
	 * 登记机关
	 */
	@Column(length=64)
	private String regorg;
	
	/**
	 * 企业（机构）类型
	 */
	@Column(length=64)
	private String enttype;

	/**
	 * 注册资本币种
	 */
	@Column(length=64)
	private String regcapcur;

	/**
	 * 认缴出资额（万元）
	 */
	@Column(length=24)
	private String subconam;

	/**
	 * 认缴出资币种
	 */
	@Column(length=64)
	private String congrocur;

	/**
	 * 企业状态
	 */
	@Column(length=64)
	private String entstatus;

	/**
	 * 吊销日期
	 */
	@Column(length=10)
	private String revdate;

	/**
	 * 订单id
	 */
	@Column(name="order_id", length=36)
	private String orderId;

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

	public String getRegno() {
		return regno;
	}

	public void setRegno(String regno) {
		this.regno = regno;
	}

	public String getConform() {
		return conform;
	}

	public void setConform(String conform) {
		this.conform = conform;
	}

	public String getEsdate() {
		return esdate;
	}

	public void setEsdate(String esdate) {
		this.esdate = esdate;
	}

	public String getFundedratio() {
		return fundedratio;
	}

	public void setFundedratio(String fundedratio) {
		this.fundedratio = fundedratio;
	}

	public String getRegcap() {
		return regcap;
	}

	public void setRegcap(String regcap) {
		this.regcap = regcap;
	}

	public String getRegorg() {
		return regorg;
	}

	public void setRegorg(String regorg) {
		this.regorg = regorg;
	}

	public String getEnttype() {
		return enttype;
	}

	public void setEnttype(String enttype) {
		this.enttype = enttype;
	}

	public String getRegcapcur() {
		return regcapcur;
	}

	public void setRegcapcur(String regcapcur) {
		this.regcapcur = regcapcur;
	}

	public String getSubconam() {
		return subconam;
	}

	public void setSubconam(String subconam) {
		this.subconam = subconam;
	}

	public String getCongrocur() {
		return congrocur;
	}

	public void setCongrocur(String congrocur) {
		this.congrocur = congrocur;
	}

	public String getEntstatus() {
		return entstatus;
	}

	public void setEntstatus(String entstatus) {
		this.entstatus = entstatus;
	}

	public String getRevdate() {
		return revdate;
	}

	public void setRevdate(String revdate) {
		this.revdate = revdate;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


}
