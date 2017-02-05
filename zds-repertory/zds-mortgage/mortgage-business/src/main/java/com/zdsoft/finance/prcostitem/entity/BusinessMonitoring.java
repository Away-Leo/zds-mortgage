package com.zdsoft.finance.prcostitem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 业务监控
 * @createTime 2017-01-11
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @version 1.0
 */
@Entity
@Table(name = "business_monitoring")
public class BusinessMonitoring extends BaseEntity {

	private static final long serialVersionUID = -5002702599558684121L;

	/**
	 * 逾期类型
	 */
	public static final String OVERDUE_TYPE_MONTH = "月";
	public static final String OVERDUE_TYPE_QUARTER = "季度";
	public static final String OVERDUE_TYPE_SEMESTER = "半年";
	public static final String OVERDUE_TYPE_YEAR = "年";
	
	/**
	 * 检测日期
	 */
	@Column
	private Long monitoringTime;
	/**
	 * 逾期特批按键总条数
	 */
	@Column
	private Long overdueTotal;
	/**
	 * 特批案件总条数
	 */
	@Column
	private Long specialTotal;
	/**
	 * 逾期类型(月，季，半年，年)
	 */
	@Column
	private String overdueType;
	
	/**
	 * 逾期率
	 */
	@Column(precision = 30,scale = 12)
	private Double overdueRate;
	
	public String getOverdueType() {
		return overdueType;
	}

	public void setOverdueType(String overdueType) {
		this.overdueType = overdueType;
	}

	public Long getMonitoringTime() {
		return monitoringTime;
	}

	public void setMonitoringTime(Long monitoringTime) {
		this.monitoringTime = monitoringTime;
	}

	public Long getOverdueTotal() {
		return overdueTotal;
	}

	public void setOverdueTotal(Long overdueTotal) {
		this.overdueTotal = overdueTotal;
	}

	public Long getSpecialTotal() {
		return specialTotal;
	}

	public void setSpecialTotal(Long specialTotal) {
		this.specialTotal = specialTotal;
	}

	public Double getOverdueRate() {
		return overdueRate;
	}

	public void setOverdueRate(Double overdueRate) {
		this.overdueRate = overdueRate;
	}
	
}
