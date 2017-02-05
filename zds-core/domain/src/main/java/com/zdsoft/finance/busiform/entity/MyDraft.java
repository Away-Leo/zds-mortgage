package com.zdsoft.finance.busiform.entity;

import java.util.Date;

/**
 * 我的草稿临时实体对象
 * @author longwei
 * @date 2017/01/13
 * @version 1.0
 */
public class MyDraft {

	/**
	 * id
	 */
	private String id;
	
	/**
	 * 流程名称
	 */
	private String processName;
	
	/**
	 * 主借人
	 */
	private String borrowerPerson;
	
	/**
	 * 申请单
	 */
	private String applayFormCode;
	
	/**
	 * 申请单名
	 */
	private String applayFormName;
	
	/**
	 * 申请时间
	 */
	private Long startTime;
	
	/**
	 * 结束时间
	 */
	private Long endTime;
	
	/**
	 * 业务编号
	 */
	private String businessCode;
	
	/**
	 * 保存时间
	 */
	private Date createTime;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getBorrowerPerson() {
		return borrowerPerson;
	}

	public void setBorrowerPerson(String borrowerPerson) {
		this.borrowerPerson = borrowerPerson;
	}

	public String getApplayFormCode() {
		return applayFormCode;
	}

	public void setApplayFormCode(String applayFormCode) {
		this.applayFormCode = applayFormCode;
	}

	public String getApplayFormName() {
		return applayFormName;
	}

	public void setApplayFormName(String applayFormName) {
		this.applayFormName = applayFormName;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public MyDraft(){}
	
	public MyDraft(String id, String borrowerPerson, String applayFormCode, String applayFormName,
			 String businessCode, Date createTime) {
		super();
		this.id = id;
		this.borrowerPerson = borrowerPerson;
		this.applayFormCode = applayFormCode;
		this.applayFormName = applayFormName;
		this.businessCode = businessCode;
		this.createTime = createTime;
	}

}
