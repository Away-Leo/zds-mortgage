package com.zdsoft.finance.customer.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.zdsoft.finance.customer.entity.LatestCustomer;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 客户Repository实现
 * @author zhangchao
 *	2016/12/23
 */
public class LatestCustomerRepositoryImpl {
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * 查询客户
	 * @param clientNm 客户姓名
	 * @param credentiaType 证件类型
	 * @param credentialNo 证件编号
	 * @return
	 */
	public LatestCustomer findByCredentialNo(String clientNm, String credentiaType, String credentialNo){
		StringBuffer hql = new StringBuffer("from LatestCustomer where isDeleted=0 ");
		//条件组装
		if(ObjectHelper.isNotEmpty(clientNm)){
			hql.append(" and clientNm like :clientNm ");
		}
		if(ObjectHelper.isNotEmpty(credentiaType)){
			hql.append(" and credentiaType = :credentiaType");
		}
		
		if(ObjectHelper.isNotEmpty(credentialNo)){
			hql.append(" and credentialNo = :credentialNo");
		}
		//参数组装
		Query query = em.createQuery(hql.toString());
		if(ObjectHelper.isNotEmpty(clientNm)){
			query.setParameter("clientNm","%"+clientNm+"%");
		}
		if(ObjectHelper.isNotEmpty(credentiaType)){
			query.setParameter("credentiaType",credentiaType);
		}
		if(ObjectHelper.isNotEmpty(credentialNo)){
			query.setParameter("credentialNo",credentialNo);
		}
		List<LatestCustomer> list = query.getResultList();
		if(ObjectHelper.isNotEmpty(list) && list.size() > 0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 通过ID查询相关客户以及配偶
	 * @param id 客户ID
	 * @return
	 */
	public List<LatestCustomer> findClientNameByClientId(String id){
		StringBuffer hql = new StringBuffer("from LatestCustomer where isDeleted=0 ");
		//条件组装
		if(ObjectHelper.isNotEmpty(id)){
			hql.append(" and clientId = :clientId ");
			hql.append(" or id = :id ");
		}
		
		//参数组装
		Query query = em.createQuery(hql.toString());
		if(ObjectHelper.isNotEmpty(id)){
			query.setParameter("clientId",id);
		}
		if(ObjectHelper.isNotEmpty(id)){
			query.setParameter("id",id);
		}
		List<LatestCustomer> list = query.getResultList();
		if(ObjectHelper.isNotEmpty(list) && list.size() > 0){
			return list;
		}else{
			return null;
		}
	}
}
