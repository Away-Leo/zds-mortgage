package com.zdsoft.finance.casemanage.record.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CaseApplyRecord.java
 * @Package com.zdsoft.finance.casemanage.record.entity
 * @Description: 案件录入信息
 * @author Liyb
 * @date 2017年1月16日 下午4:03:41
 * @version V1.0
 */
@Entity
@Table(name = "mark_case_apply_record")
public class CaseApplyRecord extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = -9217051707071495398L;

    @Column(length = 20)
    private String recordType;

    @Column(length = 20)
    private String recordStatus;

    @Column
    private Long recordDate;

    @Column(length = 32)
    private String operatorId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "caseApplyId", nullable = false)
    private CaseApply caseApply;

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public Long getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Long recordDate) {
        this.recordDate = recordDate;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public CaseApply getCaseApply() {
        return caseApply;
    }

    public void setCaseApply(CaseApply caseApply) {
        this.caseApply = caseApply;
    }

}
