package com.zdsoft.finance.finance.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.casemanage.datasurvey.service.FeeInfomationService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.ParameterUtil;
import com.zdsoft.finance.customer.entity.BeforeCustomer;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
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
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:CostStandingBookController.java
 * @Package:com.zdsoft.finance.finance.controller
 * @Description: 费用结算台账
 * @author: xiangjx
 * @date:2017年2月27日 下午3:12:18
 * @version:v1.0
 */
@Controller
@RequestMapping(value = "/costStanding")
public class CostStandingBookController extends BaseController {

	@Autowired
	private FeeInfomationService feeInfomationService;

	@Autowired
	private CaseApplyService caseApplyService;
	
	@Autowired
	private CaseApplyBeforeCustomerService  caseApplyBeforeCustomerService;

	/**
	 * 
	 * @Title: index
	 * @Description: 结算台帐列表数据
	 * @author xiangjx
	 * @return
	 */
	@RequestMapping("/index")
	@UriKey(key = "com.zdsoft.finance.finance.costStanding.index")
	@Menu(resource = "com.zdsoft.finance.finance.costStanding.index", label = "费用结算台账", group = "finance", order = 12)
	public ModelAndView index() {
		return new ModelAndView("finance/repayment/cost_standing_book_list");
	}

	/**
	 * 
	 * @Title: view
	 * @Description: 查看详情
	 * @author xiangjx
	 * @param caseApplyId
	 * @return
	 */
	@RequestMapping("/view")
	@UriKey(key = "com.zdsoft.finance.finance.costStanding.view")
	public ModelAndView view(String caseApplyId) {
		ModelMap model = new ModelMap();
		try {
			CaseApply	info = caseApplyService.findOne(caseApplyId);
			CaseApplyVo vo = new CaseApplyVo(info);
			//获取主借人
			List<BeforeCustomer> customerList=caseApplyBeforeCustomerService.findCustomerByCaseApplyIdAndJoinType(info.getId(), CaseApplyBeforeCustomer.MAIN_BORROW);
			for(BeforeCustomer cust:customerList){
				vo.setCustomerName(cust.getCustomerName());
			}
			model.addAttribute("caseApply", vo);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return new ModelAndView("finance/repayment/cost_standing_book_view", model);
	}

	/**
	 * 
	 * @Title: listJson
	 * @Description: 费用台帐列表
	 * @author xiangjx
	 * @param request
	 * @param page
	 * @param vo
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/getCaseApplyAndFeeList")
	@UriKey(key = "com.zdsoft.finance.costStanding.getCaseApplyAndFeeList")
	@ResponseBody
	public String getCaseApplyAndFeeList(HttpServletRequest request, PageRequest page) throws Exception {
		List<QueryObj> li =ParameterUtil.getQueryObjByParameter(request, new String[]{"mca","cus"});
		ResponseMsg msg = new ResponseMsg();
		try {
			Page<Map<String, Object>> list = feeInfomationService.getCaseApplyAndFeeList(page, li); 
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(list.getTotalRows());
			msg.setRows(list.getRecords());
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(msg);
	}
}
