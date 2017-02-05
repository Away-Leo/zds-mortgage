package com.zdsoft.finance.cooperator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 合作方银行
 * 
 * @author Hisa
 *
 */
@Entity
@Table(name = "cpt_cooperator_bank_account")
public class CooperatorBank extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 开户银行
	 */
	@Column(length = 20)
	private String bankName;
	/**
	 * 银行卡号
	 */
	@Column(length = 32)
	private String bankAccount;
	/**
	 * 银行代码
	 */
	@Column(length = 32)
	private String bankCode;
	/**
	 * 开户名
	 */
	@Column(length = 32)
	private String accountName;
	/**
	 * 账户描述
	 */
	@Column(length = 500)
	private String accountDetali;
	/**
	 * 关联的终端
	 */
	@ManyToOne
	@JoinColumn(name = "terminalId")
	private CooperatorTerminal cooperatorTerminal;

	private Capitalist capitalist;

	public Capitalist getCapitalist() {
		return capitalist;
	}

	public void setCapitalist(Capitalist capitalist) {
		this.capitalist = capitalist;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountDetali() {
		return accountDetali;
	}

	public void setAccountDetali(String accountDetali) {
		this.accountDetali = accountDetali;
	}

	public CooperatorTerminal getCooperatorTerminal() {
		return cooperatorTerminal;
	}

	public void setCooperatorTerminal(CooperatorTerminal cooperatorTerminal) {
		this.cooperatorTerminal = cooperatorTerminal;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	
}
