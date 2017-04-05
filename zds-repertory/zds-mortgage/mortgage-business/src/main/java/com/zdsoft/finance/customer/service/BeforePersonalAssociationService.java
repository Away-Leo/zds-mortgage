package com.zdsoft.finance.customer.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BeforePersonalAssociation;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BeforePersonalAssociationService.java 
 * @ClassName: BeforePersonalAssociationService 
 * @Description: 客户关系
 * @author xj 
 * @date 2017年3月9日 上午9:59:53 
 * @version V1.0
 */
public interface BeforePersonalAssociationService extends BaseService<BeforePersonalAssociation> {
	
	/**
	 * 
	 * @Title: queryAssociation 
	 * @Description: 通过客户主借人id和关系类型查询客户关系
	 * @author xj 
	 * @param relationship 关系类型
	 * @param beforeCustomerId 客户id
	 * @return
	 */
	public List<BeforePersonalAssociation> queryAssociation(String relationship,String beforeCustomerId);
	
	/**
	 * 
	 * @Title: loadByAssociation 
	 * @Description: 通过主借人和关联客户查询
	 * @author xj 
	 * @param beforeCustomerId 主借人id
	 * @param beforePersonalCusomerId 关联客户id
	 * @return
	 */
	public BeforePersonalAssociation loadByAssociation(String beforeCustomerId,String beforePersonalCusomerId);
	
	/**
	 * 
	 * @Title: setCustomerAssociation 
	 * @Description: 设置参与类型
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
	
	/**
	 * 
	 * @Title: batchSaveRelationship 
	 * @Description: 批量保存客户关系
	 * @author caixiekang 
	 * @param mainborrowId
	 * @param beforePersonalCustomers
	 * @throws Exception 
	 */
	public void batchSaveRelationship(String mainborrowId, List<BeforePersonalCustomer> beforePersonalCustomers) throws Exception;
	
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
	 * @Title: delete 
	 * @Description: 删除关系
	 * @author xj 
	 * @param associations 关系集合
	 */
	public void delete(List<BeforePersonalAssociation> associations);
}
