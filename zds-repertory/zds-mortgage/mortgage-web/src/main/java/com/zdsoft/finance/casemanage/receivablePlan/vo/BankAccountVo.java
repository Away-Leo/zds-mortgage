package com.zdsoft.finance.casemanage.receivablePlan.vo;

/**
 * 还款计划
 * 
 * @author wangrongwei
 * @create 2017-01-05 20:11
 **/
public class BankAccountVo {
	
	/**
	 * 放款账户信息
	 */
	private LoanAccountVo loanAccountVo;
	
	/**
	 * 扣款账户信息
	 */
	private ReceivableAccountVo recAccountVo;
	
	public LoanAccountVo getLoanAccountVo() {
		return loanAccountVo;
	}

	public void setLoanAccountVo(LoanAccountVo loanAccountVo) {
		this.loanAccountVo = loanAccountVo;
	}

	public ReceivableAccountVo getRecAccountVo() {
		return recAccountVo;
	}

	public void setRecAccountVo(ReceivableAccountVo recAccountVo) {
		this.recAccountVo = recAccountVo;
	}
	
}
