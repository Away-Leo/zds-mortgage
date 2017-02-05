package com.zdsoft.finance.cooperator.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.cooperator.entity.CapitalistTrust;

public class CapitalistTrustVo extends BaseVo<CapitalistTrust>{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 计划投入资金
	 */
	private BigDecimal planInputCost;
	/**
	 * 约定的借款利率
	 */
	private BigDecimal appointBorrowRate;
	/**
	 * 约定的代偿时间
	 */
	private Long appointRepayDate;
	public BigDecimal getPlanInputCost() {
		return planInputCost;
	}
	public void setPlanInputCost(BigDecimal planInputCost) {
		this.planInputCost = planInputCost;
	}
	public BigDecimal getAppointBorrowRate() {
		return appointBorrowRate;
	}
	public void setAppointBorrowRate(BigDecimal appointBorrowRate) {
		this.appointBorrowRate = appointBorrowRate;
	}
	public Long getAppointRepayDate() {
		return appointRepayDate;
	}
	public void setAppointRepayDate(Long appointRepayDate) {
		this.appointRepayDate = appointRepayDate;
	}
	public CapitalistTrustVo() {
		super();
	}

	public CapitalistTrustVo(CapitalistTrust capitalistTrust) {
		super(capitalistTrust);
	}

	public CapitalistTrustVo(CapitalistTrust capitalistTrust, String[] args, String[] simpleArgs) {
		super(capitalistTrust, args, simpleArgs);
	}

	public CapitalistTrust toPO() {
		CapitalistTrust po = new CapitalistTrust();
		return super.toPo(this, po);
	}
	
}
