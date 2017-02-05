package com.zdsoft.finance.customer.repository;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.PostLoanAddress;

/**
 * 贷后客户地址
 * @author <a href="mailto:502549751@qq.com">zhangchao</a>
 * @createTime: 2017-01-10 14::41
 * @version: 1.0
 *
 */
public interface PostLoanAddressRepository extends CustomRepository<PostLoanAddress, String>{

	/**
	 * 根据地址类型和客户id查询客户地址
	 * @param addressType 地址类型
	 * @param customerId 客户id
	 * @return 客户地址
	 */
	public PostLoanAddress findByAddressTypeAndCustomerId(String addressType, String customerId);
	
	/**
	 * 根据客户姓名和客户id查询客户地址
	 * @param customerName
	 * @param customerId
	 * @return
	 */
	public PostLoanAddress findByCustomerNameAndCustomerIdAndAddressType(String customerName, String customerId, String addressType);
}
