package com.zdsoft.finance.parameter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.parameter.entity.Bank;
import com.zdsoft.finance.parameter.repository.BankRepository;
import com.zdsoft.finance.parameter.service.BankService;
@Service("BankService")
public class BankServiceImpl extends BaseServiceImpl<Bank, CustomRepository<Bank, String>>
implements BankService{
	@Autowired
	private BankRepository bankRepository;
	@Override
	public void deleteBank(String id) {
		// TODO Auto-generated method stub
		bankRepository.delete(id);
	}
	@Override
	public List<Bank> findByCode(String code) {
		return bankRepository.findByCode(code);
		
	}

}
