package com.zdsoft.finance.cooperator.vo;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AttachmentVo.java 
 * @ClassName: AttachmentVo 
 * @Description: 附件vo
 * @author liuwei
 * @date 2017年3月9日 上午11:25:54 
 * @version V1.0
 */
public class AttachmentVo {

	/**
	 * 附件id
	 */
	private String id;
	
	/**
	 * 附件名称
	 */
	private String fileNm;

	/**
	 * 附件类型
	 */
	private String fileType;

	/**
	 * 附件路径
	 */
	private String filePath;

	/**
	 * 附件标题
	 */
	private String fileLabel;

	/**
	 * 附件大小
	 */
	private String fileSize;

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileNm() {
		return fileNm;
	}

	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileLabel() {
		return fileLabel;
	}

	public void setFileLabel(String fileLabel) {
		this.fileLabel = fileLabel;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

}
