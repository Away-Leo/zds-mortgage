package com.zdsoft.finance.cooperator.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.cooperator.entity.ReceiveAccount;

/**
 * 合作方返佣账户
 * 
 * @author Hisa
 *
 */
public class ReceiveAccountVo extends BaseVo<ReceiveAccount> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 账户描述
	 */
	private String accountDescribe;
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
	private String accountNumber;
	/**
	 * 返佣收款人姓名
	 */
	private String receiverName;
	/**
	 * 返佣收款人手机
	 */
	private String receiverPhoneNubmer;
	/**
	 * 审批状态
	 */
	private String approveState;
	private String approveStateName;
	/**
	 * 活动账号
	 */
	private String aliveAccount;
	/**
	 * 关联的终端
	 */
	private String terminalId;

	public String getApproveStateName() {
		return approveStateName;
	}

	public void setApproveStateName(String approveStateName) {
		this.approveStateName = approveStateName;
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

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public ReceiveAccountVo() {
		super();
	}

	public ReceiveAccountVo(ReceiveAccount receiveAccount) {
		super(receiveAccount);
	}

	public ReceiveAccountVo(ReceiveAccount receiveAccount, String[] args, String[] simpleArgs) {
		super(receiveAccount, args, simpleArgs);
	}

	public ReceiveAccount toPO() {
		ReceiveAccount po = new ReceiveAccount();
		return super.toPo(this, po);
	}
}
