package com.zdsoft.finance.parameter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;
/**
 * 诉讼资料表
 * @author hw
 *
 */
@Entity
@Table(name="zf_litigation_data")
public class LitigationData extends BaseEntity{
	private static final long serialVersionUID = -5511409644551737390L;
	/**
	 * 类型
	 */
	@Column(name="type",length=50)
	private String type;
	/**
	 * 资料名称
	 */
	@Column(name="name",length=50)
	private String name;
	/**
	 * 资料id
	 */
	@Column(name="fileId",length=20)
	private String fileId;
	/**
	 * 资料备注
	 */
	@Column(name="remark",length=500)
	private String remark;
	/**
	 * 文件名称
	 */
	@Column(name="fileName",length=100)
	private String fileName;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
