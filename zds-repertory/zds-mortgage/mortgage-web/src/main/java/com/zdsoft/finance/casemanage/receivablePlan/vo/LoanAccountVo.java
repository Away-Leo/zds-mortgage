package com.zdsoft.finance.casemanage.receivablePlan.vo;

import com.zdsoft.finance.casemanage.receivablePlan.entity.BankAccount;
import com.zdsoft.finance.common.base.BaseVo;

/**
 * 还款计划
 * 
 * @author wangrongwei
 * @create 2017-01-05 20:11
 **/
public class LoanAccountVo extends BaseVo<BankAccount> {

	private static final long serialVersionUID = 1L;

	/**
	 * 账户类型（0:收款 1：还款 2:贷款发放账户）
	 */
	private Integer accountType;
	
	/**
	 * 银行代码
	 */
	private String bankCode;
	
	/**
	 * 银行卡号
	 */
	private String bankNo;
	
	/**
	 * 开户银行
	 */
	private String bankAccount;
	
	
	/**
	 * 账户名
	 */
	private String cardholderName;
	
	/**
	 * 案件ID
	 */
	private String caseApplyId;
	
	public LoanAccountVo() {
		// TODO Auto-generated constructor stub
	}
	
	public LoanAccountVo(BankAccount po) {
		super(po);
	}
	
	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getCardholderName() {
		return cardholderName;
	}

	public void setCardholderName(String cardholderName) {
		this.cardholderName = cardholderName;
	}

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}
	
}
