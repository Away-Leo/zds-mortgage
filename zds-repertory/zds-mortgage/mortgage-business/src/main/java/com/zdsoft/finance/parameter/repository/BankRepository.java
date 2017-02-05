package com.zdsoft.finance.parameter.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.parameter.entity.Bank;

public interface BankRepository extends CustomRepository<Bank, String>{
	public List<Bank> findByCode(String code);
}
