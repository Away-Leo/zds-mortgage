package com.zdsoft.finance.projectfolder.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.zdsoft.framework.core.common.domain.BaseEntity;


/**
 * 项目资料类别
 * 
 * @author liuhai
 * 
 */
@Entity
@Table(name="zf_project_info_type")
public class ProjectInfoType extends BaseEntity {

	private static final long serialVersionUID = -5392727241147321479L;
	/**
	 * 分组名称
	 */
	@Column
	private String groupNm;
	/**
	 * 资料实体类名
	 */
	@Column
	private String classNm;
	/**
	 * 项目资料显示名称
	 */
	@Column
	private String infoLabel;
	/**
	 * 应用系统标识
	 */
	@Column
	private String appCd;
	/**
	 * 查看资料的URL
	 */
	@Column
	private String viewResource;
	/**
	 * 显示顺序
	 */
	@Column
	private Integer displayOrder;
	/**
	 * 是否流转事务表单，true 是, 如果是流转事务表单则需要显示流转审批意见，false，否,
	 */
	@Column
	private Boolean isFlow;
	/**
	 * 是否固定显示在项目树
	 */
	@Column
	private Boolean isFix;

	@OneToMany(mappedBy = "projectInfoType")
	@LazyCollection(LazyCollectionOption.EXTRA)
	private List<ProjectInfo> projectInfos = new ArrayList<ProjectInfo>();

    public ProjectInfoType(){
    	super();
    }
    public ProjectInfoType(String groupNm){
    	super();
    	this.groupNm = groupNm;
    }
    public ProjectInfoType(String groupNm,String infoLabel){
    	super();
    	this.groupNm = groupNm;
    	this.infoLabel = infoLabel;
    }
    public ProjectInfoType(String groupNm,String infoLabel,Integer displayOrder){
    	super();
    	this.groupNm = groupNm;
    	this.infoLabel = infoLabel;
    	this.displayOrder = displayOrder;
    }
	public List<ProjectInfo> getProjectInfos() {
		return projectInfos;
	}

	public void setProjectInfos(List<ProjectInfo> projectInfos) {
		this.projectInfos = projectInfos;
	}

	public String getGroupNm() {
		return groupNm;
	}

	public void setGroupNm(String groupNm) {
		this.groupNm = groupNm;
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

	public String getClassNm() {
		return classNm;
	}

	public void setClassNm(String classNm) {
		this.classNm = classNm;
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
