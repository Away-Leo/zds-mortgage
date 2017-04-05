package com.zdsoft.finance.risk.vo;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: DaasAlterVo.java
 * @Package com.zdsoft.finance.risk.vo
 * @Description: 企业历史变更信息
 * @author panshm
 */
public class DaasAlterVo {

	/**
	 * 变更前内容
	 */
	private String altbe;

	/**
	 * 变更日期
	 */
	private String altdate;

	/**
	 * 变更事项
	 */
	private String altitem;

	/**
	 * 变更后内容
	 */
	private String altaf;
	
	/**
	 * 订单id
	 */
	private String orderId;

	public String getAltbe() {
		return altbe;
	}

	public void setAltbe(String altbe) {
		this.altbe = altbe;
	}

	public String getAltdate() {
		return altdate;
	}

	public void setAltdate(String altdate) {
		this.altdate = altdate;
	}

	public String getAltitem() {
		return altitem;
	}

	public void setAltitem(String altitem) {
		this.altitem = altitem;
	}

	public String getAltaf() {
		return altaf;
	}

	public void setAltaf(String altaf) {
		this.altaf = altaf;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


}
