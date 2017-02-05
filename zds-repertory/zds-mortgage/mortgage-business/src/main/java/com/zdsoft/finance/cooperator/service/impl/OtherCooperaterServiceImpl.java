package com.zdsoft.finance.cooperator.service.impl;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.cooperator.entity.OtherCooperater;
import com.zdsoft.finance.cooperator.service.OtherCooperaterService;

@Service("otherCooperaterService")
public class OtherCooperaterServiceImpl extends
		BaseServiceImpl<OtherCooperater, CustomRepository<OtherCooperater, String>> implements OtherCooperaterService {
}
