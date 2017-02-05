package com.zdsoft.finance.casemanage.receivablePlan.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivableInfo;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlanForm;
import com.zdsoft.finance.casemanage.receivablePlan.entity.RepayPlan;

/**
 * 还款计划
 * @author wangrongwei
 * @create 2017-01-07 19:11
 */
public interface ReceivablePlanInfoService extends BaseService<ReceivableInfo>{
	
	/**还款计划生成
	 * @param planForm
	 * @return
	 */
	public List<RepayPlan> receivablePlanGuarate(ReceivablePlanForm planForm);

}
