package com.zdsoft.finance.risk.vo;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: DaasPersonVo.java
 * @Package com.zdsoft.finance.risk.vo
 * @Description: 企业主要管理人员信息
 * @author panshm
 */
public class DaasPersonVo {
	
	/**
	 * 人员姓名
	 */
	private String pername;

	/**
	 * 职务
	 */
	private String position;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 订单id
	 */
	private String orderId;

	public String getPername() {
		return pername;
	}

	public void setPername(String pername) {
		this.pername = pername;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


}
