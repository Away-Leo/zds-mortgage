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
 * @Title: HmLoanInfo.java
 * @ClassName: HmLoanInfo
 * @Description: 贷款信息表
 * @author gufeng
 * @date 2017年2月23日 上午9:39:42
 * @version V1.0
 */
@Entity
@Table(name = "hm_Loan_Info")
public class HmLoanInfo extends BaseEntity {

	private static final long serialVersionUID = 693261300014347454L;
	/**
	 * 查询表ID
	 */
	@Column(name = "query_Id")
	private String queryId;
	/**
	 * 证件号码
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
	@Column(name = "title_Info")
	private String titleInfo;

	/**
	 * 账户状态
	 */
	@Column(name = "Account_Status")
	private String accountStatus;

	/**
	 * 五级分类
	 */
	@Column(name = "Fivecl_asscode")
	private String fiveclasscode;

	/**
	 * 本金余额
	 */
	@Column(name = "Principal_Amount")
	private BigDecimal principalAmount = BigDecimal.ZERO;

	/**
	 * 剩余还款期数
	 */
	@Column(name = "Remain_Repay_Num")
	private Integer remainRepayNum;

	/**
	 * 本月应还款金额
	 */
	@Column(name = "This_Month_Repay_Amount")
	private BigDecimal ThisMonthRepayAmount = BigDecimal.ZERO;

	/**
	 * 本月应还款日期
	 */
	@Column(name = "This_Month_Repay_Day")
	private String thisMonthRepayDay;

	/**
	 * 本月实际还款金额
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
	 * 逾期 31-60 天未还本金
	 */
	@Column(name = "Overdue_31To60_Days")
	private BigDecimal overdue31To60Days = BigDecimal.ZERO;

	/**
	 * 逾期 61－90 天未还本金
	 */
	@Column(name = "Overdue_61To90_Days")
	private BigDecimal overdue61To90Days = BigDecimal.ZERO;

	/**
	 * 逾期 91－180 天未还本金
	 */
	@Column(name = "Overdue_91To180_Days")
	private BigDecimal overdue91To180Days = BigDecimal.ZERO;

	/**
	 * 逾期 180 天以上未还本金
	 */
	@Column(name = "Overdue_180_Days")
	private BigDecimal overdue180Days = BigDecimal.ZERO;

	/**
	 * 24 个月（账户）还款前面的信息
	 */
	@Column(name = "Month_24Repay_Title")
	private String month24RepayTitle;

	/**
	 * 24 个月（账户）还款状态
	 */
	@Column(name = "Month_24Repay_Status")
	private String month24RepayStatus;

	/**
	 * 特殊交易类型
	 */
	@Column(name = "Spec_Tra_Type")
	private String specTraType;

	/**
	 * 特殊交易发生日期
	 */
	@Column(name = "Spec_Tra_Date")
	private String specTraDate;

	/**
	 * 特殊交易变更月数
	 */
	@Column(name = "Spec_TraChan_Month_Num")
	private Integer SpecTraChanMonthNum;

	/**
	 * 特殊交易发生金额
	 */
	@Column(name = "Spec_Tra_Amount")
	private BigDecimal specTraAmount;

	/**
	 * 特殊交易明细记录
	 */
	@Column(name = "spec_Tra_Detailed")
	private String specTraDetailed;

	/**
	 * 逾期标题1
	 */
	@Column(name = "Over_due_Title1")
	private String overdueTitle1;

	/**
	 * 逾期月份1
	 */
	@Column(name = "Over_due_Month1")
	private String overdueMonth1;

	/**
	 * 逾期持续月数1
	 */
	@Column(name = "Over_due_Cont_Months1")
	private String overdueContMonths1;

	/**
	 * 逾期标题1
	 */
	@Column(name = "Over_due_Amount1")
	private BigDecimal overdueAmount1 = BigDecimal.ZERO;

	/**
	 * 逾期标题2
	 */
	@Column(name = "Overdue_Title2")
	private String overdueTitle2;

	/**
	 * 逾期月份2
	 */
	@Column(name = "Overdue_Month2")
	private String overdueMonth2;

	/**
	 * 逾期持续月数2
	 */
	@Column(name = "Overdue_Cont_Months2")
	private String overdueContMonths2;

	/**
	 * 逾期金额2
	 */
	@Column(name = "Overdue_Amount2")
	private BigDecimal overdueAmount2 = BigDecimal.ZERO;

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

	public String getFiveclasscode() {
		return fiveclasscode;
	}

	public void setFiveclasscode(String fiveclasscode) {
		this.fiveclasscode = fiveclasscode;
	}

	public BigDecimal getPrincipalAmount() {
		return principalAmount;
	}

	public void setPrincipalAmount(BigDecimal principalAmount) {
		this.principalAmount = principalAmount;
	}

	public Integer getRemainRepayNum() {
		return remainRepayNum;
	}

	public void setRemainRepayNum(Integer remainRepayNum) {
		this.remainRepayNum = remainRepayNum;
	}

	public BigDecimal getThisMonthRepayAmount() {
		return ThisMonthRepayAmount;
	}

	public void setThisMonthRepayAmount(BigDecimal thisMonthRepayAmount) {
		ThisMonthRepayAmount = thisMonthRepayAmount;
	}

	public String getThisMonthRepayDay() {
		return thisMonthRepayDay;
	}

	public void setThisMonthRepayDay(String thisMonthRepayDay) {
		this.thisMonthRepayDay = thisMonthRepayDay;
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

	public BigDecimal getOverdue31To60Days() {
		return overdue31To60Days;
	}

	public void setOverdue31To60Days(BigDecimal overdue31To60Days) {
		this.overdue31To60Days = overdue31To60Days;
	}

	public BigDecimal getOverdue61To90Days() {
		return overdue61To90Days;
	}

	public void setOverdue61To90Days(BigDecimal overdue61To90Days) {
		this.overdue61To90Days = overdue61To90Days;
	}

	public BigDecimal getOverdue91To180Days() {
		return overdue91To180Days;
	}

	public void setOverdue91To180Days(BigDecimal overdue91To180Days) {
		this.overdue91To180Days = overdue91To180Days;
	}

	public BigDecimal getOverdue180Days() {
		return overdue180Days;
	}

	public void setOverdue180Days(BigDecimal overdue180Days) {
		this.overdue180Days = overdue180Days;
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

	public String getSpecTraType() {
		return specTraType;
	}

	public void setSpecTraType(String specTraType) {
		this.specTraType = specTraType;
	}

	public String getSpecTraDate() {
		return specTraDate;
	}

	public void setSpecTraDate(String specTraDate) {
		this.specTraDate = specTraDate;
	}

	public Integer getSpecTraChanMonthNum() {
		return SpecTraChanMonthNum;
	}

	public void setSpecTraChanMonthNum(Integer specTraChanMonthNum) {
		SpecTraChanMonthNum = specTraChanMonthNum;
	}

	public BigDecimal getSpecTraAmount() {
		return specTraAmount;
	}

	public void setSpecTraAmount(BigDecimal specTraAmount) {
		this.specTraAmount = specTraAmount;
	}

	public String getSpecTraDetailed() {
		return specTraDetailed;
	}

	public void setSpecTraDetailed(String specTraDetailed) {
		this.specTraDetailed = specTraDetailed;
	}

	public String getOverdueTitle1() {
		return overdueTitle1;
	}

	public void setOverdueTitle1(String overdueTitle1) {
		this.overdueTitle1 = overdueTitle1;
	}

	public String getOverdueMonth1() {
		return overdueMonth1;
	}

	public void setOverdueMonth1(String overdueMonth1) {
		this.overdueMonth1 = overdueMonth1;
	}

	public String getOverdueContMonths1() {
		return overdueContMonths1;
	}

	public void setOverdueContMonths1(String overdueContMonths1) {
		this.overdueContMonths1 = overdueContMonths1;
	}

	public BigDecimal getOverdueAmount1() {
		return overdueAmount1;
	}

	public void setOverdueAmount1(BigDecimal overdueAmount1) {
		this.overdueAmount1 = overdueAmount1;
	}

	public String getOverdueTitle2() {
		return overdueTitle2;
	}

	public void setOverdueTitle2(String overdueTitle2) {
		this.overdueTitle2 = overdueTitle2;
	}

	public String getOverdueMonth2() {
		return overdueMonth2;
	}

	public void setOverdueMonth2(String overdueMonth2) {
		this.overdueMonth2 = overdueMonth2;
	}

	public String getOverdueContMonths2() {
		return overdueContMonths2;
	}

	public void setOverdueContMonths2(String overdueContMonths2) {
		this.overdueContMonths2 = overdueContMonths2;
	}

	public BigDecimal getOverdueAmount2() {
		return overdueAmount2;
	}

	public void setOverdueAmount2(BigDecimal overdueAmount2) {
		this.overdueAmount2 = overdueAmount2;
	}

}
