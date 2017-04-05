package com.zdsoft.finance.cooperator.entity;

import java.math.BigDecimal;

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
 * @Title: CapitalistTrust.java
 * @ClassName: CapitalistTrust
 * @Description: 信托资方
 * @author liuwei
 * @date 2017年3月8日 上午9:55:51
 * @version V1.0
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
	@Column(precision = 18, scale = 6)
	private BigDecimal planInputCost = BigDecimal.ZERO;

	/**
	 * 约定的借款利率
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal appointBorrowRate = BigDecimal.ZERO;

	/**
	 * 约定的代偿时间
	 */
	@Column
	private Long appointRepayDate;

	/**
	 * 资方
	 */
	@ManyToOne
	@JoinColumn(name = "capitalistId")
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
