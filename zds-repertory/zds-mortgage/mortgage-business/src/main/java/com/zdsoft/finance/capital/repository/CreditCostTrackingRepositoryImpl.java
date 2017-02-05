package com.zdsoft.finance.capital.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.zdsoft.finance.capital.entity.CreditCostTracking;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 应付费用跟踪RepositoryImpl
 * 
 * @createTime:2017年1月13日
 * @author liuwei
 * @version 1.0
 */
public class CreditCostTrackingRepositoryImpl {

	@PersistenceContext
	private EntityManager em;

	/**
	 * 通过查询条件查询应付费用跟踪
	 * 
	 * @param conditions
	 *            查询条件
	 * @return 应付费用跟踪列表
	 */
	@SuppressWarnings("unchecked")
	public List<CreditCostTracking> findByConditions(Map<String, Object> conditions) {
		StringBuffer hql = new StringBuffer(
				"select t from CreditCostTracking t where t.isDeleted = false and t.creditEntrust is not null ");
		
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
		
		List<CreditCostTracking> costTrackings = query.getResultList();
		return costTrackings;
	}
}
