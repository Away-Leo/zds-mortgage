package com.zdsoft.finance.prcostitem.service.scheduled;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.prcostitem.service.BusinessMonitoringService;
import com.zdsoft.framework.core.common.annotation.Log;

/**
 * 业务检查定时任务
 * @createTime 2017-01-12
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @version 1.0
 */
@Component
public class BusinessMonitoringScheduled {
	
	@Autowired
	private BusinessMonitoringService businessMonitoringService;
	
	@Log
	private Logger logger;
	
	/**
	 * 每天凌晨1点触发
	 */
	@Scheduled(cron = "0 0 1 * * ?")
	public void monitoring(){
		try{
			businessMonitoringService.monitoring();
		}catch(BusinessException e){
			logger.debug("业务检查定时任务出错", e.getMessage());
			e.printStackTrace();
		}
	}
}
