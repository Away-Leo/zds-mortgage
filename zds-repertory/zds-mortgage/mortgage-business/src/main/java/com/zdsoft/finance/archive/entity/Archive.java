package com.zdsoft.finance.archive.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 档案管理
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @date 2016-12-21
 */
@Entity
@Table(name = "zf_archive")
public class Archive extends BaseEntity {

	private static final long serialVersionUID = -3768555669980589667L;

	/**
	 * 案件
	 */
	@Column(length = 32)
	private String project_id;
	/**
	 * 档案清单id
	 */
	@Column(length = 32)
	private String archiveFile_id;
	/**
	 * 文件借入状态
	 */
	@Column
	private Integer entryStatus;
	/**
	 * 入库时间
	 */
	@Column
	private Long enterTime;
	/**
	 * 借出时间
	 */
	@Column
	private Long outerTime;
	/**
	 * 预计归还时间
	 */
	@Column
	private Long expectTime;
	/**
	 * 借出备注
	 */
	@Column(length = 512)
	private String entryDescription;
	/**
	 * 归还时间
	 */
	@Column
	private Long returnTime;
	/**
	 * 文件数量
	 */
	@Column(name = "archive_number")
	private Integer number;
	
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getArchiveFile_id() {
		return archiveFile_id;
	}
	public void setArchiveFile_id(String archiveFile_id) {
		this.archiveFile_id = archiveFile_id;
	}
	public Integer getEntryStatus() {
		return entryStatus;
	}
	public void setEntryStatus(Integer entryStatus) {
		this.entryStatus = entryStatus;
	}
	public Long getEnterTime() {
		return enterTime;
	}
	public void setEnterTime(Long enterTime) {
		this.enterTime = enterTime;
	}
	public Long getOuterTime() {
		return outerTime;
	}
	public void setOuterTime(Long outerTime) {
		this.outerTime = outerTime;
	}
	public Long getExpectTime() {
		return expectTime;
	}
	public void setExpectTime(Long expectTime) {
		this.expectTime = expectTime;
	}
	public String getEntryDescription() {
		return entryDescription;
	}
	public void setEntryDescription(String entryDescription) {
		this.entryDescription = entryDescription;
	}
	public Long getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(Long returnTime) {
		this.returnTime = returnTime;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
}
