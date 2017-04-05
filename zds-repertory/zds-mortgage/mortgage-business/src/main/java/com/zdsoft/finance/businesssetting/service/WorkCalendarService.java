package com.zdsoft.finance.businesssetting.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.businesssetting.entity.WorkCalendar;

import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title WorkCalendarService.java
 * @className WorkCalendarService
 * @description 工作日日历service
 * @author LiaoGuoWei
 * @create 2017/3/3 15:14
 * @version V1.0
 **/
public interface WorkCalendarService extends BaseService<WorkCalendar>{
	/**
	 * @Title: getTwoDaysIns
	 * @Description: 查询2天之间记录的日期状态
	 * @author liaoguowei
	 * @param firstDay 第一天
	 * @param lastDay 最后一天
	 * @return java.util.List<com.zdsoft.finance.businesssetting.entity.WorkCalendar>
	 * @throws
	 */
	public List<WorkCalendar> getTwoDaysIns(String firstDay, String lastDay);

	/**
	 * @Title: findOneDay
	 * @Description: 查找当前日期数据
	 * @author liaoguowei
	 * @param day 天
	 * @return java.util.List<com.zdsoft.finance.businesssetting.entity.WorkCalendar>
	 * @throws
	 */
	public List<WorkCalendar> findOneDay(String day);


}
