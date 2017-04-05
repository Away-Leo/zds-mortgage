package com.zdsoft.finance.workbench.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RemindPool.java 
 * @ClassName: RemindPool 
 * @Description: 提醒池
 * @author gufeng 
 * @date 2017年3月13日 下午5:08:02 
 * @version V1.0
 */
@Entity
@Table(name = "workbench_remind_pool")
public class RemindPool extends BaseEntity {

	private static final long serialVersionUID = 5309363197361767589L;

	/**
	 * 提醒分类
	 */
	@Column(length = 20)
	private String category;
	
	/**
	 * 提示事项
	 */
	@Column(length = 128)
	private String reminders;
	
	/**
	 * 提醒事项对应的地址
	 */
	@Column(length = 128)
	private String remindUrl;
	
	/**
	 * 对应的案件id
	 */
	@Column(length = 32)
	private String projectId;
	
	/**
	 * 案件号
	 */
	@Column(length=32)
	private String projectCode;
	
	/**
	 * 主借人cd
	 */
	@Column(length=32)
	private String customerCode;
	
	/**
	 * 主借人name
	 */
	@Column(length=100)
	private String customerName;
	
	/**
	 * 提醒内容
	 */
	@Column(length=500)
	private String content;
	
	/**
	 * 开始日期
	 */
	@Column
	private Long startDate;
	
	/**
	 * 状态
	 */
	@Column(length=32)
	private String status;
	
	/**
	 * 结束日期
	 */
	private transient Long endDate;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getReminders() {
		return reminders;
	}

	public void setReminders(String reminders) {
		this.reminders = reminders;
	}

	public String getRemindUrl() {
		return remindUrl;
	}

	public void setRemindUrl(String remindUrl) {
		this.remindUrl = remindUrl;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getStartDate() {
		return startDate;
	}

	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}
	
	
}
