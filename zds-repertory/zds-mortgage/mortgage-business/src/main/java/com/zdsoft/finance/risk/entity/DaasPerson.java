package com.zdsoft.finance.risk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: DaasPerson.java
 * @Package com.zdsoft.finance.risk.entity
 * @Description: 企业主要管理人员信息
 * @author panshm
 */
@Entity
@Table(name = "T_DAAS_PERSON")
public class DaasPerson extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 人员姓名
	 */
	@Column(length=100)
	private String pername;

	/**
	 * 职务
	 */
	@Column(length=200)
	private String position;

	/**
	 * 性别
	 */
	@Column(length=64)
	private String sex;

	/**
	 * 订单id
	 */
	@Column(name="order_id", length=36)
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
