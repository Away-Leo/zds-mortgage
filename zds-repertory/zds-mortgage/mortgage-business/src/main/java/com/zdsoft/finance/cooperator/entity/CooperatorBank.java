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
 * @Title: CooperatorBank.java
 * @ClassName: CooperatorBank
 * @Description: 合作银行(刘伟二次维护)
 * @author liuwei
 * @date 2017年2月25日 下午2:05:17
 * @version V1.0
 */
@Entity
@Table(name = "cpt_cooperator_bank")
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
	 * 关联的终端
	 */
	@ManyToOne
	@JoinColumn(name = "terminalId")
	private CooperatorTerminal cooperatorTerminal;

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public CooperatorTerminal getCooperatorTerminal() {
		return cooperatorTerminal;
	}

	public void setCooperatorTerminal(CooperatorTerminal cooperatorTerminal) {
		this.cooperatorTerminal = cooperatorTerminal;
	}

}
