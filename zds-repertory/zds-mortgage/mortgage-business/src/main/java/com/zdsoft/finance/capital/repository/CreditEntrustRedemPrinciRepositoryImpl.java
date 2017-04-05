package com.zdsoft.finance.capital.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.zdsoft.finance.capital.entity.CreditEntrustRedemPrinci;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CreditEntrustRedemPrinciRepositoryImpl.java
 * @ClassName: CreditEntrustRedemPrinciRepositoryImpl
 * @Description: 信托计划本金赎回RepositoryImpl
 * @author liuwei
 * @date 2017年2月8日 上午10:28:33
 * @version V1.0
 */
public class CreditEntrustRedemPrinciRepositoryImpl {

	@PersistenceContext
	EntityManager em;

	/**
	 * 
	 * @Title: findByConditions
	 * @Description: 多条件查询信托计划本金赎回集合
	 * @author liuwei
	 * @param conditions
	 *            查询条件
	 * @return 本金赎回集合
	 */
	@SuppressWarnings("unchecked")
	public List<CreditEntrustRedemPrinci> findByConditions(Map<String, Object> conditions) {

		// 组装hql以及查询条件
		StringBuffer hql = new StringBuffer(
				"select t from CreditEntrustRedemPrinci t where t.isDeleted = false and t.creditEntrust is not null ");

		if (ObjectHelper.isNotEmpty(conditions)) {
			if (ObjectHelper.isNotEmpty(conditions.get("creditEntrustId"))) {
				hql.append(" and t.creditEntrust.id = :creditEntrustId ");
			}
		}

		hql.append(" order by t.createTime desc ");
		Query query = em.createQuery(hql.toString());

		if (ObjectHelper.isNotEmpty(conditions)) {
			if (ObjectHelper.isNotEmpty(conditions.get("creditEntrustId"))) {
				query.setParameter("creditEntrustId", conditions.get("creditEntrustId"));
			}
		}

		List<CreditEntrustRedemPrinci> redemPrincis = query.getResultList();
		return redemPrincis;
	}

}
