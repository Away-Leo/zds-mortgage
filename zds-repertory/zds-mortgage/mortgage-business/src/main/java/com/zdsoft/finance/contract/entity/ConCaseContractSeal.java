package com.zdsoft.finance.contract.entity;

import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.framework.core.common.domain.BaseEntity;

import javax.persistence.*;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConCaseContractSeal.java
 * @ClassName: ConCaseContractSeal
 * @Description: 案件合同盖章
 * @author denglw
 * @date 2017年3月15日 下午4:14:53
 * @version V1.0
 */
@Entity
@Table(name = "con_case_contract_seal")
public class ConCaseContractSeal extends BaseEntity{
    private static final long serialVersionUID = 1L;
    /**
     * 申请类型
     */
    @Column(length = 20)
    private String applyType;

    /**
     * 驻点寄出快递单号
     */
    @Column(length = 32)
    private String trackingNoSend;

    /**
     * 退回信托快递单号
     */
    @Column(length = 32)
    private String trackingNoReceive;
    /**
     * 备注
     */
    @Column(length = 512)
    private String remark;


    /**
     * 流程基础信息
     */
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="busiFormId")
    private BusiForm busiForm;

    /**
     * 案件合同ID
     */
    @Column(length = 32)
    private String caseContractId;


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

    public String getCaseContractId() {
        return caseContractId;
    }

    public void setCaseContractId(String caseContractId) {
        this.caseContractId = caseContractId;
    }

    public BusiForm getBusiForm() {
        return busiForm;
    }

    public void setBusiForm(BusiForm busiForm) {
        this.busiForm = busiForm;
    }
}
