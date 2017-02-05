package com.zdsoft.finance.workbench.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.workbench.entity.RemindPool;

/**
 * 提醒池
 * @createTime 2017-01-17
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @version 1.0
 */
public class RemindPoolVo extends BaseVo<RemindPool> {


	private static final long serialVersionUID = -5974841635577561215L;

	/**
	 * 提醒分类
	 */
	private String category;
	
	/**
	 * 提示事项
	 */
	private String reminders;
	
	/**
	 * 提醒事项对应的地址
	 */
	private String remindUrl;
	
	/**
	 * 对应的案件id
	 */
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
	
	public RemindPoolVo(){}
	
	public RemindPoolVo(RemindPool po){
		super(po);
	}
	
	public RemindPool toPO(){
		RemindPool po = new RemindPool();
		return super.toPo(this, po);
	}
}
