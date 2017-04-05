package com.zdsoft.finance.prcostitem.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.prcostitem.entity.BusinessMonitoring;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BusinessMonitoringService.java 
 * @ClassName: BusinessMonitoringService 
 * @Description: 业务监控
 * @author gufeng 
 * @date 2017年3月13日 下午5:06:36 
 * @version V1.0
 */
public interface BusinessMonitoringService extends BaseService<BusinessMonitoring>{

	/**
	 * @Title: monitoring 
	 * @Description: 监控
	 * @author gufeng 
	 * @throws BusinessException 异常
	 */
	public void monitoring() throws BusinessException;

}
