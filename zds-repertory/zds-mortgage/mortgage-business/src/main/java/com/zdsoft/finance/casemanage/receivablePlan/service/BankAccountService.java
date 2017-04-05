package com.zdsoft.finance.casemanage.receivablePlan.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.receivablePlan.entity.BankAccount;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivableInfo;
import com.zdsoft.finance.common.exception.BusinessException;

/**
* 版权所有：重庆正大华日软件有限公司   
* @Title: BankAccountService.java 
* @Package com.zdsoft.finance.casemanage.receivablePlan.service 
* @Description: 还款计划Service 
* @author zjx  
* @date 2017年2月22日 下午8:48:33 
* @version V1.0   
*/
public interface BankAccountService extends BaseService<BankAccount> {

	
	/**
	 * 保存还款计划信息
	 *
	 * @author wrw
	 * @param receivableInfo 还款计划基本信息
	 * @param bankReAccount 收款银行卡
	 * @param bankLoanAccount 放款银行卡
	 * @param receivablePlanJson 具体还款计划
	 * @param applyTerm 申请期限
	 * @param applyTermUnit 申请期限单位
	 * @throws BusinessException 
	 * @throws Exception 
	 */
	public ReceivableInfo saveReceivableInfo(ReceivableInfo receivableInfo, BankAccount bankReAccount,
			BankAccount bankLoanAccount, String receivablePlanJson,Integer applyTerm,String applyTermUnit) throws Exception;
	
	/** 
	 * @Title: findByCaseApplyIdAndAccountType 
	 * @Description: 根据caseApplyId和类型查询出银行账户信息
	 * @author zjx 
	 * @param caseApplyId案件ID
	 * @param accountType银行账户类型
	 * @return  
	 */ 
	public BankAccount findByCaseApplyIdAndAccountType(String caseApplyId,Integer accountType);
	
	/** 
	 * @Title: saveOrUpdateBankAccounts 
	 * @Description: 批量保存和修改银行信息
	 * @author zjx 
	 * @param accounts银行账户信息List
	 * @return  
	 * @throws BusinessException 
	 */ 
	public List<BankAccount> saveOrUpdateBankAccounts(List<BankAccount> accounts) throws BusinessException;
}
