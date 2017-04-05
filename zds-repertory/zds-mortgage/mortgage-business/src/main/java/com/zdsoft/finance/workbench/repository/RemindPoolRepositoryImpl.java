package com.zdsoft.finance.workbench.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.workbench.entity.RemindPool;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageImpl;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RemindPoolRepositoryImpl.java 
 * @ClassName: RemindPoolRepositoryImpl 
 * @Description: 提醒池操作仓库实现
 * @author longwei 
 * @date 2017年2月22日 上午10:27:27 
 * @version V1.0
 */
public class RemindPoolRepositoryImpl {

	@PersistenceContext
	private EntityManager em;
	
	public Page<RemindPool> findByPage(RemindPool remindPool,Pageable pageable) throws BusinessException {
		StringBuffer hql=new StringBuffer(" from RemindPool re where re.isDeleted=:isDeleted ");
		
		if(ObjectHelper.isNotEmpty(remindPool.getProjectCode())){
			hql.append("and re.projectCode=:projectCode ");
		}
		if(ObjectHelper.isNotEmpty(remindPool.getCustomerName())){
			hql.append("and re.customerName like :customerName ");
		}
		if(ObjectHelper.isNotEmpty(remindPool.getCategory())){
			hql.append("and re.category=:category ");
		}
		if(ObjectHelper.isNotEmpty(remindPool.getStartDate())){
			hql.append("and re.startDate>=:startDate ");
		}
		if(ObjectHelper.isNotEmpty(remindPool.getEndDate())){
			hql.append("and re.startDate<=:endDate ");
		}
		if(ObjectHelper.isNotEmpty(remindPool.getStatus())){
			hql.append("and re.status=:status ");
		}
		hql.append("order by createTime desc ");
		
		Query query=em.createQuery(hql.toString());
		Query queryC=em.createQuery("select count(*) "+hql.toString());
		
		query.setParameter("isDeleted", !BaseEntity.DELETED);
		queryC.setParameter("isDeleted", !BaseEntity.DELETED);
		
		if(ObjectHelper.isNotEmpty(remindPool.getProjectCode())){
			query.setParameter("projectCode", remindPool.getProjectCode());
			queryC.setParameter("projectCode", remindPool.getProjectCode());
		}
		if(ObjectHelper.isNotEmpty(remindPool.getCustomerName())){
			query.setParameter("customerName", "%"+remindPool.getCustomerName()+"%");
			queryC.setParameter("customerName", "%"+remindPool.getCustomerName()+"%");
		}
		if(ObjectHelper.isNotEmpty(remindPool.getCategory())){
			query.setParameter("category", remindPool.getCategory());
			queryC.setParameter("category", remindPool.getCategory());
		}
		if(ObjectHelper.isNotEmpty(remindPool.getStartDate())){
			query.setParameter("startDate", remindPool.getStartDate());
			queryC.setParameter("startDate", remindPool.getStartDate());
		}
		if(ObjectHelper.isNotEmpty(remindPool.getEndDate())){
			query.setParameter("endDate", remindPool.getEndDate());
			queryC.setParameter("endDate", remindPool.getEndDate());
		}
		if(ObjectHelper.isNotEmpty(remindPool.getStatus())){
			query.setParameter("status", remindPool.getStatus());
			queryC.setParameter("status", remindPool.getStatus());
		}
		
		//查询
		query.setFirstResult(pageable.getRows()* (pageable.getPage()-1));
		query.setMaxResults(pageable.getRows());
		Page<RemindPool> pager = new PageImpl<RemindPool>(pageable);
		pager.setRecords(query.getResultList());
		pager.setTotalRows(Long.valueOf(queryC.getSingleResult().toString()));
		return pager;
	}
}
