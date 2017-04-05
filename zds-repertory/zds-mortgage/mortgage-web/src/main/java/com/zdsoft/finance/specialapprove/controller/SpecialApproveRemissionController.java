package com.zdsoft.finance.specialapprove.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlan;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivablePlanService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.DateUtil;
import com.zdsoft.finance.customer.entity.BeforeCustomer;
import com.zdsoft.finance.filestore.vo.FileStoreVo;
import com.zdsoft.finance.finance.entity.CustomerReceivable;
import com.zdsoft.finance.finance.entity.RepaymentAmountAllot;
import com.zdsoft.finance.finance.service.CustomerReceivableService;
import com.zdsoft.finance.finance.service.RepaymentAmountAllotService;
import com.zdsoft.finance.loan.entity.LoanApply;
import com.zdsoft.finance.loan.entity.LoanRecord;
import com.zdsoft.finance.loan.repository.LoanApplyRepository;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.entity.CaseApplyStageEnumSimpleCodeEnum;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveManage;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveRemission;
import com.zdsoft.finance.specialapprove.service.SpecialApproveManageService;
import com.zdsoft.finance.specialapprove.service.SpecialApproveRemissionService;
import com.zdsoft.finance.specialapprove.vo.RemissionBasicVo;
import com.zdsoft.finance.specialapprove.vo.RemissionItemVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;


/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: SpecialApproveRemissionController.java 
 * @ClassName: SpecialApproveRemissionController 
 * @Description: 费用减免特批controller
 * @author wangrongwei
 * @date 2017年2月27日 下午3:19:24 
 * @version V1.0 
 */ 
@Controller
@RequestMapping("/specialApproveRemission")
public class SpecialApproveRemissionController extends BaseController {

	@Autowired
	private SpecialApproveController specialApproveController;
	
	@Autowired
	private SpecialApproveRemissionService specialApproveRemissionService;
	
	@Autowired
	private CaseApplyService caseApplyService;
	
	@Autowired
	private SpecialApproveManageService specialApproveManageService;
	
	@Autowired
	private CaseApplyBeforeCustomerService CaseApplyBeforeCustomerService;
	
	@Autowired
	private ReceivablePlanService receivablePlanService;
	
	@Autowired
	private RepaymentAmountAllotService repaymentAmountAllotService;
	
	@Autowired
	private LoanApplyRepository loanApplyRepository;
	
	@Autowired
	private CustomerReceivableService customerReceivableService;
	
	/** 
	 * @Title: specialApproveRemissionProcess 
	 * @Description: 费用减免特批审批页面
	 * @author wangrongwei
	 * @param model
	 * @param caseApplyId 案件ID
	 * @param businessKey 业务ID
	 * @return  
	 */ 
	@RequestMapping("/specialApproveRemissionProcess")
	@UriKey(key = "com.zdsoft.finance.specialApprove.specialApproveRemissionProcess")
	@ManualJob(label="费用减免特批审批单",resource="com.zdsoft.finance.specialApprove.specialApproveRemissionProcess")
	@FinishJob(resource = "com.zdsoft.finance.specialApprove.specialApproveRemissionProcess", businessId = "businessKey", projectId = "caseApplyId")
	public ModelAndView specialApproveRemissionProcess(ModelMap model,@RequestParam("projectId")String caseApplyId,String businessKey) {
		logger.info("费用减免特批审批，查询基本信息，案件ID为：" + caseApplyId);
		model.put("caseApplyId", caseApplyId);
		try {
			SpecialApproveManage approveManage = specialApproveManageService.findOne(businessKey);
			BusiForm busiForm = approveManage.getBusiForm();
			//初始化费用减免特批申请
			RemissionBasicVo rbVo = this.queryRemissionBasicInfo(caseApplyId, busiForm);
			FileStoreVo fileStoreVo = specialApproveController.queryAttaParam(caseApplyId, busiForm.getBusinessEntityId(),
					CaseApplyStageEnumSimpleCodeEnum.SPECIAL_APPROVE.value.substring(8));
			model.put("remissionBasicVo", rbVo);//费用减免基本信息
			model.put("fileStoreVo", fileStoreVo);//附件参数
			model.put("remark", approveManage.getRemark());
			model.put("penaltyUseStandard", approveManage.getPenaltyUseStandard());
			model.put("specialApproveManageId", busiForm.getBusinessEntityId());//特批管理ID
		} catch (Exception e) {
			logger.error("错误",e);
			e.printStackTrace();
		}
		return new ModelAndView("specialapprove/remission_special_approve_process_edit", model);
	}
	
	/** 
	 * @Title: specialApproveRemissionProcessView 
	 * @Description: 费用减免特批审批单查看
	 * @author wangrongwei
	 * @param model
	 * @param caseApplyId 案件ID
	 * @param businessKey 业务ID
	 * @return  
	 */ 
	@RequestMapping("/specialApproveRemissionProcessView")
	@UriKey(key = "com.zdsoft.finance.specialApprove.specialApproveRemissionProcessView")
	@ManualJob(label="费用减免特批审批单查看",resource="com.zdsoft.finance.specialApprove.specialApproveRemissionProcessView")
	@FinishJob(resource = "com.zdsoft.finance.specialApprove.specialApproveRemissionProcessView", businessId = "businessKey", projectId = "caseApplyId")
	public ModelAndView specialApproveRemissionProcessView(ModelMap model,@RequestParam("projectId")String caseApplyId,String businessKey) {
		logger.info("费用减免特批审批，查询基本信息，案件ID为：" + caseApplyId);
		model.put("caseApplyId", caseApplyId);
		try {
			SpecialApproveManage approveManage = specialApproveManageService.findOne(businessKey);
			BusiForm busiForm = approveManage.getBusiForm();
			//初始化费用减免特批申请
			RemissionBasicVo rbVo = this.queryRemissionBasicInfo(caseApplyId, busiForm);
			FileStoreVo fileStoreVo = specialApproveController.queryAttaParam(caseApplyId, busiForm.getBusinessEntityId(),
					CaseApplyStageEnumSimpleCodeEnum.SPECIAL_APPROVE.value.substring(8));
			model.put("remissionBasicVo", rbVo);//费用减免基本信息
			model.put("fileStoreVo", fileStoreVo);//附件参数
			model.put("remark", approveManage.getRemark());
			model.put("penaltyUseStandard", approveManage.getPenaltyUseStandard());
			model.put("specialApproveManageId", busiForm.getBusinessEntityId());//特批管理ID
		} catch (Exception e) {
			logger.error("错误",e);
			e.printStackTrace();
		}
		return new ModelAndView("specialapprove/remission_special_approve_process_view", model);
	}
	
	
	/** 
	 * @Title: getReceiveAmount 
	 * @Description: 查询当期应收金额
	 * @author wangrongwei
	 * @param amountType 金额类型（罚息/违约金）
	 * @param penaltyUseStandard 罚息挂钩标准 (0：当期本金，1：剩余本金)
	 * @param jsoncallback
	 * @return  
	 */ 
	@RequestMapping("/getReceiveAmount")
	@UriKey(key="com.zdsoft.finance.specialApprove.getReceiveAmount")
	@ResponseBody
	public String getReceiveAmount(String caseApplyId,String amountTypeCode,String penaltyUseStandard,String jsoncallback){
		ResponseMsg msg=new ResponseMsg();
		Map<String, Object> map = new HashMap<>();
		try {
			BigDecimal receiveAmount = BigDecimal.ZERO;
			String repaymentAmountId = "";
			//查询当期应收金额
			ReceivablePlan plan = receivablePlanService.queryByCaseApplyIdAndPlanDate(caseApplyId, Long.valueOf((DateUtil.getCurrentDate()+"").substring(0, 8)));
			if (ObjectHelper.isNotEmpty(plan)) {
				List<RepaymentAmountAllot> allotList = repaymentAmountAllotService.findByPlanId(plan.getId());
				RepaymentAmountAllot allot = null;
				if (ObjectHelper.isNotEmpty(allotList) && allotList.size() > 0) {
					allot = allotList.get(0);
				}
				
				if (ObjectHelper.isNotEmpty(allot)) {
					repaymentAmountId = allot.getId();
					if (SpecialApproveManage.AMOUNT_TYPE_PENALTY.equals(amountTypeCode)) {
						if ("0".equals(penaltyUseStandard)) {
							receiveAmount = allot.getDcurrentPlanPenalty();//当期本金产生的罚息
						}else if("1".equals(penaltyUseStandard)){
							receiveAmount = allot.getDplanPenalty();//剩余本金产生的罚息
						}
					}else if (SpecialApproveManage.AMOUNT_TYPE_PLANDAMAGES.equals(amountTypeCode)) {
						receiveAmount = allot.getDplanDamages(); //当期违约金
					}
				}
				map.put("receiveAmount", receiveAmount);
				map.put("repaymentAmountId", repaymentAmountId);
				msg.setOptional(map);
			}else{
				msg.setMsg("案件当期没有应还，不能发起减免");
			}
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			logger.error("错误",e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("查询失败" + e.getMessage());
			e.printStackTrace(); 
		}
		return ObjectHelper.objectToJson(msg,jsoncallback);
	}
	
	/** 
	 * @Title: queryRemissionItemDetail 
	 * @Description: 查询费用减免特批项
	 * @author wangrongwei
	 * @param specialApproveManageId 特批管理ID
	 * @param jsoncallback
	 * @return  
	 */ 
	@RequestMapping("/queryRemissionItemDetail")
	@UriKey(key = "com.zdsoft.finance.specialApprove.queryRemissionItemDetail")
	@ResponseBody
	public String queryRemissionItemDetail(String specialApproveManageId,String jsoncallback){
		ResponseMsg msg = new ResponseMsg();
		try {
			if (ObjectHelper.isEmpty(specialApproveManageId)) {
				msg.setResultStatus(ResponseMsg.APP_ERROR);
				msg.setMsg("特批管理ID为空");
			}else{
				SpecialApproveManage approveManage = specialApproveManageService.findOne(specialApproveManageId);
				List<SpecialApproveRemission> list = approveManage.getListFeeRemission();
				List<RemissionItemVo> listItemVo = new ArrayList<>();
				for (SpecialApproveRemission specialApproveRemission : list) {
					RemissionItemVo vo = new RemissionItemVo(specialApproveRemission);
					listItemVo.add(vo);
				}
				msg.setRows(listItemVo);
				msg.setResultStatus(ResponseMsg.SUCCESS);
				msg.setMsg("查询成功");
			}
		} catch (BusinessException e) {
			logger.error("错误",e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("操作失败");
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(msg,jsoncallback);
	}
	
	/** 
	 * @Title: specialApproveRemissionEdit 
	 * @Description: 费用减免特批编辑
	 * @author wangrongwei
	 * @param model
	 * @param caseApplyId 案件ID
	 * @param businessKey 业务ID
	 * @return  
	 */ 
	@RequestMapping("/specialApproveRemissionEdit")
	@UriKey(key = "com.zdsoft.finance.specialApprove.specialApproveRemissionEdit")
	public ModelAndView specialApproveRemissionEdit(ModelMap model,@RequestParam("projectId")String caseApplyId,String businessKey) {
		logger.info("费用减免特批申请，查询基本信息，案件ID为：" + caseApplyId);
		model.put("caseApplyId", caseApplyId);
		try {
			SpecialApproveManage approveManage = specialApproveManageService.findOne(businessKey);
			BusiForm busiForm = approveManage.getBusiForm();
			//初始化费用减免特批申请
			RemissionBasicVo rbVo = this.queryRemissionBasicInfo(caseApplyId, busiForm);
			FileStoreVo fileStoreVo = specialApproveController.queryAttaParam(caseApplyId, busiForm.getBusinessEntityId(),
					CaseApplyStageEnumSimpleCodeEnum.SPECIAL_APPROVE.value.substring(8));
			model.put("remissionBasicVo", rbVo);//费用减免基本信息
			model.put("fileStoreVo", fileStoreVo);//附件参数
			model.put("remark", approveManage.getRemark());
			model.put("penaltyUseStandard", approveManage.getPenaltyUseStandard());
			model.put("specialApproveManageId", busiForm.getBusinessEntityId());//特批管理ID
		} catch (Exception e) {
			logger.error("错误",e);
			e.printStackTrace();
		}
		return new ModelAndView("specialapprove/remission_special_approve_apply", model);
	}
	
	/** 
	 * @Title: specialApproveRemissionView 
	 * @Description: 费用减免特批查看
	 * @author wangrongwei
	 * @param model
	 * @param caseApplyId 案件ID
	 * @param businessKey 业务ID
	 * @return  
	 */ 
	@RequestMapping("/specialApproveRemissionView")
	@UriKey(key = "com.zdsoft.finance.specialApprove.specialApproveRemissionView")
	public ModelAndView specialApproveRemissionView(ModelMap model,@RequestParam("projectId")String caseApplyId,String businessKey) {
		logger.info("费用减免特批申请，查询基本信息，案件ID为：" + caseApplyId);
		model.put("caseApplyId", caseApplyId);
		try {
			SpecialApproveManage approveManage = specialApproveManageService.findOne(businessKey);
			BusiForm busiForm = approveManage.getBusiForm();
			RemissionBasicVo rbVo = this.queryRemissionBasicInfo(caseApplyId, busiForm);
			FileStoreVo fileStoreVo = specialApproveController.queryAttaParam(caseApplyId, busiForm.getBusinessEntityId(),
					CaseApplyStageEnumSimpleCodeEnum.SPECIAL_APPROVE.value.substring(8));
			model.put("remissionBasicVo", rbVo);//费用减免基本信息
			model.put("fileStoreVo", fileStoreVo);//附件参数
			model.put("remark", approveManage.getRemark());
			model.put("penaltyUseStandard", approveManage.getPenaltyUseStandard());
			model.put("specialApproveManageId", busiForm.getBusinessEntityId());//特批管理ID
		} catch (Exception e) {
			logger.error("错误",e);
			e.printStackTrace();
		}
		return new ModelAndView("specialapprove/remission_special_approve_view", model);
	}
	
	/** 
	 * @Title: pageSpecialApproveRemissionApply 
	 * @Description: 费用减免特批申请
	 * @author wangrongwei
	 * @param model 
	 * @param caseApplyId 案件ID
	 * @return  
	 */ 
	@RequestMapping("/pageSpecialApproveRemissionApply")
	@UriKey(key = "com.zdsoft.finance.specialApprove.pageSpecialApproveRemissionApply")
	public ModelAndView pageSpecialApproveRemissionApply(ModelMap model, String caseApplyId) {
		logger.info("费用减免特批申请，查询基本信息，案件ID为：" + caseApplyId);
		model.put("caseApplyId", caseApplyId);
		try {
			//查询草稿状态的减免特批
			SpecialApproveManage approveManage = specialApproveManageService.findByCaseApplyIdAndSpecialApproveTypeAndSpecialApproveStatus(caseApplyId, 4, SpecialApproveManage.SPECIAL_APPROVE_STATUS_DRAFT);
			if (ObjectHelper.isEmpty(approveManage)) {
				//初始化费用减免特批申请
				BusiForm busiForm = specialApproveRemissionService.saveOrCommitSpecialApproveRemissionApply(caseApplyId, "", "","",null, false);
				RemissionBasicVo rbVo = this.queryRemissionBasicInfo(caseApplyId, busiForm);
				FileStoreVo fileStoreVo = specialApproveController.queryAttaParam(caseApplyId, busiForm.getBusinessEntityId(),
						CaseApplyStageEnumSimpleCodeEnum.SPECIAL_APPROVE.value.substring(8));
				model.put("remissionBasicVo", rbVo);//费用减免基本信息
				model.put("fileStoreVo", fileStoreVo);//附件参数
				model.put("specialApproveManageId", busiForm.getBusinessEntityId());//特批管理ID
			}else{
				BusiForm busiForm = approveManage.getBusiForm();
				RemissionBasicVo rbVo = this.queryRemissionBasicInfo(caseApplyId, busiForm);
				FileStoreVo fileStoreVo = specialApproveController.queryAttaParam(caseApplyId, busiForm.getBusinessEntityId(),
						CaseApplyStageEnumSimpleCodeEnum.SPECIAL_APPROVE.value.substring(8));
				model.put("remissionBasicVo", rbVo);//费用减免基本信息
				model.put("fileStoreVo", fileStoreVo);//附件参数
				model.put("remark", approveManage.getRemark());
				model.put("penaltyUseStandard", approveManage.getPenaltyUseStandard());
				model.put("specialApproveManageId", busiForm.getBusinessEntityId());//特批管理ID
			}
		} catch (Exception e) {
			logger.error("操作错误",e);
			e.printStackTrace();
		}
		return new ModelAndView("specialapprove/remission_special_approve_apply", model);
	}
	
	/** 
	 * @Title: queryRemissionBasicInfo 
	 * @Description: 获取费用减免特批基本信息
	 * @author wangrongwei
	 * @param caseApplyId 案件ID
	 * @param busiForm 业务表单
	 * @return
	 * @throws BusinessException  
	 */ 
	private RemissionBasicVo queryRemissionBasicInfo(String caseApplyId,BusiForm busiForm) throws BusinessException{
		if (ObjectHelper.isEmpty(caseApplyId)) {
			throw new BusinessException("案件ID为空");
		}
		RemissionBasicVo rbVo=new RemissionBasicVo();
		SpecialApproveManage approveManage = specialApproveManageService.findOne(busiForm.getBusinessEntityId());
		//查询放款日期
		List<LoanApply> loanList = loanApplyRepository.findByCaseApplyId(caseApplyId);
		if (ObjectHelper.isNotEmpty(loanList)) {
			Set<LoanRecord> set = loanList.get(0).getLoanRecords();
			if (ObjectHelper.isNotEmpty(set)) {
				while (set.iterator().hasNext()) {
					rbVo.setLoanDate(DateHelper.longToDate(set.iterator().next().getActualDate(), "yyyy-MM-dd"));//放款日期
					break;
				}
			}
		}
		//查询本次还款日期
		ReceivablePlan plan = receivablePlanService.queryByCaseApplyIdAndPlanDate(caseApplyId, Long.valueOf((DateUtil.getCurrentDate()+"").substring(0,8)));
		if (ObjectHelper.isNotEmpty(plan)) {
			List<RepaymentAmountAllot> allotList = repaymentAmountAllotService.findByPlanId(plan.getId());
			if (ObjectHelper.isNotEmpty(allotList)) {
				while (allotList.iterator().hasNext()) {
					rbVo.setCurrentReceiveDate(DateHelper.longToDate(allotList.iterator().next().getPaidRepayDate(), "yyyy-MM-dd"));
					break;
				}
			}
		}
		//逾期天数
		CustomerReceivable customerReceivable = customerReceivableService.findByCaseApplyIdAndIsEffect(caseApplyId, true);
		if (ObjectHelper.isNotEmpty(customerReceivable)) {
			rbVo.setOverdueDays(customerReceivable.getDays());
		}
		//查询减免特批基本信息
		CaseApply caseApply = caseApplyService.findOne(caseApplyId);
		rbVo.setLoanAmount(caseApply.getLoanApplyAnount());//放款金额
		rbVo.setApplyDate(DateHelper.longToDate(busiForm.getApplyDate(), "yyyy-MM-dd"));//申请日期
		rbVo.setApplyDeptName(approveManage.getCreateOrgName());//部门名称
		rbVo.setApplyUserName(approveManage.getCreateByName());//申请人
		rbVo.setCaseApplyCode(caseApply.getCaseApplyCode());//案件编号
		rbVo.setRemissionCode(busiForm.getBusinessCode());//编号
		//rbVo.setCurrentReceiveAmount(caseApply.getCaseApplyBalance());
		rbVo.setPrincipalBalance(caseApply.getCaseApplyBalance());//本金余额
		rbVo.setProductSubtypeName(caseApply.getProductSubtypeName());//产品
		rbVo.setPenaltyUseStandard(approveManage.getPenaltyUseStandard());//罚息挂钩标准
		rbVo.setOrgName(approveManage.getCompanyName());//机构名称
		
		List<BeforeCustomer> list=CaseApplyBeforeCustomerService.findCustomerByCaseApplyIdAndJoinType(caseApplyId, CaseApplyBeforeCustomer.MAIN_BORROW);
		if (ObjectHelper.isNotEmpty(list)) {
			rbVo.setMainBorrowName(list.get(0).getCustomerName());//主借人
		}
		return rbVo;
	}
	
	/** 
	 * @Title: saveSpecialApproveRemissionApply 
	 * @Description: 保存费用减免特批申请
	 * @author wangrongwei
	 * @param caseApplyId 案件ID
	 * @param specialApproveManageId
	 * @param remark 备注
	 * @param isSubmit 是否提交
	 * @param penaltyUseStandard 罚息挂钩标准
	 * @param jsoncallback
	 * @param remissionDetail 费用减免项
	 * @return  
	 */ 
	@SuppressWarnings("unchecked")
	@RequestMapping("/saveSpecialApproveRemissionApply")
	@UriKey(key="com.zdsoft.finance.specialApprove.saveSpecialApproveRemissionApply")
	@ResponseBody
	public String saveSpecialApproveRemissionApply(String caseApplyId,String specialApproveManageId,String remark,Boolean isSubmit,
			String penaltyUseStandard, String jsoncallback,String remissionDetail){
		ResponseMsg msg=new ResponseMsg();
		try {
			JSONArray jsonArray = JSONArray.fromObject(remissionDetail);
			List<SpecialApproveRemission> remissionlList = JSONArray.toList(jsonArray, new SpecialApproveRemission(),new JsonConfig());
			
			BusiForm busiForm = specialApproveRemissionService.saveOrCommitSpecialApproveRemissionApply(caseApplyId, specialApproveManageId, remark,penaltyUseStandard,remissionlList, isSubmit);
			if (ObjectHelper.isNotEmpty(busiForm)) {
				msg.setResultStatus(ResponseMsg.SUCCESS);
				if (isSubmit) {
					msg.setMsg("下一节点处理人：" + busiForm.getCurrentDealEmpNm());
				}else{
					msg.setMsg("保存成功");
				}
				Map<String, Object> map =new HashMap<>();
				map.put("specialApproveManageId", busiForm.getBusinessEntityId());
				msg.setOptional(map);
			}else {
				msg.setMsg("操作失败");
				msg.setResultStatus(ResponseMsg.APP_ERROR);
			}
		} catch (Exception e) {
			logger.info("保存自由还款特批出错 原因：" + e.getMessage());
			msg.setMsg("操作失败： " + e.getMessage());
			logger.error("错误",e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
}
