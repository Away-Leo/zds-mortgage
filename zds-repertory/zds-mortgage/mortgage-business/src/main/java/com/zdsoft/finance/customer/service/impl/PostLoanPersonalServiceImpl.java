package com.zdsoft.finance.customer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.PostLoanPersonal;
import com.zdsoft.finance.customer.repository.PostLoanPersonalRepository;
import com.zdsoft.finance.customer.service.PostLoanPersonalService;

@Service
public class PostLoanPersonalServiceImpl extends BaseServiceImpl<PostLoanPersonal, CustomRepository<PostLoanPersonal, String>> 
implements PostLoanPersonalService {
	
	@Autowired
	private PostLoanPersonalRepository postLoanPersonalRepository;

	@Override
	public PostLoanPersonal findByCredentiaTypeAndCredentialNo(String credentiaType, String credentialNo) {
		PostLoanPersonal postLoanPersonal = null;
		postLoanPersonal = postLoanPersonalRepository.findByCredentiaTypeAndCredentialNo(credentiaType, credentialNo);
		return postLoanPersonal;
	}

	@Override
	public PostLoanPersonal findByCustomerId(String clientId) {
		PostLoanPersonal postLoanPersonal = null;
		postLoanPersonal = postLoanPersonalRepository.findByCustomerId(clientId);
		return postLoanPersonal;
	}

}
