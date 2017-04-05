package com.zdsoft.finance.product.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.zdsoft.finance.product.entity.Category;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CategoryRepositoryImpl.java 
 * @ClassName: CategoryRepositoryImpl 
 * @Description: 产品类别操作仓库实现
 * @author gufeng 
 * @date 2017年2月6日 上午11:07:15 
 * @version V1.0
 */
public class CategoryRepositoryImpl {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Category> findAll(boolean isTree){
		// hql
		StringBuffer hql=new StringBuffer(" from Category c where 1=1 and c.isDeleted=:isDeleted ");
		if(!isTree){
			hql.append(" and c.id!='0'");
		}
		hql.append(" order by orderNumber,name");
		
		Query query=em.createQuery(hql.toString());
		
		query.setParameter("isDeleted", !BaseEntity.DELETED);
		return query.getResultList();
	}
	
	/**
	 * @Title: findByName 
	 * @Description: 通过名称查询
	 * @author gufeng 
	 * @param name 产品名称
	 * @return 是否存在
	 */
	@SuppressWarnings("unchecked")
	public boolean findByName(String name){
		// hql
		StringBuffer hql=new StringBuffer(" from Category c where 1=1 and c.isDeleted=:isDeleted and c.name=:name ");
		
		Query query=em.createQuery(hql.toString());
		
		query.setParameter("isDeleted", !BaseEntity.DELETED);
		query.setParameter("name", name);
		
		List<Category> list=query.getResultList();
		if(ObjectHelper.isNotEmpty(list) && list.size()>0){
			return false;
		}
		
		return true;
	}
}
