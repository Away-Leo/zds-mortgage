package com.zdsoft.finance.casemanage.datasurvey.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;

import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.base.impl.CustomCommon;
import com.zdsoft.finance.customer.entity.BeforePersonalAssociation;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageImpl;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:FeeInfomationRepositoryImpl.java
 * @Package:com.zdsoft.finance.casemanage.datasurvey.repository.impl
 * @Description:费用信息
 * @author: jingyh
 * @date:2017年1月16日 下午9:12:07
 * @version:v1.0
 */
@SuppressWarnings("deprecation")
public class FeeInfomationRepositoryImpl {

	@PersistenceContext
	private EntityManager em;
	
	@Log
	private Logger log;
	
	/**
	 * 
	 * 根据案件Id和参与类型查询收费对象集合
	 *
	 * @author jingyh
	 * @param caseApplyId
	 * @param joinType
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Page<Map<String, Object>> findReceiveCustomer(String caseApplyId, Pageable pageInfo) {
		StringBuffer sb = new StringBuffer(" SELECT DISTINCT");
		sb.append("	cbc.id AS customerId,");
		sb.append("	cbc.customerName AS customerName,");
		sb.append("	cabc.joinType AS joinType,");
		sb.append("	cbp.birthdayDate AS birthdayDate");
		sb.append(" FROM");
		sb.append("	cus_before_customer cbc");
		sb.append(" LEFT JOIN cus_before_personal cbp ON cbp.id = cbc.id");
		sb.append(" LEFT JOIN case_before_customer cabc ON cabc.customerId = cbc.id");
		sb.append(" AND cabc.caseApplyId = '" + caseApplyId + "'");
		sb.append(" WHERE");
		sb.append("	 cbc.id IN (");
		sb.append("		SELECT");
		sb.append("			cbpa.relationtCustomerId");
		sb.append("		FROM");
		sb.append("			cus_before_pers_association cbpa");
		sb.append("		WHERE");
		sb.append("			cbpa.relationship = '"+BeforePersonalAssociation.SPOUSE+"'"); // 配偶
		sb.append("		AND cbpa.customerId IN (");
		sb.append("			SELECT");
		sb.append("				cabc.customerId");
		sb.append("			FROM");
		sb.append("				case_before_customer cabc");
		sb.append("			WHERE");
		sb.append("				cabc.caseApplyId = '"+caseApplyId+"'");
		sb.append("		)");
		sb.append("	)");
		sb.append(" OR cbc.id IN (");
		sb.append("	SELECT");
		sb.append("		cabc.customerId");
		sb.append("	FROM");
		sb.append("		case_before_customer cabc");
		sb.append("	WHERE");
		sb.append("		cabc.caseApplyId = '"+caseApplyId+"'");
		sb.append(") ");
		// 创建query
		//得到Session
		Session session = em.unwrap(Session.class);
		//创建查询对象
		SQLQuery q = session.createSQLQuery(sb.toString());
		q.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		// 分页查询
		q.setFirstResult((pageInfo.getPage() - 1) * pageInfo.getRows());
		q.setMaxResults(pageInfo.getRows());
		log.debug("查询的hql为：{}", sb.toString());
		List<Map<String, Object>> resultList = q.getResultList();
		
		String sql = "select count(DISTINCT cbc.id) " + sb.substring(sb.indexOf("FROM"));
		log.debug("count(*) sql ---->" + sql);
		// 创建query
		Query qCount = em.createNativeQuery(sql);
		// 设置查询条件
		Page<Map<String, Object>> result = new PageImpl<Map<String, Object>>(pageInfo);
		result.setRecords(resultList);
		result.setTotalRows(Long.parseLong(qCount.getSingleResult().toString()));
		return result;
	}
	
	
	/**
	 * 
	 * 查询评估公司信息
	 *
	 * @author jingyh
	 * @param pageReq
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<Map<String, Object>> findEvaluationInfos(List<QueryObj> queryInfo,PageRequest pageReq) {
		StringBuffer sb = new StringBuffer(" SELECT zeov.* FROM zf_evaluation_org_view zeov where 1=1 ");
		Map<String, Object> conditionSql = new HashMap<String, Object>();
		if (ObjectHelper.isNotEmpty(queryInfo)) {
			conditionSql = CustomCommon.generateSearchConditionSql(queryInfo);
		}
		if (ObjectHelper.isNotEmpty(conditionSql.get(CustomCommon.QUERY_SQL))) {
			sb.append(conditionSql.get(CustomCommon.QUERY_SQL).toString());
		}
		// 创建query
		//得到Session
		Session session = em.unwrap(Session.class);
		//创建查询对象
		SQLQuery q = session.createSQLQuery(sb.toString());
		q.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		// 分页查询
		q.setFirstResult((pageReq.getPage() - 1) * pageReq.getRows());
		q.setMaxResults(pageReq.getRows());
		log.debug("查询的hql为：{}", sb.toString());
		String sql = "SELECT COUNT(*) " + sb.substring(sb.indexOf("FROM"));
		log.debug("count(*) sql ---->" + sql);
		// 创建query
		Query qCount = em.createNativeQuery(sql);
		
		if (ObjectHelper.isNotEmpty(conditionSql.get(CustomCommon.QUERY_PARAM))) {
			Object[] object = (Object[])conditionSql.get(CustomCommon.QUERY_PARAM);
			for (int i = 0; i < object.length; i++) {
				q.setParameter(i, object[i]);
				qCount.setParameter(i + 1, object[i]);
			}
		}
		
		List<Map<String, Object>> resultList = q.getResultList();
		// 设置查询条件
		Page<Map<String, Object>> result = new PageImpl<Map<String, Object>>(pageReq);
		result.setRecords(resultList);
		result.setTotalRows(Long.parseLong(qCount.getSingleResult().toString()));
		return result;
	}

}

