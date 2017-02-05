package com.zdsoft.finance.customer.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.LatestCustomer;
import com.zdsoft.finance.customer.repository.LatestCustomerRepository;
import com.zdsoft.finance.customer.service.LatestCustomerService;

@Service
public class LatestCustomerServiceImpl extends BaseServiceImpl<LatestCustomer, CustomRepository<LatestCustomer, String>> 
	implements LatestCustomerService {

	@Autowired
	private LatestCustomerRepository latestCustomerRepository;
	
	public LatestCustomer findByCredentialNo(String clientNm, String credentiaType, String credentialNo){
		LatestCustomer latestCustomer = null;
		latestCustomer = latestCustomerRepository.findByCredentialNo(clientNm, credentiaType, credentialNo);
		return latestCustomer;
	}
	
	public LatestCustomer findByCredentiaTypeAndCredentialNo(String credentiaType, String credentialNo){
		LatestCustomer latestCustomer = null;
		latestCustomer = latestCustomerRepository.findByCredentiaTypeAndCredentialNo(credentiaType, credentialNo);
		return latestCustomer;
	}
	
	public LatestCustomer findByfindByClientId(String clientId){
		LatestCustomer latestCustomer = null;
		latestCustomer = latestCustomerRepository.findByClientId(clientId);
		return latestCustomer;
	}

	@Override
	public List<LatestCustomer> findClientNameByClientId(String id) {
		List<LatestCustomer> latestCustomerList = latestCustomerRepository.findClientNameByClientId(id);
		return latestCustomerList;
	}
}
