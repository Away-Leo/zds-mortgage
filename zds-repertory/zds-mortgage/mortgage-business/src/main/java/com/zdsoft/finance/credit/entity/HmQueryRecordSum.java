package com.zdsoft.finance.credit.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: HmQueryRecordSum.java
 * @ClassName: HmQueryRecordSum
 * @Description: 查询记录汇总
 * @author gufeng
 * @date 2017年2月23日 上午9:43:46
 * @version V1.0
 */
@Entity
@Table(name = "hm_query_Record_Sum")
public class HmQueryRecordSum extends BaseEntity {

	private static final long serialVersionUID = 1126387262254470591L;
	/**
	 * 查询ID
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
	 * 贷款审批
	 */
	@Column(name = "Last1_Months_Org_Loan_Approv")
	private Integer last1MonthsOrgLoanApprovalSum;
	/**
	 * 信用卡审批
	 */
	@Column(name = "Last1_Months_Org_Credit_Card")
	private Integer last1MonthsOrgCreditCardApprovalSum;
	/**
	 * 贷款审批
	 */
	@Column(name = "Last1_Months_Loan_Approval_Su")
	private Integer last1MonthsLoanApprovalSum;
	/**
	 * 信用卡审批
	 */
	@Column(name = "Last1_Months_Credit_Card_Ap")
	private Integer last1MonthsCreditCardApprovalSum;
	/**
	 * 本人查询
	 */
	@Column(name = "Last1_Months_Self_Query_Sum")
	private Integer last1MonthsSelfQuerySum;
	/**
	 * 贷后管理
	 */
	@Column(name = "Last2_Years_Loan_Mange_Sum")
	private Integer last2YearsLoanMangeSum;
	/**
	 * 担保资格审查
	 */
	@Column(name = "Last2_Years_Guar_Appro_Sum")
	private Integer last2YearsGuarApproSum;
	/**
	 * 特约商户实名审查
	 */
	@Column(name = "Last2_Years_SpeMerch_Appro_Su")
	private Integer last2YearsSpeMerchApproSum;

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

	public Integer getLast1MonthsOrgLoanApprovalSum() {
		return last1MonthsOrgLoanApprovalSum;
	}

	public void setLast1MonthsOrgLoanApprovalSum(Integer last1MonthsOrgLoanApprovalSum) {
		this.last1MonthsOrgLoanApprovalSum = last1MonthsOrgLoanApprovalSum;
	}

	public Integer getLast1MonthsOrgCreditCardApprovalSum() {
		return last1MonthsOrgCreditCardApprovalSum;
	}

	public void setLast1MonthsOrgCreditCardApprovalSum(Integer last1MonthsOrgCreditCardApprovalSum) {
		this.last1MonthsOrgCreditCardApprovalSum = last1MonthsOrgCreditCardApprovalSum;
	}

	public Integer getLast1MonthsLoanApprovalSum() {
		return last1MonthsLoanApprovalSum;
	}

	public void setLast1MonthsLoanApprovalSum(Integer last1MonthsLoanApprovalSum) {
		this.last1MonthsLoanApprovalSum = last1MonthsLoanApprovalSum;
	}

	public Integer getLast1MonthsCreditCardApprovalSum() {
		return last1MonthsCreditCardApprovalSum;
	}

	public void setLast1MonthsCreditCardApprovalSum(Integer last1MonthsCreditCardApprovalSum) {
		this.last1MonthsCreditCardApprovalSum = last1MonthsCreditCardApprovalSum;
	}

	public Integer getLast1MonthsSelfQuerySum() {
		return last1MonthsSelfQuerySum;
	}

	public void setLast1MonthsSelfQuerySum(Integer last1MonthsSelfQuerySum) {
		this.last1MonthsSelfQuerySum = last1MonthsSelfQuerySum;
	}

	public Integer getLast2YearsLoanMangeSum() {
		return last2YearsLoanMangeSum;
	}

	public void setLast2YearsLoanMangeSum(Integer last2YearsLoanMangeSum) {
		this.last2YearsLoanMangeSum = last2YearsLoanMangeSum;
	}

	public Integer getLast2YearsGuarApproSum() {
		return last2YearsGuarApproSum;
	}

	public void setLast2YearsGuarApproSum(Integer last2YearsGuarApproSum) {
		this.last2YearsGuarApproSum = last2YearsGuarApproSum;
	}

	public Integer getLast2YearsSpeMerchApproSum() {
		return last2YearsSpeMerchApproSum;
	}

	public void setLast2YearsSpeMerchApproSum(Integer last2YearsSpeMerchApproSum) {
		this.last2YearsSpeMerchApproSum = last2YearsSpeMerchApproSum;
	}

}
