package com.zdsoft.finance.customer.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.BeforeCustomer;

/**
 * 贷前客户
 * @author zhangchao
 * 2016/12/21
 */
public interface BeforeCustomerRepository extends CustomRepository<BeforeCustomer, String> {

	/**
	 * 根据客户名称查询
	 * @param clientNm 客户名称
	 * @return
	 */
	//public List<BeforeCustomer> findByClientNm(String clientNm);
	
	/**
	 * 根据证件号查询
	 * @param credentialNo 证件号
	 * @return
	 */
	//public List<BeforeCustomer> findByCredentialNo(String credentialNo);
}
