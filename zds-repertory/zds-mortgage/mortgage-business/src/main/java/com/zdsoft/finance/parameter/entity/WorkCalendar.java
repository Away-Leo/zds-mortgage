package com.zdsoft.finance.parameter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;


/**
 * 工作日历
 * 
 * @author liuhai
 * 
 */
@Entity
@Table(name = "zf_work_calendar")
public class WorkCalendar extends BaseEntity {
	private static final long serialVersionUID = -1052282076957325290L;
	/**
	 * 状态(1:工作日，0:休息日)
	 */
	@Column
	private int status;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * 日期，格式为(2010年一月一日-->20100101)
	 */
	@Column(length=10)
	private String day;
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	
	
}
