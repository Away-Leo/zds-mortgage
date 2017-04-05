package com.zdsoft.finance.app.submitprocess.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.bpm.dto.TaskInstanceDto;
import com.zdsoft.bpm.service.client.BPM;
import com.zdsoft.finance.common.utils.AppStatus;
import com.zdsoft.finance.common.utils.app.AppServerUtil;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: SubmitProcessController.java
 * @ClassName: SubmitProcessController
 * @Description: 流程提交Controller
 * @author yangjia
 * @date 2017年3月1日 下午5:45:23
 * @version V1.0
 */
@RequestMapping("/server/submitProcess")
@Controller
public class SubmitProcessController extends BaseController {

	@Autowired
	private BPM BPM;

	/**
	 * 
	 * @Title: completeTask
	 * @Description: 流程提交
	 * @author yangjia
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/completeTask")
	@ResponseBody
	public String completeTask(HttpServletRequest req, HttpServletResponse resp) {
		List<TaskInstanceDto> list = new ArrayList<TaskInstanceDto>();

		String taskInstId = (String) req.getParameter("taskInstId");
		String businessId = (String) req.getParameter("businessId");
		String opinion = (String) req.getParameter("opinion");
		try {
			Set setTaskInstanceDto = new HashSet();
			setTaskInstanceDto = BPM.completeTask(taskInstId, businessId,
					opinion);
			list.addAll(setTaskInstanceDto);
		} catch (Exception e) {
			logger.error("流程提交失败");
			e.printStackTrace();
			return AppServerUtil.buildError(AppStatus.SystemError, e);
		}
		return AppServerUtil.buildJsonList(AppStatus.Succeed, list);
	}

}
