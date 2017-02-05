package com.zdsoft.finance.repayplantool.controller;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.common.utils.BigDecimalCalculate;
import com.zdsoft.finance.common.utils.DateUtil;
import com.zdsoft.finance.common.utils.EnumTimeUnit;
import com.zdsoft.finance.repayplantool.vo.CalcProidVo;
import com.zdsoft.finance.repayplantool.vo.RepayPlanToolVo;
import com.zdsoft.finance.repayplantool.vo.RepayPlanVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;


/**
 * 还款计划工具控制器
 * @author longwei
 * @date 2016/11/22
 * @version 1.0
 */
@Controller
@RequestMapping("/tool")
public class RepayPlanToolController extends BaseController{

	/**
	 * 还款计划工具菜单
	 * @return
	 */
	@RequestMapping("/repayPlanToolList")
    @UriKey(key = "com.zdsoft.finance.repayplantool.tool.repayPlanToolList")
	public ModelAndView repayPlanToolList(){
		ModelAndView modelAndView=new ModelAndView("repayPlanTool/repayPlanTool_list");
		return modelAndView;
	}
	
	/**
	 * 还款计划编辑
	 */
	@RequestMapping("/repayPlanToolEdit")
    @UriKey(key = "com.zdsoft.finance.repayplantool.tool.repayPlanToolEdit")
	public ModelAndView repayPlanToolEdit(){
		ModelAndView modelAndView=new ModelAndView("repayPlanTool/repayPlanTool_edit");
		return modelAndView;
	}
	
	@RequestMapping(value="/generateRepayPlan")
	@UriKey(key = "com.zdsoft.finance.repayplantool.tool.generateRepayPlan")
	@ResponseBody
	public ResponseMsg generateRepayPlan(RepayPlanToolVo repayPlanToolVo) throws Exception{
		ResponseMsg msg = new ResponseMsg();
		List<RepayPlanVo> planVoList=getRepaymentPlanList(repayPlanToolVo);
		List<RepayPlanVo> lists=new ArrayList<RepayPlanVo>();
		for(RepayPlanVo repayPlanVo:planVoList){
			RepayPlanVo principalRow=new RepayPlanVo();
			principalRow.setStartDt(repayPlanVo.getStartDt());
			principalRow.setEndDt(repayPlanVo.getEndDt());
			principalRow.setDays(repayPlanVo.getDays());
			principalRow.setPlanDueDt(repayPlanVo.getPlanDueDt());
			principalRow.setPeriodsNo(repayPlanVo.getPeriodsNo());
			principalRow.setFundType("本金");
			principalRow.setPlanAmount(repayPlanVo.getPlanPrincipalAmount());
			principalRow.setPlanPrincipalAmount(repayPlanVo.getPlanPrincipalAmount());
			principalRow.setPlanInterestAmount(repayPlanVo.getPlanInterestAmount());
			principalRow.setCurrentAccounts(repayPlanVo.getPlanInterestAmount().add(repayPlanVo.getPlanPrincipalAmount()));
			lists.add(principalRow);
			RepayPlanVo interestRow=new RepayPlanVo();
			interestRow.setStartDt(repayPlanVo.getStartDt());
			interestRow.setEndDt(repayPlanVo.getEndDt());
			interestRow.setDays(repayPlanVo.getDays());
			interestRow.setPlanDueDt(repayPlanVo.getPlanDueDt());
			interestRow.setPeriodsNo(repayPlanVo.getPeriodsNo());
			interestRow.setFundType("利息");
			interestRow.setPlanAmount(repayPlanVo.getPlanInterestAmount());
			interestRow.setPlanPrincipalAmount(repayPlanVo.getPlanPrincipalAmount());
			interestRow.setPlanInterestAmount(repayPlanVo.getPlanInterestAmount());
			interestRow.setCurrentAccounts(repayPlanVo.getPlanInterestAmount().add(repayPlanVo.getPlanPrincipalAmount()));
			lists.add(interestRow);
		}
		msg.setResultStatus(ResponseMsg.SUCCESS);
        msg.setRows(lists);
        msg.setTotal(Long.parseLong(""+lists.size()));
        return msg;
    
	}
	 
	public List<RepayPlanVo> getRepaymentPlanList(RepayPlanToolVo repayPlanToolVo) {
		
		List<CalcProidVo> calcProidVoList=this.getCalcProidList(repayPlanToolVo);
		
		List<RepayPlanVo> planVoList=new ArrayList<RepayPlanVo>();
		List<RepayPlanVo> planVoList_h=new ArrayList<RepayPlanVo>();
		
		BigDecimal period=BigDecimal.valueOf(calcProidVoList.size());//贷款期数
		
		BigDecimal shenyubenjin=repayPlanToolVo.getaMT_Page();//当期剩余本金
		for(int i=0;i<calcProidVoList.size();i++){
			CalcProidVo vo=calcProidVoList.get(i);
			
			BigDecimal benjin=BigDecimal.valueOf(0d);BigDecimal lixi=BigDecimal.valueOf(0d);
			
			int days=vo.getDays();
			
			String repayMethod=repayPlanToolVo.getRepayMethod();
			
			if("1".equals(repayMethod)||"2".equals(repayMethod)||"3".equals(repayMethod)){//到期还本
				if((i+1)==calcProidVoList.size()){
					benjin=repayPlanToolVo.getaMT_Page();
				}else{
					benjin=BigDecimal.valueOf(0d);
				}
				
				lixi=BigDecimal.valueOf(this.getInterest(repayPlanToolVo.getaMT_Page(),repayPlanToolVo.getInterestRate(),days,repayPlanToolVo.getE_RateUnit(),repayPlanToolVo.getRepaymentDay()));
			}
			
			if("4".equals(repayMethod)){//利随本清
				benjin=repayPlanToolVo.getaMT_Page();
				lixi=BigDecimal.valueOf(this.getInterest(repayPlanToolVo.getaMT_Page(),repayPlanToolVo.getInterestRate(),days,repayPlanToolVo.getE_RateUnit(),repayPlanToolVo.getRepaymentDay()));
			}
			
			if("5".equals(repayMethod)){//等额本金
				if(i==calcProidVoList.size()-1){
					benjin=shenyubenjin;
				}else{
					benjin=repayPlanToolVo.getaMT_Page().divide(period,new MathContext(10)).setScale(repayPlanToolVo.getXiaoshuwei(), BigDecimal.ROUND_HALF_UP);;
				}

				lixi=BigDecimal.valueOf(this.getInterest(shenyubenjin,repayPlanToolVo.getInterestRate(),repayPlanToolVo.getE_RateUnit()));//默认
				shenyubenjin=BigDecimal.valueOf(BigDecimalCalculate.sub(shenyubenjin.doubleValue(), benjin.doubleValue()));
			}
			
			if("6".equals(repayMethod)){//等额本息
				BigDecimal lilv=BigDecimal.valueOf(0d);
				if("09310001".equals(repayPlanToolVo.getE_RateUnit())){//年利率 
					lilv=BigDecimal.valueOf(Double.parseDouble(repayPlanToolVo.getInterestRate())).divide(BigDecimal.valueOf(12),new MathContext(10));
				}else{//月利率
					lilv=BigDecimal.valueOf(Double.parseDouble(repayPlanToolVo.getInterestRate()));
				}
				lilv=lilv.divide(BigDecimal.valueOf(100),new MathContext(10));
				//每期应还（本金+利息）  每月还款额 = 贷款本金×[月利率×(1+月利率)^还款月数]÷[(1+月利率)^还款月数-1]
				BigDecimal meiqi_benjin_lixi=
						repayPlanToolVo.getaMT_Page().multiply(lilv).multiply(BigDecimal.valueOf(Math.pow(BigDecimalCalculate.add(1d, lilv.doubleValue()), calcProidVoList.size()))
						).setScale(8, BigDecimal.ROUND_HALF_UP);
				
					   meiqi_benjin_lixi=
							   meiqi_benjin_lixi.divide(BigDecimal.valueOf(Math.pow(BigDecimalCalculate.add(1d, lilv.doubleValue()), calcProidVoList.size())-1)
									   ,new MathContext(10)).setScale(repayPlanToolVo.getXiaoshuwei(), BigDecimal.ROUND_HALF_UP);
				
					   lixi=BigDecimal.valueOf(this.getInterest(shenyubenjin,repayPlanToolVo.getInterestRate(),repayPlanToolVo.getE_RateUnit())).setScale(repayPlanToolVo.getXiaoshuwei(),BigDecimal.ROUND_HALF_UP);//默认
				
				if(i==calcProidVoList.size()-1){
					benjin=shenyubenjin;
				}else{
					benjin=BigDecimal.valueOf(BigDecimalCalculate.sub(meiqi_benjin_lixi.doubleValue(), lixi.doubleValue()));
				}
				
				shenyubenjin=BigDecimal.valueOf(BigDecimalCalculate.sub(shenyubenjin.doubleValue(), benjin.doubleValue()));
			}
			
			if("7".equals(repayMethod)){//等本等息
				benjin=BigDecimal.valueOf(BigDecimalCalculate.div(repayPlanToolVo.getaMT_Page().doubleValue(), period.doubleValue())).setScale(repayPlanToolVo.getXiaoshuwei(), BigDecimal.ROUND_HALF_UP);//每期还款本金
				if(i==calcProidVoList.size()-1){
					benjin=shenyubenjin;
				}else{
					benjin=repayPlanToolVo.getaMT_Page().divide(period,new MathContext(10)).setScale(repayPlanToolVo.getXiaoshuwei(), BigDecimal.ROUND_HALF_UP);//每期还款本金
				}
				lixi=BigDecimal.valueOf(this.getInterest(repayPlanToolVo.getaMT_Page(),repayPlanToolVo.getInterestRate(),repayPlanToolVo.getE_RateUnit()));
				shenyubenjin=BigDecimal.valueOf(BigDecimalCalculate.sub(shenyubenjin.doubleValue(), benjin.doubleValue()));
			}
			
			RepayPlanVo planBenJinVo=this.warrperRepayPlanVo(i+1, vo.getStartDt(),vo.getEndDt(),vo.getActRepayDt(),days, benjin,new BigDecimal(lixi+"").setScale(repayPlanToolVo.getXiaoshuwei(), BigDecimal.ROUND_HALF_UP));
			planVoList.add(planBenJinVo);
		}
		
		planVoList_h.addAll(planVoList);
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
	private RepayPlanVo warrperRepayPlanVo( int period,int startDt,int endDt,int actRepayDt,int days, BigDecimal benjin,BigDecimal lixi) {
		RepayPlanVo planVo=new RepayPlanVo();//利息
				planVo.setPeriodsNo(String.valueOf(period));
				planVo.setDays(days);
				planVo.setStartDt(Long.parseLong(startDt+""));
				planVo.setEndDt(Long.parseLong(endDt+""));
				planVo.setPlanDueDt(Long.parseLong(actRepayDt+""));
				planVo.setPlanPrincipalAmount(benjin);
				planVo.setPlanInterestAmount(lixi);
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
	private Double getInterest(BigDecimal getaMT_Page, String interestRate,int days, String e_RateUnit,String repaymentDay) {
		Double lilv=0d;
		if("09310001".equals(e_RateUnit)){//年利率
			lilv=BigDecimalCalculate.div(Double.parseDouble(interestRate), Double.parseDouble(repaymentDay));
		}else{//月利率
			lilv=BigDecimalCalculate.div(BigDecimalCalculate.mul(Double.parseDouble(interestRate),12d),Double.parseDouble(repaymentDay));
		}
		return BigDecimalCalculate.div(BigDecimalCalculate.mul(BigDecimalCalculate.mul(getaMT_Page.doubleValue(), lilv),days),100d);
	}
	
	/**
	 * 根据月利率计算每期的利息--主要针对等额本金、等额本息、等本等息
	 * @param getaMT_Page
	 * @param interestRate
	 * @param days
	 * @param e_RateUnit
	 * @return
	 */
	private Double getInterest(BigDecimal getaMT_Page, String interestRate, String e_RateUnit) {
		double lilv=0d;
		if("09310001".equals(e_RateUnit)){//年利率
			lilv=BigDecimalCalculate.div(Double.parseDouble(interestRate),12d);
		}else{//月利率
			lilv=Double.parseDouble(interestRate);
		}
		return BigDecimalCalculate.div(BigDecimalCalculate.mul(getaMT_Page.doubleValue(), lilv),100d);
	}


	/**
	 * 封装还款期数、每期开始日期、结束日期、天数
	 * @param applyLoanDt
	 * @param loanPeriodMonth
	 * @param loanPeriodDay
	 * @return
	 */
	private List<CalcProidVo> getCalcProidList(RepayPlanToolVo repayPlanToolVo) {
		List<CalcProidVo> voList=new ArrayList<CalcProidVo>();
		
		Integer applyLoanDt=Integer.valueOf(repayPlanToolVo.getApplyLoanDt());//放款日
		Integer endDt=Integer.valueOf(repayPlanToolVo.getApplyRepayDt());//到期日
		
		String selectFixRepaymentDt=repayPlanToolVo.getSelectFixRepaymentDt();//还款日生成策略: 1、放款日 2、指定日 3、月末
		String repaymentDt=repayPlanToolVo.getRepaymentDt();//指定还款日
		        
		Integer last_StartDt=applyLoanDt;//默认为放款日
		
		Integer actRepaymentDt=applyLoanDt;
		
		if("4".equals(repayPlanToolVo.getRepayMethod())){
			CalcProidVo vo=this.wraperCalcProidVo(applyLoanDt, endDt,repayPlanToolVo.isH_IsTailType(),repayPlanToolVo.isH_InterestType(),endDt,true,true);
			voList.add(vo);
			return voList;
		}
		
		Integer repayMethodType = 1;
		if("2".equals(repayPlanToolVo.getRepayMethod())){
			repayMethodType = 3;
		}else if("3".equals(repayPlanToolVo.getRepayMethod())){
			repayMethodType = 12;
		}
		boolean isFirst=true;
		for(Integer i=applyLoanDt;i<endDt;){
			
			if("1".equals(selectFixRepaymentDt)){//按放款日
				i=Integer.valueOf(DateUtil.addDate(String.valueOf(i), EnumTimeUnit.Month, repayMethodType));
				actRepaymentDt=i;
			}
			
			if("2".equals(selectFixRepaymentDt)){//指定日
				//1.判断放款日是否在指定日之后
				if(i>this.getRepayRi(applyLoanDt.toString(), repaymentDt)&&applyLoanDt==i){//第一期
					i=Integer.valueOf(DateUtil.addDate(String.valueOf(i), EnumTimeUnit.Month, repayMethodType));
					i=this.getRepayRi(i.toString(), repaymentDt);
				}else if(i<this.getRepayRi(applyLoanDt.toString(), repaymentDt)&&applyLoanDt==i){//第一期
					if("2".equals(repayPlanToolVo.getRepayMethod())||"3".equals(repayPlanToolVo.getRepayMethod())){
						i=Integer.valueOf(DateUtil.addDate(String.valueOf(i), EnumTimeUnit.Month, repayMethodType));
					}
					i=this.getRepayRi(i.toString(), repaymentDt);
				}else{
					i=Integer.valueOf(DateUtil.addDate(String.valueOf(i), EnumTimeUnit.Month, repayMethodType));
					i=this.getRepayRi(i.toString(), repaymentDt);
				}
				actRepaymentDt=i;
			}
			
			if("3".equals(selectFixRepaymentDt)){//月末
				if(applyLoanDt==i){//第一期
					if("2".equals(repayPlanToolVo.getRepayMethod())||"3".equals(repayPlanToolVo.getRepayMethod())){
						i=Integer.valueOf(DateUtil.addDate(String.valueOf(i), EnumTimeUnit.Month, repayMethodType));
					}
					i=DateUtil.getDateToInt(DateUtil.lastDayOfMonth(i));
				}else{
					i=Integer.valueOf(DateUtil.addDate(String.valueOf(i), EnumTimeUnit.Month, repayMethodType));
					i=DateUtil.getDateToInt(DateUtil.lastDayOfMonth(i));
				}
				actRepaymentDt=i;
			}
			
			if("4".equals(selectFixRepaymentDt)){//放款日为第一期，后面每期指定还款日，到开始日为月初结算日为月末
				if("1".equals(repayPlanToolVo.getRepayType())){
					if(applyLoanDt==i){//第一期
						if("2".equals(repayPlanToolVo.getRepayMethod())||"3".equals(repayPlanToolVo.getRepayMethod())){
							i=Integer.valueOf(DateUtil.addDate(String.valueOf(i), EnumTimeUnit.Month, repayMethodType));
						}
						actRepaymentDt=i;
						Integer filterRepaymentDt=this.getRepayRi(i.toString(), repaymentDt);
						if(filterRepaymentDt>i){
							actRepaymentDt=filterRepaymentDt;
						}
						i=DateUtil.getDateToInt(DateUtil.lastDayOfMonth(i));
					}else{
						i=Integer.valueOf(DateUtil.addDate(String.valueOf(i), EnumTimeUnit.Month, repayMethodType));
						i=DateUtil.getDateToInt(DateUtil.lastDayOfMonth(i));
						actRepaymentDt=this.getRepayRi(i.toString(), repaymentDt);
					}
				}else{
					i=Integer.valueOf(DateUtil.addDate(String.valueOf(i), EnumTimeUnit.Month, repayMethodType));
					i=DateUtil.getDateToInt(DateUtil.lastDayOfMonth(i));
					actRepaymentDt=this.getRepayRi(i.toString(), repaymentDt);
				}
			}
			
			if(i>=endDt){
				if(ObjectHelper.isNotEmpty(repaymentDt)&&"4".equals(selectFixRepaymentDt)){
					actRepaymentDt=this.getRepayRi(endDt.toString(), repaymentDt);
				}else{
					actRepaymentDt = endDt;
				}
				CalcProidVo vo=this.wraperCalcProidVo(last_StartDt, endDt,repayPlanToolVo.isH_IsTailType(),repayPlanToolVo.isH_InterestType(),actRepaymentDt,false,true);
				if(vo.getDays()>0){
					voList.add(vo);
				}
				break;
			}
			
			CalcProidVo vo=this.wraperCalcProidVo(last_StartDt, i,repayPlanToolVo.isH_IsTailType(),repayPlanToolVo.isH_InterestType(),actRepaymentDt,isFirst,false);
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
			Integer lastM_Day=DateUtil.getDateToInt(DateUtil.lastDayOfMonth(Integer.parseInt(repay.substring(0,6)+"01")));//当月最后一天
			if(xx>lastM_Day){
				xx=lastM_Day;//当月最后一天
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
	private CalcProidVo  wraperCalcProidVo(Integer startDt,Integer endDt,boolean isSuanWei,boolean isXMsuanwei,Integer actrepayDt,boolean isFirst,boolean isEnd){
		CalcProidVo vo=new CalcProidVo();
		try {
			
			if(isSuanWei&&!isFirst&&!isEnd){
				startDt=Integer.parseInt(DateUtil.addDate(startDt+"", EnumTimeUnit.Day, 1));
			}
			
			vo.setStartDt(startDt);
			vo.setEndDt(endDt);
			vo.setActRepayDt(actrepayDt);
			
			if(isEnd&&!isXMsuanwei){//项目不算尾
				endDt=Integer.parseInt(DateUtil.addDate(endDt+"", EnumTimeUnit.Day, -1));
			}
			
			if(!isEnd&&!isSuanWei){//期间内算尾
				endDt=Integer.parseInt(DateUtil.addDate(endDt+"", EnumTimeUnit.Day, -1));
			}
			
			if(isEnd&&isSuanWei){//期间内算尾
				startDt=Integer.parseInt(DateUtil.addDate(startDt+"", EnumTimeUnit.Day, 1));
			}
			
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			long to = df.parse(String.valueOf(endDt)).getTime();
			long from = df.parse(String.valueOf(startDt)).getTime();
			long days=(to - from) / (1000 * 60 * 60 * 24)+1;

			vo.setDays(Integer.parseInt(days+""));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return  vo;
	}
}
