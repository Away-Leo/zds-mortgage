package com.zdsoft.finance.customer.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.BeforePersonalAssociation;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BeforePersonalAssociationRepository.java 
 * @ClassName: BeforePersonalAssociationRepository 
 * @Description: 贷前客户于客户关联关系
 * @author xj 
 * @date 2017年3月9日 上午9:40:19 
 * @version V1.0
 */
public interface BeforePersonalAssociationRepository extends CustomRepository<BeforePersonalAssociation, String> {
	
	/**
	 * 
	 * @Title: findByRelationshipAndCustomerId 
	 * @Description: 通过客户主借人id和关系类型查询客户关系
	 * @author xj 
	 * @param relationship 关系类型
	 * @param customerId 客户id
	 * @return
	 */
	public List<BeforePersonalAssociation> findByRelationshipAndCustomerId(String relationship,String customerId);
	
	/**
	 * 
	 * @Title: findByCustomerIdAndBeforePersonalCusomerId 
	 * @Description: 通过主借人和关联客户查询
	 * @author xj 
	 * @param beforeCustomerId 主借人id
	 * @param beforePersonalCusomerId 关联客户id
	 * @return
	 */
	public BeforePersonalAssociation findByCustomerIdAndBeforePersonalCusomerId(String beforeCustomerId,String beforePersonalCusomerId);
	
	/**
	 * 
	 * @Title: findByCustomerId 
	 * @Description: 通过主借人ID 查找主借人相关人数据
	 * @author liuhuan 
	 * @param customerId 客户id
	 * @return
	 */
	public List<BeforePersonalAssociation> findByCustomerId(String customerId);
	
}
