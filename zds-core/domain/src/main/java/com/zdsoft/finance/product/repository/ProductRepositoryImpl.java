package com.zdsoft.finance.product.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.Category;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageImpl;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 产品主表操作仓库实现
 * @author longwei
 * @date 2016/12/23
 * @version 1.0
 */
public class ProductRepositoryImpl {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Product> findByCategory (Category category) {
		//hql
		StringBuffer hql = new StringBuffer("from Product p where 1=1 and p.isDeleted=:isDeleted ");
		
		if(ObjectHelper.isNotEmpty(category.getId())){
			hql.append("and p.category.id=:categoryId ");
		}
		hql.append(" and p.isValid=:isValid");
		
		//创建查询
		Query query = em.createQuery(hql.toString());
		query.setParameter("isDeleted", !BaseEntity.DELETED);
		if(ObjectHelper.isNotEmpty(category.getId())){
			query.setParameter("categoryId", category.getId());
		}
		query.setParameter("isValid", true);
		
		//查询
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public Page<Product> find(Product product,String empType,Pageable pageable) throws BusinessException{
		//hql
		StringBuffer hql = new StringBuffer("from Product p where 1=1 ");
		
		if(ObjectHelper.isEmpty(empType) || !empType.equals("4")){
			hql.append(" and p.isDeleted=:isDeleted ");
		}
		
		if(ObjectHelper.isNotEmpty(product.getCategory()) && ObjectHelper.isNotEmpty(product.getCategory().getId())){
			hql.append("and p.category.id=:categoryId ");
		}
		if(ObjectHelper.isNotEmpty(product.getProductName())){
			hql.append("and p.productName like :productName ");
		}
		if(ObjectHelper.isNotEmpty(product.getIsValid())){
			hql.append("and p.isValid=:isValid ");
		}
		hql.append("order by createTime desc ");
		
		Query query=em.createQuery(hql.toString());
		Query queryC=em.createQuery("select count(*) "+hql.toString());
		
		if(ObjectHelper.isEmpty(empType) || !empType.equals("4")){
			query.setParameter("isDeleted", !BaseEntity.DELETED);
			queryC.setParameter("isDeleted", !BaseEntity.DELETED);
		}
		if(ObjectHelper.isNotEmpty(product.getCategory()) && ObjectHelper.isNotEmpty(product.getCategory().getId())){
			query.setParameter("categoryId", product.getCategory().getId());
			queryC.setParameter("categoryId", product.getCategory().getId());
		}
		if(ObjectHelper.isNotEmpty(product.getProductName())){
			query.setParameter("productName", "%"+product.getProductName()+"%");
			queryC.setParameter("productName", "%"+product.getProductName()+"%");
		}
		if(ObjectHelper.isNotEmpty(product.getIsValid())){
			query.setParameter("isValid", product.getIsValid());
			queryC.setParameter("isValid", product.getIsValid());
		}
		
		//查询
		query.setFirstResult(pageable.getRows()* (pageable.getPage()-1));
		query.setMaxResults(pageable.getRows());
		Page<Product> pager = new PageImpl<Product>(pageable);
		pager.setRecords(query.getResultList());
		pager.setTotalRows(Long.valueOf(queryC.getSingleResult().toString()));
		return pager;
	}
	
	public Product findByProductCode(String productCode) throws BusinessException{
		//hql
		StringBuffer hql = new StringBuffer("from Product p where 1=1 and p.isDeleted=:isDeleted and p.productCode=:productCode ");
		
		Query query = em.createQuery(hql.toString());
		
		query.setParameter("isDeleted", !BaseEntity.DELETED);
		query.setParameter("productCode", productCode);
		
		return (Product) query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> findByCategoryIdAndOrgCd(String categoryId,String orgCd){
		//hql
		StringBuffer hql = new StringBuffer("select p from Category ca,Product p,CompanyProduct cp,Company c where 1=1 and p.isDeleted=:isDeleted ");
		hql.append("and p.isValid=:isValid ");
		hql.append("and ca.id=p.category.id ");
		hql.append("and p.id=cp.product.id ");
		hql.append("and cp.company.id=c.id ");
		hql.append("and ca.id=:categoryId ");
		hql.append("and c.code=:orgCd ");
		
		Query query=em.createQuery(hql.toString());
		query.setParameter("isDeleted", !BaseEntity.DELETED);
		query.setParameter("isValid", true);
		query.setParameter("categoryId", categoryId);
		query.setParameter("orgCd", orgCd);
		
		return query.getResultList();
	}
}
