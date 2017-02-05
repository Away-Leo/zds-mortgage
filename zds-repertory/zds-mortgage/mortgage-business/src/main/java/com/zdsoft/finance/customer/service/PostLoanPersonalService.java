package com.zdsoft.finance.customer.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.customer.entity.PostLoanPersonal;

/**
 * 贷后个人客户信息接口
 * @author <a href="mailto:502549751@qq.com">zhangchao</a>
 * @createTime: 2017-01-10 14::41
 * @version: 1.0
 *
 */
public interface PostLoanPersonalService extends BaseService<PostLoanPersonal>{

	/**
	 * 查询客户
	 * @param credentiaType 证件类型
	 * @param credentialNo 证件编号
	 * @return
	 */
	public PostLoanPersonal findByCredentiaTypeAndCredentialNo(String credentiaType, String credentialNo);
	
	/**
	 * 根据配偶id查询
	 * @param clientId 客户配偶ID
	 * @return
	 */
	public PostLoanPersonal findByCustomerId(String clientId);
}
