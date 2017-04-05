package com.zdsoft.finance.loan.controller;

import java.io.BufferedOutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.loan.entity.LoanOperateLog;
import com.zdsoft.finance.loan.service.LoanFollowUpService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

import net.sf.json.JSONObject;
/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: LoanFollowUpController.java 
 * @ClassName: LoanFollowUpController 
 * @Description: 放款跟进controller
 * @author huangwei 
 * @date 2017年2月21日 下午6:18:37 
 * @version V1.0
 */
@RequestMapping("/loan")
@Controller
public class LoanFollowUpController extends BaseController{
	@Autowired
	private LoanFollowUpService loanFollowUpService;
	@Autowired
	private CED CED;
	
	/**
	 * @Title: initLoanApply 
	 * @Description: 放款跟进初始化
	 * @author huangwei 
	 * @return
	 */
	@RequestMapping("/initLoanFollowUp")
	@UriKey(key = "com.zdsoft.finance.loan.initLoanFollowUp")
	@Menu(resource = "com.zdsoft.finance.loan.initLoanFollowUp", label = "放款跟进（集团）", group = "loan", order = 2)
	public ModelAndView initLoanApply() {
		return new ModelAndView("/loan/loanfollowup_list");
	}
	
	/**
	 * @Title: getLoanFollowUp 
	 * @Description: 放款跟进列表
	 * @author huangwei 
	 * @param request
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/loanfollowup")
	@UriKey(key = "com.zdsoft.finance.loan.getLoanFollowUp")
	@ResponseBody
	public String getLoanFollowUp(HttpServletRequest request, String jsoncallback, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();
		try
		{
			// 获取页面封装的查询参数
			@SuppressWarnings("unchecked")
			List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
			// 分页案件
			Page<Map<String, Object>> caseApplyPage = loanFollowUpService.getLoanFollowUpList(pageable, queryObjs);
			//msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(caseApplyPage.getTotalRows());
			msg.setRows(caseApplyPage.getRecords());
		}
		catch(Exception e){
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			logger.error("获取放款跟进列表异常：", e);
		}

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	/**
	 * @Title: goToAllowLoanPage 
	 * @Description: 跳转到准放款页面
	 * @author huangwei 
	 * @param request
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/allowLoanPage")
	@UriKey(key = "com.zdsoft.finance.loan.allowLoanPage")
	public ModelAndView goToAllowLoanPage(HttpServletRequest request){
		String param=request.getParameter("param");
		JSONObject jsonObject = JSONObject.fromObject(param);   
	    Map<String,Object> paramMap = new HashMap<String,Object>();   
        for (Iterator<Object> iter = jsonObject.keys(); iter.hasNext();) {   
            String key = (String) iter.next();   
            paramMap.put(key, jsonObject.get(key));   
        }   
		Map<String,Object> resultData=new HashMap<String,Object>();
		try {
			paramMap=loanFollowUpService.getAllowLoanForm(paramMap);
			resultData.put("apply", paramMap);
		} catch (Exception e) {
			logger.error("跳转到准放款页面异常：", e);
		}
		return new ModelAndView("/loan/allowloan_edit", resultData);
	}
	/**
	 * @Title: goToLoanPage 
	 * @Description: 跳转到放款页面
	 * @author huangwei 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/loanPage")
	@UriKey(key = "com.zdsoft.finance.loan.loanPage")
	public ModelAndView goToLoanPage(HttpServletRequest request){
		String param=request.getParameter("param");
		JSONObject jsonObject = JSONObject.fromObject(param);   
        Map<String,Object> paramMap = new HashMap<String,Object>();   
        for (Iterator<Object> iter = jsonObject.keys(); iter.hasNext();) {   
            String key = (String) iter.next();   
            paramMap.put(key, jsonObject.get(key));   
        }   
		Map<String,Object> resultData=new HashMap<String,Object>();
		try {
			//获取还款表单的其他信息s
			paramMap=loanFollowUpService.getLoanForm(paramMap);
			resultData.put("apply", paramMap);
		} catch (Exception e) {
			logger.error(" 跳转到放款页面异常：", e);
		}
		return new ModelAndView("/loan/loan_edit", resultData);
	}
	/**
	 * @Title: sureAllowLoan 
	 * @Description: 确认准放款
	 * @author huangwei 
	 * @return
	 */
	@RequestMapping("/sureAllowLoan")
	@UriKey(key = "com.zdsoft.finance.loan.sureAllowLoan")
	@ResponseBody
	public ResponseMsg sureAllowLoan(HttpServletRequest request){
		Map<String,String[]> param=request.getParameterMap();
		ResponseMsg msg = new ResponseMsg();
		try {
			loanFollowUpService.sureAllowLoan(param);
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			msg.setMsg("确认准放款失败!");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			logger.error("确认准放款异常：", e);
		}
		return msg;
	}
	/**
	 * @Title: sureAllowLoan 
	 * @Description: 确认准放款
	 * @author huangwei 
	 * @return
	 */
	@RequestMapping("/sureLoan")
	@UriKey(key = "com.zdsoft.finance.loan.sureLoan")
	@ResponseBody
	public ResponseMsg sureLoan(HttpServletRequest request){
		Map<String,String[]> param=request.getParameterMap();
		ResponseMsg msg = new ResponseMsg();
		try {
			loanFollowUpService.sureLoan(param);
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			msg.setMsg("确认放款失败!");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			logger.error("确认放款异常：", e);
		}
		return msg;
	}
	
	/**
	 * @Title: sureAllowLoan 
	 * @Description: 取消准放款
	 * @author huangwei 
	 * @return
	 */
	@RequestMapping("/cancelAllowLoan")
	@UriKey(key = "com.zdsoft.finance.loan.cancelAllowLoan")
	@ResponseBody
	public ResponseMsg cancelAllowLoan(HttpServletRequest request){
		ResponseMsg msg = new ResponseMsg();
		Map<String,String[]> param=request.getParameterMap();
		try {
			loanFollowUpService.cancelAllowLoan(param);
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			msg.setMsg("取消准放款失败!");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			logger.error(" 取消准放款异常：", e);
		}
		return msg;
	}
	
	/**
	 * 
	 * @Title: loanHistoryList 
	 * @Description: 跳转跟进历史
	 * @author huangwei 
	 * @param request
	 * @return
	 */
	@RequestMapping("/loanHistoryList")
	@UriKey(key = "com.zdsoft.finance.loan.loanHistoryList")
	@ResponseBody
	public ModelAndView loanHistoryList(HttpServletRequest request){
		String loanApplyId=request.getParameter("id");
		List<LoanOperateLog> logList=null;
		try {
			logList=loanFollowUpService.getLoanApplyLog(loanApplyId);
		} catch (Exception e) {
			logger.error("跳转跟进历史异常：", e);
		}
		Map<String,Object> resultData =new HashMap<String,Object>();
		resultData.put("log", new Gson().toJson(logList));
		return new ModelAndView("/loan/loanoperatelog_list", resultData);
	}
	/**
	 * @Title: exportLoanApply 
	 * @Description: 导出准放款
	 * @author huangwei 
	 * @param request
	 * @return
	 */
	@RequestMapping("/exportLoanApply")
	@UriKey(key = "com.zdsoft.finance.loan.exportLoanApply")
	@ResponseBody
	public void exportLoanApply(HttpServletRequest request,HttpServletResponse response){
		String codes=request.getParameter("param");
		String[] codeArray=codes.split("\\.");
		//批次号
		String batchCode=codeArray[codeArray.length-1];
		try {
			 String content=loanFollowUpService.exportLoanApply(codeArray);
			 response.setContentType("text/plain");   
			 SimpleDateFormat formart=new SimpleDateFormat("yyyy-MM-dd");
			 //文件名称
	         String fileName = URLEncoder.encode(formart.format(new Date())+"_"+CED.loadSimpleCodeNameByFullCode(batchCode)+"LoanApply", "UTF-8");    
	         response.setHeader("Content-Disposition","attachment; filename=" + fileName + ".txt");   
	         ServletOutputStream  stream=response.getOutputStream();
	         BufferedOutputStream buff = new BufferedOutputStream(stream);  
	         buff.write(content.getBytes("GBK"));
	         buff.flush();       
	         buff.close();
		} catch (Exception e) {
			logger.error("导出准放款异常：", e);
		}
		
	}
	
}
