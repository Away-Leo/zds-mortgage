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
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
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
		StringBuffer sb = new StringBuffer("SELECT ");
		
		sb.append("			   bf.componentsentityid AS caseApplyId,");
		sb.append("		       bf.businessentityid   AS businessKey,");
		sb.append("		       bf.processinstancekey AS processInstanceId,");
		sb.append("		       bf.applytitle         AS applyTitle,");
		sb.append("		       bf.formstatus         AS formStatus,");
		sb.append("		       cbcc.customername     AS mainCustomerName,");
		sb.append("		       bf.applydate          AS applyDate,");
		sb.append("		       bf.modeltype          AS modelType");
		sb.append("		  FROM busi_form bf");
		sb.append("		 INNER JOIN mkt_case_apply mca");
		sb.append("		    ON bf.componentsentityid = mca.id");
		sb.append("		  LEFT JOIN case_before_customer cbc");
		sb.append("		    ON cbc.caseapplyid = mca.id");
		sb.append("		  LEFT JOIN cust_before_customer cbcc");
		sb.append("		    ON cbcc.id = cbc.customerid");
		sb.append("		 AND cbc.jointype = '" + CaseApplyBeforeCustomer.MAIN_BORROW + "'"); //主借人
		sb.append(" WHERE 1=1 ");
		// 封装参数
		Map<String, Object> condition = new HashMap<String, Object>();
		if (ObjectHelper.isNotEmpty(params)) {
			// 标题
			if (ObjectHelper.isNotEmpty(params.get("keyWord"))) {
				sb.append(" AND bf.applytitle LIKE '%'||:keyWord||'%'");
				condition.put("keyWord", params.get("keyWord"));
			}
			// 状态
			if (ObjectHelper.isNotEmpty(params.get("formStatus"))) {
				sb.append(" AND bf.formstatus = :formStatus");
				condition.put("formStatus", params.get("formStatus"));
			}
			// 当前登录者
			if (ObjectHelper.isNotEmpty(params.get("currentEmpCd"))) {
				sb.append(" AND bf.launchEmpCode = :currentEmpCd");
				condition.put("currentEmpCd", params.get("currentEmpCd"));
			}
		}
		// 创建query
		//得到Session
		em = em.getEntityManagerFactory().createEntityManager();
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

