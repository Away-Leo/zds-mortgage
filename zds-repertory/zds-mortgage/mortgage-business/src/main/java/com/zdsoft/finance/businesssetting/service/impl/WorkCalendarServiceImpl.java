package com.zdsoft.finance.businesssetting.service.impl;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.businesssetting.entity.WorkCalendar;
import com.zdsoft.finance.businesssetting.repository.WorkCalendarRepository;
import com.zdsoft.finance.businesssetting.service.WorkCalendarService;
import com.zdsoft.finance.common.base.CustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title WorkCalendarServiceImpl.java
 * @className WorkCalendarServiceImpl
 * @description 工作日历service实现
 * @author LiaoGuoWei
 * @create 2017/3/3 14:51
 * @version V1.0
 **/
@Service("workCalendarService")
public class WorkCalendarServiceImpl extends BaseServiceImpl<WorkCalendar, CustomRepository<WorkCalendar, String>>
implements WorkCalendarService {
	@Autowired
	private WorkCalendarRepository workCalendarRepository;

	/**
	 * 查询2天之间记录的日期状态
	 * @param firstDay
	 * @param lastDay
	 * @return
	 */
	@Override
	public List<WorkCalendar> getTwoDaysIns(String firstDay, String lastDay) {
		return workCalendarRepository.findTwoDaysHoliday(firstDay, lastDay);
	}
	/**
	 * 查找当前日期数据
	 * @param day
	 */
	@Override
	public List<WorkCalendar> findOneDay(String day)
	{
		List<WorkCalendar> list=workCalendarRepository.findByDay(day);
		return list;
	}


}