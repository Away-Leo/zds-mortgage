package com.zdsoft.finance.casemanage.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.bpm.annotation.client.AutoJob;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.common.dto.DelegateExecution;
import com.zdsoft.framework.core.common.service.JavaDelegate;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
* 版权所有：重庆正大华日软件有限公司   
* @Title: UpdateCaseApplyStageJob.java 
* @Package com.zdsoft.finance.casemanage.process 
* @Description: 更新案件阶段信息事项 
* @author zjx  
* @date 2017年2月24日 上午11:12:10 
* @version V1.0   
*/
@Service
@AutoJob(label = "更新案件阶段信息事项", resource = "com.zdsoft.finance.casemanage.process.updateCaseApplyStageJob")
public class UpdateCaseApplyStageJob implements JavaDelegate{

	private Logger logger = LoggerFactory.getLogger(UpdateCaseApplyStageJob.class);

	@Autowired
	private CaseApplyService caseApplyService;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void execute(DelegateExecution execution) throws Exception {
		logger.info("开始更新案件阶段状态");
		String caseApplyId = (String) execution.getVariable("projectId");
		String caseApplyStage = (String) execution.getVariable("caseApplyStage");
		if(ObjectHelper.isEmpty(caseApplyStage)){
			logger.debug("未配置caseApplyStage参数");
			throw new BusinessException("未配置caseApplyStage参数");
		}
		logger.info("获取caseApplyId="+caseApplyId);
		logger.info("获取caseApplyStage="+caseApplyStage);
		CaseApply caseApply = caseApplyService.findOne(caseApplyId);
		caseApply.setStage(caseApplyStage);
		caseApply=caseApplyService.saveOrUpdateEntity(caseApply);
		logger.info("更新案件阶段状态结束");
	}
}
