package com.zdsoft.finance.loan.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.loan.service.CaseLoanApplyHistoryService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;
/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CaseLoanApplyHistoryController.java 
 * @ClassName: CaseLoanApplyHistoryController 
 * @Description: 放款请款历史台帐controller
 * @author huangwei 
 * @date 2017年2月24日 上午10:01:43 
 * @version V1.0
 */
@RequestMapping("/loan")
@Controller
public class CaseLoanApplyHistoryController extends BaseController{
	@Autowired
	private CaseLoanApplyHistoryService caseLoanApplyHistoryService;
	/**
	 * @Title: initLoanApply 
	 * @Description: 放款请款初始化
	 * @author huangwei 
	 * @return
	 */
	@RequestMapping("/initLoanApplyHistory")
	@UriKey(key = "com.zdsoft.finance.loan.initLoanApplyHistory")
	@Menu(resource = "com.zdsoft.finance.loan.initLoanApplyHistory", label = "放款请款历史台帐", group = "loan", order = 3)
	public ModelAndView initLoanApply() {
		return new ModelAndView("/loan/caseloanandapply_history_list");
	}
	/**
	 * @Title: getLoanApply 
	 * @Description: 初始化放款请款历史台帐列表
	 * @author huangwei 
	 * @param request
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/loanApplyHistory")
	@UriKey(key = "com.zdsoft.finance.loan.loanApplyHistory")
	@ResponseBody
	public String getLoanApply(HttpServletRequest request, String jsoncallback, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();
		try
		{
			// 获取页面封装的查询参数
			List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
			// 分页案件
			Page<Map<String, Object>> caseApplyPage = caseLoanApplyHistoryService.getLoanApplyHistory(pageable, queryObjs);
			//msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(caseApplyPage.getTotalRows());
			msg.setRows(caseApplyPage.getRecords());
		}
		catch(Exception e){
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			e.printStackTrace();
			logger.error("初始化放款请款历史台帐列表异常：", e);
		}

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	

}
