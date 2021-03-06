package com.zdsoft.finance.casemanage.controller;


import java.util.ArrayList;
import java.util.Date;
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
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.casemanage.vo.CaseTaskVo;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.cooperator.entity.Capitalist;
import com.zdsoft.finance.cooperator.entity.CooperatorTerminal;
import com.zdsoft.finance.cooperator.service.CapitalistService;
import com.zdsoft.finance.cooperator.service.CooperatorTerminalService;
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
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/** 	
* 版权所有：重庆正大华日软件有限公司	
* @Title: DataDispatchingContro
* @Package com.zdsoft.finance.casemanage.controller
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
	@Autowired
	private CapitalistService capitalistService;
	
	@Autowired
	private CED CED ;
	
	
	
	/** 
	 * @Title: initDataDispatching 
	 * @Description: 资调派单初始化加载
	 * @author liuhuan 
	 * @param caseApplyId	案件id
	 * @param processInstanceId	流程
	 * @param businessKey	流程key
	 * @return  
	 */ 
	@RequestMapping("/initDataDispatching")
	@UriKey(key = "com.zdsoft.finance.casemanage.dataDispatching.initDataDispatching")
	@ManualJob(resource = "com.zdsoft.finance.casemanage.initDataDispatching", label = "资调派单") 
	public ModelAndView initDataDispatching(@RequestParam(value="projectId")String caseApplyId, String processInstanceId, String businessKey) {
		logger.info("进入资调派单节点");
		logger.info("caseApplyId:"+caseApplyId);
		logger.info("businessKey:"+businessKey);
		ModelMap model = new ModelMap();
		model.put("caseApplyId", caseApplyId);
		model.put("businessKey", businessKey);
		//基本信息
		CaseApply caseApply = null;
		CooperatorTerminal cooperatorTerminal = null;
		Capitalist capitalist = null;
		//客户信息
		List<BeforePersonalCustomer> beforePersonalCustomerList =null;
		BeforePersonalCustomer beforePersonalCustomer = null;
		BeforePersonalCustomer spouseCustomer = null;
		List<BeforePersonalAssociation> beforePersonalAssociationList = null;
		List<BeforeAddress> beforeAddressList = null;
		CaseApplyBeforeCustomer caseApplyBeforeCustomer = null;
		CaseTask caseTask = null;
		//设置查找征信信息的参数
		StringBuilder customerIds = new StringBuilder();
		String mainCustomerId = null;
		//押品信息
		List<HouseProperty> housePropertyList = null;
		//工作单位信息
		List<BeforeWorkUnitVo> beforeWorkUnitVos = null;
		StringBuilder huiFaPersonIdentities = new StringBuilder();
		try {
			caseApply = caseApplyService.findOne(caseApplyId);
			//终端
			if(ObjectHelper.isNotEmpty(caseApply.getTerminalId())){
				cooperatorTerminal = cooperatorTerminalService.findOne(caseApply.getTerminalId());
			}
			//资金来源	
			if(ObjectHelper.isNotEmpty(caseApply.getCapitalSource())){
				capitalist = capitalistService.findOne(caseApply.getCapitalSource());
			}
			//客户实体
			beforePersonalCustomerList = beforePersonalCustomerService.queryCustomerByCaseApplyIdAndJoinType(caseApplyId, CaseApplyBeforeCustomer.MAIN_BORROW);
			if(ObjectHelper.isNotEmpty(beforePersonalCustomerList)){
				beforePersonalCustomer = beforePersonalCustomerList.get(0);
				//设置主借人id
				mainCustomerId = beforePersonalCustomer.getId();
				//设置征信信息需要的customerIds(需要所有人id,以英文,拼接)
				customerIds.append(mainCustomerId);
				beforePersonalCustomer.setJoinType(CaseApplyBeforeCustomer.MAIN_BORROW);
				//配偶实体
				beforePersonalAssociationList = beforePersonalAssociationService.queryAssociation(BeforePersonalAssociation.SPOUSE, beforePersonalCustomer.getId());
				if(ObjectHelper.isNotEmpty(beforePersonalAssociationList)){
					spouseCustomer = beforePersonalAssociationList.get(0).getBeforePersonalCusomer();
					caseApplyBeforeCustomer= caseApplyBeforeCustomerService.findByCaseApplyIdAndBeforeCustomerId(caseApplyId, spouseCustomer.getId());
					if(ObjectHelper.isNotEmpty(caseApplyBeforeCustomer)){
						spouseCustomer.setJoinType(caseApplyBeforeCustomer.getJoinType());
					}
					//设置主借人配偶id
					customerIds.append("," + spouseCustomer.getId());
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
			//查找案件关联的所有人的工作单位信息
			beforeWorkUnitVos = this.getBeforeWorkUnitVos(mainCustomerId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询数据失败",e);
		}
		//基本信息
		if(ObjectHelper.isNotEmpty(caseApply)){
			CaseApplyVo caseApplyVo = new CaseApplyVo(caseApply);
			//终端
			if(ObjectHelper.isNotEmpty(cooperatorTerminal)){
				caseApplyVo.setTerminalIdName(cooperatorTerminal.getTerminalFullName());
			}
			//资金来源
			if(ObjectHelper.isNotEmpty(capitalist)){
				caseApplyVo.setCapitalSourceName(capitalist.getCapitalName());
			}
			model.put("caseApplyVo", caseApplyVo);
		}
		//客户信息
		if(ObjectHelper.isNotEmpty(beforePersonalCustomer)){
			BeforePersonalCustomerVo beforePersonalCustomerVo = new BeforePersonalCustomerVo(beforePersonalCustomer);
			//获取主借人的证件号码
			huiFaPersonIdentities.append(beforePersonalCustomerVo.getCredentialNo());
			//配偶信息
			BeforePersonalCustomerVo spouseCustomerVo = new BeforePersonalCustomerVo(spouseCustomer);
			beforePersonalCustomerVo.setSpouseVo(spouseCustomerVo);
			if (ObjectHelper.isNotEmpty(spouseCustomer)) {
				//设置配偶的身份证号码
				huiFaPersonIdentities.append(",").append(spouseCustomer.getCredentialNo());
			}
			model.put("persCustomerVo", beforePersonalCustomerVo);
		}
		
		//地址
		if(ObjectHelper.isNotEmpty(beforeAddressList) && beforeAddressList.size() > 0){
			for(BeforeAddress beforeAddress : beforeAddressList ){
				//户籍地址
				if(ObjectHelper.isEquals(beforeAddress.getAddressType(), BeforeAddress.HOME_ADDRESS)){
					BeforeAddressVo beforeAddressVo = new BeforeAddressVo(beforeAddress);
					model.put("beforeAddressVo", beforeAddressVo);
				}
				//家庭地址
				if(ObjectHelper.isEquals(beforeAddress.getAddressType(), BeforeAddress.HOUSEHOLD_REGISTRATION_ADDRESS)){
					BeforeAddressVo homeAddressVo = new BeforeAddressVo(beforeAddress);
					model.put("homeAddressVo", homeAddressVo);
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
		//设置主借人id
		if(ObjectHelper.isNotEmpty(mainCustomerId)){
			model.put("mainCustomerId", mainCustomerId);
		}
		if(ObjectHelper.isNotEmpty(customerIds)){
			model.put("customerIds", customerIds.toString());
		}
		//设置个人法院查询信息参数
		if(huiFaPersonIdentities.length()>0){
			model.put("huiFaPersonIdentities", huiFaPersonIdentities.toString());
		}
		
		//拼接查询企业法院信息和工商信息所需的参数
		if(ObjectHelper.isNotEmpty(beforeWorkUnitVos)){
			StringBuilder huifaQueryParams = new StringBuilder();
			StringBuilder companyNames = new StringBuilder();
			for (BeforeWorkUnitVo beforeWorkUnitVo : beforeWorkUnitVos) {
				String position = beforeWorkUnitVo.getPosition();
				if(BeforeWorkUnitVo.BENEFICIAL_CONTROLLING_OWNER.equals(position) || BeforeWorkUnitVo.CHAIRMAN_OF_THE_BOARD.equals(position)
						||BeforeWorkUnitVo.FORMAT_CONTROLLING_OWNER.equals(position)){
					huifaQueryParams.append(",").append(beforeWorkUnitVo.getCompanyName()).append(",").append(beforeWorkUnitVo.getWorkUnitName());
					companyNames.append(",").append(beforeWorkUnitVo.getCompanyName());
				}
			}
			//删除第一个,
			if(huifaQueryParams.length()>0){
				huifaQueryParams.delete(0, 1);
				companyNames.delete(0, 1);
				model.put("huifaQueryParams", huifaQueryParams.toString());
				model.put("companyNames", companyNames.toString());
			}
			
		}
		//
		try {
            EmpDto empDto = CED.getLoginUser();
            model.put("companyCd", empDto.getCompanyCd());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取登录人失败！",e);
        }
		
		return new ModelAndView("/casemanage/data_dispatching_list", model);
	}
	
	
	/** 
	 * @Title: getContactInfo 
	 * @Description: 获取联系方式
	 * @author liuhuan 
	 * @param customerId	客户id
	 * @param jsoncallback	
	 * @return  
	 */ 
	@RequestMapping("/getContactInfo")
	@UriKey(key = "com.zdsoft.finance.casemanage.getContactInfo")
	@ResponseBody
	public String getContactInfo(String customerId, String jsoncallback) {
		ResponseMsg msg = new ResponseMsg();
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
			logger.error("查询数据失败",e);
			e.printStackTrace();
			msg.setMsg("列表查询异常");
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			return ObjectHelper.objectToJson(msg, jsoncallback);
		}
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setRows(beforeContactVos);
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	
	/** 
	 * @Title: getWorkUnitInfo 
	 * @Description: 获取工作单位信息
	 * @author liuhuan 
	 * @param customerId 客户id
	 * @param jsoncallback
	 * @return  
	 */ 
	@RequestMapping("/getWorkUnitInfo")
	@UriKey(key = "com.zdsoft.finance.casemanage.getWorkUnitInfo")
	@ResponseBody
	public String getWorkUnitInfo(String customerId, String jsoncallback) {
		//获取与主借人相关的人
		ResponseMsg msg = new ResponseMsg();
		try{
			List<BeforeWorkUnitVo> beforeWorkUnitVos = getBeforeWorkUnitVos(customerId);
			msg.setMsg("工作单位列表查询成功");
			msg.setRows(beforeWorkUnitVos);
			msg.setResultStatus(ResponseMsg.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("工作单位列表查询失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("工作单位列表查询失败");
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 
	 * @Title: getBeforeWorkUnitVos 
	 * @Description: 抽取方法,原author:liuhuan,只是提取方法,根据客户id返回
	 * @author caixiekang 
	 * @param customerId 客户id
	 * @return
	 * @throws Exception
	 */
	private List<BeforeWorkUnitVo> getBeforeWorkUnitVos(String customerId) throws Exception{
		List<BeforeWorkUnitVo> beforeWorkUnitVos = new ArrayList<BeforeWorkUnitVo>();
		List<BeforePersonalAssociation> beforePersonalAssociationList = null;
		List<BeforeWorkUnit> beforeWorkUnitList = null;
	
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
		
		return beforeWorkUnitVos;
	}
	
	
	/** 
	 * @Title: getMortgageInfo 
	 * @Description: 抵押情况
	 * @author liuhuan 
	 * @param houseId 房产id
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
	 * @Title: getEvaluatingInfo 
	 * @Description: 获取房产评估信息
	 * @author liuhuan 
	 * @param housePropertyId 房产id
	 * @param jsoncallback
	 * @return  
	 */ 
	@RequestMapping("/getEvaluatingInfo")
	@UriKey(key = "com.zdsoft.finance.casemanage.getEvaluatingInfo")
	@ResponseBody
	public String getEvaluatingInfo(String housePropertyId, String jsoncallback){
		ResponseMsg msg = new ResponseMsg();
		List<HouseAssessmentVo> houseAssessmentVos = new ArrayList<HouseAssessmentVo>();
		List<HouseAssessment> houseAssessmentList = null;
		try {
			houseAssessmentList = houseAssessmentService.queryHouseAssessments(housePropertyId);
			for(HouseAssessment houseAssessment : houseAssessmentList){
				HouseAssessmentVo houseAssessmentVo = new HouseAssessmentVo(houseAssessment);
				houseAssessmentVos.add(houseAssessmentVo);
			}
		} catch (BusinessException e) {
			logger.error("房产评估信息查询失败",e);
			msg.setMsg("列表查询异常");
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			return ObjectHelper.objectToJson(msg, jsoncallback);
		}
		msg.setMsg("抵押情况列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setRows(houseAssessmentVos);
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	
	/** 
	 * @Title: getRelatedLoanInfo 
	 * @Description: 获取关联贷款信息
	 * @author liuhuan 
	 * @param customerId 客户id
	 * @param jsoncallback
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
	 * @Title: saveDispatchInfo 
	 * @Description: 保持派单信息 edited by caixiekang
	 * @author liuhuan 
	 * @param request	参数
	 * @param caseApplyId 案件id
	 * @param processInstanceId 流程
	 * @param businessKey	流程key
	 * @param jsoncallback
	 * @param caseTaskVo	派工信息vo
	 * @return  
	 */ 
	@RequestMapping(value = "/saveDispatchInfo")
	@UriKey(key = "com.zdsoft.finance.casemanage.saveDispatchInfo")
	@FinishJob(resource = "com.zdsoft.finance.casemanage.initDataDispatching", businessId = "businessKey", projectId = "caseApplyId")
	@ResponseBody
	public String saveDispatchInfo(HttpServletRequest request,@RequestParam(value="projectId")String caseApplyId, String processInstanceId, String businessKey, String jsoncallback, CaseTaskVo caseTaskVo){
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		CaseTask caseTask = caseTaskVo.toPo();
		//设置案件id和指派时间
		caseTask.setCaseApplyId(caseApplyId);
		caseTask.setTaskDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
		CaseTask caseTaskEnd = null;
		try {
			caseTaskEnd = caseTaskService.saveOrUpdateCaseTask(caseTask);
			logger.info("---资调派单保存成功---");
			msg.setMsg("资调派单保存成功");
	        msg.setResultStatus(ResponseMsg.SUCCESS);
	        msg.setOptional(resultMap);
	        //页面回传ID
	        if(ObjectHelper.isNotEmpty(caseTaskEnd)){
	        	msg.setId(caseTaskEnd.getId());
	        }
		} catch (Exception e) {
			logger.error("保存派工数据失败",e);
			msg.setMsg("保存派工数据失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		} 
		return  ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
}
