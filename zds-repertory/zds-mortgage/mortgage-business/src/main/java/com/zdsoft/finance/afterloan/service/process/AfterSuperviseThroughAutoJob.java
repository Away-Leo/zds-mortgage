package com.zdsoft.finance.afterloan.service.process;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.bpm.annotation.client.AutoJob;
import com.zdsoft.finance.afterloan.service.AfterSuperviseService;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.busiform.service.BusiFormService;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.dto.DelegateExecution;
import com.zdsoft.framework.core.common.service.JavaDelegate;
import com.zdsoft.framework.core.common.util.ObjectHelper;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterSuperviseThroughAutoJob.java 
 * @ClassName: AfterSuperviseThroughAutoJob 
 * @Description: 督办审批通过后置事项
 * @author xj 
 * @date 2017年3月4日 下午3:58:53 
 * @version V1.0
 */
@Service
@AutoJob(label = "督办审批通过", resource = "com.zdsoft.finance.afterSupervise.service.process.afterSuperviseThroughAutoJob.autoJob")
public class AfterSuperviseThroughAutoJob implements JavaDelegate{
	@Log
	private Logger logger;
	@Autowired
	private AfterSuperviseService afterSuperviseService;
	@Autowired
	private BusiFormService busiFormService;
	
    @Override
    @Transactional
    public void execute(DelegateExecution delegateexecution) throws Exception {
        String afterSuperviseId = (String) delegateexecution.getVariable("afterSuperviseId");
        String caseApplyId = (String) delegateexecution.getVariable("projectId");
        logger.info("进入督办审批通过后置事项");
        logger.info("afterSuperviseId:"+afterSuperviseId);
        logger.info("caseApplyId:"+caseApplyId);
        if(ObjectHelper.isNotEmpty(afterSuperviseId)){
        	BusiForm busiForm = afterSuperviseService.findOne(afterSuperviseId).getBusiForm();
        	busiForm.setFormStatus(BusiFormStatus.THROUAPPROVAL.value);
        	busiFormService.updateEntity(busiForm);
        }
        logger.info("设置督办审批通过后置事项");
        
    }
}
