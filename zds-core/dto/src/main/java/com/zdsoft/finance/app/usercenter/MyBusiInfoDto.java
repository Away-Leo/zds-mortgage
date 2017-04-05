package com.zdsoft.finance.app.usercenter;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:MyBusiInfoDto.java
 * @Package:com.zdsoft.finance.app.usercenter
 * @Description:我的申请Dto（APP）
 * @author: jingyh
 * @date:2017年1月13日 下午6:31:56
 * @version:v1.0
 */
public class MyBusiInfoDto {

	/**
	 * 案件申请Id
	 */
	private String caseApplyId;
	
	/**
	 * 具体业务表单Id
	 */
	private String businessKey;
	
	/**
	 * 流程实例Id
	 */
	private String processInstanceId;
	
	/**
	 * 标题
	 */
	private String applyTitle;
	
	/**
	 * 状态值
	 */
	private Integer formStatus;
	
	/**
	 * 主借人姓名
	 */
	private String mainCustomerName;
	
	/**
	 * 申请时间字符串
	 */
	private Long applyDate;
	
	/**
	 * 申请类型标示
	 */
	private String modelType;
	
	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public String getApplyTitle() {
		return applyTitle;
	}

	public void setApplyTitle(String applyTitle) {
		this.applyTitle = applyTitle;
	}

	public Integer getFormStatus() {
		return formStatus;
	}

	public void setFormStatus(Integer formStatus) {
		this.formStatus = formStatus;
	}

	public String getMainCustomerName() {
		return mainCustomerName;
	}

	public void setMainCustomerName(String mainCustomerName) {
		this.mainCustomerName = mainCustomerName;
	}
	
	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	public Long getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Long applyDate) {
		this.applyDate = applyDate;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public MyBusiInfoDto() {
		super();
	}
}
