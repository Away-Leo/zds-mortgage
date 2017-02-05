package com.zdsoft.finance.customer.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.BeforeCustomer;
import com.zdsoft.finance.customer.repository.BeforeCustomerRepository;
import com.zdsoft.finance.customer.service.BeforeCustomerService;

@Service
public class BeforeCustomerServiceImpl extends BaseServiceImpl<BeforeCustomer, CustomRepository<BeforeCustomer, String>> 
			implements BeforeCustomerService {
	
	@Autowired
	private BeforeCustomerRepository beforeCustomerRepository;
	
	
}
