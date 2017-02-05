package com.zdsoft.finance.busiform.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.zdsoft.finance.busiform.entity.MyDraft;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageImpl;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 我的草稿操作仓库实现
 * @author longwei
 * @date 2016/01/13
 * @version 1.0
 */
public class BusiFormRepositoryImpl {

	@PersistenceContext
	private EntityManager em;
	
	public Page<MyDraft> findByPage(MyDraft myDraft,Pageable pageable) throws BusinessException {
		
		StringBuffer hql=new StringBuffer(" from BusiForm bf where 1=1 and bf.isDeleted=:isDeleted ");
		
		if(ObjectHelper.isNotEmpty(myDraft.getProcessName())){
			hql.append(" and bf.processKey like :processKey");
		}
		if(ObjectHelper.isNotEmpty(myDraft.getBorrowerPerson())){
			hql.append(" and bf.applyEmpNm like :applyEmpNm");
		}
		if(ObjectHelper.isNotEmpty(myDraft.getApplayFormCode())){
			hql.append(" and bf.applayFormCode = :applayFormCode");
		}
		if(ObjectHelper.isNotEmpty(myDraft.getStartTime())){
			hql.append(" and bf.applyTime >= :startTime");
		}
		if(ObjectHelper.isNotEmpty(myDraft.getEndTime())){
			hql.append(" and bf.applyTime <= :endTime");
		}
		
		Query query=em.createQuery("select new com.zdsoft.finance.busiform.entity.MyDraft(bf.id,bf.applyEmpNm,bf.applayFormCode,bf.applayFormName,bf.productCd,bf.createTime)"+hql.toString());
		Query queryC=em.createQuery("select count(*)" + hql.toString());
		
		query.setParameter("isDeleted", !BaseEntity.DELETED);
		queryC.setParameter("isDeleted", !BaseEntity.DELETED);
		if(ObjectHelper.isNotEmpty(myDraft.getProcessName())){
			query.setParameter("processKey", "%"+myDraft.getProcessName()+"%");
			queryC.setParameter("processKey", "%"+myDraft.getProcessName()+"%");
		}
		if(ObjectHelper.isNotEmpty(myDraft.getBorrowerPerson())){
			query.setParameter("applyEmpNm", "%"+myDraft.getBorrowerPerson()+"%");
			queryC.setParameter("applyEmpNm", "%"+myDraft.getBorrowerPerson()+"%");
		}
		if(ObjectHelper.isNotEmpty(myDraft.getApplayFormCode())){
			query.setParameter("applayFormCode", myDraft.getApplayFormCode());
			queryC.setParameter("applayFormCode", myDraft.getApplayFormCode());
		}
		if(ObjectHelper.isNotEmpty(myDraft.getStartTime())){
			query.setParameter("startTime", myDraft.getStartTime());
			queryC.setParameter("startTime", myDraft.getStartTime());
		}
		if(ObjectHelper.isNotEmpty(myDraft.getEndTime())){
			query.setParameter("endTime", myDraft.getEndTime());
			queryC.setParameter("endTime", myDraft.getEndTime());
		}
		
		Page<MyDraft> page=new PageImpl<MyDraft>(pageable);
		page.setRecords(query.getResultList());
		page.setTotalRows(Long.valueOf(queryC.getSingleResult().toString()));
		return page;
	}
}
