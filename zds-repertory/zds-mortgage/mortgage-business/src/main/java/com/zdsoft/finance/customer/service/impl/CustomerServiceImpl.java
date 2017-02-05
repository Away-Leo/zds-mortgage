package com.zdsoft.finance.customer.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.Customer;
import com.zdsoft.finance.customer.repository.CustomerRepository;
import com.zdsoft.finance.customer.service.CustomerService;

@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer, CustomRepository<Customer, String>> 
		implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
}
