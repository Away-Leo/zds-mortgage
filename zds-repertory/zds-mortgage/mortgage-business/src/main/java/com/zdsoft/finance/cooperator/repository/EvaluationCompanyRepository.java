package com.zdsoft.finance.cooperator.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.cooperator.entity.EvaluationCompany;

public interface EvaluationCompanyRepository extends CustomRepository<EvaluationCompany, String>{

	/**
	 * 查询所有的评估公司
	 */
	public List<EvaluationCompany> findAll();
}
