package com.zdsoft.finance.cooperator.service.impl;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.cooperator.entity.EvaluateCompanyRule;
import com.zdsoft.finance.cooperator.service.EvaluateCompanyRuleService;

/**
 * 评估公司接口实现
 * @author zhangchao
 *
 */
@Service
public class EvaluateCompanyRuleServiceImpl extends BaseServiceImpl<EvaluateCompanyRule, CustomRepository<EvaluateCompanyRule, String>> implements EvaluateCompanyRuleService {

}
