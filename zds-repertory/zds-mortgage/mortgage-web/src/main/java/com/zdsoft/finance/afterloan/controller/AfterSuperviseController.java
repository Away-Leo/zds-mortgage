package com.zdsoft.finance.afterloan.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.bpm.annotation.client.FinishJob;
import com.zdsoft.bpm.annotation.client.ManualJob;
import com.zdsoft.bpm.service.client.BPM;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.afterloan.entity.AfterDispatch;
import com.zdsoft.finance.afterloan.entity.AfterMonitorRecord;
import com.zdsoft.finance.afterloan.entity.AfterSupervise;
import com.zdsoft.finance.afterloan.service.AfterDispatchService;
import com.zdsoft.finance.afterloan.service.AfterSuperviseService;
import com.zdsoft.finance.afterloan.service.LoanAfterHandleService;
import com.zdsoft.finance.afterloan.vo.AfterDispatchVo;
import com.zdsoft.finance.afterloan.vo.AfterSuperviseVo;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.finance.entity.CustomerReceivable;
import com.zdsoft.finance.finance.service.CustomerReceivableService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterSuperviseController.java 
 * @ClassName: AfterSuperviseController 
 * @Description: 督办controller
 * @author xj 
 * @date 2017年3月2日 下午3:24:21 
 * @version V1.0
 */
@Controller
@RequestMapping("/afterSupervise")
public class AfterSuperviseController extends BaseController {
	@Autowired
	private AfterSuperviseService afterSuperviseService;
	@Autowired
	private CaseApplyService caseApplyService;
	@Autowired
	private CustomerReceivableService customerReceivableService;
	@Autowired
	private AfterDispatchService afterDispatchService;
	@Autowired
	private LoanAfterBasePageController loanAfterBasePageController;
	@Autowired
	private CED CED;
	@Autowired
	private BPM BPM;
	@Autowired
	private LoanAfterHandleService loanAfterHandleService;
	
	/**
	 * 
	 * @Title: launchAfterSupervise 
	 * @Description: 发起督办入口
	 * @author xj 
	 * @param caseApplyId 案件id
	 * @return
	 */
	@RequestMapping("/launchAfterSuperviseInit")
	@UriKey(key = "com.zdsoft.finance.afterloan.afterSupervise.launchAfterSuperviseInit")
	public ModelAndView launchAfterSuperviseInit(String caseApplyId){
		ModelMap modelMap = null;
		try {
			ModelAndView modelAndView = loanAfterBasePageController.initCaseApplyDetail(caseApplyId, AfterMonitorRecord.MIDDLE_MONITOR_RECORD);
		    modelMap = modelAndView.getModelMap();
			EmpDto loginUser = CED.getLoginUser();
			String empNm = loginUser.getEmpNm();
			String departmentName = loginUser.getDepartmentName();
			modelMap.put("empNm", empNm);
			modelMap.put("departmentName", departmentName);
			modelMap.put("caseApplyId", caseApplyId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("发起督办入口", e);
		}
		return new ModelAndView("afterloan/afterSupervise/after_supervise_edit",modelMap);
	}
	
	/**
	 * 
	 * @Title: launchAfterSuperviseInitEdit 
	 * @Description: 草稿箱进入
	 * @author xj 
	 * @param afterSuperviseId 督办id
	 * @return
	 */
	@RequestMapping("/launchAfterSuperviseInitEdit")
	@UriKey(key = "com.zdsoft.finance.afterloan.afterSupervise.launchAfterSuperviseInitEdit")
	public ModelAndView launchAfterSuperviseInitEdit(@RequestParam(value="businessKey")String afterSuperviseId){
		ModelMap modelMap = null;
		try {
			AfterSupervise afterSupervise = afterSuperviseService.findOne(afterSuperviseId);
			
			ModelAndView modelAndView = loanAfterBasePageController.initCaseApplyDetail(afterSupervise.getCaseApplyId(), AfterMonitorRecord.MIDDLE_MONITOR_RECORD);
			modelAndView.setViewName("afterloan/afterSupervise/after_supervise_edit");
			modelMap = modelAndView.getModelMap();
			
			AfterSuperviseVo afterSuperviseVo = new AfterSuperviseVo(afterSupervise);
			modelMap.put("afterSuperviseVo", afterSuperviseVo);
			modelMap.put("caseApplyId", afterSupervise.getCaseApplyId());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("草稿箱进入督办事项-编辑", e);
		}
		return new ModelAndView("afterloan/afterSupervise/after_supervise_edit",modelMap);
	}
	
	/**
	 * 
	 * @Title: launchAfterSuperviseInitView 
	 * @Description: 督办查看（我的申请）
	 * @author xj 
	 * @param afterSuperviseId 督办id
	 * @return
	 */
	@RequestMapping("/launchAfterSuperviseInitView")
	@UriKey(key = "com.zdsoft.finance.afterloan.afterSupervise.launchAfterSuperviseInitView")
	public ModelAndView launchAfterSuperviseInitView(@RequestParam(value="businessKey")String afterSuperviseId){
		ModelMap modelMap = null;
		try {
			AfterSupervise afterSupervise = afterSuperviseService.findOne(afterSuperviseId);
			
			ModelAndView modelAndView = loanAfterBasePageController.initCaseApplyDetail(afterSupervise.getCaseApplyId(), AfterMonitorRecord.MIDDLE_MONITOR_RECORD);
		    modelMap = modelAndView.getModelMap();
			
			AfterSuperviseVo afterSuperviseVo = new AfterSuperviseVo(afterSupervise);
			modelMap.put("afterSuperviseVo", afterSuperviseVo);
			modelMap.put("caseApplyId", afterSupervise.getCaseApplyId());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("草稿箱进入督办事项-查看", e);
		}
		return new ModelAndView("afterloan/afterSupervise/after_supervise_view",modelMap);
	}
	
	/**
	 * 
	 * @Title: launchAfterSupervise 
	 * @Description: 发起督办
	 * @author xj 
	 * @param afterSuperviseVo 督办信息
	 * @param submitStatus 是否提交
	 * @return
	 */
	@RequestMapping("/launchAfterSupervise")
	@UriKey(key = "com.zdsoft.finance.afterloan.afterSupervise.launchAfterSupervise")
	@ResponseBody
	public ResponseMsg launchAfterSupervise(AfterSuperviseVo afterSuperviseVo,boolean submitStatus){
		ResponseMsg msg = new ResponseMsg();
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setMsg("发起督办成功！");
		AfterSupervise afterSupervise = afterSuperviseVo.toPO();
		try {
			afterSupervise = afterSuperviseService.saveOrUpdateAfterSupervise(afterSupervise, submitStatus);
			msg.setId(afterSupervise.getId());
			if(ObjectHelper.isNotEmpty(afterSupervise.getCurrentDealEmpName())){
					msg.setMsg("保存成功！下一节点处理人："+afterSupervise.getCurrentDealEmpName());
	            }else{
	            	msg.setMsg("保存成功！");
	            }
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			logger.error("发起督办", e);
			e.printStackTrace();
		}
		
		return msg;
	}
	
	/**
	 * 
	 * @Title: orgTask 
	 * @Description: 流程中机构派工入口
	 * @author xj 
	 * @param caseApplyId 案件id
	 * @param businessKey 业务id
	 * @return
	 */
	@RequestMapping("/orgTask")
	@UriKey(key = "com.zdsoft.finance.afterloan.afterSupervise.orgTask")
	@ManualJob(resource = "com.zdsoft.finance.afterloan.afterSupervise.orgTask", label = "机构派工") 
	public ModelAndView orgTask(@RequestParam(value="projectId")String caseApplyId,String businessKey,String processInstanceId){
		
		ModelMap model = null;
		logger.info("进入机构派工");
		logger.info("caseApplyId:"+caseApplyId);
		logger.info("businessKey:"+businessKey);
		try {
			ModelAndView modelAndView = loanAfterBasePageController.initCaseApplyDetail(caseApplyId, AfterMonitorRecord.MIDDLE_MONITOR_RECORD);
			model = modelAndView.getModelMap();
			
			EmpDto loginUser = CED.getLoginUser();
			String afterSuperviseId = (String) BPM.getBusinessVariable(processInstanceId, "afterSuperviseId");
			AfterDispatch afterDispatch = afterDispatchService.findByAfterSuperviseIdAndSuperviserCode(afterSuperviseId, loginUser.getEmpCd());
			if(ObjectHelper.isNotEmpty(afterDispatch)){
				AfterDispatchVo afterDispatchVo = new AfterDispatchVo(afterDispatch);
				model.put("afterDispatchVo", afterDispatchVo);
			}
			logger.info("afterSuperviseId:"+afterSuperviseId);
			//督办
			AfterSupervise afterSupervise = afterSuperviseService.findOne(afterSuperviseId);
			AfterSuperviseVo afterSuperviseVo = new AfterSuperviseVo(afterSupervise);
			//跟进催收单
			String followInfoId = afterSupervise.getFollowInfoId();
			logger.info("followinfoId:"+followInfoId);
			if(ObjectHelper.isNotEmpty(followInfoId)){
				if(ObjectHelper.isNotEmpty(followInfoId)){
					Map<String, Object> followInfoForm = this.getFollowInfoForm(followInfoId);
					model.put("follow", followInfoForm);
				}
			}
			
			model.put("caseApplyId", caseApplyId);
			model.put("businessKey", businessKey);
			model.put("afterSuperviseVo", afterSuperviseVo);
			
		} catch (Exception e) {
			logger.error("进入合规复核审批", e);
			e.printStackTrace();
		}
		return new ModelAndView("/afterloan/afterSupervise/after_orgTask_process_edit", model);
	}

	/**
	 * @Title: saveOrupdateOrgTask 
	 * @Description: 流程中机构派工保存
	 * @author xj 
	 * @param jsoncallback
	 * @param afterSuperviseVo 督办信息
	 * @param caseApplyId 案件id
	 * @param businessKey 业务表单id
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdateOrgTask")
	@UriKey(key = "com.zdsoft.finance.afterloan.afterSupervise.saveOrUpdateOrgTask")
	@FinishJob(resource = "com.zdsoft.finance.afterloan.afterSupervise.orgTask", businessId = "businessKey", projectId = "caseApplyId")
	@ResponseBody
	public String saveOrUpdateOrgTask(String jsoncallback,AfterDispatchVo afterDispatchVo,String afterSuperviseId,String caseApplyId,String businessKey){
		logger.info("流程中机构派工:"+afterDispatchVo);
		ResponseMsg msg = new ResponseMsg();
		msg.setResultStatus(ResponseMsg.SUCCESS);
		try {
			 afterDispatchService.saveAfterDispatch(afterSuperviseId, afterDispatchVo.getDispatchCode());
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("保存失败，请查看系统日志！");
			e.printStackTrace();
			logger.error("流程中流程中机构派工保存", e);
		}
		return  ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 
	 * @Title: orgTask 
	 * @Description: 流程中贷后人员反馈入口
	 * @author xj 
	 * @param caseApplyId 案件id
	 * @param businessKey 业务id
	 * @return
	 */
	@RequestMapping("/afterPerFeedback")
	@UriKey(key = "com.zdsoft.finance.afterloan.afterSupervise.afterPerFeedback")
	@ManualJob(resource = "com.zdsoft.finance.afterloan.afterSupervise.afterPerFeedback", label = "贷后人员反馈") 
	public ModelAndView afterPerFeedback(@RequestParam(value="projectId")String caseApplyId,String businessKey,String processInstanceId){
		
		ModelMap model = new ModelMap();
		logger.info("进入贷后人员反馈");
		logger.info("caseApplyId:"+caseApplyId);
		logger.info("businessKey:"+businessKey);
		try {
			ModelAndView modelAndView = loanAfterBasePageController.initCaseApplyDetail(caseApplyId, AfterMonitorRecord.MIDDLE_MONITOR_RECORD);
			model = modelAndView.getModelMap();
			
			EmpDto loginUser = CED.getLoginUser();
			String afterSuperviseId = (String) BPM.getBusinessVariable(processInstanceId, "afterSuperviseId");
			logger.info("afterSuperviseId:"+afterSuperviseId);
			//派工信息
			AfterDispatch afterDispatch = afterDispatchService.findByAfterSuperviseIdAndDispatchCode(afterSuperviseId, loginUser.getEmpCd());
			AfterDispatchVo afterDispatchVo = new AfterDispatchVo(afterDispatch);
			//督办信息
			AfterSupervise afterSupervise = afterSuperviseService.findOne(afterSuperviseId);
			AfterSuperviseVo afterSuperviseVo = new AfterSuperviseVo(afterSupervise);
			long feedbackDate = DateHelper.dateToLong(new Date(), DateHelper.DATE_SHORT_SIMPLE_FORMAT);
			afterDispatchVo.setFeedbackDate(feedbackDate);
			
			//跟进催收单
			String followInfoId = afterSupervise.getFollowInfoId();
			logger.info("followinfoId:"+followInfoId);
			if(ObjectHelper.isNotEmpty(followInfoId)){
				Map<String, Object> followInfoForm = this.getFollowInfoForm(followInfoId);
				model.put("follow", followInfoForm);
			}
			model.put("caseApplyId", caseApplyId);
			model.put("businessKey", businessKey);
			model.put("afterSuperviseVo", afterSuperviseVo);
			model.put("afterDispatchVo", afterDispatchVo);
		} catch (Exception e) {
			logger.error("进入贷后人员反馈", e);
			e.printStackTrace();
		}
		return new ModelAndView("/afterloan/afterSupervise/after_perFeedback_process_edit", model);
	}
	
	/**
	 * @Title: saveOrupdateOrgTask 
	 * @Description: 流程贷后人员反馈保存
	 * @author xj 
	 * @param jsoncallback
	 * @param afterSuperviseVo 督办信息
	 * @param caseApplyId 案件id
	 * @param businessKey 业务表单id
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdateAfterPerFeedback")
	@UriKey(key = "com.zdsoft.finance.afterloan.afterSupervise.saveOrUpdateAfterPerFeedback")
	@FinishJob(resource = "com.zdsoft.finance.afterloan.afterSupervise.afterPerFeedback", businessId = "businessKey", projectId = "caseApplyId")
	@ResponseBody
	public String saveOrUpdateAfterPerFeedback(String jsoncallback,String feedbackRresults,String afterSuperviseId,String caseApplyId,String businessKey){
		logger.info("后人员反馈节结果:"+feedbackRresults);
		ResponseMsg msg = new ResponseMsg();
		msg.setResultStatus(ResponseMsg.SUCCESS);
		
		try {
			afterDispatchService.updateAfterDispatch(afterSuperviseId, feedbackRresults);
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("保存失败，请查看系统日志！");
			e.printStackTrace();
			logger.error("后人员反馈保存", e);
		}
		return  ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
  	 * 
  	 * @Title: findAllContactPersonalByCaseApplyId 
  	 * @Description: 通过案件id查询所有联系人 主借人、共借人、担保人、紧急联系人
  	 * @author xj 
  	 * @param caseApplyId 案件id
  	 * @return
  	 * @throws Exception 
  	 */
	@RequestMapping(value = "/findAllContactPersonalByCaseApplyId")
	@UriKey(key = "com.zdsoft.finance.afterloan.afterSupervise.findAllContactPersonalByCaseApplyId")
	@ResponseBody
	public ResponseMsg findAllContactPersonalByCaseApplyId(String caseApplyId){
		ResponseMsg msg = new ResponseMsg();
		try {
			List<Map<String, Object>> queryAllJoinCustomer = afterSuperviseService.queryAllJoinCustomer(caseApplyId);
			msg.setRows(queryAllJoinCustomer);
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			logger.error("通过案件id查询所有联系人", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			e.printStackTrace();
		}
		return msg;
	}
	
	/**
	 * 
	 * @Title: findCaseApplyBaseMsg 
	 * @Description: 通过案件id查询案件的基本信息
	 * @author xj 
	 * @param caseApplyId
	 * @return
	 */
	@RequestMapping(value = "/findCaseApplyBaseMsg")
	@UriKey(key = "com.zdsoft.finance.afterloan.afterSupervise.findCaseApplyBaseMsg")
	@ResponseBody
	public ResponseMsg findCaseApplyBaseMsg(String caseApplyId){
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			CaseApply findOne = caseApplyService.findOne(caseApplyId);
			CaseApplyVo caseApplyVo = new CaseApplyVo(findOne);
			resultMap.put("caseApplyVo", caseApplyVo);
			CustomerReceivable customerReceivable = customerReceivableService.findByCaseApplyIdAndIsEffect(caseApplyId, true);
			resultMap.put("customerReceivable", customerReceivable);
			
			msg.setOptional(resultMap);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("获取案件基本信息", e);
		}
		return msg;
	}
	
	/**
	 * @Title: getFollowInfoForm 
	 * @Description: 根据跟催id查找跟催详细信息
	 * @author huangwei 
	 * @param followInfoId
	 * @return
	 */
	public Map<String,Object> getFollowInfoForm(String followInfoId) throws Exception{
		Map<String,Object> dataMap=new HashMap<String,Object>();
		dataMap=loanAfterHandleService.getFollowInfoMessage(followInfoId);
		return dataMap;
	}
}
