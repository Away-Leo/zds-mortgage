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
 * @Title: HmPerGuaranteeInfo.java
 * @ClassName: HmPerGuaranteeInfo
 * @Description: 对外担保信息
 * @author gufeng
 * @date 2017年2月23日 上午9:40:49
 * @version V1.0
 */
@Entity
@Table(name = "hm_per_guarantee_info")
public class HmPerGuaranteeInfo extends BaseEntity {

	private static final long serialVersionUID = 903883529126139549L;
	/**
	 * 查询主表id
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
	 * 编号
	 */
	@Column(name = "no")
	private Integer no;

	/**
	 * 担保贷款发放机构
	 */
	@Column(name = "Guar_Loan_Issuing_Agency")
	private String guarLoanIssuingAgency;

	/**
	 * 担保贷款合同金额
	 */
	@Column(name = "Guar_Loan_Contract_Amount")
	private BigDecimal guarLoanContractAmount = BigDecimal.ZERO;

	/**
	 * 担保贷款发放日期
	 */
	@Column(name = "Guar_Loan_Issue_Date")
	private String guarLoanIssueDate;

	/**
	 * 担保贷款到期日期
	 */
	@Column(name = "Guar_Loan_Due_Date")
	private String guarLoanDueDate;

	/**
	 * 担保金额
	 */
	@Column(name = "Guar_Amount")
	private BigDecimal guarAmount = BigDecimal.ZERO;

	/**
	 * 担保贷款本金余额
	 */
	@Column(name = "Guar_Loan_Principal_Amount")
	private BigDecimal guarLoanPrincipalAmount = BigDecimal.ZERO;

	/**
	 * 担保贷款五级分类
	 */
	@Column(name = "Guar_Loan_Fiveclass_code")
	private String guarLoanFiveclasscode;

	/**
	 * 结算日期
	 */
	@Column(name = "Sett_lement_Date")
	private String settlementDate;

	public String getGuarLoanIssuingAgency() {
		return guarLoanIssuingAgency;
	}

	public void setGuarLoanIssuingAgency(String guarLoanIssuingAgency) {
		this.guarLoanIssuingAgency = guarLoanIssuingAgency;
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

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public BigDecimal getGuarLoanContractAmount() {
		return guarLoanContractAmount;
	}

	public void setGuarLoanContractAmount(BigDecimal guarLoanContractAmount) {
		this.guarLoanContractAmount = guarLoanContractAmount;
	}

	public String getGuarLoanIssueDate() {
		return guarLoanIssueDate;
	}

	public void setGuarLoanIssueDate(String guarLoanIssueDate) {
		this.guarLoanIssueDate = guarLoanIssueDate;
	}

	public String getGuarLoanDueDate() {
		return guarLoanDueDate;
	}

	public void setGuarLoanDueDate(String guarLoanDueDate) {
		this.guarLoanDueDate = guarLoanDueDate;
	}

	public BigDecimal getGuarAmount() {
		return guarAmount;
	}

	public void setGuarAmount(BigDecimal guarAmount) {
		this.guarAmount = guarAmount;
	}

	public BigDecimal getGuarLoanPrincipalAmount() {
		return guarLoanPrincipalAmount;
	}

	public void setGuarLoanPrincipalAmount(BigDecimal guarLoanPrincipalAmount) {
		this.guarLoanPrincipalAmount = guarLoanPrincipalAmount;
	}

	public String getGuarLoanFiveclasscode() {
		return guarLoanFiveclasscode;
	}

	public void setGuarLoanFiveclasscode(String guarLoanFiveclasscode) {
		this.guarLoanFiveclasscode = guarLoanFiveclasscode;
	}

	public String getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(String settlementDate) {
		this.settlementDate = settlementDate;
	}

}
