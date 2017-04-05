package com.zdsoft.finance.product.repository;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.zdsoft.finance.product.entity.ProductRate;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ProductRateRepositoryImpl.java 
 * @ClassName: ProductRateRepositoryImpl 
 * @Description: 产品利率
 * @author gufeng 
 * @date 2017年3月6日 下午8:27:24 
 * @version V1.0
 */
public class ProductRateRepositoryImpl {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<ProductRate> findByProductId(String productId){
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
