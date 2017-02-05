package com.zdsoft.finance.customer.vo;

import com.zdsoft.finance.customer.entity.LatestCustomer;

/**
 * 最新客户信息
 * @author zhangchao
 * 2016/12/21
 */
public class LatestCustomerVo extends CustomerVo {

	public LatestCustomerVo(){}
	
	public LatestCustomerVo(LatestCustomer po){
		super(po);
	}
	
	public LatestCustomer toPO(){
		LatestCustomer po = new LatestCustomer();
		return (LatestCustomer) super.toPo(this, po);
	}
}
