package com.zdsoft.finance.filestore.vo;

import com.zdsoft.finance.common.enums.ENUM_LINK_CODE;
import org.springframework.beans.BeanUtils;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.common.utils.VoUtil;
import com.zdsoft.finance.filestore.entity.FileStore;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title FileStoreVo.java
 * @className FileStoreVo
 * @description 文件仓库vo
 * @author LiaoGuoWei
 * @create 2017/2/27 11:28
 * @version V1.0
 **/
public class FileStoreVo extends BaseVo<FileStore> {

    private static final long serialVersionUID = -3481701350351290321L;
    /**
     * 产品ID
     */
    private String productId;

    /**
     * 资料ID
     */
    private String materiaId;

    /**
     * 案件号
     */
    private String caseApplyId;

    /**
     * 序号
     */
    private Integer disOrder;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文档名称
     */
    private String documentName;

    /**
     * 来源
     */
    private String sourceCode;

    /**
     * 附件ID
     */
    private String attachmentId;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 状态
     */
    private String status;

    /**
     * 表单ID
     */
    private String businessId;

    /**
     * 环节编号
     */
    private String linkCode;
	 /**
     * 环节名称
     */
    private String linkName;
    /**
     * 资料大类编号
     */
    private String materiaTypeCode;

    /**
     * 资料大类名称
     */
    private String materiaTypeCodeName;

    /**
     * 资料类别编号
     */
    private String materiaCode;

    /**
     * 资料类别编号组
     */
    private String materiaCodes;

    /**
     * 资料类别名称
     */
    private String materiaCodeName;

    /**
     * 上传人
     */
    private String updateEmpName;

    /**
     * 上传时间
     */
    private String updateTimeStr;
    
    /**   
     * @Fields attachmentIds : 附件id集(多个用逗号拼接)  
     * model by liuhuan  
     */ 
    private String attachmentIds;

    /**
     * 使用环境类型（编辑页面、查看页面）
     */
    private String useType;

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getAttachmentIds() {
		return attachmentIds;
	}

	public void setAttachmentIds(String attachmentIds) {
		this.attachmentIds = attachmentIds;
	}

	public String getMateriaCodes() {
        return materiaCodes;
    }

    public void setMateriaCodes(String materiaCodes) {
        this.materiaCodes = materiaCodes;
    }

    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    public String getUpdateEmpName() {
        return updateEmpName;
    }

    public void setUpdateEmpName(String updateEmpName) {
        this.updateEmpName = updateEmpName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMateriaTypeCode() {
        return materiaTypeCode;
    }

    public void setMateriaTypeCode(String materiaTypeCode) {
        this.materiaTypeCode = materiaTypeCode;
    }

    public String getMateriaTypeCodeName() {
        return materiaTypeCodeName;
    }

    public void setMateriaTypeCodeName(String materiaTypeCodeName) {
        this.materiaTypeCodeName = materiaTypeCodeName;
    }

    public String getMateriaCode() {
        return materiaCode;
    }

    public void setMateriaCode(String materiaCode) {
        this.materiaCode = materiaCode;
    }

    public String getMateriaCodeName() {
        return materiaCodeName;
    }

    public void setMateriaCodeName(String materiaCodeName) {
        this.materiaCodeName = materiaCodeName;
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

    public FileStoreVo(){}
    
    /**
      * @Title: FileStoreVo
      * @Description: 带有文件库参数的构造方法
      * @author liaoguowei 
      * @param fileStore
      * @return 
      * @throws  
    */
    public FileStoreVo(FileStore fileStore){
        VoUtil.copyPoperties(fileStore,this,false,null,null);
        if(ObjectHelper.isNotEmpty(fileStore.getUpdateTime())){
            this.setUpdateTimeStr(DateHelper.dateToString(fileStore.getUpdateTime(),DateHelper.DATE_WITHMINUTE_FORMAT));
        }
        if(ObjectHelper.isNotEmpty(fileStore.getLinkCode())){
            this.setLinkName(ENUM_LINK_CODE.getLinkName(fileStore.getLinkCode()));
        }
    }
    
    /**
      * @Title:  toPo
      * @Description: 转换为域对象
      * @author liaoguowei 
      * @param
      * @return FileStore
      * @throws  
    */
    public FileStore toPo(){
        FileStore fileStore=new FileStore();
        BeanUtils.copyProperties(this,fileStore);
        return fileStore;
    }
}
