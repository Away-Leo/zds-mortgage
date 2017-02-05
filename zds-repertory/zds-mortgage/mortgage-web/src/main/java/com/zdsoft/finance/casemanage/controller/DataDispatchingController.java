package com.zdsoft.finance.casemanage.controller;


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

import com.zdsoft.bpm.annotation.client.FinishJob;
import com.zdsoft.bpm.annotation.client.ManualJob;
import com.zdsoft.finance.casemanage.vo.CaseTaskVo;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.cooperator.entity.CooperatorTerminal;
import com.zdsoft.finance.cooperator.service.CooperatorTerminalService;
import com.zdsoft.finance.cooperator.vo.CooperatorTerminalVo;
import com.zdsoft.finance.customer.entity.BeforeAddress;
import com.zdsoft.finance.customer.entity.BeforeContact;
import com.zdsoft.finance.customer.entity.BeforePersonalAssociation;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.entity.BeforeWorkUnit;
import com.zdsoft.finance.customer.service.BeforeAddressService;
import com.zdsoft.finance.customer.service.BeforeContactService;
import com.zdsoft.finance.customer.service.BeforePersonalAssociationService;
import com.zdsoft.finance.customer.service.BeforePersonalCustomerService;
import com.zdsoft.finance.customer.service.BeforeWorkUnitService;
import com.zdsoft.finance.customer.vo.BeforeAddressVo;
import com.zdsoft.finance.customer.vo.BeforeContactVo;
import com.zdsoft.finance.customer.vo.BeforePersonalCustomerVo;
import com.zdsoft.finance.customer.vo.BeforeWorkUnitVo;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.entity.CaseTask;
import com.zdsoft.finance.marketing.entity.HouseAssessment;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.entity.PledgeInfo;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.service.CaseTaskService;
import com.zdsoft.finance.marketing.service.HouseAssessmentService;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.finance.marketing.service.PledgeInfoService;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;
import com.zdsoft.finance.marketing.vo.HouseAssessmentVo;
import com.zdsoft.finance.marketing.vo.HousePropertyVo;
import com.zdsoft.finance.marketing.vo.PledgeInfoVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/** 	
* 版权所有：重庆正大华日软件有限公司	
* @Title: DataDispatchingContro
import com.zdsoft.framework.cra.annotation.Menu;ller.java 	
* @Package com.zdsoft.finance.casemanage.controller
import com.zdsoft.framework.cra.annotation.Menu; 	
* @Description: 资调派单Controller	
* @author liuhuan 	
* @date 2017年1月13日 上午10:46:21 	
* @version V1.0 	
*/
@Controller
@RequestMapping("/casemanage/dataDispatching")
public class DataDispatchingController extends BaseController{
	
	@Autowired
	private CaseApplyService caseApplyService;
	
	@Autowired
	private CooperatorTerminalService cooperatorTerminalService;
	
	@Autowired
	private BeforePersonalCustomerService beforePersonalCustomerService;
	
	@Autowired
	private BeforeAddressService beforeAddressService;
	
	@Autowired
	private BeforeContactService beforeContactService;
	
	@Autowired
	private BeforeWorkUnitService beforeWorkUnitService;
	
	@Autowired
	private BeforePersonalAssociationService beforePersonalAssociationService;
	
	@Autowired
	private HousePropertyService housePropertyService;
	
	@Autowired
	private PledgeInfoService pledgeInfoService;
	
	@Autowired 
	private HouseAssessmentService houseAssessmentService;
	
	@Autowired
	private CaseApplyBeforeCustomerService caseApplyBeforeCustomerService;
	
	@Autowired
	private CaseTaskService caseTaskService;
	
	
	/**
	 * 资调派单
	 * @return
	 */
	@RequestMapping("/initDataDispatching")
	@UriKey(key = "com.zdsoft.finance.casemanage.dataDispatching.initDataDispatching")
	@ManualJob(resource = "com.zdsoft.finance.casemanage.initDataDispatching", label = "资调派单") 
//	@Menu(resource = "com.zdsoft.finance.casemanage.dataDispatching.initDataDispatching", label = "资调派单", group = "project", order = 1)
	public ModelAndView initDataDispatching(@RequestParam(value="projectId")String caseApplyId, String processInstanceId, String businessKey) {
		logger.info("----进入资调派单----");
		ModelMap model = new ModelMap();
		model.put("caseApplyId", caseApplyId);
		model.put("processInstanceId", processInstanceId);
		model.put("businessKey", businessKey);
		//基本信息
		CaseApply basicInfo = null;
		CooperatorTerminal cooperatorTerminal = null;
		//客户信息
		List<BeforePersonalCustomer> beforePersonalCustomerList =null;
		BeforePersonalCustomer beforePersonalCustomer = null;
		BeforePersonalCustomer spouseCustomer = null;
		List<BeforePersonalAssociation> beforePersonalAssociationList = null;
		List<BeforeAddress> beforeAddressList = null;
		CaseApplyBeforeCustomer caseApplyBeforeCustomer = null;
		CaseTask caseTask = null;
		//押品信息
		List<HouseProperty> housePropertyList = null;
		try {
			basicInfo = caseApplyService.findOne(caseApplyId);
			//终端
			if(ObjectHelper.isNotEmpty(basicInfo)){
				cooperatorTerminal = cooperatorTerminalService.findOne(basicInfo.getTerminalId());
			}
			//客户实体
			beforePersonalCustomerList = beforePersonalCustomerService.queryCustomerByCaseApplyIdAndJoinType(caseApplyId, CaseApplyBeforeCustomer.MAIN_BORROW);
			if(ObjectHelper.isNotEmpty(beforePersonalCustomerList)){
				beforePersonalCustomer = beforePersonalCustomerList.get(0);
				beforePersonalCustomer.setJoinType(CaseApplyBeforeCustomer.MAIN_BORROW);
				//配偶实体
				beforePersonalAssociationList = beforePersonalAssociationService.queryAssociation(BeforePersonalAssociation.SPOUSE, beforePersonalCustomer.getId());
				if(ObjectHelper.isNotEmpty(beforePersonalAssociationList)){
					spouseCustomer = beforePersonalAssociationList.get(0).getBeforePersonalCusomer();
					caseApplyBeforeCustomer= caseApplyBeforeCustomerService.findByCaseApplyIdAndBeforeCustomerId(caseApplyId, spouseCustomer.getId());
					spouseCustomer.setJoinType(caseApplyBeforeCustomer.getJoinType());
				}
			}
			//地址
			if(ObjectHelper.isNotEmpty(beforePersonalCustomer)){
				beforeAddressList = beforeAddressService.queryAddresss(beforePersonalCustomer.getId());
			}
			//押品
			housePropertyList = housePropertyService.findByCaseApplyId(caseApplyId);
			//资信人员信息
			caseTask = caseTaskService.findByCaseApplyId(caseApplyId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询数据失败",e.getMessage());
		}
		//基本信息
		if(ObjectHelper.isNotEmpty(basicInfo)){
			CaseApplyVo basicInfoVo = new CaseApplyVo(basicInfo);
			model.put("basicInfoVo", basicInfoVo);
		}
		//终端
		if(ObjectHelper.isNotEmpty(cooperatorTerminal)){
			CooperatorTerminalVo cooperatorTerminalVo = new CooperatorTerminalVo(cooperatorTerminal);
			model.put("cooperatorTerminalVo", cooperatorTerminalVo);
		}
		//客户信息
		if(ObjectHelper.isNotEmpty(beforePersonalCustomer)){
			BeforePersonalCustomerVo beforePersonalCustomerVo = new BeforePersonalCustomerVo(beforePersonalCustomer);
			model.put("beforePersonalCustomerVo", beforePersonalCustomerVo);
		}
		//配偶信息
		if(ObjectHelper.isNotEmpty(spouseCustomer)){
			BeforePersonalCustomerVo spouseCustomerVo = new BeforePersonalCustomerVo(spouseCustomer);
			model.put("spouseVo", spouseCustomerVo);
		}
		//地址
		if(ObjectHelper.isNotEmpty(beforeAddressList) && beforeAddressList.size() > 0){
			for(BeforeAddress beforeAddress : beforeAddressList ){
				if(ObjectHelper.isEquals(beforeAddress.getAddressType(), BeforeAddress.HOME_ADDRESS)){
					BeforeAddressVo beforeAddressVo = new BeforeAddressVo(beforeAddress);
					model.put("homeAddress", beforeAddressVo);
				}
				if(ObjectHelper.isEquals(beforeAddress.getAddressType(), BeforeAddress.HOUSEHOLD_REGISTRATION_ADDRESS)){
					BeforeAddressVo beforeAddressVo = new BeforeAddressVo(beforeAddress);
					model.put("homeHoldAddress", beforeAddressVo);
				}
			}
		}
		//押品信息
		if(ObjectHelper.isNotEmpty(housePropertyList) && housePropertyList.size() > 0){
			HouseProperty houseProperty= housePropertyList.get(0);
			HousePropertyVo housePropertyVo = new HousePropertyVo(houseProperty); 
			model.put("housePropertyVo", housePropertyVo);
		}
		//资信人员
		if(ObjectHelper.isNotEmpty(caseTask)){
			CaseTaskVo caseTaskVo = new CaseTaskVo(caseTask);
			model.put("caseTaskVo", caseTaskVo);
		}
		return new ModelAndView("/casemanage/data_dispatching_list", model);
	}
	
	/**
	 * 获取联系方式
	 * 
	 * @param customerId 客户id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/getContactInfo")
	@UriKey(key = "com.zdsoft.finance.casemanage.getContactInfo")
	@ResponseBody
	public String getContactInfo(String customerId, String jsoncallback) {
		//获取与主借人相关的人
		List<BeforeContactVo> beforeContactVos = new ArrayList<BeforeContactVo>();
		List<BeforePersonalAssociation> beforePersonalAssociationList = null;
		List<BeforeContact> beforeContactList = null;
		try {
			//主借人的联系方式
			beforeContactList = beforeContactService.queryContact(customerId);
			for(BeforeContact beforeContact : beforeContactList){
				BeforeContactVo beforeContactVo = new BeforeContactVo(beforeContact);
				beforeContactVos.add(beforeContactVo);
			}
			//关联人的联系方式
			beforePersonalAssociationList = beforePersonalAssociationService.queryCustomerId(customerId);
			for(BeforePersonalAssociation beforePersonalAssociation : beforePersonalAssociationList){
				BeforePersonalCustomer beforePersonalCusomer = beforePersonalAssociation.getBeforePersonalCusomer();
				beforeContactList = beforeContactService.queryContact(beforePersonalCusomer.getId());
				for(BeforeContact beforeContact : beforeContactList){
					BeforeContactVo beforeContactVo = new BeforeContactVo(beforeContact);
					beforeContactVos.add(beforeContactVo);
				}
			}
		} catch (Exception e) {
			logger.error("查询数据失败",e.getMessage());
		}
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setRows(beforeContactVos);
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 获取工作单位信息
	 * 
	 * @param beforeCustomerId 客户id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/getWorkUnitInfo")
	@UriKey(key = "com.zdsoft.finance.casemanage.getWorkUnitInfo")
	@ResponseBody
	public String getWorkUnitInfo(String customerId, String jsoncallback) {
		//获取与主借人相关的人
		List<BeforeWorkUnitVo> beforeWorkUnitVos = new ArrayList<BeforeWorkUnitVo>();
		List<BeforePersonalAssociation> beforePersonalAssociationList = null;
		List<BeforeWorkUnit> beforeWorkUnitList = null;
		try {
			//主借人的工作单位信息
			beforeWorkUnitList = beforeWorkUnitService.queryByCustomerId(customerId);
			for(BeforeWorkUnit beforeWorkUnit : beforeWorkUnitList){
				BeforeWorkUnitVo beforeWorkUnitVo = new BeforeWorkUnitVo(beforeWorkUnit);
				beforeWorkUnitVos.add(beforeWorkUnitVo);
			}
			//关联人的工作单位信息
			beforePersonalAssociationList = beforePersonalAssociationService.queryCustomerId(customerId);
			for(BeforePersonalAssociation beforePersonalAssociation : beforePersonalAssociationList){
				BeforePersonalCustomer beforePersonalCusomer = beforePersonalAssociation.getBeforePersonalCusomer();
				beforeWorkUnitList = beforeWorkUnitService.queryByCustomerId(beforePersonalCusomer.getId());
				for(BeforeWorkUnit beforeWorkUnit : beforeWorkUnitList){
					BeforeWorkUnitVo beforeWorkUnitVo = new BeforeWorkUnitVo(beforeWorkUnit);
					beforeWorkUnitVos.add(beforeWorkUnitVo);
				}
			}
		} catch (Exception e) {
			logger.error("查询数据失败",e.getMessage());
		}
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setRows(beforeWorkUnitVos);
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 抵押情况
	 * 
	 * @param request
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/getMortgageInfo")
	@UriKey(key = "com.zdsoft.finance.casemanage.getMortgageInfoList")
	@ResponseBody
	public String getMortgageInfo(String houseId, String jsoncallback) {
		List<PledgeInfo> pledgeInfoList = null;
		try {
			pledgeInfoList = pledgeInfoService.findByHouseId(houseId);
		} catch (BusinessException e) {
			logger.error("抵押情况查询失败",e);
		}
		List<PledgeInfoVo> pledgeInfoVos = new ArrayList<PledgeInfoVo>();
		for(PledgeInfo pledgeInfo : pledgeInfoList){
			PledgeInfoVo pledgeInfoVo = new PledgeInfoVo(pledgeInfo);
			pledgeInfoVos.add(pledgeInfoVo);
		}
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("抵押情况列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setRows(pledgeInfoVos);
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 获取房产评估信息
	 * @param cassApplyId
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/getEvaluatingInfo")
	@UriKey(key = "com.zdsoft.finance.casemanage.getEvaluatingInfo")
	@ResponseBody
	public String getEvaluatingInfo(String housePropertyId, String jsoncallback){
		List<HouseAssessmentVo> houseAssessmentVos = new ArrayList<HouseAssessmentVo>();
		List<HouseAssessment> houseAssessmentList = null;
		try {
			houseAssessmentList = houseAssessmentService.queryHouseAssessments(housePropertyId);
			for(HouseAssessment houseAssessment : houseAssessmentList){
				HouseAssessmentVo houseAssessmentVo = new HouseAssessmentVo(houseAssessment);
				houseAssessmentVos.add(houseAssessmentVo);
			}
		} catch (BusinessException e) {
			logger.error("房产评估信息查询失败",e.getMessage());
		}
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("抵押情况列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setRows(houseAssessmentVos);
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 获取关联贷款信息
	 * @param customerId
	 * @return
	 */
	@RequestMapping("/getRelatedLoanInfo")
	@UriKey(key = "com.zdsoft.finance.casemanage.getRelatedLoanInfo")
	@ResponseBody
	public String getRelatedLoanInfo(String customerId, String jsoncallback){
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("customerId", customerId);
		List<Map<String, Object>> caseApplyBeforeCustomerList = new ArrayList<Map<String, Object>>();
		try {
			//查询该客户关联的所有案件
			caseApplyBeforeCustomerList = caseApplyBeforeCustomerService.queryCaseApplyByCustomer(condition);
			for(Map<String, Object> map : caseApplyBeforeCustomerList){
				if(ObjectHelper.isEquals(map.get("joinType"), CaseApplyBeforeCustomer.MAIN_BORROW)){//主借人
					map.replace("joinType", "主借人");
				}
				if(ObjectHelper.isEquals(map.get("joinType"), CaseApplyBeforeCustomer.MAIN_BORROW)){//等 静态值 完善
					
				}
			}
			msg.setMsg("关联贷款信息查询成功");
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setRows(caseApplyBeforeCustomerList);
		} catch (Exception e) {
			logger.error("查询数据失败",e.getMessage());
			msg.setMsg("关联贷款信息查询失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return  ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 保持派单信息
	 * @param businessKey
	 * @param caseApplyId
	 * @return
	 */
	@RequestMapping(value = "/saveDispatchInfo")
	@UriKey(key = "com.zdsoft.finance.casemanage.saveDispatchInfo")
	@FinishJob(resource = "com.zdsoft.finance.casemanage.initDataDispatching", businessId = "businessKey", projectId = "caseApplyId")
	@ResponseBody
	public String saveDispatchInfo(HttpServletRequest request,String caseApplyId, String processInstanceId, String businessKey, String jsoncallback){
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String empName = request.getParameter("empName");
		String empCode = request.getParameter("empCode");
		String remark = request.getParameter("remark");
		String empId = request.getParameter("empId");
		CaseTask caseTask = new CaseTask();
		if(ObjectHelper.isNotEmpty(empId)){
			caseTask.setId(empId);
		}
		caseTask.setMo(remark);
		caseTask.setTaskPersonnelName(empName);
		caseTask.setTaskPersonnelCode(empCode);
		caseTask.setCaseApplyId(caseApplyId);
		resultMap.put("empName", empName);
		resultMap.put("empCode", empCode);
		resultMap.put("remark", remark);
		CaseTask caseTaskEnd = null;
		try {
			caseTaskEnd = caseTaskService.saveOrUpdateEntity(caseTask);
			logger.info("---资调派单完成---");
			msg.setMsg("关联贷款信息查询成功");
	        msg.setResultStatus(ResponseMsg.SUCCESS);
	        msg.setOptional(resultMap);
	        if(ObjectHelper.isNotEmpty(caseTaskEnd)){
	        	msg.setId(caseTaskEnd.getId());
	        }
		} catch (BusinessException e) {
			logger.error("保存派工数据失败",e);
			msg.setMsg("关联贷款信息查询失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		} 
		return  ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
}
