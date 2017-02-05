package com.zdsoft.finance.prcostitem.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.prcostitem.entity.BusinessMonitoring;

/**
 * 业务监控
 * @createTime 2017-01-11
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @version 1.0
 */
public interface BusinessMonitoringService extends BaseService<BusinessMonitoring>{

	/**
	 * 监控
	 */
	public void monitoring() throws BusinessException;

}
