package com.zdsoft.finance.archive.vo;


import com.zdsoft.finance.archive.entity.ArchiveFile;
import com.zdsoft.finance.common.base.BaseVo;

/**
 * 档案清单
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @date 2016-12-21
 */
public class ArchiveFileVo extends BaseVo<ArchiveFile> {

	/**
	 * 档案等级
	 */
	private String archiveLevel;
	/**
	 * 名称
	 */
	private String archiveNm;
	/**
	 * 数量
	 */
	private Integer number;
	/**
	 * 原件/复印类型
	 */
	private String type;
	/**
	 * 档案柜号
	 */
	private String cabinetNo;
	/**
	 * 归档人
	 */
	private String fileBy;
	/**
	 * 归档时间
	 */
	private Long fileTime;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 档案编号
	 */
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
	
	public ArchiveFileVo(){}
	
	public ArchiveFileVo(ArchiveFile po){
		super(po);
	}
	
	public ArchiveFile toPO(){
		ArchiveFile po = new ArchiveFile();
		return super.toPo(this, po);
	}
	
}
