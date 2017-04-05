package com.zdsoft.finance.marketing.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.marketing.entity.RiskAuditApprove;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RiskAuditApproveService.java 
 * @ClassName: RiskAuditApproveService 
 * @Description: 风险审核 风险信息回显 接口定义
 * @author dengyy 
 * @date 2017年3月4日 上午11:46:17 
 * @version V1.0 
 */
public interface RiskAuditApproveService extends BaseService<RiskAuditApprove>{

	/**
	 * 
	 * @Title: saveOrUpdateApproveInfo 
	 * @Description: 保存或更新风险审核审批意见
	 * @author jingyh 
	 * @param procInstanceId
	 * @param taskInstanceId
	 * @param opinionIds
	 * @throws Exception
	 */
	public void saveOrUpdateApproveInfo(String procInstanceId, String taskInstanceId,List<String> opinionIds) throws Exception;
	
	/**
	 * 
	 * @Title: findRiskAuditApproveInfos 
	 * @Description: 查询指定人员的审批意见
	 * @author jingyh 
	 * @param procInstanceId
	 * @param taskInstanceId
	 * @param empCode
	 * @return
	 */
	public List<RiskAuditApprove> findRiskAuditApproveInfos(String procInstanceId,String taskInstanceId, String empCode);
	
	/**
	 * 
	 * @Title: saveApproveInfos 
	 * @Description: 批量保存审核审批意见
	 * @author jingyh 
	 * @param procInstanceId
	 * @param taskInstanceId
	 * @param approveInfos
	 * @return
	 * @throws Exception
	 */
	public List<RiskAuditApprove> saveApproveInfos(String procInstanceId, String taskInstanceId,List<RiskAuditApprove> approveInfos) throws Exception;
}
