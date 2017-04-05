package com.zdsoft.finance.filestore.entity;

import com.zdsoft.framework.core.common.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title FileStore.java
 * @className FileStore
 * @description 文件库域对象
 * @author LiaoGuoWei
 * @create 2017/2/27 10:08
 * @version V1.0
 **/
@Entity
@Table(name = "common_filestore")
public class FileStore extends BaseEntity {

    private static final long serialVersionUID = -3215559590866671818L;

    /**
     * 产品ID
     */
    @Column(length = 32)
    private String productId;

    /**
     * 资料ID
     */
    @Column(length = 32)
    private String materiaId;

    /**
     * 案件号
     */
    @Column(length = 32)
    private String caseApplyId;

    /**
     * 序号
     */
    @Column(length = 3)
    private Integer disOrder;

    /**
     * 文件类型
     */
    @Column(length = 2)
    private String fileType;

    /**
     * 文件名
     */
    @Column(length = 128)
    private String fileName;

    /**
     * 文档名称
     */
    @Column(length = 64)
    private String documentName;

    /**
     * 来源
     */
    @Column(length = 32)
    private String sourceCode;

    /**
     * 附件ID
     */
    @Column(length = 32)
    private String attachmentId;

    /**
     * 经度
     */
    @Column(precision = 13,scale = 10)
    private Double longitude;

    /**
     * 纬度
     */
    @Column(precision = 13,scale = 10)
    private Double latitude;

    /**
     * 状态
     */
    @Column(length = 1)
    private String status;

    /**
     * 表单ID
     */
    @Column(length = 32)
    private String businessId;

    /**
     * 环节编号
     */
    @Column(length = 32)
    private String linkCode;

    /**
     * 资料大类编号
     */
    private transient String materiaTypeCode;
    /**
     * 资料大类编号名称
     */
    private transient String materiaTypeCodeName;

    /**
     * 资料类别编号
     */
    private transient String materiaCode;
    /**
     * 资料类别编号名称
     */
    private transient String materiaCodeName;

    /**
     * 资料类别编号组
     */
    private transient String materiaCodes;

    public String getMateriaTypeCodeName() {
        return materiaTypeCodeName;
    }

    public void setMateriaTypeCodeName(String materiaTypeCodeName) {
        this.materiaTypeCodeName = materiaTypeCodeName;
    }

    public String getMateriaCodeName() {
        return materiaCodeName;
    }

    public void setMateriaCodeName(String materiaCodeName) {
        this.materiaCodeName = materiaCodeName;
    }

    public String getMateriaCodes() {
        return materiaCodes;
    }

    public void setMateriaCodes(String materiaCodes) {
        this.materiaCodes = materiaCodes;
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(String materiaId) {
        this.materiaId = materiaId;
    }

    public String getCaseApplyId() {
        return caseApplyId;
    }

    public void setCaseApplyId(String caseApplyId) {
        this.caseApplyId = caseApplyId;
    }

    public Integer getDisOrder() {
        return disOrder;
    }

    public void setDisOrder(Integer disOrder) {
        this.disOrder = disOrder;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
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

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getLinkCode() {
        return linkCode;
    }

    public void setLinkCode(String linkCode) {
        this.linkCode = linkCode;
    }
}
