package com.zdsoft.finance.businesssetting.controller;

import com.zdsoft.finance.businesssetting.vo.RangeDay;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.businesssetting.entity.WorkCalendar;
import com.zdsoft.finance.businesssetting.service.WorkCalendarService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title WorkCalendarController.java
 * @className WorkCalendarController
 * @description 工作日历controller
 * @author LiaoGuoWei
 * @create 2017/3/3 15:21
 * @version V1.0
 **/
@Controller
@RequestMapping("/parameter")
public class WorkCalendarController extends BaseController {

	@Autowired
	private WorkCalendarService workCalendarService;


	/**
	 * @Title: initHolidayConfig
	 * @Description: 工作日历页面
	 * @author liaoguowei
	 * @param year
	 * @param jsoncallback
	 * @return org.springframework.web.servlet.ModelAndView
	 * @throws
	 */
	@RequestMapping("/initCalendarSet")
	@UriKey(key = "com.zdsoft.finance.parameter.initCalendarSet")
	@Menu(label = "工作日历", group = "businessSetting", resource = "com.zdsoft.finance.parameter.initCalendarSet")
	public ModelAndView initHolidayConfig(Integer year, String jsoncallback) {
		ModelAndView mav = new ModelAndView("businesssetting/workcalendar");
		return mav;
	}

	/**
	 * @Title: getWorkCalendar
	 * @Description: 银行代码列表
	 * @author liaoguowei
	 * @param rangeDay
	 * @param jsoncallback
	 * @return java.lang.String
	 * @throws
	 */
	@RequestMapping("/workCalendar")
	@UriKey(key = "com.zdsoft.finance.parameter.getWorkCalendar")
	@ResponseBody
	public String getWorkCalendar(RangeDay rangeDay, String jsoncallback) {
		ResponseMsg msg = new ResponseMsg();
		try {
			List<WorkCalendar> holidays = workCalendarService.getTwoDaysIns(rangeDay.getFirstDay(), rangeDay.getLastDay());
			//msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setRows(holidays);
		} catch (Exception e) {
			logger.error("获取工作日历出错", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * @Title: updateWorkCalendar
	 * @Description: 更新银行代码
	 * @author liaoguowei
	 * @param workCalendar
	 * @param jsoncallback
	 * @return java.lang.String
	 * @throws
	 */
	@RequestMapping("/updateWorkCalendar")
	@UriKey(key = "com.zdsoft.finance.parameter.updateWorkCalendar")
	@ResponseBody
	public String updateWorkCalendar(WorkCalendar workCalendar, String jsoncallback) {
		ResponseMsg msg = new ResponseMsg();
		try {
			List<WorkCalendar> list = workCalendarService.findOneDay(workCalendar.getDay());
			//如果该日期有数据，则修改状态
			if (list != null && list.size() > 0) {
				WorkCalendar cal = list.get(0);
				if (cal.getStatus() == 0) {
					cal.setStatus(1);
				} else {
					cal.setStatus(0);
				}
				workCalendarService.updateEntity(cal);
			}
			//如果没有数据，修改状态后添加数据
			else {
				if (workCalendar.getStatus() == 0) {
					workCalendar.setStatus(1);
				} else {
					workCalendar.setStatus(0);
				}
				workCalendarService.saveEntity(workCalendar);
			}
			msg.setMsg("操作成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);

		} catch (BusinessException e) {
			logger.error("",e);
			msg.setMsg("操作失败！");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	


}
