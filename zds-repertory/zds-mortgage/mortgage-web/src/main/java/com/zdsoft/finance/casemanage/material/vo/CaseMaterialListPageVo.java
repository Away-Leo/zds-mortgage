package com.zdsoft.finance.casemanage.material.vo;

public class CaseMaterialListPageVo {

	/**
	 * 案件资料清单附件id
	 */
	private String id;
	/**
	 * 案件资料清单id
	 */
	private String cmlid;
	/**
	 * 附件id
	 */
	private String attachmentId;
	/**
	 * 文件名
	 */
	private String attachmentName;
	/**
	 * 上传人
	 */
	private String operatorNm;
	/**
	 * 上传时间
	 */
	private String operatorTime;
	/**
	 * 是否定位
	 */
	private String position;
	/**
	 * 来源
	 */
	private String source;
	/**
	 * 所属分类 大类
	 */
	private String materiaTypeName;
	/**
	 * 资料类型名称 小类
	 */
	private String materiaName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCmlid() {
		return cmlid;
	}

	public void setCmlid(String cmlid) {
		this.cmlid = cmlid;
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

	public String getOperatorNm() {
		return operatorNm;
	}

	public void setOperatorNm(String operatorNm) {
		this.operatorNm = operatorNm;
	}

	public String getOperatorTime() {
		return operatorTime;
	}

	public void setOperatorTime(String operatorTime) {
		this.operatorTime = operatorTime;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getMateriaTypeName() {
		return materiaTypeName;
	}

	public void setMateriaTypeName(String materiaTypeName) {
		this.materiaTypeName = materiaTypeName;
	}

	public String getMateriaName() {
		return materiaName;
	}

	public void setMateriaName(String materiaName) {
		this.materiaName = materiaName;
	}
}