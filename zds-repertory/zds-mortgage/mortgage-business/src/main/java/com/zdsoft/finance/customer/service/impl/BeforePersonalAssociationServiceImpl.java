package com.zdsoft.finance.customer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BeforePersonalAssociation;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.repository.BeforePersonalAssociationRepository;
import com.zdsoft.finance.customer.service.BeforePersonalAssociationService;
import com.zdsoft.framework.core.common.util.ObjectHelper;
@Service("beforePersonalAssociationService")
public class BeforePersonalAssociationServiceImpl extends BaseServiceImpl<BeforePersonalAssociation, CustomRepository<BeforePersonalAssociation, String>>  implements BeforePersonalAssociationService {
	@Autowired
	private BeforePersonalAssociationRepository beforePersonalAssociationRepository;
	@Override
	public List<BeforePersonalAssociation> queryAssociation(String relationship,
			String beforeCustomerId) {
		return beforePersonalAssociationRepository.findByRelationshipAndCustomerId(relationship, beforeCustomerId);
	}

	@Override
	public BeforePersonalAssociation loadByAssociation(String beforeCustomerId,
			String beforePersonalCusomerId) {
		return beforePersonalAssociationRepository.findByCustomerIdAndBeforePersonalCusomerId(beforeCustomerId, beforePersonalCusomerId);
	}
	/*@Transactional(rollbackFor=Exception.class)
	@Override
	public void deleteByAssociation(String beforeCustomerId, String beforePersonalCusomerId) {
		BeforePersonalAssociation loadByAssociation = this.loadByAssociation(beforeCustomerId, beforePersonalCusomerId);
		if(ObjectHelper.isNotEmpty(loadByAssociation)){
			
		}
		
	}*/
	@Transactional(rollbackFor=Exception.class)
	@Override
	public void setCustomerAssociation(String customerId, BeforePersonalCustomer personalCustomer, String relationship)
			throws BusinessException {
			//首先想删除以前的关系
			BeforePersonalAssociation association1 = beforePersonalAssociationRepository.findByCustomerIdAndBeforePersonalCusomerId(customerId, personalCustomer.getId());
			if(ObjectHelper.isNotEmpty(association1)){
				beforePersonalAssociationRepository.delete(association1);
			}
			BeforePersonalAssociation association2 = beforePersonalAssociationRepository.findByCustomerIdAndBeforePersonalCusomerId(personalCustomer.getId(),customerId);
			if(ObjectHelper.isNotEmpty(association2)){
				beforePersonalAssociationRepository.delete(association2);
			}
			BeforePersonalAssociation beforePersonalAssociation = new BeforePersonalAssociation();
			beforePersonalAssociation.setCustomerId(customerId);
			beforePersonalAssociation.setBeforePersonalCusomer(personalCustomer);
			beforePersonalAssociation.setRelationship(relationship);
			this.saveEntity(beforePersonalAssociation);
		
	}

	@Override
	public List<BeforePersonalAssociation> queryCustomerId(String customerId) {
		return beforePersonalAssociationRepository.findByCustomerId(customerId);
	}

}