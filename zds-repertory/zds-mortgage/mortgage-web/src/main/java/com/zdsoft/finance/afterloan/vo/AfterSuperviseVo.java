package com.zdsoft.finance.afterloan.vo;

import com.zdsoft.finance.afterloan.entity.AfterSupervise;
import com.zdsoft.finance.common.base.BaseVo;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterSuperviseVo.java 
 * @ClassName: AfterSuperviseVo 
 * @Description: 督办申请vo
 * @author xj 
 * @date 2017年2月16日 下午2:55:40 
 * @version V1.0
 */

public class AfterSuperviseVo extends BaseVo<AfterSupervise> {

	/**   
	 * 序列表    
	 */ 
	private static final long serialVersionUID = -7125203638274280305L;
	
	/**
	 * 督办类型code
	 */
	private String superviseType;
	
	/**
	 * 督办类型name
	 */
	private String superviseTypeName;
	
	/**
	 * 督办子类型code
	 */
	private String superviseChildType;
	
	/**
	 * 督办子类型name
	 */
	private String superviseChildTypeName;
	
	/**
	 * 督办接收人code(逗号隔开)
	 */
	private String superviseReceiverCode;
	
	/**
	 * 督办人name(逗号隔开)
	 */
	private String superviseReceiverName;
	
	/**
	 * 督办抄送人code
	 */
	private String superviseCopyReceiverCode;
	
	/**
	 * 督办抄送人Name
	 */
	private String superviseCopyReceiverName;
	
	/**
	 * 处理时限
	 */
	private Long processingDate;
	
	/**
	 * 督办人id
	 */
	private String superviserCode;
	
	/**
	 * 督办人Name
	 */
	private String superviserName;
	
	/**
	 * 跟踪部门
	 */
	private String departmentCode;
	
	/**
	 * 跟踪部门名称
	 */
	private String departmentName;
	
	/**
	 * 督办说明
	 */
	private String remark;
	
	/**
	 * 跟催id
	 */
	private String followInfoId;
	
	/**
	 * 创建人公司code
	 */
	private String companyCode;
	
	/**
	 * 最后一次反馈时间（用于督办详情列表）
	 */
	private Long feedbackDate;
	
	/**
	 * 最后一次反馈结果（用于督办详情列表）
	 */
	private String feedbackRresults;
	
	/**
	 * 案件Id
	 */
	private String caseApplyId;
	
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

	public String getCaseApplyId() {
		return caseApplyId;
	}
	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}
	public String getSuperviseTypeName() {
		return superviseTypeName;
	}
	public void setSuperviseTypeName(String superviseTypeName) {
		this.superviseTypeName = superviseTypeName;
	}
	public String getSuperviseChildTypeName() {
		return superviseChildTypeName;
	}
	public void setSuperviseChildTypeName(String superviseChildTypeName) {
		this.superviseChildTypeName = superviseChildTypeName;
	}
	public String getSuperviserName() {
		return superviserName;
	}
	public void setSuperviserName(String superviserName) {
		this.superviserName = superviserName;
	}
	@Override
	public String toString() {
		return "AfterSuperviseVo [superviseType=" + superviseType + ", superviseTypeName=" + superviseTypeName
				+ ", superviseChildType=" + superviseChildType + ", superviseChildTypeName=" + superviseChildTypeName
				+ ", superviseReceiverCode=" + superviseReceiverCode + ", superviseReceiverName="
				+ superviseReceiverName + ", superviseCopyReceiverCode=" + superviseCopyReceiverCode
				+ ", superviseCopyReceiverName=" + superviseCopyReceiverName + ", processingDate=" + processingDate
				+ ", superviserCode=" + superviserCode + ", superviserName=" + superviserName + ", departmentCode="
				+ departmentCode + ", departmentName=" + departmentName + ", remark=" + remark + ", followinfoId="
				+ followInfoId + ", companyCode=" + companyCode + ", caseApplyId=" + caseApplyId + "]";
	}
	public AfterSuperviseVo() {
		super();
	}
	public AfterSuperviseVo(AfterSupervise po){
		super(po,null,new String[]{"superviseType","superviseChildType"});
	}
	
	public AfterSupervise toPO(){
		AfterSupervise po = new AfterSupervise();
		return super.toPo(this, po);
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
