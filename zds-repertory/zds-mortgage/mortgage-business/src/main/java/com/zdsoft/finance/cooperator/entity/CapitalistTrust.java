package com.zdsoft.finance.cooperator.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 信托资方
 * 
 * @author Hisa
 *
 */
@Entity
@Table(name = "cpt_capitalist_trust")
public class CapitalistTrust extends BaseEntity {
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
	

	private Capitalist capitalist;

	public Capitalist getCapitalist() {
		return capitalist;
	}

	public void setCapitalist(Capitalist capitalist) {
		this.capitalist = capitalist;
	}

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
}
