package com.zdsoft.finance.cooperator.service.impl;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.cooperator.entity.CapitalistTrust;
import com.zdsoft.finance.cooperator.service.CapitalistTrustService;

@Service("capitalistTrustService")
public class CapitalistTrustServiceImpl extends BaseServiceImpl<CapitalistTrust, CustomRepository<CapitalistTrust, String>>
		implements CapitalistTrustService {

}
