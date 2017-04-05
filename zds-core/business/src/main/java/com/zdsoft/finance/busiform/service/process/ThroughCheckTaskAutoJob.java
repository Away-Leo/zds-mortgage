package com.zdsoft.finance.busiform.service.process;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.bpm.annotation.client.AutoJob;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.dto.DelegateExecution;
import com.zdsoft.framework.core.common.service.CheckTask;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ThroughCheckTaskAutoJob.java 
 * @ClassName: ThroughCheckTaskAutoJob 
 * @Description: 直接通过检测事项
 * @author dengyy 
 * @date 2017年3月2日 上午11:10:01 
 * @version V1.0
 */
@Service
@AutoJob(label = "直接通过的检测任务",  resource = "com.zdsoft.finance.busiform.service.process.throughCheckTaskAutoJob",checkJob=true)
public class ThroughCheckTaskAutoJob implements CheckTask{
	
	@Log
	private Logger log;
	
	@Override
	@Transactional
	public boolean executeCheck(DelegateExecution execution) throws Exception {
		log.debug("直接通过的检测任务:{}",execution.getVariables());
		return true;
	}
}
