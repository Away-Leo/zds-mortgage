package com.zdsoft.finance.specialapprove.repository;

import java.util.HashMap;
import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.specialapprove.entity.RiskRules;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RiskRulesRepository.java 
 * @ClassName: RiskRulesRepository 
 * @Description:  风险规则明细Repository 
 * @author jingjy 
 * @date 2017年2月14日 下午5:54:38 
 * @version V1.0
 */
public interface RiskRulesRepository extends CustomRepository<RiskRules, String>{
	
	/** 
	 * @Title: findByCaseApplyIdAndSpecialStatus 
	 * @Description: 根据案件ID和状态查询风险规则明细
	 * @author wangrongwei
	 * @param caseApplyId
	 * @param specialStatus
	 * @return  
	 */ 
	public default List<RiskRules> findByCaseApplyIdAndSpecialStatus(String caseApplyId,String specialStatus){
		StringBuffer hql = new StringBuffer(" from RiskRules where caseApplyId = '");
		hql.append(caseApplyId);
		hql.append("' ");
		if(ObjectHelper.isEmpty(specialStatus)){
			hql.append(" and specialStatus is null ");
		}else{
			hql.append(" and specialStatus = '");
			hql.append(specialStatus);
			hql.append("' ");
		}
		return this.findByHql(hql.toString(), new HashMap<>());
	}
	
	/**
	 * 
	 * @Title: findRiskRulesInfoByCaseApplyId 
	 * @Description: 通过案件ID查询 风险规则
	 * @author jingjiyan 
	 * @param caseApplyId
	 * 			案件id
	 * @return
	 */
	public List<RiskRules> findRiskRulesInfoByCaseApplyId(String caseApplyId);
	
	/**
	 * 
	 * @Title: findByCaseApplyIdAndIsDeleted 
	 * @Description: 通过案件ID查询 风险规则
	 * @author xj 
	 * @param caseApplyId 案件id
	 * @param isDeleted 是否删除
	 * @return
	 */
	public List<RiskRules> findByCaseApplyIdAndIsDeleted(String caseApplyId,Boolean isDeleted);
	
	
	/** 
	 * @Title: findByRulesTypeAndCaseApplyId 
	 * @Description: 通过规则明细事项CODE与案件ID查询 风险规则
	 * @author wangrongwei
	 * @param rulesType 大类
	 * @param caseApplyId 案件ID
	 * @return  
	 */ 
	public List<RiskRules> findByRulesTypeAndCaseApplyId(String rulesType,String caseApplyId);
}
