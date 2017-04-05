package com.zdsoft.finance.loan.service.process;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.bpm.annotation.client.AutoJob;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlan;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlanForm;
import com.zdsoft.finance.casemanage.receivablePlan.entity.RepayPlan;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivablePlanService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.RateConvertUtil;
import com.zdsoft.finance.common.utils.TimeUtil;
import com.zdsoft.finance.loan.entity.LoanApply;
import com.zdsoft.finance.loan.entity.LoanRecord;
import com.zdsoft.finance.loan.service.LoanApplySerivce;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.dto.DelegateExecution;
import com.zdsoft.framework.core.common.service.JavaDelegate;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AutoCreateLoanPlan.java 
 * @ClassName: AutoCreateLoanPlan 
 * @Description: 自动生成还款计划
 * @author gufeng 
 * @date 2017年3月8日 下午3:49:39 
 * @version V1.0
 */
@Service
@AutoJob(label = "自动生成还款计划", resource = "com.zdsoft.finance.loan.service.process.autoCreateLoanPlan")
@Lazy(false)
public class AutoCreateLoanPlan implements JavaDelegate{

	@Log
	private Logger logger;
	
	@Autowired
	private ReceivablePlanService receivablePlanService;
	
	@Autowired
	private LoanApplySerivce loanApplySerivce;
	
	@Autowired
	private CaseApplyService caseApplyService;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void execute(DelegateExecution de) throws Exception {
		String busiId = (String)de.getVariable("businessKey");
		if(ObjectHelper.isEmpty(busiId)){
			logger.info("10000000001","=LoanProcessShunt====流程businessKey不存在");
			throw new BusinessException("10000000001", "=AutoCreateLoanPlan====流程businessKey不存在");
		}
		LoanApply apply = loanApplySerivce.findOne(busiId);
		if(ObjectHelper.isEmpty(apply)){
			logger.info("10000000002","=LoanProcessShunt====还款申请不存在");
			throw new BusinessException("10000000002","=AutoCreateLoanPlan====还款申请不存在");
		}
		if(ObjectHelper.isEmpty(apply.getCaseApplyId())){
			logger.info("10000000003","=caseApplyId====不存在");
			throw new BusinessException("10000000003","=caseApplyId====不存在");
		}
		CaseApply ca = caseApplyService.findOne(apply.getCaseApplyId());
		if(ObjectHelper.isEmpty(ca) || ObjectHelper.isEmpty(ca.getApplyRate())){
			logger.info("10000000004","=CaseApply====或利率，不存在");
			throw new BusinessException("10000000004","=CaseApply====或利率，不存在");
		}
		//还款计划生成
		ReceivablePlanForm form = new ReceivablePlanForm();
		Set<LoanRecord> loanSet=apply.getLoanRecords();
		form.setLoanDate(TimeUtil.getCurrentDateInteger().longValue());//放款日期
		form.setPrincipalAmount(apply.getLoanAmount());//贷款金额
		for(LoanRecord loan:loanSet){
			form.setLoanDate(loan.getActualDate());//放款日期
			form.setPrincipalAmount(loan.getLoanAmount());//贷款金额
		}
		
		Double rate = RateConvertUtil.convertToYFeeRate(ca.getApplyRate().doubleValue(), ca.getApplyRateUnit());
		form.setRate(new BigDecimal(rate));//年利率
		form.setTerm(this.convertToMTerm(ca.getApplyTerm(), ca.getApplyTermUnit()));//期限
		form.setRepayMethod(ca.getRepayMethod());//还款方式
		List<RepayPlan> planList=receivablePlanService.receivablePlanGuarate(form);
		//保存还款计划
		for (RepayPlan repayPlan : planList) {
			ReceivablePlan plan = new ReceivablePlan();
			plan.setPeriods(repayPlan.getPeriods()); //期数
			plan.setPlanRepayDate(repayPlan.getPlanRepayDate()); //当前还款日期
			plan.setStartDate(repayPlan.getStartDate()); //当前开始日期
			plan.setEndDate(repayPlan.getEndDate()); //当前结束日期
			plan.setPlanPrincipalAmount(repayPlan.getPlanPrincipalAmount()); //当期本金
			plan.setPlanInterestAmount(repayPlan.getPlanInterestAmount()); //当期利息
			plan.setPlanServiceFee(repayPlan.getPlanServiceFee()); //当期服务费
			plan.setRemainPrincipal(repayPlan.getRemainPrincipal()); //当期剩余本金
			plan.setCaseApplyId(ca.getId()); //案件id
			plan.setLoanApplyId(apply.getId()); //放款ID
			plan.setOrgId(repayPlan.getOrgId()); //所属机构
			receivablePlanService.saveEntity(plan);
		}
	}
	
	/**
	 * @Title: convertToMTerm 
	 * @Description: 期限转换
	 * @author gufeng 
	 * @param term 期限
	 * @param util 期限单位
	 * @return 月期限
	 */
	private Integer convertToMTerm(Integer term,String util){
		if(ObjectHelper.isEmpty(term)){
			return 0;
		}
		switch (util) {
		case "0931001":
			term *= 12;
			break;
		case "0931003":
			term /= 30;
			break;
		default:
			break;
		}
		return term;
	}

}
