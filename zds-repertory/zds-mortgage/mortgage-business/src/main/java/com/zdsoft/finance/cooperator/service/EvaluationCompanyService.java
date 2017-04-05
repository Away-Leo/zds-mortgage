package com.zdsoft.finance.cooperator.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.cooperator.entity.EvaluationCompany;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: EvaluationCompanyService.java 
 * @ClassName: EvaluationCompanyService 
 * @Description: 评估公司Service
 * @author liuwei
 * @date 2017年3月8日 上午9:53:27 
 * @version V1.0
 */
public interface EvaluationCompanyService extends BaseService<EvaluationCompany>{
	
	/**
	 * 查询所有的评估公司
	 * @return
	 */
	public List<EvaluationCompany> findAll();

}
