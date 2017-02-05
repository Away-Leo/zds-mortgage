package com.zdsoft.finance.parameter.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.parameter.entity.WorkCalendar;

public interface WorkCalendarService extends BaseService<WorkCalendar>{
	/**
	 * 查询2天之间记录的日期状态
	 * @param firstDay
	 * @param lastDay
	 * @return
	 */
	public List<WorkCalendar> getTwoDaysIns(String firstDay,String lastDay);
	/**
	 * 查找当前日期数据
	 * @param day
	 */
	public List<WorkCalendar> findOneDay(String day);
	/**
	 * 获取一天的状态，0为休息日，1为工作日
	 * @param day 
	 * @return
	 */
	public int getDayStatus(String day);

}
