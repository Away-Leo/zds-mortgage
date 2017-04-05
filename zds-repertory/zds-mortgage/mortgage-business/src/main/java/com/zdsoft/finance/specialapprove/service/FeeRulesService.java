package com.zdsoft.finance.specialapprove.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.specialapprove.entity.FeeRules;


/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: FeeRulesService.java 
 * @ClassName: FeeRulesService 
 * @Description: 费用规则明细接口
 * @author jingjy 
 * @date 2017年2月14日 下午5:52:39 
 * @version V1.0
 */
public interface FeeRulesService extends BaseService<FeeRules>{
	
	/**
	 * 
	 * @Title: findFeeRulesInfoByCaseApplyId 
	 * @Description: 根据案件ID查询费用规则信息
	 * @author wangrongwei
	 * @param  caseApplyId 案件ID
	 * @param  specialStatus 审批状态
	 * @return
	 */
	public List<FeeRules> findFeeRulesInfoByCaseApplyIdAndSpecialStatus(String caseApplyId,String specialStatus);
	
	/**
	 * 
	 * @Title: findFeeRulesInfoByCaseApplyId 
	 * @Description: 根据案件ID查询费用规则信息
	 * @author jingjy 
	 * @param 
	 * 		caseApplyId 案件ID
	 * @return
	 */
	public List<FeeRules> findFeeRulesInfoByCaseApplyId(String caseApplyId);
	
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
