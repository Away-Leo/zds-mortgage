package com.zdsoft.finance.businesssetting.entity;

import com.zdsoft.framework.core.common.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @title WorkCalendar.java
 * @className WorkCalendar
 * @description 工作日历
 * @author LiaoGuoWei
 * @create 2017/3/3 14:24
 * @version V1.0
 **/
@Entity
@Table(name = "buss_workcalendar")
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
