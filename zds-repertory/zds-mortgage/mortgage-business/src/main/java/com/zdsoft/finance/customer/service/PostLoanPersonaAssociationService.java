package com.zdsoft.finance.customer.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.customer.entity.PostLoanPersonaAssociation;

/**
 * 贷后客户关系表接口
 * @author <a href="mailto:502549751@qq.com">zhangchao</a>
 * @createTime: 2017-01-10 14::41
 * @version: 1.0
 *
 */
public interface PostLoanPersonaAssociationService extends BaseService<PostLoanPersonaAssociation>{

	/**
	 * 根据主表客户id和关系查询从表客户id
	 * @param postLoanCustomerId 主表客户id
	 * @param relationship 客户关系
	 * @return 从表客户id
	 */
	public String findByPostLoanCustomerIdAndRelationship(String postLoanCustomerId, String relationship);
}
