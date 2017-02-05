package com.zdsoft.finance.casemanage.material.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:CaseAttachment.java
 * @Package:com.zdsoft.finance.casemanage.atta.entity
 * @Description:案件资料清单附件
 * @author: gonght
 * @date:2017年1月15日 上午11:10:50
 * @version:v1.0
 */
@Entity
@Table(name = "case_material_list_atta")
public class CaseMaterialListAtta extends BaseEntity {

	private static final long serialVersionUID = 2400984049082289093L;

	/**
	 * 关联案件资料清单
	 */
	@ManyToOne
	@JoinColumn(name = "caseMaterialListId")
	private CaseMaterialList caseMaterialList;

	/**
	 * 是否定位
	 */
	@Column(length = 32)
	private String position;

	/**
	 * 定位地址
	 */
	@Column(length = 256)
	private String positionPath;

	/**
	 * 来源
	 */
	@Column(length = 32)
	private String source;

	/**
	 * 附件.Id
	 */
	@Column(length = 32)
	private String attachmentId;

	/**
	 * 附件.文件名(冗余)
	 */
	@Column(length = 128)
	private String attachmentName;

	/**
	 * 附件.上传人Code(冗余)
	 */
	@Column(length = 32)
	private String operatorCd;

	/**
	 * 附件.上传人名称(冗余)
	 */
	@Column(length = 128)
	private String operatorNm;

	/**
	 * 附件.上传时间(冗余)
	 */
	@Column
	private Long operatorTime;

	/**
	 * 案件资料清单附件下载日志集合
	 */
	@OneToMany(mappedBy = "caseMaterialListAtta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CaseMaterialListAttaLog> caseMaterialListAttaLogs = new ArrayList<CaseMaterialListAttaLog>();

	/**
	 * 业务表单id
	 */
	@Column(length = 32)
	private String businessId;

	/**
	 * 附件状态(非正式、正式)
	 */
	@Column(length = 32)
	private String state;

	/**
	 * 关联案件资料清单Id，不持久化
	 */
	@Transient
	private String caseMaterialListId;
	/**
	 * 资料类别code
	 */
	@Transient
	private String materiaCode;

	/**
	 * 资料类型名称
	 */
	@Transient
	private String materiaName;

	/**
	 * 产品子类Id
	 */
	@Transient
	private String caseApplyId;

	public CaseMaterialList getCaseMaterialList() {
		return caseMaterialList;
	}

	public void setCaseMaterialList(CaseMaterialList caseMaterialList) {
		this.caseMaterialList = caseMaterialList;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPositionPath() {
		return positionPath;
	}

	public void setPositionPath(String positionPath) {
		this.positionPath = positionPath;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getOperatorCd() {
		return operatorCd;
	}

	public void setOperatorCd(String operatorCd) {
		this.operatorCd = operatorCd;
	}

	public String getOperatorNm() {
		return operatorNm;
	}

	public void setOperatorNm(String operatorNm) {
		this.operatorNm = operatorNm;
	}

	public Long getOperatorTime() {
		return operatorTime;
	}

	public void setOperatorTime(Long operatorTime) {
		this.operatorTime = operatorTime;
	}

	public List<CaseMaterialListAttaLog> getCaseMaterialListAttaLogs() {
		return caseMaterialListAttaLogs;
	}

	public void setCaseMaterialListAttaLogs(List<CaseMaterialListAttaLog> caseMaterialListAttaLogs) {
		this.caseMaterialListAttaLogs = caseMaterialListAttaLogs;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCaseMaterialListId() {
		return caseMaterialListId;
	}

	public void setCaseMaterialListId(String caseMaterialListId) {
		this.caseMaterialListId = caseMaterialListId;
	}

	public String getMateriaCode() {
		return materiaCode;
	}

	public void setMateriaCode(String materiaCode) {
		this.materiaCode = materiaCode;
	}

	public String getMateriaName() {
		return materiaName;
	}

	public void setMateriaName(String materiaName) {
		this.materiaName = materiaName;
	}

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}
}