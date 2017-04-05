package com.zdsoft.finance.risk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: DaasBasic.java
 * @Package com.zdsoft.finance.risk.entity
 * @Description: 企业照面信息
 * @author panshm
 */
@Entity
@Table(name = "T_DAAS_BASIC")
public class DaasBasic extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 统一社会信用代码
	 */
	@Column(length=18)
	private String creditcode;

	/**
	 * 注册号
	 */
	@Column(length=50)
	private String regno;

	/**
	 * 注册资本币种
	 */
	@Column(length=50)
	private String regcapcur;

	/**
	 * 经营期限至
	 */
	@Column(length=10)
	private String opto;

	/**
	 * 经营（业务）范围
	 */
	@Column(length=3000)
	private String opscope;

	/**
	 * 法定代表人/负责人/执行事务合伙人
	 */
	@Column(length=100)
	private String frname;

	/**
	 * 注册资本（万元）
	 */
	@Column(length=30)
	private String regcap;

	/**
	 * 住址
	 */
	@Column(length=600)
	private String dom;

	/**
	 * 经营（业务）范围及方式
	 */
	@Column(length=3000)
	private String opscoandform;

	/**
	 * 原注册号
	 */
	@Column(length=50)
	private String oriregno;

	/**
	 * 许可经营项目
	 */
	@Column(length=1000)
	private String abuitem;

	/**
	 * 登记机关
	 */
	@Column(length=64)
	private String regorg;

	/**
	 * 成立日期
	 */
	@Column(length=10)
	private String esdate;

	/**
	 * 吊销日期
	 */
	@Column(length=10)
	private String revdate;

	/**
	 * 经营期限自
	 */
	@Column(length=10)
	private String opfrom;

	/**
	 * 企业（机构）类型
	 */
	@Column(length=64)
	private String enttype;

	/**
	 * 注销日期
	 */
	@Column(length=10)
	private String candate;

	/**
	 * 经营状态
	 */
	@Column(length=64)
	private String entstatus;

	/**
	 * 企业名称
	 */
	@Column(length=100)
	private String entname;

	/**
	 * 一般经营项目
	 */
	@Column(length=4000)
	private String cbuitem;

	/**
	 * 订单id
	 */
	@Column(name="order_id", length=36)
	private String orderId;

	public String getCreditcode() {
		return creditcode;
	}

	public void setCreditcode(String creditcode) {
		this.creditcode = creditcode;
	}

	public String getRegno() {
		return regno;
	}

	public void setRegno(String regno) {
		this.regno = regno;
	}

	public String getRegcapcur() {
		return regcapcur;
	}

	public void setRegcapcur(String regcapcur) {
		this.regcapcur = regcapcur;
	}

	public String getOpto() {
		return opto;
	}

	public void setOpto(String opto) {
		this.opto = opto;
	}

	public String getOpscope() {
		return opscope;
	}

	public void setOpscope(String opscope) {
		this.opscope = opscope;
	}

	public String getFrname() {
		return frname;
	}

	public void setFrname(String frname) {
		this.frname = frname;
	}

	public String getRegcap() {
		return regcap;
	}

	public void setRegcap(String regcap) {
		this.regcap = regcap;
	}

	public String getDom() {
		return dom;
	}

	public void setDom(String dom) {
		this.dom = dom;
	}

	public String getOpscoandform() {
		return opscoandform;
	}

	public void setOpscoandform(String opscoandform) {
		this.opscoandform = opscoandform;
	}

	public String getOriregno() {
		return oriregno;
	}

	public void setOriregno(String oriregno) {
		this.oriregno = oriregno;
	}

	public String getAbuitem() {
		return abuitem;
	}

	public void setAbuitem(String abuitem) {
		this.abuitem = abuitem;
	}

	public String getRegorg() {
		return regorg;
	}

	public void setRegorg(String regorg) {
		this.regorg = regorg;
	}

	public String getEsdate() {
		return esdate;
	}

	public void setEsdate(String esdate) {
		this.esdate = esdate;
	}

	public String getRevdate() {
		return revdate;
	}

	public void setRevdate(String revdate) {
		this.revdate = revdate;
	}

	public String getOpfrom() {
		return opfrom;
	}

	public void setOpfrom(String opfrom) {
		this.opfrom = opfrom;
	}

	public String getEnttype() {
		return enttype;
	}

	public void setEnttype(String enttype) {
		this.enttype = enttype;
	}

	public String getCandate() {
		return candate;
	}

	public void setCandate(String candate) {
		this.candate = candate;
	}

	public String getEntstatus() {
		return entstatus;
	}

	public void setEntstatus(String entstatus) {
		this.entstatus = entstatus;
	}

	public String getEntname() {
		return entname;
	}

	public void setEntname(String entname) {
		this.entname = entname;
	}

	public String getCbuitem() {
		return cbuitem;
	}

	public void setCbuitem(String cbuitem) {
		this.cbuitem = cbuitem;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


}
