package com.zdsoft.finance.app.repaymenttrial.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.finance.app.repaymenttrial.vo.TrialCalcProidVo;
import com.zdsoft.finance.app.repaymenttrial.vo.TrialRepayPlanVo;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlanForm;
import com.zdsoft.finance.casemanage.receivablePlan.entity.RepayPlan;
import com.zdsoft.finance.casemanage.receivablePlan.service.impl.ReceivablePlanServiceCalc;
import com.zdsoft.finance.common.utils.AppStatus;
import com.zdsoft.finance.common.utils.BigDecimalCalculate;
import com.zdsoft.finance.common.utils.BigDecimalCalculateTwo;
import com.zdsoft.finance.common.utils.EnumTimeUnit;
import com.zdsoft.finance.common.utils.TimeUtil;
import com.zdsoft.finance.common.utils.app.AppServerUtil;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.component.BaseController;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:RepaymentTrialController.java
 * @Package:com.zdsoft.finance.app.repaymenttrial.controller
 * @Description:还款计划试算
 * @author: jingjy
 * @date:2017年1月15日 下午7:12:35
 * @version:v1.0
 */

@Controller
@RequestMapping("server/bizCenter/repaymentPlan")
public class RepaymentTrialController extends BaseController{
    
    @Autowired
    private ReceivablePlanServiceCalc receivablePlanServiceCalc;
    
    @RequestMapping("/trial")
    @ResponseBody
    public String RepaymentTrial(TrialCalcProidVo vo){
        List<RepayPlan> list = null;
        List<TrialRepayPlanVo> list_old = new ArrayList<TrialRepayPlanVo>();
        List<TrialRepayPlanVo> list_new = new ArrayList<TrialRepayPlanVo>();
        if(ObjectHelper.isEmpty(vo)){
            return   AppServerUtil.buildJsonMessage(AppStatus.ArgsError);
        }
        try{
            ReceivablePlanForm planVo = new ReceivablePlanForm();
            Integer applyLoanDt = TimeUtil.getCurrentDateInteger();
            String loanPeriod = vo.getPeriodNum();
            String amount = String.valueOf(vo.getLoanAmount()).split("\\.")[0];
            
            BigDecimal amount_ = BigDecimal.valueOf(Double.valueOf(amount)).setScale(0, BigDecimal.ROUND_HALF_UP);
            String repayMethod = vo.getRepaymentType();
            String interestRate = Double.valueOf(vo.getAnnualRate())+"";
            String periodNumUnit = vo.getPeriodNumUnit();
            Integer pageIndex = Integer.valueOf(vo.getPageIndex()); 
            Integer pageSize = Integer.valueOf(vo.getPageSize()); 
            int total = Integer.parseInt(String.valueOf(BigDecimalCalculate.mul(Double.valueOf(pageIndex+""), Double.valueOf(pageSize+""))).split("\\.")[0]);
            String periodNumUnit_CH = "个月";
            if("12".equals(periodNumUnit) ){
                periodNumUnit = "09310001";
                periodNumUnit_CH="年";
                loanPeriod = String.valueOf(Integer.valueOf(loanPeriod)*12);
            }else{
                periodNumUnit = "09310002";
                
            }
            String applyRepayDt = TimeUtil.addDateTwo(applyLoanDt+"", EnumTimeUnit.Month, Integer.valueOf(loanPeriod));
            
            logger.info("本金amount："+amount+"期限loanPeriod："+loanPeriod);
            
            planVo.setApplyLoanDt(applyLoanDt);//放款日?
            planVo.setApplyRepayDt(Integer.valueOf(applyRepayDt));
            planVo.setLoanPeriodMonth(loanPeriod);//贷款期限-月?
            planVo.setPiecewisePeriod("3");
//          vo.setLoanPeriodDay("35");//贷款期限-日?
            planVo.setSelectFixRepaymentDt("1");//还款日方式? 1、还款日 2、指定日 3、月末?
            planVo.setaMT_Page(amount_);//贷款金额
            /* 还款方式 1、到期还本? 4、利随本清? 5、等额本金 6、等额本息 
             * 7、等本等息 9.等额本息（银行）10.分段还款 11 季度部分本息 */
            planVo.setRepayMethod(repayMethod);
            planVo.setE_RateUnit("09310001");//利率单位
            planVo.setInterestRate(interestRate);//利率
            planVo.setPiecewiseRate("15");//分段还款利率
            planVo.setQuarterlyPrincipalRatio("12");
            
            planVo.setH_IsInterest(false);//是否提前收息
            BigDecimal sum_ben = BigDecimal.valueOf(0);
            BigDecimal sum_xi = BigDecimal.valueOf(0);
            
             list = receivablePlanServiceCalc.getRepayPlanVoList(planVo);
             Map<String, Object> result = null;    
             for(int i=0;i<list.size();i++){
                 if(i%2==1){
                     TrialRepayPlanVo vo_new = new TrialRepayPlanVo();
                     BigDecimal planPrincipalAmount = list.get(i-1).getPlanAmount().setScale(2, BigDecimal.ROUND_HALF_UP);
//                     BigDecimal residualprincipal = list.get(i-1).getResidualprincipal().setScale(2, BigDecimal.ROUND_HALF_UP);
                     BigDecimal planInterestAmount = list.get(i).getPlanAmount().setScale(2, BigDecimal.ROUND_HALF_UP);
                     
                     sum_ben = BigDecimalCalculateTwo.add(sum_ben,planPrincipalAmount);
                     sum_xi=BigDecimalCalculateTwo.add(sum_xi,planInterestAmount);
                     String periodsNo = list.get(i-1).getPeriodsNo();
                     String planDueDt = TimeUtil.getCalendar_8(list.get(i-1).getPlanDueDt()+"");
                     
                     vo_new.setRepaymentAmount(planPrincipalAmount+"");
                     vo_new.setPeriodsNo(periodsNo);
                     vo_new.setRepaymentDate(planDueDt+"");
                     vo_new.setInterestAmount(planInterestAmount+"");
                     vo_new.setLoanBalance(BigDecimalCalculateTwo.add(planPrincipalAmount,planInterestAmount)+"");
                     
                     logger.info("期数："+periodsNo+"还款日："+planDueDt+"，本金："+planPrincipalAmount+"，利息:"+planInterestAmount);
                     list_old.add(vo_new);
                 }
             }
             if(total>list_old.size()){
                 total = list_old.size();
             }
             int index = 0;
             if(vo.getPageIndex() > 1 && index < total){
                 index = Integer.parseInt(String.valueOf(BigDecimalCalculate.mul(Double.valueOf(BigDecimalCalculate.sub(Double.valueOf(pageIndex), 1.0)), Double.valueOf(pageSize+""))).split("\\.")[0]);
             }
             for(int i=index;i<total;i++){
                 TrialRepayPlanVo planvo = list_old.get(i);
                 planvo.setPeriodsNo(i+1+"");
                 list_new.add(planvo);
             };
             
             result = new  HashMap<String,Object>();
             Map<String, Object> resultdata = new  HashMap<String,Object>();
             result.put("status", AppStatus.Succeed.value() );
             result.put("message", "试算成功");
             resultdata.put("loanAmount", amount+"");
             resultdata.put("periodNum", vo.getPeriodNum()+"");
             resultdata.put("totalCount", list_old.size()+"");
             resultdata.put("periodNumUnit", periodNumUnit_CH+"");
             resultdata.put("grossInterest", sum_xi+"");
             resultdata.put("repaymentTotal", BigDecimalCalculateTwo.add(sum_ben,sum_xi)+"");
             resultdata.put("list", list_new);
             result.put("data", resultdata);
             
             return    AppServerUtil.buildJsonObjectTwo(result);
        }catch(Exception e){
            e.printStackTrace();
            logger.info(e.getMessage()+"");
            return   AppServerUtil.buildJsonMessage(AppStatus.SystemError);  
    }
}
    
}