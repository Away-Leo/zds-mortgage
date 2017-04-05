package com.zdsoft.finance.product.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.zdsoft.finance.product.entity.CreditEntrustPlanConfig;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageImpl;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreditEntrustPlanConfigRepositoryImpl.java 
 * @ClassName: CreditEntrustPlanConfigRepositoryImpl 
 * @Description: 资金计划配置
 * @author gufeng 
 * @date 2017年3月6日 下午7:53:45 
 * @version V1.0
 */
public class CreditEntrustPlanConfigRepositoryImpl {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public Page<CreditEntrustPlanConfig> findByPage(CreditEntrustPlanConfig creditEntrustPlanConfig,
			Pageable pageable) {
		// hql
		StringBuffer hql = new StringBuffer("from CreditEntrustPlanConfig c where 1=1 and c.isDeleted=:isDeleted ");
		if (ObjectHelper.isNotEmpty(creditEntrustPlanConfig.getProduct())
				&& ObjectHelper.isNotEmpty(creditEntrustPlanConfig.getProduct().getId())) {
			hql.append("and c.product.id=:productId ");
		}

		Query query = em.createQuery(hql.toString());
		Query queryC = em.createQuery("select count(*) " + hql.toString());

		query.setParameter("isDeleted", !BaseEntity.DELETED);
		queryC.setParameter("isDeleted", !BaseEntity.DELETED);
		if (ObjectHelper.isNotEmpty(creditEntrustPlanConfig.getProduct())
				&& ObjectHelper.isNotEmpty(creditEntrustPlanConfig.getProduct().getId())) {
			query.setParameter("productId", creditEntrustPlanConfig.getProduct().getId());
			queryC.setParameter("productId", creditEntrustPlanConfig.getProduct().getId());
		}

		query.setFirstResult((pageable.getPage() - 1) * pageable.getRows());
		query.setMaxResults(pageable.getRows());

		Page<CreditEntrustPlanConfig> page = new PageImpl<>(pageable);
		page.setRecords(query.getResultList());
		page.setTotalRows(Long.valueOf(queryC.getSingleResult().toString()));

		return page;
	}

	@SuppressWarnings("unchecked")
	public List<CreditEntrustPlanConfig> findByProductIdAndCapitalistId(String productId, String capitalistId) {
		// hql
		StringBuffer hql = new StringBuffer(
				"from CreditEntrustPlanConfig c where c.isDeleted=:isDeleted and c.product.id=:productId and c.capitalistId=:capitalistId ");

		Query query = em.createQuery(hql.toString());
		query.setParameter("isDeleted", !BaseEntity.DELETED);
		query.setParameter("productId", productId);
		query.setParameter("capitalistId", capitalistId);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<CreditEntrustPlanConfig> findByProductIdAndCapitalistId(String productId, String capitalistId,
			String evaluateNum) {
		// hql
		StringBuffer hql = new StringBuffer(
				"from CreditEntrustPlanConfig c where c.isDeleted=:isDeleted and c.product.id=:productId and c.capitalistId=:capitalistId and c.minPercentage <= :evaluateNum and c.maxPercentage>:evaluateNum ");

		Query query = em.createQuery(hql.toString());
		query.setParameter("isDeleted", !BaseEntity.DELETED);
		query.setParameter("productId", productId);
		query.setParameter("capitalistId", capitalistId);
		query.setParameter("evaluateNum", Integer.parseInt(evaluateNum));

		return query.getResultList();
	}
}
