package com.zdsoft.finance.afterloan.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.afterloan.service.AfterMonitorRecordService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.utils.ParameterUtil;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: LoanMiddleMonitor.java 
 * @ClassName: LoanMiddleMonitor 
 * @Description: 贷中监控
 * @author xj 
 * @date 2017年2月9日 上午9:51:00 
 * @version V1.0
 */
@Controller
@RequestMapping("loanMiddleMonitor")
public class LoanMiddleMonitorController extends BaseController {
	@Autowired
	private AfterMonitorRecordService afterMonitorRecordService;
	@Autowired
	private CED CED;
	
	/**
	 * 
	 * @Title: init 
	 * @Description: 跳转到贷中监控列表
	 * @author xj 
	 * @return
	 */
	@RequestMapping("/init")
	@UriKey(key = "com.cnfh.rms.loanMiddleMonitor.init")
	@Menu(resource = "com.cnfh.rms.loanMiddleMonitor.init", label = "贷中监控", group = "afterloan", order = 3)
	public String init(){
		return "/afterloan/loanMiddleMonitor/loanMiddleMonitor_list";
	}
	
	/**
	 * 
	 * @Title: listCaseApply 
	 * @Description: 查询案件基本信息和其他其他信息列表
	 * @author xj 
	 * @param jsoncallback
	 * @param controlType  贷前还是贷后监控
	 * @return
	 */
	@RequestMapping("/listCaseApply")
	@UriKey(key = "com.cnfh.rms.loanMiddleMonitor.listCaseApply")
	@ResponseBody
	public String listCaseApply(PageRequest pageable,String jsoncallback,HttpServletRequest request,String controlType){
		ResponseMsg msg = new ResponseMsg();
		try {
			List<QueryObj> queryObjs = ParameterUtil.getQueryObjByParameter(request, new String[]{"ma","cafc","mr"});
			Page<Map<String, Object>> page = afterMonitorRecordService.findPageMonitorRecord(pageable, queryObjs,controlType);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(page.getTotalRows());
			msg.setRows(page.getRecords());
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			logger.error("查询案件基本信息和其他其他信息列表异常：", e);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
		
	}
	
	/**
	 * 
	 * @Title: importConnectLand 
	 * @Description: 跳转到导入汇法页面
	 * @author xj 
	 * @param caseApplyId
	 * @return
	 */
	@RequestMapping("/importConnectLand")
	@UriKey(key = "com.cnfh.rms.loanMiddleMonitor.importConnectLand")
	public ModelAndView importConnectLand (String caseApplyId) {
		ModelMap modelMap = new ModelMap();
		try {
			//发送人
			EmpDto loginUser = CED.getLoginUser();
			String empNm = loginUser.getEmpNm();
			modelMap.put("sender", empNm);
			//发送时间
			modelMap.put("sendTime", DateHelper.dateToString(new Date(), DateHelper.DATE_SHORT_FORMAT));
		
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("跳转到导入汇法页面", e);
		}
		return new ModelAndView("/afterloan/loanMiddleMonitor/importConnectLand_list",modelMap);
	}
	
	/**
	 * 
	 * @Title: saveOrsubmitConnectLand 
	 * @Description: 保存或者提交待发送清单
	 * @author xj 
	 * @param ids
	 * @param isSubmit
	 * @return
	 */
	@RequestMapping("/saveOrsubmitConnectLand")
	@UriKey(key = "com.cnfh.rms.loanMiddleMonitor.saveOrsubmitConnectLand")
	@ResponseBody
	public ResponseMsg saveOrsubmitConnectLand(String[] ids){
		ResponseMsg msg = new ResponseMsg();
		try {	
			afterMonitorRecordService.saveOrsubmitConnectLand(ids);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("保存或者提交待发送清单成功！");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存或者提交待发送清单失败", e);
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			msg.setMsg("系统内部错误，请查看日志");
		}
		return msg;
		
	}
	

}
