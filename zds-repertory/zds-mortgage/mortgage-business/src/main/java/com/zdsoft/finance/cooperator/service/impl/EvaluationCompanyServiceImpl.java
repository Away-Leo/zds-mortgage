package com.zdsoft.finance.cooperator.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.cooperator.entity.EvaluationCompany;
import com.zdsoft.finance.cooperator.repository.EvaluationCompanyRepository;
import com.zdsoft.finance.cooperator.service.EvaluationCompanyService;

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
