package com.zdsoft.finance.businesssetting.repository;

import com.zdsoft.finance.businesssetting.entity.WorkCalendar;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title WorkCalendarRepositoryImpl.java
 * @className WorkCalendarRepositoryImpl
 * @description 工作日历操作库实现
 * @author LiaoGuoWei
 * @create 2017/3/3 14:47
 * @version V1.0
 **/
public class WorkCalendarRepositoryImpl {
	
	@Autowired
	EntityManager entityManager;

	/**
	 * @Title: findTwoDaysHoliday
	 * @Description: 查询2天之间记录的日期状态
	 * @author liaoguowei
	 * @param firstDay
	 * @param lastDay
	 * @return java.util.List<com.zdsoft.finance.businesssetting.entity.WorkCalendar>
	 * @throws
	 */
	public List<WorkCalendar> findTwoDaysHoliday(String firstDay,String lastDay) {
		String sql = "select c.day,c.status from WorkCalendar c where c.day >= :firstDay and c.day <= :lastDay order by day";
		Query q = entityManager.createQuery(sql);
		q.setParameter("firstDay", firstDay);
		q.setParameter("lastDay", lastDay);
		return q.getResultList();
	}

}
