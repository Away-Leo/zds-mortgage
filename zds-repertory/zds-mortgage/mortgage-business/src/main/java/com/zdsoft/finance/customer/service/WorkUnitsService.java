package com.zdsoft.finance.customer.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.customer.entity.WorkUnits;

/**
 * 工作单位接口
 * @author zhangchao
 * @date 2016-12-22
 */
public interface WorkUnitsService extends BaseService<WorkUnits>{

	/**
	 * 根据客户ID查询工作单位
	 * @param clientId 客户ID
	 * @return
	 */
	public List<WorkUnits> findByClientId(String clientId);
}
