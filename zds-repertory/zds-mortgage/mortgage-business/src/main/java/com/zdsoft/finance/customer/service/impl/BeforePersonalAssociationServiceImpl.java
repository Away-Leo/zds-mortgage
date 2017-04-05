package com.zdsoft.finance.customer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BeforePersonalAssociation;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.repository.BeforePersonalAssociationRepository;
import com.zdsoft.finance.customer.service.BeforePersonalAssociationService;
import com.zdsoft.framework.core.common.util.ObjectHelper;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BeforePersonalAssociationServiceImpl.java 
 * @ClassName: BeforePersonalAssociationServiceImpl 
 * @Description: 客户关系
 * @author xj 
 * @date 2017年3月6日 下午6:14:42 
 * @version V1.0
 */
@Service("beforePersonalAssociationService")
public class BeforePersonalAssociationServiceImpl extends BaseServiceImpl<BeforePersonalAssociation,BeforePersonalAssociationRepository>  implements BeforePersonalAssociationService {
	@Override
	public List<BeforePersonalAssociation> queryAssociation(String relationship,
			String beforeCustomerId) {
		return this.customReposity.findByRelationshipAndCustomerId(relationship, beforeCustomerId);
	}

	@Override
	public BeforePersonalAssociation loadByAssociation(String beforeCustomerId,
			String beforePersonalCusomerId) {
		return this.customReposity.findByCustomerIdAndBeforePersonalCusomerId(beforeCustomerId, beforePersonalCusomerId);
	}


	@Transactional(rollbackFor=Exception.class)
	@Override
	public void setCustomerAssociation(String customerId, BeforePersonalCustomer personalCustomer, String relationship)
			throws BusinessException {
			//首先想删除以前的关系
			BeforePersonalAssociation association1 = this.customReposity.findByCustomerIdAndBeforePersonalCusomerId(customerId, personalCustomer.getId());
			if(ObjectHelper.isNotEmpty(association1)){
				this.customReposity.delete(association1);
			}
			BeforePersonalAssociation association2 = this.customReposity.findByCustomerIdAndBeforePersonalCusomerId(personalCustomer.getId(),customerId);
			if(ObjectHelper.isNotEmpty(association2)){
				this.customReposity.delete(association2);
			}
			BeforePersonalAssociation beforePersonalAssociation = new BeforePersonalAssociation();
			beforePersonalAssociation.setCustomerId(customerId);
			beforePersonalAssociation.setBeforePersonalCusomer(personalCustomer);
			beforePersonalAssociation.setRelationship(relationship);
			this.saveEntity(beforePersonalAssociation);
		
	}

	@Override
	public List<BeforePersonalAssociation> queryCustomerId(String customerId) {
		return this.customReposity.findByCustomerId(customerId);
	}
	
	@Transactional
	@Override
	public void batchSaveRelationship(String mainborrowId, List<BeforePersonalCustomer> beforePersonalCustomers) throws Exception{
		
		//拼装BeforePersonalAssociation
		List<BeforePersonalAssociation> associations = new ArrayList<BeforePersonalAssociation>();
		
		for (BeforePersonalCustomer beforePersonalCustomer : beforePersonalCustomers) {
			BeforePersonalAssociation association = new BeforePersonalAssociation();
			association.setCustomerId(mainborrowId);
			association.setBeforePersonalCusomer(beforePersonalCustomer);
			association.setRelationship(beforePersonalCustomer.getRelationship());
			associations.add(association);
		}
		
		//批量删除以前的关系
		List<BeforePersonalAssociation> old = this.queryCustomerId(mainborrowId);
		if(ObjectHelper.isNotEmpty(old)){
			this.customReposity.delete(old);;
		}
		//批量保存新设置的关系
		this.customReposity.batchSave(associations);
	}

	@Override
	public List<BeforePersonalAssociation> findByRelationshipAndCustomerId(String relationship, String customerId) {
		return this.customReposity.findByRelationshipAndCustomerId(relationship, customerId);
	}

	@Override
	public BeforePersonalAssociation findByCustomerIdAndBeforePersonalCusomerId(String beforeCustomerId,
			String beforePersonalCusomerId) {
		return this.customReposity.findByCustomerIdAndBeforePersonalCusomerId(beforeCustomerId, beforePersonalCusomerId);
	}
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public void delete(List<BeforePersonalAssociation> associations) {
		this.customReposity.delete(associations);
	}

}