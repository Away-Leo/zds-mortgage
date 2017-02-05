package com.zdsoft.finance.customer.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.WorkUnits;
/**
 * 工作单位
 * @author zhangchao
 * 2016/12/21
 */
public interface WorkUnitsRepository extends CustomRepository<WorkUnits, String> {

	/**
	 * 根据客户ID查询工作单位
	 * @param clientId 客户ID
	 * @return
	 */
	public List<WorkUnits> findByClientId(String clientId);
}
