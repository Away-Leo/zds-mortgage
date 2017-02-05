package com.zdsoft.finance.casemanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.bpm.annotation.client.FinishJob;
import com.zdsoft.bpm.annotation.client.ManualJob;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:DataSurveyController.java
 * @Package:com.zdsoft.finance.casemanage.controller
 * @Description:资调
 * @author: laijun
 * @date:2017年1月10日 下午9:38:22
 * @version:v1.0
 */
@Controller
@RequestMapping("/casemanage/datasurvey")
public class DataSurveyController extends BaseController {

	/**
	 * 
	 * 资调编辑
	 *
	 * @author laijun
	 * @date:2017年1月10日 下午9:38:42
	 * @param caseApplyId
	 * @return
	 */
	@RequestMapping(value = "/edit")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.edit")
	public ModelAndView edit(String caseApplyId) {
		ModelMap model = new ModelMap();
		model.put("caseApplyId", caseApplyId);
		return new ModelAndView("casemanage/datasurvey/datasurvey_edit", model);

	}

	/**
	 * 
	 * 资调编辑流程中
	 *
	 * @author laijun
	 * @date:2017年1月10日 下午9:38:59
	 * @param caseApplyId
	 * @param processInstanceId
	 * @param businessKey
	 * @return
	 */
	@RequestMapping(value = "/editJob")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.editJob")
	@ManualJob(resource = "com.zdsoft.finance.casemanage.datasurvey.editJob", label = "资调编辑")
	public ModelAndView editJob(String projectId, String processInstanceId, String businessKey) {
		ModelMap model = new ModelMap();
		model.put("caseApplyId", projectId);
		model.put("processInstanceId", processInstanceId);
		model.put("businessKey", businessKey);

		return new ModelAndView("casemanage/datasurvey/datasurvey_edit", model);

	}

	/**
	 * 
	 * 资调修改保存
	 *
	 * @author laijun
	 * @date:2017年1月10日 下午9:39:16
	 * @param businessKey
	 * @param caseApplyId
	 * @return
	 */
	@RequestMapping(value = "/editJobSave")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.editJobSave")
	@ResponseBody
	@FinishJob(resource = "com.zdsoft.finance.casemanage.datasurvey.editJob", businessId = "businessKey", projectId = "projectId")
	public ResponseMsg conditionJobEditSave(String businessKey, String projectId) {
		ResponseMsg msg = new ResponseMsg();
		try {

			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("保存成功");
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("保存失败");
			logger.error("保存失败", e);
			e.printStackTrace();
		}
		return msg;

	}

	/**
	 * 
	 * 资调查看
	 *
	 * @author laijun
	 * @date:2017年1月10日 下午9:39:43
	 * @param projectId
	 * @param processInstanceId
	 * @param businessKey
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/viewJob")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.viewJob")
	@ManualJob(resource = "com.zdsoft.finance.casemanage.datasurvey.viewJob", label = "资调查看")
	@FinishJob(resource = "com.zdsoft.finance.casemanage.datasurvey.viewJob", businessId = "businessKey", projectId = "projectId")
	public ModelAndView viewJob(String projectId, String processInstanceId, String businessKey)
			throws BusinessException {
		ModelMap model = new ModelMap();
		model.put("caseApplyId", projectId);
		model.put("processInstanceId", processInstanceId);
		model.put("businessKey", businessKey);
		return new ModelAndView("casemanage/datasurvey/datasurvey_view", model);
	}
	/**
	 * 
	 * 资调查看
	 *
	 * @author laijun
	 * @date:2017年1月10日 下午9:39:43
	 * @param projectId
	 * @param processInstanceId
	 * @param businessKey
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/view")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.view")
	public ModelAndView view(String caseApplyId, String processInstanceId, String businessKey)
			throws BusinessException {
		ModelMap model = new ModelMap();
		model.put("caseApplyId", caseApplyId);
		model.put("processInstanceId", processInstanceId);
		model.put("businessKey", businessKey);
		return new ModelAndView("casemanage/datasurvey/datasurvey_view", model);
	}
}
