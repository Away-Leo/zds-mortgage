package com.zdsoft.finance.customer.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.CrmAssociation;
import com.zdsoft.finance.customer.repository.CrmAssociationRepository;
import com.zdsoft.finance.customer.service.CrmAssociationService;

@Service
public class CrmAssociationServicempl extends BaseServiceImpl<CrmAssociation, CustomRepository<CrmAssociation, String>> 
	implements CrmAssociationService {
	
	@Autowired
	private CrmAssociationRepository crmAssociationRepository;
}
