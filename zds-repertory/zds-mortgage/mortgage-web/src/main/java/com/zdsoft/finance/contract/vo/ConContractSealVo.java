package com.zdsoft.finance.contract.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.contract.entity.ConCaseContractSeal;
import com.zdsoft.finance.customer.vo.BeforeWorkUnitVo;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import javax.persistence.Column;
import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 *
 * @author denglw
 * @version V1.0
 * @Title: ConContractSealVo.java
 * @ClassName: ConContractSealVo
 * @Description: 合同盖章vo
 * @date 2017年3月18日 上午10:53:39
 */
public class ConContractSealVo extends BaseVo<ConCaseContractSeal> {
    /**
     * 申请类型
     */
    private String applyType;

    /**
     * 驻点寄出快递单号
     */
    private String trackingNoSend;

    /**
     * 退回信托快递单号
     */
    private String trackingNoReceive;

    /**
     * 申请id
     */
    private String busiFormId;
    /**
     * 合同id
     */
    private String caseContractId;
    /**
     * 备注
     */
    private String remark;

    /**
     * 新增页面附件临时Busi id
     */
    private String fileUuid;
    /**
     * false保存 true提交
     */
    private boolean submitStatus;

    /**
     * 盖章明细
     */
    private List<ConContractSealDetailVo> sealDetails;

    /**
     * 页面返回盖章明细JSON
     */
    private String sealDetailStrs;


    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getTrackingNoSend() {
        return trackingNoSend;
    }

    public void setTrackingNoSend(String trackingNoSend) {
        this.trackingNoSend = trackingNoSend;
    }

    public String getTrackingNoReceive() {
        return trackingNoReceive;
    }

    public void setTrackingNoReceive(String trackingNoReceive) {
        this.trackingNoReceive = trackingNoReceive;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isSubmitStatus() {
        return submitStatus;
    }

    public void setSubmitStatus(boolean submitStatus) {
        this.submitStatus = submitStatus;
    }

    public List<ConContractSealDetailVo> getSealDetails() {
        return sealDetails;
    }

    public void setSealDetails(List<ConContractSealDetailVo> sealDetails) {
        this.sealDetails = sealDetails;
    }

    public String getSealDetailStrs() {
        return sealDetailStrs;
    }

    public String getBusiFormId() {
        return busiFormId;
    }

    public void setBusiFormId(String busiFormId) {
        this.busiFormId = busiFormId;
    }

    public String getCaseContractId() {
        return caseContractId;
    }

    public void setCaseContractId(String caseContractId) {
        this.caseContractId = caseContractId;
    }

    public String getFileUuid() {
        return fileUuid;
    }

    public void setFileUuid(String fileUuid) {
        this.fileUuid = fileUuid;
    }

    public void setSealDetailStrs(String sealDetailStrs) {
        if (ObjectHelper.isNotEmpty(sealDetailStrs)) {
            JSONArray dealArray = JSONArray.fromObject(sealDetailStrs);

            this.sealDetails = JSONArray.toList(dealArray, new ConContractSealDetailVo(),
                    new JsonConfig());
        }
        this.sealDetailStrs = sealDetailStrs;
    }
}
