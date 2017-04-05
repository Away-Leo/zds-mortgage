package com.zdsoft.finance.cooperator.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.cooperator.entity.EvaluationCompany;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: EvaluationCompanyRepository.java 
 * @ClassName: EvaluationCompanyRepository 
 * @Description: 评估公司Repository
 * @author liuwei
 * @date 2017年3月8日 上午9:50:52 
 * @version V1.0
 */
public interface EvaluationCompanyRepository extends CustomRepository<EvaluationCompany, String>{

	/**
	 * 查询所有的评估公司
	 */
	public List<EvaluationCompany> findAll();
}
