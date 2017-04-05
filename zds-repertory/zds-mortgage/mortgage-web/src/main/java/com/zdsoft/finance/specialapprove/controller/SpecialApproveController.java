package com.zdsoft.finance.specialapprove.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.zdsoft.bpm.annotation.client.FinishJob;
import com.zdsoft.bpm.annotation.client.ManualJob;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.basedata.SimpleCodeDto;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.businesssetting.entity.ExceptMatter;
import com.zdsoft.finance.businesssetting.service.ExceptMatterService;
import com.zdsoft.finance.casemanage.datasurvey.controller.ReceivablePlanController;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.RateUtil;
import com.zdsoft.finance.cooperator.entity.CooperatorTerminal;
import com.zdsoft.finance.cooperator.service.CapitalistService;
import com.zdsoft.finance.cooperator.service.CooperatorTerminalService;
import com.zdsoft.finance.cooperator.vo.CooperatorTerminalVo;
import com.zdsoft.finance.filestore.vo.FileStoreVo;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyStageEnumSimpleCodeEnum;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;
import com.zdsoft.finance.marketing.vo.HousePropertyVo;
import com.zdsoft.finance.product.entity.ProductRate;
import com.zdsoft.finance.product.service.ProductRateService;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveManage;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveThings;
import com.zdsoft.finance.specialapprove.service.SpecialApproveManageService;
import com.zdsoft.finance.specialapprove.service.SpecialApproveThingsService;
import com.zdsoft.finance.specialapprove.vo.RiskItemVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: SpecialApproveController.java
 * @ClassName: SpecialApproveController
 * @Description: 风险特批controller
 * @author wangrongwei
 * @date 2017年2月14日 下午3:25:52
 * @version V1.0
 */
@Controller
@RequestMapping("/specialApprove")
public class SpecialApproveController extends BaseController {

	@Autowired
	private CaseApplyService caseApplyService;

	@Autowired
	private CooperatorTerminalService cooperatorTerminalService;

	@Autowired
	private HousePropertyService housePropertyService;

	@Autowired
	private CED ced;

	@Autowired
	private SpecialApproveThingsService specialApproveThingsService;

	@Autowired
	private SpecialApproveManageService specialApproveManageService;
	
	@Autowired
	private ExceptMatterService exceptMatterService;
	
	@Autowired
	private CapitalistService capitalistService;
	
	@Autowired
	private ProductRateService productRateService;
	
	@Autowired
	private ReceivablePlanController specialApproveController;
	
	/** 
	 * @Title: validateSysSpecProcessStatus 
	 * @Description: 验证是否存在可特批的系统触发事项
	 * @author wangrongwei
	 * @param caseApplyId 案件ID
	 * @param specialApproveType 特批类型
	 * @return  
	 */ 
	@ResponseBody
	@RequestMapping("/validateSysSpecProcessStatus")
	@UriKey(key="com.zdsoft.finance.specialApprove.validateSysSpecProcessStatus")
	public ResponseMsg validateSysSpecProcessStatus(String caseApplyId,String specialApproveType){
		ResponseMsg msg = new ResponseMsg();
		Boolean status;
		try {
			status = specialApproveManageService.validateSysSpecProcessStatus(caseApplyId, specialApproveType);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			Map<String, Object> map = new HashMap<>();
			map.put("status", status);
			msg.setOptional(map);
			msg.setMsg("查询成功");
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e.getMessage());
			logger.error("验证是否存在可特批的系统触发事项失败 ：",e);
			e.printStackTrace();
		}
		return msg;
	}
	
	/** 
	 * @Title: specialApproveAbandoned 
	 * @Description: 特批废弃
	 * @author wangrongwei
	 * @param caseApplyId 案件ID
	 * @param businessKey 业务ID
	 */ 
	@RequestMapping("/specialApproveAbandoned")
	@UriKey(key="com.zdsoft.finance.specialApprove.specialApproveAbandoned")
	@ResponseBody
	public ResponseMsg specialApproveAbandoned(String businessKey){
		ResponseMsg msg = new ResponseMsg();
		if (ObjectHelper.isNotEmpty(businessKey)) {
			try {
				specialApproveManageService.specialApproveAbandoned(businessKey);
				msg.setResultStatus(ResponseMsg.SUCCESS);
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("废弃申请发生异常！");
				msg.setResultStatus(ResponseMsg.APP_ERROR);
				msg.setMsg(e.getMessage());
			}
		}
		return msg;
	}
	
	/**
	 * @Title: initSpecialApprove
	 * @Description: 初始化特批管理（页面跳转）
	 * @author wangrongwei
	 * @return
	 */
	@RequestMapping("/init")
	@UriKey(key = "com.zdsoft.finance.specialApprove.initSpecialApprove")
	@Menu(resource = "com.zdsoft.finance.specialApprove.initSpecialApprove", group = "exceptmanage", label = "案件特批管理", order = 1)
	public ModelAndView initSpecialApprove() {
		return new ModelAndView("specialapprove/special_approve_manage_list");
	}

	/**
	 * @Title: querySpecialApproveList
	 * @Description: 查询案件特批管理列表
	 * @author wangrongwei
	 * @param pageable 分页参数
	 * @param jsoncallback 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/querySpecialApproveList")
	@ResponseBody
	@UriKey(key = "com.zdsoft.finance.specialApprove.querySpecialApproveList")
	public String querySpecialApproveList(HttpServletRequest request, PageRequest pageable, String jsoncallback) {
		List<QueryObj> list = (List<QueryObj>) request.getAttribute("listObj");
		Page<Map<String, Object>> page = null;
		ResponseMsg msg = new ResponseMsg();
		try {
			logger.info("查询特批列表...");
			page = caseApplyService.findPageCaseSpecialApprove(pageable, list);
			msg.setRows(page.getRecords());
			msg.setTotal(page.getTotalRows());
			msg.setMsg("案件特批列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("案件特批列表查询失败");
			logger.info("查询特批列表出错... " + e.getMessage());
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/** 
	 * @Title: pageSpecialApproveRiskApply 
	 * @Description: 手动触发风险特批申请
	 * @author wangrongwei
	 * @param model
	 * @param caseApplyId 案件ID
	 * @return  
	 */ 
	@RequestMapping("/pageSpecialApproveRiskApply")
	@UriKey(key = "com.zdsoft.finance.specialApprove.pageSpecialApproveRiskApply")
	public ModelAndView pageSpecialApproveRiskApply(ModelMap model, String caseApplyId) {
		try {
			//查询状态为草稿的风险特批管理记录
			SpecialApproveManage specialApproveManage = specialApproveManageService.findByCaseApplyIdAndSpecialApproveTypeAndSpecialApproveStatus(caseApplyId, 1, SpecialApproveManage.SPECIAL_APPROVE_STATUS_DRAFT);
			if (ObjectHelper.isEmpty(specialApproveManage)) {
				// 查询特批事项分类
				logger.info("查询所有风险特批事项");
				List<SimpleCodeDto> simplecodeList = ced.querySimpleCodeByCategoryId(SpecialApproveThings.THINGS_TYPE_CODE);
				List<RiskItemVo> itemList = new ArrayList<>();
				for (SimpleCodeDto simpleCodeDto : simplecodeList) {
					// 查询风险特批事项
					List<ExceptMatter> riskItemList = exceptMatterService
							.findByExceptMatterType(simpleCodeDto.getFullCode());
					RiskItemVo riskItemVo = new RiskItemVo(simpleCodeDto.getFullCode(), simpleCodeDto.getName(),
							riskItemList, null);
					itemList.add(riskItemVo);
				}
				//初始化申请信息
				BusiForm busiForm = specialApproveThingsService.saveOrCommitRiskSpecialApproveApply(new HashMap<>(), false, caseApplyId, "", "", false,null);
				FileStoreVo fileStoreVo = this.queryAttaParam(caseApplyId, busiForm.getBusinessEntityId(),
						CaseApplyStageEnumSimpleCodeEnum.SPECIAL_APPROVE.value.substring(8));
				model.put("fileStoreVo", fileStoreVo);
				model.put("itemList", itemList);
				model.put("caseApplyId", caseApplyId);
				model.put("businessKey", busiForm.getBusinessEntityId());
			}else {
				//查询风险特批（存在 草稿状态的风险特批）
				return this.queryRiskApprove(model, specialApproveManage, caseApplyId);
			}
		} catch (Exception e) {
			logger.error("风险特批，查询所有风险特批事项失败");
			logger.error("风险特批审批",e);
			e.printStackTrace();
		}
		return new ModelAndView("specialapprove/risk_special_approve_apply", model);
	}
	
	/** 
	 * @Title: queryRiskApprove 
	 * @Description: 查询风险特批
	 * @author wangrongwei
	 * @param model
	 * @param specialApproveManage 特批管理
	 * @param caseApplyId 案件ID
	 * @return
	 * @throws Exception  
	 */ 
	public ModelAndView queryRiskApprove(ModelMap model,SpecialApproveManage specialApproveManage,String caseApplyId) throws Exception{
		List<RiskItemVo> itemList = null;
		String viewName = "";
		// 查询风险特批管理
		FileStoreVo fileStoreVo = this.queryAttaParam(caseApplyId, specialApproveManage.getId(),
				CaseApplyStageEnumSimpleCodeEnum.SPECIAL_APPROVE.value.substring(8));
		model.put("fileStoreVo", fileStoreVo);
		
		if (specialApproveManage.isSystem()) {//系统触发的风险规则
			model.put("process", false);
			viewName = "specialapprove/system_risk_special_approve";
		}else{
			viewName = "specialapprove/risk_special_approve_apply";
		}
		
		//人工触发的风险规则
		itemList = new ArrayList<>();
		List<SpecialApproveThings> list = specialApproveManage.getListSpecialApproveThings();
		
		RiskItemVo vo = null;
		// 查询所有风险项类别
		List<SimpleCodeDto> simplecodeList = ced.querySimpleCodeByCategoryId(SpecialApproveThings.THINGS_TYPE_CODE);
		for (SimpleCodeDto simpleCodeDto : simplecodeList) {
			vo = new RiskItemVo();
			vo.setItemType(simpleCodeDto.getFullCode());
			vo.setTypeName(simpleCodeDto.getName());
			
			// 查询风险特批事项
			List<ExceptMatter> riskItemList = exceptMatterService.findByExceptMatterType(simpleCodeDto.getFullCode());
			vo.setList(riskItemList);
			List<SpecialApproveThings> satList = new ArrayList<>();
			for (SpecialApproveThings specialApproveThings : list) {
				if (vo.getItemType().equals(specialApproveThings.getItemType())) {
					satList.add(specialApproveThings);
				}
				if("TPDM000006".equals(specialApproveThings.getItemCode())){
					model.put("otherInfo", specialApproveThings.getOtherInfo());
				}
			}
			if (ObjectHelper.isNotEmpty(satList)) {
				vo.setListThing(satList);
			}
			itemList.add(vo);
		}
		model.put("itemList", itemList);
		model.put("caseApplyId", caseApplyId);
		model.put("remark", specialApproveManage.getRemark());
		model.put("businessKey", specialApproveManage.getId());
		return new ModelAndView(viewName, model);
	}
	
	/** 
	 * @Title: queryAttaParam 
	 * @Description: 获取附件参数
	 * @author wangrongwei
	 * @param caseApplyId 案件ID
	 * @param businessId 业务表单ID
	 * @param linkCode 资料查看权限CODE
	 * @return
	 * @throws BusinessException  
	 */ 
	public FileStoreVo queryAttaParam(String caseApplyId,String businessId,String linkCode) throws BusinessException{
		FileStoreVo vo = new FileStoreVo();
		CaseApply caseApply = caseApplyService.findOne(caseApplyId);
		vo.setCaseApplyId(caseApply.getCaseApplyCode());
		vo.setProductId(caseApply.getProductSubtypeId());
		vo.setBusinessId(businessId);
		vo.setLinkCode(linkCode);
		return vo;
	}
	
	/** 
	 * @Title: pageSpecialApproveRiskSysApply 
	 * @Description: 系统触发风险特批
	 * @author wangrongwei
	 * @param model
	 * @param caseApplyId 案件ID
	 * @return  
	 */ 
	@RequestMapping("/pageSpecialApproveRiskSysApply")
	@UriKey(key = "com.zdsoft.finance.specialApprove.pageSpecialApproveRiskSysApply")
	public ModelAndView pageSpecialApproveRiskSysApply(ModelMap model, String caseApplyId) {
		try {
			SpecialApproveManage specialApproveManage = specialApproveManageService.findByCaseApplyIdAndSpecialApproveTypeAndSpecialApproveStatus(caseApplyId, 1, SpecialApproveManage.SPECIAL_APPROVE_STATUS_DRAFT_SYS);
			if (ObjectHelper.isEmpty(specialApproveManage)) {
				//初始化申请信息
				BusiForm busiForm = specialApproveThingsService.saveOrCommitRiskSpecialApproveApply(null, false, caseApplyId, "", "", true,null);
				specialApproveManage = specialApproveManageService.findOne(busiForm.getBusinessEntityId());
			}
			//查询风险特批（存在 草稿状态的风险特批）
			return this.queryRiskApprove(model, specialApproveManage, caseApplyId);
			
		} catch (Exception e) {
			logger.error("风险特批，通过案件ID查询系统触发的风险规则失败");
			logger.error("风险特批审批",e);
			e.printStackTrace();
		}
		return new ModelAndView("specialapprove/system_risk_special_approve", model);
	}

	/**
	 * @Title: publicCaseApplyBasicInfo
	 * @Description: 查询案件基本信息
	 * @author wangrongwei
	 * @param caseApplyId
	 *            案件ID
	 * @param type
	 *            查询类型（0：案件基本信息，1：押品信息）
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/publicCaseApplyBasicInfo")
	@UriKey(key = "com.zdsoft.finance.specialApprove.publicCaseApplyBasicInfo")
	@ResponseBody
	public String publicCaseApplyBasicInfo(String caseApplyId, String type, String jsoncallback) {
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> map = new HashMap<>();
		// 查询案件基本信息
		try {
			if (ObjectHelper.isEmpty(caseApplyId)) {
				msg.setResultStatus(ResponseMsg.APP_ERROR);
				msg.setMsg("案件ID为空");
				return ObjectHelper.objectToJson(msg, jsoncallback);
			}
			if (ObjectHelper.isEmpty(type)) {
				msg.setResultStatus(ResponseMsg.APP_ERROR);
				msg.setMsg("查询类型为空");
				return ObjectHelper.objectToJson(msg, jsoncallback);
			}
			logger.info("风险特批申请》》》》   查询基本信息,案件ID为：" + caseApplyId + "----------查询类型为（0：案件基本信息，1：押品信息）：" + type);
			CaseApply basicInfo = caseApplyService.findOne(caseApplyId);
			if ("0".equals(type)) {
				if (ObjectHelper.isNotEmpty(basicInfo)) {
					// 基本信息
					if (ObjectHelper.isNotEmpty(basicInfo)) {
						CaseApplyVo basicInfoVo = new CaseApplyVo(basicInfo);
						String applyTermUnit = basicInfoVo.getApplyTermUnit();
						if (applyTermUnit.equals(CaseApply.DATEUNIT_YEAR)) {//年
							basicInfoVo.setApplyTerm(basicInfoVo.getApplyTerm() * 12);
						}else if(applyTermUnit.equals(CaseApply.DATEUNIT_DAY)){//天
							basicInfoVo.setApplyTerm(basicInfoVo.getApplyTerm() / 30);
						}
						//贷款利率
						BigDecimal applyRate = basicInfoVo.getApplyRate();
						if (ObjectHelper.isEmpty(applyRate) || BigDecimal.ZERO.compareTo(applyRate) == 0) {
							//查询产品中配置的利率
							List<ProductRate> list = productRateService.findByProductId(basicInfoVo.getProductSubtypeId());
							if (ObjectHelper.isNotEmpty(list)) {
								int applyDate = specialApproveController.deadlineConversionDay(basicInfoVo.getApplyTermUnit(), basicInfoVo.getApplyTerm().longValue());
								for (ProductRate productRate : list) {
									int startDate = specialApproveController.deadlineConversionDay(productRate.getStartDateUnit(), productRate.getStartDate());
									int endDate = specialApproveController.deadlineConversionDay(productRate.getEndDateUnit(), productRate.getEndDate());
									if (applyDate >= startDate && applyDate <= endDate) {
										basicInfoVo.setApplyRate(RateUtil.percentRate3(BigDecimal.valueOf(productRate.getRate()), false));
										basicInfoVo.setApplyRateUnit(productRate.getRateUnit());
										basicInfoVo.setApplyRateUnitName(ced.loadSimpleCodeNameByFullCode(productRate.getRateUnit()));
										break;
									}
								}
							}
						}
						//查询资金来源名称
						if (ObjectHelper.isNotEmpty(basicInfo.getCapitalSource())) {
							basicInfoVo.setCapitalSourceName(capitalistService.findOne(basicInfo.getCapitalSource()).getCapitalName());
						}
						map.put("basicInfoVo", basicInfoVo);
					}

					// 查询终端
					CooperatorTerminal cooperatorTerminal = cooperatorTerminalService
							.findOne(basicInfo.getTerminalId());
					if (ObjectHelper.isNotEmpty(cooperatorTerminal)) {
						CooperatorTerminalVo cooperatorTerminalVo = new CooperatorTerminalVo(cooperatorTerminal);
						map.put("cooperatorTerminalVo", cooperatorTerminalVo);
					}
				}
				msg.setMsg("查询案件基本信息成功");
			} else if ("1".equals(type)) {
				// 押品信息
				List<HouseProperty> housePropertyList = housePropertyService.findByCaseApplyId(caseApplyId);
				logger.debug("风险特批申请》》》》   查询押品信息---------- 押品总数为：{}" , housePropertyList.size());
				// 押品信息
				if (ObjectHelper.isNotEmpty(housePropertyList) && housePropertyList.size() > 0) {
					List<HousePropertyVo> housePropertyVoList = new ArrayList<>();
					for (HouseProperty houseProperty : housePropertyList) {
						housePropertyVoList.add(new HousePropertyVo(houseProperty));
					}
					msg.setRows(housePropertyVoList);
					msg.setTotal((long) housePropertyVoList.size());
				}
				msg.setMsg("查询案件押品信息成功");
			}
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setOptional(map);

		} catch (Exception e) {
			logger.error("风险特批申请》》》》》》》》》 查询案件基本信息出错");
			logger.error("风险特批审批",e);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/** 
	 * @Title: saveOrCommitRiskSpecialApproveApply 
	 * @Description: 保存或提交风险特批申请
	 * @author wangrongwei
	 * @param remark
	 * @param rickItem 风险特批项
	 * @param submitStatus 是否提交
	 * @param caseApplyId 案件ID
	 * @param specialApproveManageId 特批管理ID
	 * @param isSystem 是否系统触发的风险特批
	 * @param jsoncallback 回调
	 * @return  
	 */ 
	@SuppressWarnings("unchecked")
	@RequestMapping("/saveOrCommitRiskSpecialApproveApply")
	@UriKey(key = "com.zdsoft.finance.specialApprove.saveOrCommitRiskSpecialApproveApply")
	@ResponseBody
	public String saveOrCommitRiskSpecialApproveApply(String remark, String rickItem, Boolean submitStatus,
			String caseApplyId, String specialApproveManageId,Boolean isSystem,String otherInfo, String jsoncallback) {
		ResponseMsg msg = new ResponseMsg();
		try {
			Gson gson = new Gson();
			Map<String, String> riskItemMap = new HashMap<>();
			riskItemMap = gson.fromJson(rickItem, riskItemMap.getClass());
			logger.info("开始保存或提交风险特批申请 .....");
			BusiForm busiForm = specialApproveThingsService.saveOrCommitRiskSpecialApproveApply(riskItemMap,
					submitStatus, caseApplyId, specialApproveManageId, remark,ObjectHelper.isEmpty(isSystem)?false:isSystem,otherInfo);
			logger.info("保存或提交风险特批申请 成功.....");
			Map<String, Object> map =new HashMap<>();
			map.put("specialApproveManageId", busiForm.getBusinessEntityId());
			msg.setOptional(map);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			if (submitStatus != null && submitStatus) {
				msg.setMsg("下一节点处理人：" + busiForm.getCurrentDealEmpNm());
			}else{
				msg.setMsg("保存成功");
			}
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("操作失败： " + e.getMessage());
			logger.error("保存或提交风险特批申请失败，原因：",e);
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/** 
	 * @Title: riskSpecialApproveExamination 
	 * @Description: 风险特批审批单
	 * @author wangrongwei
	 * @param caseApplyId 案件ID
	 * @param businessKey 业务ID
	 * @param model
	 * @return  
	 */ 
	@RequestMapping("/riskSpecialApproveExamination")
	@UriKey(key="com.zdsoft.finance.specialApprove.riskSpecialApproveExamination")
	@ManualJob(resource = "com.zdsoft.finance.specialApprove.riskSpecialApproveExamination", label = "风险特批审批单")
	@FinishJob(resource = "com.zdsoft.finance.specialApprove.riskSpecialApproveExamination", businessId = "businessKey", projectId = "caseApplyId")
	public ModelAndView riskSpecialApproveExamination(@RequestParam(value = "projectId") String caseApplyId,
			String businessKey, ModelMap model) {
		String view = "";
		try {
			SpecialApproveManage specialApproveManage = specialApproveManageService.findOne(businessKey);
			this.queryRiskApprove(model, specialApproveManage, caseApplyId);
			if (specialApproveManage.isSystem()) {
				model.put("process", true);
				view = "specialapprove/system_risk_special_approve";
			}else{
				view = "specialapprove/process_risk_special_approve_edit";
			}
		} catch (Exception e) {
			logger.error("风险特批，通过案件ID查询系统触发的风险规则失败");
			logger.error("风险特批审批",e);
			e.printStackTrace();
		}
		model.put("caseApplyId", caseApplyId);
		model.put("businessKey", businessKey);
		return new ModelAndView(view, model);
	}
	
	/** 
	 * @Title: riskSpecialApproveExaminationView 
	 * @Description: 风险特批审批单查看
	 * @author wangrongwei
	 * @param caseApplyId 案件ID
	 * @param businessKey 业务ID
	 * @param model
	 * @return  
	 */ 
	@RequestMapping("/riskSpecialApproveExaminationView")
	@UriKey(key="com.zdsoft.finance.specialApprove.riskSpecialApproveExaminationView")
	@ManualJob(resource = "com.zdsoft.finance.specialApprove.riskSpecialApproveExaminationView", label = "风险特批审批单查看")
	@FinishJob(resource = "com.zdsoft.finance.specialApprove.riskSpecialApproveExaminationView", businessId = "businessKey", projectId = "caseApplyId")
	public ModelAndView riskSpecialApproveExaminationView(@RequestParam(value = "projectId") String caseApplyId,
			String businessKey, ModelMap model) {
		SpecialApproveManage specialApproveManage = new SpecialApproveManage();
		try {
			// 查询风险特批管理
			specialApproveManage = specialApproveManageService.findOne(businessKey);
			this.queryRiskApprove(model, specialApproveManage, caseApplyId);
		} catch (Exception e) {
			logger.error("风险特批审批",e);
		}
		return new ModelAndView("specialapprove/process_risk_special_approve_view", model);
	}
	
	/**
	 * @Title: riskSpecialApproveEdit
	 * @Description: 风险特批编辑
	 * @author wangrongwei
	 * @param caseApplyId 案件ID
	 * @param businessKey 业务ID
	 * @return
	 */
	@RequestMapping("/riskSpecialApproveEdit")
	@UriKey(key="com.zdsoft.finance.specialApprove.riskSpecialApproveEdit")
	public ModelAndView riskSpecialApproveEdit(@RequestParam(value = "projectId") String caseApplyId,
			String businessKey, ModelMap model) {
		String viewName = "";
		SpecialApproveManage specialApproveManage = new SpecialApproveManage();
		List<RiskItemVo> itemList = null;
		try {
			//附件参数
			FileStoreVo fileStoreVo = this.queryAttaParam(caseApplyId, specialApproveManage.getId(),
					CaseApplyStageEnumSimpleCodeEnum.SPECIAL_APPROVE.value.substring(8));
			model.put("fileStoreVo", fileStoreVo);
			
			// 查询风险特批管理
			specialApproveManage = specialApproveManageService.findOne(businessKey);
			
			if (specialApproveManage.isSystem()) {//系统触发的风险规则
				viewName="specialapprove/system_risk_special_approve";
				model.put("process", false);
			}else{
				viewName="specialapprove/risk_special_approve_apply";
			}
			
			itemList = new ArrayList<>();
			List<SpecialApproveThings> list = specialApproveManage.getListSpecialApproveThings();
			
			RiskItemVo vo = null;
			// 查询所有风险项类别
			List<SimpleCodeDto> simplecodeList = ced.querySimpleCodeByCategoryId(SpecialApproveThings.THINGS_TYPE_CODE);
			for (SimpleCodeDto simpleCodeDto : simplecodeList) {
				vo = new RiskItemVo();
				vo.setItemType(simpleCodeDto.getFullCode());
				vo.setTypeName(simpleCodeDto.getName());
				
				// 查询风险特批事项
				List<ExceptMatter> riskItemList = exceptMatterService
						.findByExceptMatterType(simpleCodeDto.getFullCode());
				vo.setList(riskItemList);
				List<SpecialApproveThings> satList = new ArrayList<>();
				for (SpecialApproveThings specialApproveThings : list) {
					if (vo.getItemType().equals(specialApproveThings.getItemType())) {
						satList.add(specialApproveThings);
					}
					if("TPDM000006".equals(specialApproveThings.getItemCode())){
						model.put("otherInfo", specialApproveThings.getOtherInfo());
					}
				}
				if (ObjectHelper.isNotEmpty(satList)) {
					vo.setListThing(satList);
				}
				itemList.add(vo);
			}
		} catch (Exception e) {
			logger.error("风险特批审批",e);
		}
		
		model.put("itemList", itemList);
		model.put("caseApplyId", caseApplyId);
		model.put("remark", specialApproveManage.getRemark());
		model.put("businessKey", businessKey);
		return new ModelAndView(viewName, model);
	}
	
	/** 
	 * @Title: riskSpecialApproveView 
	 * @Description: 风险特批查看
	 * @author wangrongwei
	 * @param caseApplyId 案件ID
	 * @param businessKey 业务ID
	 * @param model
	 * @return  
	 */ 
	@RequestMapping("/riskSpecialApproveView")
	@UriKey(key="com.zdsoft.finance.specialApprove.riskSpecialApproveView")
	public ModelAndView riskSpecialApproveView(@RequestParam(value = "projectId") String caseApplyId,
			String businessKey, ModelMap model) {
		SpecialApproveManage specialApproveManage = new SpecialApproveManage();
		try {
			// 查询风险特批管理
			specialApproveManage = specialApproveManageService.findOne(businessKey);
			this.queryRiskApprove(model, specialApproveManage, caseApplyId);
		} catch (Exception e) {
			logger.error("风险特批查看",e);
		}
		
		return new ModelAndView("specialapprove/process_risk_special_approve_view", model);
	}
}
