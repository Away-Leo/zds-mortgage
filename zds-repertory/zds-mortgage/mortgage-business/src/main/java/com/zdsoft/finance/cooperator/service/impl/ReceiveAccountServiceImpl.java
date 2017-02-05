package com.zdsoft.finance.cooperator.service.impl;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.cooperator.entity.ReceiveAccount;
import com.zdsoft.finance.cooperator.service.ReceiveAccountService;

@Service("receiveAccountService")
public class ReceiveAccountServiceImpl extends BaseServiceImpl<ReceiveAccount, CustomRepository<ReceiveAccount, String>>
implements ReceiveAccountService{
	
}