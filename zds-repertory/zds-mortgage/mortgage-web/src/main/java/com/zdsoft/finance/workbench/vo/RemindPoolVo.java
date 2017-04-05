package com.zdsoft.finance.workbench.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.workbench.entity.RemindPool;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RemindPoolVo.java 
 * @ClassName: RemindPoolVo 
 * @Description: 提醒池
 * @author gufeng 
 * @date 2017年3月13日 下午5:10:19 
 * @version V1.0
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

	/**
	 * 案件号
	 */
	private String projectCode;
	
	/**
	 * 主借人cd
	 */
	private String customerCode;
	
	/**
	 * 主借人name
	 */
	private String customerName;
	
	/**
	 * 提醒内容
	 */
	private String content;
	
	/**
	 * 开始日期
	 */
	private Long startDate;
	
	/**
	 * 状态
	 */
	private String status;
	
	/**
	 * 结束日期
	 */
	private Long endDate;
	
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

	public RemindPoolVo(){}
	
	public RemindPoolVo(RemindPool po){
		super(po);
	}
	
	public RemindPool toPO(){
		RemindPool po = new RemindPool();
		return super.toPo(this, po);
	}
}
