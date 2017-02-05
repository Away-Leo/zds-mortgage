package com.zdsoft.finance.parameter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.parameter.entity.InComeBody;
import com.zdsoft.finance.parameter.repository.InComeBodyRepository;
import com.zdsoft.finance.parameter.service.InComeBodyService;
@Service("inComeBodyService")
public class InComeBodyServiceImpl extends BaseServiceImpl<InComeBody, CustomRepository<InComeBody, String>>
implements InComeBodyService{
	@Autowired
	private InComeBodyRepository inComeBodyRepository;
	@Override
	public void deleteInComeBody(String id) {
		// TODO Auto-generated method stub
		inComeBodyRepository.delete(id);
	}
}