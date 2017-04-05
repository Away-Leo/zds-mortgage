package com.zdsoft.finance.prcostitem.service.scheduled;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.prcostitem.service.BusinessMonitoringService;
import com.zdsoft.framework.core.common.annotation.Log;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BusinessMonitoringScheduled.java 
 * @ClassName: BusinessMonitoringScheduled 
 * @Description: 业务检查定时任务
 * @author gufeng 
 * @date 2017年3月13日 下午5:06:26 
 * @version V1.0
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
			logger.debug("业务检查定时任务出错", e);
			e.printStackTrace();
		}
	}
}
