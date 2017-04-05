package com.zdsoft.finance.businesssetting.entity;

import com.zdsoft.framework.core.common.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title Bank.java
 * @className Bank
 * @description 银行代码域对象
 * @author LiaoGuoWei
 * @create 2017/2/15 15:37
 * @version V1.0
 **/
@Entity
@Table(name="buss_bankcode")
public class Bank extends BaseEntity{
	private static final long serialVersionUID = 2741867178996966281L;
	/**
	 * 银行名称
	 */
	@Column(length=64)
	private String bankName;
	/**
	 * 银行代码
	 */
	@Column(length=32)
	private String bankCode;

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
