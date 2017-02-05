package com.zdsoft.finance.cooperator.service.impl;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.cooperator.entity.CooperatorBank;
import com.zdsoft.finance.cooperator.service.CooperatorBankService;

@Service("cooperatorBankService")
public class CooperatorBankServiceImpl extends BaseServiceImpl<CooperatorBank, CustomRepository<CooperatorBank, String>>
implements CooperatorBankService{

}