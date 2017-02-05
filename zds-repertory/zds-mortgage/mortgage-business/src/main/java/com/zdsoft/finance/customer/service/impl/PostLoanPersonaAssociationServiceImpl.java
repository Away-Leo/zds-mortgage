package com.zdsoft.finance.customer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.PostLoanPersonaAssociation;
import com.zdsoft.finance.customer.repository.PostLoanPersonaAssociationRepository;
import com.zdsoft.finance.customer.service.PostLoanPersonaAssociationService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

@Service
public class PostLoanPersonaAssociationServiceImpl extends BaseServiceImpl<PostLoanPersonaAssociation, CustomRepository<PostLoanPersonaAssociation, String>> 
implements PostLoanPersonaAssociationService{

	@Autowired
	private PostLoanPersonaAssociationRepository postLoanPersonaAssociationRepository;
	
	@Override
	public String findByPostLoanCustomerIdAndRelationship(String postLoanCustomerId, String relationship){
		PostLoanPersonaAssociation postLoanPersonaAssociation = null;
		postLoanPersonaAssociation = postLoanPersonaAssociationRepository.findByPostLoanCustomerIdAndRelationship(postLoanCustomerId, relationship);
		if(ObjectHelper.isNotEmpty(postLoanPersonaAssociation)){
			return postLoanPersonaAssociation.getRelationtCustomerId();
		}else{
			return null;
		}
	}
}
