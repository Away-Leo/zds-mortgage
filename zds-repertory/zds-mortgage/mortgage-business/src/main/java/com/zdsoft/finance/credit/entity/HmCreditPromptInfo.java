package com.zdsoft.finance.credit.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: HmCreditPromptInfo.java
 * @ClassName: HmCreditPromptInfo
 * @Description: 信用提示
 * @author gufeng
 * @date 2017年2月23日 上午9:39:01
 * @version V1.0
 */
@Entity
@Table(name = "hm_credit_prompt_info")
public class HmCreditPromptInfo extends BaseEntity {

	private static final long serialVersionUID = 5372650419510180029L;
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
	 * 个人住房贷款笔数
	 */
	@Column(name = "Personal_House_Loan_Num")
	private String personalHouseLoanNum;
	/**
	 * 个人商用房（包括商住两用）贷款笔数
	 */
	@Column(name = "House_Loan_Num")
	private String houseLoanNum;
	/**
	 * 其他贷款笔数
	 */
	@Column(name = "Other_Loan_Num")
	private String otherLoanNum;
	/**
	 * 首笔贷款发放月份
	 */
	@Column(name = "Frist_Loan_Month")
	private String fristLoanMonth;
	/**
	 * 贷记卡账户数
	 */
	@Column(name = "Credit_Card_Account_Num")
	private String creditCardAccountNum;
	/**
	 * 首张贷记卡发卡月份
	 */
	@Column(name = "Frist_Credit_Card_Month")
	private String fristCreditCardMonth;

	/**
	 * 准贷记卡账户数
	 */
	@Column(name = "Quasi_Credit_Card_Num")
	private String quasiCreditCardNum;
	/**
	 * 首张准贷记卡发卡月份
	 */
	@Column(name = "Quasi_Credit_Card_Month")
	private String quasiCreditCardMonth;
	/**
	 * 本人声明数目
	 */
	@Column(name = "Declare_Num")
	private String declareNum;
	/**
	 * 异议标注数目
	 */
	@Column(name = "Dissent_Num")
	private String dissentNum;

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

	public String getPersonalHouseLoanNum() {
		return personalHouseLoanNum;
	}

	public void setPersonalHouseLoanNum(String personalHouseLoanNum) {
		this.personalHouseLoanNum = personalHouseLoanNum;
	}

	public String getHouseLoanNum() {
		return houseLoanNum;
	}

	public void setHouseLoanNum(String houseLoanNum) {
		this.houseLoanNum = houseLoanNum;
	}

	public String getOtherLoanNum() {
		return otherLoanNum;
	}

	public void setOtherLoanNum(String otherLoanNum) {
		this.otherLoanNum = otherLoanNum;
	}

	public String getFristLoanMonth() {
		return fristLoanMonth;
	}

	public void setFristLoanMonth(String fristLoanMonth) {
		this.fristLoanMonth = fristLoanMonth;
	}

	public String getCreditCardAccountNum() {
		return creditCardAccountNum;
	}

	public void setCreditCardAccountNum(String creditCardAccountNum) {
		this.creditCardAccountNum = creditCardAccountNum;
	}

	public String getFristCreditCardMonth() {
		return fristCreditCardMonth;
	}

	public void setFristCreditCardMonth(String fristCreditCardMonth) {
		this.fristCreditCardMonth = fristCreditCardMonth;
	}

	public String getQuasiCreditCardNum() {
		return quasiCreditCardNum;
	}

	public void setQuasiCreditCardNum(String quasiCreditCardNum) {
		this.quasiCreditCardNum = quasiCreditCardNum;
	}

	public String getQuasiCreditCardMonth() {
		return quasiCreditCardMonth;
	}

	public void setQuasiCreditCardMonth(String quasiCreditCardMonth) {
		this.quasiCreditCardMonth = quasiCreditCardMonth;
	}

	public String getDeclareNum() {
		return declareNum;
	}

	public void setDeclareNum(String declareNum) {
		this.declareNum = declareNum;
	}

	public String getDissentNum() {
		return dissentNum;
	}

	public void setDissentNum(String dissentNum) {
		this.dissentNum = dissentNum;
	}

}
