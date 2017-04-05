package com.zdsoft.finance.specialapprove.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.specialapprove.entity.RiskRules;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RiskRulesService.java 
 * @ClassName: RiskRulesService 
 * @Description: 风险规则明细接口
 * @author jingjy 
 * @date 2017年2月14日 下午5:52:21 
 * @version V1.0
 */
public interface RiskRulesService extends BaseService<RiskRules>{
	
	/** 
	 * @Title: findByCaseApplyIdAndSpecialStatus 
	 * @Description: 根据案件ID和特批状态查询风险特批规则信息
	 * @author wangrongwei
	 * @param caseApplyId
	 * @param specialStatus
	 * @return  
	 */ 
	public List<RiskRules> findByCaseApplyIdAndSpecialStatus(String caseApplyId,String specialStatus);
	
	/**
	 * 
	 * @Title: findRiskRulesInfoByCaseApplyId 
	 * @Description: 根据项目ID查询风险规则信息
	 * @author jingjy 
	 * @param caseApplyId
	 * 			案件id
	 * @return
	 */
	public List<RiskRules> findRiskRulesInfoByCaseApplyId(String caseApplyId);
	
	/**
	 * 
	 * @Title: judgeComplianceReviewPassed 
	 * @Description: 如果存在审批通过数据返回true，一条数据就没有返回true，其他返回false
	 * @author xj 
	 * @param caseApplyId 案件id
	 * @return
	 */
	public boolean judgeComplianceReviewPassed(String caseApplyId);

}
