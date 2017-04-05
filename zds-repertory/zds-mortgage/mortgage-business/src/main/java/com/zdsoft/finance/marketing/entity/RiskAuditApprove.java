package com.zdsoft.finance.marketing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RiskAuditApprove.java 
 * @ClassName: RiskAuditApprove 
 * @Description: 风险审核 风险信息回显
 * @author dengyy 
 * @date 2017年3月4日 上午11:38:44 
 * @version V1.0 
 */
@Entity
@Table(name="case_risk_audit_approve")
public class RiskAuditApprove extends BaseEntity{

    private static final long serialVersionUID = 1L;
    
    /**
     * 流程id
     */
    @Column(length=32)
    private String  procInstanceId;
    
    /**
     * 任务id
     */
    @Column(length=32)
    private String  taskInstanceId;
    
    /**
     * 风险意见id
     */
    @Column(length=32)
    private String approvalOpinionId;
    
    /**
     * 风险意见类容
     */
    @Column(length = 1024)
    private String opinionContent;
    
    /**
     * 申请人code
     */
    @Column(length=32)
    private String applyCode;
    
    /**
     * 申请人姓名 
     */
    @Column(length=64)
    private String applyName;
    
    /**
     * 申请时间 
     */
    @Column(length=16)
    private Long applyDate;
    
    /**
     * 是否已选择
     */
    @Column
    @Type(type="true_false")
    private Boolean hasChecked = Boolean.FALSE;

    public String getProcInstanceId() {
        return procInstanceId;
    }

    public void setProcInstanceId(String procInstanceId) {
        this.procInstanceId = procInstanceId;
    }

    public String getTaskInstanceId() {
        return taskInstanceId;
    }

    public void setTaskInstanceId(String taskInstanceId) {
        this.taskInstanceId = taskInstanceId;
    }

    public String getApprovalOpinionId() {
        return approvalOpinionId;
    }

    public void setApprovalOpinionId(String approvalOpinionId) {
        this.approvalOpinionId = approvalOpinionId;
    }

    public String getApplyCode() {
        return applyCode;
    }

    public void setApplyCode(String applyCode) {
        this.applyCode = applyCode;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public Long getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Long applyDate) {
        this.applyDate = applyDate;
    }

	public String getOpinionContent() {
		return opinionContent;
	}

	public void setOpinionContent(String opinionContent) {
		this.opinionContent = opinionContent;
	}

	public Boolean getHasChecked() {
		return hasChecked;
	}

	public void setHasChecked(Boolean hasChecked) {
		this.hasChecked = hasChecked;
	}

}
