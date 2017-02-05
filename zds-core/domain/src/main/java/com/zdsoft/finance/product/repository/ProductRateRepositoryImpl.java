package com.zdsoft.finance.product.repository;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.ProductRate;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 产品利率操作仓库实现
 * @author longwei
 * @date 2016/12/23
 * @version 1.0
 */
public class ProductRateRepositoryImpl {

	@PersistenceContext
	private EntityManager em;
	
	
	@SuppressWarnings("unchecked")
	public List<ProductRate> findByProductId(String productId) throws BusinessException{
		//hql
		StringBuffer hql = new StringBuffer("from ProductRate p where 1=1 and p.isDeleted=:isDeleted and p.product.id=:productId ");
		
		Query query=em.createQuery(hql.toString());
		
		query.setParameter("isDeleted", !BaseEntity.DELETED);
		query.setParameter("productId", productId);
		//查询
		List<ProductRate> productRates = query.getResultList();
		if(ObjectHelper.isNotEmpty(productRates)){
			return productRates;
		}else{
			return Collections.emptyList();
		}
	}
}
