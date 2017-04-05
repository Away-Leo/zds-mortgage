package com.zdsoft.finance.customer.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.customer.entity.AfterAddress;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterAddressService.java 
 * @ClassName: AfterAddressService 
 * @Description: 贷后客户地址接口
 * @author zhangchao 
 * @date 2017年2月6日 上午10:22:41 
 * @version V1.0
 */
public interface AfterAddressService extends BaseService<AfterAddress>{

	/**
	 * 
	 * @Title: findByAddressTypeAndCustomerId 
	 * @Description: 根据地址类型和客户id查询客户地址
	 * @author zhangchao 
	 * @param addressType 地址类型
	 * @param customerId 客户id
	 * @return 客户地址
	 */
	public AfterAddress findByAddressTypeAndCustomerId(String addressType, String customerId);
	
	/**
	 * 
	 * @Title: findByCustomerNameAndCustomerIdAndAddressType 
	 * @Description: 根据客户姓名和客户id和地址类型查询客户地址
	 * @author zhangchao 
	 * @param customerName 客户姓名
	 * @param customerId 客户id
	 * @param addressType 地址类型
	 * @return 客户地址
	 */
	public AfterAddress findByCustomerNameAndCustomerIdAndAddressType(String customerName, String customerId, String addressType);
}
