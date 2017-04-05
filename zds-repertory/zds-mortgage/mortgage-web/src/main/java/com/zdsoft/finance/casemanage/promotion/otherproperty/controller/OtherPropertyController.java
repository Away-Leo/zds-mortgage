package com.zdsoft.finance.casemanage.promotion.otherproperty.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:OtherPropertyController.java
 * @Package:com.zdsoft.finance.casemanage.promotion.otherproperty.controller
 * @Description:兴业贷资调中的其他资产控制器
 * @author: xiongpan
 * @date:2017年2月14日 下午4:32:54
 * @version:v1.0
 */
@Controller
@RequestMapping("/casemanage/datasurvey")
public class OtherPropertyController extends BaseController{
	
	
	/**
	 * 
	 * @Title: otherProperty 
	 * @Description: 跳转到其他资产页面
	 * @author xiongpan
	 * @param caseApplyId 案件id
	 * @return
	 */
	@RequestMapping("/otherProperty")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.otherProperty")
	public ModelAndView otherProperty(String caseApplyId){
		ModelMap model = new ModelMap();
		model.put("caseApplyId", caseApplyId);
		
		return new ModelAndView("casemanage/promotion/promotion_data_research", model);
	}
	
	/**
	 * 
	 * @Title: otherPropertyView 
	 * @Description: 跳转到其他资产查看界面
	 * @author xiongpan
	 * @param caseApplyId 案件id
	 * @return
	 */
	@RequestMapping("/otherPropertyView")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.otherPropertyView")
	public ModelAndView otherPropertyView(String caseApplyId){
		ModelMap model = new ModelMap();
		model.put("caseApplyId", caseApplyId);
		
		return new ModelAndView("casemanage/promotion/promotion_data_research_view", model);
	}
	
	
}
