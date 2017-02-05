package com.zdsoft.finance.prcostitem.repository;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.prcostitem.entity.BusinessMonitoring;

/**
 * 业务监控
 * @createTime 2017-01-11
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @version 1.0
 */
public interface BusinessMonitoringRepository extends CustomRepository<BusinessMonitoring, String> {

	/**
	 * 逾期特批按键条数
	 * @param overdueDays 逾期天数
	 * @return 条数
	 */
	public Long overdueSpecialCount(Integer overdueDays);
	
	/**
	 * 特批按键总条数
	 * @return 条数
	 */
	public Long specialCount();
}
