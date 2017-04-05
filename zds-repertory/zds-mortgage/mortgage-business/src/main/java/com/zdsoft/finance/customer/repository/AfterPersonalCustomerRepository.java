package com.zdsoft.finance.customer.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.AfterPersonalCustomer;

/**
 * 贷后个人客户信息
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterPersonalCustomerRepository.java 
 * @ClassName: AfterPersonalCustomerRepository 
 * @Description: 
 * @author zhangchao 
 * @date 2017年2月6日 上午10:17:47 
 * @version V1.0
 */
public interface AfterPersonalCustomerRepository extends CustomRepository<AfterPersonalCustomer, String>{

	/**
	 * 查询客户
	 * @Title: findBycredentialTypeAndCredentialNo 
	 * @Description: 
	 * @author zhangchao 
	 * @param credentialType 证件类型
	 * @param credentialNo 证件编号
	 * @return 客户信息
	 */
	public AfterPersonalCustomer findByCredentialTypeAndCredentialNo(String credentialType, String credentialNo);
	
	/**
	 * 
	 * @Title: findByCredentialTypeAndCredentialNoAndIsLatest 
	 * @Description: 通过证件和是否是最新查询
	 * @author xj 
	 * @param credentialType 证件类型
	 * @param credentialNo 证件号码
	 * @param isLatest 是否最新 true：最新，false：不是最新
	 * @return
	 */
	public List<AfterPersonalCustomer> findByCredentialTypeAndCredentialNoAndIsLatest(String credentialType, String credentialNo,boolean isLatest);
}
