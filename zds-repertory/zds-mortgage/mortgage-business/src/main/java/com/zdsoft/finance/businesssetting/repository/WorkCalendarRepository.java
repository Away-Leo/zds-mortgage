package com.zdsoft.finance.businesssetting.repository;

import com.zdsoft.finance.businesssetting.entity.WorkCalendar;
import com.zdsoft.finance.common.base.CustomRepository;

import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title WorkCalendarRepository.java
 * @className WorkCalendarRepository
 * @description 工作日历操作库
 * @author LiaoGuoWei
 * @create 2017/3/3 14:45
 * @version V1.0
 **/
public interface WorkCalendarRepository extends CustomRepository<WorkCalendar, String> {
	/**
	  * @Title: findTwoDaysHoliday
	  * @Description: 查询2天之间记录的日期状态
	  * @author liaoguowei
	  * @param firstDay 第一天
	  * @param lastDay 最后一天
	  * @return List<WorkCalendar>
	  * @throws
	*/
	public List<WorkCalendar> findTwoDaysHoliday(String firstDay, String lastDay);
	/**
	  * @Title: findByDay
	  * @Description: 查找当前日期是否有数据
	  * @author liaoguowei
	  * @param day 天
	  * @return List<WorkCalendar>
	  * @throws
	*/
	public List<WorkCalendar> findByDay(String day);
}
