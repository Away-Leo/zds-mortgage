package com.zdsoft.finance.casemanage.receivablePlan.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.dto.permission.DataOperPermDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlan;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlanForm;
import com.zdsoft.finance.casemanage.receivablePlan.entity.RepayPlan;
import com.zdsoft.finance.casemanage.receivablePlan.repository.ReceivablePlanRepository;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivablePlanService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.BigDecimalCalculateTwo;
import com.zdsoft.finance.common.utils.EnumTimeUnit;
import com.zdsoft.finance.common.utils.TimeUtil;
import com.zdsoft.finance.finance.entity.RepaymentAmountAllot;
import com.zdsoft.finance.finance.service.RepaymentAmountAllotService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.repository.CaseApplyRepository;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.spi.receivablePlan.CaseReceivableDto;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ReceivablePlanServiceImpl.java 
 * @ClassName: ReceivablePlanServiceImpl 
 * @Description: 还款计划接口实现
 * @author jincheng 
 * @date 2017年2月17日 下午1:55:45 
 * @version V1.0
 */
@Service("receivablePlanService")
public class ReceivablePlanServiceImpl extends BaseServiceImpl<ReceivablePlan, ReceivablePlanRepository>implements ReceivablePlanService {

	@Autowired
	private ReceivablePlanServiceCalc repayPlanServiceCalc;
	
	@Autowired
	private CaseApplyService caseApplyService;
	
	@Autowired
	private RepaymentAmountAllotService  repaymentAmountAllotService;
	
	@Autowired
	private ReceivablePlanRepository receivablePlanRepository;
	
	@Autowired
	private CaseApplyRepository caseApplyRepository;
	
	@Override
	public ReceivablePlan queryByCaseApplyIdAndPlanDate(String caseApplyId, Long planDate) {
		return receivablePlanRepository.queryByCaseApplyIdAndPlanDate(caseApplyId, planDate);
	}
	
	@Override
	public List<RepayPlan> receivablePlanGuarate(ReceivablePlanForm planForm) {
		return repayPlanServiceCalc.getRepayPlanVoList(planForm);
	}

	@Override
	public List<ReceivablePlan> findReceivablePlanByCaseApplyId(String caseApplyId) {
		return this.customReposity.findReceivablePlanByCaseApplyIdOrderByCreateTimeAsc(caseApplyId);
	}


	@Override
	public List<ReceivablePlan> findReceivablePlanByLoanApplyId(String loanApplyId) {
		return this.customReposity.findReceivablePlanByLoanApplyIdOrderByCreateTimeAsc(loanApplyId);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void saveReceivablePlan(String caseApplyId, String loanApplyId, List<ReceivablePlan> planList)throws BusinessException {
		for(ReceivablePlan plan:planList){
			this.saveEntity(plan);
		}
	}

	@Override
	public List<ReceivablePlan> getReceivablePlanList(String caseApplyId, Long repayDate) {
		if(ObjectHelper.isEmpty(repayDate)){//如果不传入还款日期，默认为系统当前日期
			repayDate=TimeUtil.getCurrentDateInteger().longValue();
		}
		return customReposity.findReceivablePlanByCaseApplyIdAndPlanRepayDate(caseApplyId,repayDate);
	}

	@Override
	public List<CaseReceivableDto> getCaseReceivableList(String caseApplyId, Long repayDate, boolean isAll) {
		List<CaseReceivableDto> dtoList=new ArrayList<CaseReceivableDto>();
		if(ObjectHelper.isEmpty(repayDate)){//如果不传入还款日期，默认为系统当前日期
			repayDate=TimeUtil.getCurrentDateInteger().longValue();
		}
		
		List<ReceivablePlan> planList=null;
		if(ObjectHelper.isNotEmpty(caseApplyId)){
			try {
				CaseApply apply=caseApplyService.findOne(caseApplyId);
				if(isAll){
					planList=this.customReposity.findReceivablePlanByCaseApplyIdOrderByCreateTimeAsc(caseApplyId);
				}else{
					planList=this.customReposity.findReceivablePlanByCaseApplyIdAndPlanRepayDate(caseApplyId,repayDate);
				}
				
				for(ReceivablePlan plan:planList){
					CaseReceivableDto dto=new CaseReceivableDto();
					dto.setRepayMethod(apply.getRepayMethod());//还款方式
					dto.setCaseApplyBalance(apply.getCaseApplyBalance());//案件剩余本金
					dto.setRemainPrincipal(plan.getRemainPrincipal());//每期剩余本金
					dto.setOverdueRate(apply.getOverdueRate());//逾期利率
					dto.setOverdueRateUnit(apply.getOverdueRateUnit());//逾期利率单位
					dto.setPlanId(plan.getId());
					dto.setSettlement(plan.getSettlement());
					List<RepaymentAmountAllot> allotList=repaymentAmountAllotService.findRepaymentAmountAllotByPlanId(plan.getId());//获取还款成功的分配
					if(ObjectHelper.isNotEmpty(allotList)){
						RepaymentAmountAllot c_allot=allotList.get(0);
						BeanUtils.copyProperties(c_allot,dto);
						dto.setStartDate(plan.getStartDate());
						dto.setEndDate(plan.getEndDate());
						
						 BigDecimal currentReductionPenalty = BigDecimal.ZERO;//减免当期罚息
						 BigDecimal reductionPenalty = BigDecimal.ZERO;//减免罚息
						 BigDecimal reductionDamages = BigDecimal.ZERO;//减免违约金
						 BigDecimal paidTotalPrincipalAmount = BigDecimal.ZERO;//实付本金
						 BigDecimal paidTotalInterestAmount = BigDecimal.ZERO;//实付利息
						 BigDecimal paidTotalServiceFee = BigDecimal.ZERO;//实付服务费
						 BigDecimal paidTotalPenalty = BigDecimal.ZERO;//实付罚息
						 BigDecimal currentPaidTotalPenalty = BigDecimal.ZERO;//实付当前罚息
						 BigDecimal paidTotalDamages = BigDecimal.ZERO;//实付违约金
						 
						for(RepaymentAmountAllot allot:allotList){//统计
							if(2==allot.getPaidType()){//减免
								currentReductionPenalty=BigDecimalCalculateTwo.add(currentReductionPenalty, allot.getCurrentPaidPenalty());
								reductionPenalty=BigDecimalCalculateTwo.add(reductionPenalty, allot.getPaidPenalty());
								reductionDamages=BigDecimalCalculateTwo.add(reductionDamages, allot.getPaidDamages());
							}else{//实付
								paidTotalPrincipalAmount=BigDecimalCalculateTwo.add(paidTotalPrincipalAmount, allot.getPaidPrincipalAmount());
								paidTotalInterestAmount=BigDecimalCalculateTwo.add(paidTotalInterestAmount, allot.getPaidInterestAmount());
								paidTotalServiceFee=BigDecimalCalculateTwo.add(paidTotalServiceFee, allot.getPaidServiceFee());
								paidTotalPenalty=BigDecimalCalculateTwo.add(paidTotalPenalty, allot.getPaidPenalty());
								currentPaidTotalPenalty=BigDecimalCalculateTwo.add(currentPaidTotalPenalty, allot.getCurrentPaidPenalty());
								paidTotalDamages=BigDecimalCalculateTwo.add(paidTotalDamages, allot.getPaidDamages());
							}
							if(c_allot.getPaidPrincipalAmount().compareTo(c_allot.getDplanPrincipalAmount())>=0&&c_allot.getPaidInterestAmount().compareTo(c_allot.getDplanInterestAmount())>=0){
								dto.setPiSettlementDate(c_allot.getPaidRepayDate());
							}
						}
						
						dto.setDplanPrincipalAmount(c_allot.getDplanPrincipalAmount().subtract(c_allot.getPaidPrincipalAmount()));
						dto.setDplanInterestAmount(c_allot.getDplanInterestAmount().subtract(c_allot.getPaidInterestAmount()));
						dto.setDplanDamages(c_allot.getDplanDamages().subtract(c_allot.getPaidDamages()));
						dto.setDplanServiceFee(c_allot.getDplanServiceFee().subtract(c_allot.getPaidServiceFee()));
						dto.setDplanPenalty(c_allot.getPaidPenalty().compareTo(c_allot.getDplanPenalty())>0?BigDecimal.ZERO:c_allot.getDplanPenalty().subtract(c_allot.getPaidPenalty()));
						dto.setDcurrentPlanPenalty(c_allot.getCurrentPaidPenalty().compareTo(c_allot.getDcurrentPlanPenalty())>0?BigDecimal.ZERO:c_allot.getDcurrentPlanPenalty().subtract(c_allot.getCurrentPaidPenalty()));
						
						dto.setCurrentReductionPenalty(currentReductionPenalty);
						dto.setReductionDamages(reductionDamages);
						dto.setReductionPenalty(reductionPenalty);
						
						dto.setPaidTotalPrincipalAmount(paidTotalPrincipalAmount);
						dto.setPaidTotalInterestAmount(paidTotalInterestAmount);
						dto.setPaidTotalPenalty(paidTotalPenalty);
						dto.setCurrentPaidTotalPenalty(currentPaidTotalPenalty);
						dto.setPaidTotalServiceFee(paidTotalServiceFee);
						dto.setPaidTotalDamages(paidTotalDamages);
					}else{
						BeanUtils.copyProperties(plan,dto);
						dto.setDplanPrincipalAmount(plan.getPlanPrincipalAmount());
						dto.setDplanInterestAmount(plan.getPlanInterestAmount());
						dto.setDplanServiceFee(plan.getPlanServiceFee());
					}
					
					if(!plan.getSettlement()&&plan.getPlanRepayDate()<repayDate){//计算罚息(当前计划未结清并还款日小于当前传入的还款日)
						dto=this.calcEachPeriodPeantly(dto,repayDate);
					}
					dtoList.add(dto);
				}
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
		return dtoList;
	}

	/**
	 * @Title: calcEachPeriodPeantly 
	 * @Description: 计算每期还款计划的罚息
	 * @author jincheng 
	 * @param dto
	 * @param repayDate
	 * @return
	 */
	private CaseReceivableDto calcEachPeriodPeantly(CaseReceivableDto dto, Long repayDate) {
		//	1、一次性还本：当期本金罚息=剩余本金罚息
		//	2、本金分期还款：当期罚息=当期本金*千2*天，剩余本金罚息=剩余本金*千2*天
		
		//获取日罚息率
		BigDecimal  dayInterest=this.getDayInterestPenalty(dto.getOverdueRate(),dto.getOverdueRateUnit());
				
		//判断是否一次性还本
		boolean isOneRepayPrincipal=this.judgeIsOneRepayPrincipal(dto.getRepayMethod());
		
		if(isOneRepayPrincipal){//本金分期还款
			BigDecimal penalty=BigDecimal.ZERO;
			if(dto.getDplanPrincipalAmount().compareTo(BigDecimal.ZERO)>0&&repayDate>dto.getPlanRepayDate()){
				int days=TimeUtil.compareDate(""+dto.getPlanRepayDate(), ""+repayDate, EnumTimeUnit.Day);
				penalty=BigDecimalCalculateTwo.mul(BigDecimalCalculateTwo.mul(dto.getCaseApplyBalance(), dayInterest),BigDecimal.valueOf(days)).setScale(2, BigDecimal.ROUND_HALF_UP);
				dto.setDays(days);
			}
			dto.setPlanPenalty(dto.getPlanPenalty().add(penalty));
			dto.setCurrentPlanPenalty(dto.getCurrentPlanPenalty().add(penalty));
			dto.setDplanPenalty(dto.getDplanPenalty().add(penalty));
			dto.setDcurrentPlanPenalty(dto.getDcurrentPlanPenalty().add(penalty));
		}else{
			BigDecimal penalty=BigDecimal.ZERO;//罚息
			BigDecimal currentPenalty=BigDecimal.ZERO;//当期罚息
			if(dto.getDplanPrincipalAmount().compareTo(BigDecimal.ZERO)>0&&repayDate>dto.getPlanRepayDate()){
				int days=TimeUtil.compareDate(""+dto.getPlanRepayDate(), ""+repayDate, EnumTimeUnit.Day);
				currentPenalty=BigDecimalCalculateTwo.mul(BigDecimalCalculateTwo.mul(dto.getDplanPrincipalAmount(), dayInterest),BigDecimal.valueOf(days)).setScale(2, BigDecimal.ROUND_HALF_UP);
				penalty=BigDecimalCalculateTwo.mul(BigDecimalCalculateTwo.mul(dto.getCaseApplyBalance(), dayInterest),BigDecimal.valueOf(days)).setScale(2, BigDecimal.ROUND_HALF_UP);
				dto.setDays(days);
			}
			dto.setPlanPenalty(dto.getPlanPenalty().add(penalty));
			dto.setCurrentPlanPenalty(dto.getCurrentPlanPenalty().add(currentPenalty));
			dto.setDplanPenalty(dto.getDplanPenalty().add(penalty));
			dto.setDcurrentPlanPenalty(dto.getDcurrentPlanPenalty().add(currentPenalty));
		}
		return dto;
	}

	
	/**
	 * @Title: judgeIsOneRepayPrincipal 
	 * @Description: 判断是否一次性还本
	 * @author jincheng 
	 * @param repayMethod
	 * @return
	 */
	private boolean judgeIsOneRepayPrincipal(String repayMethod) {
		boolean bool=false;
		if("YWDM0051005".equals(repayMethod)||"YWDM0051002".equals(repayMethod)||"YWDM0051001".equals(repayMethod)||"YWDM0051003".equals(repayMethod)||"YWDM0051004".equals(repayMethod)){
			bool=true;
		}
		return bool;
	}

	/**
	 * @Title: getDayInterestPenalty 
	 * @Description: 获取日罚息率
	 * @author jincheng 
	 * @param overdueRate
	 * @param overdueRateUnit
	 * @return
	 */
	private BigDecimal getDayInterestPenalty(BigDecimal overdueRate, String overdueRateUnit) {
		BigDecimal  dayInterest=BigDecimal.valueOf(0.002);//日罚息率(默认千分之二)
		if(ObjectHelper.isEmpty(overdueRate)||ObjectHelper.isEmpty(overdueRateUnit)){
			return dayInterest;
		}
		if("YWDM0011901".equals(overdueRateUnit)){//年
			dayInterest=BigDecimalCalculateTwo.div(overdueRate, BigDecimal.valueOf(360));
		}else if("YWDM0011902".equals(overdueRateUnit)){//月
			dayInterest=BigDecimalCalculateTwo.div(overdueRate, BigDecimal.valueOf(30));
		}else{//日
			dayInterest=overdueRate;
		}
		return dayInterest;
	}

	@Override
	public List<ReceivablePlan> findByCaseApplyId(String caseApplyId) {
		if(ObjectHelper.isEmpty(caseApplyId)){
			return null;
		}
		return receivablePlanRepository.findByCaseApplyIdAndIsDeleted(caseApplyId,false);
	}

	@Override
	public Page<Map<String, Object>> findPageReceivablePlan(Pageable pageable, List<QueryObj> queryObjs,String riskStatusName,DataOperPermDto dtOperPermDto)	throws Exception {
	    StringBuffer dataAuth = this.developmentManagerDataAuth(dtOperPermDto, "c");
		return caseApplyRepository.findPageReceivablePlan(pageable, queryObjs,riskStatusName,dataAuth);
	}

	@Override
	@Transactional
	public void delete(String id) {
		if(ObjectHelper.isNotEmpty(id)){
			receivablePlanRepository.delete(id);
		}
	}
}
