package com.zdsoft.finance.rule.process;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.bpm.annotation.client.AutoJob;
import com.zdsoft.bpm.service.client.BPM;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.busiform.service.BusiFormService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.entity.CaseApplyStageEnumSimpleCodeEnum;
import com.zdsoft.finance.marketing.entity.CaseTask;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.service.CaseTaskService;
import com.zdsoft.finance.rule.enums.RuleApprovalFinal;
import com.zdsoft.finance.rule.service.RuleApprovalService;
import com.zdsoft.framework.core.common.dto.DelegateExecution;
import com.zdsoft.framework.core.common.service.JavaDelegate;
import com.zdsoft.message.client.service.CMS;
import com.zdsoft.message.dto.MessageInfoDto;
import com.zdsoft.message.dto.SendType;

@Service
@AutoJob(label = "二审规则判断", resource = "com.zdsoft.finance.rule.process.SecondInstanceRulesJob")
public class SecondInstanceRulesJob implements JavaDelegate{

	private Logger logger = LoggerFactory.getLogger(SecondInstanceRulesJob.class);

	@Autowired
	private RuleApprovalService ruleApprovalService;
	@Autowired
	private CaseApplyService caseApplyService;
	@Autowired
	private BusiFormService busiFormService;
	@Autowired
	private CaseApplyBeforeCustomerService caseApplyBeforeCustomerService;
	@Autowired
	private CaseTaskService caseTaskService;
	@Autowired
	private BPM bpm;
	@Autowired
	private CMS CMS;
	@Autowired
	private CED CED;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void execute(DelegateExecution execution) throws Exception {
		logger.info("开始二审规则判断......");
		String caseApplyId = (String) execution.getVariable("projectId");
		String processInstanceId = (String)execution.getVariable("processInstanceId");
//		String processInstanceId = (String)execution.getVariable("processInstanceId");
		logger.info("获取caseApplyId="+caseApplyId);
		logger.info("获取processInstanceId="+processInstanceId);
		CaseApply apply = caseApplyService.findOne(caseApplyId);
		BusiForm busiForm = apply.getBusiForm();
		Integer isRefuse;
		//判断是否被规则拒绝过  如拒绝过  就不在走规则的拒绝
		if(busiForm.getHadRulesRefuse()){
			isRefuse = RuleApprovalFinal.RUN_NOREFUSE.getValue();
		}else{
			isRefuse = RuleApprovalFinal.RUN_NORMAL.getValue();
		}
		Boolean result = false;
		result = ruleApprovalService.secondApproval(caseApplyId,isRefuse);
		logger.info("二审规则判断结果="+result);
		Map<String,String> map = new HashMap<String,String>();
		if(result){
			logger.info("二审规则判断返回拒绝,更改流程参数rule_opinion_key=2");
			logger.info("更改案件状态为拒绝");
			map.put("rule_opinion_key", "2");
			apply.setStage(CaseApplyStageEnumSimpleCodeEnum.VETO.value);
			busiForm.setFormStatus(BusiFormStatus.RULESREFUSE.value);
			busiForm.setHadRulesRefuse(true);
			busiForm = busiFormService.updateBusiForm(busiForm);
			caseApplyService.saveOrUpdateEntity(apply);
			try {
				logger.info("发送通知短信通知案件拒绝");
				sendMobileMsg(apply);
				logger.info("短信发送完成");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("发送规则拒绝信息失败",e);
			}
		}else{
			logger.info("未中二审拒绝规则,更改流程参数rule_opinion_key=1");
			map.put("rule_opinion_key", "1");
			logger.info("调用评分规则");
			ruleApprovalService.caseScoreApproval(caseApplyId);
			logger.info("调用费用特批规则");
			ruleApprovalService.costApproval(caseApplyId);
		}
	    bpm.setEngineVariable(processInstanceId,map);
	}

	/** 
	 * @Title: sendMobileMsg 
	 * @Description: 发送短信告诉被拒绝了
	 * @author zjx 
	 * @param apply  
	 * @throws Exception 
	 */ 
	private void sendMobileMsg(CaseApply apply) throws Exception {
		// 发送短信
        MessageInfoDto messageInfoDto = new MessageInfoDto();
        // 获取拓展经理信息
        EmpDto Sender = CED.loadEmployeeByCode(apply.getDevelopmentManagerCode());
        CaseTask caseTask = caseTaskService.findByCaseApplyId(apply.getId());
        // 获取资信员信息
        EmpDto Sender2 = CED.loadEmployeeByCode(caseTask.getTaskPersonnelCode());
        // 获取客户名字
        List<CaseApplyBeforeCustomer> customers = caseApplyBeforeCustomerService.queryByCaseApplyIdAndJoinType(apply.getId(),CaseApplyBeforeCustomer.MAIN_BORROW);
        // 发送信息(说得写死)
        String strContent="您为客户【"+customers.get(0).getBeforeCustomer().getCustomerName()+"】申请的案件【"+apply.getCaseApplyCode()+"】已被系统拒绝，案件已退回到拓展经理我的申请中，请查阅。";
        logger.info("短信内容为====="+strContent);
        logger.info("短信接收人(拓展经理)："+Sender.getEmpNm()+","+Sender.getEmpMobile());
        logger.info("短信接收人(资调员)："+Sender2.getEmpNm()+","+Sender2.getEmpMobile());
        messageInfoDto.setContent(strContent);
        // 发送人
        messageInfoDto.setSender(Sender);
        // 发送类型 立即发送
        messageInfoDto.setSendType(SendType.TXI);
        Set<EmpDto> receivers = new HashSet<EmpDto>();
        // 拓展经理
        receivers.add(Sender);
        receivers.add(Sender2);
        // 发送短信
        CMS.sendSMS(messageInfoDto,receivers);
	}
}
