package com.zdsoft.finance.customer.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:BeforePersonalCusomerRepository.java
 * @Package:com.zdsoft.finance.customer.repository
 * @Description:贷前客户
 * @author: xj
 * @date:2017年1月11日 下午2:41:49
 * @version:v1.0
 */
public interface BeforePersonalCusomerRepository extends CustomRepository<BeforePersonalCustomer,String> {
	/**
	 * 
	 * 通过证件类型和证件号码查询
	 *
	 * @author xj
	 * @param credentiaType
	 * @param credentialNo
	 * @return
	 */
	public List<BeforePersonalCustomer> findByCredentialTypeAndCredentialNoOrderByUpdateTimeDesc(String credentiaType,String credentialNo);
	
	
	
}
