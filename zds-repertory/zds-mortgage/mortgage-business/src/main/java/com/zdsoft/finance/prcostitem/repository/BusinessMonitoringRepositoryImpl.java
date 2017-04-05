package com.zdsoft.finance.prcostitem.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.zdsoft.framework.core.common.util.ObjectHelper;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BusinessMonitoringRepositoryImpl.java 
 * @ClassName: BusinessMonitoringRepositoryImpl 
 * @Description: 业务监控
 * @author gufeng 
 * @date 2017年3月13日 下午5:01:08 
 * @version V1.0
 */
public class BusinessMonitoringRepositoryImpl{
	
	@PersistenceContext
	private EntityManager em;
	
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
	
	public Long specialCount(){
		String sql = "select count(id) from zf_test_project_special where isDeleted = 0";
		Query queryCount = (Query) em.createNativeQuery(sql);
		return Long.parseLong(queryCount.getSingleResult().toString());
	}
	
}
