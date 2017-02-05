package com.zdsoft.finance.problemmanage.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.problemmanage.entity.QuestionSceneQuery;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageImpl;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 场景设置操作仓库实现
 * @author longwei
 * @date 2016/01/03
 * @version 1.0
 */
public class QuestionSceneRepositoryImpl {

	@PersistenceContext
	private EntityManager em;
	
	public Page<QuestionSceneQuery> findByPage(QuestionSceneQuery questionSceneQuery,Pageable pageable) throws BusinessException {
		StringBuffer hql=new StringBuffer("select new com.zdsoft.finance.problemmanage.entity.QuestionSceneQuery(qc.id,qc.sceneCd,qc.sceneNm,q.title,q.typeCd,q.typeNm,q.paramCd,q.paramNm,q.hitRule)"
				+ " from Question q,QuestionScene qc where 1=1 and q.isDeleted=:isDeleted and qc.isDeleted=:isDeleted and q.id=qc.question.id ");
		
		StringBuffer hqlCount=new StringBuffer("select count(*)"
				+ " from Question q,QuestionScene qc where 1=1 and q.isDeleted=:isDeleted and qc.isDeleted=:isDeleted and q.id=qc.question.id ");
		
		
		if(ObjectHelper.isNotEmpty(questionSceneQuery.getTitle())){
			hql.append("and q.title like :title ");
			hqlCount.append("and q.title like :title ");
		}
		if(ObjectHelper.isNotEmpty(questionSceneQuery.getTypeCd())){
			hql.append("and q.typeCd=:typeCd ");
			hqlCount.append("and q.typeCd=:typeCd ");
		}
		if(ObjectHelper.isNotEmpty(questionSceneQuery.getSceneCd())){
			hql.append("and qc.sceneCd=:sceneCd ");
			hqlCount.append("and qc.sceneCd=:sceneCd ");
		}
		
		Query query=em.createQuery(hql.toString()+" order by qc.sceneCd,qc.createTime desc ");
		Query queryC=em.createQuery(hqlCount.toString());
		
		query.setParameter("isDeleted", !BaseEntity.DELETED);
		queryC.setParameter("isDeleted", !BaseEntity.DELETED);
		if(ObjectHelper.isNotEmpty(questionSceneQuery.getTitle())){
			query.setParameter("title", "%"+questionSceneQuery.getTitle()+"%");
			queryC.setParameter("title", "%"+questionSceneQuery.getTitle()+"%");
		}
		if(ObjectHelper.isNotEmpty(questionSceneQuery.getTypeCd())){
			query.setParameter("typeCd", questionSceneQuery.getTypeCd());
			queryC.setParameter("typeCd", questionSceneQuery.getTypeCd());
		}
		if(ObjectHelper.isNotEmpty(questionSceneQuery.getSceneCd())){
			query.setParameter("sceneCd", questionSceneQuery.getSceneCd());
			queryC.setParameter("sceneCd", questionSceneQuery.getSceneCd());
		}
		query.setFirstResult((pageable.getPage()-1)*pageable.getRows());
		query.setMaxResults(pageable.getRows());
		
		Page<QuestionSceneQuery> page=new PageImpl<QuestionSceneQuery>(pageable);
		page.setRecords(query.getResultList());
		page.setTotalRows(Long.valueOf(queryC.getSingleResult().toString()));
		return page;
	}
}
