package com.zdsoft.finance.workbench.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.workbench.entity.OverdueCase;
import com.zdsoft.finance.workbench.service.OverdueCaseService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: OverdueCaseController.java 
 * @ClassName: OverdueCaseController 
 * @Description: 逾期案件控制器
 * @author longwei 
 * @date 2017年2月23日 上午9:38:27 
 * @version V1.0
 */
@Controller
@RequestMapping("/overdueCase")
public class OverdueCaseController extends BaseController {

	@Autowired
	private OverdueCaseService overdueCaseService;
	
	/**
	 * @Title: init 
	 * @Description: 逾期案件列表页面
	 * @author longwei
	 * @return
	 */
	@RequestMapping("/init")
	@UriKey(key = "com.zdsoft.finance.workbench.overdueCase.init")
	@Menu(resource = "com.zdsoft.finance.workbench.overdueCase.init", label = "逾期案件", group = "workbench", order = 1)
	public ModelAndView init() {
		return new ModelAndView("/workbench/my_overdue_case_list");
	}
	
	/**
	 * @Title: getOverdueCaseList 
	 * @Description: 逾期案件列表
	 * @author longwei
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getOverdueCaseList")
	@UriKey(key = "com.zdsoft.finance.workbench.overdueCase.getOverdueCaseList")
	public ResponseMsg getOverdueCaseList(OverdueCase overdueCase,PageRequest pageable) {
		ResponseMsg msg=new ResponseMsg();
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			map.put("applayCaseCode", overdueCase.getApplayCaseCode());
			map.put("orgCode", overdueCase.getOrgCode());
			if(ObjectHelper.isEmpty(overdueCase.getOverdueDays())){
				map.put("overdueDays",OverdueCase.DAYS_0);	
			}else{
			    map.put("overdueDays", overdueCase.getOverdueDays());
			}
			map.put("paymentAmount", overdueCase.getPaymentAmount());
			map.put("paymentAmountEnd", overdueCase.getPaymentAmountEnd());
			map.put("startloanDate", overdueCase.getStartloanDate());
			map.put("endLoanDate", overdueCase.getEndLoanDate());
			map.put("caseapplystatus", overdueCase.getAfterLoanStatus());
			Page<Map<String, Object>> page=overdueCaseService.findPageOverdueCase(map, pageable);
			msg.setRows(page.getRecords());
			msg.setTotal(page.getTotalRows());
		} catch (Exception e) {
			logger.error("查询列表错误",e);
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("查询列表错误");
		}
		return msg;
	}
	
}
