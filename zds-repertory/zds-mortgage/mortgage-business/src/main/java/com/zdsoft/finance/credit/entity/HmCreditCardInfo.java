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
 * @Title: HmCreditCardInfo.java
 * @ClassName: HmCreditCardInfo
 * @Description: 贷记卡概况
 * @author gufeng
 * @date 2017年2月23日 上午9:38:48
 * @version V1.0
 */
@Entity
@Table(name = "hm_Credit_Card_Info")
public class HmCreditCardInfo extends BaseEntity {

	private static final long serialVersionUID = 6001360147847181704L;
	/**
	 * 查询表主键Id
	 */
	@Column(name = "query_Id")
	private String queryId;
	/**
	 * 身份证号码
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
	 * 账号状态
	 */
	@Column(name = "Account_Status")
	private String accountStatus;
	/**
	 * 共享额度
	 */
	@Column(name = "Shared_Credit_Limit")
	private BigDecimal sharedCreditLimit = BigDecimal.ZERO;

	/**
	 * 已用额度
	 */
	@Column(name = "Used_Credit_Limit")
	private BigDecimal usedCreditLimit = BigDecimal.ZERO;
	/**
	 * 已用额度
	 */
	@Column(name = "Last6_Months_Ave")
	private BigDecimal last6MonthsAve = BigDecimal.ZERO;
	/**
	 * 最近6个月平均使用额度
	 */
	@Column(name = "Last6_Months_Ave_Used_Credit")
	private BigDecimal last6MonthsAveUsedCreditLimit = BigDecimal.ZERO;
	/**
	 * 最大使用额度
	 */
	@Column(name = "Max_Used_Credit_Limit")
	private BigDecimal maxUsedCreditLimit = BigDecimal.ZERO;
	/**
	 * 本月应还款
	 */
	@Column(name = "This_Month_Repay_Amount")
	private BigDecimal thisMonthRepayAmount = BigDecimal.ZERO;
	/**
	 * 账单日
	 */
	@Column(name = "Billing_Date")
	private String billingDate;
	/**
	 * 本月实还款
	 */
	@Column(name = "This_Month_Actual_Repay_Amount")
	private BigDecimal thisMonthActualRepayAmount = BigDecimal.ZERO;
	/**
	 * 最近一次还款日期
	 */
	@Column(name = "The_Lastest_Repay_Day")
	private String theLastestRepayDay;
	/**
	 * 当前逾期期数
	 */
	@Column(name = "Current_Overdue_Num")
	private Integer currentOverdueNum;
	/**
	 * 当前逾期金额
	 */
	@Column(name = "Current_Overdue_Amount")
	private BigDecimal currentOverdueAmount = BigDecimal.ZERO;
	/**
	 * 最近24个月还款记录标题
	 */
	@Column(name = "Month_24Repay_Title")
	private String month24RepayTitle;
	/**
	 * 最近24个月还款状态
	 */
	@Column(name = "Month_24Repay_Status")
	private String month_24Repay_Status;

	/**
	 * 逾期的前面的信息
	 */
	@Column(name = "Over_due_Tile")
	private String OverdueTile;

	/**
	 * 逾期月份1
	 */
	@Column(name = "Over_due_Month1")
	private String OverdueMonth1;

	/**
	 * 逾期持续月数1
	 */
	@Column(name = "Over_due_Month_Num1")
	private Integer OverdueMonthNum1;

	/**
	 * 逾期持续月数1
	 */
	@Column(name = "Over_due_Amount1")
	private BigDecimal OverdueAmount1 = BigDecimal.ZERO;

	/**
	 * 逾期月份2
	 */
	@Column(name = "Over_due_Month2")
	private String OverdueMonth2;

	/**
	 * 逾期持续月数2
	 */
	@Column(name = "Over_due_Month_Num2")
	private Integer OverdueMonthNum2;

	/**
	 * 逾期金额2
	 */
	@Column(name = "Over_due_Amount2")
	private BigDecimal OverdueAmount2 = BigDecimal.ZERO;

	// 缺少字段
	/**
	 * 最近24月还款状态
	 */
	@Column(name = "month24RepayStatus")
	private String month24RepayStatus;

	public String getMonth24RepayStatus() {
		return month24RepayStatus;
	}

	public void setMonth24RepayStatus(String month24RepayStatus) {
		this.month24RepayStatus = month24RepayStatus;
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

	public BigDecimal getUsedCreditLimit() {
		return usedCreditLimit;
	}

	public void setUsedCreditLimit(BigDecimal usedCreditLimit) {
		this.usedCreditLimit = usedCreditLimit;
	}

	public BigDecimal getLast6MonthsAveUsedCreditLimit() {
		return last6MonthsAveUsedCreditLimit;
	}

	public void setLast6MonthsAveUsedCreditLimit(BigDecimal last6MonthsAveUsedCreditLimit) {
		this.last6MonthsAveUsedCreditLimit = last6MonthsAveUsedCreditLimit;
	}

	public BigDecimal getMaxUsedCreditLimit() {
		return maxUsedCreditLimit;
	}

	public void setMaxUsedCreditLimit(BigDecimal maxUsedCreditLimit) {
		this.maxUsedCreditLimit = maxUsedCreditLimit;
	}

	public BigDecimal getThisMonthRepayAmount() {
		return thisMonthRepayAmount;
	}

	public void setThisMonthRepayAmount(BigDecimal thisMonthRepayAmount) {
		this.thisMonthRepayAmount = thisMonthRepayAmount;
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

	public Integer getCurrentOverdueNum() {
		return currentOverdueNum;
	}

	public void setCurrentOverdueNum(Integer currentOverdueNum) {
		this.currentOverdueNum = currentOverdueNum;
	}

	public BigDecimal getCurrentOverdueAmount() {
		return currentOverdueAmount;
	}

	public void setCurrentOverdueAmount(BigDecimal currentOverdueAmount) {
		this.currentOverdueAmount = currentOverdueAmount;
	}

	public String getMonth24RepayTitle() {
		return month24RepayTitle;
	}

	public void setMonth24RepayTitle(String month24RepayTitle) {
		this.month24RepayTitle = month24RepayTitle;
	}

	public String getMonth_24Repay_Status() {
		return month_24Repay_Status;
	}

	public void setMonth_24Repay_Status(String month_24Repay_Status) {
		this.month_24Repay_Status = month_24Repay_Status;
	}

	public BigDecimal getSharedCreditLimit() {
		return sharedCreditLimit;
	}

	public void setSharedCreditLimit(BigDecimal sharedCreditLimit) {
		this.sharedCreditLimit = sharedCreditLimit;
	}

	public BigDecimal getLast6MonthsAve() {
		return last6MonthsAve;
	}

	public void setLast6MonthsAve(BigDecimal last6MonthsAve) {
		this.last6MonthsAve = last6MonthsAve;
	}

	public String getOverdueTile() {
		return OverdueTile;
	}

	public void setOverdueTile(String overdueTile) {
		OverdueTile = overdueTile;
	}

	public String getOverdueMonth1() {
		return OverdueMonth1;
	}

	public void setOverdueMonth1(String overdueMonth1) {
		OverdueMonth1 = overdueMonth1;
	}

	public Integer getOverdueMonthNum1() {
		return OverdueMonthNum1;
	}

	public void setOverdueMonthNum1(Integer overdueMonthNum1) {
		OverdueMonthNum1 = overdueMonthNum1;
	}

	public BigDecimal getOverdueAmount1() {
		return OverdueAmount1;
	}

	public void setOverdueAmount1(BigDecimal overdueAmount1) {
		OverdueAmount1 = overdueAmount1;
	}

	public String getOverdueMonth2() {
		return OverdueMonth2;
	}

	public void setOverdueMonth2(String overdueMonth2) {
		OverdueMonth2 = overdueMonth2;
	}

	public Integer getOverdueMonthNum2() {
		return OverdueMonthNum2;
	}

	public void setOverdueMonthNum2(Integer overdueMonthNum2) {
		OverdueMonthNum2 = overdueMonthNum2;
	}

	public BigDecimal getOverdueAmount2() {
		return OverdueAmount2;
	}

	public void setOverdueAmount2(BigDecimal overdueAmount2) {
		OverdueAmount2 = overdueAmount2;
	}

}
