package com.zdsoft.finance.casemanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:FinancialReviewController.java
 * @Package:com.zdsoft.finance.casemanage.controller
 * @Description:财务复核流程
 * @author: laijun
 * @date:2017年1月13日 下午3:15:13
 * @version:v1.0
 */
@Controller
@RequestMapping("/casemanage/financialreview")
public class FinancialReviewController extends BaseController{
	/**
	 * 
	 * 财务复核
	 *
	 * @author laijun
	 * @date:2017年1月10日 下午9:38:42
	 * @param caseApplyId
	 * @return
	 */
	@RequestMapping(value = "/edit")
	@UriKey(key = "com.zdsoft.finance.casemanage.financialreview.edit")
	public ModelAndView edit(String caseApplyId) {
		ModelMap model = new ModelMap();
		model.put("caseApplyId", caseApplyId);
		return new ModelAndView("casemanage/financialreview/financialreview_edit", model);

	}
}
