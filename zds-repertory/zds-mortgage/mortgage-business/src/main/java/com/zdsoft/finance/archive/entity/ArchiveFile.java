package com.zdsoft.finance.archive.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 档案清单
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @date 2016-12-21
 */
@Entity
@Table(name = "zf_archive_file")
public class ArchiveFile extends BaseEntity {

	private static final long serialVersionUID = -1032073737467888197L;

	/**
	 * 档案等级
	 */
	@Column(length = 32)
	private String archiveLevel;
	/**
	 * 名称
	 */
	@Column
	private String archiveNm;
	/**
	 * 数量
	 */
	@Column(name = "archive_number")
	private Integer number;
	/**
	 * 原件/复印类型
	 */
	@Column
	private String type;
	/**
	 * 档案柜号
	 */
	@Column
	private String cabinetNo;
	/**
	 * 归档人
	 */
	@Column
	private String fileBy;
	/**
	 * 归档时间
	 */
	@Column
	private Long fileTime;
	/**
	 * 状态
	 */
	@Column
	private Integer status;
	/**
	 * 档案编号
	 */
	@Column
	private String archiveCd;
	
	public String getArchiveLevel() {
		return archiveLevel;
	}
	public void setArchiveLevel(String archiveLevel) {
		this.archiveLevel = archiveLevel;
	}
	public String getArchiveNm() {
		return archiveNm;
	}
	public void setArchiveNm(String archiveNm) {
		this.archiveNm = archiveNm;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCabinetNo() {
		return cabinetNo;
	}
	public void setCabinetNo(String cabinetNo) {
		this.cabinetNo = cabinetNo;
	}
	public String getFileBy() {
		return fileBy;
	}
	public void setFileBy(String fileBy) {
		this.fileBy = fileBy;
	}
	public Long getFileTime() {
		return fileTime;
	}
	public void setFileTime(Long fileTime) {
		this.fileTime = fileTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getArchiveCd() {
		return archiveCd;
	}
	public void setArchiveCd(String archiveCd) {
		this.archiveCd = archiveCd;
	}
	
}
