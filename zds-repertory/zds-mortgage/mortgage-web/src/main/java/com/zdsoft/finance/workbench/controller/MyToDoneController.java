package com.zdsoft.finance.workbench.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.busiform.service.BusiFormService;
import com.zdsoft.finance.workbench.vo.BusiFormVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.exception.AppException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MyToDoneController.java 
 * @ClassName: MyToDoneController 
 * @Description: 我的已办
 * @author longwei 
 * @date 2017年2月21日 上午11:29:37 
 * @version V1.0
 */
@Controller
@RequestMapping("/toDone")
public class MyToDoneController extends BaseController {

	@Autowired
	private BPM BPM;
	@Autowired
	private CED CED;
	@Autowired
	private BusiFormService busiFormService;
	
	/**
	 * @Title: init 
	 * @Description: 我的已办列表
	 * @author longwei
	 * @return
	 */
	@RequestMapping("/init")
	@UriKey(key = "com.zdsoft.finance.workbench.toDone.init")
	@Menu(resource = "com.zdsoft.finance.workbench.toDone.init", label = "我的已办", group = "workbench", order = 1)
	public ModelAndView init() {
		logger.info("进入我的已办列表页面");
		ModelMap model = new ModelMap();
		try {
			model.put("processViewUrl", BPM.getProStatusDiagramUrl());
		} catch (AppException e) {
			e.printStackTrace();
			logger.error("获得流程图url失败！");
		}
		return new ModelAndView("/workbench/my_to_done_list",model);
	}
	
	/**
	 * @Title: getToDoneList 
	 * @Description: 获取我的已办列表
	 * @author longwei
	 * @param taskInstanceQueryDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getToDoneList")
	@UriKey(key = "com.zdsoft.finance.workbench.toDone.getToDoneList")
	public ResponseMsg getToDoneList(HttpServletRequest req,PageRequest pageRequest) {
		ResponseMsg msg=new ResponseMsg();
		try {
			
			 //查询我的已办列表
			//4.1.4-SNAPSHOT 版本 START
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("empDto", CED.getLoginUser());
			params.put("taskNm", req.getParameter("taskName"));
			params.put("subject", req.getParameter("subject"));
			params.put("processNm", req.getParameter("processNm"));
			params.put("applierNm", req.getParameter("applyNm"));
			params.put("endTimeBegin", req.getParameter("endTimeBegin"));
			params.put("endTimenEnd", req.getParameter("endTimeEnd"));
			Page<TaskInstanceQueryDto> page = BPM.findMyProcessedTasks(params,pageRequest);
			 // 4.1.4-SNAPSHOT 版本 END
			 //Page<TaskInstanceQueryDto> page= BPM.findMyPendingTasks(pageRequest, CED.getLoginUser());
			 msg.setRows(page.getRecords());
			 msg.setTotal(page.getTotalRows());
		} catch (Exception e) {
			logger.error("查询已办列表错误",e);
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("查询已办列表错误");
		}
		return msg;
	}
	
	/**
	 * 
	 * @Title: getBusiForm 
	 * @Description: 获取申请信息
	 * @author yangjia 
	 * @param taskInstanceQueryDto
	 * @param pageRequest
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getBusiForm")
	@UriKey(key = "com.zdsoft.finance.workbench.toDone.getBusiForm")
	public ResponseMsg getBusiForm(HttpServletRequest req) {
		ResponseMsg msg=new ResponseMsg();
		try {
			
		String componentsEntityId = req.getParameter("projectId");
		String businessEntityId = req.getParameter("businessKey");
		String processInstanceKey = req.getParameter("processInstanceId");
		
		List<BusiForm> busiFormlist = busiFormService.findBusiFormByHql(componentsEntityId, businessEntityId, processInstanceKey);
		
		List<BusiFormVo> busiFormVolist = new ArrayList<BusiFormVo>();
		
		for(int i=0;i<busiFormlist.size();i++){
			
			BusiFormVo  busiFormVo = new BusiFormVo(req,busiFormlist.get(i));
			
			busiFormVolist.add(busiFormVo);
			
		}
		
		msg.setRows(busiFormVolist);
		} catch (Exception e) {
			logger.error("获取BusiForm错误",e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("获取BusiForm错误");
		}
		
		return msg;
	}
}
