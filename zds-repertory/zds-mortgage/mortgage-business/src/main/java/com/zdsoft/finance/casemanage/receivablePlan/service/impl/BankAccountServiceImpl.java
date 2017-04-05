package com.zdsoft.finance.casemanage.receivablePlan.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.receivablePlan.entity.BankAccount;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivableInfo;
import com.zdsoft.finance.casemanage.receivablePlan.repository.CaseBankAccountRepository;
import com.zdsoft.finance.casemanage.receivablePlan.service.BankAccountService;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivablePlanInfoService;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivablePlanTempService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.BigDecimalCalculateTwo;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

@Service
public class BankAccountServiceImpl extends BaseServiceImpl<BankAccount, CaseBankAccountRepository>
		implements BankAccountService {

	@Autowired
	private ReceivablePlanInfoService receivablePlanInfoService;

	@Autowired
    private ReceivablePlanTempService receivablePlanTempService;
	
	@Autowired
	private CaseApplyService caseApplyService;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public ReceivableInfo saveReceivableInfo(ReceivableInfo receivableInfo, BankAccount bankReAccount,
			BankAccount bankLoanAccount, String receivablePlanJson,Integer applyTerm,String applyTermUnit) throws Exception {

		if(ObjectHelper.isNotEmpty(bankReAccount)){
			// 保存收款银行信息
			BankAccount recba = this.findByCaseApplyIdAndAccountType(receivableInfo.getCaseApplyId(), BankAccount.REPAYMENT);
			if (ObjectHelper.isEmpty(recba)) {
				recba = new BankAccount();
			}
			BeanUtils.copyProperties(bankReAccount, recba,new String[] { "id" });
			recba.setCaseApplyId(receivableInfo.getCaseApplyId());
			recba.setAccountType(BankAccount.REPAYMENT);
			this.saveOrUpdateEntity(recba);
		}
		
		if(ObjectHelper.isNotEmpty(bankLoanAccount)){
		// 保存放款银行信息
		BankAccount loanba = this.findByCaseApplyIdAndAccountType(receivableInfo.getCaseApplyId(), BankAccount.RECEIVABLES);
			if (ObjectHelper.isEmpty(loanba)) {
				loanba = new BankAccount();
			}
			BeanUtils.copyProperties(bankLoanAccount, loanba,new String[] { "id" });
			loanba.setAccountType(BankAccount.RECEIVABLES);
			loanba.setCaseApplyId(receivableInfo.getCaseApplyId());
			this.saveOrUpdateEntity(loanba);
		}
		
		// 保存还款计划基本信息
		ReceivableInfo po;
		if (ObjectHelper.isNotEmpty(receivableInfo.getId())) {
			po = receivablePlanInfoService.findOne(receivableInfo.getId());
			BeanUtils.copyProperties(receivableInfo, po, new String[] { "id","receivablePlan" });
		} else {
			po = receivableInfo;
		}
		//更改小数位数
		po.setLoanMonthRate(BigDecimalCalculateTwo.div(po.getLoanMonthRate(),
				ReceivableInfo.RECEIVABLEINFO_DAY.equals(po.getLoanMonthRateUnit())?BigDecimal.valueOf(1000):BigDecimal.valueOf(100)));
		po.setOverdueDailyRate(BigDecimalCalculateTwo.div(po.getOverdueDailyRate(),
				ReceivableInfo.RECEIVABLEINFO_DAY.equals(po.getOverdueDailyRateUnit())?BigDecimal.valueOf(1000):BigDecimal.valueOf(100)));
		if(ObjectHelper.isNotEmpty(po.getSyntheticalRate())){
			po.setSyntheticalRate(BigDecimalCalculateTwo.div(po.getSyntheticalRate(),
					ReceivableInfo.RECEIVABLEINFO_DAY.equals(po.getSyntheticalRateUnit())?BigDecimal.valueOf(1000):BigDecimal.valueOf(100)));
		}
		if(ObjectHelper.isNotEmpty(po.getInternalRateReturn())){
			po.setInternalRateReturn(BigDecimalCalculateTwo.div(po.getInternalRateReturn(),
					ReceivableInfo.RECEIVABLEINFO_DAY.equals(po.getInternalRateReturnUnit())?BigDecimal.valueOf(1000):BigDecimal.valueOf(100)));
		}
		//更新案件实体类
		CaseApply caseApply = caseApplyService.findOne(po.getCaseApplyId());
		caseApply.setRepayMethod(po.getRepaymentType());
		caseApply.setApplyRate(po.getLoanMonthRate());
		caseApply.setApplyRateUnit(po.getLoanMonthRateUnit());
		caseApply.setOverdueRate(po.getOverdueDailyRate());
		caseApply.setOverdueRateUnit(po.getOverdueDailyRateUnit());
		caseApply.setSynthesizeRate(po.getSyntheticalRate());
		caseApply.setSynthesizeRateUnit(po.getSyntheticalRateUnit());
		caseApply.setDynamicRate(po.getInternalRateReturn());
		caseApply.setDynamicRateUnit(po.getInternalRateReturnUnit());
		caseApply.setApplyTerm(applyTerm);
		caseApply.setApplyTermUnit(applyTermUnit);
		
		po = receivablePlanInfoService.saveOrUpdateEntity(po);
		receivablePlanTempService.deleteReceivablePlanTempByReceivableInfoId(po.getId());
		receivablePlanTempService.saveReceivablePlanTemp(caseApply.getId(), po.getId(), receivablePlanJson);
		//保存关系
		caseApply.setReceivableInfo(po);
		caseApplyService.updateEntity(caseApply);
		return po;
	}

	@Override
	public BankAccount findByCaseApplyIdAndAccountType(String caseApplyId, Integer accountType) {
		return this.customReposity.findByCaseApplyIdAndAccountType(caseApplyId,accountType);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public List<BankAccount> saveOrUpdateBankAccounts(List<BankAccount> accounts) throws BusinessException {
		for(BankAccount account : accounts){
			this.saveOrUpdateEntity(account);
		}
		return accounts;
	}
	

}
