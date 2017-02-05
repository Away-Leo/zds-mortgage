package com.zdsoft.finance.customer.entity;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:CustomerCreateType.java
 * @Package:com.zdsoft.finance.customer.entity
 * @Description:客户创建类型
 * @author: xj
 * @date:2017年1月12日 下午5:18:16
 * @version:v1.0
 */
public enum CustomerCreateType {
	/**
	 * pc端创建
	 */
	MAIN_CUSTOMER(0),

	/**
	 * app创建
	 */
	APP(1);
	private int value;

	public int value() {
		return value;
	}

	private CustomerCreateType(int status) {
		this.value = status;
	}
}
