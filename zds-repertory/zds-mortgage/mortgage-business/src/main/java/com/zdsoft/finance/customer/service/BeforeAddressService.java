package com.zdsoft.finance.customer.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.customer.entity.BeforeAddress;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:BeforeAddressService.java
 * @Package:com.zdsoft.finance.customer.service
 * @Description:贷前客户地址
 * @author: xj
 * @date:2017年1月12日 下午1:30:20
 * @version:v1.0
 */
public interface BeforeAddressService extends BaseService<BeforeAddress> {
	/**
	 * 
	 * 保存或者修改客户地址
	 *
	 * @author xj
	 * @param beforeAddresss
	 * @return
	 */
	public List<BeforeAddress> saveOrUpdateAddress(List<BeforeAddress> beforeAddresss,String customerId,String token) throws Exception;
	/**
	 * 
	 * 根据客户id查询地址
	 *
	 * @author xj
	 * @param customerId
	 * @return
	 */
	public List<BeforeAddress> queryAddresss(String customerId);
	/**
	 * 
	 * 通过案件id和地址类型查询地址
	 *
	 * @author xj
	 * @param customerId
	 * @param addressType
	 * @return
	 */
	public BeforeAddress loadAddresss(String customerId,String addressType);
}
