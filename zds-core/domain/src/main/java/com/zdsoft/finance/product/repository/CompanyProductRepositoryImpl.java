package com.zdsoft.finance.product.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.zdsoft.finance.product.entity.CompanyProduct;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CompanyProductRepositoryImpl.java 
 * @ClassName: CompanyProductRepositoryImpl 
 * @Description: 公司产品中间表
 * @author gufeng 
 * @date 2017年3月14日 下午4:34:21 
 * @version V1.0
 */
public class CompanyProductRepositoryImpl {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<CompanyProduct> findByProductId(String productId) {
		//hql
		StringBuffer hql = new StringBuffer("from CompanyProduct cp where 1=1 and cp.product.id=:productId ");
		
		Query query=em.createQuery(hql.toString());
		
		query.setParameter("productId", productId);
		
		//查询
		return query.getResultList();
	}
}
