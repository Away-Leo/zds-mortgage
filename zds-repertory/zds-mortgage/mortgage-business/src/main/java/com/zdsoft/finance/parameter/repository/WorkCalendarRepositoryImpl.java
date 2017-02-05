package com.zdsoft.finance.parameter.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import com.zdsoft.finance.parameter.entity.WorkCalendar;

public class WorkCalendarRepositoryImpl {
	
	@Autowired
	EntityManager entityManager;
	/**
	 * 查询2天之间记录的日期状态
	 * @param firstDay
	 * @param lastDay
	 * @return
	 */
	public List<WorkCalendar> findTwoDaysHoliday(String firstDay,String lastDay) {
		String sql = "select c.day,c.status from WorkCalendar c where c.day >= :firstDay and c.day <= :lastDay order by day";
		Query q = entityManager.createQuery(sql);
		q.setParameter("firstDay", firstDay);
		q.setParameter("lastDay", lastDay);
		return q.getResultList();
	}

}
