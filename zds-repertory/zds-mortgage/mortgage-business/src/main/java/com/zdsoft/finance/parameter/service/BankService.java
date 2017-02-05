package com.zdsoft.finance.parameter.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.parameter.entity.Bank;

public interface BankService extends BaseService<Bank>{
	public void deleteBank(String id);
	public List<Bank> findByCode(String code);
}
