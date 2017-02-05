package com.zdsoft.finance.casemanage.receivablePlan.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zdsoft.finance.casemanage.receivablePlan.entity.CalcProid;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlanForm;
import com.zdsoft.finance.casemanage.receivablePlan.entity.RepayPlan;
import com.zdsoft.finance.common.utils.BigDecimalCalculateTwo;
import com.zdsoft.finance.common.utils.EnumTimeUnit;
import com.zdsoft.finance.common.utils.IrrUtils;
import com.zdsoft.finance.common.utils.RateUtil;
import com.zdsoft.finance.common.utils.TimeUtil;

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

@Repository
public class ReceivablePlanServiceCalc {


    /**
     * 根据页面表单参数生成还款计划
     */
    @SuppressWarnings("unchecked")
    public List<RepayPlan> getRepayPlanVoList(ReceivablePlanForm repayPlanFormVo) {
        
        List<CalcProid> calcProidVoList=this.getCalcProidList(repayPlanFormVo);
        
        List<RepayPlan> planVoList=new ArrayList<RepayPlan>();
        List<RepayPlan> planVoList_h=new ArrayList<RepayPlan>();
        
        BigDecimal period=BigDecimal.valueOf(calcProidVoList.size());//贷款期数
        
        BigDecimal shenyubenjin=repayPlanFormVo.getaMT_Page();//当期剩余本金
        BigDecimal amt = repayPlanFormVo.getaMT_Page();
        BigDecimal month = BigDecimal.valueOf(Long.valueOf(repayPlanFormVo.getLoanPeriodMonth()));
        BigDecimal  InterestRate = BigDecimal.valueOf(RateUtil.rate(Double.valueOf(repayPlanFormVo.getInterestRate()))) ;
        BigDecimal sumlixi = BigDecimalCalculateTwo.mul(BigDecimalCalculateTwo.div(BigDecimalCalculateTwo.mul(amt, InterestRate),BigDecimal.valueOf(12)),month);
        BigDecimal actualRate = BigDecimal.valueOf(0d);
        BigDecimal benjin_lixi = BigDecimal.valueOf(0d);
        Integer applyLoanDt_bef = 0;//还款时间
        if("9".equals(repayPlanFormVo.getRepayMethod())|| "10".equals(repayPlanFormVo.getRepayMethod())){
        
//          System.out.println(sumlixi);
            
            Map<String,Object> map_month = TimeUtil.getMonthCha(repayPlanFormVo.getApplyLoanDt()+"",repayPlanFormVo.getApplyRepayDt()+"");
            @SuppressWarnings("rawtypes")
            List planPeriod_h = new ArrayList();
            planPeriod_h.add(-Integer.valueOf(amt+""));
            int month_ = Integer.valueOf(map_month.get("month")+"");
            int month_his = Integer.valueOf(map_month.get("month")+"");
            int day_ =  Integer.valueOf(map_month.get("day")+"");
            
             if("10".equals(repayPlanFormVo.getRepayMethod())){
                 month_ = BigDecimalCalculateTwo.sub(BigDecimal.valueOf(month_), BigDecimal.valueOf(Integer.valueOf(repayPlanFormVo.getPiecewisePeriod()))).intValue();
                 sumlixi = BigDecimalCalculateTwo.mul(BigDecimalCalculateTwo.div(BigDecimalCalculateTwo.mul(amt, InterestRate),BigDecimal.valueOf(12)),BigDecimal.valueOf(month_));
//               BigDecimal  lixi_benxi=BigDecimalCalculateTwo.mul(repayPlanFormVo.getaMT_Page(),BigDecimal.valueOf(RateUtil.rate(Double.valueOf(repayPlanFormVo.getPiecewiseRate()))));
                 
//               for(int k=0;k<Integer.valueOf(repayPlanFormVo.getPiecewisePeriod()) ; k++ ){
//                   planPeriod_h.add(lixi_benxi);
//               }
                 
              }
              benjin_lixi=
                    BigDecimalCalculateTwo.div(BigDecimalCalculateTwo.add(amt,sumlixi),BigDecimal.valueOf(month_) );
             
            
            if(day_ > 0){
                month_ = month_+day_;
            }
            for(int j=0;j<month_ ;j++){
                planPeriod_h.add(benjin_lixi);
            }
            
            IrrUtils  irrRate = new IrrUtils(planPeriod_h, month_his);
            System.out.println("实际利率："+BigDecimal.valueOf(irrRate.calculator()));
            actualRate = BigDecimal.valueOf(irrRate.calculator());
        }
        String repayMethod=repayPlanFormVo.getRepayMethod();
        if("1".equals(repayMethod)||"2".equals(repayMethod)||"3".equals(repayMethod)){
            
//          this
            
        }
        
        
        for(int i=0;i<calcProidVoList.size();i++){
            CalcProid vo=calcProidVoList.get(i);
            
            BigDecimal benjin=BigDecimal.valueOf(0d);BigDecimal lixi=BigDecimal.valueOf(0d);
            
            int days=vo.getDays();
            
            
            if("1".equals(repayMethod)||"2".equals(repayMethod)||"3".equals(repayMethod)){//TODO 到期还本
                if((i+1)==calcProidVoList.size()){
                    benjin=repayPlanFormVo.getaMT_Page();
                }else{
                    benjin=BigDecimal.valueOf(0d);
                }
                
                lixi=this.getInterest(repayPlanFormVo.getaMT_Page(),repayPlanFormVo.getInterestRate(),repayPlanFormVo.getE_RateUnit());
            }
            
            if("4".equals(repayMethod)){//TODO 利随本清
                benjin=repayPlanFormVo.getaMT_Page();
                lixi=this.getInterest(repayPlanFormVo.getaMT_Page(),repayPlanFormVo.getInterestRate(),repayPlanFormVo.getE_RateUnit());
            }
            
            if("YWDM0051006".equals(repayMethod)){//TODO 等额本金
                if(i==calcProidVoList.size()-1){
                    benjin=shenyubenjin;
                }else{
                    benjin=repayPlanFormVo.getaMT_Page().divide(period,new MathContext(10)).setScale(repayPlanFormVo.getXiaoshuwei(), BigDecimal.ROUND_HALF_UP);;
                }

                lixi=this.getInterest(shenyubenjin,repayPlanFormVo.getInterestRate(),days,repayPlanFormVo.getE_RateUnit(),repayPlanFormVo.getRepaymentDay());//默认
                shenyubenjin=BigDecimalCalculateTwo.sub(shenyubenjin, benjin);
            }
            
            if("YWDM0051007".equals(repayMethod)){//TODO 等额本息
                BigDecimal lilv=BigDecimal.valueOf(0d);
                if("09310001".equals(repayPlanFormVo.getE_RateUnit())){//年利率? 
                    lilv=BigDecimal.valueOf(Double.parseDouble(repayPlanFormVo.getInterestRate())).divide(BigDecimal.valueOf(12),new MathContext(10));
                }else if("09310002".equals(repayPlanFormVo.getE_RateUnit())){//月利率?
                    lilv=BigDecimal.valueOf(Double.parseDouble(repayPlanFormVo.getInterestRate()));
                }else{
                    lilv=BigDecimalCalculateTwo.mul(BigDecimal.valueOf(Double.parseDouble(repayPlanFormVo.getInterestRate())),BigDecimal.valueOf(30d));
                }
                lilv=lilv.divide(BigDecimal.valueOf(100),new MathContext(10));
                //每期应还（本金?+利息?  每月还款? = 贷款本金×[月利率?(1+月利率?)^还款月数]÷[(1+月利率?)^还款月数-1]
                BigDecimal meiqi_benjin_lixi=
                        repayPlanFormVo.getaMT_Page().multiply(lilv).multiply(BigDecimal.valueOf(Math.pow(BigDecimalCalculateTwo.add(BigDecimal.valueOf(1), lilv).doubleValue(), calcProidVoList.size()))
                        ).setScale(8, BigDecimal.ROUND_HALF_UP);
                
                       meiqi_benjin_lixi=
                               meiqi_benjin_lixi.divide(BigDecimal.valueOf(Math.pow(BigDecimalCalculateTwo.add(BigDecimal.valueOf(1), lilv).doubleValue(), calcProidVoList.size())-1)
                                       ,new MathContext(10)).setScale(repayPlanFormVo.getXiaoshuwei(), BigDecimal.ROUND_HALF_UP);
                
                       lixi=this.getInterest(shenyubenjin,repayPlanFormVo.getInterestRate(),repayPlanFormVo.getE_RateUnit()).setScale(repayPlanFormVo.getXiaoshuwei(), BigDecimal.ROUND_HALF_UP);//默认
                
                if(i==calcProidVoList.size()-1){
                    benjin=shenyubenjin;
                }else{
                    benjin=BigDecimalCalculateTwo.sub(meiqi_benjin_lixi, lixi);
                }
                
                shenyubenjin=BigDecimalCalculateTwo.sub(shenyubenjin, benjin);
            }
            
            if("7".equals(repayMethod)){//TODO 等本等息
                benjin=BigDecimalCalculateTwo.div(repayPlanFormVo.getaMT_Page(), period).setScale(repayPlanFormVo.getXiaoshuwei(), BigDecimal.ROUND_HALF_UP);//每期还款本金
                if(i==calcProidVoList.size()-1){
                    benjin=shenyubenjin;
                }else{
                    benjin=repayPlanFormVo.getaMT_Page().divide(period,new MathContext(10)).setScale(repayPlanFormVo.getXiaoshuwei(), BigDecimal.ROUND_HALF_UP);//每期还款本金
                }
                lixi=this.getInterest(repayPlanFormVo.getaMT_Page(),repayPlanFormVo.getInterestRate(),repayPlanFormVo.getE_RateUnit());
                shenyubenjin=BigDecimalCalculateTwo.sub(shenyubenjin, benjin);
            }
            if("9".equals(repayMethod)){//TODO 等额本息(银行)
                
                
                BigDecimal lilv=BigDecimal.valueOf(0d);
                if("09310001".equals(repayPlanFormVo.getE_RateUnit())){//年利率? 
                    lilv=BigDecimal.valueOf(Double.parseDouble(repayPlanFormVo.getInterestRate())).divide(BigDecimal.valueOf(12),new MathContext(10));
                }else if("09310002".equals(repayPlanFormVo.getE_RateUnit())){//月利率?
                    lilv=BigDecimal.valueOf(Double.parseDouble(repayPlanFormVo.getInterestRate()));
                }else{
                    lilv=BigDecimalCalculateTwo.mul(BigDecimal.valueOf(Double.parseDouble(repayPlanFormVo.getInterestRate())),BigDecimal.valueOf(30d));
                }
                lilv=lilv.divide(BigDecimal.valueOf(100),new MathContext(10));
                
                       lixi=BigDecimalCalculateTwo.mul(shenyubenjin,actualRate).setScale(repayPlanFormVo.getXiaoshuwei(), BigDecimal.ROUND_HALF_UP);//默认
                
                if(i==calcProidVoList.size()-1){
                    benjin=shenyubenjin;
                }else{
                    benjin=BigDecimalCalculateTwo.sub(benjin_lixi, lixi);
                }
                
                shenyubenjin=BigDecimalCalculateTwo.sub(shenyubenjin, benjin);
            }
            
        if("10".equals(repayMethod)){//分段还款
            if(repayPlanFormVo.getPiecewisePeriod() != null && i< Integer.valueOf(repayPlanFormVo.getPiecewisePeriod())){
                if((i+1)==calcProidVoList.size()){
                    benjin=repayPlanFormVo.getaMT_Page();
                }else{
                    benjin=BigDecimal.valueOf(0d);
                }
                BigDecimal piecewiseRate = BigDecimal.valueOf(RateUtil.rate(Double.valueOf(repayPlanFormVo.getPiecewiseRate()))) ;
                lixi=BigDecimalCalculateTwo.mul(repayPlanFormVo.getaMT_Page(),piecewiseRate);
            }else{
            
                BigDecimal lilv=BigDecimal.valueOf(0d);
                if("09310001".equals(repayPlanFormVo.getE_RateUnit())){//年利率? 
                    lilv=BigDecimal.valueOf(Double.parseDouble(repayPlanFormVo.getInterestRate())).divide(BigDecimal.valueOf(12),new MathContext(10));
                }else if("09310002".equals(repayPlanFormVo.getE_RateUnit())){//月利率?
                    lilv=BigDecimal.valueOf(Double.parseDouble(repayPlanFormVo.getInterestRate()));
                }else{
                    lilv=BigDecimalCalculateTwo.mul(BigDecimal.valueOf(Double.parseDouble(repayPlanFormVo.getInterestRate())),BigDecimal.valueOf(30d));
                }
                lilv=lilv.divide(BigDecimal.valueOf(100),new MathContext(10));
                
                       lixi=BigDecimalCalculateTwo.mul(shenyubenjin,actualRate).setScale(repayPlanFormVo.getXiaoshuwei(), BigDecimal.ROUND_HALF_UP);//默认
                
                if(i==calcProidVoList.size()-1){
                    benjin=shenyubenjin;
                }else{
                    benjin=BigDecimalCalculateTwo.sub(benjin_lixi, lixi);
                }
                
                shenyubenjin=BigDecimalCalculateTwo.sub(shenyubenjin, benjin);
            
            }
            
            
        }
        if("11".equals(repayMethod)){//季度部分本息
                BigDecimal meiqibenjin = BigDecimal.valueOf(0);
                Integer applyLoanDt = vo.getStartDt();
                
                if(i == 0){
                      applyLoanDt_bef = Integer.valueOf(TimeUtil.addDateTwo(applyLoanDt+"", EnumTimeUnit.Month, 3));
                }
                meiqibenjin = BigDecimalCalculateTwo.mul(repayPlanFormVo.getaMT_Page(),BigDecimal.valueOf(RateUtil.rate(Double.valueOf(repayPlanFormVo.getQuarterlyPrincipalRatio())))).setScale(repayPlanFormVo.getXiaoshuwei(), BigDecimal.ROUND_HALF_UP);
                    if(applyLoanDt.equals(applyLoanDt_bef) && shenyubenjin.compareTo(meiqibenjin) == 1){
                        benjin = meiqibenjin;
                        shenyubenjin=BigDecimalCalculateTwo.sub(shenyubenjin, benjin);
                        applyLoanDt_bef = Integer.valueOf(TimeUtil.addDate(applyLoanDt_bef+"", EnumTimeUnit.Month, 3));
                    }
                    
                if(shenyubenjin.compareTo(meiqibenjin) == -1 || applyLoanDt.equals(repayPlanFormVo.getApplyRepayDt()) ){
                    benjin = shenyubenjin;
                    lixi=this.getInterest(shenyubenjin,repayPlanFormVo.getInterestRate(),repayPlanFormVo.getE_RateUnit());
                    shenyubenjin=BigDecimalCalculateTwo.sub(shenyubenjin, benjin);
                    break;
                } 
                lixi=this.getInterest(shenyubenjin,repayPlanFormVo.getInterestRate(),repayPlanFormVo.getE_RateUnit());
        }
        
        
        RepayPlan planBenJinVo=this.warrperRepayPlanVo(i+1, vo.getStartDt(),vo.getEndDt(),vo.getActRepayDt(),days, benjin,"1",shenyubenjin);
        RepayPlan planLixiVo=this.warrperRepayPlanVo(i+1, vo.getStartDt(),vo.getEndDt(),vo.getActRepayDt(),days, new BigDecimal(lixi+"").setScale(repayPlanFormVo.getXiaoshuwei(), BigDecimal.ROUND_HALF_UP),"2",BigDecimal.ZERO);//四舍五入不保留小�?
        planVoList.add(planBenJinVo);
        planVoList.add(planLixiVo);
    }
    
        
        
        
        planVoList_h.removeAll(planVoList);
        if(planVoList.size()>0){
            @SuppressWarnings("unused")
            RepayPlan planVo_h=planVoList.get(1);
            int period_h=0;
            
            for(int j=0;j<planVoList.size();j++){
                RepayPlan planVo_h_s=planVoList.get(j);
                            if(j%2==0&&j>1){
                                period_h+=1;
                            }
                            planVo_h_s.setPeriodsNo(String.valueOf(period_h));//更新还款期数
                            
                            if("2".equals(planVo_h_s.getMoneyType())){
                                if(j==planVoList.size()-1){
                                    planVo_h_s.setPlanAmount(new BigDecimal(0));
                                    planVo_h_s.setAffirmAmount(new BigDecimal(0));
                                }else{
                                    planVo_h_s.setPlanAmount(planVoList.get(j+2).getPlanAmount());
                                    planVo_h_s.setAffirmAmount(planVoList.get(j+2).getAffirmAmount());
                                }
                            }
                            
                            planVoList_h.add(planVo_h_s);   
        }
            
//          planVoList_h.addAll(planVoList_h);
            
        
        }
        
        
        
        
        
        
        
        //提前收息---已放款日作为还款日单独产生一期，利息会下期利息（及还款计划的每期的利息都向上推�?�最后一期的利息�?0�?
//      if(repayPlanFormVo.isH_IsInterest()&&planVoList.size()>0){
//          //新建第一�?
//          RepayPlan planVo_h=planVoList.get(1);
//          RepayPlan planBenJinVo=this.warrperRepayPlanVo(1, planVo_h.getStartDt(),planVo_h.getStartDt(),planVo_h.getStartDt(),planVo_h.getDays(), 0,"1");
//          RepayPlan planLixiVo=this.warrperRepayPlanVo(1, planVo_h.getStartDt(),planVo_h.getStartDt(),planVo_h.getStartDt(),planVo_h.getDays(),new BigDecimal(planVo_h.getPlanAmount()).setScale(repayPlanFormVo.getXiaoshuwei(), BigDecimal.ROUND_HALF_UP).doubleValue(),"2");
//          planVoList_h.add(planBenJinVo);
//          planVoList_h.add(planLixiVo);
//          int period_h=2;
//          for(int j=0;j<planVoList.size();j++){
//              RepayPlan planVo_h_s=planVoList.get(j);
//                          if(j%2==0&&j>1){
//                              period_h+=1;
//                          }
//                          planVo_h_s.setPeriodsNo(String.valueOf(period_h));//更新还款期数
//                          
//                          if("2".equals(planVo_h_s.getMoneyType())){
//                              if(j==planVoList.size()-1){
//                                  planVo_h_s.setPlanAmount(0);
//                                  planVo_h_s.setAffirmAmount(0);
//                              }else{
//                                  planVo_h_s.setPlanAmount(planVoList.get(j+2).getPlanAmount());
//                                  planVo_h_s.setAffirmAmount(planVoList.get(j+2).getAffirmAmount());
//                              }
//                          }
//          }
//      }
//      planVoList_h.addAll(planVoList);
        return planVoList_h;
    }
    

    /**
     * 封装每期计划
     * @param period
     * @param startDt
     * @param endDt
     * @param days
     * @param amount
     * @param moneyType
     * @return
     */
    private RepayPlan warrperRepayPlanVo( int period,int startDt,int endDt,int actRepayDt,int days, BigDecimal amount,String moneyType,BigDecimal shenyubenjin) {
        RepayPlan planVo=new RepayPlan();//利息
                planVo.setPeriodsNo(String.valueOf(period));
                planVo.setDays(days);
                planVo.setPlanAmount(amount);
                planVo.setAffirmAmount(amount);
                planVo.setMoneyType(Integer.parseInt(moneyType));
                planVo.setStartDt(Long.parseLong(startDt+""));
                planVo.setEndDt(Long.parseLong(endDt+""));
                planVo.setAffirmDt(Long.parseLong(actRepayDt+""));
                planVo.setPlanDueDt(Long.parseLong(actRepayDt+""));
                planVo.setResidualprincipal(shenyubenjin);
                return planVo;
    }
    
    /**
     * 根据天数计算利息
     * @param getaMT_Page
     * @param interestRate
     * @param days
     * @param e_RateUnit
     * @return
     */
    private BigDecimal getInterest(BigDecimal getaMT_Page, String interestRate,int days, String e_RateUnit,String repaymentDay) {
        BigDecimal lilv=BigDecimal.valueOf(0d);
        if("09310001".equals(e_RateUnit)){//年利率?
            lilv=BigDecimalCalculateTwo.div(BigDecimal.valueOf(Double.parseDouble(interestRate)), BigDecimal.valueOf(Double.parseDouble(repaymentDay)));
        }else if("09310002".equals(e_RateUnit)){//月利率?
            lilv=BigDecimalCalculateTwo.div(BigDecimalCalculateTwo.mul(BigDecimal.valueOf(Double.parseDouble(interestRate)),BigDecimal.valueOf(12d)),BigDecimal.valueOf(Double.parseDouble(repaymentDay)));
        }else{
            lilv=BigDecimalCalculateTwo.div(BigDecimalCalculateTwo.mul(BigDecimal.valueOf(Double.parseDouble(interestRate)),BigDecimal.valueOf(360d)),BigDecimal.valueOf(Double.parseDouble(repaymentDay)));
        }
        return BigDecimalCalculateTwo.div(BigDecimalCalculateTwo.mul(BigDecimalCalculateTwo.mul(getaMT_Page, lilv),BigDecimal.valueOf(days)),BigDecimal.valueOf(100d));
    }
    
    /**
     * 根据月利率计算每期的利息--主要针对等额本金、等额本息、等本等息
     * @param getaMT_Page
     * @param interestRate
     * @param days
     * @param e_RateUnit
     * @return
     */
    private BigDecimal getInterest(BigDecimal getaMT_Page, String interestRate, String e_RateUnit) {
        BigDecimal lilv=BigDecimal.valueOf(0d);
        if("09310001".equals(e_RateUnit)){//年利率?
            lilv=BigDecimalCalculateTwo.div(BigDecimal.valueOf(Double.parseDouble(interestRate)),BigDecimal.valueOf(12d));
        }else if("09310002".equals(e_RateUnit)){//月利率?
            lilv=BigDecimal.valueOf(Double.parseDouble(interestRate));
        }else{
            lilv=BigDecimalCalculateTwo.mul(BigDecimal.valueOf(Double.parseDouble(interestRate)),BigDecimal.valueOf(30d));
        }
        return BigDecimalCalculateTwo.div(BigDecimalCalculateTwo.mul(getaMT_Page, lilv),BigDecimal.valueOf(100d));
    }


    /**
     * 封装还款期数、每期开始日期、结束日期、天
     * @param applyLoanDt
     * @param loanPeriodMonth
     * @param loanPeriodDay
     * @return
     */
    private List<CalcProid> getCalcProidList(ReceivablePlanForm repayPlanFormVo) {
        List<CalcProid> voList=new ArrayList<CalcProid>();
        
        Integer applyLoanDt=Integer.valueOf(repayPlanFormVo.getApplyLoanDt());//放款日?
        Integer endDt=Integer.valueOf(repayPlanFormVo.getApplyRepayDt());//到期日?
        
        String selectFixRepaymentDt=repayPlanFormVo.getSelectFixRepaymentDt();//还款日生成策略?: 1、放款日 2、指定日 3、月末?
        String repaymentDt=repayPlanFormVo.getRepaymentDt();//指定还款日?
                
        Integer last_StartDt=applyLoanDt;//默认为放款日
        
        Integer actRepaymentDt=applyLoanDt;
        
        if("4".equals(repayPlanFormVo.getRepayMethod())){
            CalcProid vo=this.wraperCalcProidVo(applyLoanDt, endDt,repayPlanFormVo.isH_IsTailType(),repayPlanFormVo.isH_InterestType(),endDt,true,true);
            voList.add(vo);
            return voList;
        }
        
        Integer repayMethodType = 1;
        if("2".equals(repayPlanFormVo.getRepayMethod())){
            repayMethodType = 3;
        }else if("3".equals(repayPlanFormVo.getRepayMethod())){
            repayMethodType = 12;
        }
        boolean isFirst=true;
        for(Integer i=applyLoanDt;i<endDt;){
            
            if("1".equals(selectFixRepaymentDt)){//按放款日
                i=Integer.valueOf(TimeUtil.addDate(String.valueOf(i), EnumTimeUnit.Month, repayMethodType));
                actRepaymentDt=i;
            }
            
            if("2".equals(selectFixRepaymentDt)){//指定日期?
                //1.判断放款日是否在指定日之后
                if(i>this.getRepayRi(applyLoanDt.toString(), repaymentDt)&&applyLoanDt==i){//第一期?
                    i=Integer.valueOf(TimeUtil.addDate(String.valueOf(i), EnumTimeUnit.Month, repayMethodType));
                    i=this.getRepayRi(i.toString(), repaymentDt);
                }else if(i<this.getRepayRi(applyLoanDt.toString(), repaymentDt)&&applyLoanDt==i){//第一期?
                    if("2".equals(repayPlanFormVo.getRepayMethod())||"3".equals(repayPlanFormVo.getRepayMethod())){
                        i=Integer.valueOf(TimeUtil.addDate(String.valueOf(i), EnumTimeUnit.Month, repayMethodType));
                    }
                    i=this.getRepayRi(i.toString(), repaymentDt);
                }else{
                    i=Integer.valueOf(TimeUtil.addDate(String.valueOf(i), EnumTimeUnit.Month, repayMethodType));
                    i=this.getRepayRi(i.toString(), repaymentDt);
                }
                actRepaymentDt=i;
            }
            
            if("3".equals(selectFixRepaymentDt)){//月末
                if(applyLoanDt==i){//第一期?
                    if("2".equals(repayPlanFormVo.getRepayMethod())||"3".equals(repayPlanFormVo.getRepayMethod())){
                        i=Integer.valueOf(TimeUtil.addDate(String.valueOf(i), EnumTimeUnit.Month, repayMethodType));
                    }
                    i=TimeUtil.getDateToInt(TimeUtil.lastDayOfMonth(i));
                }else{
                    i=Integer.valueOf(TimeUtil.addDate(String.valueOf(i), EnumTimeUnit.Month, repayMethodType));
                    i=TimeUtil.getDateToInt(TimeUtil.lastDayOfMonth(i));
                }
                actRepaymentDt=i;
            }
            
            if("4".equals(selectFixRepaymentDt)){//放款日为第一期，后面每期指定还款日，到开始日为月初结算日为月末?
                if("1".equals(repayPlanFormVo.getRepayType())){
                    if(applyLoanDt==i){//第一期?
                        if("2".equals(repayPlanFormVo.getRepayMethod())||"3".equals(repayPlanFormVo.getRepayMethod())){
                            i=Integer.valueOf(TimeUtil.addDate(String.valueOf(i), EnumTimeUnit.Month, repayMethodType));
                        }
                        actRepaymentDt=i;
                        Integer filterRepaymentDt=this.getRepayRi(i.toString(), repaymentDt);
                        if(filterRepaymentDt>i){
                            actRepaymentDt=filterRepaymentDt;
                        }
                        i=TimeUtil.getDateToInt(TimeUtil.lastDayOfMonth(i));
                    }else{
                        i=Integer.valueOf(TimeUtil.addDate(String.valueOf(i), EnumTimeUnit.Month, repayMethodType));
                        i=TimeUtil.getDateToInt(TimeUtil.lastDayOfMonth(i));
                        actRepaymentDt=this.getRepayRi(i.toString(), repaymentDt);
                    }
                }else{
                    i=Integer.valueOf(TimeUtil.addDate(String.valueOf(i), EnumTimeUnit.Month, repayMethodType));
                    i=TimeUtil.getDateToInt(TimeUtil.lastDayOfMonth(i));
                    actRepaymentDt=this.getRepayRi(i.toString(), repaymentDt);
                }
            }
            
            if(i>=endDt){
                if(repaymentDt != null &&"4".equals(selectFixRepaymentDt)){
                    actRepaymentDt=this.getRepayRi(endDt.toString(), repaymentDt);
                }else{
                    actRepaymentDt = endDt;
                }
                CalcProid vo=this.wraperCalcProidVo(last_StartDt, endDt,repayPlanFormVo.isH_IsTailType(),repayPlanFormVo.isH_InterestType(),actRepaymentDt,false,true);
                if(vo.getDays()>0){
                    voList.add(vo);
                }
                break;
            }
            
            CalcProid vo=this.wraperCalcProidVo(last_StartDt, i,repayPlanFormVo.isH_IsTailType(),repayPlanFormVo.isH_InterestType(),actRepaymentDt,isFirst,false);
            last_StartDt=i;
            voList.add(vo); 
            isFirst=false;
        }
        
        return voList;
    }
    
    /**
     * 指定还款日，解决指定日 在28号之后
     * @param repay
     * @param repaymentDt
     * @return
     */
    private Integer getRepayRi(String repay,String repaymentDt){
        if(repaymentDt.length()==1){
            repaymentDt="0"+repaymentDt;
        }
        Integer xx=Integer.parseInt(repay.substring(0,6)+repaymentDt);
        if(Integer.parseInt(repaymentDt)>28){
            Integer lastM_Day=TimeUtil.getDateToInt(TimeUtil.lastDayOfMonth(Integer.parseInt(repay.substring(0,6)+"01")));//当月�?后一�?
            if(xx>lastM_Day){
                xx=lastM_Day;//当月�?后一�?
            }
        }
        return xx;
    }
    
    /**
     * 封装每期天数、开始日、结束日
     * @param startDt
     * @param endDt
     * @return
     */
    private CalcProid  wraperCalcProidVo(Integer startDt,Integer endDt,boolean isSuanWei,boolean isXMsuanwei,Integer actrepayDt,boolean isFirst,boolean isEnd){
        CalcProid vo=new CalcProid();
        try {
            
            if(isSuanWei&&!isFirst&&!isEnd){
                startDt=Integer.parseInt(TimeUtil.addDate(startDt+"", EnumTimeUnit.Day, 1));
            }
            
            vo.setStartDt(startDt);
            vo.setEndDt(endDt);
            vo.setActRepayDt(actrepayDt);
            
            if(isEnd&&!isXMsuanwei){//项目不算尾?
                endDt=Integer.parseInt(TimeUtil.addDate(endDt+"", EnumTimeUnit.Day, -1));
            }
            
            if(!isEnd&&!isSuanWei){//期间内算尾?
                endDt=Integer.parseInt(TimeUtil.addDate(endDt+"", EnumTimeUnit.Day, -1));
            }
            
            if(isEnd&&isSuanWei){//期间内算尾?
                startDt=Integer.parseInt(TimeUtil.addDate(startDt+"", EnumTimeUnit.Day, 1));
            }
            
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            long to = df.parse(String.valueOf(endDt)).getTime();
            long from = df.parse(String.valueOf(startDt)).getTime();
            long days=(to - from) / (1000 * 60 * 60 * 24);

            vo.setDays(Integer.parseInt(days+""));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  vo;
    }

    
    /**
     * 提前结清、部分还款从新封装还款
     */
    public List<RepayPlan> getAdvanceRepayPlanVoList(ReceivablePlanForm repayPlanFormVo) {
        
        List<RepayPlan> planVoList=new ArrayList<RepayPlan>();
        
        //最重要的是找到上次还款日 （可以从LoanPartialPaymentLog表中获取，但是这就限制了页面不能更改还款日）
        Integer lastRepayDt=repayPlanFormVo.getLastRepayDt();
        
        if(repayPlanFormVo.isH_IsTailType()){
            lastRepayDt=Integer.parseInt(TimeUtil.addDate(lastRepayDt+"", EnumTimeUnit.Day, 1));
        }
        
        CalcProid proidVo=this.wraperCalcProidVo(lastRepayDt,repayPlanFormVo.getRepayDt(), repayPlanFormVo.isH_IsTailType(),repayPlanFormVo.isH_InterestType(),repayPlanFormVo.getRepayDt(),true,false);//第一�?
        
        //还款当期作为一期还款计划（提前结清、部分还款）
        //1.计算还款日当期应还利息（需考虑提前收息的情况、以便退费）
        RepayPlan jieqinBenJinPlanVo=this.warrperRepayPlanVo(1, lastRepayDt,repayPlanFormVo.getRepayDt(),repayPlanFormVo.getRepayDt(),proidVo.getDays(), repayPlanFormVo.getRepayAmt(),"1",repayPlanFormVo.getaMT_Page());
        BigDecimal  lixi=this.getInterest(repayPlanFormVo.getRepayAmt(),repayPlanFormVo.getInterestRate(),proidVo.getDays(),repayPlanFormVo.getE_RateUnit(),repayPlanFormVo.getRepaymentDay());
        
        repayPlanFormVo.setApplyLoanDt(lastRepayDt);//还款日
        
        if("2".equals(repayPlanFormVo.getLixiRepayWay())){//贷款金额产生息
                lixi=this.getInterest(repayPlanFormVo.getaMT_Page(),repayPlanFormVo.getInterestRate(),proidVo.getDays(),repayPlanFormVo.getE_RateUnit(),repayPlanFormVo.getRepaymentDay());
                if(repayPlanFormVo.isH_IsTailType()){
                    repayPlanFormVo.setApplyLoanDt(Integer.parseInt(TimeUtil.addDate(repayPlanFormVo.getRepayDt()+"", EnumTimeUnit.Day, 1)));//还款�?
                }else{
                    repayPlanFormVo.setApplyLoanDt(repayPlanFormVo.getRepayDt());//还款日
                }
        }
        
        if(repayPlanFormVo.isH_IsInterest()){//TODO 判断还款日前期是否收息，如果收息就考虑退费情况
//          double shouxi=this.getRaymentAmount(repayPlanFormVo.getOrderID(),repayPlanFormVo.getRepayDt());//判断还款日上期是否收�?
//          if(shouxi>0){
//              lixi=BigDecimalCalculateTwo.sub(lixi, shouxi);
//          }
        }
        
        RepayPlan jieqinLiXiPlanVo=this.warrperRepayPlanVo(1, lastRepayDt,repayPlanFormVo.getRepayDt(),repayPlanFormVo.getRepayDt(),proidVo.getDays(), new BigDecimal(lixi+"").setScale(repayPlanFormVo.getXiaoshuwei(), BigDecimal.ROUND_HALF_UP),"2",BigDecimal.ZERO);

        planVoList.add(jieqinBenJinPlanVo);
        planVoList.add(jieqinLiXiPlanVo);
        
        if("2".equals(repayPlanFormVo.getRepayType())){//部分还款
            //使用剩余的本金，还款日 生成新的还款计划
            repayPlanFormVo.setaMT_Page(BigDecimalCalculateTwo.sub(repayPlanFormVo.getaMT_Page(), repayPlanFormVo.getRepayAmt()));//剩余本金
            
            List<RepayPlan> repayPlanVoList=this.getRepayPlanVoList(repayPlanFormVo);
            
            for(RepayPlan planVo:repayPlanVoList){
                if(planVo.getDays()>0){
                    planVoList.add(planVo);
                }
            }
            
        }
        return planVoList;
    }
    
    /**
     * 计算还款日上期还款额
     * @param orderID
     * @param repayDt
     * @return
     */
//  private double getRaymentAmount(String orderID, int repayDt) {
//
//      List<LoanReceivableBill> loanbillList = loanReceivableBillDao.getLoanReceivableBillByOrderID(orderID);
//      LoanReceivableBill bill_x=null;
//      double shoukuan=0d;
//      for(LoanReceivableBill bill:loanbillList){
//          if(repayDt>bill.getPlanDueDt()&&"2".equals(bill.getMoneyType())){
//              bill_x=bill;
//              break;
//          }
//      }
//      if(ObjectHelper.isNotEmpty(bill_x)){
//          List<LoanPaidBill> paidBillList=loanPaidBillDao.getLoanPaidBillByReceivableBillID(bill_x.getId());
//          for(LoanPaidBill paidBill:paidBillList){
//              shoukuan=BigDecimalCalculateTwo.add(shoukuan, paidBill.getPaidAmount());
//          }
//      }
//      return shoukuan;
//  }
}
