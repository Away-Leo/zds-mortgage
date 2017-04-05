package com.zdsoft.finance.app.usercenter.vo;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.zdsoft.finance.app.usercenter.MyBusiInfoDto;
import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.common.enums.busiform.ApplyModelTypeEnum;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:MyBusiInfoVo.java
 * @Package:com.zdsoft.finance.app.flow.vo
 * @Description:我的申请相关信息Vo（APP）
 * @author: jingyh
 * @date:2017年1月13日 下午6:14:59
 * @version:v1.0
 */
public class MyBusiInfoVo implements Serializable {

	private static final long serialVersionUID = 7218824783954271371L;

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
	 * 状态描述
	 */
	private String formStatusName;
	
	/**
	 * 主借人姓名
	 */
	private String mainCustomerName;
	
	/**
	 * 申请时间字符串
	 */
	private String applyDateStr;
	
	/**
	 * 申请类型标示
	 */
	private String modelType;
	
	/**
	 * 申请类型名称
	 */
	private String modelTypeName;


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

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
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

	public String getApplyDateStr() {
		return applyDateStr;
	}

	public void setApplyDateStr(String applyDateStr) {
		this.applyDateStr = applyDateStr;
	}

	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	public String getModelTypeName() {
		return modelTypeName;
	}

	public void setModelTypeName(String modelTypeName) {
		this.modelTypeName = modelTypeName;
	}

	public String getFormStatusName() {
		return formStatusName;
	}

	public void setFormStatusName(String formStatusName) {
		this.formStatusName = formStatusName;
	}

	public MyBusiInfoVo() {
		super();
	}
	
	/**
	 * 
	 * 通过Dto构造 
	 *
	 * @param info
	 */
	public MyBusiInfoVo(MyBusiInfoDto info) {
		if (ObjectHelper.isNotEmpty(info)) {
			BeanUtils.copyProperties(info, this);
			// 状态值
			if (ObjectHelper.isNotEmpty(info.getFormStatus())) {
				this.setFormStatusName(BusiFormStatus.getName(info.getFormStatus()));
			}
			// 申请时间
			if (ObjectHelper.isNotEmpty(info.getApplyDate())) {
				this.setApplyDateStr(DateHelper.longToDate(info.getApplyDate(), DateHelper.DATE_WITHMINUTE_FORMAT));
			}
			// 申请类型
			if (ObjectHelper.isNotEmpty(info.getModelType())) {
				this.setModelTypeName(ApplyModelTypeEnum.getName(info.getModelType()));
			}
		}
	}
}
