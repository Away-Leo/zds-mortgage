package com.zdsoft.finance.afterloan.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: FollowInfo.java 
 * @ClassName: FollowInfo 
 * @Description: 跟催实体
 * @author huangwei 
 * @date 2017年3月7日 下午6:00:58 
 * @version V1.0
 */
@Entity
@Table(name = "aftloan_follow_info")
public class FollowInfo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**
     * 案件Id
     */
    @Column(length = 32)
    private String caseApplyId;
    /**
     * 跟催方式
     */
    @Column(length = 20)
    private String followType;
    /**
     * 跟催状态(1有效，0无效)
     */
    @Column(length = 20)
    private String followStatus;
    /**
     * 外呼对象
     */
    @Column(length = 128)
    private String callOutObject;
    /**
     * 处置预案
     */
    @Column(length = 20)
    private String handlePlan;
    /**
     * 子目录（处置预案）
     */
    @Column(length = 20)
    private String childHandlePlan;
    /**
     * 跟催部门名称
     */
    @Column(length = 128)
    private String departmentName;
    /**
     * 跟催部门code
     */
    @Column(length = 32)
    private String departmentCode;
    /**
     * 预计还款日期
     */
    @Column
    private Long predictRepayDate;
    /**
     * 预计下次跟进日期
     */
    @Column
    private Long pretNextFlUpDate;
    /**
     * 是否发起督办(1.发起，0.不发起)
     */
    @Column(length = 20)
    private String supervisd;
    /**
     * 跟催情况
     */
    @Column(length = 512)
    private String followCondiction;
    /**
     * 跟催人姓名
     */
    @Column(length = 128)
    private String employeeName;
    /**
     * 跟催人code
     */
    @Column(length = 32)
    private String employeeCode;
    /**
     * 跟催日期
     */
    @Column
    private Long followDate;
    /**
     * 督办id
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "afterSuperviseId", nullable = true, unique = true)
    private AfterSupervise afterSupervise;
    /**
     * 跟催客户id
     */
    @Column(length = 32)
    private String customerId;
    /**
     * 跟催客户姓名
     */
    @Column(length = 128)
    private String customerName;
    /**
     * 创建人公司名称code
     */
    @Column(length = 32)
    private String companyCode;
    /**
     * 无效原因
     */
    @Column(length = 20)
    private String invalidReason;
	public String getCaseApplyId() {
		return caseApplyId;
	}
	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}
	public String getFollowType() {
		return followType;
	}
	public void setFollowType(String followType) {
		this.followType = followType;
	}
	public String getFollowStatus() {
		return followStatus;
	}
	public void setFollowStatus(String followStatus) {
		this.followStatus = followStatus;
	}
	public String getCallOutObject() {
		return callOutObject;
	}
	public void setCallOutObject(String callOutObject) {
		this.callOutObject = callOutObject;
	}
	public String getHandlePlan() {
		return handlePlan;
	}
	public void setHandlePlan(String handlePlan) {
		this.handlePlan = handlePlan;
	}
	public String getChildHandlePlan() {
		return childHandlePlan;
	}
	public void setChildHandlePlan(String childHandlePlan) {
		this.childHandlePlan = childHandlePlan;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public Long getPredictRepayDate() {
		return predictRepayDate;
	}
	public void setPredictRepayDate(Long predictRepayDate) {
		this.predictRepayDate = predictRepayDate;
	}
	public Long getPretNextFlUpDate() {
		return pretNextFlUpDate;
	}
	public void setPretNextFlUpDate(Long pretNextFlUpDate) {
		this.pretNextFlUpDate = pretNextFlUpDate;
	}
	public String getSupervisd() {
		return supervisd;
	}
	public void setSupervisd(String supervisd) {
		this.supervisd = supervisd;
	}
	public String getFollowCondiction() {
		return followCondiction;
	}
	public void setFollowCondiction(String followCondiction) {
		this.followCondiction = followCondiction;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public Long getFollowDate() {
		return followDate;
	}
	public void setFollowDate(Long followDate) {
		this.followDate = followDate;
	}
	public AfterSupervise getAfterSupervise() {
		return afterSupervise;
	}
	public void setAfterSupervise(AfterSupervise afterSupervise) {
		this.afterSupervise = afterSupervise;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getInvalidReason() {
		return invalidReason;
	}
	public void setInvalidReason(String invalidReason) {
		this.invalidReason = invalidReason;
	}
	
}