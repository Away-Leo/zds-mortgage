package com.zdsoft.finance.marketing.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:CaseApplyContioller.java
 * @Package:com.zdsoft.finance.marketing.controller
 * @Description:案件申请Controller
 * @author: zhoushichao
 * @date:2017年1月14日 下午8:55:00
 * @version:v1.0
 */
@Controller
@RequestMapping("/caseApply")
public class CaseApplyContioller extends BaseController {

	@Autowired
	CaseApplyService caseApplyService;
	
	/**
	 * 案件查询列表菜单注册入口
	 */
	@RequestMapping("/caseApplyList")
	@UriKey(key = "com.zdsoft.finance.marketing.caseApplyList")
	@Menu(resource = "com.zdsoft.finance.marketing.caseApplyList", label = "案件查询", group = "marketing", order = 14)
	public ModelAndView caseApplyList() {
		return new ModelAndView("marketing/case_apply_list");
	}
	
	/**
	 * 案件查询查询列表
	 * @param request
	 *            请求
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/getCaseApplyList")
	@UriKey(key = "com.zdsoft.finance.marketing.getCaseApplyList")
	@ResponseBody
	public String getCaseApplyList(HttpServletRequest request, String jsoncallback, PageRequest pageable) throws Exception {

		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		

		Page<Map<String, Object>> caseApplyPage = caseApplyService.findPageCaseApply(pageable, queryObjs);

		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功!");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setTotal(caseApplyPage.getTotalRows());
		msg.setRows(caseApplyPage.getRecords());

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
}
