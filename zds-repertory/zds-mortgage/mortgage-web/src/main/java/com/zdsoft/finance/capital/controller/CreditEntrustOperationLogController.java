package com.zdsoft.finance.capital.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.finance.capital.entity.CreditEntrustOperationLog;
import com.zdsoft.finance.capital.service.CreditEntrustOperationLogService;
import com.zdsoft.finance.capital.vo.CreditEntrustOperationLogVo;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 信托计划操作日志controller
 * 
 * @createTime:2017年1月12日
 * @author liuwei
 * @version 1.0
 */
@Controller
@RequestMapping("/creditOperationLog")
public class CreditEntrustOperationLogController extends BaseController {

	@Autowired
	CreditEntrustOperationLogService creditEntrustOperationLogService;

	/**
	 * 查询列表
	 * 
	 * @param request
	 *            请求
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/getCreditLogs")
	@UriKey(key = "com.zdsoft.finance.capital.getCreditLogs")
	@ResponseBody
	public String getCreditLogs(HttpServletRequest request, String jsoncallback, PageRequest pageable) {

		// 获取查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");

		// 如果没有传递参数，则直接不进行查询
		if (ObjectHelper.isEmpty(queryObjs)) {
			ResponseMsg msg = new ResponseMsg();
			msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(0L);
			msg.setRows(new ArrayList<CreditEntrustOperationLogVo>());
			return ObjectHelper.objectToJson(msg, jsoncallback);
		}

		// 获取资方机构配置信息
		Page<CreditEntrustOperationLog> logPage = creditEntrustOperationLogService.findByHqlConditions(pageable,
				queryObjs);
		List<CreditEntrustOperationLogVo> logsVo = new ArrayList<CreditEntrustOperationLogVo>();

		for (CreditEntrustOperationLog log : logPage.getRecords()) {
			CreditEntrustOperationLogVo logVo = new CreditEntrustOperationLogVo(log, new String[] {}, new String[] {});

			logsVo.add(logVo);
		}
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setTotal(logPage.getTotalRows());
		msg.setRows(logsVo);
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
}