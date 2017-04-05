package com.zdsoft.finance.casemanage.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.marketing.entity.RiskAuditApprove;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RiskAuditApproveVo.java 
 * @ClassName: RiskAuditApproveVo 
 * @Description: 风险审核 风险信息回显 vo
 * @author dengyy 
 * @date 2017年3月4日 下午3:39:12 
 * @version V1.0 
 */
public class RiskAuditApproveVo extends BaseVo<RiskAuditApprove> {

    private static final long serialVersionUID = -5856474126142176662L;

    /**
     * 流程id
     */
    private String  procInstanceId ;
    
    /**
     * 任务id
     */
    private String  taskInstanceId ;
    
    /**
     * 风险信息id
     */
    private String approvalOpinionId ;
    
    /**
     * 申请人code
     */
    private String applyCode ;
    
    /**
     * 申请人姓名 
     */
    private String applyName ;
    
    /**
     * 申请时间 
     */
    private Long applyDate ;

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
    
    public RiskAuditApproveVo(){
        super();
    }
    
    public RiskAuditApproveVo(RiskAuditApprove entity){
        super(entity);
    }
    
    public RiskAuditApprove toPo(){
        RiskAuditApprove entity = new RiskAuditApprove();
        return super.toPo(this, entity);
    }
}
