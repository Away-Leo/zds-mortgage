//package com.zdsoft.finance.projectatta.vo;
//
//import com.zdsoft.finance.common.base.BaseVo;
//import com.zdsoft.finance.project.entity.Project;
//import com.zdsoft.finance.spi.projectatta.dto.ProjectAttaSourceEnum;
//import com.zdsoft.finance.spi.projectatta.dto.ProjectAttaStatusEnum;
//import com.zdsoft.framework.core.common.util.ObjectHelper;
//
///** 
//* 版权所有：重庆正大华日软件有限公司
//* @Title: ProjectAttaVo.java 
//* @Package com.zdsoft.finance.project.vo 
//* @Description: 业务附件Vo
//* @author jingyh 
//* @date 2016年12月2日 上午11:02:18 
//* @version V1.0 
//*/
//public class ProjectAttaVo extends BaseVo<ProjectAtta> {
//
//	/**
//     * 项目Id
//     */
//    private String projectId;
//    
//    /**
//     * 项目案号
//     */
//    private String projectCd;
//    
//    /**
//     * 客户代码
//     */
//    private String clientCd;
//    
//    /**
//     * 客户名称
//     */
//    private String clientNm;
//    
//	/**
//	 * 附件类型
//	 */
//	private String attaType;
//	
//	/**
//	 * 附件类型名称
//	 */
//	private String attaTypeNm;
//	
//	/**
//	 * 业务表单Id
//	 */
//	private String businessKey;
//	
//	/**
//	 * 关联业务类型
//	 */
//	private String businessType;
//	
//	/**
//	 * 附件Id
//	 */
//	private String attachmentId;
//	
//	/**
//	 * 附件名称
//	 */
//	private String attachmentLabel;
//	
//	/**
//	 * 附件状态
//	 */
//	private String status;
//	
//	/**
//	 * 附件状态名称
//	 */
//	private String statusStr;
//	
//	/**
//	 * 项目归档Id
//	 */
//	private String filedId;
//	
//	/**
//	 * 操作者代码
//	 */
//	private String operatorCd;
//	
//	/**
//	 * 操作者名字
//	 */
//	private String operatorNm;
//	
//	/**
//	 * 操作时间
//	 */
//	private Long operatorTime;
//	
//	/**
//	 * 附件来源
//	 */
//	private String attaSource;
//	
//	/**
//	 * 附件来源名称
//	 */
//	private String attaSourceStr;
//	
//	/**
//	 * 附件模板Id
//	 */
//	private String templateId;
//	
//	/**
//	 * 自定义的项目附件的名称
//	 */
//	private String attaNm;
//	
//	/**
//	 * 备注
//	 */
//	private String remark;
//
//	public String getProjectId() {
//		return projectId;
//	}
//
//	public void setProjectId(String projectId) {
//		this.projectId = projectId;
//	}
//
//	public String getProjectCd() {
//		return projectCd;
//	}
//
//	public void setProjectCd(String projectCd) {
//		this.projectCd = projectCd;
//	}
//
//	public String getClientCd() {
//		return clientCd;
//	}
//
//	public void setClientCd(String clientCd) {
//		this.clientCd = clientCd;
//	}
//	
//	public String getClientNm() {
//		return clientNm;
//	}
//
//	public void setClientNm(String clientNm) {
//		this.clientNm = clientNm;
//	}
//
//	public String getStatusStr() {
//		return statusStr;
//	}
//
//	public void setStatusStr(String statusStr) {
//		this.statusStr = statusStr;
//	}
//
//	public String getAttaSourceStr() {
//		return attaSourceStr;
//	}
//
//	public void setAttaSourceStr(String attaSourceStr) {
//		this.attaSourceStr = attaSourceStr;
//	}
//
//	public String getAttaType() {
//		return attaType;
//	}
//
//	public void setAttaType(String attaType) {
//		this.attaType = attaType;
//	}
//
//	public String getAttaTypeNm() {
//		return attaTypeNm;
//	}
//
//	public void setAttaTypeNm(String attaTypeNm) {
//		this.attaTypeNm = attaTypeNm;
//	}
//
//	public String getBusinessKey() {
//		return businessKey;
//	}
//
//	public void setBusinessKey(String businessKey) {
//		this.businessKey = businessKey;
//	}
//
//	public String getBusinessType() {
//		return businessType;
//	}
//
//	public void setBusinessType(String businessType) {
//		this.businessType = businessType;
//	}
//
//	public String getAttachmentId() {
//		return attachmentId;
//	}
//
//	public void setAttachmentId(String attachmentId) {
//		this.attachmentId = attachmentId;
//	}
//
//	public String getAttachmentLabel() {
//		return attachmentLabel;
//	}
//
//	public void setAttachmentLabel(String attachmentLabel) {
//		this.attachmentLabel = attachmentLabel;
//	}
//
//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	public String getFiledId() {
//		return filedId;
//	}
//
//	public void setFiledId(String filedId) {
//		this.filedId = filedId;
//	}
//
//	public String getOperatorCd() {
//		return operatorCd;
//	}
//
//	public void setOperatorCd(String operatorCd) {
//		this.operatorCd = operatorCd;
//	}
//
//	public String getOperatorNm() {
//		return operatorNm;
//	}
//
//	public void setOperatorNm(String operatorNm) {
//		this.operatorNm = operatorNm;
//	}
//
//	public Long getOperatorTime() {
//		return operatorTime;
//	}
//
//	public void setOperatorTime(Long operatorTime) {
//		this.operatorTime = operatorTime;
//	}
//
//	public String getAttaSource() {
//		return attaSource;
//	}
//
//	public void setAttaSource(String attaSource) {
//		this.attaSource = attaSource;
//	}
//
//	public String getTemplateId() {
//		return templateId;
//	}
//
//	public void setTemplateId(String templateId) {
//		this.templateId = templateId;
//	}
//
//	public String getAttaNm() {
//		return attaNm;
//	}
//
//	public void setAttaNm(String attaNm) {
//		this.attaNm = attaNm;
//	}
//
//	public String getRemark() {
//		return remark;
//	}
//
//	public void setRemark(String remark) {
//		this.remark = remark;
//	}
//
//	public ProjectAttaVo() {
//		super();
//	}
//	
//	/**
//	 * 通过实体构造
//	 * @param projectAtta
//	 * @param args
//	 * @param simpleArgs
//	 */
//	public ProjectAttaVo(ProjectAtta projectAtta, String[] args, String[] simpleArgs) {
//		super(projectAtta, args, simpleArgs);
//		// 处理项目信息
//		if (ObjectHelper.isNotEmpty(projectAtta) && ObjectHelper.isNotEmpty(projectAtta.getProject())) {
//			Project project = projectAtta.getProject();
//			this.projectId = project.getId();
//			this.projectCd = project.getProjectCd();
//			this.clientCd = project.getClientCd();
//			this.clientNm = project.getClientNm();
//		}
//		// 状态或值的处理
//		this.statusStr = ProjectAttaStatusEnum.getName(this.status);
//		this.attaSourceStr = ProjectAttaSourceEnum.getName(this.attaSource);
//		if (ObjectHelper.isEmpty(this.attaNm)) {
//			this.attaNm = this.attachmentLabel;
//		}
//	}
//
//	/**
//	 * 
//	 * @Title: toPo 
//	 * @Description: 转换成实体
//	 * @author jingyh 
//	 * @date 2016年12月2日 上午11:09:46
//	 * @return 
//	 * ProjectAtta 
//	 * @throws
//	 */
//	public ProjectAtta toPo() {
//		return super.toPo(this, new ProjectAtta());
//	}
//}
