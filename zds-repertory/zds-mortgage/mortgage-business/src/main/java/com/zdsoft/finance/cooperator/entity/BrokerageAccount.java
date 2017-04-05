package com.zdsoft.finance.cooperator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: ReceiveAccount.java
 * @ClassName: ReceiveAccount
 * @Description: 返佣账户信息(刘伟二次维护)
 * @author liuwei
 * @date 2017年2月25日 下午2:07:59
 * @version V1.0
 */
@Entity
@Table(name = "cpt_brokerage_account_info")
public class BrokerageAccount extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 账户描述
	 */
	@Column(length = 500)
	private String accountDetail;

	/**
	 * 开户银行
	 */
	@Column(length = 32)
	private String bankName;

	/**
	 * 开户名
	 */
	@Column(length = 32)
	private String accountName;

	/**
	 * 帐号/卡号
	 */
	@Column(length = 32)
	private String bankAccount;

	/**
	 * 返佣收款人姓名
	 */
	@Column(length = 32)
	private String brokeragePersonName;

	/**
	 * 返佣收款人手机
	 */
	@Column(length = 32)
	private String brokeragePersonPhone;

	/**
	 * 审批状态
	 */
	@Column(length = 20)
	private String approveType;

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

	/**
	 * 临时存放终端id
	 */
	private transient String terminalId;

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

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAliveAccount() {
		return aliveAccount;
	}

	public void setAliveAccount(String aliveAccount) {
		this.aliveAccount = aliveAccount;
	}

	public String getAccountDetail() {
		return accountDetail;
	}

	public void setAccountDetail(String accountDetail) {
		this.accountDetail = accountDetail;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBrokeragePersonName() {
		return brokeragePersonName;
	}

	public void setBrokeragePersonName(String brokeragePersonName) {
		this.brokeragePersonName = brokeragePersonName;
	}

	public String getBrokeragePersonPhone() {
		return brokeragePersonPhone;
	}

	public void setBrokeragePersonPhone(String brokeragePersonPhone) {
		this.brokeragePersonPhone = brokeragePersonPhone;
	}

	public String getApproveType() {
		return approveType;
	}

	public void setApproveType(String approveType) {
		this.approveType = approveType;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

}
