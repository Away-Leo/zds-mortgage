package com.zdsoft.finance.parameter.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.parameter.entity.WorkCalendar;
import com.zdsoft.finance.parameter.repository.WorkCalendarRepository;
import com.zdsoft.finance.parameter.service.WorkCalendarService;
@Service("workCalendarService")
public class WorkCalendarServiceImpl extends BaseServiceImpl<WorkCalendar, CustomRepository<WorkCalendar, String>>
implements WorkCalendarService{
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
		// TODO Auto-generated method stub
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
	/**
	 * 获取一天的状态，0为休息日，1为工作日
	 * @param day 
	 * @return
	 */
	@Override
	public int getDayStatus(String day)
	{
		List<WorkCalendar> list=workCalendarRepository.findByDay(day);
		//如果数据库有该天的数据则返回数据库数据
		if(list!=null&&list.size()>0)
		{
			return list.get(0).getStatus();
		}
		//如果数据库没有当天的数据，则更具星期数来确定改天是否为休息日
		else
		{
			Calendar cal=Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			try {
				Date dayDate=format.parse(day);
				cal.setTime(dayDate);
				int weekDay=cal.get(Calendar.DAY_OF_WEEK);
				//如果是星期六或者星期天，则为休息日
				if(weekDay==1||weekDay==7){ 
					return 0;
				}
				else
				{
					return 1;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}
		}
	}


}