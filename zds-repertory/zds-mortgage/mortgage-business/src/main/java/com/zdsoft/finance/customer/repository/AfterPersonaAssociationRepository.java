package com.zdsoft.finance.customer.repository;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.AfterPersonaAssociation;

/**
 * 贷后客户关系表
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterPersonaAssociationRepository.java 
 * @ClassName: AfterPersonaAssociationRepository 
 * @Description: 
 * @author zhangchao 
 * @date 2017年2月6日 上午10:17:41 
 * @version V1.0
 */
public interface AfterPersonaAssociationRepository extends CustomRepository<AfterPersonaAssociation, String>{

	/**
	 * 根据主表客户id和关系查询从表客户id
	 * @Title: findByPostLoanCustomerIdAndRelationship 
	 * @Description: 
	 * @author zhangchao 
	 * @param postLoanCustomerId 主表客户id
	 * @param relationship 客户关系
	 * @return 客户关系
	 */
	public AfterPersonaAssociation findByCustomerIdAndRelationship(String postLoanCustomerId, String relationship);
}
