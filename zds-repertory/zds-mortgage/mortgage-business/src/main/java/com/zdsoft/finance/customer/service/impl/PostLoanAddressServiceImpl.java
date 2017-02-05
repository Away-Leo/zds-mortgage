package com.zdsoft.finance.customer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.PostLoanAddress;
import com.zdsoft.finance.customer.repository.PostLoanAddressRepository;
import com.zdsoft.finance.customer.service.PostLoanAddressService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

@Service
public class PostLoanAddressServiceImpl extends BaseServiceImpl<PostLoanAddress, CustomRepository<PostLoanAddress, String>> 
implements PostLoanAddressService{

	@Autowired
	private PostLoanAddressRepository postLoanAddressRepository;

	@Override
	public PostLoanAddress findByAddressTypeAndCustomerId(String addressType, String customerId) {
		PostLoanAddress postLoanAddress = null;
		postLoanAddress = postLoanAddressRepository.findByAddressTypeAndCustomerId(addressType, customerId);
		if(ObjectHelper.isNotEmpty(postLoanAddress)){
			return postLoanAddress;
		}
		return null;
	}

	@Override
	public PostLoanAddress findByCustomerNameAndCustomerIdAndAddressType(String customerName, String customerId, String addressType) {
		PostLoanAddress postLoanAddress = null;
		postLoanAddress = postLoanAddressRepository.findByCustomerNameAndCustomerIdAndAddressType(customerName, customerId, addressType);
		if(ObjectHelper.isNotEmpty(postLoanAddress)){
			return postLoanAddress;
		}
		return null;
	}
}
