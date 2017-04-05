package com.zdsoft.finance.casemanage.receivablePlan;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSONObject;
import com.zdsoft.finance.businesssetting.entity.QuestionScene;
import com.zdsoft.finance.casemanage.receivablePlan.constant.RepaymentPlanTypeEnum;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlanForm;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlanTemp;
import com.zdsoft.finance.casemanage.receivablePlan.entity.RepayPlan;
import com.zdsoft.finance.casemanage.receivablePlan.service.impl.ReceivablePlanServiceCalc;
import com.zdsoft.finance.common.enums.busiform.ApplyModelTypeEnum;
import com.zdsoft.finance.spi.businesssetting.QuestionnaireDto;

public class RepaymentPlanTest {
	
	
	@Test
	public void testRandom(){
		 System.out.println(ApplyModelTypeEnum.REQUESTCOMMISSION_APPLY.name);
//		SpringContextHolder.getBean("");
//		ContextLoader.getCurrentWebApplicationContext().getBean(event.getProcess());
//		Random r=new Random();
//		double rox=r.nextDouble()*3;
//		System.out.println(rox);
//		System.out.println(Math.round(rox)+2);
//		String[] b = {"公共的", "私有的", "受保护的"};
//		Random rand = new Random();
//		int num = rand.nextInt(3);
//		System.out.println(b[num]); 
//		System.out.println(num); 
		
		List<QuestionScene> sceneList3=new ArrayList<QuestionScene>();
		QuestionScene q1=new QuestionScene();
		q1.setQuestionContent("zhang1");
		sceneList3.add(q1);
		QuestionScene q2=new QuestionScene();
		q2.setQuestionContent("zhang2");
		sceneList3.add(q2);
		QuestionScene q3=new QuestionScene();
		q3.setQuestionContent("zhang3");
		sceneList3.add(q3);
//		QuestionScene q4=new QuestionScene();
//		q4.setQuestionContent("zhang4");
//		sceneList3.add(q4);
//		QuestionScene q5=new QuestionScene();
//		q5.setQuestionContent("zhang5");
//		sceneList3.add(q5);
//		QuestionScene q6=new QuestionScene();
//		q6.setQuestionContent("zhang6");
//		sceneList3.add(q1);
//		QuestionScene q7=new QuestionScene();
//		q7.setQuestionContent("zhang7");
//		sceneList3.add(q7);
		
		
		Random r=new Random();
		Long rox=Math.round(r.nextDouble()*3)+2;//2-5的随机数
		List<QuestionnaireDto> questionList2=new ArrayList<QuestionnaireDto>();
		
		if(rox>sceneList3.size()){
			for(QuestionScene qu:sceneList3){
				QuestionnaireDto dto3=new QuestionnaireDto();
				BeanUtils.copyProperties(qu, dto3,new String[]{"id"});
				dto3.setQuestionItem(qu.getParaValue());
				questionList2.add(dto3);
			}
		}else{
				for(Long j = 0l;j<rox;j++){
					Random rand = new Random();
					int num = rand.nextInt(sceneList3.size());
					QuestionnaireDto dto3=new QuestionnaireDto();
					BeanUtils.copyProperties(sceneList3.get(num), dto3,new String[]{"id"});
					dto3.setQuestionItem(sceneList3.get(num).getParaValue());
					questionList2.add(dto3);
					sceneList3.remove(sceneList3.get(num));
				}
		}
		for(QuestionnaireDto dto:questionList2){
			System.out.println(dto.getQuestionContent());
		}
	}
	

	/**
	 * @Title: testCalcAverageCapitalPlusInterest 
	 * @Description: 测试--等额本息
	 * @author jincheng
	 */
	@Test
	public void testCalcAverageCapitalPlusInterest(){
		System.out.println(RepaymentPlanTypeEnum.DRAFT.getValue());
		ReceivablePlanServiceCalc rps=new ReceivablePlanServiceCalc();
		ReceivablePlanForm form=new ReceivablePlanForm();
		form.setLoanDate(20161001l);//放款日期
		form.setPrincipalAmount(BigDecimal.valueOf(30000000d));//贷款金额
		form.setRate(BigDecimal.valueOf(12));//年利率
		form.setTerm(24);//期限
		form.setRepayMethod("1");//还款方式
		List<RepayPlan> planList=rps.getRepayPlanVoList(form);
		for(RepayPlan plan:planList){
				System.out.println(plan.getPeriods()+":本金["+plan.getPlanPrincipalAmount()+"]利息["+plan.getPlanInterestAmount()+"]天数["+plan.getDays()+"]还款日期["+plan.getPlanRepayDate()+"]剩余本金["+plan.getRemainPrincipal()+"]"+"开始日期["+plan.getStartDate()+"]结束日期["+plan.getEndDate()+"]服务费["+plan.getPlanServiceFee()+"]当前还款总额["+plan.getPlanTotalAmount()+"]");
		}
	}
	
	/**
	 * @Title: testCalcAverageCapital 
	 * @Description: 测试--等额本金
	 * @author jincheng
	 */
	@Test
	public void testCalcAverageCapital(){
		ReceivablePlanServiceCalc rps=new ReceivablePlanServiceCalc();
		ReceivablePlanForm form=new ReceivablePlanForm();
		form.setLoanDate(20161223l);//放款日期
		form.setPrincipalAmount(BigDecimal.valueOf(1000000d));//贷款金额
		form.setRate(BigDecimal.valueOf(12));//年利率
		form.setTerm(12);//期限
		form.setRepayMethod("2");//还款方式
		List<RepayPlan> planList=rps.getRepayPlanVoList(form);
		for(RepayPlan plan:planList){
				System.out.println(plan.getPeriods()+":本金["+plan.getPlanPrincipalAmount()+"]利息["+plan.getPlanInterestAmount()+"]天数["+plan.getDays()+"]还款日期["+plan.getPlanRepayDate()+"]剩余本金["+plan.getRemainPrincipal()+"]"+"开始日期["+plan.getStartDate()+"]结束日期["+plan.getEndDate()+"]服务费["+plan.getPlanServiceFee()+"]当前还款总额["+plan.getPlanTotalAmount()+"]");
		}
	}
	
	/**
	 * @Title: testCalcInterestPaymentDue 
	 * @Description: 测试-- 一次付息到期还本
	 * @author jincheng
	 */
	@Test
	public void testCalcInterestPaymentDue(){
		ReceivablePlanServiceCalc rps=new ReceivablePlanServiceCalc();
		ReceivablePlanForm form=new ReceivablePlanForm();
		form.setLoanDate(20161223l);//放款日期
		form.setBusinessType("1");
//		form.setBusinessType("2");
//		form.setLoanDays(10);//垫资天数
		form.setPrincipalAmount(BigDecimal.valueOf(1000000d));//贷款金额
		form.setRate(BigDecimal.valueOf(12));//月利率
		form.setTerm(1);//期限
		form.setRepayMethod("3");//还款方式
		List<RepayPlan> planList=rps.getRepayPlanVoList(form);
		for(RepayPlan plan:planList){
				System.out.println(plan.getPeriods()+":本金["+plan.getPlanPrincipalAmount()+"]利息["+plan.getPlanInterestAmount()+"]天数["+plan.getDays()+"]还款日期["+plan.getPlanRepayDate()+"]剩余本金["+plan.getRemainPrincipal()+"]"+"开始日期["+plan.getStartDate()+"]结束日期["+plan.getEndDate()+"]服务费["+plan.getPlanServiceFee()+"]当前还款总额["+plan.getPlanTotalAmount()+"]");
		}
	}

	/**
	 * @Title: testCalcInterestPaymentMonth 
	 * @Description: 测试-- 逐月付息后一次性还本
	 * @author jincheng
	 */
	@Test
	public void testCalcInterestPaymentMonth(){
		ReceivablePlanServiceCalc rps=new ReceivablePlanServiceCalc();
		ReceivablePlanForm form=new ReceivablePlanForm();
		form.setBusinessType(null);//业务类型
		form.setLoanDate(20161223l);//放款日期
		form.setPrincipalAmount(BigDecimal.valueOf(1000000d));//贷款金额
		form.setRate(BigDecimal.valueOf(12));//年利率
		form.setTerm(12);//期限
		form.setRepayMethod("4");//还款方式
		List<RepayPlan> planList=rps.getRepayPlanVoList(form);
		for(RepayPlan plan:planList){
				System.out.println(plan.getPeriods()+":本金["+plan.getPlanPrincipalAmount()+"]利息["+plan.getPlanInterestAmount()+"]天数["+plan.getDays()+"]还款日期["+plan.getPlanRepayDate()+"]剩余本金["+plan.getRemainPrincipal()+"]"+"开始日期["+plan.getStartDate()+"]结束日期["+plan.getEndDate()+"]服务费["+plan.getPlanServiceFee()+"]当前还款总额["+plan.getPlanTotalAmount()+"]");
		}
	}
	
	
	/**
	 * @Title: testCalcInterestPaymentQuarter 
	 * @Description: 测试-- 逐季付息后一次性还本 
	 * @author jincheng
	 */
	@Test
	public void testCalcInterestPaymentQuarter(){
		ReceivablePlanServiceCalc rps=new ReceivablePlanServiceCalc();
		ReceivablePlanForm form=new ReceivablePlanForm();
		form.setBusinessType(null);//业务类型
		form.setLoanDate(20161223l);//放款日期
		form.setPrincipalAmount(BigDecimal.valueOf(1000000d));//贷款金额
		form.setRate(BigDecimal.valueOf(13));//年利率
		form.setTerm(12);//期限
		form.setRepayMethod("YWDM0051001");//还款方式
		List<RepayPlan> planList=rps.getRepayPlanVoList(form);
		for(RepayPlan plan:planList){
				System.out.println(plan.getPeriods()+":本金["+plan.getPlanPrincipalAmount()+"]利息["+plan.getPlanInterestAmount()+"]天数["+plan.getDays()+"]还款日期["+plan.getPlanRepayDate()+"]剩余本金["+plan.getRemainPrincipal()+"]"+"开始日期["+plan.getStartDate()+"]结束日期["+plan.getEndDate()+"]服务费["+plan.getPlanServiceFee()+"]当前还款总额["+plan.getPlanTotalAmount()+"]");
		}
	}
	
	/**
	 * @Title: testCalcServiceFeePaymentDuePrincipal 
	 * @Description: 测试-- 服务费 逐月付息 到期本息
	 * @author jincheng
	 */
	@Test
	public void testCalcServiceFeePaymentDuePrincipal(){
		ReceivablePlanServiceCalc rps=new ReceivablePlanServiceCalc();
		ReceivablePlanForm form=new ReceivablePlanForm();
		form.setBusinessType(null);//业务类型
		form.setLoanDate(20161223l);//放款日期
		form.setPrincipalAmount(BigDecimal.valueOf(1000000d));//贷款金额
		form.setRate(BigDecimal.valueOf(12));//年利率
		form.setTerm(12);//期限
		form.setRepayMethod("6");//还款方式
		form.setRepaymentDate(25);
		List<RepayPlan> planList=rps.getRepayPlanVoList(form);
		for(RepayPlan plan:planList){
				System.out.println(plan.getPeriods()+":本金["+plan.getPlanPrincipalAmount()+"]利息["+plan.getPlanInterestAmount()+"]天数["+plan.getDays()+"]还款日期["+plan.getPlanRepayDate()+"]剩余本金["+plan.getRemainPrincipal()+"]"+"开始日期["+plan.getStartDate()+"]结束日期["+plan.getEndDate()+"]服务费["+plan.getPlanServiceFee()+"]当前还款总额["+plan.getPlanTotalAmount()+"]");
		}
	}
	
	/**
	 * @Title: testCalcServiceFeePaymentPrincipal 
	 * @Description: 测试-- 服务费 逐月付息 到期还本
	 * @author jincheng
	 */
	@Test
	public void testCalcServiceFeePaymentPrincipal(){
		ReceivablePlanServiceCalc rps=new ReceivablePlanServiceCalc();
		ReceivablePlanForm form=new ReceivablePlanForm();
		form.setBusinessType(null);//业务类型
		form.setLoanDate(20161223l);//放款日期
		form.setPrincipalAmount(BigDecimal.valueOf(1000000d));//贷款金额
		form.setRate(BigDecimal.valueOf(12));//年利率
		form.setTerm(12);//期限
		form.setRepayMethod("7");//还款方式
		form.setRepaymentDate(25);
		List<RepayPlan> planList=rps.getRepayPlanVoList(form);
		for(RepayPlan plan:planList){
				System.out.println(plan.getPeriods()+":本金["+plan.getPlanPrincipalAmount()+"]利息["+plan.getPlanInterestAmount()+"]天数["+plan.getDays()+"]还款日期["+plan.getPlanRepayDate()+"]剩余本金["+plan.getRemainPrincipal()+"]"+"开始日期["+plan.getStartDate()+"]结束日期["+plan.getEndDate()+"]服务费["+plan.getPlanServiceFee()+"]当前还款总额["+plan.getPlanTotalAmount()+"]");
		}
	}	
	
	/**
	 * @Title: testCalcSubsectionRepayment 
	 * @Description: 测试-- 分段还款
	 * @author jincheng
	 */
	@Test
	public void testCalcSubsectionRepayment(){
		ReceivablePlanServiceCalc rps=new ReceivablePlanServiceCalc();
		ReceivablePlanForm form=new ReceivablePlanForm();
		form.setLoanDate(20170214l);//放款日期
		form.setPrincipalAmount(BigDecimal.valueOf(1000000d));//贷款金额
		form.setRate(BigDecimal.valueOf(12));//年利率
		form.setTerm(12);//期限
		form.setSpTerm(3);
		form.setSpRate(BigDecimal.valueOf(15));//月利率
		form.setRepayMethod("8");//还款方式
		form.setRepaymentDate(6);//每期还款号数
		List<RepayPlan> planList=rps.getRepayPlanVoList(form);
		for(RepayPlan plan:planList){
				System.out.println(plan.getPeriods()+":本金["+plan.getPlanPrincipalAmount()+"]利息["+plan.getPlanInterestAmount()+"]天数["+plan.getDays()+"]还款日期["+plan.getPlanRepayDate()+"]剩余本金["+plan.getRemainPrincipal()+"]"+"开始日期["+plan.getStartDate()+"]结束日期["+plan.getEndDate()+"]服务费["+plan.getPlanServiceFee()+"]当前还款总额["+plan.getPlanTotalAmount()+"]");
		}
	}	
	
	/**
	 * @Title: testCalcAverageCapitalPlusInterestDecreasingYear 
	 * @Description: 测试-- 逐年递减等额本息
	 * @author jincheng
	 */
	@Test
	public void testCalcAverageCapitalPlusInterestDecreasingYear(){
		ReceivablePlanServiceCalc rps=new ReceivablePlanServiceCalc();
		ReceivablePlanForm form=new ReceivablePlanForm();
		form.setBusinessType(null);//业务类型
		form.setLoanDate(20170214l);//放款日期
		form.setLoanDays(10);//垫资天数
		form.setPrincipalAmount(BigDecimal.valueOf(1000000d));//贷款金额
		form.setRate(BigDecimal.valueOf(12));//月利率
		form.setTerm(12);//期限
		form.setSpTerm(3);
		form.setSpRate(BigDecimal.valueOf(15));
		form.setRepayMethod("9");//还款方式
		form.setRateNature(null);
		form.setRepaymentDate(6);//每期还款号数
		form.setRepaymentDay(null);
		form.setSplictPeriod(null);
		List<RepayPlan> planList=rps.getRepayPlanVoList(form);
		for(RepayPlan plan:planList){
				System.out.println(plan.getPeriods()+":本金["+plan.getPlanPrincipalAmount()+"]利息["+plan.getPlanInterestAmount()+"]天数["+plan.getDays()+"]还款日期["+plan.getPlanRepayDate()+"]剩余本金["+plan.getRemainPrincipal()+"]"+"开始日期["+plan.getStartDate()+"]结束日期["+plan.getEndDate()+"]服务费["+plan.getPlanServiceFee()+"]当前还款总额["+plan.getPlanTotalAmount()+"]");
		}
	}	
	
	@Test
	public void testJsonToObject(){
		String planList="[{\"periods\":1,\"planRepayDate\":20170301,\"planPrincipalAmount\":10107.19,\"planServiceFee\":0,\"planInterestAmount\":5392.81,\"remainPrincipal\":289892.81,\"startDate\":20170222,\"endDate\":20170301}]";
		List<ReceivablePlanTemp> planTempList=JSONObject.parseArray(planList, ReceivablePlanTemp.class);
		for(ReceivablePlanTemp temp:planTempList){
			System.out.println(temp.getRemainPrincipal());
		}
	}
	
}
