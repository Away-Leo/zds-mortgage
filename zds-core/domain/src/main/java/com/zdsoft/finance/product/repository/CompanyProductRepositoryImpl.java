package com.zdsoft.finance.product.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.CompanyProduct;

/**
 * @author longwei
 * @date 2016/12/23
 * @version 1.0
 */
public class CompanyProductRepositoryImpl {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<CompanyProduct> findByProductId(String productId) throws BusinessException{
		//hql
		StringBuffer hql = new StringBuffer("from CompanyProduct cp where 1=1 and cp.product.id=:productId ");
		
		Query query=em.createQuery(hql.toString());
		
		query.setParameter("productId", productId);
		
		//查询
		return query.getResultList();
	}
}
