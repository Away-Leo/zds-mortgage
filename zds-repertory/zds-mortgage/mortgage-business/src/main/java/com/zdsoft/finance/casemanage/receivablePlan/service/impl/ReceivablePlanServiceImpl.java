package com.zdsoft.finance.casemanage.receivablePlan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivableInfo;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlan;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlanForm;
import com.zdsoft.finance.casemanage.receivablePlan.entity.RepayPlan;
import com.zdsoft.finance.casemanage.receivablePlan.repository.ReceivableRepository;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivablePlanInfoService;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivablePlanService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.util.ObjectHelper;

@Service
public class ReceivablePlanServiceImpl extends BaseServiceImpl<ReceivablePlan, CustomRepository<ReceivablePlan, String>>
		implements ReceivablePlanService {

	@Autowired
	private ReceivablePlanServiceCalc repayPlanServiceCalc;

	@Autowired
	private ReceivableRepository receivableRepository;
	
	@Autowired
	private ReceivablePlanInfoService receivablePlanInfoService;

	@Override
	public List<RepayPlan> receivablePlanGuarate(ReceivablePlanForm planForm) {
		return repayPlanServiceCalc.getRepayPlanVoList(planForm);
	}

	@Override
	@Transactional
	public boolean saveReceivablePlan(String receivableInfoId, String loanApplyId, List<ReceivablePlan> list)
			throws BusinessException {
		ReceivableInfo info = receivablePlanInfoService.findOne(receivableInfoId);
		if (ObjectHelper.isNotEmpty(receivableInfoId)) {
			//删除案件还款计划
			receivableRepository.delete(info.getReceivablePlan());
			//保存案件还款计划
			info.setReceivablePlan(list);
		}else if(ObjectHelper.isNotEmpty(loanApplyId)){
			//保存放款还款计划
		}
		receivablePlanInfoService.saveOrUpdateEntity(info);
		return true;
	}

}
