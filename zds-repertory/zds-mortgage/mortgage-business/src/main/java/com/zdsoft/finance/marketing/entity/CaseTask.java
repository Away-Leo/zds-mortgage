package com.zdsoft.finance.marketing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/** 	
* 版权所有：重庆正大华日软件有限公司	
* @Title: CaseTask.java 	
* @Package com.zdsoft.finance.marketing.entity 	
* @Description: 派工	
* @author liuhuan 	
* @date 2017年1月18日 下午8:29:32 	
* @version V1.0 	
*/
@Entity
@Table(name="case_task")
public class CaseTask extends BaseEntity{

	private static final long serialVersionUID = 8105375809976411214L;
	
	/**
	 * 案件ID
	 */
	@Column(length=32)
	private String caseApplyId;
	
	/**
	 * 派工时间
	 */
	@Column
	private Long taskDate;
	
	/**
	 * 资调人员 name
	 */
	@Column(length=128)
	private String taskPersonnelName;
	
	/**
	 * 资调人员 Code
	 */
	@Column(length=32)
	private String taskPersonnelCode;
	
	/**
	 * 派工说明
	 */
	@Column(length=500)
	private String remark;


	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public Long getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(Long taskDate) {
		this.taskDate = taskDate;
	}

	public String getTaskPersonnelName() {
		return taskPersonnelName;
	}

	public void setTaskPersonnelName(String taskPersonnelName) {
		this.taskPersonnelName = taskPersonnelName;
	}

	public String getTaskPersonnelCode() {
		return taskPersonnelCode;
	}

	public void setTaskPersonnelCode(String taskPersonnelCode) {
		this.taskPersonnelCode = taskPersonnelCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
