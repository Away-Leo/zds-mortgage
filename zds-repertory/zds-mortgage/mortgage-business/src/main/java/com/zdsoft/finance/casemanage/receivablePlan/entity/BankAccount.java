package com.zdsoft.finance.casemanage.receivablePlan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 还款计划
 * 
 * @author wangrongwei
 * @create 2017-01-05 20:11
 **/
@Entity
@Table(name = "case_bank_account")
public class BankAccount extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 账户类型（0:收款 1：还款 2:贷款发放账户）
	 */
	@Column
	private Integer accountType;
	
	/**
	 * 银行代码
	 */
	@Column(length=32)
	private String bankCode;
	
	/**
	 * 银行卡号
	 */
	@Column(length=50)
	private String bankNo;
	
	/**
	 * 开户银行
	 */
	@Column(length=64)
	private String bankAccount;
	
	
	/**
	 * 账户名
	 */
	@Column(length=128)
	private String cardholderName;
	
	/**
	 * 案件ID
	 */
	@Column(length=32)
	private String caseApplyId;

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
