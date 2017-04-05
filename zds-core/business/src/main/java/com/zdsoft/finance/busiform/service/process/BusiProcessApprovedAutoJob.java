package com.zdsoft.finance.busiform.service.process;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.bpm.annotation.client.AutoJob;
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
 * @Title: BusiProcessApprovedAutoJob.java 
 * @ClassName: BusiProcessApprovedAutoJob 
 * @Description: 流程业务审批通过
 * @author dengyy 
 * @version V1.0 
 * @date 2017年3月2日 上午11:26:05 
 *
 */
@Service
@AutoJob(label = "业务审批通过", resource = "com.zdsoft.finance.busiform.service.process.approved.autoJob")
public class BusiProcessApprovedAutoJob implements JavaDelegate {

	@Resource
	private BusiFormService busiFormService;
	
	@Log
	private Logger log;
	
	@Override
	@Transactional
	public void execute(DelegateExecution execution) throws Exception {
		log.info("业务申请通过");
		Object busiObj = execution.getVariable(BusiForm.PROCESS_STORE_KEY);
		if (ObjectHelper.isNotEmpty(busiObj)) {
			BusiForm entity = busiFormService.findOne((String)busiObj);
			if (ObjectHelper.isNotEmpty(entity)) {
				log.debug("业务流程表单Id为：{}", entity.getId());
				entity.setFormStatus(BusiFormStatus.THROUAPPROVAL.value);
				this.busiFormService.updateEntity(entity);
			}
		}
	}
}
