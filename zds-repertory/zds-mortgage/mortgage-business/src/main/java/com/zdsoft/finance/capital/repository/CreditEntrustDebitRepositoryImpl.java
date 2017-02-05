package com.zdsoft.finance.capital.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.zdsoft.finance.capital.entity.CreditEntrustDebit;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 信托计划借方资金RepositoryImpl
 * 
 * @createTime:2017年1月13日
 * @author liuwei
 * @version 1.0
 */
public class CreditEntrustDebitRepositoryImpl {

	@PersistenceContext
	EntityManager em;

	/**
	 * 多条件查询借方资金列表
	 * 
	 * @param conditions
	 *            查询条件
	 * @return 借方资金列表
	 */
	@SuppressWarnings("unchecked")
	public List<CreditEntrustDebit> findByConditions(Map<String, Object> conditions) {
		StringBuffer hql = new StringBuffer(
				"select t from CreditEntrustDebit t where t.isDeleted = false and t.creditEntrust is not null ");
		if (ObjectHelper.isNotEmpty(conditions)) {
			if (ObjectHelper.isNotEmpty(conditions.get("capitallist_id"))) {
				hql.append(" and t.creditEntrust.capitallist.id = :capitallist_id ");
			}
			if (ObjectHelper.isNotEmpty(conditions.get("creditEntrustName"))) {
				hql.append(" and t.creditEntrust.creditEntrustName like :creditEntrustName ");
			}
		}
		Query query = em.createQuery(hql.toString());

		if (ObjectHelper.isNotEmpty(conditions)) {
			if (ObjectHelper.isNotEmpty(conditions.get("capitallist_id"))) {
				query.setParameter("capitallist_id", conditions.get("capitallist_id"));
			}
			if (ObjectHelper.isNotEmpty(conditions.get("creditEntrustName"))) {
				query.setParameter("creditEntrustName", "%" + conditions.get("creditEntrustName") + "%");
			}
		}
		List<CreditEntrustDebit> debits = query.getResultList();
		return debits;
	}

}
