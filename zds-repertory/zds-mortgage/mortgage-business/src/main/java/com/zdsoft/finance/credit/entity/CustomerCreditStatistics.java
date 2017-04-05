package com.zdsoft.finance.credit.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/** 
* 版权所有：重庆正大华日软件有限公司
* @Title: CustomerCreditStatistics.java 
* @Package com.zdsoft.finance.credit.entity 
* @Description: 客户征信统计表
* @author jingyh 
* @date 2017年2月22日 下午6:22:58 
* @version V1.0 
*/
@Entity
@Table(name = "cust_credit_statis")
public class CustomerCreditStatistics extends BaseEntity {

	private static final long serialVersionUID = 8862760467123956034L;
	
	/**
	 * 数据来源：线下
	 */
	public static final String SOURCE_OFFLINE = "0000";
	
	/**
	 * 数据来源：线上
	 */
	public static final String SOURCE_ONLINE = "0001";
	
	/**
	 * 案件Id
	 */
	@Column(length = 32, nullable = false)
	private String caseApplyId;
	
	/**
	 * 案件阶段
	 */
	@Column(length = 20, nullable = false)
	private String caseApplyStage;
	
	/**
	 * 数据来源标示Id
	 */
	@Column(length = 50, nullable = false)
	private String sourceMarkId;
	
	/**
	 * 来源
	 */
	@Column(length = 4, nullable = false)
	private String sourceFrom;
	
	/**
	 * 客户Id
	 */
	@Column(length = 32, nullable = false)
	private String customerId;
	
	/**
	 * 客户姓名
	 */
	@Column(length = 64, nullable = false)
	private String customerName;
	
	/**
	 * 贷款总额
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal loanTotalAmount = BigDecimal.ZERO;
	
	/**
	 * 贷款总笔数
	 */
	@Column
	private Integer loanTotalNum = 0;
	
	/**
	 * 已结清贷款笔数
	 */
	@Column
	private Integer endLoanTotalNum = 0;
	
	/**
	 * 已结清贷款总额
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal endLoanTotalAmount = BigDecimal.ZERO;
	
	/**
	 * 未结清笔数
	 */
	@Column
	private Integer loaningTotalNum = 0;
	
	/**
	 * 未结清总额
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal loaningTotalAmount = BigDecimal.ZERO;
	
	/**
	 * 信用卡发卡总额
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal creditApplyTotalAmount = BigDecimal.ZERO;
	
	/**
	 * 发放张数
	 */
	@Column
	private Integer creditApplyNum = 0;
	
	/**
	 * 已使用额度
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal creditUsedAmount = BigDecimal.ZERO;
	
	/**
	 * 信用卡使用率
	 */
	@Column
	private Double creditUsedRate = 0D;
	
	/**
	 * 贷款逾期：逾期总笔数
	 */
	@Column
	private Integer loanOverdueNum = 0;
	
	/**
	 * 贷款逾期：未逾期笔数
	 */
	@Column
	private Integer loanNotOverNum = 0;
	
	/**
	 * 贷款逾期：超标笔数
	 */
	@Column
	private Integer loanOverMarkNum = 0;
	
	/**
	 * 贷款逾期：单笔最高逾期期数
	 */
	@Column
	private Integer loanMaxOverduePeriods = 0;
	
	/**
	 * 贷款逾期：单笔最高逾期金额 
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal loanMaxOverAmount = BigDecimal.ZERO;
	
	/**
	 * 贷记卡逾期：逾期总笔数
	 */
	@Column
	private Integer cardOverdueNum = 0;
	
	/**
	 * 贷记卡逾期：未逾期笔数
	 */
	@Column
	private Integer cardNotOverNum = 0;
	
	/**
	 * 贷记卡逾期：超标笔数
	 */
	@Column
	private Integer cardOverMarkNum = 0;
	
	/**
	 * 贷记卡逾期：单笔最高逾期期数
	 */
	@Column
	private Integer cardMaxOverPeriods = 0;
	
	/**
	 * 贷记卡逾期：单笔最高逾期金额
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal cardMaxOverAmount = BigDecimal.ZERO;

	/**
	 * 对外担保笔数
	 */
	@Column
	private Integer externalGuaranteeNum = 0;
	
	/**
	 * 对外担保金额
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal externalGuaranteeAmount = BigDecimal.ZERO;
	
	/**
	 * 一年累计逾期
	 */
	@Column
	private Integer yearOverNum = 0;
	
	/**
	 * 一年内最高逾期期数
	 */
	@Column
	private Integer yearMaxOverNum = 0;
	
	/**
	 * 呆账超标
	 */
	@Column
	private Integer excessiveBadNum = 0;
	
	/**
	 * 冻结超标
	 */
	@Column
	private Integer excessiveFreezeNum = 0;
	
	/**
	 * 征信超标
	 */
	@Column
	@Type(type="true_false")
	private Boolean iSExcessiveCredit = false;
	
	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public String getSourceFrom() {
		return sourceFrom;
	}

	public void setSourceFrom(String sourceFrom) {
		this.sourceFrom = sourceFrom;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public BigDecimal getLoanTotalAmount() {
		return loanTotalAmount;
	}

	public void setLoanTotalAmount(BigDecimal loanTotalAmount) {
		this.loanTotalAmount = loanTotalAmount;
	}

	public Integer getLoanTotalNum() {
		return loanTotalNum;
	}

	public void setLoanTotalNum(Integer loanTotalNum) {
		this.loanTotalNum = loanTotalNum;
	}

	public Integer getEndLoanTotalNum() {
		return endLoanTotalNum;
	}

	public void setEndLoanTotalNum(Integer endLoanTotalNum) {
		this.endLoanTotalNum = endLoanTotalNum;
	}

	public BigDecimal getEndLoanTotalAmount() {
		return endLoanTotalAmount;
	}

	public void setEndLoanTotalAmount(BigDecimal endLoanTotalAmount) {
		this.endLoanTotalAmount = endLoanTotalAmount;
	}

	public Integer getLoaningTotalNum() {
		return loaningTotalNum;
	}

	public void setLoaningTotalNum(Integer loaningTotalNum) {
		this.loaningTotalNum = loaningTotalNum;
	}

	public BigDecimal getLoaningTotalAmount() {
		return loaningTotalAmount;
	}

	public void setLoaningTotalAmount(BigDecimal loaningTotalAmount) {
		this.loaningTotalAmount = loaningTotalAmount;
	}

	public BigDecimal getCreditApplyTotalAmount() {
		return creditApplyTotalAmount;
	}

	public void setCreditApplyTotalAmount(BigDecimal creditApplyTotalAmount) {
		this.creditApplyTotalAmount = creditApplyTotalAmount;
	}

	public Integer getCreditApplyNum() {
		return creditApplyNum;
	}

	public void setCreditApplyNum(Integer creditApplyNum) {
		this.creditApplyNum = creditApplyNum;
	}

	public BigDecimal getCreditUsedAmount() {
		return creditUsedAmount;
	}

	public void setCreditUsedAmount(BigDecimal creditUsedAmount) {
		this.creditUsedAmount = creditUsedAmount;
	}

	public Double getCreditUsedRate() {
		return creditUsedRate;
	}

	public void setCreditUsedRate(Double creditUsedRate) {
		this.creditUsedRate = creditUsedRate;
	}

	public Integer getLoanOverdueNum() {
		return loanOverdueNum;
	}

	public void setLoanOverdueNum(Integer loanOverdueNum) {
		this.loanOverdueNum = loanOverdueNum;
	}

	public Integer getLoanNotOverNum() {
		return loanNotOverNum;
	}

	public void setLoanNotOverNum(Integer loanNotOverNum) {
		this.loanNotOverNum = loanNotOverNum;
	}

	public Integer getLoanOverMarkNum() {
		return loanOverMarkNum;
	}

	public void setLoanOverMarkNum(Integer loanOverMarkNum) {
		this.loanOverMarkNum = loanOverMarkNum;
	}

	public Integer getLoanMaxOverduePeriods() {
		return loanMaxOverduePeriods;
	}

	public void setLoanMaxOverduePeriods(Integer loanMaxOverduePeriods) {
		this.loanMaxOverduePeriods = loanMaxOverduePeriods;
	}

	public BigDecimal getLoanMaxOverAmount() {
		return loanMaxOverAmount;
	}

	public void setLoanMaxOverAmount(BigDecimal loanMaxOverAmount) {
		this.loanMaxOverAmount = loanMaxOverAmount;
	}

	public Integer getCardOverdueNum() {
		return cardOverdueNum;
	}
 
	public void setCardOverdueNum(Integer cardOverdueNum) {
		this.cardOverdueNum = cardOverdueNum;
	}

	public Integer getCardNotOverNum() {
		return cardNotOverNum;
	}

	public void setCardNotOverNum(Integer cardNotOverNum) {
		this.cardNotOverNum = cardNotOverNum;
	}

	public Integer getCardOverMarkNum() {
		return cardOverMarkNum;
	}

	public void setCardOverMarkNum(Integer cardOverMarkNum) {
		this.cardOverMarkNum = cardOverMarkNum;
	}

	public Integer getCardMaxOverPeriods() {
		return cardMaxOverPeriods;
	}

	public void setCardMaxOverPeriods(Integer cardMaxOverPeriods) {
		this.cardMaxOverPeriods = cardMaxOverPeriods;
	}

	public BigDecimal getCardMaxOverAmount() {
		return cardMaxOverAmount;
	}

	public void setCardMaxOverAmount(BigDecimal cardMaxOverAmount) {
		this.cardMaxOverAmount = cardMaxOverAmount;
	}

	public Integer getExternalGuaranteeNum() {
		return externalGuaranteeNum;
	}

	public void setExternalGuaranteeNum(Integer externalGuaranteeNum) {
		this.externalGuaranteeNum = externalGuaranteeNum;
	}

	public BigDecimal getExternalGuaranteeAmount() {
		return externalGuaranteeAmount;
	}

	public void setExternalGuaranteeAmount(BigDecimal externalGuaranteeAmount) {
		this.externalGuaranteeAmount = externalGuaranteeAmount;
	}

	public Integer getYearMaxOverNum() {
		return yearMaxOverNum;
	}

	public void setYearMaxOverNum(Integer yearMaxOverNum) {
		this.yearMaxOverNum = yearMaxOverNum;
	}

	public Integer getExcessiveBadNum() {
		return excessiveBadNum;
	}

	public void setExcessiveBadNum(Integer excessiveBadNum) {
		this.excessiveBadNum = excessiveBadNum;
	}

	public Integer getExcessiveFreezeNum() {
		return excessiveFreezeNum;
	}

	public void setExcessiveFreezeNum(Integer excessiveFreezeNum) {
		this.excessiveFreezeNum = excessiveFreezeNum;
	}

	public Boolean getiSExcessiveCredit() {
		return iSExcessiveCredit;
	}

	public void setiSExcessiveCredit(Boolean iSExcessiveCredit) {
		this.iSExcessiveCredit = iSExcessiveCredit;
	}

	public Integer getYearOverNum() {
		return yearOverNum;
	}

	public void setYearOverNum(Integer yearOverNum) {
		this.yearOverNum = yearOverNum;
	}

	public String getCaseApplyStage() {
		return caseApplyStage;
	}

	public void setCaseApplyStage(String caseApplyStage) {
		this.caseApplyStage = caseApplyStage;
	}

	public String getSourceMarkId() {
		return sourceMarkId;
	}

	public void setSourceMarkId(String sourceMarkId) {
		this.sourceMarkId = sourceMarkId;
	}

}
