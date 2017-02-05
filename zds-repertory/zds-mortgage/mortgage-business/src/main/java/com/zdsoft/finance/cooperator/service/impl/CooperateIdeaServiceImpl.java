package com.zdsoft.finance.cooperator.service.impl;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.cooperator.entity.CooperateIdea;
import com.zdsoft.finance.cooperator.service.CooperateIdeaService;

@Service("cooperateIdeaService")
public class CooperateIdeaServiceImpl extends BaseServiceImpl<CooperateIdea, CustomRepository<CooperateIdea, String>>
		implements CooperateIdeaService {
}
