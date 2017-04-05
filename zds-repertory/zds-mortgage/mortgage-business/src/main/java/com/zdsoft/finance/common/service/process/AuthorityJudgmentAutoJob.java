package com.zdsoft.finance.common.service.process;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.bpm.annotation.client.AutoJob;
import com.zdsoft.bpm.service.client.BPM;
import com.zdsoft.finance.businesssetting.service.AuthGradeService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.prcostitem.service.job.PrCostitemApplyEffectJob;
import com.zdsoft.framework.core.common.dto.DelegateExecution;
import com.zdsoft.framework.core.common.exception.BusinessException;
import com.zdsoft.framework.core.common.service.JavaDelegate;
import com.zdsoft.framework.core.common.util.ObjectHelper;

 
/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AuthorityJudgmentAutoJob.java 
 * @ClassName: AuthorityJudgmentAutoJob 
 * @Description: 权限判断
 * @author wangrongwei
 * @date 2017年3月3日 下午1:53:50 
 * @version V1.0 
 */ 
@Service
@AutoJob(resource="com.zdsoft.finance.caseApply.AuthorityJudgmentAutoJob",label="权限判断")
public class AuthorityJudgmentAutoJob implements JavaDelegate {

	private Logger logger = LoggerFactory.getLogger(PrCostitemApplyEffectJob.class);
	
	@Autowired
	private BPM bpm;
	
	@Autowired
	private AuthGradeService authGradeService;
	
	@Autowired
	private CaseApplyService caseApplyService;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void execute(DelegateExecution exec) throws Exception {
		logger.info("开始执行权限判断自动事项...");
		String businessKey = ObjectHelper.isEmpty(exec.getVariable("businessKey"))?"":exec.getVariable("businessKey").toString();
		String procInstId = ObjectHelper.isEmpty(exec.getVariable("processInstanceId"))?"":exec.getVariable("processInstanceId").toString();
		logger.info("业务ID为 ：" + businessKey);
		logger.info("processInstansId为 ：" + procInstId);
		CaseApply caseApply = caseApplyService.findOne(businessKey);
		if (ObjectHelper.isNotEmpty(caseApply)) {
			//评级额度
			BigDecimal authAmount = authGradeService.findByCaseApplyId(caseApply.getId());
			if (ObjectHelper.isNotEmpty(authAmount)) {
				Map<String, String> vars = new HashMap<>();
				if (caseApply.getApplyAmount().compareTo(authAmount)>0) {
					//额度外
					vars.put("power_key", "2");
					bpm.setEngineVariable(procInstId, vars);
				}else{
					//额度内
					vars.put("power_key", "1");
					bpm.setEngineVariable(procInstId, vars);
				}
			}else{
				throw new BusinessException("权限判断自动事项  通过案件查询评级额度为空，请检查机构评级配置  案件ID为：" + caseApply.getId());
			}
		}else{
			throw new BusinessException("案件ID有误");
		}
	}
}
