package com.zdsoft.finance.customer.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.customer.entity.BeforeAddress;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BeforeAddressService.java 
 * @ClassName: BeforeAddressService 
 * @Description: 贷前客户地址
 * @author xj 
 * @date 2017年3月9日 上午9:56:50 
 * @version V1.0
 */
public interface BeforeAddressService extends BaseService<BeforeAddress> {
	

	/**
	 * 
	 * @Title: saveOrUpdateAddress 
	 * @Description: 保存或者修改客户地址
	 * @author xj 
	 * @param beforeAddresss 地址集合
	 * @param customerId 客户id
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public List<BeforeAddress> saveOrUpdateAddress(List<BeforeAddress> beforeAddresss,String customerId,String token) throws Exception;
	
	/**
	 * 
	 * @Title: queryAddresss 
	 * @Description: 根据客户id查询地址
	 * @author xj 
	 * @param customerId 客户id
	 * @return
	 */
	public List<BeforeAddress> queryAddresss(String customerId);
	
	/**
	 * 
	 * @Title: loadAddresss 
	 * @Description: 通过案件id和地址类型查询地址
	 * @author xj 
	 * @param customerId 客户id
	 * @param addressType 地址类型
	 * @return
	 */
	public BeforeAddress loadAddresss(String customerId,String addressType);
}
