package com.zdsoft.finance.afterloan.controller;

import com.zdsoft.finance.afterloan.entity.AfterLoanReview;
import com.zdsoft.finance.afterloan.service.AfterCheckService;
import com.zdsoft.finance.afterloan.vo.AfterLoanReviewVo;
import com.zdsoft.finance.common.exception.BusinessException;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: LoanMonitorQueryController.java 
 * @ClassName: LoanMonitorQueryController 
 * @Description: 贷后监控查询Controller
 * @author zhoushichao 
 * @date 2017-02-13 09:46:14
 * @version V1.0 
 */ 
@Controller
@RequestMapping("/loanMonitorQuery")
public class LoanMonitorQueryController extends BaseController {

	@Autowired
	AfterCheckService afterCheckService;
	@Autowired
	private CaseApplyService caseApplyService;
	@Autowired
	private BeforePersonalCustomerService beforePersonalCustomerService;
	
	/**
	 * 
	 * @Title: LoanMonitorQueryList 
	 * @Description: 贷后监控查询列表菜单注册入口
	 * @author zhoushichao 
	 * @return
	 */
	@RequestMapping("/loanMonitorQueryList")
	@UriKey(key = "com.zdsoft.finance.afterloan.loanMonitorQuery.loanMonitorQueryList")
	@Menu(resource = "com.zdsoft.finance.afterloan.loanMonitorQuery.loanMonitorQueryList", label = "贷后监控查询", group = "afterloan", order = 13)
	public ModelAndView LoanMonitorQueryList() {
		return new ModelAndView("afterloan/loanMonitorQuery/loan_monitor_query_list");
	}


	/**
	 * @Title: getLoanMonitorQueryList
	 * @Description: 贷后监控查询
	 * @author liaoguowei
	 * @param vo
	 * @param jsoncallback
	 * @param pageable
	 * @return java.lang.String
	 * @throws
	 */
	@RequestMapping("/getLoanMonitorQueryList")
	@UriKey(key = "com.zdsoft.finance.afterloan.loanMonitorQuery.getLoanMonitorQueryList")
	@ResponseBody
	public String getLoanMonitorQueryList(AfterLoanReviewVo vo, String jsoncallback, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();
		try {
			Page<AfterLoanReview> caseApplyPage = afterCheckService.findAfterLoanReviewByCondition(pageable, vo.toPo());
			List<AfterLoanReviewVo> returnData=new ArrayList<AfterLoanReviewVo>();
			for(AfterLoanReview temp:caseApplyPage.getRecords()){
				AfterLoanReviewVo afterLoanReviewVo=new AfterLoanReviewVo(temp);
				returnData.add(afterLoanReviewVo);
			}
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(caseApplyPage.getTotalRows());
			msg.setRows(returnData);
		} catch (Exception e) {
			msg.setMsg(e.getMessage());
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			e.printStackTrace();
			logger.error("分页查询贷后监控查询出错", e);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 
	 * @Title: viewLoanMonitorQuery 
	 * @Description: 案件号详情入口页面
	 * @author zhoushichao 
	 * @param caseApplyId
	 * @return
	 */
	@RequestMapping("/viewLoanMonitorQuery")
	@UriKey(key = "com.zdsoft.finance.afterloan.loanMonitorQuery.viewLoanMonitorQuery")
	public ModelAndView viewLoanMonitorQuery(String caseApplyId) {
		ModelMap modelMap = new ModelMap();
		
		try {
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
			//TODO 汇法网反馈的预警信息
			//TODO 工商预警信息
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("跳转到案件号详情页面出错", e);
		}
		
		return new ModelAndView("afterloan/loanMonitorQuery/loan_monitor_query_view",modelMap);
	}
	
	/**
	 * 
	 * @Title: eidtHandle 
	 * @Description: 处理入口页面
	 * @author zhoushichao 
	 * @param caseApplyId
	 * @return
	 */
	@RequestMapping("/eidtHandle")
	@UriKey(key = "com.zdsoft.finance.afterloan.loanMonitorQuery.eidtHandle")
	public ModelAndView eidtHandle(String caseApplyId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("afterloan/loanMonitorQuery/Handle_eidt");
		return modelAndView;
	}
}
