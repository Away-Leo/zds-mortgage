package com.zdsoft.finance.cooperator.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.cooperator.entity.EvaluationCompany;

public interface EvaluationCompanyService extends BaseService<EvaluationCompany>{
	
	/**
	 * 查询所有的评估公司
	 * @return
	 */
	public List<EvaluationCompany> findAll();

}
