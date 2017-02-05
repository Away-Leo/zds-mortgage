package com.zdsoft.finance.product.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.ApprovalOpinion;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageImpl;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 产品审批配置操作仓库接口实现
 * @author longwei
 * @date 2016/12/28
 * @version 1.0
 */
public class ApprovalOpinionRepositoryImpl {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public Page<ApprovalOpinion> findPage(ApprovalOpinion approvalOpinion,Pageable pageable) throws BusinessException {
		
		StringBuffer hql=new StringBuffer(" from ApprovalOpinion appr where 1=1 and appr.isDeleted=:isDeleted ");
		if(ObjectHelper.isNotEmpty(approvalOpinion.getApprovalTypeNm())){
			hql.append("and appr.approvalTypeNm like :approvalTypeNm ");
		}
		if(ObjectHelper.isNotEmpty(approvalOpinion.getMortgageOrderCd())){
			hql.append("and appr.mortgageOrderCd=:mortgageOrderCd ");
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
		if(ObjectHelper.isNotEmpty(approvalOpinion.getApprovalTypeNm())){
			query.setParameter("approvalTypeNm", "%"+approvalOpinion.getApprovalTypeNm()+"%");
			queryC.setParameter("approvalTypeNm", "%"+approvalOpinion.getApprovalTypeNm()+"%");
		}
		if(ObjectHelper.isNotEmpty(approvalOpinion.getMortgageOrderCd())){
			query.setParameter("mortgageOrderCd", approvalOpinion.getMortgageOrderCd());
			queryC.setParameter("mortgageOrderCd", approvalOpinion.getMortgageOrderCd());
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
