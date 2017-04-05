package com.zdsoft.finance.filestore.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.common.enums.ENUM_LINK_CODE;
import com.zdsoft.finance.filestore.entity.DownHistory;
import com.zdsoft.finance.filestore.entity.FileStore;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.springframework.beans.BeanUtils;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title DownHistoryVo.java
 * @className DownHistoryVo
 * @description 下载历史VO
 * @author LiaoGuoWei
 * @create 2017/2/27 11:28
 * @version V1.0
 **/
public class DownHistoryVo extends BaseVo<DownHistory> {

    private static final long serialVersionUID = 8011518425684587974L;
    /**
     * 所属分类
     */
    private String materiaTypeCode;

    /**
     * 类别
     */
    private String materiaCode;
    /**
     * 所属分类名称
     */
    private String materiaTypeName;

    /**
     * 类别名称
     */
    private String materiaName;

    /**
     * 下载人编号
     */
    private String downEmpCode;
    /**
     * 下载人名称
     */
    private String downEmpName;

    /**
     * 下载次数
     */
    private Long downFrequency;

    /**
     * 最近一次下载日期
     */
    private Long latestDownDate;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文档名称
     */
    private String documentName;

    /**
     * 来源编号
     */
    private String sourceCode;
    /**
     * 来源名称
     */
    private String sourceName;

    /**
     * 文件库Vo
     */
    private FileStoreVo fileStoreVo;

    public FileStoreVo getFileStoreVo() {
        return fileStoreVo;
    }

    public void setFileStoreVo(FileStoreVo fileStoreVo) {
        this.fileStoreVo = fileStoreVo;
    }

    public String getMateriaTypeName() {
        return materiaTypeName;
    }

    public void setMateriaTypeName(String materiaTypeName) {
        this.materiaTypeName = materiaTypeName;
    }

    public String getMateriaName() {
        return materiaName;
    }

    public void setMateriaName(String materiaName) {
        this.materiaName = materiaName;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
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

    public String getDownEmpName() {
        return downEmpName;
    }

    public void setDownEmpName(String downEmpName) {
        this.downEmpName = downEmpName;
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

    public DownHistoryVo(){}

    /**
     * 带有域对象的构造方法
     * @param downHis
     */
    public DownHistoryVo(DownHistory downHis){
        BeanUtils.copyProperties(downHis,this);
        if(ObjectHelper.isNotEmpty(downHis.getSourceCode())){
            this.setSourceName(ENUM_LINK_CODE.getLinkName(downHis.getSourceCode()));
        }
    }

    public DownHistory toPo(){
        DownHistory downHis=new DownHistory();
        BeanUtils.copyProperties(this,downHis);
        FileStore fileStore=new FileStore();
        BeanUtils.copyProperties(this.getFileStoreVo(),fileStore);
        downHis.setFileStore(fileStore);
        return downHis;
    }
}
