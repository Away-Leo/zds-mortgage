package com.zdsoft.finance.casemanage.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.marketing.entity.CaseTask;

/** 	
* 版权所有：重庆正大华日软件有限公司	
* @Title: CaseTaskVo.java 	
* @Package com.zdsoft.finance.casemanage.vo 	
* @Description: TODO	
* @author liuhuan 	
* @date 2017年1月22日 上午10:34:51 	
* @version V1.0 	
*/
public class CaseTaskVo extends BaseVo<CaseTask>{

	private static final long serialVersionUID = -9117809379891862440L;
	
	/**
	 * 案件ID
	 */
	private String caseApplyId;
	
	/**
	 * 派工时间
	 */
	private Long taskDate;
	
	/**
	 * 资调人员 name
	 */
	private String taskPersonnelName;
	
	/**
	 * 资调人员 Code
	 */
	private String taskPersonnelCode;
	
	/**
	 * 派工说明
	 */
	private String mo;

	
	public CaseTaskVo() {
		super();
	}
	public CaseTaskVo(CaseTask po) {
		super(po);
	}
	
	public CaseTask toPo(){
		CaseTask caseTask = new CaseTask();
		return super.toPo(this, caseTask);
	}
	
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

	public String getMo() {
		return mo;
	}

	public void setMo(String mo) {
		this.mo = mo;
	}
	
	
}
