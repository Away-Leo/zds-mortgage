package com.zdsoft.finance.marketing.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.marketing.entity.CaseApplyAfterCustomer;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CaseApplyAfterCustomerRepository.java 
 * @ClassName: CaseApplyAfterCustomerRepository 
 * @Description: 案件客户关系表（贷后案件申请人）
 * @author xj 
 * @date 2017年3月13日 上午11:17:34 
 * @version V1.0
 */
public interface CaseApplyAfterCustomerRepository extends CustomRepository<CaseApplyAfterCustomer, String> {
	
	/**
	 * 
	 * @Title: findByCaseApplyIdAndJoinType 
	 * @Description: 根据案件Id和类型查询贷前客户信息
	 * @author xj 
	 * @param caseApplyId 案件id
	 * @param joinType 参与类型
	 * @return
	 */
	public List<CaseApplyAfterCustomer> findByCaseApplyIdAndJoinType(String caseApplyId, String joinType);
	
	/**
	 * 
	 * @Title: findByCaseApplyIdAndAfterCustomerId 
	 * @Description: 通过案件id和客户id查询关联关系
	 * @author xj 
	 * @param caseApplyId 案件id
	 * @param beforeCustomerId  客户id
	 * @return
	 */
	public CaseApplyAfterCustomer findByCaseApplyIdAndAfterCustomerId(String caseApplyId, String beforeCustomerId);


}
