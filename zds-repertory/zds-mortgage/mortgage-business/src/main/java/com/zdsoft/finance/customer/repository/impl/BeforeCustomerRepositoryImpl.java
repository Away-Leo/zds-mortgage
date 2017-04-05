package com.zdsoft.finance.customer.repository.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;

import com.zdsoft.finance.customer.entity.BeforePersonalAssociation;
import com.zdsoft.framework.core.common.annotation.Log;

/** 
* 版权所有：重庆正大华日软件有限公司
* @Title: BeforeCustomerRepositoryImpl.java 
* @Package com.zdsoft.finance.customer.repository.impl 
* @Description: 贷前客户Repository实现
* @author jingyh 
* @date 2017年2月27日 下午2:06:54 
* @version V1.0 
*/
@SuppressWarnings("deprecation")
public class BeforeCustomerRepositoryImpl {
	
	@PersistenceContext
	private EntityManager em;
	
	@Log
	private Logger log;
	
	/**
	 * 
	 * @Title: findRelationCustomerByCaseApplyId 
	 * @Description: 根据案件Id查询关联客户信息（含配偶）
	 * @author jingyh 
	 * @param caseApplyId
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map<String, Object>> findRelationCustomerByCaseApplyId(String caseApplyId) {
		StringBuffer sb = new StringBuffer(" SELECT DISTINCT cbc.id AS customerId,");
		sb.append("	        cbc.customerName AS customerName,");
		sb.append("	        cbc.credentialType AS credentialType,");
		sb.append("	        cbc.credentialNo AS credentialNo,");
		sb.append("	        cabc.joinType AS joinType,        ");       
		sb.append("	        CASE WHEN cbpr.id IS NOT NULL THEN 1 ELSE 0  END  AS isSpouse,");
		sb.append("	        cbc_p.actualuseperson AS actualUsePerson,        ");    // 是否实际用款人 
		sb.append("	        cbpr.customerid AS spouseCustomerId       ");     // 配偶Id
		sb.append("	FROM cust_before_customer cbc ");
		sb.append(" LEFT JOIN cust_before_personal cbc_p on cbc.id = cbc_p.id "); // 个人信息
		sb.append("	LEFT JOIN case_before_customer cabc");
		sb.append("	ON cabc.customerId = cbc.id");
		sb.append("	AND cabc.caseApplyId = '" + caseApplyId + "'");
		sb.append("	LEFT JOIN cust_before_pers_association cbpr");
		sb.append("	ON cbpr.relationtCustomerId = cbc.id");
		sb.append("	WHERE cbc.id IN");
		sb.append("	(SELECT cbpa.relationtCustomerId");
		sb.append("	  FROM cust_before_pers_association cbpa");
		sb.append("	 WHERE cbpa.relationship = '" + BeforePersonalAssociation.SPOUSE + "'");
		sb.append("	   AND cbpa.customerId IN");
		sb.append("	       (SELECT cabc.customerId");
		sb.append("	          FROM case_before_customer cabc");
		sb.append("	         WHERE cabc.caseApplyId = '" + caseApplyId + "'))");
		sb.append("	OR cbc.id IN");
		sb.append("	(SELECT cabc.customerId");
		sb.append("	  FROM case_before_customer cabc");
		sb.append("	 WHERE cabc.caseApplyId = '" + caseApplyId + "')");
		// 创建query
		//得到Session
		Session session = em.unwrap(Session.class);
		//创建查询对象
		SQLQuery q = session.createSQLQuery(sb.toString());
		q.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		log.debug("查询的sql为：{}", sb.toString());
		List<Map<String, Object>> resultList = q.getResultList();
		return resultList;
	}
}
