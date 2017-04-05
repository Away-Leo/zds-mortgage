package com.zdsoft.finance.casemanage.handleMortgage.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.bpm.annotation.client.FinishJob;
import com.zdsoft.bpm.annotation.client.ManualJob;
import com.zdsoft.finance.casemanage.datasurvey.entity.MatterModuleValidate;
import com.zdsoft.finance.casemanage.datasurvey.entity.MatterNameEnum;
import com.zdsoft.finance.casemanage.datasurvey.service.MatterModuleValidateService;
import com.zdsoft.finance.casemanage.vo.MatterModuleValidateVo;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HandleMortgageController.java 
 * @ClassName: HandleMortgageController 
 * @Description: 办理抵押Controller
 * @author zhoushichao 
 * @date 2017年2月24日 下午2:41:03 
 * @version V1.0 
 */ 
@Controller
@RequestMapping("/casemanage/handleMortgage")
public class HandleMortgageController extends BaseController {

	@Autowired
    private CaseApplyService caseApplyService;
	@Autowired
	private MatterModuleValidateService matterModuleValidateService;
	
	/**
	 * @Title: editHandleMortgage 
	 * @Description: 办理抵押入口
	 * @author zhoushichao 
	 * @param projectId 案件Id
	 * @param processInstanceId 流程实例key
	 * @param businessKey 流程key
	 * @return
	 */
	@RequestMapping("/editHandleMortgage")
    @UriKey(key = "com.cnfh.rms.casemanage.handleMortgage.editHandleMortgage")
    @ManualJob(resource = "com.cnfh.rms.casemanage.handleMortgage.editHandleMortgage", label = "办理抵押")
	
    public ModelAndView editHandleMortgage(String projectId, String processInstanceId, String businessKey){
        ModelMap model = new ModelMap();
        try {
			CaseApply caseApply = caseApplyService.findOne(projectId);
			CaseApplyVo caseApplyVo = new CaseApplyVo(caseApply);
			
			//设置事项名称
			String matterName = MatterNameEnum.HANDLE_MORTGAGE.value;
			List<MatterModuleValidate> validateList = matterModuleValidateService.findByBusinessKeyAndMatterName(projectId, matterName);
			List<MatterModuleValidateVo> validateVoList = new ArrayList<MatterModuleValidateVo>();
			for (MatterModuleValidate matterModuleValidate : validateList) {
				MatterModuleValidateVo validateVo = new MatterModuleValidateVo();
				validateVo.setTabName(matterModuleValidate.getTabName());
				validateVo.setExecuteTag(matterModuleValidate.getExecuteTag());
				validateVoList.add(validateVo);
			}
			String validateVoJson = ObjectHelper.objectToJson(validateVoList);
			model.put("validateVoJson", validateVoJson);
			model.put("caseApplyVo", caseApplyVo);
			model.put("businessKey", businessKey);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("办理抵押入口失败:", e);
		}
        
        return new ModelAndView("/casemanage/handleMortgage/handle_mortgage_eidt", model);
    }
	
	/**
	 * 
	 * @Title: editJobSaveHandleMortgage 
	 * @Description: 办理抵押提交判断
	 * @author zhoushichao 
	 * @param businessKey 流程实例key
	 * @param projectId  案件Id
	 * @return
	 */
	@RequestMapping(value = "/editJobSaveHandleMortgage")
	@UriKey(key = "com.cnfh.rms.casemanage.handleMortgage.editJobSaveHandleMortgage")
	@ResponseBody
	@FinishJob(resource = "com.cnfh.rms.casemanage.handleMortgage.editHandleMortgage", businessId = "businessKey", projectId = "projectId")
	public ResponseMsg editJobSaveHandleMortgage(String businessKey, String projectId) {
		ResponseMsg msg = new ResponseMsg();
		logger.info("提交办理抵押编辑事项");
		logger.debug("projectId：{}", projectId);
		logger.debug("businessKey:{}", businessKey);
		try {
				msg.setResultStatus(ResponseMsg.SUCCESS);
				msg.setMsg("提交办理抵押成功！");
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("提交办理抵押失败!");
			logger.error("提交办理抵押失败", e);
			e.printStackTrace();
		}
		return msg;

	}
	
}
