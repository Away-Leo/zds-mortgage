package com.zdsoft.finance.afterloan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterDispatch.java 
 * @ClassName: AfterDispatch 
 * @Description: 派工人员
 * @author xj 
 * @date 2017年3月7日 下午12:52:27 
 * @version V1.0
 */
@Entity
@Table(name = "aftloan_dispatch")
public class AfterDispatch extends BaseEntity {

	 
	private static final long serialVersionUID = 2616585312278060244L;
	
	/**
	 * 督办人code
	 */
	@Column(length=32)
	private String superviserCode;
	
	/**
	 * 督办人name
	 */
	@Column(length=64)
	private String superviserName;
	
	/**
	 * 派工员工code
	 */
	@Column(length=32)
	private String dispatchCode;
	
	/**
	 * 派工人姓名
	 */
	@Column(length=64)
	private String dispatchName;
	
	/**
	 * 派工所属部门code
	 */
	@Column(length=32)
	private String dispatchDepartmentCode;
	
	/**
	 * 派工所属部门name
	 */
	@Column(length=128)
	private String dispatchDepartmentName;
	
	/**
	 * 反馈时间
	 */
	@Column
	private Long feedbackDate;
	
	/**
	 * 反馈结果
	 */
	@Column(length=3000)
	private String feedbackRresults;
	
	/**
	 * 督办
	 */
	@ManyToOne
	@JoinColumn(name="superviseId")
	private AfterSupervise afterSupervise;

	public String getSuperviserCode() {
		return superviserCode;
	}

	public void setSuperviserCode(String superviserCode) {
		this.superviserCode = superviserCode;
	}

	public String getSuperviserName() {
		return superviserName;
	}

	public void setSuperviserName(String superviserName) {
		this.superviserName = superviserName;
	}

	public String getDispatchCode() {
		return dispatchCode;
	}

	public void setDispatchCode(String dispatchCode) {
		this.dispatchCode = dispatchCode;
	}

	public String getDispatchName() {
		return dispatchName;
	}

	public void setDispatchName(String dispatchName) {
		this.dispatchName = dispatchName;
	}

	public String getDispatchDepartmentCode() {
		return dispatchDepartmentCode;
	}

	public void setDispatchDepartmentCode(String dispatchDepartmentCode) {
		this.dispatchDepartmentCode = dispatchDepartmentCode;
	}

	public String getDispatchDepartmentName() {
		return dispatchDepartmentName;
	}

	public void setDispatchDepartmentName(String dispatchDepartmentName) {
		this.dispatchDepartmentName = dispatchDepartmentName;
	}

	public Long getFeedbackDate() {
		return feedbackDate;
	}

	public void setFeedbackDate(Long feedbackDate) {
		this.feedbackDate = feedbackDate;
	}

	public String getFeedbackRresults() {
		return feedbackRresults;
	}

	public void setFeedbackRresults(String feedbackRresults) {
		this.feedbackRresults = feedbackRresults;
	}

	public AfterSupervise getAfterSupervise() {
		return afterSupervise;
	}

	public void setAfterSupervise(AfterSupervise afterSupervise) {
		this.afterSupervise = afterSupervise;
	}

	
}
