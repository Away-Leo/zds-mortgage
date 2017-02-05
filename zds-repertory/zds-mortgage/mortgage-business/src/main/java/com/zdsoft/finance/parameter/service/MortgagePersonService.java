package com.zdsoft.finance.parameter.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.parameter.entity.MortgagePerson;

public interface MortgagePersonService extends BaseService<MortgagePerson>{
	public void deletePerson(String id);

}
