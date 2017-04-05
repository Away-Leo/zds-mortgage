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
 * @Title: HmNoCancelCreditCardSummaryInfo.java
 * @ClassName: HmNoCancelCreditCardSummaryInfo
 * @Description: 未销户贷记卡信息汇总
 * @author gufeng
 * @date 2017年2月23日 上午9:39:53
 * @version V1.0
 */
@Entity
@Table(name = "hm_No_Cancel_Credit_Card_Summa")
public class HmNoCancelCreditCardSummaryInfo extends BaseEntity {

	private static final long serialVersionUID = -2593927833776071556L;
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
	 * 发卡法人机构数
	 */
	@Column(name = "Hairpin_Legal_Org_Num")
	private Integer hairpinLegalOrgNum;
	/**
	 * 发卡机构数
	 */
	@Column(name = "Hairpin_Org_Num")
	private Integer hairpinOrgNum;

	/**
	 * 账户数
	 */
	@Column(name = "Account_Num")
	private Integer accountNum;
	/**
	 * 授信总额
	 */
	@Column(name = "Finance_Profits")
	private BigDecimal financeProfits = BigDecimal.ZERO;
	/**
	 * 单家行最高授信额
	 */
	@Column(name = "Single_Bank_Max_Finance_Limit")
	private BigDecimal singleBankMaxFinanceLimit = BigDecimal.ZERO;
	/**
	 * 单家行最低授信额
	 */
	@Column(name = "Single_Bank_Min_Finance_Limit")
	private BigDecimal singleBankMinFinanceLimit = BigDecimal.ZERO;
	/**
	 * 已用额度
	 */
	@Column(name = "Used_Credit_Limit")
	private BigDecimal usedCreditLimit = BigDecimal.ZERO;
	/**
	 * 最近6个月平均使用额度
	 */
	@Column(name = "Last6_Months_Avg_Use_Limit")
	private BigDecimal last6MonthsAvgUseLimit = BigDecimal.ZERO;

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

	public Integer getHairpinLegalOrgNum() {
		return hairpinLegalOrgNum;
	}

	public void setHairpinLegalOrgNum(Integer hairpinLegalOrgNum) {
		this.hairpinLegalOrgNum = hairpinLegalOrgNum;
	}

	public Integer getHairpinOrgNum() {
		return hairpinOrgNum;
	}

	public void setHairpinOrgNum(Integer hairpinOrgNum) {
		this.hairpinOrgNum = hairpinOrgNum;
	}

	public Integer getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(Integer accountNum) {
		this.accountNum = accountNum;
	}

	public BigDecimal getFinanceProfits() {
		return financeProfits;
	}

	public void setFinanceProfits(BigDecimal financeProfits) {
		this.financeProfits = financeProfits;
	}

	public BigDecimal getSingleBankMaxFinanceLimit() {
		return singleBankMaxFinanceLimit;
	}

	public void setSingleBankMaxFinanceLimit(BigDecimal singleBankMaxFinanceLimit) {
		this.singleBankMaxFinanceLimit = singleBankMaxFinanceLimit;
	}

	public BigDecimal getSingleBankMinFinanceLimit() {
		return singleBankMinFinanceLimit;
	}

	public void setSingleBankMinFinanceLimit(BigDecimal singleBankMinFinanceLimit) {
		this.singleBankMinFinanceLimit = singleBankMinFinanceLimit;
	}

	public BigDecimal getUsedCreditLimit() {
		return usedCreditLimit;
	}

	public void setUsedCreditLimit(BigDecimal usedCreditLimit) {
		this.usedCreditLimit = usedCreditLimit;
	}

	public BigDecimal getLast6MonthsAvgUseLimit() {
		return last6MonthsAvgUseLimit;
	}

	public void setLast6MonthsAvgUseLimit(BigDecimal last6MonthsAvgUseLimit) {
		this.last6MonthsAvgUseLimit = last6MonthsAvgUseLimit;
	}

}
