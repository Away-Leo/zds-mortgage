package com.zdsoft.finance.afterloan.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.zdsoft.finance.afterloan.entity.AfterCheck;
import com.zdsoft.finance.afterloan.service.AfterCheckService;
import com.zdsoft.finance.afterloan.vo.AfterCheckVo;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.DateUtil;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.service.BeforePersonalCustomerService;
import com.zdsoft.finance.customer.vo.BeforePersonalCustomerVo;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterCheckController.java 
 * @ClassName: AfterCheckController 
 * @Description: 贷后检查Controller
 * @author zhoushichao 
 * @date 2017年2月8日 下午2:02:31 
 * @version V1.0 
 */ 
@Controller
@RequestMapping("/afterCheck")
public class AfterCheckController extends BaseController {

	@Autowired
	AfterCheckService afterCheckService;
	@Autowired
	private CaseApplyService caseApplyService;
	@Autowired
	private BeforePersonalCustomerService beforePersonalCustomerService;
	@Autowired
	private CED CED;
	
	/**
	 * 
	 * @Title: AfterCheckList 
	 * @Description: 贷后检查列表菜单注册入口
	 * @author zhoushichao 
	 * @return
	 */
	@RequestMapping("/afterCheckList")
	@UriKey(key = "com.zdsoft.finance.afterloan.afterCheck.afterCheckList")
	@Menu(resource = "com.zdsoft.finance.afterloan.afterCheck.afterCheckList", label = "贷后检查", group = "afterloan", order = 11)
	public ModelAndView AfterCheckList() {
		return new ModelAndView("afterloan/afterCheck/after_check_list");
	}
	
	/**
	 * 
	 * @Title: getAfterCheckList 
	 * @Description: 查询贷后检查列表数据
	 * @author zhoushichao 
	 * @param request  请求查询参数
	 * @param jsoncallback
	 * @param pageable  分页信息
	 * @return
	 */
	@RequestMapping("/getAfterCheckList")
	@UriKey(key = "com.zdsoft.finance.afterloan.afterCheck.getAfterCheckList")
	@ResponseBody
	public String getAfterCheckList(HttpServletRequest request, String jsoncallback, PageRequest pageable) {

		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		
		ResponseMsg msg = new ResponseMsg();

		try {
			Page<Map<String, Object>> caseApplyPage = afterCheckService.findPageAfterCheck(pageable, queryObjs);
			msg.setMsg("贷后检查列表查询成功!");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(caseApplyPage.getTotalRows());
			msg.setRows(caseApplyPage.getRecords());
		} catch (Exception e) {
			msg.setMsg("贷后检查列表查询失败!");
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			e.printStackTrace();
			logger.error("贷后检查列表查询失败:",e);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 
	 * @Title: editAfterCheck 
	 * @Description: 检查入口页面
	 * @author zhoushichao 
	 * @param caseApplyId  案件Id
	 * @return
	 */
	@RequestMapping("/editAfterCheck")
	@UriKey(key = "com.zdsoft.finance.afterloan.afterCheck.editAfterCheck")
	public ModelAndView editAfterCheck(String caseApplyId) {
		ModelMap modelMap = new ModelMap();
		
		try {
			//获取登陆人信息
			EmpDto emp = CED.getLoginUser();
			modelMap.put("trackerName", emp.getEmpNm());
			//案件信息 caseApplyVo
			CaseApply caseApply = caseApplyService.findOne(caseApplyId);
			CaseApplyVo caseApplyVo = new CaseApplyVo(caseApply);
			modelMap.put("caseApplyVo", caseApplyVo);
			//获取主借人信息
			List<BeforePersonalCustomer> beforePersonalCustomers = beforePersonalCustomerService.queryByCaseApplyId(caseApplyId);
			BeforePersonalCustomer beforePersonalCustomer = null;
			for (BeforePersonalCustomer bcs : beforePersonalCustomers) {
				if(CaseApplyBeforeCustomer.MAIN_BORROW.equals(bcs.getJoinType())){
					beforePersonalCustomer = bcs;
					break;
				}
			}
			BeforePersonalCustomerVo beforePersonalCustomerVo = new BeforePersonalCustomerVo(beforePersonalCustomer);
			modelMap.put("persCustomerVo", beforePersonalCustomerVo);
			//获取系统当前时间
			Long currentDate = DateUtil.getCurrentDate();
			modelMap.put("currentDate", currentDate);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("跳转到检查页面出错", e);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("获取登陆人信息失败："+e);
		}
		
		return new ModelAndView("afterloan/afterCheck/after_check_edit",modelMap);
	}
	
	/**
	 * 
	 * @Title: saveAfterCheck 
	 * @Description: 保存或修改检查信息
	 * @author zhoushichao 
	 * @param afterCheckVo  检查信息
	 * @return
	 */
	@RequestMapping("/saveAfterCheck")
	@UriKey(key = "com.zdsoft.finance.afterloan.afterCheck.saveAfterCheck")
	@ResponseBody
	public ResponseMsg saveAfterCheck(AfterCheckVo afterCheckVo) {
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> afterCheckMap = new HashMap<String, Object>();
		
		// 将Vo转成Po
		AfterCheck afterCheck = afterCheckVo.toPO();
		try {
			afterCheck = afterCheckService.saveOrUpdateAfterCheck(afterCheck);
			afterCheckMap.put("afterCheck", afterCheck);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setOptional(afterCheckMap);
			msg.setMsg("保存检查信息成功！");
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			msg.setMsg("保存检查信息失败！");
			e.printStackTrace();
			logger.error("保存检查信息失败:",e);
		}
		return msg;
	}
	
	/**
	 * 
	 * @Title: getAfterCheckPage 
	 * @Description: 查询检查信息列表
	 * @author zhoushichao 
	 * @param caseApplyId  案件Id
	 * @param pageRequest  分页信息
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/getAfterCheckPage")
	@UriKey(key="com.zdsoft.finance.afterloan.afterCheck.getAfterCheckPage")
	@ResponseBody
	public String getAfterCheckPage(String caseApplyId,PageRequest pageRequest,String jsoncallback){
		ResponseMsg msg=new ResponseMsg();
		
		try {
			Page<AfterCheck> afterCheckPage = afterCheckService.findByCaseApplyId(caseApplyId, pageRequest);
			List<AfterCheckVo> list = new ArrayList<AfterCheckVo>();
			for(AfterCheck afterCheck : afterCheckPage.getRecords()){
				list.add(new AfterCheckVo(afterCheck));
			}
			msg.setRows(list);
			msg.setTotal(afterCheckPage.getTotalRows());
		} catch (Exception e) {
			logger.error("查询检查信息列表失败",e);
			msg.setMsg("查询检查信息列表失败");
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 
	 * @Title: viewAfterCheck 
	 * @Description: 检查详情入口页面
	 * @author zhoushichao 
	 * @param id 检查详情Id
	 * @return
	 */
	@RequestMapping("/viewAfterCheck")
	@UriKey(key = "com.zdsoft.finance.afterloan.afterCheck.viewAfterCheck")
	public ModelAndView viewAfterCheck(String id) {
		ModelMap modelMap = new ModelMap();
		try {
			AfterCheck afterCheck = afterCheckService.findOne(id);
			AfterCheckVo afterCheckVo = new AfterCheckVo(afterCheck);
			modelMap.put("afterCheckVo", afterCheckVo);
		} catch (BusinessException e) {
			logger.error("跳转到检查详情页面出错", e);
			e.printStackTrace();
		}
		
		return new ModelAndView("afterloan/afterCheck/after_check_view",modelMap);
	}
}
