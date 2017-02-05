package com.zdsoft.finance.customer.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.LatestCustomer;
/**
 * 最新客户信息
 * @author zhangchao
 * 2016/12/21
 */
public interface LatestCustomerRepository extends CustomRepository<LatestCustomer, String> {

	/**
	 * 根据客户名称查询
	 * @param clientNm 客户名称
	 * @return
	 */
	public List<LatestCustomer> findByClientNm(String clientNm);
	
	/**
	 * 根据配偶id查询
	 * @param clientId 客户配偶ID
	 * @return
	 */
	public LatestCustomer findByClientId(String clientId);
	
	/**
	 * 根据证件号查询
	 * @param credentialNo 证件号
	 * @return
	 */
	public List<LatestCustomer> findByCredentialNo(String credentialNo);
	
	/**
	 * 查询客户
	 * @param clientNm 客户名称
	 * @param credentiaType 证件类型
	 * @param credentialNo 证件编号
	 * @return
	 */
	public LatestCustomer findByCredentialNo(String clientNm, String credentiaType, String credentialNo);
	
	/**
	 * 查询客户
	 * @param credentiaType 证件类型
	 * @param credentialNo 证件编号
	 * @return
	 */
	public LatestCustomer findByCredentiaTypeAndCredentialNo(String credentiaType, String credentialNo);
	
	/**
	 * 根据id查询相关客户姓名
	 * @param id 客户id
	 * @return
	 */
	public List<LatestCustomer> findClientNameByClientId(String id);
}
