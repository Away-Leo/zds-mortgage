package com.zdsoft.finance.casemanage.receivablePlan.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Component;

import com.zdsoft.finance.casemanage.receivablePlan.IrrUtil;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlanForm;
import com.zdsoft.finance.casemanage.receivablePlan.entity.RepayPlan;
import com.zdsoft.finance.common.utils.BigDecimalCalculateTwo;
import com.zdsoft.finance.common.utils.EnumTimeUnit;
import com.zdsoft.finance.common.utils.TimeUtil;
import com.zdsoft.framework.core.common.util.ObjectHelper;

import common.Logger;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:RepayPlanServiceCalc.java
 * @Package:package com.zdsoft.finance.casemanage.receivablePlan.service.impl
 * @Description:还款计划生成实现
 * @author: jincheng
 * @date:2017-1-10 下午2:32:28
 * @version: V1.0
 */
@Component
public class ReceivablePlanServiceCalc {
	
    private Logger log=Logger.getLogger(ReceivablePlanServiceCalc.class);

    /**
     * 根据页面表单参数生成还款计划
     */
    public List<RepayPlan> getRepayPlanVoList(ReceivablePlanForm formVo) {
	        switch (formVo.getRepayMethod()) {
					case "YWDM0051007"://等额本息  --完成
						return this.calcAverageCapitalPlusInterest(formVo);
					case "YWDM0051006"://等额本金	--完成
						return this.calcAverageCapital(formVo);
					case "YWDM0051005":// 一次付息到期还本(过桥贷) --完成
						return this.calcInterestPaymentDue(formVo);
					case "YWDM0051002":// 逐月付息后一次性还本  --完成
						return this.calcInterestPaymentMonth(formVo);
					case "YWDM0051001"://逐季付息后一次性还本  --完成
						return this.calcInterestPaymentQuarter(formVo);
					case "YWDM0051003"://服务费 逐月付息 到期本息 --完成
						return this.calcServiceFeePaymentDuePrincipal(formVo);
					case "YWDM0051004"://服务费 逐月付息 到期还本 --完成
						return this.calcServiceFeePaymentPrincipal(formVo);
					case "8"://分段还款 --完成
						return this.calcSubsectionRepayment(formVo);
					case "9"://逐年递减等额本息
						return this.calcAverageCapitalPlusInterestDecreasingYear(formVo);
					default:
						break;
			}
	        return null;
    }

    /**
     * @Title: calcAverageCapitalPlusInterestDecreasingYear 
     * @Description: 逐年递减等额本息:第一年：15%，第二年：14%，第三年13%，第四年12%，第五年9.5%
     * @author jincheng 
     * @param formVo
     * @return
     */
	private List<RepayPlan> calcAverageCapitalPlusInterestDecreasingYear(ReceivablePlanForm formVo) {
		return null;
	}

	/**
	 * @Title: calcSubsectionRepayment 
	 * @Description: 分段还款:为先期为先息后本还款方式，只还利息，后期为等额本息算法。两种算法分开计算，然后拼接为还款计划表
	 * @author jincheng 
	 * @param formVo
	 * @return
	 */
	private List<RepayPlan> calcSubsectionRepayment(ReceivablePlanForm formVo) {
		log.info("分段还款");
		List<RepayPlan> planList=new  ArrayList<RepayPlan>();
		Integer periods=formVo.getTerm();//总期数
		Integer speriods=formVo.getSpTerm();//先息后本期数
		
		Long startDate=formVo.getLoanDate(),endDate=null,planRepayDate=null;
		Integer days=0;
		BigDecimal planPrincipalAmount=BigDecimal.ZERO,planInterestAmount=BigDecimal.ZERO,planServiceFee=BigDecimal.ZERO,remainPrincipal=formVo.getPrincipalAmount();
		
		for(Integer i=0;i<speriods;i++){
			if(i>0){
				startDate=endDate;
			}
			endDate=this.calcEndDate(startDate,formVo.getRepaymentDate(),formVo.getRepayMethod());
			days=TimeUtil.compareDate(startDate+"", endDate+"", EnumTimeUnit.Day);//当期天数
			planRepayDate=endDate;//当期还款日
			planInterestAmount=BigDecimalCalculateTwo.div(BigDecimalCalculateTwo.mul(formVo.getPrincipalAmount(), formVo.getSpRate()),BigDecimal.valueOf(100d)).setScale(formVo.getDecimalPlace(), BigDecimal.ROUND_HALF_UP);//当期利息
			RepayPlan plan=new RepayPlan(startDate,endDate,planRepayDate,days,i+1,planPrincipalAmount,planInterestAmount,planServiceFee,remainPrincipal,null);
			planList.add(plan);
		}
		formVo.setLoanDate(endDate);
		formVo.setTerm(periods-speriods);
		planList.addAll(this.calcAverageCapitalPlusInterest(formVo));
		int j=0;//重新排序期数
		for(RepayPlan plan:planList){
			plan.setPeriods(j+1);
			j++;
		}
		return planList;
	}

	/**
     * @Title: calcServiceFeePaymentPrincipal 
     * @Description: 服务费 逐月付息 到期还本
     * @author jincheng 
     * @param formVo
     * @return
     */
    private List<RepayPlan> calcServiceFeePaymentPrincipal(ReceivablePlanForm formVo) {
    	log.info(" 服务费 逐月付息 到期还本：比总期数多一期");
		List<RepayPlan> planList=new  ArrayList<RepayPlan>();
		Integer periods=formVo.getTerm();//期数
		Long startDate=formVo.getLoanDate(),endDate=null,planRepayDate=null;
		Integer days=0;
		BigDecimal planPrincipalAmount=BigDecimal.ZERO,planInterestAmount=BigDecimal.ZERO,planServiceFee=BigDecimal.ZERO,remainPrincipal=formVo.getPrincipalAmount();
		for(Integer i=0;i<periods;i++){
			if(i>0){
				startDate=endDate;
			}
			if(i==0){//第一期
				endDate=startDate;
				days=0;//当期天数
				planRepayDate=endDate;//当期还款日
			}else{
				endDate=this.calcEndDate(startDate,formVo.getRepaymentDate(),formVo.getRepayMethod());
				days=TimeUtil.compareDate(startDate+"", endDate+"", EnumTimeUnit.Day);//当期天数
				planRepayDate=endDate;//当期还款日
			}
			planInterestAmount=BigDecimalCalculateTwo.div(BigDecimalCalculateTwo.mul(remainPrincipal, formVo.getRate()),BigDecimal.valueOf(1200d)).setScale(formVo.getDecimalPlace(), BigDecimal.ROUND_HALF_UP);//当期利息
			RepayPlan plan=new RepayPlan(startDate,endDate,planRepayDate,days,i+1,planPrincipalAmount,planInterestAmount,planServiceFee,remainPrincipal,null);
			planList.add(plan);
		}
		endDate=Long.valueOf(TimeUtil.addDate(""+formVo.getLoanDate(), EnumTimeUnit.Month, periods));
		RepayPlan plan=new RepayPlan(startDate,endDate,endDate,days,periods+1,remainPrincipal,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,null);
		planList.add(plan);
		return planList;
	}

	/**
     * @Title: calcServiceFeePaymentDuePrincipal 
     * @Description: 服务费 逐月付息 到期本息
     * @author jincheng 
     * @param formVo
     * @return
     */
    private List<RepayPlan> calcServiceFeePaymentDuePrincipal(ReceivablePlanForm formVo) {
    	log.info(" 服务费 逐月付息 到期本息");
		List<RepayPlan> planList=new  ArrayList<RepayPlan>();
		Integer periods=formVo.getTerm();//期数
		Long startDate=formVo.getLoanDate(),endDate=null,planRepayDate=null;
		Integer days=0;
		BigDecimal planPrincipalAmount=BigDecimal.ZERO,planInterestAmount=BigDecimal.ZERO,planServiceFee=BigDecimal.ZERO,remainPrincipal=formVo.getPrincipalAmount();
		for(Integer i=0;i<periods;i++){
			if(i>0){
				startDate=endDate;
			}
			if(i+1==periods){//最后一期
				planPrincipalAmount=remainPrincipal;
				endDate=Long.valueOf(TimeUtil.addDate(formVo.getLoanDate()+"", EnumTimeUnit.Month, periods));
				days=TimeUtil.compareDate(startDate+"", endDate+"", EnumTimeUnit.Day);//当期天数
				planRepayDate=endDate;//当期还款日
			}else{
				endDate=this.calcEndDate(startDate,formVo.getRepaymentDate(),formVo.getRepayMethod());
				days=TimeUtil.compareDate(startDate+"", endDate+"", EnumTimeUnit.Day);//当期天数
				planRepayDate=endDate;//当期还款日
			}
			planInterestAmount=BigDecimalCalculateTwo.div(BigDecimalCalculateTwo.mul(remainPrincipal, formVo.getRate()),BigDecimal.valueOf(1200d)).setScale(formVo.getDecimalPlace(), BigDecimal.ROUND_HALF_UP);//当期利息
			planServiceFee=BigDecimal.ZERO;//当期服务费
			remainPrincipal=BigDecimalCalculateTwo.sub(remainPrincipal, planPrincipalAmount);//当期剩余本金
			RepayPlan plan=new RepayPlan(startDate,endDate,planRepayDate,days,i+1,planPrincipalAmount,planInterestAmount,planServiceFee,remainPrincipal,null);
			planList.add(plan);
		}
		return planList;
	}
    
    /**
     * @Title: calcInterestPaymentQuarter 
     * @Description: 逐季付息后一次性还本
     * @author jincheng 
     * @param formVo
     * @return
     */
    private List<RepayPlan> calcInterestPaymentQuarter(ReceivablePlanForm formVo) {
    	log.info("逐季付息后一次性还本");
		List<RepayPlan> planList=new  ArrayList<RepayPlan>();
		Integer periods=formVo.getTerm();//期数
		Long startDate=formVo.getLoanDate(),endDate=null,planRepayDate=null;
		Integer days=0;
		BigDecimal planPrincipalAmount=BigDecimal.ZERO,planInterestAmount=BigDecimal.ZERO,planServiceFee=BigDecimal.ZERO,remainPrincipal=formVo.getPrincipalAmount();
		for(Integer i=0;i<periods;i++){
			if(i>0){
				startDate=endDate;
			}
			endDate=this.calcEndDate(startDate,formVo.getRepaymentDate(),formVo.getRepayMethod());
			days=TimeUtil.compareDate(startDate+"", endDate+"", EnumTimeUnit.Day);//当期天数
			planRepayDate=endDate;//当期还款日
			if(i+1==periods){//最后一期
				planPrincipalAmount=remainPrincipal;
			}else{
				if((i+1)%3==0){
					planPrincipalAmount=BigDecimalCalculateTwo.div(formVo.getPrincipalAmount(), BigDecimal.valueOf(periods/3));//当期本金
				}else{
					planPrincipalAmount=BigDecimal.ZERO;
				}
			}
			planInterestAmount=BigDecimalCalculateTwo.div(BigDecimalCalculateTwo.mul(remainPrincipal, formVo.getRate()),BigDecimal.valueOf(1200d)).setScale(formVo.getDecimalPlace(), BigDecimal.ROUND_HALF_UP);//当期利息
			planServiceFee=BigDecimal.ZERO;//当期服务费
			planPrincipalAmount=planPrincipalAmount.setScale(formVo.getDecimalPlace(), BigDecimal.ROUND_HALF_UP);
			remainPrincipal=BigDecimalCalculateTwo.sub(remainPrincipal, planPrincipalAmount);//当期剩余本金
			RepayPlan plan=new RepayPlan(startDate,endDate,planRepayDate,days,i+1,planPrincipalAmount,planInterestAmount,planServiceFee,remainPrincipal,null);
			planList.add(plan);
		}
		return planList;
	}

	/**
     * @Title: calcInterestPaymentMonth 
     * @Description: 逐月付息后一次性还本
     * @author jincheng 
     * @param formVo
     * @return
     */
    private List<RepayPlan> calcInterestPaymentMonth(ReceivablePlanForm formVo) {
    	log.info(" 逐月付息后一次性还本");
		List<RepayPlan> planList=new  ArrayList<RepayPlan>();
		Integer periods=formVo.getTerm();//期数
		Long startDate=formVo.getLoanDate(),endDate=null,planRepayDate=null;
		Integer days=0;
		BigDecimal planPrincipalAmount=BigDecimal.ZERO,planInterestAmount=BigDecimal.ZERO,planServiceFee=BigDecimal.ZERO,remainPrincipal=formVo.getPrincipalAmount();
		for(Integer i=0;i<periods;i++){
			if(i>0){
				startDate=endDate;
			}
			endDate=this.calcEndDate(startDate,formVo.getRepaymentDate(),formVo.getRepayMethod());
			days=TimeUtil.compareDate(startDate+"", endDate+"", EnumTimeUnit.Day);//当期天数
			planRepayDate=endDate;//当期还款日
			if(i+1==periods){//最后一期
				planPrincipalAmount=remainPrincipal;
			}else{
				planPrincipalAmount=BigDecimal.ZERO;//当期本金
			}
			planInterestAmount=BigDecimalCalculateTwo.div(BigDecimalCalculateTwo.mul(remainPrincipal, formVo.getRate()),BigDecimal.valueOf(1200d)).setScale(formVo.getDecimalPlace(), BigDecimal.ROUND_HALF_UP);//当期利息
			planServiceFee=BigDecimal.ZERO;//当期服务费
			remainPrincipal=BigDecimalCalculateTwo.sub(remainPrincipal, planPrincipalAmount);//当期剩余本金
			RepayPlan plan=new RepayPlan(startDate,endDate,planRepayDate,days,i+1,planPrincipalAmount,planInterestAmount,planServiceFee,remainPrincipal,null);
			planList.add(plan);
		}
		return planList;
	}

	/**
     * @Title: calcInterestPaymentDue 
     * @Description: 一次付息到期(按期)还本
     * @author jincheng 
     * @param formVo
     * @return
     */
    private List<RepayPlan> calcInterestPaymentDue(ReceivablePlanForm formVo) {
    	log.info("一次付息到期(按期)还本");
		List<RepayPlan> planList=new  ArrayList<RepayPlan>();
		Integer loanDays=formVo.getLoanDays();//垫资天数
		Long startDate=formVo.getLoanDate(),endDate=null;
		Integer days=0;
		BigDecimal planPrincipalAmount=BigDecimal.ZERO,planInterestAmount=BigDecimal.ZERO,planServiceFee=BigDecimal.ZERO,remainPrincipal=formVo.getPrincipalAmount();
		
		if("1".equals(formVo.getBusinessType())){//兴业贷
			planInterestAmount=BigDecimalCalculateTwo.mul(BigDecimalCalculateTwo.div(BigDecimalCalculateTwo.mul(formVo.getPrincipalAmount(), formVo.getRate()), BigDecimal.valueOf(1200)), BigDecimal.valueOf(formVo.getTerm())).setScale(formVo.getDecimalPlace(), BigDecimal.ROUND_HALF_UP);
			endDate=Long.valueOf(TimeUtil.addDate(startDate+"", EnumTimeUnit.Month, formVo.getTerm()));
		}else{//过桥贷
			planInterestAmount=BigDecimalCalculateTwo.mul(BigDecimalCalculateTwo.div(BigDecimalCalculateTwo.mul(formVo.getPrincipalAmount(), formVo.getRate()), BigDecimal.valueOf(36000)), BigDecimal.valueOf(loanDays)).setScale(formVo.getDecimalPlace(), BigDecimal.ROUND_HALF_UP);
			endDate=Long.valueOf(TimeUtil.addDate(startDate+"", EnumTimeUnit.Day, loanDays));
		}
		
		RepayPlan plan1=new RepayPlan(startDate,startDate,startDate,days,1,planPrincipalAmount,planInterestAmount,planServiceFee,remainPrincipal,null);
		planList.add(plan1);
	
		RepayPlan plan2=new RepayPlan(startDate,endDate,endDate,loanDays,2,remainPrincipal,BigDecimal.ZERO,planServiceFee,BigDecimal.ZERO,null);
		planList.add(plan2);
		return planList;
	}

	/**
     * @Title: calcAverageCapital 
     * @Description: 等额本金
     * @author jincheng 
     * @param formVo
     * @return
     */
    private List<RepayPlan> calcAverageCapital(ReceivablePlanForm formVo) {
    	log.info("等额本金");
		List<RepayPlan> planList=new  ArrayList<RepayPlan>();
		Integer periods=formVo.getTerm();//期数
		Long startDate=formVo.getLoanDate(),endDate=null,planRepayDate=null;
		Integer days=0;
		BigDecimal planPrincipalAmount=BigDecimal.ZERO,planInterestAmount=BigDecimal.ZERO,planServiceFee=BigDecimal.ZERO,remainPrincipal=formVo.getPrincipalAmount();
		for(Integer i=0;i<periods;i++){
			if(i>0){
				startDate=endDate;
			}
			endDate=this.calcEndDate(startDate,formVo.getRepaymentDate(),formVo.getRepayMethod());
			days=TimeUtil.compareDate(startDate+"", endDate+"", EnumTimeUnit.Day);//当期天数
			planRepayDate=endDate;//当期还款日
			if(i+1==periods){//最后一期
				planPrincipalAmount=remainPrincipal;
			}else{
				planPrincipalAmount=BigDecimalCalculateTwo.div(formVo.getPrincipalAmount(), BigDecimal.valueOf(periods)).setScale(formVo.getDecimalPlace(), BigDecimal.ROUND_HALF_UP);//当期本金
			}
			planInterestAmount=BigDecimalCalculateTwo.div(BigDecimalCalculateTwo.mul(remainPrincipal, formVo.getRate()),BigDecimal.valueOf(1200d)).setScale(formVo.getDecimalPlace(), BigDecimal.ROUND_HALF_UP);//当期利息
			planServiceFee=BigDecimal.ZERO;//当期服务费
			remainPrincipal=BigDecimalCalculateTwo.sub(remainPrincipal, planPrincipalAmount);//当期剩余本金
			RepayPlan plan=new RepayPlan(startDate,endDate,planRepayDate,days,i+1,planPrincipalAmount,planInterestAmount,planServiceFee,remainPrincipal,null);
			planList.add(plan);
		}
		return planList;
	}


    /**
     * @Title: calcAverageCapitalPlusInterest 
     * @Description: 等额本息(固定利率)
     * @author jincheng 
     * @param formVo
     * @return
     */
	private List<RepayPlan> calcAverageCapitalPlusInterest(ReceivablePlanForm formVo) {
		log.info("等额本息(固定利率)");
		List<RepayPlan> planList=new  ArrayList<RepayPlan>();
		Integer periods=formVo.getTerm();//期数
		Long startDate=formVo.getLoanDate(),endDate=null,planRepayDate=null;
		Integer days=0;
		BigDecimal planPrincipalAmount=BigDecimal.ZERO,planInterestAmount=BigDecimal.ZERO,planServiceFee=BigDecimal.ZERO,remainPrincipal=formVo.getPrincipalAmount();
		BigDecimal totalInterestAmount=BigDecimalCalculateTwo.div(BigDecimalCalculateTwo.mul(BigDecimalCalculateTwo.mul(formVo.getPrincipalAmount(), formVo.getRate()),
																													BigDecimal.valueOf(formVo.getTerm())),BigDecimal.valueOf(1200));//应还贷款利息
		
		BigDecimal eachTotalAmount=BigDecimalCalculateTwo.div(BigDecimalCalculateTwo.add(formVo.getPrincipalAmount(), totalInterestAmount), BigDecimal.valueOf(formVo.getTerm())).setScale(formVo.getDecimalPlace(), BigDecimal.ROUND_HALF_UP);//每期还款合计
		
		List<Double> flowInArr=new ArrayList<Double>();  
	        flowInArr.add(BigDecimal.ZERO.subtract(formVo.getPrincipalAmount()).doubleValue());
	        for(int i=0;i<periods;i++){
	        	 flowInArr.add(eachTotalAmount.doubleValue());
	        }
	        BigDecimal baseRate=IrrUtil.getIrr(flowInArr);//实际月利率
	        
		for(Integer i=0;i<periods;i++){
			if(i>0){
				startDate=endDate;
			}
			endDate=this.calcEndDate(startDate,formVo.getRepaymentDate(),formVo.getRepayMethod());
			days=TimeUtil.compareDate(startDate+"", endDate+"", EnumTimeUnit.Day);//当期天数
			planRepayDate=endDate;//当期还款日
			
			planInterestAmount=BigDecimalCalculateTwo.mul(remainPrincipal, baseRate).setScale(formVo.getDecimalPlace(), BigDecimal.ROUND_HALF_UP);//当期利息
			if(i+1==periods){//最后一期
				planPrincipalAmount=remainPrincipal;
			}else{
				planPrincipalAmount=BigDecimalCalculateTwo.sub(eachTotalAmount,planInterestAmount );//当期本金
			}
			planServiceFee=BigDecimal.ZERO;//当期服务费
			remainPrincipal=BigDecimalCalculateTwo.sub(remainPrincipal, planPrincipalAmount);//当期剩余本金
			
			RepayPlan plan=new RepayPlan(startDate,endDate,planRepayDate,days,i+1,planPrincipalAmount,planInterestAmount,planServiceFee,remainPrincipal,null);
			planList.add(plan);
		}
		return planList;
	}

	/**
	 * @Title: calcEndDate 
	 * @Description: 计算每期的到期日期
	 * @author jincheng 
	 * @param startDate 当期开始日期
	 * @param repaymentDate 每期还款号数
	 * @param repayMethod 还款方式
	 * @return
	 */
	private Long calcEndDate(Long startDate,Integer repaymentDate,String repayMethod) {
		Long endDate=0l;
		
		if(ObjectHelper.isNotEmpty(repaymentDate)&&repaymentDate>0){
			if(repaymentDate>31){
				repaymentDate=31;
			}
			
			Integer startDateNum=this.getDaysByYearMonth(Integer.parseInt((""+startDate).substring(0, 4)), Integer.parseInt((""+startDate).substring(4, 6)));
			
			if((Integer.parseInt((""+startDate).substring(6, 8))==startDateNum)){
				endDate= Long.valueOf(TimeUtil.addDate(""+startDate, EnumTimeUnit.Month, 1));
				Integer endDateNum=this.getDaysByYearMonth(Integer.parseInt((""+endDate).substring(0, 4)), Integer.parseInt((""+endDate).substring(4, 6)));
				if(endDateNum>repaymentDate){
					endDateNum=repaymentDate;
				}
				Long repaymentDate_=0l;
				if(endDateNum>9){
					repaymentDate_=Long.valueOf((""+endDate).substring(0, 6)+""+endDateNum);
				}else{
					repaymentDate_=Long.valueOf((""+endDate).substring(0, 6)+"0"+endDateNum);
				}
				return repaymentDate_;
			}
			
			if(repaymentDate>9){
				startDate=Long.valueOf((""+startDate).substring(0, 6)+""+repaymentDate);
			}else{
				startDate=Long.valueOf((""+startDate).substring(0, 6)+"0"+repaymentDate);
			}
			endDate=Long.valueOf(TimeUtil.addDate(""+startDate, EnumTimeUnit.Month, 1));
		}else{
			endDate=Long.valueOf(TimeUtil.addDate(""+startDate, EnumTimeUnit.Month, 1));
		}
		return endDate;
	}
	
	/**
	 * @Description: 获取某月最后一天的号数
	 * @author jincheng 
	 * @param year
	 * @param month
	 * @return
	 */
	  public  int getDaysByYearMonth(int year, int month) {  
	        Calendar a = Calendar.getInstance();  
	        a.set(Calendar.YEAR, year);  
	        a.set(Calendar.MONTH, month - 1);  
	        a.set(Calendar.DATE, 1);  
	        a.roll(Calendar.DATE, -1);  
	        return a.get(Calendar.DATE);  
	    } 
}