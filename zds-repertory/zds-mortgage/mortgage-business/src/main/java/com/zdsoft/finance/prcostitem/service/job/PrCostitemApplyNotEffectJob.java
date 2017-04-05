package com.zdsoft.finance.prcostitem.service.job;

import com.zdsoft.bpm.annotation.client.AutoJob;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.busiform.service.BusiFormService;
import com.zdsoft.finance.prcostitem.entity.PrCostitemApply;
import com.zdsoft.finance.prcostitem.service.PrCostitemApplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.framework.core.common.dto.DelegateExecution;
import com.zdsoft.framework.core.common.service.JavaDelegate;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: PrCostitemApplyNotEffectJob.java 
 * @ClassName: PrCostitemApplyNotEffectJob 
 * @Description: 费用支拥审批不通过
 * @author gufeng 
 * @date 2017年3月13日 下午5:06:17 
 * @version V1.0
 */
@Service
@AutoJob(label = "费用支佣审批不通过", resource = "com.zdsoft.finance.prCostitemApply.prCostitemApplyNotEffectJob")
@Lazy(false)
public class PrCostitemApplyNotEffectJob implements JavaDelegate{

	private Logger logger = LoggerFactory.getLogger(PrCostitemApplyNotEffectJob.class);
	
	@Autowired
	private PrCostitemApplyService prCostitemApplyService;
	@Autowired
	private BusiFormService busiFormService;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void execute(DelegateExecution execution) throws Exception {
		logger.info("业务申请不通过");
		String projectId = (String) execution.getVariable("projectId");
		PrCostitemApply bean = prCostitemApplyService.findOne(projectId);
		bean.setStatus(BusiFormStatus.NOTAPPROVALED.value);
		prCostitemApplyService.updateEntity(bean);
		String businessKey = (String) execution.getVariable("businessKey");
		BusiForm busiForm = busiFormService.findById(businessKey);
		busiForm.setFormStatus(BusiFormStatus.NOTAPPROVALED.value);
		busiFormService.updateBusiForm(busiForm);
	}
	

}
