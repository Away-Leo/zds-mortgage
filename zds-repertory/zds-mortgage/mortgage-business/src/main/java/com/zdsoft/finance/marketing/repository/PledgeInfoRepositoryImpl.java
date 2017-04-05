package com.zdsoft.finance.marketing.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import com.zdsoft.finance.common.base.impl.CustomCommon;
import com.zdsoft.finance.marketing.entity.PledgeInfo;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: PledgeInfoRepositoryImpl.java 
 * @ClassName: PledgeInfoRepositoryImpl 
 * @Description: 房产
 * @author xj 
 * @date 2017年2月16日 下午8:56:35 
 * @version V1.0
 */
@SuppressWarnings("deprecation")
public class PledgeInfoRepositoryImpl {
	@PersistenceContext
	private EntityManager em;
	/**
	 * 
	 * @Title: findPledgeInfoByCaseApplyId 
	 * @Description: 通过案件id查询房产信息
	 * @author xj 
	 * @param caseApplyId
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<PledgeInfo> findPledgeInfoByCaseApplyId(String caseApplyId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select inf.* from mkt_collateral coll ");
		sb.append("  inner join mkt_pledge_info inf on inf.housepropertyid=coll.id ");
		sb.append("  where  coll.caseApplyId =:caseApplyId ");
		sb.append(" order by inf.createTime desc ");
		
		// 创建query
		//得到Session
		Session session = em.unwrap(Session.class);
		//创建查询对象
		SQLQuery q = session.createSQLQuery(sb.toString());
		q.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		q.setParameter("caseApplyId", caseApplyId);
		List<Map<String, Object>> sourceData = q.getResultList();
		return (List)CustomCommon.convert(PledgeInfo.class, sourceData);
	}
}
