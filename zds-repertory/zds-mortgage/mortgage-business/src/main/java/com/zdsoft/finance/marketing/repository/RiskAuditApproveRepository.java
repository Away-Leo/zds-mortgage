
package com.zdsoft.finance.marketing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.marketing.entity.RiskAuditApprove;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RiskAuditApproveRepository.java 
 * @ClassName: RiskAuditApproveRepository 
 * @Description: 风险审核 风险信息回显 Repository 
 * @author dengyy 
 * @date 2017年3月4日 上午11:45:06 
 * @version V1.0 
 */
public interface RiskAuditApproveRepository  extends CustomRepository<RiskAuditApprove, String> {
    
    /**
     * 
     * @Title: findByProcInstanceIdAndTaskInstanceId 
     * @Description: 获取当前节点的风险审核信息
     * @author jingyh 
     * @param procInstanceId 流程id
     * @param taskInstanceId 任务id
     * @param applyCode 申请人编号
     * @return
     */
    public List<RiskAuditApprove> findByProcInstanceIdAndTaskInstanceIdAndApplyCode(String procInstanceId,String taskInstanceId, String applyCode);
    
    /**
     * 
     * @Title: cancleConcern 
     * @Description: 删除当前节点的风险审核信息
     * @author jingyh 
     * @param procInstanceId 流程id
     * @param taskInstanceId 任务id
     * @param applyCode 申请人编号
     * @return
     */
    @Modifying
    @Query(value="DELETE FROM case_risk_audit_approve WHERE procInstanceId= :procInstanceId AND taskInstanceId= :taskInstanceId AND applyCode = :applyCode",nativeQuery=true)
    public int deleteRiskAuditApprove(@Param("procInstanceId")String procInstanceId, @Param("taskInstanceId")String taskInstanceId,@Param("applyCode")String applyCode );
    
    /**
     * 
     * @Title: unCheckedAllRiskApprove 
     * @Description: 更新所有审批意见为未选择
     * @author jingyh 
     * @param procInstanceId
     * @param taskInstanceId
     * @param applyCode
     * @return
     */
    @Modifying
    @Query(value="UPDATE case_risk_audit_approve SET hasChecked='F' WHERE procInstanceId= :procInstanceId AND taskInstanceId= :taskInstanceId AND applyCode = :applyCode",nativeQuery=true)
    public int unCheckedAllRiskApprove(@Param("procInstanceId")String procInstanceId, @Param("taskInstanceId")String taskInstanceId,@Param("applyCode")String applyCode);
}
