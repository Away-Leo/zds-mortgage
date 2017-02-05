package com.zdsoft.finance.casemanage.receivablePlan.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.receivablePlan.entity.BankAccount;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivableInfo;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlan;
import com.zdsoft.finance.common.exception.BusinessException;

/**
 * 还款计划
 * 
 * @author wangrongwei
 * @create 2017-01-07 19:11
 */
public interface BankAccountService extends BaseService<BankAccount> {

	
	/**
	 * 保存还款计划信息
	 *
	 * @author wrw
	 * @param receivableInfo 还款计划基本信息
	 * @param bankReAccount 收款银行卡
	 * @param bankLoanAccount 放款银行卡
	 * @param receivablePlan 具体还款计划
	 * @throws BusinessException 
	 */
	public ReceivableInfo saveReceivableInfo(ReceivableInfo receivableInfo, BankAccount bankReAccount,
			BankAccount bankLoanAccount, List<ReceivablePlan> receivablePlan) throws BusinessException;
}
