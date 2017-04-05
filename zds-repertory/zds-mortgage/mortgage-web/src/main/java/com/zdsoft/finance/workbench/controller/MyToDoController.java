package com.zdsoft.finance.workbench.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.bpm.dto.TaskInstanceQueryDto;
import com.zdsoft.bpm.service.client.BPM;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.exception.AppException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MyToDoController.java 
 * @ClassName: MyToDoController 
 * @Description: 我的待办
 * @author longwei 
 * @date 2017年2月21日 上午11:29:19 
 * @version V1.0
 */
@Controller
@RequestMapping("/toDo")
public class MyToDoController extends BaseController {

	@Autowired
	private BPM BPM;
	@Autowired
	private CED CED;
	/**
	 * @Title: init 
	 * @Description: 我的待办列表
	 * @author longwei
	 * @return
	 */
	@RequestMapping("/init")
	@UriKey(key = "com.zdsoft.finance.workbench.toDo.init")
	@Menu(resource = "com.zdsoft.finance.workbench.toDo.init", label = "我的待办", group = "workbench", order = 1)
	public ModelAndView init() {
		logger.info("进入我的待办列表页面");
		ModelMap model = new ModelMap();
		try {
			model.put("processViewUrl", BPM.getProStatusDiagramUrl());
		} catch (AppException e) {
			e.printStackTrace();
			logger.error("获得流程图url失败！");
		}
		return new ModelAndView("/workbench/my_to_do_list",model);
	}
	
	/**
	 * @Title: getToDoList 
	 * @Description: 获取我的待办列表
	 * @author longwei
	 * @param taskInstanceQueryDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getToDoList")
	@UriKey(key = "com.zdsoft.finance.workbench.toDo.getToDoList")
	public ResponseMsg getToDoList(HttpServletRequest req,PageRequest pageRequest) {
		ResponseMsg msg=new ResponseMsg();
		try {
			 // 查询我的待办列表
			//4.1.4-SNAPSHOT 版本 START
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("empDto", CED.getLoginUser());
			params.put("taskNm", req.getParameter("taskName"));
			params.put("subject", req.getParameter("subject"));
			params.put("processNm", req.getParameter("processNm"));
			params.put("applierNm", req.getParameter("applyNm"));
			params.put("startTimeBegin", req.getParameter("startTime"));
			params.put("startTimenEnd", req.getParameter("endTime"));
			Page<TaskInstanceQueryDto> page = BPM.findMyPendingTasks(params,pageRequest,false);
			//4.1.4-SNAPSHOT 版本 END
			//Page<TaskInstanceQueryDto> page= BPM.findMyPendingTasks(pageRequest, CED.getLoginUser());
			 msg.setRows(page.getRecords());
			 msg.setTotal(page.getTotalRows());
		} catch (Exception e) {
			logger.error("查询待办列表失败",e);
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("查询待办列表失败");
		}
		return msg;
	}
}
