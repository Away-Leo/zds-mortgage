package com.zdsoft.finance.product.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.zdsoft.finance.product.entity.RepayPlanConfig;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageImpl;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RepayPlanConfigRepositoryImpl.java 
 * @ClassName: RepayPlanConfigRepositoryImpl 
 * @Description: 还款计划操作仓库接口实现
 * @author gufeng 
 * @date 2017年3月6日 下午4:59:03 
 * @version V1.0
 */
public class RepayPlanConfigRepositoryImpl {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public Page<RepayPlanConfig> findPage(RepayPlanConfig repayPlanConfig,Pageable pageable){
		
		StringBuffer hql=new StringBuffer(" from RepayPlanConfig rp where 1=1 and rp.isDeleted=:isDeleted ");
		if(ObjectHelper.isNotEmpty(repayPlanConfig.getFeeName())){
			hql.append("and rp.feeName like :feeName ");
		}
		if(ObjectHelper.isNotEmpty(repayPlanConfig.getFeeCode())){
			hql.append("and rp.feeCode=:feeCode ");
		}
		if(ObjectHelper.isNotEmpty(repayPlanConfig.getReceiverId())){
			hql.append("and rp.receiverId = :receiverId ");
		}
		if(ObjectHelper.isNotEmpty(repayPlanConfig.getReceiverName())){
			hql.append("and rp.receiverName like :receiverName ");
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
		if(ObjectHelper.isNotEmpty(repayPlanConfig.getFeeName())){
			query.setParameter("feeName", "%"+repayPlanConfig.getFeeName()+"%");
			queryC.setParameter("feeName", "%"+repayPlanConfig.getFeeName()+"%");
		}
		if(ObjectHelper.isNotEmpty(repayPlanConfig.getFeeCode())){
			query.setParameter("feeCode", repayPlanConfig.getFeeCode());
			queryC.setParameter("feeCode", repayPlanConfig.getFeeCode());
		}
		if(ObjectHelper.isNotEmpty(repayPlanConfig.getReceiverName())){
			query.setParameter("receiverName", "%"+repayPlanConfig.getReceiverName()+"%");
			queryC.setParameter("receiverName", "%"+repayPlanConfig.getReceiverName()+"%");
		}
		if(ObjectHelper.isNotEmpty(repayPlanConfig.getReceiverId())){
			query.setParameter("receiverId", repayPlanConfig.getReceiverId());
			queryC.setParameter("receiverId", repayPlanConfig.getReceiverId());
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
