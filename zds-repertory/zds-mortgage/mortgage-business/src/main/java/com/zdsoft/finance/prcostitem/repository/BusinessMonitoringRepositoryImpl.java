package com.zdsoft.finance.prcostitem.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 业务监控
 * @createTime 2017-01-12
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @version 1.0
 */
public class BusinessMonitoringRepositoryImpl{
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * 逾期特批按键条数
	 * @param overdueDays 逾期天数
	 * @return 条数
	 */
	public Long overdueSpecialCount(Integer overdueDays){
		StringBuffer sb = new StringBuffer("select count(pl.id) from zf_test_post_loan pl where pl.isDeleted = 0 and EXISTS(select ps.id from zf_test_project_special ps where ps.isDeleted = 0 and ps.projectCd = pl.projectCd)");
		if(ObjectHelper.isNotEmpty(overdueDays)){
			sb.append(" and pl.overdueDays <= ?");
		}
		Query query = (Query) em.createNativeQuery(sb.toString());
		if(ObjectHelper.isNotEmpty(overdueDays)){
			query.setParameter(1, overdueDays);
		}
		return Long.parseLong(query.getSingleResult().toString());
	}
	
	/**
	 * 特批按键总条数
	 * @return 条数
	 */
	public Long specialCount(){
		String sql = "select count(id) from zf_test_project_special where isDeleted = 0";
		Query queryCount = (Query) em.createNativeQuery(sql);
		return Long.parseLong(queryCount.getSingleResult().toString());
	}
	
}
