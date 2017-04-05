package com.zdsoft.finance.product.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: FeeOptionRepository.java
 * @ClassName: FeeOptionRepository
 * @Description: 机构产品费用项
 * @author gufeng
 * @date 2017年3月6日 上午10:54:36
 * @version V1.0
 */
public class FeeOptionRepositoryImpl {

	@PersistenceContext
	private EntityManager entityManager;
	
	public String findCostItemByCode(String code) {
		String sql = "select costItemName from buss_costitem where isDeleted='F' and costItemCode=:costItemCode and rownum=1";
		Query query = (Query) entityManager.createNativeQuery(sql);
		query.setParameter("costItemCode", code);
		@SuppressWarnings("unchecked")
		List<String> values = query.getResultList();
		if (ObjectHelper.isNotEmpty(values) && values.size() != 0) {
			return values.get(0);
		} else {
			return null;
		}
	}
}
