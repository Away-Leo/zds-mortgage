package com.zdsoft.finance.marketing.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.marketing.entity.CaseApplyAfterCustomer;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CaseApplyAfterCustomerService.java 
 * @ClassName: CaseApplyAfterCustomerService 
 * @Description: 贷后客户申请人service
 * @author xj 
 * @date 2017年3月2日 下午5:37:05 
 * @version V1.0
 */
public interface CaseApplyAfterCustomerService extends BaseService<CaseApplyAfterCustomer> {
	
	/**
	 * 
	 * @Title: findByCaseApplyIdAndJoinType 
	 * @Description: 通过案件id和参与类型查询
	 * @author xj 
	 * @param caseApplyId 案件id
	 * @param joinType 参与类型
	 * @return
	 */
	public List<CaseApplyAfterCustomer> findByCaseApplyIdAndJoinType(String caseApplyId, String joinType);
}
