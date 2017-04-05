package com.zdsoft.finance.app.repaymenttrial.vo;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: TrialRepayPlanVo.java 
 * @ClassName: TrialRepayPlanVo 
 * @Description: 还款试算计划vo
 * @author jingjiyan 
 * @date 2017年3月7日 上午10:04:48 
 * @version V1.0
 */
public class TrialRepayPlanVo {

	/**
	 * 计划到期日
	 */
	private String repaymentDate;
	/**
	 * 期数
	 */
	private String periodsNo;
	/**
	 * 计划本金金额
	 */
	private String repaymentAmount;
	
	/**
	 * 计划利息金额
	 */
	private String interestAmount;
	
	
	/**
     * 当前应收
     */
    private String loanBalance;


    public String getRepaymentDate() {
        return repaymentDate;
    }


    public void setRepaymentDate(String repaymentDate) {
        this.repaymentDate = repaymentDate;
    }


    public String getPeriodsNo() {
        return periodsNo;
    }


    public void setPeriodsNo(String periodsNo) {
        this.periodsNo = periodsNo;
    }


    public String getRepaymentAmount() {
        return repaymentAmount;
    }


    public void setRepaymentAmount(String repaymentAmount) {
        this.repaymentAmount = repaymentAmount;
    }


    public String getInterestAmount() {
        return interestAmount;
    }


    public void setInterestAmount(String interestAmount) {
        this.interestAmount = interestAmount;
    }


    public String getLoanBalance() {
        return loanBalance;
    }


    public void setLoanBalance(String loanBalance) {
        this.loanBalance = loanBalance;
    }

}
