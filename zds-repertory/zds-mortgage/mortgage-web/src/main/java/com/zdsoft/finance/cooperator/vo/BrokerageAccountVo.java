package com.zdsoft.finance.cooperator.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.cooperator.entity.BrokerageAccount;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: BrokerageAccountVo.java
 * @ClassName: BrokerageAccountVo
 * @Description: 返佣账户信息Vo
 * @author liuwei
 * @date 2017年3月1日 下午6:10:24
 * @version V1.0
 */
public class BrokerageAccountVo extends BaseVo<BrokerageAccount> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 账户描述
	 */
	private String accountDetail;

	/**
	 * 开户银行
	 */
	private String bankName;

	/**
	 * 开户名
	 */
	private String accountName;

	/**
	 * 帐号/卡号
	 */
	private String bankAccount;

	/**
	 * 返佣收款人姓名
	 */
	private String brokeragePersonName;

	/**
	 * 返佣收款人手机
	 */
	private String brokeragePersonPhone;

	/**
	 * 账户状态
	 */
	private String approveType;

	/**
	 * 账户状态Name
	 */
	private String approveTypeName;

	/**
	 * 活动账号
	 */
	private String aliveAccount;

	/**
	 * 终端id
	 */
	private String terminalId;

	public String getAccountDetail() {
		return accountDetail;
	}

	public void setAccountDetail(String accountDetail) {
		this.accountDetail = accountDetail;
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

	public String getAliveAccount() {
		return aliveAccount;
	}

	public void setAliveAccount(String aliveAccount) {
		this.aliveAccount = aliveAccount;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getApproveTypeName() {
		return approveTypeName;
	}

	public void setApproveTypeName(String approveTypeName) {
		this.approveTypeName = approveTypeName;
	}

	public BrokerageAccountVo() {
		super();
	}

	public BrokerageAccountVo(BrokerageAccount brokerageAccount) {
		super(brokerageAccount);
	}

	public BrokerageAccountVo(BrokerageAccount brokerageAccount, String[] args, String[] simpleArgs) {
		super(brokerageAccount, args, simpleArgs);
	}

	public BrokerageAccount toPo() {
		BrokerageAccount brokerageAccount = new BrokerageAccount();
		return super.toPo(this, brokerageAccount);
	}
}
