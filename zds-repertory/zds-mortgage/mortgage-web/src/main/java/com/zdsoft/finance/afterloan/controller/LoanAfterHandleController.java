package com.zdsoft.finance.afterloan.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.zdsoft.bpm.annotation.client.FinishJob;
import com.zdsoft.bpm.annotation.client.ManualJob;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.afterloan.entity.AfterSupervise;
import com.zdsoft.finance.afterloan.entity.EmergencyContacts;
import com.zdsoft.finance.afterloan.entity.FollowInfo;
import com.zdsoft.finance.afterloan.repository.AfterSuperviseRepository;
import com.zdsoft.finance.afterloan.service.LoanAfterHandleService;
import com.zdsoft.finance.afterloan.vo.AfterSuperviseVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

import io.netty.util.internal.StringUtil;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: LoanAfterHandle.java 
 * @ClassName: LoanAfterHandle 
 * @Description: 贷后处理controller
 * @author huangwei 
 * @date 2017年3月1日 下午3:32:19 
 * @version V1.0
 */
@Controller
@RequestMapping("/loanafterhandle")
public class LoanAfterHandleController extends BaseController {
	@Autowired
	private LoanAfterHandleService loanAfterHandleService;
	@Autowired
	private CED CED;
	@Autowired
	private AfterSuperviseRepository afterSuperviseRepository;
	/**
	 * @Title: toToHandlePage 
	 * @Description: 初始化并返回贷后 处理页面
	 * @author huangwei
	 */
	@RequestMapping("/loanAfterHandle")
	@UriKey(key = "com.zdsoft.finance.afterloan.loanAfterHandle")
	public ModelAndView toToHandlePage(String caseApplyId){
		ModelMap modelMap=null;
		try {
			modelMap = loanAfterHandleService.getCaseApplyDetail(caseApplyId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("初始化并返回贷后 处理页面异常：", e);
		}
		return new ModelAndView("/afterloan/loanafterhandle/loanafterhandle_list",modelMap);
	}
	/**
	 * @Title: getContactList 
	 * @Description: 获取联系人信息
	 * @author huangwei 
	 * @param request
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/contacts")
	@UriKey(key = "com.zdsoft.finance.afterloan.contacts")
	@ResponseBody
	public String getContactList(HttpServletRequest request, String jsoncallback, PageRequest pageable){
		ResponseMsg msg = new ResponseMsg();
		String caseApplyId=request.getParameter("caseApplyId");
		try{
			// 分页案件
			Page<Map<String, Object>> caseApplyPage = loanAfterHandleService.getContactsList(caseApplyId, pageable);
			//msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(caseApplyPage.getTotalRows());
			msg.setRows(caseApplyPage.getRecords());
		}
		catch(Exception e)
		{
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			e.printStackTrace();
			logger.error("获取获取联系人信息异常：", e);
		}

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	/**
	 * @Title: getContactInformation 
	 * @Description: 获取联系人的联系方式
	 * @author huangwei 
	 * @param request
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/contactsImformation")
	@UriKey(key = "com.zdsoft.finance.afterloan.contactsImformation")
	@ResponseBody
	public String getContactInformation(HttpServletRequest request, String jsoncallback){
		ResponseMsg msg = new ResponseMsg();
		//是否为紧急联系人
		String isEm=request.getParameter("isEmergency");
		String customerId=request.getParameter("customerId");
		try{
			List resultData=loanAfterHandleService.getCustomerContacts(customerId,isEm);
			//msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(Long.parseLong(resultData.size()+""));
			msg.setRows(resultData);
		}
		catch(Exception e)
		{
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			e.printStackTrace();
			logger.error("获取联系人的联系方式异常：", e);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	/**
	 * @Title: addOrUpdateContact 
	 * @Description: 添加或修改联系人信息
	 * @author huangwei 
	 * @param request
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/addOrUpdateContact")
	@UriKey(key = "com.zdsoft.finance.afterloan.addOrUpdateContact")
	@ResponseBody
	public String addOrUpdateContact(HttpServletRequest request, String jsoncallback){
		ResponseMsg msg = new ResponseMsg();
		String customerId=request.getParameter("customerId");
		//是否为紧急联系人
		String isEm=request.getParameter("isEmergency");
		try{
			//如果id存在，则修改联系方式
			if(customerId!=null)
			{
				loanAfterHandleService.updateContactInformation(request.getParameter("content"),isEm,customerId);
				msg.setMsg("修改联系方式成功");
			}
			//如果id不存在，则添加紧急联系人
			else
			{
				String caseApplyId=request.getParameter("caseApplyId");
				String customerName=request.getParameter("customerName");
				String relation=request.getParameter("relation");
				EmergencyContacts contacts=new EmergencyContacts();
				contacts.setCaseApplyId(caseApplyId);
				contacts.setContactName(customerName);
				contacts.setRelationship(relation);
				loanAfterHandleService.addEmCustomerContacts(request.getParameter("content"),contacts);
				msg.setMsg("添加紧急联系人成功");
			}
			msg.setResultStatus(ResponseMsg.SUCCESS);
		}
		catch(Exception e)
		{
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			if(customerId!=null)
			{
				msg.setMsg("修改联系方式失败");
			}
			else
			{
				msg.setMsg("添加紧急联系人失败");
			}
			e.printStackTrace();
			logger.error("添加或保存联系人异常：", e);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	/**
	 * @Title: initFollowInfoMessage 
	 * @Description: 跳转跟催编辑页面
	 * @author huangwei 
	 * @param customerId
	 * @param caseApplyId
	 * @return
	 */
	@RequestMapping("/initFollowInfoMessage")
	@UriKey(key = "com.zdsoft.finance.afterloan.initFollowInfoMessage")
	public ModelAndView initFollowInfoMessage(String customerId,String caseApplyId){
		ModelMap modelMap=new ModelMap();
		try {
			modelMap=loanAfterHandleService.initFollowInfoMessage(customerId,caseApplyId);
			//
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("跟催页面加载异常：", e);
		}
		return new ModelAndView("/afterloan/loanafterhandle/followinfo_edit",modelMap);
	}
	
	
	/**
	 * @Title: submitFollowInfo 
	 * @Description: 提交跟催
	 * @author huangwei 
	 * @param request
	 * @param FollowInfo   跟催对象
	 * @param afterSupervise   督办对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/submitFollowInfo")
	@UriKey(key = "com.zdsoft.finance.afterloan.submitFollowInfo")
	@ResponseBody
	public ResponseMsg submitFollowInfo(HttpServletRequest request){
		String param=request.getParameter("param");
		Map<String,Object> paramMap=new Gson().fromJson(param, Map.class);
		FollowInfo followInfo=new Gson().fromJson(param, FollowInfo.class);
		AfterSupervise afterSupervise=new Gson().fromJson(param, AfterSupervise.class);
		if(paramMap.get("followInfoId")!=null)
		{
			followInfo.setId(paramMap.get("followInfoId").toString());
		}
		if(paramMap.get("supersiveId")!=null)
		{
			afterSupervise.setId(paramMap.get("supersiveId").toString());
		}
 		ResponseMsg msg = new ResponseMsg();
 		String idStr="";
		//存储表单信息
		try {
			idStr=loanAfterHandleService.submitFollowInfo(followInfo,afterSupervise,paramMap.get("operate").toString());
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setId(idStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			msg.setMsg("操作失败!");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			e.printStackTrace();
			logger.error("提交跟催异常：", e);
			
		}
		return msg;
	} 
	
	/**
	 * 
	 * @Title: toToFollowInfoForm 
	 * @Description: 根据跟催id跳转跟催详情页面
	 * @author huangwei 
	 * @param followInfoId    跟催id
	 * @return
	 */
	@RequestMapping("/followInfoView")
	@UriKey(key = "com.zdsoft.finance.afterloan.followInfoView")
	@ResponseBody
	public ModelAndView toToFollowInfoView(HttpServletRequest request){
		ModelMap modelMap=new ModelMap();
		try {
			String id=request.getParameter("businessKey");
			modelMap=loanAfterHandleService.getFollowInfoForm(id);
			//如要发起督办，则还要返回督办信息
			if(modelMap.get("supervisd").equals("是"))
			{
				List<AfterSupervise> afterSuperviseList=afterSuperviseRepository.findByfollowInfoId(id);
				modelMap.put("afterSuperviseVo", new AfterSuperviseVo(afterSuperviseList.get(0)));
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("加载跟催页面异常：", e);
		}
		return new ModelAndView("/afterloan/loanafterhandle/followinfo_view",modelMap);
	}
	/**
	 * 
	 * @Title: toToFollowInfoEidt 
	 * @Description: 根据跟催id跳转跟催编辑页面
	 * @author huangwei 
	 * @param followInfoId    跟催id
	 * @return
	 */
	@RequestMapping("/followInfoEdit")
	@UriKey(key = "com.zdsoft.finance.afterloan.followInfoEdit")
	@ResponseBody
	public ModelAndView toToFollowInfoEidt(String businessKey){
		ModelMap modelMap=new ModelMap();
		try {
			modelMap=loanAfterHandleService.getFollowInfoForm(businessKey);
			//如要发起督办，则还要返回督办信息
			if(modelMap.get("supervisd").equals("是"))
			{
				List<AfterSupervise> afterSuperviseList=afterSuperviseRepository.findByfollowInfoId(businessKey);
				modelMap.put("afterSuperviseVo", new AfterSuperviseVo(afterSuperviseList.get(0)));
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("加载跟催页面异常：", e);
		}
		return new ModelAndView("/afterloan/loanafterhandle/followinfo_edit",modelMap);
	}
	/**
	 * @Title: toToFollowInfoView 
	 * @Description: 加载督办事项组件
	 * @author huangwei 
	 * @return
	 */
	@RequestMapping("/superviseComponent")
	@UriKey(key = "com.zdsoft.finance.afterloan.superviseComponent")
	@ResponseBody
	public ModelAndView superviseComponent(){
		ModelMap modelMap=new ModelMap();
		try {
			modelMap.put("empNm", CED.getLoginUser().getEmpNm());
			modelMap.put("departmentName", CED.getLoginUser().getDepartmentName());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("加载督办页面异常：", e);
		}
		return new ModelAndView("/afterloan/loanafterhandle/supervise_component",modelMap);
	}
	/**
	 * @Title: toToFollowInfoView 
	 * @Description: 删除督办事项组件
	 * @author huangwei 
	 * @return
	 */
	@RequestMapping("/delSuperviseComponent")
	@UriKey(key = "com.zdsoft.finance.afterloan.delSuperviseComponent")
	@ResponseBody
	public ModelAndView delSuperviseComponent(){
		ModelMap modelMap=new ModelMap();
		try {
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("页面异常：", e);
		}
		return new ModelAndView("/afterloan/loanafterhandle/nothing",modelMap);
	}
	
	/**
	 * @Title: followInfoPorcessView 
	 * @Description: 跟催审批流程展示事项
	 * @author huangwei 
	 * @param request   
	 * @param projectId   项目id
	 * @param businessKey  表单id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/followInfoApproveView")
	@UriKey(key = "com.zdsoft.finance.afterloan.followInfoApproveView")
	@ManualJob(resource = "com.zdsoft.finance.afterloan.followInfoApproveView", label = "跟催审批")
	@FinishJob(resource = "com.zdsoft.finance.afterloan.followInfoApproveView", businessId = "businessKey", projectId = "projectId")
	public ModelAndView followInfoPorcessView(HttpServletRequest request,String projectId,  String businessKey){
		ModelMap resultData=new ModelMap();
		try {
			resultData=loanAfterHandleService.getFollowInfoForm(businessKey);
			//如要发起督办，则还要返回督办信息
			Map<String,String> map=(Map<String,String>)resultData.get("follow");
			if(map.get("supervisd").equals("是"))
			{
				List<AfterSupervise> afterSuperviseList=afterSuperviseRepository.findByfollowInfoId(businessKey);
				resultData.put("afterSuperviseVo", new AfterSuperviseVo(afterSuperviseList.get(0)));
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统异常：", e);
		}
		return new ModelAndView("/afterloan/loanafterhandle/followinfoprocess_view",resultData);
	}
	
	

}
