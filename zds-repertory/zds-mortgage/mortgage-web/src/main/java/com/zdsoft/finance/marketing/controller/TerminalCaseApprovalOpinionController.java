package com.zdsoft.finance.marketing.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.ExportExcelUtil;
import com.zdsoft.finance.marketing.entity.TerminalCaseApprovalOpinion;
import com.zdsoft.finance.marketing.service.TerminalCaseApprovalOpinionService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.common.util.StoreHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

import net.sf.json.JSONArray;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: TerminalCaseApprovalOpinionController.java 
 * @ClassName: TerminalCaseApprovalOpinionController 
 * @Description: 终端进件审批意见控制器
 * @author xiongpan
 * @date 2017年3月8日 下午11:18:49 
 * @version V1.0 
 */ 
@Controller
@RequestMapping("/terminalCaseApprovalOpinion")
public class TerminalCaseApprovalOpinionController extends BaseController{

	@Autowired
	private TerminalCaseApprovalOpinionService terminalCaseApprovalOpinionService;
	@Autowired
	private CED CED;

	
	/**
	 * 
	 * @Title: saveTerminalCaseApprovalOpinion 
	 * @Description: 批量保存终端进件审批意见
	 * @author xiongpan
	 * @param id 需要保存的所有案件的id字符串
	 * @param terminalCaseApprovalOpinion 需要保存的所有案件的id字符串
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/saveTerminalCaseApprovalOpinion")
	@UriKey(key = "com.zdsoft.finance.marketing.terminalCaseApprovalOpinion.saveTerminalCaseApprovalOpinion")
	@ResponseBody
	public String saveTerminalCaseApprovalOpinion(String ids, TerminalCaseApprovalOpinion terminalCaseApprovalOpinion) throws BusinessException {
		ResponseMsg msg = new ResponseMsg();
		try {
			terminalCaseApprovalOpinionService.saveTerminalCaseApprovalOpinion(ids, terminalCaseApprovalOpinion);
			msg.setMsg("保存成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMsg("操作异常！" + e.getMessage());
		}

		return ObjectHelper.objectToJson(msg);
	}
	
	/**
	 * 
	 * @Title: submitTerminalCaseApprovalOpinion 
	 * @Description: 批量提交终端进件审批意见
	 * @author xiongpan
	 * @param id 需要保存的所有案件的id字符串
	 * @param terminalCaseApprovalOpinion 需要保存的所有案件的id字符串
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/submitTerminalCaseApprovalOpinion")
	@UriKey(key = "com.zdsoft.finance.marketing .terminalCaseApprovalOpinion.submitTerminalCaseApprovalOpinion")
	@ResponseBody
	public String submitTerminalCaseApprovalOpinion(String ids, TerminalCaseApprovalOpinion terminalCaseApprovalOpinion) throws BusinessException {
		ResponseMsg msg = new ResponseMsg();
		try {
			terminalCaseApprovalOpinionService.submitTerminalCaseApprovalOpinion(ids, terminalCaseApprovalOpinion);
			msg.setMsg("提交成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMsg("操作异常！" + e.getMessage());
		}
		
		return ObjectHelper.objectToJson(msg);
	}	
	
	/**
	 * 
	 * @Title: exportTerminalCase 
	 * @Description: 终端进件导出
	 * @author xiongpan
	 * @param fileName 导出文件名称
	 * @param exportTerminalCaseVos 前台需要导出的数据
	 * @param jsoncallback json数据
	 * @param request 请求
	 * @param response 响应
	 */
    @RequestMapping(value = "/exportTerminalCase", produces = "text/html;charset=UTF-8")
    @UriKey(key = "com.zdsoft.finance.marketing.terminalCaseApprovalOpinion.exportTerminalCase")
    public void exportTerminalCase(String fileName, String jsoncallback, HttpServletRequest request, HttpServletResponse response){
    	try {
    		String servicePath = request.getSession().getServletContext().getRealPath("/");
    		logger.info("获得的服务器根路径为：》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》" + servicePath);
			fileName = fileName + System.currentTimeMillis() + ".xls";
    		
    		List<Map<String,Object>> paras = terminalCaseApprovalOpinionService.terminalCaseDataMap(request.getParameter("content"));
    		
    		ExportExcelUtil.exportExcel(request, response, paras, fileName);
    		
			
		} catch (Exception e) {
            logger.error("导出Excel出错！", e);
            e.printStackTrace();
		}
    	
    }
    
    /**
     * 
     * @Title: importTerminalCases 
     * @Description: 终端进件导入
     * @author xiongpan
     * @param multipartRequest 导入文件的请求
     * @param request Http请求
     * @return
     */
    @ResponseBody
    @RequestMapping("/importTerminalCases")
    @UriKey(key = "com.zdsoft.finance.marketing.terminalCaseApprovalOpinion.importTerminalCases")
    public String importTerminalCases(MultipartHttpServletRequest multipartRequest,HttpServletRequest request){
		logger.debug("upload---------终端进件Excel开始上传------------------------>start");
		ResponseMsg msg = new ResponseMsg();
		try {
			logger.error("终端进件Excel开始上传-------" + StoreHelper.getUserToken());
			String zdsUserToken = request.getParameter("zdsUserToken");
			if (StoreHelper.getUserToken() == null && ObjectHelper.isNotEmpty(zdsUserToken)) {
				StoreHelper.setUserToken(zdsUserToken);
			}
			CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("Filedata");
			if (null == file || file.isEmpty()) {
				msg.setResultStatus(ResponseMsg.APP_ERROR);
				msg.setMsg("终端进件Excel为空");
				logger.error("上传终端进件Excel失败，终端进件Excel不能为空!");
			} else {
				if(ObjectHelper.isEmpty(file)){
					msg.setResultStatus(ResponseMsg.APP_ERROR);
					msg.setMsg("终端进件Excel为空");
				}else{
					terminalCaseApprovalOpinionService.importTerminalCases(file,msg);
				}
				
			}
		} catch (Exception e) {
			logger.error("终端进件Excel上传异常", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("导入失败");
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("resultStatus", msg.getResultStatus());
		result.put("msg", msg.getMsg());
		JSONArray json = JSONArray.fromObject(result);
		logger.debug("upload---------终端进件Excel上传处理完成------------------------>end");
		return "[" + json.get(0).toString() + "]";
		
    }
    
    /**
     * 
     * @Title: channelManagerByOrg 
     * @Description: 通过机构code查询该机构的渠道经理(暂且是改机构所有员工,没加角色控制)
     * @author xiongpan
     * @param jsoncallback 返回json数据
     * @param organizationCd 机构code
     * @return
     */
	@RequestMapping("/channelManagerByOrgCd")
	@UriKey(key = "com.zdsoft.finance.marketing.terminalCaseApprovalOpinion.channelManagerByOrgCd")
	@ResponseBody
    public String channelManagerByOrg(String jsoncallback,String organizationCd){
		List<Map<String,String>> empList = new ArrayList<Map<String,String>>();
		try {
			List<EmpDto> allEmpDtoByOrgCd = CED.getAllEmpDtoByOrgCd(organizationCd);
			if(ObjectHelper.isNotEmpty(allEmpDtoByOrgCd)){
				for(EmpDto emp : allEmpDtoByOrgCd){
					Map<String,String> empMap = new HashMap<String,String>();
					empMap.put("empCd", emp.getEmpCd());
					empMap.put("empNm", emp.getEmpNm());
					empList.add(empMap);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取该机构的渠道经理失败", e);
		}
    	
		return ObjectHelper.objectToJson(empList, jsoncallback);
    }
	
/*	@ResponseBody
	@RequestMapping("/getEmpByEmpCode")
	@UriKey(key="com.zdsoft.finance.marketing.terminalCaseApprovalOpinion.getEmpByEmpCode")
	public String getEmpByEmpCode(String jsoncallback,String channelManagerCd){
		Map<String,String> empMap = new HashMap<String,String>();
		try {
			EmpDto emp = CED.loadEmployeeByCode(channelManagerCd);
			empMap.put("empCd", emp.getEmpCd());
			empMap.put("empNm", emp.getEmpNm());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据员工code为找到该员工", e);
		}
		
		return ObjectHelper.objectToJson(empMap, jsoncallback);
	}*/
	
}
