package com.zdsoft.finance.customer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.PostLoanWorkUnit;
import com.zdsoft.finance.customer.repository.PostLoanWorkUnitRepository;
import com.zdsoft.finance.customer.service.PostLoanWorkUnitService;

@Service
public class PostLoanWorkUnitServiceImpl extends BaseServiceImpl<PostLoanWorkUnit, CustomRepository<PostLoanWorkUnit, String>> implements PostLoanWorkUnitService {
	
	@Autowired
	private PostLoanWorkUnitRepository postLoanWorkUnitRepository;
}
