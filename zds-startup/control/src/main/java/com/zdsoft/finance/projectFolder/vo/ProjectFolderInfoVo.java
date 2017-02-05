package com.zdsoft.finance.projectFolder.vo;

import java.io.Serializable;


public class ProjectFolderInfoVo implements Serializable {

	private static final long serialVersionUID = -5466365652216444304L;
	/**
	 * 项目资料ID
	 */
	private String id;
	/**
	 * 项目资料类别ID
	 */
	private String typeId;
	/**
	 * 项目编号
	 */
	private String projectCd;
	/**
	 * 项目资料信息Id
	 */
	private String businessId;
	/**
	 * 分组名称
	 */
	private String groupNm;
	/**
	 * 资料实体类名
	 */
	private String classNm;
	/**
	 * 项目资料显示名称
	 */
	private String infoLabel;
	/**
	 * 应用系统标识
	 */
	private String appCd;
	/**
	 * 查看资料的URL
	 */
	private String viewResource;
	/**
	 * 显示顺序
	 */
	private Integer displayOrder;
	/**
	 * 是否流转事务表单，true 是, 如果是流转事务表单则需要显示流转审批意见，false，否,
	 */
	private Boolean isFlow;
	/**
	 * 是否固定显示在项目树
	 */
	private Boolean isFix;
	public ProjectFolderInfoVo(){
		
	}
	public ProjectFolderInfoVo(String groupNm){
		this.groupNm = groupNm;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getProjectCd() {
		return projectCd;
	}
	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getGroupNm() {
		return groupNm;
	}
	public void setGroupNm(String groupNm) {
		this.groupNm = groupNm;
	}
	public String getClassNm() {
		return classNm;
	}
	public void setClassNm(String classNm) {
		this.classNm = classNm;
	}
	public String getInfoLabel() {
		return infoLabel;
	}
	public void setInfoLabel(String infoLabel) {
		this.infoLabel = infoLabel;
	}
	public String getAppCd() {
		return appCd;
	}
	public void setAppCd(String appCd) {
		this.appCd = appCd;
	}
	public String getViewResource() {
		return viewResource;
	}
	public void setViewResource(String viewResource) {
		this.viewResource = viewResource;
	}
	public Integer getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}
	public Boolean getIsFlow() {
		return isFlow;
	}
	public void setIsFlow(Boolean isFlow) {
		this.isFlow = isFlow;
	}
	public Boolean getIsFix() {
		return isFix;
	}
	public void setIsFix(Boolean isFix) {
		this.isFix = isFix;
	}


}
