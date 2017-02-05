package com.zdsoft.finance.capital.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.zdsoft.finance.capital.entity.CreditEntrustRedemPrinci;
import com.zdsoft.framework.core.common.util.ObjectHelper;

public class CreditEntrustRedemPrinciRepositoryImpl {

	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<CreditEntrustRedemPrinci> findByConditions(Map<String, Object> conditions) {
		StringBuffer hql = new StringBuffer(
				"select t from CreditEntrustRedemPrinci t where t.isDeleted = false and t.creditEntrust is not null ");

		if (ObjectHelper.isNotEmpty(conditions)) {
			if (ObjectHelper.isNotEmpty(conditions.get("creditEntrustId"))) {
				hql.append(" and t.creditEntrust.id = :creditEntrustId ");
			}
		}
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
