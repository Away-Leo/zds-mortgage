package com.zdsoft.finance.contract.entity;

import com.zdsoft.framework.core.common.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConCaseContractSealDetail.java
 * @ClassName: ConCaseContractSealDetail
 * @Description: 案件合同盖章明细
 * @author denglw
 * @date 2017年3月15日 下午4:14:53
 * @version V1.0
 */
@Entity
@Table(name = "con_case_contract_seal_detail")
public class ConCaseContractSealDetail  extends BaseEntity{
    /**
     * 资料类型Code
     */
    @Column(length = 32)
    private String materialCode;

    /**
     * 资料类型名称
     */
    @Column(length = 64)
    private String materialName;

    /**
     * 原件（份数）
     */
    @Column
    private Integer originalNum;

    /**
     * 复印件（份数）
     */
    @Column
    private Integer copyNum;

    /**
     * 申请公章
     */
    @Column(length = 84)
    private String applySeal;

    /**
     * 其它说明
     */
    @Column(length = 128)
    private String otherExplain;

    /**
     * 案件合同盖章ID
     */
    @Column(length = 32)
    private String caseContractSealId;

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Integer getOriginalNum() {
        return originalNum;
    }

    public void setOriginalNum(Integer originalNum) {
        this.originalNum = originalNum;
    }

    public Integer getCopyNum() {
        return copyNum;
    }

    public void setCopyNum(Integer copyNum) {
        this.copyNum = copyNum;
    }

    public String getApplySeal() {
        return applySeal;
    }

    public void setApplySeal(String applySeal) {
        this.applySeal = applySeal;
    }

    public String getOtherExplain() {
        return otherExplain;
    }

    public void setOtherExplain(String otherExplain) {
        this.otherExplain = otherExplain;
    }

    public String getCaseContractSealId() {
        return caseContractSealId;
    }

    public void setCaseContractSealId(String caseContractSealId) {
        this.caseContractSealId = caseContractSealId;
    }
}
