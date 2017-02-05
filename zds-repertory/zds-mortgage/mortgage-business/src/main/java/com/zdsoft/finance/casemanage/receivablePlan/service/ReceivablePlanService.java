package com.zdsoft.finance.casemanage.receivablePlan.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlan;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlanForm;
import com.zdsoft.finance.casemanage.receivablePlan.entity.RepayPlan;
import com.zdsoft.finance.common.exception.BusinessException;

/**
 * 还款计划
 * 
 * @author wangrongwei
 * @create 2017-01-07 19:11
 */
public interface ReceivablePlanService extends BaseService<ReceivablePlan> {

	/**
	 * 还款计划生成
	 * 
	 * @param planForm
	 * @return
	 */
	public List<RepayPlan> receivablePlanGuarate(ReceivablePlanForm planForm);

	
	/**
	 * 保存还款计划
	 *
	 * @author wrw
	 * @param receivableInfoId 还款计划信息ID
	 * @param loanApplyId 放款ID
	 * @param list 具体还款计划
	 * @return
	 * @throws BusinessException
	 */
	public boolean saveReceivablePlan(String receivableInfoId, String loanApplyId, List<ReceivablePlan> list)
			throws BusinessException;

}
