package com.zdsoft.finance.cooperator.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.cooperator.entity.EvaluationCompany;
import com.zdsoft.finance.cooperator.repository.EvaluationCompanyRepository;
import com.zdsoft.finance.cooperator.service.EvaluationCompanyService;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: EvaluationCompanyServiceImpl.java 
 * @ClassName: EvaluationCompanyServiceImpl 
 * @Description: 评估公司ServiceImpl
 * @author liuwei
 * @date 2017年3月8日 上午9:55:14 
 * @version V1.0
 */
@Service("evaluationCompanyService")
public class EvaluationCompanyServiceImpl extends BaseServiceImpl<EvaluationCompany, CustomRepository<EvaluationCompany, String>> implements EvaluationCompanyService {

	@Autowired
	private EvaluationCompanyRepository evaluationCompanyRepository;
	
	@Override
	public List<EvaluationCompany> findAll() {
		List<EvaluationCompany> evaluationCompanys = evaluationCompanyRepository.findAll();
		return evaluationCompanys;
	}
}
