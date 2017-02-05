package com.zdsoft.finance.customer.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.PostLloanCustome;
import com.zdsoft.finance.customer.repository.PostLloanCustomeRepository;
import com.zdsoft.finance.customer.service.PostLloanCustomeService;

@Service
public class PostLloanCustomeServiceImpl extends BaseServiceImpl<PostLloanCustome, CustomRepository<PostLloanCustome, String>> 
	implements PostLloanCustomeService {
	
	@Autowired
	private PostLloanCustomeRepository postLloanCustomeRepository;
}
