package com.zdsoft.finance.parameter.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.parameter.entity.WorkCalendar;

/**
 * 工作日历设置
 * 
 * @author liuhai
 * 
 */
public interface WorkCalendarRepository extends CustomRepository<WorkCalendar, String> {
	/**
	 * 查询2天之间记录的日期状态
	 * @param firstDay
	 * @param lastDay
	 * @return
	 */
	public List<WorkCalendar> findTwoDaysHoliday(String firstDay,String lastDay);
	/**
	 * 查找当前日期是否有数据
	 * @param day
	 */
	public List<WorkCalendar> findByDay(String day);
}
