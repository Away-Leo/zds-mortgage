package com.zdsoft.finance.parameter.repository;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.parameter.entity.MortgagePerson;

public interface MortgagePersonRepository extends CustomRepository<MortgagePerson, String>{
	public void deleteById(String id);

}
