 package com.zdsoft.finance.problemmanage.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.problemmanage.entity.Question;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageImpl;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;


/**
 * 问题定义操作仓库事项
 * @author longwei
 * @date 2016/01/03
 * @version 1.0
 */
public class QuestionRepositoryImpl {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public Page<Question> findByPage(Question question,Pageable pageable) throws BusinessException {
		
		StringBuffer hql=new StringBuffer(" from Question q where 1=1 and q.isDeleted=:isDeleted ");
		if(ObjectHelper.isNotEmpty(question.getTitle())){
			hql.append("and q.title like :title ");
		}
		if(ObjectHelper.isNotEmpty(question.getTypeCd())){
			hql.append("and q.typeCd=:typeCd ");
		}
		
		Query query=em.createQuery(hql.toString()+" order by typeCd,createTime desc ");
		Query queryC=em.createQuery("select count(*) " + hql.toString());
		
		query.setParameter("isDeleted", !BaseEntity.DELETED);
		queryC.setParameter("isDeleted", !BaseEntity.DELETED);
		
		if(ObjectHelper.isNotEmpty(question.getTitle())){
			query.setParameter("title", "%"+question.getTitle()+"%");
			queryC.setParameter("title", "%"+question.getTitle()+"%");
		}
		if(ObjectHelper.isNotEmpty(question.getTypeCd())){
			query.setParameter("typeCd", question.getTypeCd());
			queryC.setParameter("typeCd", question.getTypeCd());
		}
		query.setFirstResult((pageable.getPage()-1)*pageable.getRows());
		query.setMaxResults(pageable.getRows());
		
		Page<Question> page=new PageImpl<Question>(pageable);
		page.setRecords(query.getResultList());
		page.setTotalRows(Long.valueOf(queryC.getSingleResult().toString()));
		return page;
	}
}
