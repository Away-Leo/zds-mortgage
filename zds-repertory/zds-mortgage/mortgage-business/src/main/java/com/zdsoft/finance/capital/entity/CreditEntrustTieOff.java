package com.zdsoft.finance.capital.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CreditEntrustTieOff.java
 * @ClassName: CreditEntrustTieOff
 * @Description: 信托计划扎帐统计表
 * @author liuwei
 * @date 2017年2月16日 下午8:22:05
 * @version V1.0
 */
@Entity
@Table(name = "cptl_credit_entrust_tieoff")
public class CreditEntrustTieOff extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -2051827138975580634L;

	/**
	 * 未分配备付金
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal notEquippedPay = BigDecimal.ZERO;

	/**
	 * 账面余额
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal bookBalance = BigDecimal.ZERO;

	/**
	 * 累计收回本金
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal cumulativeRecoveryPrincipal = BigDecimal.ZERO;

	/**
	 * 累计收回利息
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal cumulativeRecoveryInterest = BigDecimal.ZERO;

	/**
	 * 累计收回罚息
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal cumulativeRecoveryPenalty = BigDecimal.ZERO;

	/**
	 * 累计收回违约金
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal cumulativeRecoveryLiqDamages = BigDecimal.ZERO;

	/**
	 * 扎帐日期
	 */
	@Column
	private Long tieOffDate;

	/**
	 * 信托计划id
	 */
	@Column(length = 32)
	private String creditEntrustId;

	public BigDecimal getNotEquippedPay() {
		return notEquippedPay;
	}

	public void setNotEquippedPay(BigDecimal notEquippedPay) {
		this.notEquippedPay = notEquippedPay;
	}

	public BigDecimal getBookBalance() {
		return bookBalance;
	}

	public void setBookBalance(BigDecimal bookBalance) {
		this.bookBalance = bookBalance;
	}

	public BigDecimal getCumulativeRecoveryPrincipal() {
		return cumulativeRecoveryPrincipal;
	}

	public void setCumulativeRecoveryPrincipal(BigDecimal cumulativeRecoveryPrincipal) {
		this.cumulativeRecoveryPrincipal = cumulativeRecoveryPrincipal;
	}

	public BigDecimal getCumulativeRecoveryInterest() {
		return cumulativeRecoveryInterest;
	}

	public void setCumulativeRecoveryInterest(BigDecimal cumulativeRecoveryInterest) {
		this.cumulativeRecoveryInterest = cumulativeRecoveryInterest;
	}

	public BigDecimal getCumulativeRecoveryPenalty() {
		return cumulativeRecoveryPenalty;
	}

	public void setCumulativeRecoveryPenalty(BigDecimal cumulativeRecoveryPenalty) {
		this.cumulativeRecoveryPenalty = cumulativeRecoveryPenalty;
	}

	public BigDecimal getCumulativeRecoveryLiqDamages() {
		return cumulativeRecoveryLiqDamages;
	}

	public void setCumulativeRecoveryLiqDamages(BigDecimal cumulativeRecoveryLiqDamages) {
		this.cumulativeRecoveryLiqDamages = cumulativeRecoveryLiqDamages;
	}

	public Long getTieOffDate() {
		return tieOffDate;
	}

	public void setTieOffDate(Long tieOffDate) {
		this.tieOffDate = tieOffDate;
	}

	public String getCreditEntrustId() {
		return creditEntrustId;
	}

	public void setCreditEntrustId(String creditEntrustId) {
		this.creditEntrustId = creditEntrustId;
	}

}
