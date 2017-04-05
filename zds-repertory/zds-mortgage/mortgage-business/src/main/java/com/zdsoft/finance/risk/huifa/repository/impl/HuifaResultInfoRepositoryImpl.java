package com.zdsoft.finance.risk.huifa.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;

import com.zdsoft.finance.risk.entity.HuifaResultInfo;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HuifaResultInfoRepositoryImpl.java 
 * @ClassName: HuifaResultInfoRepositoryImpl 
 * @Description: HuifaResultInfoRepository仓储接口实现类
 * @author panshm 
 * @date 2017年2月18日 下午2:22:16 
 * @version V1.0
 */
public class HuifaResultInfoRepositoryImpl {

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * 根据汇法网请求结果id和类型取得结果列表
	 * @Title: findByTypeAndResultId 
	 * @Description: 根据汇法网请求结果id和类型取得结果列表
	 * @author panshm 
	 * @param type 结果类型
	 * @param resultId 所属结果id
	 * @return List<HuifaResultInfo>
	 */
	@SuppressWarnings("unchecked")
	public List<HuifaResultInfo> findByTypeAndResultId(String type, String resultId) {
		// hql
		StringBuffer hql = new StringBuffer(" from HuifaResultInfo hri where 1=1 ");
		Query query = em.createQuery(hql.toString());
		if (StringUtils.isNotEmpty(type)) {			
			hql.append(" and hri.type=:type ");
			query.setParameter("type", type);
		}
		if (StringUtils.isNotEmpty(resultId)) {			
			hql.append(" and hri.resultId=:resultId ");
			query.setParameter("resultId", resultId);
		}
		hql.append(" order by hri.datetime desc ");
		
		return query.getResultList();
	}

}
