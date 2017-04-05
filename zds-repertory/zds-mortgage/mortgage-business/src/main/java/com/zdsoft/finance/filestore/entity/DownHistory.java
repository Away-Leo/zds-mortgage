package com.zdsoft.finance.filestore.entity;

import com.zdsoft.framework.core.common.domain.BaseEntity;

import javax.persistence.*;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title DownHistory.java
 * @className DownHistory
 * @description 下载历史域对象
 * @author LiaoGuoWei
 * @create 2017/2/27 10:07
 * @version V1.0
 **/
@Table(name = "common_downhis")
@Entity
public class DownHistory extends BaseEntity{

    private static final long serialVersionUID = -4673861768996374119L;
    /**
     * 所属分类
     */
    @Column(length = 32)
    private String materiaTypeCode;

    /**
     * 类别
     */
    @Column(length = 32)
    private String materiaCode;

    /**
     * 下载人编号
     */
    @Column(length = 32)
    private String downEmpCode;
    /**
     * 下载人名称
     */
    @Column(length = 64)
    private String downEmpName;

    /**
     * 下载次数
     */
    @Column(length = 20)
    private Long downFrequency;

    /**
     * 最近一次下载日期
     */
    @Column(length = 16)
    private Long latestDownDate;

    /**
     * 文件库ID
     */
    @ManyToOne
    @JoinColumn(name = "fileStore_id")
    private FileStore fileStore;

    /**
     * 文件名
     */
    private transient String fileName;

    /**
     * 文档名称
     */
    private transient String documentName;

    /**
     * 来源
     */
    private transient String sourceCode;

    public String getDownEmpName() {
        return downEmpName;
    }

    public void setDownEmpName(String downEmpName) {
        this.downEmpName = downEmpName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getMateriaTypeCode() {
        return materiaTypeCode;
    }

    public void setMateriaTypeCode(String materiaTypeCode) {
        this.materiaTypeCode = materiaTypeCode;
    }

    public String getMateriaCode() {
        return materiaCode;
    }

    public void setMateriaCode(String materiaCode) {
        this.materiaCode = materiaCode;
    }

    public String getDownEmpCode() {
        return downEmpCode;
    }

    public void setDownEmpCode(String downEmpCode) {
        this.downEmpCode = downEmpCode;
    }

    public Long getDownFrequency() {
        return downFrequency;
    }

    public void setDownFrequency(Long downFrequency) {
        this.downFrequency = downFrequency;
    }

    public Long getLatestDownDate() {
        return latestDownDate;
    }

    public void setLatestDownDate(Long latestDownDate) {
        this.latestDownDate = latestDownDate;
    }

    public FileStore getFileStore() {
        return fileStore;
    }

    public void setFileStore(FileStore fileStore) {
        this.fileStore = fileStore;
    }
}
