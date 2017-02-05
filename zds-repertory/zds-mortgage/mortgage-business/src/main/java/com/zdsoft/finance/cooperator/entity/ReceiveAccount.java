package com.zdsoft.finance.cooperator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 合作方返佣账户
 * 
 * @author Hisa
 *
 */
@Entity
@Table(name = "cpt_brokerage_account_info")
public class ReceiveAccount extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 账户描述
	 */
	@Column(length = 100)
	private String accountDescribe;
	/**
	 * 开户银行
	 */
	@Column(length = 64)
	private String bankName;
	/**
	 * 开户名
	 */
	@Column(length = 64)
	private String accountName;
	/**
	 * 帐号/卡号
	 */
	@Column(length = 32)
	private String accountNumber;
	/**
	 * 返佣收款人姓名
	 */
	@Column(length = 64)
	private String receiverName;
	/**
	 * 返佣收款人手机
	 */
	@Column(length = 15)
	private String receiverPhoneNubmer;
	/**
	 * 审批状态
	 */
	@Column(length = 15)
	private String approveState;
	/**
	 * 活动账号
	 */
	@Column(length = 32)
	private String aliveAccount;

	/**
	 * 关联的终端
	 */
	@ManyToOne
	@JoinColumn(name = "terminalId")
	private CooperatorTerminal cooperatorTerminal;

	public CooperatorTerminal getCooperatorTerminal() {
		return cooperatorTerminal;
	}

	public void setCooperatorTerminal(CooperatorTerminal cooperatorTerminal) {
		this.cooperatorTerminal = cooperatorTerminal;
	}

	public String getAccountDescribe() {
		return accountDescribe;
	}

	public void setAccountDescribe(String accountDescribe) {
		this.accountDescribe = accountDescribe;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverPhoneNubmer() {
		return receiverPhoneNubmer;
	}

	public void setReceiverPhoneNubmer(String receiverPhoneNubmer) {
		this.receiverPhoneNubmer = receiverPhoneNubmer;
	}

	public String getApproveState() {
		return approveState;
	}

	public void setApproveState(String approveState) {
		this.approveState = approveState;
	}

	public String getAliveAccount() {
		return aliveAccount;
	}

	public void setAliveAccount(String aliveAccount) {
		this.aliveAccount = aliveAccount;
	}

}
