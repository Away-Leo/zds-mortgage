package com.zdsoft.finance.workbench.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.workbench.entity.RemindPool;
import com.zdsoft.finance.workbench.service.RemindPoolService;
import com.zdsoft.finance.workbench.vo.RemindPoolVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RemindCaseController.java 
 * @ClassName: RemindCaseController 
 * @Description: 案件提醒
 * @author longwei 
 * @date 2017年2月22日 上午9:52:53 
 * @version V1.0
 */
@Controller
@RequestMapping("/remindCase")
public class RemindCaseController extends BaseController {

	@Autowired
	private RemindPoolService remindPoolService;
	
	/**
	 * @Title: init 
	 * @Description: 案件提醒列表页面
	 * @author longwei
	 * @return
	 */
	@RequestMapping("/init")
	@UriKey(key = "com.zdsoft.finance.workbench.remindCase.init")
	@Menu(resource = "com.zdsoft.finance.workbench.remindCase.init", label = "案件提醒", group = "workbench", order = 1)
	public ModelAndView init() {
		return new ModelAndView("/workbench/my_remind_case_list");
	}
	
	/**
	 * @Title: getRemindCaseList 
	 * @Description: 案件提醒列表
	 * @author longwei
	 * @param remindPoolVo
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getRemindCaseList")
	@UriKey(key = "com.zdsoft.finance.workbench.remindCase.getRemindCaseList")
	public ResponseMsg getRemindCaseList(RemindPoolVo remindPoolVo,PageRequest pageable) {
		ResponseMsg msg=new ResponseMsg();
		try {
			//查询未确认案件提醒
			remindPoolVo.setStatus("1");
			
			Page<RemindPool> page=remindPoolService.findByPage(remindPoolVo.toPO(),pageable);
			List<RemindPoolVo> remindPoolVos=new ArrayList<RemindPoolVo>();
			for(RemindPool remindPool:page.getRecords()){
				remindPoolVos.add(new RemindPoolVo(remindPool));
			}
			msg.setRows(remindPoolVos);
			msg.setTotal(page.getTotalRows());
		} catch (BusinessException e) {
			logger.error("查询案件提醒错误",e);
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("查询错误");
		}
		return msg;
	}
	
	/**
	 * @Title: findRemind 
	 * @Description: 案件提醒查看
	 * @author longwei
	 * @return
	 */
	@RequestMapping("/findRemind")
	@UriKey(key = "com.zdsoft.finance.workbench.remindCase.findRemind")
	public ModelAndView findRemind(String remindPoolId) throws BusinessException {
		ModelAndView modelAndView=new ModelAndView("/workbench/my_remind_case_find");
		if(ObjectHelper.isEmpty(remindPoolId)){
			logger.error("未获取到列表主键");
			throw new BusinessException("未获取到列表主键");
		}
		RemindPoolVo remindPoolVo=new RemindPoolVo(remindPoolService.findOne(remindPoolId));
		modelAndView.addObject("remindPool", remindPoolVo);
		
		return modelAndView;
	}
	
	/**
	 * @Title: findRemind 
	 * @Description: 案件提醒确认
	 * @author longwei
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/confirmeRemind")
	@UriKey(key = "com.zdsoft.finance.workbench.remindCase.confirmeRemind")
	public ResponseMsg confirmeRemind(String remindPoolId) {
		ResponseMsg msg=new ResponseMsg();
		if(ObjectHelper.isEmpty(remindPoolId)){
			logger.error("未获取到列表主键");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("未获取到列表主键");
			return msg;
		}
		try {
			remindPoolService.confirmeRemind(remindPoolId);
		} catch (BusinessException e) {
			logger.error("案件提醒确认错误",e);
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("案件提醒确认错误");
		}
		return msg;
	}
}
