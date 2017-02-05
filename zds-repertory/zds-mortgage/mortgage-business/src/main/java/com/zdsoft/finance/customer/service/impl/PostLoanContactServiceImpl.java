package com.zdsoft.finance.customer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.PostLoanContact;
import com.zdsoft.finance.customer.repository.PostLoanContactRepository;
import com.zdsoft.finance.customer.service.PostLoanContactService;

@Service
public class PostLoanContactServiceImpl extends BaseServiceImpl<PostLoanContact, CustomRepository<PostLoanContact, String>> implements PostLoanContactService {
	
	@Autowired
	private PostLoanContactRepository postLoanContactRepository;
}
