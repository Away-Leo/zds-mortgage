package com.zdsoft.finance.product.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.zdsoft.finance.product.entity.ProcessConfig;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageImpl;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ProcessConfigRepositoryImpl.java 
 * @ClassName: ProcessConfigRepositoryImpl 
 * @Description: 产品流程配置
 * @author gufeng 
 * @date 2017年3月6日 下午5:43:03 
 * @version V1.0
 */
public class ProcessConfigRepositoryImpl {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public Page<ProcessConfig> findPage(ProcessConfig processConfig,Pageable pageable) {
		
		StringBuffer hql=new StringBuffer(" from ProcessConfig p where 1=1 and p.isDeleted=:isDeleted ");
		if(ObjectHelper.isNotEmpty(processConfig.getProcessName())){
			hql.append("and p.processName like :processName ");
		}
		if(ObjectHelper.isNotEmpty(processConfig.getProcessCode())){
			hql.append("and p.processCode=:processCode ");
		}
		if(ObjectHelper.isNotEmpty(processConfig.getIsEnable())){
			hql.append("and p.isEnable=:isEnable ");
		}
		if(ObjectHelper.isNotEmpty(processConfig.getProduct()) && ObjectHelper.isNotEmpty(processConfig.getProduct().getId())){
			hql.append("and p.product.id=:productId ");
		}
		hql.append(" order by createTime desc");
		
		Query query=em.createQuery(hql.toString());
		Query queryC=em.createQuery("select count(*) "+hql.toString());
		
		query.setParameter("isDeleted", !BaseEntity.DELETED);
		queryC.setParameter("isDeleted", !BaseEntity.DELETED);
		if(ObjectHelper.isNotEmpty(processConfig.getProcessName())){
			query.setParameter("processName", "%"+processConfig.getProcessName()+"%");
			queryC.setParameter("processName", "%"+processConfig.getProcessName()+"%");
		}
		if(ObjectHelper.isNotEmpty(processConfig.getProcessCode())){
			query.setParameter("processCode", processConfig.getProcessCode());
			queryC.setParameter("processCode", processConfig.getProcessCode());
		}
		if(ObjectHelper.isNotEmpty(processConfig.getIsEnable())){
			query.setParameter("isEnable", processConfig.getIsEnable());
			queryC.setParameter("isEnable", processConfig.getIsEnable());
		}
		if(ObjectHelper.isNotEmpty(processConfig.getProduct()) && ObjectHelper.isNotEmpty(processConfig.getProduct().getId())){
			query.setParameter("productId", processConfig.getProduct().getId());
			queryC.setParameter("productId", processConfig.getProduct().getId());
		}
		
		query.setFirstResult((pageable.getPage()-1)*pageable.getRows());
		query.setMaxResults(pageable.getRows());
		Page<ProcessConfig> pager=new PageImpl<ProcessConfig>(pageable);
		pager.setRecords(query.getResultList());
		pager.setTotalRows(Long.valueOf(queryC.getSingleResult().toString()));
		
		return pager;
	}
}
