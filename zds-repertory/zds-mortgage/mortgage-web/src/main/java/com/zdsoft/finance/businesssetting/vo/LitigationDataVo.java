package com.zdsoft.finance.businesssetting.vo;

import com.zdsoft.finance.businesssetting.entity.LitigationData;
import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.common.utils.VoUtil;
import org.springframework.beans.BeanUtils;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @author LiaoGuoWei
 * @version V1.0
 * @title LitigationDataVo.java
 * @className LitigationDataVo
 * @description 诉讼资料配置VO
 * @create 2017-02-14 16:04
 **/
public class LitigationDataVo extends BaseVo<LitigationData>{


    private static final long serialVersionUID = 3912757845319613598L;
    /**
     * 类型编号
     */
    private String fileTypeCode;
    /**
     * 类型名称
     */
    private String fileTypeCodeName;
    /**
     * 资料备注
     */
    private String remark;
    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 附件ID
     */
    private String attachmentId;

    /**
     * 附件路径
     */
    private String filePath;

    public String getFileTypeCodeName() {
        return fileTypeCodeName;
    }

    public void setFileTypeCodeName(String fileTypeCodeName) {
        this.fileTypeCodeName = fileTypeCodeName;
    }

    public String getFileTypeCode() {
        return fileTypeCode;
    }

    public void setFileTypeCode(String fileTypeCode) {
        this.fileTypeCode = fileTypeCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LitigationDataVo(){}
    public LitigationDataVo(LitigationData litigationData){
        VoUtil.copyPoperties(litigationData,this,false,null,new String[]{"fileTypeCode"});
    }

    public LitigationData toPo(){
        LitigationData data=new LitigationData();
        BeanUtils.copyProperties(this,data);
        return data;
    }
}
