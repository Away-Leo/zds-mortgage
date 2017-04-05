package com.zdsoft.finance.businesssetting.entity;

import com.zdsoft.framework.core.common.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title LitigationData.java
 * @className LitigationData
 * @description 诉讼资料域对象
 * @author LiaoGuoWei
 * @create 2017/3/3 11:45
 * @version V1.0
 **/
@Entity
@Table(name="buss_litigationfile")
public class LitigationData extends BaseEntity{
	private static final long serialVersionUID = -5511409644551737390L;
	/**
	 * 类型
	 */
	@Column(length=32)
	private String fileTypeCode;
	/**
	 * 资料备注
	 */
	@Column(length=512)
	private String remark;
	/**
	 * 文件名称
	 */
	@Column(length=64)
	private String fileName;

	/**
	 * 附件ID
	 */
	@Column(length = 32)
	private String attachmentId;

	public String getFileTypeCode() {
		return fileTypeCode;
	}

	public void setFileTypeCode(String fileTypeCode) {
		this.fileTypeCode = fileTypeCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}
}
