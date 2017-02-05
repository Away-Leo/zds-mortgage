package com.zdsoft.finance.customer.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 最新客户信息
 * @author zhangchao
 * 2016/12/21
 */
@Entity
@Table(name = "zf_latestcustomer")
public class LatestCustomer extends Customer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
