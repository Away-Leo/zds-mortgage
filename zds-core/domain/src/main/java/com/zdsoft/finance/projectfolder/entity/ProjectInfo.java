package com.zdsoft.finance.projectfolder.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.zdsoft.framework.core.common.domain.BaseEntity;



@Entity
@Table(name="zf_project_info")
public class ProjectInfo extends BaseEntity {

	private static final long serialVersionUID = -9031554442457695315L;
	/**
	 * 项目编号
	 */
	@Column
	private String projectCd;
	/**
	 * 项目资料信息Id
	 */
	@Column
	private String businessId;
	/**
	 * 项目资料类别
	 */
	@ManyToOne
	@JoinColumn(name="projectInfoType_id")
	private ProjectInfoType projectInfoType;

	

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

	public ProjectInfoType getProjectInfoType() {
		return projectInfoType;
	}

	public void setProjectInfoType(ProjectInfoType projectInfoType) {
		this.projectInfoType = projectInfoType;
	}

}
