package com.zdsoft.finance.risk.huifa.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;

import com.zdsoft.finance.risk.entity.HuifaRequest;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HuifaRequestRepositoryImpl.java 
 * @ClassName: HuifaRequestRepositoryImpl 
 * @Description: HuifaRequestRepository仓储接口实现类
 * @author panshm 
 * @date 2017年2月18日 下午2:22:59 
 * @version V1.0
 */
public class HuifaRequestRepositoryImpl {

	@PersistenceContext
	private EntityManager em;

	/**
	 * 根据个人证件信息取得汇法请求数据
	 * <br>在汇法网信息页展示该出借人对应的汇法请求数据时调用
	 * 
	 * @Title: findByCodeAndStyle 
	 * @Description: 根据个人证件信息取得汇法请求数据
	 * @author panshm 
	 * @param code 出借人对应的证件号
	 * @param style 企业请求或个人请求类型
	 * @return 汇法网请求数据
	 */
	@SuppressWarnings("unchecked")
	public List<HuifaRequest> findByCodeAndStype(String code, int stype) {
		// hql
		StringBuffer hql = new StringBuffer(" from HuifaRequest hr where 1=1 ");
		if (StringUtils.isNotEmpty(code)) {	
			hql.append(" and hr.code=:code ");
		}
		hql.append(" and hr.stype=:stype ");
		hql.append(" order by hr.reqTimes desc ");
		Query query = em.createQuery(hql.toString());
		if (StringUtils.isNotEmpty(code)) {			
			query.setParameter("code", code);
		}
		query.setParameter("stype", stype);

		return query.getResultList();
	}

	/**
	 * 根据个人证件信息取得汇法请求数据
	 * <br>在汇法网信息页展示企业对应的汇法请求数据时调用
	 * 
	 * @Title: findByNameAndStype 
	 * @Description: 根据企业名称取得汇法请求数据
	 * @author panshm 
	 * @param name 企业名称
	 * @param stype 企业请求或个人请求类型
	 * @return 汇法网请求数据
	 */
	@SuppressWarnings("unchecked")
	public List<HuifaRequest> findByNameAndStype(String name, int stype) {
		// hql
		StringBuffer hql = new StringBuffer(" from HuifaRequest hr where 1=1 ");
		if (StringUtils.isNotEmpty(name)) {	
			hql.append(" and hr.name=:name ");
		}
		hql.append(" and hr.stype=:stype ");
		hql.append(" order by hr.reqTimes desc ");
		Query query = em.createQuery(hql.toString());
		if (StringUtils.isNotEmpty(name)) {			
			query.setParameter("name", name);
		}
		query.setParameter("stype", stype);

		return query.getResultList();
	}

}
