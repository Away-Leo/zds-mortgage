package com.zdsoft.finance.parameter.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.parameter.entity.PrePayRule;
import com.zdsoft.finance.parameter.entity.PrePayRule;
import com.zdsoft.finance.parameter.service.PrePayRuleService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;
@RequestMapping("/paramter")
@Controller
public class PrePayRuleController extends BaseController{
	@Autowired
	private PrePayRuleService prePayRuleService;
	/**
	 * 提前还款违约金管理初始化
	 * 
	 * @return 提前还款违约金管理页面
	 */
	@RequestMapping("/initPrePayRule")
	@UriKey(key = "com.zdsoft.finance.parameter.initPrePayRule")
	@Menu(resource = "com.zdsoft.finance.parameter.initPrePayRule", label = "提前还款违约金管理", group = "businessSetting", order = 1)
	public ModelAndView initPrePayRule() {
		return new ModelAndView("/parameter/prepayrle_list");
	}
	
	/**
	 * 提前还款违约金列表
	 * @param request
	 * @param jsoncallback
	 * @param pageable
	 * @return
	*/
	@RequestMapping("/prePayRule")
	@UriKey(key = "com.zdsoft.finance.parameter.getPrePayRule")
	@ResponseBody
	public String getPrePayRule(HttpServletRequest request, String jsoncallback, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();
		try
		{
			// 获取页面封装的查询参数
			List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
			// 分页抵押权人
			Page<PrePayRule> prePayRulePage = prePayRuleService.findByHqlConditions(pageable, queryObjs);
			//msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(prePayRulePage.getTotalRows());
			msg.setRows(prePayRulePage.getRecords());
		}
		catch(Exception e)
		{
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
}
