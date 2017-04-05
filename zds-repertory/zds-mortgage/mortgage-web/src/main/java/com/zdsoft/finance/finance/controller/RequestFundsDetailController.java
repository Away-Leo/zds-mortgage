package com.zdsoft.finance.finance.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.finance.entity.RequestFundsDetail;
import com.zdsoft.finance.finance.service.RequestFundsDetailService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RequestFundsDetailController.java 
 * @ClassName: RequestFundsDetailController 
 * @Description: 费用请款明细Controller
 * @author jincheng 
 * @date 2017年2月17日 下午4:54:31 
 * @version V1.0
 */
@Controller
@RequestMapping("requestFundsDetail")
public class RequestFundsDetailController extends BaseController {
	 
    @Autowired
    private RequestFundsDetailService requestFundsDetailService;
    
    /**
     * @Title: getRequestFundsList 
     * @Description: 获取请款费用明细列表
     * @author jincheng 
     * @param request
     * @param jsoncallback
     * @param pageable
     * @return
     * @throws Exception
     */
    @RequestMapping("/getRequestFundsDetailList")
	@UriKey(key = "com.zdsoft.finance.requestFunds.getRequestFundsDetailList")
	@ResponseBody
	public ResponseMsg getRequestFundsList(HttpServletRequest request, PageRequest pageable) throws Exception {
		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		Page<RequestFundsDetail> page = requestFundsDetailService.findByHqlConditions(pageable, queryObjs);
		ResponseMsg msg = new ResponseMsg();
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setTotal(page.getTotalRows());
		msg.setRows(page.getRecords());
		return msg;
	}
	

}
