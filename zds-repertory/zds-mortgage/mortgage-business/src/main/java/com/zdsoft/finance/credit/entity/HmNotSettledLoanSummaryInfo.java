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
 * @Title: HmNotSettledLoanSummaryInfo.java
 * @ClassName: HmNotSettledLoanSummaryInfo
 * @Description: 未结清贷款信息汇总
 * @author gufeng
 * @date 2017年2月23日 上午9:40:06
 * @version V1.0
 */
@Entity
@Table(name = "hm_not_settled_loan_summaryinf")
public class HmNotSettledLoanSummaryInfo extends BaseEntity {

	private static final long serialVersionUID = 4161180147037968817L;
	/**
	 * 查询表ID
	 */
	@Column(name = "query_Id")
	private String queryId;
	/**
	 * 证件号
	 */
	@Column(name = "id_card")
	private String idCard;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Timestamp createTime;

	/**
	 * 贷款法人机构数
	 */
	@Column(name = "Loan_Legal_Org_Num")
	private Integer loanLegalOrgNum;

	/**
	 * 贷款机构数
	 */
	@Column(name = "Loan_Org_Num")
	private Integer loanOrgNum;

	/**
	 * 笔数
	 */
	@Column(name = "Count_Num")
	private Integer countNum;

	/**
	 * 合同总额
	 */
	@Column(name = "Contract_Profits")
	private BigDecimal contractProfits = BigDecimal.ZERO;

	/**
	 * 最近 6个月平均应还款
	 */
	@Column(name = "Last6_Moths_Avg_Repay_Amount")
	private BigDecimal last6MothsAvgRepayAmount = BigDecimal.ZERO;
	/**
	 * 余额
	 */
	@Column(name = "balance")
	private BigDecimal balance = BigDecimal.ZERO;

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

	public Integer getLoanLegalOrgNum() {
		return loanLegalOrgNum;
	}

	public void setLoanLegalOrgNum(Integer loanLegalOrgNum) {
		this.loanLegalOrgNum = loanLegalOrgNum;
	}

	public Integer getLoanOrgNum() {
		return loanOrgNum;
	}

	public void setLoanOrgNum(Integer loanOrgNum) {
		this.loanOrgNum = loanOrgNum;
	}

	public Integer getCountNum() {
		return countNum;
	}

	public void setCountNum(Integer countNum) {
		this.countNum = countNum;
	}

	public BigDecimal getContractProfits() {
		return contractProfits;
	}

	public void setContractProfits(BigDecimal contractProfits) {
		this.contractProfits = contractProfits;
	}

	public BigDecimal getLast6MothsAvgRepayAmount() {
		return last6MothsAvgRepayAmount;
	}

	public void setLast6MothsAvgRepayAmount(BigDecimal last6MothsAvgRepayAmount) {
		this.last6MothsAvgRepayAmount = last6MothsAvgRepayAmount;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

}
