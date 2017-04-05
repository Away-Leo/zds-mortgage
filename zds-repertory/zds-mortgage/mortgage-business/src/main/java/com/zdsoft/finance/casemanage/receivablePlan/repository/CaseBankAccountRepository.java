package com.zdsoft.finance.casemanage.receivablePlan.repository;

import com.zdsoft.finance.casemanage.receivablePlan.entity.BankAccount;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 还款计划
 * @author wangrongwei
 * @create 2017-01-07 19:11
 */
public interface CaseBankAccountRepository extends CustomRepository<BankAccount, String>{
    /** 
     * @Title: findByCaseApplyIdAndAccountType 
     * @Description: 根据caseApplyId和类型查询出实体
     * @author zjx 
     * @param caseApplyId案件ID
     * @param accountType账户类型
     * @return  
     */ 
    public BankAccount findByCaseApplyIdAndAccountType(String caseApplyId, Integer accountType);
}
