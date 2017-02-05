package com.zdsoft.finance.customer.repository;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.PostLoanPersonaAssociation;

/**
 * 贷后客户关系表
 * @author <a href="mailto:502549751@qq.com">zhangchao</a>
 * @createTime: 2017-01-10 14::41
 * @version: 1.0
 *
 */
public interface PostLoanPersonaAssociationRepository extends CustomRepository<PostLoanPersonaAssociation, String>{

	/**
	 * 根据主表客户id和关系查询从表客户id
	 * @param postLoanCustomerId 主表客户id
	 * @param relationship 客户关系
	 * @return 客户关系表
	 */
	public PostLoanPersonaAssociation findByPostLoanCustomerIdAndRelationship(String postLoanCustomerId, String relationship);
}
