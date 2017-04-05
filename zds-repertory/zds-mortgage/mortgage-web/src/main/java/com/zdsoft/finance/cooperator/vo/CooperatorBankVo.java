package com.zdsoft.finance.cooperator.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.cooperator.entity.CooperatorBank;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CooperatorBankVo.java
 * @ClassName: CooperatorBankVo
 * @Description: 合作方银行Vo
 * @author liuwei
 * @date 2017年3月9日 上午11:27:17
 * @version V1.0
 */
public class CooperatorBankVo extends BaseVo<CooperatorBank> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 开户银行
	 */
	private String bankName;
	/**
	 * 银行卡号
	 */
	private String bankAccount;
	/**
	 * 开户名
	 */
	private String accountName;
	/**
	 * 银行代码
	 */
	private String bankCode;
	/**
	 * 账户描述
	 */
	private String accountDetali;
	/**
	 * 关联的终端
	 */
	private String terminalId;

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
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

	public CooperatorBankVo() {
		super();
	}

	public CooperatorBankVo(CooperatorBank cooperatorBank) {
		super(cooperatorBank);
	}

	public CooperatorBankVo(CooperatorBank cooperatorBank, String[] args, String[] simpleArgs) {
		super(cooperatorBank, args, simpleArgs);
	}

	public CooperatorBank toPO() {
		CooperatorBank po = new CooperatorBank();
		return super.toPo(this, po);
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

}
