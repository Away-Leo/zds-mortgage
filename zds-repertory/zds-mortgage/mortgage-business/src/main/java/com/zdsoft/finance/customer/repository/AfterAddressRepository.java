package com.zdsoft.finance.customer.repository;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.AfterAddress;

/**
 * 贷后客户地址
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterAddressRepository.java 
 * @ClassName: AfterAddressRepository 
 * @Description: 
 * @author zhangchao 
 * @date 2017年2月6日 上午10:17:26 
 * @version V1.0
 */
public interface AfterAddressRepository extends CustomRepository<AfterAddress, String>{

	/**
	 * 根据地址类型和客户id查询客户地址
	 * @Title: findByAddressTypeAndCustomerId 
	 * @Description: 
	 * @author zhangchao 
	 * @param addressType 地址类型
	 * @param customerId 客户id
	 * @return 客户地址
	 */
	public AfterAddress findByAddressTypeAndCustomerId(String addressType, String customerId);
	
	/**
	 * 根据客户姓名和客户id和地址类型查询客户地址
	 * @Title: findByCustomerNameAndCustomerIdAndAddressType 
	 * @Description: 
	 * @author zhangchao 
	 * @param customerName 客户姓名
	 * @param customerId 客户id
	 * @param addressType 地址类型
	 * @return 客户地址
	 */
	public AfterAddress findByCustomerNameAndCustomerIdAndAddressType(String customerName, String customerId, String addressType);
}
