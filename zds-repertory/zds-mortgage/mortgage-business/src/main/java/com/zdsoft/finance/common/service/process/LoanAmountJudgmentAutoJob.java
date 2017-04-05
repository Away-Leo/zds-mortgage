package com.zdsoft.finance.common.service.process;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.bpm.annotation.client.AutoJob;
import com.zdsoft.bpm.service.client.BPM;
import com.zdsoft.finance.common.utils.AmountConversionUtil;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.prcostitem.service.job.PrCostitemApplyEffectJob;
import com.zdsoft.framework.core.common.dto.DelegateExecution;
import com.zdsoft.framework.core.common.exception.BusinessException;
import com.zdsoft.framework.core.common.service.JavaDelegate;
import com.zdsoft.framework.core.common.util.ObjectHelper;

 
/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: LoanAmountJudgmentAutoJob.java 
 * @ClassName: LoanAmountJudgmentAutoJob 
 * @Description: 权限判断
 * @author wangrongwei
 * @date 2017年3月3日 下午1:53:50 
 * @version V1.0 
 */ 
@Service
@AutoJob(resource="com.zdsoft.finance.caseApply.LoanAmountJudgmentAutoJob",label="贷款金额判断")
@Lazy(false)
public class LoanAmountJudgmentAutoJob implements JavaDelegate {

	private Logger logger = LoggerFactory.getLogger(PrCostitemApplyEffectJob.class);
	
	@Autowired
	private BPM bpm;
	
	@Autowired
	private CaseApplyService caseApplyService;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void execute(DelegateExecution exec) throws Exception {
		logger.info("开始执行贷款金额判断自动事项...");
		String businessKey = ObjectHelper.isEmpty(exec.getVariable("businessKey"))?"":exec.getVariable("businessKey").toString();
		String procInstId = ObjectHelper.isEmpty(exec.getVariable("processInstanceId"))?"":exec.getVariable("processInstanceId").toString();
		logger.info("业务ID为 ：" + businessKey);
		logger.info("processInstansId为 ：" + procInstId);
		CaseApply caseApply = caseApplyService.findOne(businessKey);
		if (ObjectHelper.isNotEmpty(caseApply)) {
			BigDecimal loanAmount = caseApply.getApplyAmount();
			loanAmount = AmountConversionUtil.convertToWYuan(loanAmount);
			Map<String, String> vars = new HashMap<>();
			vars.put("apply_amount", loanAmount.intValue()+"");
			bpm.setEngineVariable(procInstId, vars);
		}else{
			throw new BusinessException("案件ID有误");
		}
	}
}
