package com.zdsoft.finance.businesssetting.entity;

import com.zdsoft.framework.core.common.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: OrgBankAccount.java
 * @ClassName: OrgBankAccount
 * @Description: 机构银行账户
 * @author jincheng
 * @date 2017年2月20日 下午8:59:20
 * @version V1.0
 */
@Entity
@Table(name = "buss_org_bank_account")
public class OrgBankAccount extends BaseEntity {
	private static final long serialVersionUID = -1052282076957325290L;

	/**
	 * 机构Id
	 */
	@Column(length = 32)
	private String orgId;

	/**
	 * 收款开户行
	 */
	@Column(length = 50)
	private String bankCode;

	/**
	 * 收款开户行名称
	 */
	@Column(length = 128)
	private String bankName;

	/**
	 * 收款银行账户名称
	 */
	@Column(length = 128)
	private String proceedsAccount;

	/**
	 * 收款账号
	 */
	@Column(length = 128)
	private String proceedsNumber;

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getProceedsAccount() {
		return proceedsAccount;
	}

	public void setProceedsAccount(String proceedsAccount) {
		this.proceedsAccount = proceedsAccount;
	}

	public String getProceedsNumber() {
		return proceedsNumber;
	}

	public void setProceedsNumber(String proceedsNumber) {
		this.proceedsNumber = proceedsNumber;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
