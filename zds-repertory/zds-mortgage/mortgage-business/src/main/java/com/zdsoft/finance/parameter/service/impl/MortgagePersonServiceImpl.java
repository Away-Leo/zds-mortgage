package com.zdsoft.finance.parameter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.parameter.entity.MortgagePerson;
import com.zdsoft.finance.parameter.repository.MortgagePersonRepository;
import com.zdsoft.finance.parameter.service.MortgagePersonService;
@Service("mortgagePersonService")
public class MortgagePersonServiceImpl  extends BaseServiceImpl<MortgagePerson, CustomRepository<MortgagePerson, String>>
implements MortgagePersonService{
	@Autowired
	private MortgagePersonRepository mortgagePersonRepository;
	@Override
	@Transactional
	public void deletePerson(String id) {
		// TODO Auto-generated method stub
		mortgagePersonRepository.deleteById(id);
	}

}
