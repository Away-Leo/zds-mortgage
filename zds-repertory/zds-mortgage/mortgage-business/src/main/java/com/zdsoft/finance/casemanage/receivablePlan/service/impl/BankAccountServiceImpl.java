package com.zdsoft.finance.casemanage.receivablePlan.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.receivablePlan.entity.BankAccount;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivableInfo;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlan;
import com.zdsoft.finance.casemanage.receivablePlan.repository.ReceivableRepository;
import com.zdsoft.finance.casemanage.receivablePlan.service.BankAccountService;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivablePlanInfoService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

@Service
public class BankAccountServiceImpl extends BaseServiceImpl<BankAccount, CustomRepository<BankAccount, String>>
		implements BankAccountService {

	@Autowired
	private ReceivablePlanInfoService receivablePlanInfoService;

	@Autowired
	private ReceivableRepository receivableRepository;
	
	@Autowired
	private CaseApplyService caseApplyService;

	@Override
	@Transactional
	public ReceivableInfo saveReceivableInfo(ReceivableInfo receivableInfo, BankAccount bankReAccount,
			BankAccount bankLoanAccount, List<ReceivablePlan> receivablePlan) throws BusinessException {

		if(ObjectHelper.isNotEmpty(bankReAccount)){
			// 保存收款银行信息
			BankAccount recba = new BankAccount();
			if (ObjectHelper.isNotEmpty(bankReAccount.getId())) {
				recba = this.findOne(bankReAccount.getId());
			}
			BeanUtils.copyProperties(bankReAccount, recba,new String[] { "id" });
			recba.setCaseApplyId(receivableInfo.getCaseApplyId());
			recba.setAccountType(0);
			this.saveOrUpdateEntity(recba);
		}
		
		if(ObjectHelper.isNotEmpty(bankLoanAccount)){
		// 保存放款银行信息
		BankAccount loanba =new BankAccount();
			if (ObjectHelper.isNotEmpty(bankLoanAccount.getId())) {
				loanba = this.findOne(bankLoanAccount.getId());
			}
			BeanUtils.copyProperties(bankLoanAccount, loanba,new String[] { "id" });
			loanba.setAccountType(1);
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
		
		CaseApply caseApply = caseApplyService.findOne(po.getCaseApplyId());
		if(ObjectHelper.isNotEmpty(receivablePlan) && receivablePlan.size()>0){
			//查询详细还款计划
			List<ReceivablePlan> lilst = po.getReceivablePlan();
			po.setReceivablePlan(null);
			//删除旧数据
			receivableRepository.delete(lilst);
			//保存新数据
			po.setReceivablePlan(receivablePlan);
		}
		
		po = receivablePlanInfoService.saveOrUpdateEntity(po);
		//保存关系
		caseApply.setReceivableInfo(po);
		return po;
	}

}
