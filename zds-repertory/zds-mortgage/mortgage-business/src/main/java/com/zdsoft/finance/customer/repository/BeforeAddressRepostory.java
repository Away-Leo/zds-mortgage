package com.zdsoft.finance.customer.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.BeforeAddress;
/**
 * 
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:BeforeAddressRepostory.java
 * @Package:com.zdsoft.finance.customer.repository
 * @Description:贷前客户地址
 * @author: xj
 * @date:2017年1月12日 下午1:38:59
 * @version:v1.0
 */
public interface BeforeAddressRepostory extends CustomRepository<BeforeAddress, String> {
	/**
	 * 
	 * 通过案件查询地址
	 *
	 * @author xj
	 * @param beforeCustomerId
	 * @return
	 */
	public List<BeforeAddress> findByCustomerId(String customerId);
	/**
	 * 
	 * 通过案件id和地址类型查询地址
	 *
	 * @author xj
	 * @param beforeCustomerId
	 * @return
	 */
	public BeforeAddress findByCustomerIdAndAddressType(String customerId,String addressType);
}
