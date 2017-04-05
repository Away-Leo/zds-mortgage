package com.zdsoft.finance.loan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.zdsoft.bpm.annotation.client.FinishJob;
import com.zdsoft.bpm.annotation.client.ManualJob;
import com.zdsoft.essential.client.aop.annotation.DataAuthResource;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.permission.DataOperPermDto;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.loan.service.LoanApplySerivce;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.common.util.StoreHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;
/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: LoanApplyController.java 
 * @ClassName: LoanApplyController 
 * @Description: 放款申请controller
 * @author huangwei 
 * @date 2017年2月21日 下午6:18:11 
 * @version V1.0
 */
@RequestMapping("/loan")
@Controller
public class LoanApplyController extends BaseController{
	@Autowired
	private LoanApplySerivce loanApplyService;
	@Autowired
	private CED CED ;
	/**
	 * @Title: initLoanApply 
	 * @Description: 放款请款初始化
	 * @author huangwei 
	 * @return
	 */
	@RequestMapping("/initLoanApply")
	@UriKey(key = "com.zdsoft.finance.loan.initLoanApply")
	@Menu(resource = "com.zdsoft.finance.loan.initLoanApply", label = "放款请款", group = "loan", order = 1)
	@DataAuthResource(resource="com.zdsoft.finance.loan.initLoanApply.dataAuth",label="放款请款",group="com.zdsoft.finance.loan.initLoanApply")
	public ModelAndView initLoanApply() {
		return new ModelAndView("/loan/loanapply_list");
	}
	
	/**
	 * @Title: getLoanApply 
	 * @Description: 放款请款列表
	 * @author huangwei 
	 * @param request
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/loanApply")
	@UriKey(key = "com.zdsoft.finance.loan.getLoanApply")
	@ResponseBody
	public String getLoanApply(HttpServletRequest request, String jsoncallback, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();
		try{
			// 获取页面封装的查询参数
			@SuppressWarnings("unchecked")
			List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
			//权限控制
    		DataOperPermDto dataOperPermDto = CED.findDataPerms(StoreHelper.getApplication(), "com.zdsoft.finance.loan.initLoanApply.dataAuth");
			// 分页案件
			Page<Map<String, Object>> caseApplyPage = loanApplyService.getCaseList(pageable, queryObjs,dataOperPermDto);
			//msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(caseApplyPage.getTotalRows());
			msg.setRows(caseApplyPage.getRecords());
		}
		catch(Exception e)
		{
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			e.printStackTrace();
			logger.error("获取放款请款列表异常：", e);
		}

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	
	/**
	 * @Title: getLoanApplyDetail 
	 * @Description: 获取请款申请单详情
	 * @author huangwei 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/loanApplyDetail")
	@UriKey(key = "com.zdsoft.finance.loan.getLoanApplyDetail")
	public ModelAndView getLoanApplyDetail(HttpServletRequest request,Model model){
		String param=request.getParameter("param");
		Gson gson=new Gson();
		@SuppressWarnings("unchecked")
		Map<String,Object> paramMap=gson.fromJson(param, Map.class);
		Map<String,Object> resultData=new HashMap<String,Object>();
		try {
			//请款单信息
			Map<String,Object> applyMap=loanApplyService.getLoanApplyForm(paramMap.get("ID").toString());
			applyMap.put("loanApplyAmount", paramMap.get("loanApplyAmount"));
			applyMap.putAll(loanApplyService.getLoanApplyCase(paramMap));
			resultData.put("apply", applyMap);
			resultData.put("applyObj", gson.toJson(applyMap));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取获取请款申请单详情异常：", e);
		}
		return new ModelAndView("loan/loanapply_detail_edit", resultData);
	}
	/**
	 * @Title: submitLoanApply 
	 * @Description: 提交请款单
	 * @author huangwei 
	 * @param request
	 * @return
	 */
	@RequestMapping("/submitLoanApply")
	@UriKey(key = "com.zdsoft.finance.loan.submitLoanApply")
	@ResponseBody
	public ResponseMsg submitLoanApply(HttpServletRequest request){
		ResponseMsg msg = new ResponseMsg();
		Map<String,String[]> paramMap=request.getParameterMap();
		//存储表单信息
		try {
			String id=loanApplyService.submitLoanApply(paramMap);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setId(id);
		} catch (Exception e) {
			msg.setMsg("操作失败!");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			e.printStackTrace();
			logger.error("提交请款单异常：", e);
			
		}
		return msg;
	}
	/**
	 * @Title: loanApplyForm 
	 * @Description:根据业务表单id跳转表单查看页面
	 * @author huangwei 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/loanApplyForm")
	@UriKey(key = "com.zdsoft.finance.loan.loanApplyForm")
	public ModelAndView loanApplyForm(HttpServletRequest request,Model model){
		String busiId=request.getParameter("businessKey");
		Map<String,Object> resultData=new HashMap<String,Object>();
		try {
			Map<String,Object> dataMap=loanApplyService.getLoanApplyFormByBusiId(busiId);
			resultData.put("applyObj", new Gson().toJson(dataMap));
			resultData.put("apply", dataMap);
			resultData.put("loanApplyId", busiId);
		} catch (Exception e) {
			logger.error("系统异常：", e);
		}
		return new ModelAndView("loan/loanapply_detail", resultData);
	}
	/**
	 * @Title: loanApplyForm 
	 * @Description:根据业务表单id跳转表单编辑页面
	 * @author huangwei 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/loanApplyFormEdit")
	@UriKey(key = "com.zdsoft.finance.loan.loanApplyFormEdit")
	public ModelAndView loanApplyFormEdit(HttpServletRequest request,Model model){
		String busiId=request.getParameter("businessKey");
		Map<String,Object> resultData=new HashMap<String,Object>();
		try {
			Map<String,Object> dataMap=loanApplyService.getLoanApplyFormByBusiId(busiId);
			resultData.put("applyObj", new Gson().toJson(dataMap));
			resultData.put("apply", dataMap);
			resultData.put("loanApplyId", busiId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统异常：", e);
		}
		return new ModelAndView("loan/loanapply_detail_edit", resultData);
	}
	
	
	/**
	 * @Title: loanApplyProcess 
	 * @Description: 流程审批事项
	 * @author huangwei 
	 * @param request
	 * @param projectId     项目id
	 * @param businessKey   表单id
	 * @return
	 */
	@RequestMapping("/loanApplyProcess")
	@UriKey(key = "com.zdsoft.finance.loan.loanApplyProcess")
	@ManualJob(resource = "com.zdsoft.finance.loan.loanApplyProcess", label = "放款申请审批")
	@FinishJob(resource = "com.zdsoft.finance.loan.loanApplyProcess", businessId = "businessKey", projectId = "projectId")
	public ModelAndView loanApplyProcess(HttpServletRequest request,String projectId,  String businessKey){
		Map<String,Object> resultData=new HashMap<String,Object>();
		try {
			Map<String,Object> dataMap=loanApplyService.getLoanApplyFormByBusiId(businessKey);
			resultData.put("applyObj", new Gson().toJson(dataMap));
			resultData.put("apply", dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统异常：", e);
		}
		return new ModelAndView("loan/loanapply_process",resultData);
	}
	
	/**
	 * @Title: loanApplyProcessEdit 
	 * @Description: 流程编辑事项
	 * @author huangwei 
	 * @param request
	 * @param projectId   项目id
	 * @param businessKey   表单Id
	 * @return
	 */
	@RequestMapping("/loanApplyProcessEdit")
	@UriKey(key = "com.zdsoft.finance.loan.loanApplyProcessEdit")
	@ManualJob(resource = "com.zdsoft.finance.loan.loanApplyProcessEdit", label = "放款申请编辑")
	public ModelAndView loanApplyProcessEdit(HttpServletRequest request,String projectId,  String businessKey){
		Map<String,Object> resultData=new HashMap<String,Object>();
		try {
			Map<String,Object> dataMap=loanApplyService.getLoanApplyFormByBusiId(businessKey);
			resultData.put("applyObj", new Gson().toJson(dataMap));
			resultData.put("apply", dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统异常：", e);
		}
		return new ModelAndView("loan/loanapply_process",resultData);
	}
	/**
	 * @Title: loanApplyProcessEditSave 
	 * @Description: 放款流程编辑提交
	 * @author huangwei 
	 * @param request
	 * @param projectId
	 * @param businessKey
	 * @return
	 */
	@RequestMapping("/loanApplyProcessEditSave")
	@UriKey(key = "com.zdsoft.finance.loan.loanApplyProcessEditSave")
	@FinishJob(resource = "com.zdsoft.finance.loan.loanApplyProcessEditSave", businessId = "businessKey", projectId = "projectId")
	public ResponseMsg loanApplyProcessEditSave(HttpServletRequest request,String projectId,  String businessKey){
		ResponseMsg msg = new ResponseMsg();
		String applyAmount=request.getParameter("applyAmount");
		//存储表单信息
		try {
			loanApplyService.loanApplyProcessEditSave(businessKey, applyAmount);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("保存成功!");
		} catch (Exception e) {
			msg.setMsg("保存失败!");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			e.printStackTrace();
			logger.error("系统异常：", e);
		}
		return msg;
	}
	

}
