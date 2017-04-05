package com.zdsoft.finance.casemanage.handleMortgage.vo;

import com.zdsoft.finance.casemanage.handleMortgage.entity.MaterialPromise;
import com.zdsoft.finance.common.base.BaseVo;
/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MaterialPromiseVo.java 
 * @ClassName: MaterialPromiseVo 
 * @Description: 后补资料承诺Vo类
 * @author zhoushichao 
 * @date 2017年2月18日 下午2:05:27 
 * @version V1.0 
 */ 
public class MaterialPromiseVo extends BaseVo<MaterialPromise> {

	private static final long serialVersionUID = 1L;

	/**
     * 案件Id
     */
    private String caseApplyId;
    /**
     * 后补资料类型
     */
    private String materialTypeCode;
    /**
     * 后补资料类型名称
     */
    private String materialTypeName;
    /**
     * 后补资料类型父类
     */
    private String pMaterialTypeCode;
    /**
     * 后补资料类型名称父类
     */
    private String pMaterialTypeName;
    /**
     * 预计后补时间
     */
    private Long predictDate;
    /**
     * 操作人code
     */
    private String operatorCode;
    /**
     * 操作人姓名
     */
    private String operatorName;
    /**
     * 承诺时间 
     */
    private Long promiseDate;
    /**
     * 承诺内容
     */
    private String promiseRemark;
    /**
     * 上传完成时间
     */
    private Long uploadDate;
    /**
     * 上传人code
     */
    private String uploadPersonCode;
    /**
     * 上传人姓名
     */
    private String uploadPersonName;
    /**
     * 是否后补完成（0：未完成；1：完成）
     */
    private String isUpload;
    /**
     * 附件id
     */
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
	public MaterialPromiseVo(){
        super();
    }
    public MaterialPromiseVo(MaterialPromise materialPromise){
        super(materialPromise, null, null);
    }
    public MaterialPromise toPo(){
    	MaterialPromise materialPromise = new MaterialPromise();
        return super.toPo(this, materialPromise);
    }
}
