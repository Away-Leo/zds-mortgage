package com.zdsoft.finance.capital.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.zdsoft.finance.capital.entity.LoanCapital;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: LoanCapitalRepositoryImpl.java
 * @ClassName: LoanCapitalRepositoryImpl
 * @Description: 专户贷方资金跟踪RepositoryImpl
 * @author liuwei
 * @date 2017年2月8日 上午10:32:22
 * @version V1.0
 */
public class LoanCapitalRepositoryImpl {

	@PersistenceContext
	private EntityManager em;

	/**
	 * 
	 * @Title: findByConditions
	 * @Description: 根据查询条件查询专户贷方跟踪列表
	 * @author liuwei
	 * @param conditions
	 *            查询条件
	 * @return 专户贷方资金跟踪列表
	 */
	@SuppressWarnings("unchecked")
	public List<LoanCapital> findByConditions(Map<String, Object> conditions) {

		// 组装hql和查询条件
		StringBuffer hql = new StringBuffer(
				"select t from LoanCapital t where t.isDeleted = false and t.creditEntrust is not null ");
		if (ObjectHelper.isNotEmpty(conditions)) {
			if (ObjectHelper.isNotEmpty(conditions.get("capitallist_id"))) {
				hql.append(" and t.creditEntrust.capitallist.id = :capitallist_id ");
			}
			if (ObjectHelper.isNotEmpty(conditions.get("creditEntrustName"))) {
				hql.append(" and t.creditEntrust.creditEntrustName like :creditEntrustName ");
			}
			if (ObjectHelper.isNotEmpty(conditions.get("creditEntrustId"))) {
				hql.append(" and t.creditEntrust.id = :creditEntrustId ");
			}
		}

		hql.append(" order by t.createTime desc ");
		Query query = em.createQuery(hql.toString());

		if (ObjectHelper.isNotEmpty(conditions)) {
			if (ObjectHelper.isNotEmpty(conditions.get("capitallist_id"))) {
				query.setParameter("capitallist_id", conditions.get("capitallist_id"));
			}
			if (ObjectHelper.isNotEmpty(conditions.get("creditEntrustName"))) {
				query.setParameter("creditEntrustName", "%" + conditions.get("creditEntrustName") + "%");
			}
			if (ObjectHelper.isNotEmpty(conditions.get("creditEntrustId"))) {
				query.setParameter("creditEntrustId", conditions.get("creditEntrustId"));
			}
		}
		List<LoanCapital> loanCapitals = query.getResultList();
		return loanCapitals;
	}

}
