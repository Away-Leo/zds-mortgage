package com.zdsoft.finance.workbench.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 提醒池
 * @createTime 2017-01-17
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @version 1.0
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
	
}
