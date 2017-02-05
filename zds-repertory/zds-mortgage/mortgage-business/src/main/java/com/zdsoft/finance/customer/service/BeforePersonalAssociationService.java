package com.zdsoft.finance.customer.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BeforePersonalAssociation;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:BeforePersonalAssociationRepositoryService.java
 * @Package:com.zdsoft.finance.customer.service
 * @Description:客户关系
 * @author: xj
 * @date:2017年1月11日 下午8:26:22
 * @version:v1.0
 */
public interface BeforePersonalAssociationService extends BaseService<BeforePersonalAssociation> {
	/**
	 * 
	 * 通过客户主借人id和关系类型查询客户关系
	 *
	 * @author xj
	 * @param relationship 关系类型
	 * @param beforeCustomerId
	 * @param beforePersonalCusomerId
	 * @return
	 */
	public List<BeforePersonalAssociation> queryAssociation(String relationship,String beforeCustomerId);
	/**
	 * 
	 * 通过主借人和关联客户查询
	 *
	 * @author xj
	 * @param beforeCustomerId 主借人id
	 * @param beforePersonalCusomerId 关联客户id
	 * @return
	 */
	public BeforePersonalAssociation loadByAssociation(String beforeCustomerId,String beforePersonalCusomerId);
	/**
	 * 
	 * 设置参与类型
	 *
	 * @author xj
	 * @param customerId 主
	 * @param personalCustomer 从
	 * @param relationship 关系
	 * @throws BusinessException 
	 */
	public void setCustomerAssociation(String customerId,BeforePersonalCustomer personalCustomer,String relationship) throws BusinessException;

	/**
	 * 通过主借人ID 查找主借人相关人数据
	 * 
	 * @author liuhuan
	 * @param customerId 主借人ID
	 * @return
	 */
	public List<BeforePersonalAssociation> queryCustomerId(String customerId);
	
}
