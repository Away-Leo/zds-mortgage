package com.zdsoft.finance.specialapprove.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.casemanage.datasurvey.entity.FeeInfomation;
import com.zdsoft.finance.casemanage.datasurvey.service.FeeInfomationService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.filestore.vo.FileStoreVo;
import com.zdsoft.finance.marketing.entity.CaseApplyStageEnumSimpleCodeEnum;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveFee;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveManage;
import com.zdsoft.finance.specialapprove.service.SpecialApproveFeeService;
import com.zdsoft.finance.specialapprove.service.SpecialApproveManageService;
import com.zdsoft.finance.specialapprove.vo.FeeSpecialApproveItemVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: SpecialApproveController.java
 * @ClassName: SpecialApproveController
 * @Description: 费用特批controller
 * @author wangrongwei
 * @date 2017年2月14日 下午3:25:52
 * @version V1.0
 */
@Controller
@RequestMapping("/specialApproveFee")
public class SpecialApproveFeeController extends BaseController {

	@Autowired
	private FeeInfomationService feeInfomationService;

	@Autowired
	private SpecialApproveFeeService specialApproveFeeService;
	
	@Autowired
	private SpecialApproveManageService specialApproveManageService;
	
	@Autowired
	private SpecialApproveController specialApproveController;
	
	/** 
	 * @Title: feeSpecialApproveExamination 
	 * @Description: 费用特批审批单（流程中编辑）
	 * @author wangrongwei
	 * @param caseApplyId 案件ID
	 * @param businessKey 业务ID
	 * @param model
	 * @return  
	 */ 
	@RequestMapping("/feeSpecialApproveExamination")
	@UriKey(key="com.zdsoft.finance.specialApprove.feeSpecialApproveExamination")
	@ManualJob(resource="com.zdsoft.finance.specialApprove.feeSpecialApproveExamination",label="费用特批审批单")
	@FinishJob(resource="com.zdsoft.finance.specialApprove.feeSpecialApproveExamination",businessId="businessKey",projectId="caseApplyId")
	public ModelAndView feeSpecialApproveExamination(@RequestParam(value = "projectId") String caseApplyId,
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
		return new ModelAndView("specialapprove/system_fee_special_approve_process_edit",model);
	}
	
	/** 
	 * @Title: feeSpecialApproveExaminationView 
	 * @Description: 费用特批审批单（流程中查看）
	 * @author wangrongwei
	 * @param caseApplyId 案件ID
	 * @param businessKey 业务ID
	 * @param model
	 * @return  
	 */ 
	@RequestMapping("/feeSpecialApproveExaminationView")
	@UriKey(key="com.zdsoft.finance.specialApprove.feeSpecialApproveExaminationView")
	@ManualJob(resource="com.zdsoft.finance.specialApprove.feeSpecialApproveExaminationView",label="费用特批审批单查看")
	@FinishJob(resource="com.zdsoft.finance.specialApprove.feeSpecialApproveExaminationView",businessId="businessKey",projectId="caseApplyId")
	public ModelAndView feeSpecialApproveExaminationView(@RequestParam(value = "projectId") String caseApplyId,
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
		return new ModelAndView("specialapprove/system_fee_special_approve",model);
	}

	/**
	 * @Title: saveOrCommitFeeSpecialApproveApply
	 * @Description: 保存提交费用特批申请
	 * @author wangrongwei
	 * @param caseApplyId 案件ID
	 * @param specialApproveManageId 特批管理ID
	 * @param remark 备注
	 * @param isSubmit 是否提交
	 * @return
	 */
	@RequestMapping("/saveOrCommitFeeSpecialApproveApply")
	@UriKey(key = "com.zdsoft.finance.specialApprove.saveOrCommitFeeSpecialApproveApply")
	@ResponseBody
	public String saveOrCommitFeeSpecialApproveApply(String caseApplyId, String specialApproveManageId, String remark,
			Boolean isSubmit,String jsoncallback) {
		ResponseMsg msg = new ResponseMsg();
		BusiForm busiForm;
		try {
			busiForm = specialApproveFeeService.saveOrCommitFeeSpecialApproveApply(caseApplyId, specialApproveManageId,
					remark, isSubmit);
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
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("操作失败： " + e.getMessage());
			logger.error("错误",e);
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(msg,jsoncallback);
	}

	/**
	 * @Title: specialApproveFeeApply
	 * @Description: 费用特批申请
	 * @author wangrongwei
	 * @param caseApplyId 案件ID
	 * @param model
	 * @return
	 */
	@RequestMapping("/initSpecialApproveFeeApply")
	@UriKey(key = "com.zdsoft.finance.specialApprove.initSpecialApproveFeeApply")
	public ModelAndView initSpecialApproveFeeApply(String caseApplyId, ModelMap model) {
		model.put("caseApplyId", caseApplyId);
		model.put("viewType", 0);//编辑
		//初始化申请信息
		FileStoreVo fileStoreVo = null;
		try {
			//查询草稿状态的费用特批
			SpecialApproveManage approveManage = specialApproveManageService.findByCaseApplyIdAndSpecialApproveTypeAndSpecialApproveStatus(caseApplyId, 3, SpecialApproveManage.SPECIAL_APPROVE_STATUS_DRAFT_SYS);
			if (ObjectHelper.isEmpty(approveManage)) {
				//初始化费用特批
				BusiForm busiForm = specialApproveFeeService.saveOrCommitFeeSpecialApproveApply(caseApplyId, "", "", false);
				fileStoreVo = specialApproveController.queryAttaParam(caseApplyId, busiForm.getBusinessEntityId(),
						CaseApplyStageEnumSimpleCodeEnum.SPECIAL_APPROVE.value.substring(8));
				model.put("businessKey", busiForm.getBusinessEntityId());
			}else{
				model.put("remark", approveManage.getRemark());
				fileStoreVo = specialApproveController.queryAttaParam(caseApplyId, approveManage.getId(),
						CaseApplyStageEnumSimpleCodeEnum.SPECIAL_APPROVE.value.substring(8));
				model.put("businessKey", approveManage.getId());
			}
			model.put("fileStoreVo", fileStoreVo);
		} catch (Exception e) {
			logger.error("查询费用特批出错：" + e.getMessage());
			logger.error("错误",e);
			e.printStackTrace();
		}
		return new ModelAndView("specialapprove/system_fee_special_approve", model);
	}
	
	/** 
	 * @Title: initSpecialApproveFeeEdit 
	 * @Description: 费用特批编辑页面
	 * @author wangrongwei
	 * @param caseApplyId 案件ID
	 * @param businessKey 业务ID
	 * @param model
	 * @return  
	 */ 
	@RequestMapping("/specialApproveFeeEdit")
	@UriKey(key = "com.zdsoft.finance.specialApprove.specialApproveFeeEdit")
	public ModelAndView specialApproveFeeEdit(@RequestParam("projectId") String caseApplyId,String businessKey, ModelMap model) {
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
		model.put("viewType", 0);//编辑页面
		return new ModelAndView("specialapprove/system_fee_special_approve", model);
	}
	
	/** 
	 * @Title: specialApproveFeeView 
	 * @Description: 费用特批查看
	 * @author wangrongwei
	 * @param caseApplyId 案件ID
	 * @param businessKey 业务ID
	 * @param model
	 * @return  
	 */ 
	@RequestMapping("/specialApproveFeeView")
	@UriKey(key = "com.zdsoft.finance.specialApprove.specialApproveFeeView")
	public ModelAndView specialApproveFeeView(@RequestParam("projectId") String caseApplyId,String businessKey, ModelMap model) {
		model.put("caseApplyId", caseApplyId);
		try {
			SpecialApproveManage specialApproveManage = specialApproveManageService.findOne(businessKey);
			if (ObjectHelper.isNotEmpty(specialApproveManage)) {
				model.put("remark", specialApproveManage.getRemark());
				model.put("businessKey", specialApproveManage.getId());
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
		
		return new ModelAndView("specialapprove/system_fee_special_approve", model);
	}

	/**
	 * @Title: querySysFeeRules
	 * @Description: 查询系统触发的费用特批规则明细
	 * @author wangrongwei
	 * @param caseApplyId  案件ID
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/querySysFeeRules")
	@UriKey(key = "com.zdsoft.finance.specialApprove.querySysFeeRules")
	@ResponseBody
	public String querySysFeeRules(String businessKey, String jsoncallback) {
		ResponseMsg msg = new ResponseMsg();
		List<FeeSpecialApproveItemVo> listFeeSpecialApproveItemVo = new ArrayList<>();
		try {
			logger.info("查询费用特批规则明细.....  businessKey为:" + businessKey);
			// 查询系统触发的费用特批
			List<SpecialApproveFee> listFee=specialApproveManageService.findOne(businessKey).getListSpecialApproveFee();
			FeeSpecialApproveItemVo sumfeeSpecialApproveItemVo = new FeeSpecialApproveItemVo();
			BigDecimal expectedAmount = new BigDecimal(0);//应收金额
			BigDecimal standardAmount = new BigDecimal(0);//标准应收金额
			int i = 1;
			for (SpecialApproveFee fee : listFee) {
				// 查询费用收费
				FeeInfomation feeInfomation = feeInfomationService.findOne(fee.getReceiveFeeId());// 收费ID
				FeeSpecialApproveItemVo feeSpecialApproveItemVo = new FeeSpecialApproveItemVo(i+"",feeInfomation.getExpectedAmount(),fee.getStandardAmount(),feeInfomation.getFeeTypeName());
				listFeeSpecialApproveItemVo.add(feeSpecialApproveItemVo);
				expectedAmount = expectedAmount.add(feeSpecialApproveItemVo.getExpectedAmount());
				standardAmount = standardAmount.add(feeSpecialApproveItemVo.getStandardAmount());
				i++;
			}
			sumfeeSpecialApproveItemVo.setExpectedAmount(expectedAmount);
			sumfeeSpecialApproveItemVo.setStandardAmount(standardAmount);
			sumfeeSpecialApproveItemVo.setFeeTypeName("合计");
			listFeeSpecialApproveItemVo.add(sumfeeSpecialApproveItemVo);
			logger.info("查询到的费用特批规则明细长度为：" + listFee.size());
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("查询成功");
			msg.setTotal((long) listFee.size());
			msg.setRows(listFeeSpecialApproveItemVo);
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("查询失败");
			logger.error("错误",e);
			logger.info("查询费用特批规则明细失败....." + e.getMessage());
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

}
