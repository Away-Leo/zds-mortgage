package com.zdsoft.finance.busiform.service.process;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.zdsoft.bpm.annotation.client.AutoJob;
import com.zdsoft.bpm.annotation.client.FinishJob;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.dto.DelegateExecution;
import com.zdsoft.framework.core.common.service.JavaDelegate;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: EmptyTaskAutoJob.java 
 * @ClassName: EmptyTaskAutoJob 
 * @Description: 空任务，多用于<系统任务>的[任务事项]配置,什么事情都不做
 * @author dengyy 
 * @date 2017年3月2日 上午10:36:20 
 * @version V1.0 
 */
@Service
@AutoJob(label = "空任务", resource = "com.zdsoft.finance.busiform.service.process.emptyTaskAutoJob")
public class EmptyTaskAutoJob implements JavaDelegate{

    @Log
    private Logger log;
    
    @Override
    @FinishJob(resource="com.zdsoft.finance.busiform.service.process.emptyTaskAutoJob",businessId="execution.businessKey",projectId="execution.projectId")
    public void execute(DelegateExecution execution) throws Exception {
        log.debug("执行空任务:{}",execution.getVariables());
    }

}
