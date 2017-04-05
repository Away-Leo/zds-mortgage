package com.zdsoft.finance.risk.vo;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: DaasMorguaInfoVo.java
 * @Package com.zdsoft.finance.risk.vo
 * @Description: 动产抵押物信息
 * @author panshm
 */
public class DaasMorguaInfoVo {

	/**
	 * 抵押物名称
	 */
	private String guaname;

	/**
	 * 数量
	 */
	private String quan;

	/**
	 * 价值（万元）
	 */
	private String value;

	/**
	 * 抵押ID
	 */
	private String morregId;

	/**
	 * 订单id
	 */
	private String orderId;

	public String getGuaname() {
		return guaname;
	}

	public void setGuaname(String guaname) {
		this.guaname = guaname;
	}

	public String getQuan() {
		return quan;
	}

	public void setQuan(String quan) {
		this.quan = quan;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getMorregId() {
		return morregId;
	}

	public void setMorregId(String morregId) {
		this.morregId = morregId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
