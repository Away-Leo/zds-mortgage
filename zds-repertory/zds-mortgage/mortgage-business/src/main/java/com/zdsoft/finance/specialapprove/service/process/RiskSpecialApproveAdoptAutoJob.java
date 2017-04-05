package com.zdsoft.finance.specialapprove.service.process;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.bpm.annotation.client.AutoJob;
import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.specialapprove.entity.RiskRules;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveManage;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveThings;
import com.zdsoft.finance.specialapprove.repository.RiskRulesRepository;
import com.zdsoft.finance.specialapprove.service.SpecialApproveManageService;
import com.zdsoft.framework.core.common.dto.DelegateExecution;
import com.zdsoft.framework.core.common.service.JavaDelegate;
import com.zdsoft.framework.core.common.util.ObjectHelper;


/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RiskSpecialApproveAutoJob.java 
 * @ClassName: RiskSpecialApproveAutoJob 
 * @Description: 通过风险特批自动事项
 * @author wangrongwei
 * @date 2017年2月23日 上午11:43:52 
 * @version V1.0 
 */ 
@Service
@AutoJob(resource="com.zdsoft.finance.specialApprove.adoptRiskSpecialApprove",label="风险特批通过")
public class RiskSpecialApproveAdoptAutoJob implements JavaDelegate {

	private Logger logger = LoggerFactory.getLogger(RiskSpecialApproveAdoptAutoJob.class);
	
	@Autowired
	private SpecialApproveManageService specialApproveManageService;
	
	@Autowired
	private RiskRulesRepository riskRulesRepository;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void execute(DelegateExecution exec) throws Exception {
		logger.info("开始执行通过风险特批...");
		String specialApproveId = ObjectHelper.isEmpty(exec.getVariable("businessKey"))?"":exec.getVariable("businessKey").toString();
		logger.info("业务ID：" + specialApproveId);
		
		SpecialApproveManage approveManage = specialApproveManageService.findOne(specialApproveId);
		if (ObjectHelper.isNotEmpty(approveManage)) {
			logger.info("风险特批审批通过");
			approveManage.setSpecialApproveStatus(SpecialApproveManage.SPECIAL_APPROVE_STATUS_ADOPT);
			approveManage.getBusiForm().setFormStatus(BusiFormStatus.THROUAPPROVAL.value);
			List<SpecialApproveThings> listRisk = approveManage.getListSpecialApproveThings();
			if (ObjectHelper.isNotEmpty(listRisk)) {
				logger.info("风险事项长度：" + listRisk.size());
				for (SpecialApproveThings risk : listRisk) {
					if (ObjectHelper.isNotEmpty(risk.getExpRiskRulesId()) && approveManage.isSystem()) {//系统触发特批
						List<RiskRules> listRiskRules = riskRulesRepository.findByRulesTypeAndCaseApplyId(risk.getItemCode(), approveManage.getCaseApply().getId());
						for (RiskRules riskRules : listRiskRules) {
							riskRules.setSpecialStatus(SpecialApproveManage.SPECIAL_APPROVE_STATUS_ADOPT);
						}
					}else if(approveManage.isSystem()){
						throw new BusinessException("更新系统触发风险特批状态数据异常");
					}
				}
			}
			
		}else{
			logger.info("----特批管理ID不存在或为空----");
			throw new BusinessException("数据异常");
		}
	}
}
