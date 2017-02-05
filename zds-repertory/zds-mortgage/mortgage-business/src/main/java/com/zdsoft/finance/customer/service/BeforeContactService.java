package com.zdsoft.finance.customer.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.customer.entity.BeforeContact;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:BeforeContactService.java
 * @Package:com.zdsoft.finance.customer.service
 * @Description:联系方式
 * @author: xj
 * @date:2017年1月12日 下午2:12:26
 * @version:v1.0
 */
public interface BeforeContactService extends BaseService<BeforeContact> {
	/**
	 * 
	 * 保存或者修改联系方式
	 *
	 * @author xj
	 * @param BeforeContact
	 * @return
	 */
	public List<BeforeContact> saveOrUpdateContact(List<BeforeContact> beforeContact,String customerId,String token) throws Exception;
	/**
	 * 
	 * 根据客户id查询联系方式
	 *
	 * @author xj
	 * @param customerId
	 * @return
	 */
	public List<BeforeContact> queryContact(String customerId);
	/**
	 * 
	 * 通过案件id和地址类型查询联系方式
	 *
	 * @author xj
	 * @param customerId
	 * @param addressType
	 * @return
	 */
	public List<BeforeContact> loadContact(String customerId,String addressType);
}
