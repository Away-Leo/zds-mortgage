package com.zdsoft.finance.loan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.loan.entity.LoanApply;
import com.zdsoft.finance.loan.repository.LoanApplyRepository;
import com.zdsoft.finance.loan.service.LoanApplySerivce;

@Service
public class LoanApplyServiceImpl extends BaseServiceImpl<LoanApply, CustomRepository<LoanApply, String>>
		implements LoanApplySerivce {
	@Autowired
	LoanApplyRepository loanApplyRepository;

}
