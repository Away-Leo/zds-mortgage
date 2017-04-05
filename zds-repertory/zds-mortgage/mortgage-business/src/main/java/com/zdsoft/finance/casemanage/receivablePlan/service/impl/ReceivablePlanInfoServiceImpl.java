package com.zdsoft.finance.casemanage.receivablePlan.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.receivablePlan.IrrUtil;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivableInfo;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlanForm;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlanTemp;
import com.zdsoft.finance.casemanage.receivablePlan.entity.RepayPlan;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivablePlanInfoService;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivablePlanService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.BigDecimalCalculateTwo;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;

@Service
public class ReceivablePlanInfoServiceImpl extends BaseServiceImpl<ReceivableInfo, CustomRepository<ReceivableInfo,String>>
		implements ReceivablePlanInfoService {

	@Autowired
	private ReceivablePlanService receivablePlanService;
	@Autowired
	private CaseApplyService caseApplyService;
	
	@Override
	public List<ReceivablePlanTemp> receivablePlanGenerate(ReceivableInfo receivableInfo) throws BusinessException {
		List<ReceivablePlanTemp> plans = new ArrayList<ReceivablePlanTemp>();
		ReceivablePlanForm planForm = new ReceivablePlanForm();
		//将值注入ReceivablePlanForm类中  算出还款计划
		intoReceivablePlanFormVal(receivableInfo,planForm);
		List<RepayPlan> planList=receivablePlanService.receivablePlanGuarate(planForm);
		//将生成还款计划数据写入表中
		for (RepayPlan plan:planList) {
			ReceivablePlanTemp plan2 = new ReceivablePlanTemp();
			plan2.setPeriods(plan.getPeriods());
			plan2.setPlanRepayDate(plan.getPlanRepayDate());
			plan2.setStartDate(plan.getStartDate());
			plan2.setEndDate(plan.getEndDate());
			plan2.setPlanPrincipalAmount(plan.getPlanPrincipalAmount());
			plan2.setPlanInterestAmount(plan.getPlanInterestAmount());
			plan2.setPlanServiceFee(plan.getPlanServiceFee());
			plan2.setRemainPrincipal(plan.getRemainPrincipal());
			plans.add(plan2);
		}
		return plans;
	}

	private void intoReceivablePlanFormVal(ReceivableInfo receivableInfo, ReceivablePlanForm planForm) throws BusinessException {
		CaseApply apply = caseApplyService.findOne(receivableInfo.getCaseApplyId());
		planForm.setPrincipalAmount(apply.getApplyAmount());
		planForm.setRepaymentDate(receivableInfo.getRepaymentDate());
		planForm.setLoanDate(receivableInfo.getExpectLoanDate());
		planForm.setRateNature(receivableInfo.getRateNature());
		planForm.setRepayMethod(receivableInfo.getRepaymentType());
		planForm.setBusinessType("1");//暂时使用
//		planForm.setBusinessType(apply.getProductSubtypeId());
		Integer term;
		if(CaseApply.DATEUNIT_YEAR.equals(apply.getApplyTermUnit())){
			term = 12*apply.getApplyTerm();
		}else if(CaseApply.DATEUNIT_MONTH.equals(apply.getApplyTermUnit())){
			term = apply.getApplyTerm();
		}else{
			throw new BusinessException("暂不支持日算法");
		}
		planForm.setTerm(term);
		BigDecimal rate = BigDecimal.ZERO;
		if(ReceivableInfo.RECEIVABLEINFO_YEAR.equals(receivableInfo.getLoanMonthRateUnit())){
			rate = receivableInfo.getLoanMonthRate();
		}else if(ReceivableInfo.RECEIVABLEINFO_MONTH.equals(receivableInfo.getLoanMonthRateUnit())){
			rate = BigDecimalCalculateTwo.mul(receivableInfo.getLoanMonthRate(), BigDecimal.valueOf(12));
		}else{
			rate = BigDecimalCalculateTwo.mul(receivableInfo.getLoanMonthRate(), BigDecimal.valueOf(36.5));//日利息是千分号
		}
		planForm.setRate(rate);
	}

	@Override
	public Map<String, Object> calculateOtherRate(ReceivableInfo info) throws BusinessException {
		Map<String, Object> map = new HashMap<String, Object>();
		CaseApply apply = caseApplyService.findOne(info.getCaseApplyId());
		Integer term;
		if(CaseApply.DATEUNIT_YEAR.equals(apply.getApplyTermUnit())){
			term = 12*apply.getApplyTerm();
		}else if(CaseApply.DATEUNIT_MONTH.equals(apply.getApplyTermUnit())){
			term = apply.getApplyTerm();
		}else{
			throw new BusinessException("暂不支持日算法");
		}
		BigDecimal rate = BigDecimal.ZERO;
		if(ReceivableInfo.RECEIVABLEINFO_YEAR.equals(info.getLoanMonthRateUnit())){
			rate = info.getLoanMonthRate();
		}else if(ReceivableInfo.RECEIVABLEINFO_MONTH.equals(info.getLoanMonthRateUnit())){
			rate = BigDecimalCalculateTwo.mul(info.getLoanMonthRate(), BigDecimal.valueOf(12));
		}else{
			rate = BigDecimalCalculateTwo.mul(info.getLoanMonthRate(), BigDecimal.valueOf(36.5));//日利息是千分号
		}
		BigDecimal totalInterestAmount=BigDecimalCalculateTwo.div(BigDecimalCalculateTwo.mul(BigDecimalCalculateTwo.mul(apply.getApplyAmount(),rate),BigDecimalCalculateTwo.div(BigDecimal.valueOf(term),BigDecimal.valueOf(12))),BigDecimal.valueOf(100));
		BigDecimal eachTotalAmount=BigDecimalCalculateTwo.div(BigDecimalCalculateTwo.add(apply.getApplyAmount(), totalInterestAmount), BigDecimal.valueOf(term)).setScale(2, BigDecimal.ROUND_HALF_UP);//每期还款合计
		List<Double> flowInArr=new ArrayList<Double>();  
	        flowInArr.add(BigDecimal.ZERO.subtract(apply.getApplyAmount()).doubleValue());
	        for(int i=0;i<term;i++){
	        	 flowInArr.add(eachTotalAmount.doubleValue());
	        }
	    BigDecimal baseRate=IrrUtil.getIrr(flowInArr);//实际月利率
	    map.put("internalRateReturn", BigDecimalCalculateTwo.mul(baseRate, BigDecimal.valueOf(1200)).setScale(2, BigDecimal.ROUND_HALF_UP));
	    map.put("syntheticalRate", rate);
		return map;
	}

	
}
