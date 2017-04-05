package com.zdsoft.finance.prcostitem.repository;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.prcostitem.entity.BusinessMonitoring;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BusinessMonitoringRepository.java 
 * @ClassName: BusinessMonitoringRepository 
 * @Description: 业务监控
 * @author gufeng 
 * @date 2017年3月13日 下午5:00:22 
 * @version V1.0
 */
public interface BusinessMonitoringRepository extends CustomRepository<BusinessMonitoring, String> {

	/**
	 * @Title: overdueSpecialCount 
	 * @Description: 逾期特批按键条数
	 * @author gufeng 
	 * @param overdueDays 逾期天数
	 * @return 条数
	 */
	public Long overdueSpecialCount(Integer overdueDays);
	
	/**
	 * @Title: specialCount 
	 * @Description: 特批按键总条数
	 * @author gufeng 
	 * @return 条数
	 */
	public Long specialCount();
}
