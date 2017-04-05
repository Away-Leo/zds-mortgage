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
 * @Title: HmNoCAQuasiCreditCardInfoSum.java
 * @ClassName: HmNoCAQuasiCreditCardInfoSum
 * @Description: 未销户准贷记卡信息汇总
 * @author gufeng
 * @date 2017年2月23日 上午9:40:00
 * @version V1.0
 */
@Entity
@Table(name = "hm_No_CAQuasi_Credit_Card_Info")
public class HmNoCAQuasiCreditCardInfoSum extends BaseEntity {

	private static final long serialVersionUID = -4731315662394572994L;
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
	@Column(name = "Send_Card_Legal_Person_Org_Nu")
	private Integer sendCardLegalPersonOrgNum;
	/**
	 * 发卡机构数
	 */
	@Column(name = "Send_Card_Org_Num")
	private Integer sendCardOrgNum;

	/**
	 * 账户数
	 */
	@Column(name = "Account_Number")
	private Integer accountNumber;
	/**
	 * 授信额度
	 */
	@Column(name = "Credit_Total_Amount")
	private BigDecimal creditTotalAmount = BigDecimal.ZERO;
	/**
	 * 单家行最高授信额
	 */
	@Column(name = "Alone_Bank_Highest_Credit_Amo")
	private BigDecimal aloneBankHighestCreditAmount = BigDecimal.ZERO;
	/**
	 * 单家行最低授信额
	 */
	@Column(name = "Alone_Bank_Lowest_Credit_Amo")
	private BigDecimal aloneBankLowestCreditAmount = BigDecimal.ZERO;
	/**
	 * 透支余额
	 */
	@Column(name = "Overdraft_Amount")
	private BigDecimal overdraftAmount = BigDecimal.ZERO;
	/**
	 * 最近6个月平均透支余额
	 */
	@Column(name = "Recent6_Month_Ave_Overd_Amo")
	private BigDecimal recent6MonthAveOverdAmount = BigDecimal.ZERO;

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

	public Integer getSendCardLegalPersonOrgNum() {
		return sendCardLegalPersonOrgNum;
	}

	public void setSendCardLegalPersonOrgNum(Integer sendCardLegalPersonOrgNum) {
		this.sendCardLegalPersonOrgNum = sendCardLegalPersonOrgNum;
	}

	public Integer getSendCardOrgNum() {
		return sendCardOrgNum;
	}

	public void setSendCardOrgNum(Integer sendCardOrgNum) {
		this.sendCardOrgNum = sendCardOrgNum;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getCreditTotalAmount() {
		return creditTotalAmount;
	}

	public void setCreditTotalAmount(BigDecimal creditTotalAmount) {
		this.creditTotalAmount = creditTotalAmount;
	}

	public BigDecimal getAloneBankHighestCreditAmount() {
		return aloneBankHighestCreditAmount;
	}

	public void setAloneBankHighestCreditAmount(BigDecimal aloneBankHighestCreditAmount) {
		this.aloneBankHighestCreditAmount = aloneBankHighestCreditAmount;
	}

	public BigDecimal getAloneBankLowestCreditAmount() {
		return aloneBankLowestCreditAmount;
	}

	public void setAloneBankLowestCreditAmount(BigDecimal aloneBankLowestCreditAmount) {
		this.aloneBankLowestCreditAmount = aloneBankLowestCreditAmount;
	}

	public BigDecimal getOverdraftAmount() {
		return overdraftAmount;
	}

	public void setOverdraftAmount(BigDecimal overdraftAmount) {
		this.overdraftAmount = overdraftAmount;
	}

	public BigDecimal getRecent6MonthAveOverdAmount() {
		return recent6MonthAveOverdAmount;
	}

	public void setRecent6MonthAveOverdAmount(BigDecimal recent6MonthAveOverdAmount) {
		this.recent6MonthAveOverdAmount = recent6MonthAveOverdAmount;
	}

}
