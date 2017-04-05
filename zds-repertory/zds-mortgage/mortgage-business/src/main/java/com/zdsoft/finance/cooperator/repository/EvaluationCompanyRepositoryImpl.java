package com.zdsoft.finance.cooperator.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.zdsoft.finance.cooperator.entity.EvaluationCompany;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: EvaluationCompanyRepositoryImpl.java
 * @ClassName: EvaluationCompanyRepositoryImpl
 * @Description: 评估公司RepositoryImpl
 * @author liuwei
 * @date 2017年3月8日 上午9:51:16
 * @version V1.0
 */
public class EvaluationCompanyRepositoryImpl {

	@PersistenceContext
	private EntityManager em;

	/**
	 * 查询所有的评估公司
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EvaluationCompany> findAll() {
		StringBuffer hql = new StringBuffer("from EvaluationCompany where isDeleted=0 ");
		Query query = em.createQuery(hql.toString());
		List<EvaluationCompany> list = query.getResultList();
		if (ObjectHelper.isNotEmpty(list) && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
}
