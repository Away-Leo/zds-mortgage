package com.zdsoft.finance.archive.vo;


import com.zdsoft.finance.archive.entity.Archive;
import com.zdsoft.finance.common.base.BaseVo;

/**
 * 档案管理
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @date 2016-12-21
 */
public class ArchiveVo extends BaseVo<Archive>{
	/**
	 * 档案清单id
	 */
	private String archiveFile_id;
	/**
	 * 文件借入状态
	 */
	private Integer entryStatus;
	/**
	 * 入库时间
	 */
	private Long enterTime;
	/**
	 * 借出时间
	 */
	private Long outerTime;
	/**
	 * 预计归还时间
	 */
	private Long expectTime;
	/**
	 * 借出备注
	 */
	private String entryDescription;
	/**
	 * 归还时间
	 */
	private Long returnTime;
	/**
	 * 文件数量
	 */
	private Integer number;
	/**
	 * 案件id
	 */
	private String project_id;
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
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	
	public ArchiveVo(){}
	
	public ArchiveVo(Archive po){
		super(po);
	}
	
	public Archive toPO(){
		Archive po = new Archive();
		return super.toPo(this,po);
	}
	
}
