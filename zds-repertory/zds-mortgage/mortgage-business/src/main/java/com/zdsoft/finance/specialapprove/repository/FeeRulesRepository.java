package com.zdsoft.finance.specialapprove.repository;

import java.util.HashMap;
import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.specialapprove.entity.FeeRules;
import com.zdsoft.framework.core.common.util.ObjectHelper;


/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: FeeRulesRepository.java 
 * @ClassName: FeeRulesRepository 
 * @Description: 费用规则明细Repository 
 * @author jingjy 
 * @date 2017年2月14日 下午5:51:05 
 * @version V1.0
 */
public interface FeeRulesRepository extends CustomRepository<FeeRules, String>{
	
	/** 
	 * @Title: findFeeRulesInfoByCaseApplyIdAndSpecialStatus 
	 * @Description: 根据案件ID查询费用规则信息
	 * @author wangrongwei
	 * @param caseApplyId 案件ID
	 * @param specialStatus 审批状态
	 * @return  
	 */ 
	public default List<FeeRules> findFeeRulesInfoByCaseApplyIdAndSpecialStatus(String caseApplyId,String specialStatus){
		StringBuffer hql = new StringBuffer(" from FeeRules where caseApplyId = '");
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
	 * @Title: findFeeRulesInfoByCaseApplyId 
	 * @Description:根据案件查询费用规则信息
	 * @author jingjy 
	 * @param caseApplyId
	 * 		案件ID
	 * @return
	 */
	public List<FeeRules> findFeeRulesInfoByCaseApplyId(String caseApplyId);
	
	/**
	 * 
	 * @Title: findByCaseApplyIdAndIsDeleted 
	 * @Description: 根据案件查询费用规则信息
	 * @author xj 
	 * @param caseApplyId 案件id
	 * @param isDeleted 是否被删除
	 * @return
	 */
	public List<FeeRules> findByCaseApplyIdAndIsDeleted(String caseApplyId,Boolean isDeleted);

}
