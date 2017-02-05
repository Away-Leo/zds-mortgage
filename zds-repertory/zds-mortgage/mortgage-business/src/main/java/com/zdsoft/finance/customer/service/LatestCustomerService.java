package com.zdsoft.finance.customer.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.customer.entity.LatestCustomer;

/**
 * 最新客户接口
 * @author zhangchao
 * @date 2016-12-22
 */
public interface LatestCustomerService extends BaseService<LatestCustomer>{

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
	 * 根据配偶id查询
	 * @param clientId 配偶ID
	 * @return
	 */
	public LatestCustomer findByfindByClientId(String clientId);
	
	/**
	 * 根据id查询相关客户姓名
	 * @param id 客户id
	 * @return
	 */
	public List<LatestCustomer> findClientNameByClientId(String id);
}
