package com.zdsoft.finance.casemanage.datasurvey.vo;

import java.io.Serializable;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:FeeReceiverVo.java
 * @Package:com.zdsoft.finance.casemanage.datasurvey.vo
 * @Description:费用收费对象Vo
 * @author: jingyh
 * @date:2017年1月16日 下午9:01:12
 * @version:v1.0
 */
public class FeeReceiverVo implements Serializable {

	private static final long serialVersionUID = 3463378527969969446L;
	
	/**
	 * 客户Id
	 */
	private String customerId;
	
	/**
	 * 客户姓名
	 */
	private String customerName;
	
	/**
	 * 参与类型
	 */
	private String joinType;
	
	/**
	 * 参与类型名称
	 */
	private String joinTypeName;
	
	/**
	 * 生日
	 */
	private Long birthdayDate;
	
	/**
	 * 客户年龄
	 */
	private Integer customerAge;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getJoinType() {
		return joinType;
	}

	public void setJoinType(String joinType) {
		this.joinType = joinType;
	}

	public String getJoinTypeName() {
		return joinTypeName;
	}

	public void setJoinTypeName(String joinTypeName) {
		this.joinTypeName = joinTypeName;
	}

	public Long getBirthdayDate() {
		return birthdayDate;
	}

	public void setBirthdayDate(Long birthdayDate) {
		this.birthdayDate = birthdayDate;
	}

	public Integer getCustomerAge() {
		return customerAge;
	}

	public void setCustomerAge(Integer customerAge) {
		this.customerAge = customerAge;
	}
}

