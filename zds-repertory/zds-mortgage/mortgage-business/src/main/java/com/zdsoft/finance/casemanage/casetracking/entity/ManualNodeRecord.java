package com.zdsoft.finance.casemanage.casetracking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:CaseTrackingRecord.java
 * @Package:com.zdsoft.finance.casemanage.casetracking.entity
 * @Description:贷前流程节点记录
 * @author: caixiekang
 * @date:2017年1月14日 下午3:46:53
 * @version:v1.0
 */
@Entity
@Table(name = "case_manual_node_record_before")
public class ManualNodeRecord extends BaseEntity{
    /**
     * 当前节点
     */
    @Column(length = 32)
    private String nodeName;
   
    /**
     * 当前处理人
     */
    @Column(length = 32)
    private String operatorName;
    
    /**
     * 案件Id
     */
    @Column(length = 32)
    private String caseApplyId;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getCaseApplyId() {
        return caseApplyId;
    }

    public void setCaseApplyId(String caseApplyId) {
        this.caseApplyId = caseApplyId;
    }
    
    
}
