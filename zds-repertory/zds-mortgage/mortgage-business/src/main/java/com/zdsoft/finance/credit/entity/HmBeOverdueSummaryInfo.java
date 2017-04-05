package com.zdsoft.finance.credit.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: HmBeOverdueSummaryInfo.java
 * @ClassName: HmBeOverdueSummaryInfo
 * @Description: 逾期（透支）信息汇总
 * @author gufeng
 * @date 2017年2月23日 上午9:38:33
 * @version V1.0
 */
@Entity
@Table(name = "hm_Be_Over_due_Summary_Info")
public class HmBeOverdueSummaryInfo extends BaseEntity {

	private static final long serialVersionUID = 864461017687287818L;
	/**
	 * 查询表ID
	 */
	@Column(name = "query_Id")
	private String queryId;
	/**
	 * 身份证号
	 */
	@Column(name = "id_card")
	private String idCard;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Timestamp createTime;

	/**
	 * 贷款逾期_笔数
	 */
	@Column(name = "Loan_Over_due_Account_Num")
	private Integer loanOverdueAccountNum;
	/**
	 * 贷款逾期_月份数
	 */
	@Column(name = "Loan_Over_due_Month_Num")
	private Integer loanOverdueMonthNum;
	/**
	 * 贷款逾期_单月最高逾期总额
	 */
	@Column(name = "LoanOverdueHighestSinglAmount")
	private BigDecimal loanOverdueHighestSingleMothOverdueAmount = BigDecimal.ZERO;
	/**
	 * 贷款逾期_最长逾期月数
	 */
	@Column(name = "LoanOverdueLongOverdueMonths")
	private Integer loanOverdueLongOverdueMonths;

	/**
	 * 贷记卡逾_账户数
	 */
	@Column(name = "CreditCardOverdueAccountNum")
	private Integer creditCardOverdueAccountNum;
	/**
	 * 贷记卡逾_月份数
	 */
	@Column(name = "CreditCardOverdueMothNum")
	private Integer creditCardOverdueMothNum;
	/**
	 * 贷记卡逾_单月最高逾期总额
	 */
	@Column(name = "CreditCardOvMaxSingMothAmount")
	private BigDecimal creditCardOverdueMaxSingleMothOverdueAmount = BigDecimal.ZERO;
	/**
	 * 贷记卡逾_最长逾期月数
	 */
	@Column(name = "CreditCardOvraftLongOveMonths")
	private Integer creditCardOverdraftLongOverdueMonths;
	/**
	 * 准贷记卡60天以上透支_账户数
	 */
	@Column(name = "QuasiCreditCardOveAccNum")
	private Integer quasiCreditCardOverdraftAccountNum;
	/**
	 * 准贷记卡60天以上透支_月份数
	 */
	@Column(name = "QuasiCreditCardOveDaysMothNum")
	private Integer quasiCreditCardOverdraftDaysMothNum;
	/**
	 * 准贷记卡60天以上透支_单月最高透支余额
	 */
	@Column(name = "QuasiCreditCardMaxMothAmount")
	private BigDecimal quasiCreditCardMaxSingleMothOverdueAmount = BigDecimal.ZERO;
	/**
	 * 准贷记卡60天以上透支_最长透支月数
	 */
	@Column(name = "QuasiCreditCardOvMonths")
	private Integer quasiCreditCardOverdueLongOverdueMonths;
	// 缺少字段
	/**
	 * 资产处置总额
	 */
	@Column(name = "assetsdisposalinfosumamoun")
	private BigDecimal assetsDisposalInfoSumAmount = BigDecimal.ZERO;
	/**
	 * 透资时间数
	 */
	@Column(name = "quasicreditcardodrafdaysm")
	private Integer quasiCreditCardOverdraft0DaysMothNum;

	public Integer getQuasiCreditCardOverdraft0DaysMothNum() {
		return quasiCreditCardOverdraft0DaysMothNum;
	}

	public void setQuasiCreditCardOverdraft0DaysMothNum(Integer quasiCreditCardOverdraft0DaysMothNum) {
		this.quasiCreditCardOverdraft0DaysMothNum = quasiCreditCardOverdraft0DaysMothNum;
	}

	public BigDecimal getAssetsDisposalInfoSumAmount() {
		return assetsDisposalInfoSumAmount;
	}

	public void setAssetsDisposalInfoSumAmount(BigDecimal assetsDisposalInfoSumAmount) {
		this.assetsDisposalInfoSumAmount = assetsDisposalInfoSumAmount;
	}

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getLoanOverdueAccountNum() {
		return loanOverdueAccountNum;
	}

	public void setLoanOverdueAccountNum(Integer loanOverdueAccountNum) {
		this.loanOverdueAccountNum = loanOverdueAccountNum;
	}

	public Integer getLoanOverdueMonthNum() {
		return loanOverdueMonthNum;
	}

	public void setLoanOverdueMonthNum(Integer loanOverdueMonthNum) {
		this.loanOverdueMonthNum = loanOverdueMonthNum;
	}

	public BigDecimal getLoanOverdueHighestSingleMothOverdueAmount() {
		return loanOverdueHighestSingleMothOverdueAmount;
	}

	public void setLoanOverdueHighestSingleMothOverdueAmount(BigDecimal loanOverdueHighestSingleMothOverdueAmount) {
		this.loanOverdueHighestSingleMothOverdueAmount = loanOverdueHighestSingleMothOverdueAmount;
	}

	public Integer getLoanOverdueLongOverdueMonths() {
		return loanOverdueLongOverdueMonths;
	}

	public void setLoanOverdueLongOverdueMonths(Integer loanOverdueLongOverdueMonths) {
		this.loanOverdueLongOverdueMonths = loanOverdueLongOverdueMonths;
	}

	public Integer getCreditCardOverdueAccountNum() {
		return creditCardOverdueAccountNum;
	}

	public void setCreditCardOverdueAccountNum(Integer creditCardOverdueAccountNum) {
		this.creditCardOverdueAccountNum = creditCardOverdueAccountNum;
	}

	public Integer getCreditCardOverdueMothNum() {
		return creditCardOverdueMothNum;
	}

	public void setCreditCardOverdueMothNum(Integer creditCardOverdueMothNum) {
		this.creditCardOverdueMothNum = creditCardOverdueMothNum;
	}

	public BigDecimal getCreditCardOverdueMaxSingleMothOverdueAmount() {
		return creditCardOverdueMaxSingleMothOverdueAmount;
	}

	public void setCreditCardOverdueMaxSingleMothOverdueAmount(BigDecimal creditCardOverdueMaxSingleMothOverdueAmount) {
		this.creditCardOverdueMaxSingleMothOverdueAmount = creditCardOverdueMaxSingleMothOverdueAmount;
	}

	public Integer getCreditCardOverdraftLongOverdueMonths() {
		return creditCardOverdraftLongOverdueMonths;
	}

	public void setCreditCardOverdraftLongOverdueMonths(Integer creditCardOverdraftLongOverdueMonths) {
		this.creditCardOverdraftLongOverdueMonths = creditCardOverdraftLongOverdueMonths;
	}

	public Integer getQuasiCreditCardOverdraftAccountNum() {
		return quasiCreditCardOverdraftAccountNum;
	}

	public void setQuasiCreditCardOverdraftAccountNum(Integer quasiCreditCardOverdraftAccountNum) {
		this.quasiCreditCardOverdraftAccountNum = quasiCreditCardOverdraftAccountNum;
	}

	public Integer getQuasiCreditCardOverdraftDaysMothNum() {
		return quasiCreditCardOverdraftDaysMothNum;
	}

	public void setQuasiCreditCardOverdraftDaysMothNum(Integer quasiCreditCardOverdraftDaysMothNum) {
		this.quasiCreditCardOverdraftDaysMothNum = quasiCreditCardOverdraftDaysMothNum;
	}

	public BigDecimal getQuasiCreditCardMaxSingleMothOverdueAmount() {
		return quasiCreditCardMaxSingleMothOverdueAmount;
	}

	public void setQuasiCreditCardMaxSingleMothOverdueAmount(BigDecimal quasiCreditCardMaxSingleMothOverdueAmount) {
		this.quasiCreditCardMaxSingleMothOverdueAmount = quasiCreditCardMaxSingleMothOverdueAmount;
	}

	public Integer getQuasiCreditCardOverdueLongOverdueMonths() {
		return quasiCreditCardOverdueLongOverdueMonths;
	}

	public void setQuasiCreditCardOverdueLongOverdueMonths(Integer quasiCreditCardOverdueLongOverdueMonths) {
		this.quasiCreditCardOverdueLongOverdueMonths = quasiCreditCardOverdueLongOverdueMonths;
	}

}
