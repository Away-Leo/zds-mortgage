package com.zdsoft.finance.busilog.controller;

import com.zdsoft.finance.busilog.entity.BusinessLog;
import com.zdsoft.finance.busilog.entity.LogType;
import com.zdsoft.finance.busilog.service.BusinessLogService;
import com.zdsoft.finance.busilog.vo.BusinessLogVo;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 业务日志Controller
 * 
 * @author liuwei
 *
 */
@Controller
@RequestMapping("/businessLog")
public class BusinessLogController extends BaseController {

	@Autowired
	BusinessLogService businessLogService;

	@Menu(resource = "com.zdsoft.finance.busilog.initLogList", label = "业务日志", group = "busiLog", order = 1)
	@RequestMapping("/initLogList")
	@UriKey(key = "com.zdsoft.finance.busilog.initLogList")
	public ModelAndView initLogList() {
		return new ModelAndView("busilog/busilog_list");
	}

	@RequestMapping("/getLogList")
	@ResponseBody
	@UriKey(key = "zf.businesslog.getLogList")
	public String getLogList(HttpServletRequest request, PageRequest pageable, String jsoncallback) {

		@SuppressWarnings("unchecked")
		List<QueryObj> listObj = (List<QueryObj>) request.getAttribute("listObj");
		Page<BusinessLog> businessLogs = businessLogService.findByHqlConditions(pageable, listObj);
		List<BusinessLogVo> vos = new ArrayList<BusinessLogVo>();
		ResponseMsg msg = new ResponseMsg();
		if (ObjectHelper.isNotEmpty(businessLogs)) {

			for (BusinessLog businessLogEntity : businessLogs.getRecords()) {
				try {
					BusinessLogVo vo = new BusinessLogVo(businessLogEntity);
					vos.add(vo);
				} catch (BusinessException e) {
					e.printStackTrace();
				}
			}
			msg.setTotal(businessLogs.getTotalRows());
			msg.setRows(vos);
		} else {
			msg.setRows(new ArrayList<BusinessLogVo>());
			msg.setTotal(0L);
			msg.setResultStatus(ResponseMsg.SUCCESS);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	@RequestMapping("/saveBusinessLog")
	@UriKey(key = "zf.businesslog.saveBusinessLog")
	@ResponseBody
	public ResponseMsg saveBusinessLog(BusinessLogVo businessLogVo, String jsoncallback) {
		BusinessLog businessLog = null;
		ResponseMsg msg = new ResponseMsg();

		// 将Vo转成Po
		businessLog = businessLogVo.toPo();

		// 设置必要参数
		businessLog.setOperatorTime(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
		businessLog.setLogType(LogType.MANUAL.value);

		// 执行保存
		try {
			businessLogService.saveOrUpdateEntity(businessLog);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("businessLog保存失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("系统内部错误，请查看日志");
		}
		return msg;
	}

}
