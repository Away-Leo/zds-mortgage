package com.zdsoft.finance.cooperator.service.impl;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.cooperator.entity.EvaluationCompanyContact;
import com.zdsoft.finance.cooperator.service.EvaluationCompanyContactService;
@Service("evaluationCompanyContactService")
public class EvaluationCompanyContactServiceImpl extends BaseServiceImpl<EvaluationCompanyContact, CustomRepository<EvaluationCompanyContact, String>> implements EvaluationCompanyContactService {
}
