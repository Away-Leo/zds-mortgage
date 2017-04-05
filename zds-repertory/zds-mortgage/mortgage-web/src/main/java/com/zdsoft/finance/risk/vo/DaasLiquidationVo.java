package com.zdsoft.finance.risk.vo;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: DaasLiquidationVo.java
 * @Package com.zdsoft.finance.risk.vo
 * @Description: 清算信息
 * @author panshm
 */
public class DaasLiquidationVo {

	/**
	 * 清算完结情况
	 */
	private String ligst;

	/**
	 * 清算组成员
	 */
	private String liqmen;

	/**
	 * 债券承接人
	 */
	private String claimtranee;

	/**
	 * 清算责任人
	 */
	private String ligentity;

	/**
	 * 清算负责人
	 */
	private String ligprincipal;

	/**
	 * 债务承接人
	 */
	private String debttranee;

	/**
	 * 清算完结日期
	 */
	private String ligenddate;

	/**
	 * 订单id
	 */
	private String orderId;

	public String getLigst() {
		return ligst;
	}

	public void setLigst(String ligst) {
		this.ligst = ligst;
	}

	public String getLiqmen() {
		return liqmen;
	}

	public void setLiqmen(String liqmen) {
		this.liqmen = liqmen;
	}

	public String getClaimtranee() {
		return claimtranee;
	}

	public void setClaimtranee(String claimtranee) {
		this.claimtranee = claimtranee;
	}

	public String getLigentity() {
		return ligentity;
	}

	public void setLigentity(String ligentity) {
		this.ligentity = ligentity;
	}

	public String getLigprincipal() {
		return ligprincipal;
	}

	public void setLigprincipal(String ligprincipal) {
		this.ligprincipal = ligprincipal;
	}

	public String getDebttranee() {
		return debttranee;
	}

	public void setDebttranee(String debttranee) {
		this.debttranee = debttranee;
	}

	public String getLigenddate() {
		return ligenddate;
	}

	public void setLigenddate(String ligenddate) {
		this.ligenddate = ligenddate;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


}
