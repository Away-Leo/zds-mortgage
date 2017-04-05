package com.zdsoft.finance.cooperator.service.impl;

import org.springframework.stereotype.Service;


import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.cooperator.entity.EvaluationCompanyContact;
import com.zdsoft.finance.cooperator.service.EvaluationCompanyContactService;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: EvaluationCompanyContactServiceImpl.java 
 * @ClassName: EvaluationCompanyContactServiceImpl 
 * @Description: 评估公司联系方式ServiceImpl
 * @author liuwei
 * @date 2017年3月8日 上午9:54:53 
 * @version V1.0
 */
@Service("evaluationCompanyContactService")
public class EvaluationCompanyContactServiceImpl extends BaseServiceImpl<EvaluationCompanyContact, CustomRepository<EvaluationCompanyContact, String>> implements EvaluationCompanyContactService {
}
