package com.zdsoft.finance.product.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.zdsoft.finance.product.entity.ApprovalOpinion;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageImpl;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ApprovalOpinionRepositoryImpl.java 
 * @ClassName: ApprovalOpinionRepositoryImpl 
 * @Description: 产品审批配置操作
 * @author gufeng 
 * @date 2017年3月6日 下午7:24:37 
 * @version V1.0
 */
public class ApprovalOpinionRepositoryImpl {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public Page<ApprovalOpinion> findPage(ApprovalOpinion approvalOpinion,Pageable pageable) {
		
		StringBuffer hql=new StringBuffer(" from ApprovalOpinion appr where 1=1 and appr.isDeleted=:isDeleted ");
		if(ObjectHelper.isNotEmpty(approvalOpinion.getApprovalTypeName())){
			hql.append("and appr.approvalTypeName like :approvalTypeName ESCAPE '\\' ");
		}
		if(ObjectHelper.isNotEmpty(approvalOpinion.getMortgageOrder())){
			hql.append("and appr.mortgageOrder=:mortgageOrder ");
		}
		if(ObjectHelper.isNotEmpty(approvalOpinion.getIsEnable())){
			hql.append("and appr.isEnable=:isEnable ");
		}
		if(ObjectHelper.isNotEmpty(approvalOpinion.getProduct()) && ObjectHelper.isNotEmpty(approvalOpinion.getProduct().getId())){
			hql.append("and appr.product.id=:productId ");
		}
		hql.append(" order by createTime desc");
		
		Query query=em.createQuery(hql.toString());
		Query queryC=em.createQuery("select count(*) "+hql.toString());
		
		query.setParameter("isDeleted", !BaseEntity.DELETED);
		queryC.setParameter("isDeleted", !BaseEntity.DELETED);
		if(ObjectHelper.isNotEmpty(approvalOpinion.getApprovalTypeName())){
			query.setParameter("approvalTypeName", "%"+approvalOpinion.getApprovalTypeName().replace("%", "\\%")+"%");
			queryC.setParameter("approvalTypeName", "%"+approvalOpinion.getApprovalTypeName().replace("%", "\\%")+"%");
		}
		if(ObjectHelper.isNotEmpty(approvalOpinion.getMortgageOrder())){
			query.setParameter("mortgageOrder", approvalOpinion.getMortgageOrder());
			queryC.setParameter("mortgageOrder", approvalOpinion.getMortgageOrder());
		}
		if(ObjectHelper.isNotEmpty(approvalOpinion.getIsEnable())){
			query.setParameter("isEnable", approvalOpinion.getIsEnable());
			queryC.setParameter("isEnable", approvalOpinion.getIsEnable());
		}
		if(ObjectHelper.isNotEmpty(approvalOpinion.getProduct()) && ObjectHelper.isNotEmpty(approvalOpinion.getProduct().getId())){
			query.setParameter("productId", approvalOpinion.getProduct().getId());
			queryC.setParameter("productId", approvalOpinion.getProduct().getId());
		}
		
		query.setFirstResult((pageable.getPage()-1)*pageable.getRows());
		query.setMaxResults(pageable.getRows());
		Page<ApprovalOpinion> pager=new PageImpl<ApprovalOpinion>(pageable);
		pager.setRecords(query.getResultList());
		pager.setTotalRows(Long.valueOf(queryC.getSingleResult().toString()));
		
		return pager;
	}
}
