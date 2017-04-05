package com.zdsoft.finance.customer.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.customer.entity.AfterPersonaAssociation;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterPersonaAssociationService.java 
 * @ClassName: AfterPersonaAssociationService 
 * @Description: 贷后客户关系表接口
 * @author zhangchao 
 * @date 2017年2月6日 上午10:23:04 
 * @version V1.0
 */
public interface AfterPersonaAssociationService extends BaseService<AfterPersonaAssociation>{

	/**
	 * 
	 * @Title: findByPostLoanCustomerIdAndRelationship 
	 * @Description: 根据主表客户id和关系查询从表客户id
	 * @author zhangchao 
	 * @param postLoanCustomerId 主表客户id
	 * @param relationship 客户关系
	 * @return 从表客户id
	 */
	public String findByPostLoanCustomerIdAndRelationship(String postLoanCustomerId, String relationship);
}
