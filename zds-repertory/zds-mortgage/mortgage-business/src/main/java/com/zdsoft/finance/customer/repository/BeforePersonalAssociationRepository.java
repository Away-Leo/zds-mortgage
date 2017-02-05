package com.zdsoft.finance.customer.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.BeforePersonalAssociation;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:BeforePersonalAssociationRepository.java
 * @Package:com.zdsoft.finance.customer.repository
 * @Description:贷前客户于客户关联关系
 * @author: xj
 * @date:2017年1月11日 下午3:38:08
 * @version:v1.0
 */
public interface BeforePersonalAssociationRepository extends CustomRepository<BeforePersonalAssociation, String> {
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
	public List<BeforePersonalAssociation> findByRelationshipAndCustomerId(String relationship,String customerId);
	/**
	 * 
	 * 通过主借人和关联客户查询
	 *
	 * @author xj
	 * @param beforeCustomerId 主借人id
	 * @param beforePersonalCusomerId 关联客户id
	 * @return
	 */
	public BeforePersonalAssociation findByCustomerIdAndBeforePersonalCusomerId(String beforeCustomerId,String beforePersonalCusomerId);
	
	/**
	 * 通过主借人ID 查找主借人相关人数据
	 * 
	 * @author liuhuan
	 * @param customerId
	 * @return
	 */
	public List<BeforePersonalAssociation> findByCustomerId(String customerId);
	
}
