package com.zdsoft.finance.app.usercenter.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.Oracle11gDialect;
import org.hibernate.dialect.OracleDialect;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import com.zdsoft.finance.app.usercenter.MyBusiInfoDto;
import com.zdsoft.finance.common.base.impl.CustomCommon;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:MyBusiRepository.java
 * @Package:com.zdsoft.finance.app.usercenter.repository
 * @Description:我的业务查询Repository
 * @author: jingyh
 * @date:2017年1月13日 下午9:05:21
 * @version:v1.0
 */
@SuppressWarnings("deprecation")
@Repository
public class MyBusiRepository {

	@Log
	private Logger log;
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * 
	 * 是否是mysql
	 *
	 * @author jingyh
	 * @return
	 */
	public boolean isMysql() {
		Dialect dialect = em.unwrap(SessionImplementor.class).getSessionFactory().getDialect();
		if (dialect instanceof MySQLDialect || dialect instanceof MySQL5InnoDBDialect) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * 是否是oracle
	 *
	 * @author jingyh
	 * @return
	 */
	public  boolean isOracle() {
		Dialect dialect = em.unwrap(SessionImplementor.class).getSessionFactory().getDialect();
		if (dialect instanceof OracleDialect || dialect instanceof Oracle11gDialect) {
			return true;
		}
		return false;
	}
	/**
	 * 
	 * 查询我的申请相关数据
	 *
	 * @author jingyh
	 * @param params
	 * @param pageInfo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<MyBusiInfoDto> findMyBusiPageInfo(Map<String,Object> params, Pageable pageInfo) {
		// 使用了mysql的查询 limit 关键字
		StringBuffer sb = new StringBuffer(" SELECT DISTINCT ");
		sb.append("	apply.id as caseApplyId ,");
		sb.append("	apply.caseApplyCode as caseCode,");
		sb.append("	apply.applyAmount as applyAmount,");
		sb.append("	prod.productName as productName,");
		sb.append("	house.synthesizePrice as estimate,");
		sb.append("	clientInfo.customerName as clientName,");
		sb.append("	clientInfo.contactway as contact,");
		sb.append("	apply.caseApplyStatus as caseApplyStatus,");
		sb.append("	apply.assessedPriceMortgage as assessedPriceMortgage,");
		sb.append("	apply.stage as stage ");
		sb.append(" FROM");
		sb.append("	mark_case_apply apply");
		sb.append(" INNER JOIN prct_product prod ON apply.productTypeId = prod.id");
		sb.append(" INNER JOIN mark_collateral coll on coll.caseApplyId=apply.id");
		sb.append(" INNER JOIN mark_house_property house ON house.id = coll.id");
		sb.append(" INNER JOIN case_before_customer rlaclient ON apply.id = rlaclient.caseApplyId");
		sb.append(" INNER JOIN (");
		sb.append("	SELECT");
		sb.append("		client.id,");
		sb.append("		client.customerName,");
		sb.append("		(");
		sb.append("			SELECT");
		sb.append("				contact.phoneNumber");
		sb.append("			FROM");
		sb.append("				cus_before_contact contact");
		sb.append("			WHERE");
		sb.append("				contact.customerId = client.id");
		if (isMysql()) {
			sb.append("			LIMIT 1");
		}
		if (isOracle()) {
			sb.append("			AND ROWNUM=1");
		}
		sb.append("		) AS contactway");
		sb.append("	FROM");
		sb.append("		cus_before_customer client ");
		sb.append(" ) clientInfo ON rlaclient.customerId = clientInfo.id ");
		sb.append(" WHERE 1=1 ");
		// 封装参数
		Map<String, Object> condition = new HashMap<String, Object>();
		if (ObjectHelper.isNotEmpty(params)) {
			// 封装sql
			// 关键字
			if (ObjectHelper.isNotEmpty(params.get("keyWord"))) {
				sb.append(" AND clientInfo.customerName LIKE '%'||:keyWord||'%'");
				sb.append(" AND apply.caseApplyCode LIKE '%'||:keyWord||'%'");
				// 产品名称
				sb.append(" AND prod.productName LIKE '%'||:keyWord||'%'");
				condition.put("keyWord", params.get("keyWord"));
			}
			// 类型
			if (ObjectHelper.isNotEmpty(params.get("stage"))) {
				sb.append(" AND apply.examinationStatus = :stage");
				condition.put("stage", params.get("stage"));
			}
			// 状态值
			if (ObjectHelper.isNotEmpty(params.get("status"))) {
				sb.append(" AND apply.caseApplyStatus = :status");
				condition.put("status", params.get("status"));
			}
			// 当前登录者
			if (ObjectHelper.isNotEmpty(params.get("currentEmpCd"))) {
				sb.append(" AND apply.developmentManagerCode = :currentEmpCd");
				condition.put("currentEmpCd", params.get("currentEmpCd"));
			}
		}
		// 创建query
		//得到Session
		Session session = em.unwrap(Session.class);
		//创建查询对象
		SQLQuery q = session.createSQLQuery(sb.toString());
		q.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		log.debug("查询的hql为：{}", sb.toString());
		// 设置查询条件
		if (ObjectHelper.isNotEmpty(condition)) {
			Set<String> keySet = condition.keySet();
			for (String key : keySet) {
				Object value = condition.get(key);
				if (value instanceof Collection) {
					q.setParameterList(key, (Collection)value);
				} else {
					q.setParameter(key, value);
				}
			}
		}
		// 分页查询
		q.setFirstResult((pageInfo.getPage() - 1) * pageInfo.getRows());
		q.setMaxResults(pageInfo.getRows());
		List<Map<String, Object>> sourceData = q.getResultList();
		return (List)CustomCommon.convert(MyBusiInfoDto.class, sourceData);
	}
}

