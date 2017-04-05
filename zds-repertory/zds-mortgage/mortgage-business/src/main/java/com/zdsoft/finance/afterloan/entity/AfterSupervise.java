package com.zdsoft.finance.afterloan.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.framework.core.common.domain.BaseEntity;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterSupervise.java 
 * @ClassName: AfterSupervise 
 * @Description: 督办申请
 * @author xj 
 * @date 2017年2月16日 下午2:40:45 
 * @version V1.0
 */
@Entity
@Table(name = "aftloan_supervise")
public class AfterSupervise extends BaseEntity {
	/**
	 * 流程代码
	 */
	public static final String PROCEESS_CODE = "YWDM0012611"; 
	
	/**   
	 * 序列表    
	 */ 
	private static final long serialVersionUID = -7125203638274280305L;
	
	/**
	 * 督办类型
	 */
	@Column(length=20)
	private String superviseType;
	
	/**
	 * 督办子类型
	 */
	@Column(length=20)
	private String superviseChildType;
	
	/**
	 * 督办接收人code(逗号隔开)
	 */
	@Column(length=512)
	private String superviseReceiverCode;
	
	/**
	 * 督办人name(逗号隔开)
	 */
	@Column(length=512)
	private String superviseReceiverName;
	
	/**
	 * 督办抄送人code
	 */
	@Column(length=32)
	private String superviseCopyReceiverCode;
	
	/**
	 * 督办抄送人Name
	 */
	@Column(length=64)
	private String superviseCopyReceiverName;
	
	/**
	 * 处理时限
	 */
	@Column
	private Long processingDate;
	
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
	 * 跟踪部门CODE
	 */
	@Column(length=32)
	private String departmentCode;
	
	/**
	 * 跟踪部门名称
	 */
	@Column(length=128)
	private String departmentName;
	
	/**
	 * 督办说明
	 */
	@Column(length=512)
	private String remark;
	
	/**
	 * 跟催id
	 */
	@Column(length=32)
	private String followInfoId;
	
	/**
	 * 案件Id
	 */
	@Column(length=32)
	private String caseApplyId;
	
	/**
	 * 创建人公司code
	 */
	@Column(length=32)
	private String companyCode;
	
	/**
	 * 最后一次反馈时间（用于督办详情列表）
	 */
	@Column
	private Long feedbackDate;
	
	
	/**
	 * 最后一次反馈结果（用于督办详情列表）
	 */
	@Column(length=512)
	private String feedbackRresults;
	
	/**
     * 业务表单id
     */
    @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name = "busiFromId")
    private BusiForm busiForm;
    
    /**
     * 下一处理人
     */
    private transient String currentDealEmpName ;
    
	public String getSuperviseType() {
		return superviseType;
	}
	public void setSuperviseType(String superviseType) {
		this.superviseType = superviseType;
	}
	public String getSuperviseChildType() {
		return superviseChildType;
	}
	public void setSuperviseChildType(String superviseChildType) {
		this.superviseChildType = superviseChildType;
	}
	
	public String getSuperviseReceiverCode() {
		return superviseReceiverCode;
	}
	public void setSuperviseReceiverCode(String superviseReceiverCode) {
		this.superviseReceiverCode = superviseReceiverCode;
	}
	public String getSuperviseReceiverName() {
		return superviseReceiverName;
	}
	public void setSuperviseReceiverName(String superviseReceiverName) {
		this.superviseReceiverName = superviseReceiverName;
	}
	public String getSuperviseCopyReceiverCode() {
		return superviseCopyReceiverCode;
	}
	public void setSuperviseCopyReceiverCode(String superviseCopyReceiverCode) {
		this.superviseCopyReceiverCode = superviseCopyReceiverCode;
	}
	public String getSuperviseCopyReceiverName() {
		return superviseCopyReceiverName;
	}
	public void setSuperviseCopyReceiverName(String superviseCopyReceiverName) {
		this.superviseCopyReceiverName = superviseCopyReceiverName;
	}
	public Long getProcessingDate() {
		return processingDate;
	}
	public void setProcessingDate(Long processingDate) {
		this.processingDate = processingDate;
	}
	
	public String getSuperviserCode() {
		return superviserCode;
	}
	public void setSuperviserCode(String superviserCode) {
		this.superviserCode = superviserCode;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFollowInfoId() {
		return followInfoId;
	}
	public void setFollowInfoId(String followInfoId) {
		this.followInfoId = followInfoId;
	}
	public String getCaseApplyId() {
		return caseApplyId;
	}
	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}
	public BusiForm getBusiForm() {
		return busiForm;
	}
	public void setBusiForm(BusiForm busiForm) {
		this.busiForm = busiForm;
	}
	public String getSuperviserName() {
		return superviserName;
	}
	public void setSuperviserName(String superviserName) {
		this.superviserName = superviserName;
	}
	public String getCurrentDealEmpName() {
		return currentDealEmpName;
	}
	public void setCurrentDealEmpName(String currentDealEmpName) {
		this.currentDealEmpName = currentDealEmpName;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
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
    
}
