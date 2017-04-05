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
 * @Title: HmQuasiCreditCard.java
 * @ClassName: HmQuasiCreditCard
 * @Description: 贷记卡信息表
 * @author gufeng
 * @date 2017年2月23日 上午9:41:14
 * @version V1.0
 */
@Entity
@Table(name = "hm_Quasi_Credit_Card")
public class HmQuasiCreditCard extends BaseEntity {

	private static final long serialVersionUID = 8225361276687974977L;
	/**
	 * 查询表ID
	 */
	@Column(name = "query_Id")
	private String queryId;
	/**
	 * 查询人身份证号码
	 */
	@Column(name = "id_card")
	private String idCard;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Timestamp createTime;
	/**
	 * 标题
	 */
	@Column(name = "Title_Info")
	private String titleInfo;
	/**
	 * 账户状态
	 */
	@Column(name = "Account_Status")
	private String accountStatus;

	/**
	 * 共享额度
	 */
	@Column(name = "Shared_Credit_Limit")
	private BigDecimal sharedCreditLimit = BigDecimal.ZERO;

	/**
	 * 透支余额
	 */
	@Column(name = "Overdraft_Balance")
	private BigDecimal overdraftBalance = BigDecimal.ZERO;

	/**
	 * 最近 6个月平均透支余额
	 */
	@Column(name = "Last6_Months_Ave_Over_Balan")
	private BigDecimal last6MonthsAveOverBalan = BigDecimal.ZERO;

	/**
	 * 最大透支余额
	 */
	@Column(name = "Max_Over_Balan")
	private BigDecimal maxOverBalan = BigDecimal.ZERO;

	/**
	 * 账单日
	 */
	@Column(name = "Billing_Date")
	private String billingDate;

	/**
	 * 最近一次还款日期
	 */
	@Column(name = "The_Lastest_Repay_Day")
	private String theLastestRepayDay;

	/**
	 * 透支 180 天以上未付余额
	 */
	@Column(name = "Over_Over_180_Unpaid_Balan")
	private BigDecimal overOver180UnpaidBalan = BigDecimal.ZERO;

	/**
	 * 24 个月（账户）还款记录前面的信 息
	 */
	@Column(name = "Month_24_Repay_Title")
	private String month24RepayTitle;

	/**
	 * 24 个月（账户）还款记录状态
	 */
	@Column(name = "Month_24Repay_Status")
	private String month24RepayStatus;

	/**
	 * 逾期的前面的信息（和贷记卡一样可 能有
	 */
	@Column(name = "Over_due_Tile")
	private String overdueTile;

	/**
	 * 逾期月份1（和贷记卡一样可能有
	 */
	@Column(name = "Over_due_Month1")
	private String overdueMonth1;

	/**
	 * 逾期持续月数1（和贷记卡一样可能 有）
	 */
	@Column(name = "Over_due_Month_Num1")
	private Integer overdueMonthNum1;

	/**
	 * 逾期金额1（和贷记卡一样可能有
	 */
	@Column(name = "Over_due_Amount1")
	private BigDecimal overdueAmount1 = BigDecimal.ZERO;

	/**
	 * 逾期月份2（和贷记卡一样可能有
	 */
	@Column(name = "Over_due_Month2")
	private String overdueMonth2;

	/**
	 * 逾期持续月数2（和贷记卡一样可能 有
	 */
	@Column(name = "Over_due_Month_Num2")
	private Integer overdueMonthNum2;

	/**
	 * 逾期金额2（和贷记卡一样可能有
	 */
	@Column(name = "Over_due_Amount2")
	private BigDecimal overdueAmount2 = BigDecimal.ZERO;

	/**
	 * 本月实还款
	 */
	@Column(name = "This_Month_Actual_Repay_Amount")
	private BigDecimal thisMonthActualRepayAmount = BigDecimal.ZERO;

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

	public String getTitleInfo() {
		return titleInfo;
	}

	public void setTitleInfo(String titleInfo) {
		this.titleInfo = titleInfo;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public BigDecimal getSharedCreditLimit() {
		return sharedCreditLimit;
	}

	public void setSharedCreditLimit(BigDecimal sharedCreditLimit) {
		this.sharedCreditLimit = sharedCreditLimit;
	}

	public BigDecimal getOverdraftBalance() {
		return overdraftBalance;
	}

	public void setOverdraftBalance(BigDecimal overdraftBalance) {
		this.overdraftBalance = overdraftBalance;
	}

	public BigDecimal getLast6MonthsAveOverBalan() {
		return last6MonthsAveOverBalan;
	}

	public void setLast6MonthsAveOverBalan(BigDecimal last6MonthsAveOverBalan) {
		this.last6MonthsAveOverBalan = last6MonthsAveOverBalan;
	}

	public BigDecimal getMaxOverBalan() {
		return maxOverBalan;
	}

	public void setMaxOverBalan(BigDecimal maxOverBalan) {
		this.maxOverBalan = maxOverBalan;
	}

	public String getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(String billingDate) {
		this.billingDate = billingDate;
	}

	public BigDecimal getThisMonthActualRepayAmount() {
		return thisMonthActualRepayAmount;
	}

	public void setThisMonthActualRepayAmount(BigDecimal thisMonthActualRepayAmount) {
		this.thisMonthActualRepayAmount = thisMonthActualRepayAmount;
	}

	public String getTheLastestRepayDay() {
		return theLastestRepayDay;
	}

	public void setTheLastestRepayDay(String theLastestRepayDay) {
		this.theLastestRepayDay = theLastestRepayDay;
	}

	public BigDecimal getOverOver180UnpaidBalan() {
		return overOver180UnpaidBalan;
	}

	public void setOverOver180UnpaidBalan(BigDecimal overOver180UnpaidBalan) {
		this.overOver180UnpaidBalan = overOver180UnpaidBalan;
	}

	public String getMonth24RepayTitle() {
		return month24RepayTitle;
	}

	public void setMonth24RepayTitle(String month24RepayTitle) {
		this.month24RepayTitle = month24RepayTitle;
	}

	public String getMonth24RepayStatus() {
		return month24RepayStatus;
	}

	public void setMonth24RepayStatus(String month24RepayStatus) {
		this.month24RepayStatus = month24RepayStatus;
	}

	public String getOverdueTile() {
		return overdueTile;
	}

	public void setOverdueTile(String overdueTile) {
		this.overdueTile = overdueTile;
	}

	public String getOverdueMonth1() {
		return overdueMonth1;
	}

	public void setOverdueMonth1(String overdueMonth1) {
		this.overdueMonth1 = overdueMonth1;
	}

	public Integer getOverdueMonthNum1() {
		return overdueMonthNum1;
	}

	public void setOverdueMonthNum1(Integer overdueMonthNum1) {
		this.overdueMonthNum1 = overdueMonthNum1;
	}

	public BigDecimal getOverdueAmount1() {
		return overdueAmount1;
	}

	public void setOverdueAmount1(BigDecimal overdueAmount1) {
		this.overdueAmount1 = overdueAmount1;
	}

	public String getOverdueMonth2() {
		return overdueMonth2;
	}

	public void setOverdueMonth2(String overdueMonth2) {
		this.overdueMonth2 = overdueMonth2;
	}

	public Integer getOverdueMonthNum2() {
		return overdueMonthNum2;
	}

	public void setOverdueMonthNum2(Integer overdueMonthNum2) {
		this.overdueMonthNum2 = overdueMonthNum2;
	}

	public BigDecimal getOverdueAmount2() {
		return overdueAmount2;
	}

	public void setOverdueAmount2(BigDecimal overdueAmount2) {
		this.overdueAmount2 = overdueAmount2;
	}

}
