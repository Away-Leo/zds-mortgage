package com.zdsoft.finance.customer.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.BeforeContact;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:BeforeContactRepository.java
 * @Package:com.zdsoft.finance.customer.repository
 * @Description:贷前客户联系方式
 * @author: xj
 * @date:2017年1月11日 下午2:56:09
 * @version:v1.0
 */
public interface BeforeContactRepository extends CustomRepository<BeforeContact, String> {
	/**
	 * 
	 * 通过贷前客户id查询联系方式
	 *
	 * @author xj
	 * @param beforeCustomerId
	 * @return
	 */
	public List<BeforeContact> findByCustomerId(String customerId);
	/**
	 * 
	 * 通过案件id和联系类型查询联系方式
	 *
	 * @author xj
	 * @param beforeCustomerId
	 * @return
	 */
	public List<BeforeContact> findByCustomerIdAndContactType(String customerId,String contactType);
}
