package com.zdsoft.finance.finance.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivablePlanService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.BigDecimalCalculateTwo;
import com.zdsoft.finance.common.utils.TimeUtil;
import com.zdsoft.finance.customer.entity.BeforeCustomer;
import com.zdsoft.finance.finance.entity.CaseReceivableDay;
import com.zdsoft.finance.finance.entity.CustomerReceivable;
import com.zdsoft.finance.finance.repository.CustomerReceivableRepostory;
import com.zdsoft.finance.finance.service.CaseReceivableDayService;
import com.zdsoft.finance.finance.service.CustomerReceivableService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.finance.spi.receivablePlan.CaseReceivableDto;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CustomerReceivableServiceImpl.java 
 * @ClassName: CustomerReceivableServiceImpl 
 * @Description: 客户应还接口实现
 * @author jincheng 
 * @date 2017年2月16日 下午5:06:29 
 * @version V1.0
 */
@Service("customerReceivableService")
public class CustomerReceivableServiceImpl extends BaseServiceImpl<CustomerReceivable, CustomerReceivableRepostory>  implements CustomerReceivableService{
	
	@Autowired
	private ReceivablePlanService receivablePlanService;
	
	@Autowired
	private CaseReceivableDayService  caseReceivableDayService;
	
	@Autowired
	private CaseApplyBeforeCustomerService caseApplyBeforeCustomerService;
	
	@Override 
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public CustomerReceivable saveOrUpdateCustomerReceivable(CustomerReceivable entity) throws Exception {
		return this.saveEntity(entity);
	}

	@Override
	public List<CustomerReceivable> findByCaseApplyId(String caseApplyId) {
		return this.customReposity.findByCaseApplyId(caseApplyId);
	}

	@Override
	public CustomerReceivable findByCaseApplyIdAndIsEffect(String caseApplyId, boolean isEffect) {
		return this.customReposity.findByCaseApplyIdAndIsEffect(caseApplyId,isEffect);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=BusinessException.class)
	public void caseApplyBatch(List<CaseApply> applyList, Long batchDay) throws BusinessException {
		for(CaseApply apply:applyList){
			
			List<CaseReceivableDto> planList=receivablePlanService.getCaseReceivableList(apply.getId(), TimeUtil.getCurrentDateInteger().longValue(), true);
			BigDecimal planPrincipalAmount=BigDecimal.ZERO;//本金
			BigDecimal planInterestAmount=BigDecimal.ZERO;//利息
			BigDecimal planServiceFee=BigDecimal.ZERO;//服务费
			BigDecimal planPenalty=BigDecimal.ZERO;//罚息
			BigDecimal currentPlanPenalty=BigDecimal.ZERO;//当期罚息
			BigDecimal planDamages=BigDecimal.ZERO;//违约金
			BigDecimal remainPrincipal = BigDecimal.ZERO;//当期剩余本金
			
			BigDecimal paidPrincipalAmount=BigDecimal.ZERO;//还本金
			BigDecimal paidInterestAmount=BigDecimal.ZERO;//还利息
			BigDecimal paidServiceFee=BigDecimal.ZERO;//还服务费
			BigDecimal paidPenalty=BigDecimal.ZERO;//还罚息
			BigDecimal currentPaidPenalty=BigDecimal.ZERO;//还当期罚息
			BigDecimal paidDamages=BigDecimal.ZERO;//还违约金
			
			BigDecimal loanAnount=BigDecimal.ZERO;//放款金额
			BigDecimal interestAmount=BigDecimal.ZERO;//利息总收入
			
			BigDecimal currentReductionPenalty = BigDecimal.ZERO;//减免当期罚息
			BigDecimal reductionPenalty = BigDecimal.ZERO;//减免罚息
			BigDecimal reductionDamages = BigDecimal.ZERO;//减免违约金
			
			Integer overDueTime=0;//逾期次数
			Integer days=0;//逾期天数
			Long  planRepayDate=0l;//最近还款日期
			Integer periods=1;
			for(CaseReceivableDto dto:planList){
				CaseReceivableDay day=new CaseReceivableDay();
				BeanUtils.copyProperties(dto, day,new String[]{"id"});
				day.setBatchDay(batchDay);
				if(dto.getStartDate()<=TimeUtil.getCurrentDateInteger().longValue()&&dto.getEndDate()>=TimeUtil.getCurrentDateInteger().longValue()){
					planRepayDate=dto.getPlanRepayDate();
					periods=dto.getPeriods();
					remainPrincipal=dto.getRemainPrincipal();
				}
				
				if(dto.getIsOverDue()){
					overDueTime++;
					if(dto.getDays()>days){
						days=dto.getDays();
					}
					day.setIsOverDue(true);
				}
				day.setCaseApplyId(apply.getId());
				caseReceivableDayService.saveEntity(day);
				planPrincipalAmount=BigDecimalCalculateTwo.add(planPrincipalAmount, dto.getDplanPrincipalAmount());
				planInterestAmount=BigDecimalCalculateTwo.add(planInterestAmount, dto.getDplanInterestAmount());
				planServiceFee=BigDecimalCalculateTwo.add(planServiceFee, dto.getDplanServiceFee());
				planPenalty=BigDecimalCalculateTwo.add(planPenalty, dto.getDplanPenalty());
				currentPlanPenalty=BigDecimalCalculateTwo.add(currentPlanPenalty, dto.getDcurrentPlanPenalty());
				planDamages=BigDecimalCalculateTwo.add(planDamages, dto.getDplanDamages());
				
				paidPrincipalAmount=BigDecimalCalculateTwo.add(paidPrincipalAmount, dto.getPaidPrincipalAmount());
				paidInterestAmount=BigDecimalCalculateTwo.add(paidInterestAmount, dto.getPaidInterestAmount());
				paidServiceFee=BigDecimalCalculateTwo.add(paidServiceFee, dto.getPaidServiceFee());
				paidPenalty=BigDecimalCalculateTwo.add(paidPenalty, dto.getPaidPenalty());
				currentPaidPenalty=BigDecimalCalculateTwo.add(currentPaidPenalty, dto.getCurrentPaidPenalty());
				paidDamages=BigDecimalCalculateTwo.add(paidDamages, dto.getPaidDamages());
				
				currentReductionPenalty=BigDecimalCalculateTwo.add(currentReductionPenalty, dto.getCurrentReductionPenalty());
				reductionPenalty=BigDecimalCalculateTwo.add(reductionPenalty, dto.getReductionPenalty());
				reductionDamages=BigDecimalCalculateTwo.add(reductionDamages, dto.getReductionDamages());
				
				loanAnount=BigDecimalCalculateTwo.add(loanAnount, dto.getPlanPrincipalAmount());
				interestAmount=BigDecimalCalculateTwo.add(interestAmount, dto.getPlanInterestAmount());
			}
			
			List<CustomerReceivable> crList=this.findByCaseApplyId(apply.getId());
			for(CustomerReceivable cr:crList){
				cr.setIsEffect(false);
				this.updateEntity(cr);
			}
			
			 CustomerReceivable cr=new CustomerReceivable();
			 cr.setBatchDay(batchDay);
			 cr.setPlanRepayDate(planRepayDate);
			 cr.setPeriods(periods);
			 
			 cr.setRemainPrincipal(remainPrincipal);
			 cr.setPlanPrincipalAmount(planPrincipalAmount);
			 cr.setPlanInterestAmount(planInterestAmount);
			 cr.setPlanServiceFee(planServiceFee);
			 cr.setPlanPenalty(planPenalty);
			 cr.setCurrentPlanPenalty(currentPlanPenalty);
			 cr.setPlanDamages(planDamages);
			 
			 cr.setPlanPrincipalAmount(paidPrincipalAmount);
			 cr.setPlanInterestAmount(paidInterestAmount);
			 cr.setPlanServiceFee(paidServiceFee);
			 cr.setPlanPenalty(paidPenalty);
			 cr.setCurrentPlanPenalty(currentPaidPenalty);
			 cr.setPlanDamages(paidDamages);
			 
			 cr.setCurrentReductionPenalty(currentReductionPenalty);
			 cr.setReductionPenalty(reductionPenalty);
			 cr.setReductionDamages(reductionDamages);
			 
			 cr.setLoanAnount(loanAnount);
			 cr.setInterestAmount(interestAmount);
			 
			//获取主借人
			List<BeforeCustomer> customerList=caseApplyBeforeCustomerService.findCustomerByCaseApplyIdAndJoinType(apply.getId(), CaseApplyBeforeCustomer.MAIN_BORROW);
				
			 if(ObjectHelper.isNotEmpty(customerList)){
				 cr.setCustomerId(customerList.get(0).getId());
				 cr.setCustomerName(customerList.get(0).getCustomerName());
			 }
			 
			 cr.setIsEffect(true);
			 cr.setCaseApplyId(apply.getId());
			 cr.setOvreDueTime(overDueTime);
			 cr.setDays(days);
			 this.saveEntity(cr);
		}
	}
}
