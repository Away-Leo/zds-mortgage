package com.zdsoft.finance.casemanage.handleMortgage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;
/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MaterialPromise.java 
 * @ClassName: MaterialPromise 
 * @Description: 后补资料承诺实体类
 * @author zhoushichao 
 * @date 2017年2月18日 下午2:05:27 
 * @version V1.0 
 */ 
@Entity
@Table(name = "case_material_promise")
public class MaterialPromise extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 借款借据
	 */   
	public static final String BORROWER_IOU = "YWDM001500217";
	/**
	 * 补充合同
	 */   
	public static final String SUPPLEMENTARY = "YWDM001500216";
	/**
	 * 他项权证
	 */   
	public static final String HIS_WARRANT = "YWDM001500411";
	/**
	 * 委托公证书
	 */   
	public static final String NOTARIZATION = "YWDM001500403";
	/**
	 * 未完成
	 */
	public static final String NOT_COMPLETE = "0";
	/**
	 * 完成
	 */
	public static final String COMPLETE = "1";

	/**
     * 案件Id
     */
    @Column(length = 32)
    private String caseApplyId;
    /**
     * 后补资料类型
     */
    @Column(length = 20)
    private String materialTypeCode;
    /**
     * 后补资料类型名称
     */
    @Column(length = 64)
    private String materialTypeName;
    /**
     * 后补资料类型父类
     */
    @Column(length = 20)
    private String pMaterialTypeCode;
    /**
     * 后补资料类型名称父类
     */
    @Column(length = 64)
    private String pMaterialTypeName;
    /**
     * 预计后补时间
     */
    @Column
    private Long predictDate;
    /**
     * 操作人code
     */
    @Column(length = 32)
    private String operatorCode;
    /**
     * 操作人姓名
     */
    @Column(length = 64)
    private String operatorName;
    /**
     * 承诺时间 
     */
    @Column
    private Long promiseDate;
    /**
     * 承诺内容
     */
    @Column(length = 512)
    private String promiseRemark;
    /**
     * 上传完成时间
     */
    @Column
    private Long uploadDate;
    /**
     * 上传人code
     */
    @Column(length = 32)
    private String uploadPersonCode;
    /**
     * 上传人姓名
     */
    @Column(length = 64)
    private String uploadPersonName;
    /**
     * 是否后补完成（0：未完成；1：完成）
     */
    @Column(length = 1)
    private String isUpload;
    /**
     * 附件id
     */
    @Column(length = 32)
    private String attachmentId;
	public String getCaseApplyId() {
		return caseApplyId;
	}
	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}
	public String getMaterialTypeCode() {
		return materialTypeCode;
	}
	public void setMaterialTypeCode(String materialTypeCode) {
		this.materialTypeCode = materialTypeCode;
	}
	public String getMaterialTypeName() {
		return materialTypeName;
	}
	public void setMaterialTypeName(String materialTypeName) {
		this.materialTypeName = materialTypeName;
	}
	public Long getPredictDate() {
		return predictDate;
	}
	public void setPredictDate(Long predictDate) {
		this.predictDate = predictDate;
	}
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public Long getPromiseDate() {
		return promiseDate;
	}
	public void setPromiseDate(Long promiseDate) {
		this.promiseDate = promiseDate;
	}
	public String getPromiseRemark() {
		return promiseRemark;
	}
	public void setPromiseRemark(String promiseRemark) {
		this.promiseRemark = promiseRemark;
	}
	public Long getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Long uploadDate) {
		this.uploadDate = uploadDate;
	}
	public String getUploadPersonCode() {
		return uploadPersonCode;
	}
	public void setUploadPersonCode(String uploadPersonCode) {
		this.uploadPersonCode = uploadPersonCode;
	}
	public String getUploadPersonName() {
		return uploadPersonName;
	}
	public void setUploadPersonName(String uploadPersonName) {
		this.uploadPersonName = uploadPersonName;
	}
	public String getIsUpload() {
		return isUpload;
	}
	public void setIsUpload(String isUpload) {
		this.isUpload = isUpload;
	}
	public String getpMaterialTypeCode() {
		return pMaterialTypeCode;
	}
	public void setpMaterialTypeCode(String pMaterialTypeCode) {
		this.pMaterialTypeCode = pMaterialTypeCode;
	}
	public String getpMaterialTypeName() {
		return pMaterialTypeName;
	}
	public void setpMaterialTypeName(String pMaterialTypeName) {
		this.pMaterialTypeName = pMaterialTypeName;
	}
	public String getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}
}
