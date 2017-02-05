package com.zdsoft.finance.casemanage.receivablePlan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivableInfo;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlanForm;
import com.zdsoft.finance.casemanage.receivablePlan.entity.RepayPlan;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivablePlanInfoService;
import com.zdsoft.finance.common.base.CustomRepository;

@Service
public class ReceivablePlanInfoServiceImpl extends BaseServiceImpl<ReceivableInfo, CustomRepository<ReceivableInfo,String>>
		implements ReceivablePlanInfoService {

	@Autowired
	private ReceivablePlanServiceCalc repayPlanServiceCalc;
	
	@Override
	public List<RepayPlan> receivablePlanGuarate(ReceivablePlanForm planForm) {
		return repayPlanServiceCalc.getRepayPlanVoList(planForm);
	}
	
}
