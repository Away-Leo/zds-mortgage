package com.zdsoft.finance.product.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.RepayPlanConfig;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageImpl;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 还款计划操作仓库接口实现
 * @author longwei
 * @date 2016/12/28
 * @version 1.0
 */
public class RepayPlanConfigRepositoryImpl {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public Page<RepayPlanConfig> findPage(RepayPlanConfig repayPlanConfig,Pageable pageable) throws BusinessException {
		
		StringBuffer hql=new StringBuffer(" from RepayPlanConfig rp where 1=1 and rp.isDeleted=:isDeleted ");
		if(ObjectHelper.isNotEmpty(repayPlanConfig.getFeeNm())){
			hql.append("and rp.feeNm like :feeNm ");
		}
		if(ObjectHelper.isNotEmpty(repayPlanConfig.getFeeCd())){
			hql.append("and rp.feeCd=:feeCd ");
		}
		if(ObjectHelper.isNotEmpty(repayPlanConfig.getReceiverCd())){
			hql.append("and rp.receiverCd = :receiverCd ");
		}
		if(ObjectHelper.isNotEmpty(repayPlanConfig.getReceiverNm())){
			hql.append("and rp.receiverNm like :receiverNm ");
		}
		if(ObjectHelper.isNotEmpty(repayPlanConfig.getIsEnable())){
			hql.append("and rp.isEnable=:isEnable ");
		}
		if(ObjectHelper.isNotEmpty(repayPlanConfig.getProduct()) && ObjectHelper.isNotEmpty(repayPlanConfig.getProduct().getId())){
			hql.append("and rp.product.id=:productId ");
		}
		hql.append(" order by createTime desc");
		
		Query query=em.createQuery(hql.toString());
		Query queryC=em.createQuery("select count(*) "+hql.toString());
		
		query.setParameter("isDeleted", !BaseEntity.DELETED);
		queryC.setParameter("isDeleted", !BaseEntity.DELETED);
		if(ObjectHelper.isNotEmpty(repayPlanConfig.getFeeNm())){
			query.setParameter("feeNm", "%"+repayPlanConfig.getFeeNm()+"%");
			queryC.setParameter("feeNm", "%"+repayPlanConfig.getFeeNm()+"%");
		}
		if(ObjectHelper.isNotEmpty(repayPlanConfig.getFeeCd())){
			query.setParameter("feeCd", repayPlanConfig.getFeeCd());
			queryC.setParameter("feeCd", repayPlanConfig.getFeeCd());
		}
		if(ObjectHelper.isNotEmpty(repayPlanConfig.getReceiverNm())){
			query.setParameter("receiverNm", "%"+repayPlanConfig.getReceiverNm()+"%");
			queryC.setParameter("receiverNm", "%"+repayPlanConfig.getReceiverNm()+"%");
		}
		if(ObjectHelper.isNotEmpty(repayPlanConfig.getReceiverCd())){
			query.setParameter("receiverCd", repayPlanConfig.getReceiverCd());
			queryC.setParameter("receiverCd", repayPlanConfig.getReceiverCd());
		}
		if(ObjectHelper.isNotEmpty(repayPlanConfig.getIsEnable())){
			query.setParameter("isEnable", repayPlanConfig.getIsEnable());
			queryC.setParameter("isEnable", repayPlanConfig.getIsEnable());
		}
		if(ObjectHelper.isNotEmpty(repayPlanConfig.getProduct()) && ObjectHelper.isNotEmpty(repayPlanConfig.getProduct().getId())){
			query.setParameter("productId", repayPlanConfig.getProduct().getId());
			queryC.setParameter("productId", repayPlanConfig.getProduct().getId());
		}
		
		query.setFirstResult((pageable.getPage()-1)*pageable.getRows());
		query.setMaxResults(pageable.getRows());
		Page<RepayPlanConfig> pager=new PageImpl<RepayPlanConfig>(pageable);
		pager.setRecords(query.getResultList());
		pager.setTotalRows(Long.valueOf(queryC.getSingleResult().toString()));
		
		return pager;
	}
}
