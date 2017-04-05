package com.zdsoft.finance.risk.vo;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: DaasFiliationVo.java
 * @Package com.zdsoft.finance.risk.vo
 * @Description: 企业分支机构信息
 * @author panshm
 */
public class DaasFiliationVo {

	/**
	 * 分支机构企业注册号
	 */
	private String brregno;

	/**
	 * 分支机构名称
	 */
	private String brname;

	/**
	 * 一般经营项目
	 */
	private String cbuitem;

	/**
	 * 分支机构地址
	 */
	private String braddr;

	/**
	 * 分支机构负责人
	 */
	private String brprincipal;
	
	/**
	 * 订单id
	 */
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
