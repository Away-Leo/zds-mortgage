package com.zdsoft.finance.product.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.ProcessConfig;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageImpl;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 产品流程配置操作仓库接口实现
 * @author longwei
 * @date 2016/12/28
 * @version 1.0
 */
public class ProcessConfigRepositoryImpl {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public Page<ProcessConfig> findPage(ProcessConfig processConfig,Pageable pageable) throws BusinessException {
		
		StringBuffer hql=new StringBuffer(" from ProcessConfig p where 1=1 and p.isDeleted=:isDeleted ");
		if(ObjectHelper.isNotEmpty(processConfig.getProcessName())){
			hql.append("and p.processName like :processName ");
		}
		if(ObjectHelper.isNotEmpty(processConfig.getProcessFormCd())){
			hql.append("and p.processFormCd=:processFormCd ");
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
		if(ObjectHelper.isNotEmpty(processConfig.getProcessFormCd())){
			query.setParameter("processFormCd", processConfig.getProcessFormCd());
			queryC.setParameter("processFormCd", processConfig.getProcessFormCd());
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
