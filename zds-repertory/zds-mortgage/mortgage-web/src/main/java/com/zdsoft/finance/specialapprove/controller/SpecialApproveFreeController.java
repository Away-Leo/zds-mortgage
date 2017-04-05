package com.zdsoft.finance.specialapprove.controller;

import java.util.HashMap;
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
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.filestore.vo.FileStoreVo;
import com.zdsoft.finance.marketing.entity.CaseApplyStageEnumSimpleCodeEnum;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveManage;
import com.zdsoft.finance.specialapprove.service.SpecialApproveManageService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: SpecialApproveFreeController.java
 * @ClassName: SpecialApproveFreeController
 * @Description: 自由还款特批controller
 * @author wangrongwei
 * @date 2017年2月14日 下午3:25:52
 * @version V1.0
 */
@Controller
@RequestMapping("/specialApproveFree")
public class SpecialApproveFreeController extends BaseController {

	@Autowired
	private SpecialApproveManageService specialApproveManageService;
	
	@Autowired
	private SpecialApproveController specialApproveController;
	
	/** 
	 * @Title: freeSpecialApproveExamination 
	 * @Description: 自由还款特批审批单(流程中编辑页面)
	 * @author wangrongwei
	 * @param caseApplyId 案件ID
	 * @param businessKey 业务ID（特批管理ID）
	 * @param model
	 * @return  
	 */ 
	@RequestMapping("/freeSpecialApproveExamination")
	@UriKey(key="com.zdsoft.finance.specialApprove.freeSpecialApproveExamination")
	@ManualJob(resource="com.zdsoft.finance.specialApprove.freeSpecialApproveExamination",label="自由还款特批审批单")
	@FinishJob(resource="com.zdsoft.finance.specialApprove.freeSpecialApproveExamination",businessId="businessKey",projectId="caseApplyId")
	public ModelAndView freeSpecialApproveExamination(@RequestParam(value = "projectId") String caseApplyId,
			String businessKey, ModelMap model){
		SpecialApproveManage specialApproveManage = null;
		if (ObjectHelper.isNotEmpty(businessKey)) {
			try {
				specialApproveManage = specialApproveManageService.findOne(businessKey);
				FileStoreVo fileStoreVo = specialApproveController.queryAttaParam(caseApplyId, businessKey,
						CaseApplyStageEnumSimpleCodeEnum.SPECIAL_APPROVE.value.substring(8));
				model.put("fileStoreVo", fileStoreVo);
			} catch (BusinessException e) {
				logger.error("错误",e);
				e.printStackTrace();
			}
		}
		if (ObjectHelper.isNotEmpty(specialApproveManage)) {
			model.put("remark", specialApproveManage.getRemark());
		}
		model.put("businessKey", businessKey);
		model.put("caseApplyId", caseApplyId);
		return new ModelAndView("specialapprove/free_repay_special_approve_process_edit",model);
	}
	
	/** 
	 * @Title: freeSpecialApproveExaminationView 
	 * @Description: 自由还款特批审批单(流程中查看页面)
	 * @author wangrongwei
	 * @param caseApplyId 案件ID
	 * @param businessKey 业务ID（特批管理ID）
	 * @param model
	 * @return  
	 */ 
	@RequestMapping("/freeSpecialApproveExaminationView")
	@UriKey(key="com.zdsoft.finance.specialApprove.freeSpecialApproveExaminationView")
	@ManualJob(resource="com.zdsoft.finance.specialApprove.freeSpecialApproveExaminationView",label="自由还款特批审批单查看")
	@FinishJob(resource="com.zdsoft.finance.specialApprove.freeSpecialApproveExaminationView",businessId="businessKey",projectId="caseApplyId")
	public ModelAndView freeSpecialApproveExaminationView(@RequestParam(value = "projectId") String caseApplyId,
			String businessKey, ModelMap model){
		SpecialApproveManage specialApproveManage = null;
		if (ObjectHelper.isNotEmpty(businessKey)) {
			try {
				specialApproveManage = specialApproveManageService.findOne(businessKey);
				FileStoreVo fileStoreVo = specialApproveController.queryAttaParam(caseApplyId, businessKey,
						CaseApplyStageEnumSimpleCodeEnum.SPECIAL_APPROVE.value.substring(8));
				model.put("fileStoreVo", fileStoreVo);
			} catch (BusinessException e) {
				logger.error("错误",e);
				e.printStackTrace();
			}
		}
		if (ObjectHelper.isNotEmpty(specialApproveManage)) {
			model.put("remark", specialApproveManage.getRemark());
		}
		model.put("businessKey", businessKey);
		model.put("caseApplyId", caseApplyId);
		model.put("viewType", 2);//流程中编辑
		return new ModelAndView("specialapprove/free_repay_special_approve",model);
	}
	
	/** 
	 * @Title: specialApproveFreeEdit 
	 * @Description: 自由还款特批编辑
	 * @author wangrongwei
	 * @param caseApplyId 案件ID
	 * @param businessKey 业务ID（特批管理ID）
	 * @param model
	 * @return  
	 */ 
	@RequestMapping("/specialApproveFreeEdit")
	@UriKey(key = "com.zdsoft.finance.specialApprove.specialApproveFreeEdit")
	public ModelAndView specialApproveFreeEdit(@RequestParam("projectId") String caseApplyId,String businessKey, ModelMap model) {
		model.put("caseApplyId", caseApplyId);
		model.put("businessKey", businessKey);
		try {
			SpecialApproveManage specialApproveManage = specialApproveManageService.findOne(businessKey);
			if (ObjectHelper.isNotEmpty(specialApproveManage)) {
				model.put("remark", specialApproveManage.getRemark());
			}else{
				logger.error("特批管理ID有误！");
			}
			FileStoreVo fileStoreVo = specialApproveController.queryAttaParam(caseApplyId, businessKey,
					CaseApplyStageEnumSimpleCodeEnum.SPECIAL_APPROVE.value.substring(8));
			model.put("fileStoreVo", fileStoreVo);
		} catch (Exception e) {
			logger.error("错误",e);
			e.printStackTrace();
		}
		model.put("viewType", 0);//编辑
		return new ModelAndView("specialapprove/free_repay_special_approve", model);
	}
	
	/** 
	 * @Title: specialApproveFreeView 
	 * @Description: 自由还款特批查看
	 * @author wangrongwei
	 * @param caseApplyId 案件ID
	 * @param businessKey 业务ID
	 * @param model
	 * @return  
	 */ 
	@RequestMapping("/specialApproveFreeView")
	@UriKey(key = "com.zdsoft.finance.specialApprove.specialApproveFreeView")
	public ModelAndView specialApproveFreeView(@RequestParam("projectId") String caseApplyId,String businessKey, ModelMap model) {
		model.put("caseApplyId", caseApplyId);
		try {
			SpecialApproveManage specialApproveManage = specialApproveManageService.findOne(businessKey);
			if (ObjectHelper.isNotEmpty(specialApproveManage)) {
				model.put("remark", specialApproveManage.getRemark());
			}else{
				logger.error("特批管理ID有误！");
			}
			FileStoreVo fileStoreVo = specialApproveController.queryAttaParam(caseApplyId, businessKey,
					CaseApplyStageEnumSimpleCodeEnum.SPECIAL_APPROVE.value.substring(8));
			model.put("fileStoreVo", fileStoreVo);
		} catch (Exception e) {
			logger.error("错误",e);
			e.printStackTrace();
		}
		model.put("viewType", 1);//查看
		return new ModelAndView("specialapprove/free_repay_special_approve", model);
	}
	
	/**
	 * @Title: pageSpecialApproveRiskApply
	 * @Description: 跳转自由还款特批申请页面
	 * @author wangrongwei
	 * @param caseApplyId 案件ID
	 * @return
	 */
	@RequestMapping("/pageSpecialApproveFreeApply")
	@UriKey(key = "com.zdsoft.finance.specialApprove.pageSpecialApproveFreeApply")
	public ModelAndView pageSpecialApproveFreeApply(ModelMap model, String caseApplyId) {
		model.put("caseApplyId", caseApplyId);
		model.put("viewType", 0);//编辑
		FileStoreVo fileStoreVo = null;
		try {
			//查询草稿状态的自由还款特批
			SpecialApproveManage approveManage = specialApproveManageService.findByCaseApplyIdAndSpecialApproveTypeAndSpecialApproveStatus(caseApplyId, 2, SpecialApproveManage.SPECIAL_APPROVE_STATUS_DRAFT);
			if (ObjectHelper.isEmpty(approveManage)) {
				//初始化自由还款特批
				BusiForm busiForm = specialApproveManageService.saveSpecialApproveFreeApply(caseApplyId, "", "", false);
				fileStoreVo = specialApproveController.queryAttaParam(caseApplyId, busiForm.getBusinessEntityId(),
						CaseApplyStageEnumSimpleCodeEnum.SPECIAL_APPROVE.value.substring(8));
				model.put("businessKey", busiForm.getBusinessEntityId());
			}else{
				//查询特批详情
				fileStoreVo = specialApproveController.queryAttaParam(caseApplyId, approveManage.getId(),
						CaseApplyStageEnumSimpleCodeEnum.SPECIAL_APPROVE.value.substring(8));
				model.put("businessKey", approveManage.getId());
				model.put("remark", approveManage.getRemark());
			}
			model.put("fileStoreVo", fileStoreVo);
		} catch (Exception e) {
			logger.error("错误",e);
			e.printStackTrace();
		}
		return new ModelAndView("specialapprove/free_repay_special_approve", model);
	}
	
	/** 
	 * @Title: saveSpecialApproveFreeApply 
	 * @Description: 保存自由还款特批申请
	 * @author wangrongwei
	 * @param caseApplyId 案件ID
	 * @param specialApproveManageId 业务ID
	 * @param remark 备注
	 * @param isSubmit 是否提交
	 * @return  
	 */ 
	@RequestMapping("/saveSpecialApproveFreeApply")
	@UriKey(key="com.zdsoft.finance.specialApprove.saveSpecialApproveFreeApply")
	@ResponseBody
	public String saveSpecialApproveFreeApply(String caseApplyId,String specialApproveManageId,String remark,Boolean isSubmit,String jsoncallback){
		ResponseMsg msg=new ResponseMsg();
		try {
			BusiForm busiForm = specialApproveManageService.saveSpecialApproveFreeApply(caseApplyId, specialApproveManageId, remark, isSubmit);
			if (ObjectHelper.isNotEmpty(busiForm)) {
				msg.setResultStatus(ResponseMsg.SUCCESS);
				if (isSubmit) {
					msg.setMsg("下一节点处理人：" + busiForm.getCurrentDealEmpNm());
				}else 
					msg.setMsg("保存成功");
				Map<String, Object> map =new HashMap<>();
				map.put("specialApproveManageId", busiForm.getBusinessEntityId());
				msg.setOptional(map);
			}else {
				msg.setMsg("操作失败");
				msg.setResultStatus(ResponseMsg.APP_ERROR);
			}
		} catch (Exception e) {
			logger.error("错误",e);
			logger.info("保存自由还款特批出错 原因：" + e.getMessage());
			msg.setMsg("操作失败： " + e.getMessage());
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
}
