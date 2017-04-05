package com.zdsoft.finance.credit.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.credit.entity.CustomerCreditStatistics;

/** 
* 版权所有：重庆正大华日软件有限公司
* @Title: CustomerCreditStatisticsVo.java 
* @Package com.zdsoft.finance.credit.vo 
* @Description: 客户
* @author jingyh 
* @date 2017年2月22日 下午7:55:23 
* @version V1.0 
*/
public class CustomerCreditStatisticsVo extends BaseVo<CustomerCreditStatistics> {

	private static final long serialVersionUID = 924729992251505334L;

	/**
	 * 案件Id
	 */
	private String caseApplyId;
	
	/**
	 * 案件阶段
	 */
	private String caseApplyStage;
	
	/**
	 * 数据来源标示Id
	 */
	private String sourceMarkId;
	
	/**
	 * 来源
	 */
	private String sourceFrom;
	
	/**
	 * 客户Id
	 */
	private String customerId;
	
	/**
	 * 客户姓名
	 */
	private String customerName;
	
	/**
	 * 贷款总额
	 */
	private BigDecimal loanTotalAmount;
	
	/**
	 * 贷款总笔数
	 */
	private Integer loanTotalNum;
	
	/**
	 * 已结清贷款笔数
	 */
	private Integer endLoanTotalNum;
	
	/**
	 * 已结清贷款总额
	 */
	private BigDecimal endLoanTotalAmount;
	
	/**
	 * 未结清笔数
	 */
	private Integer loaningTotalNum;
	
	/**
	 * 未结清总额
	 */
	private BigDecimal loaningTotalAmount;
	
	/**
	 * 信用卡发卡总额
	 */
	private BigDecimal creditApplyTotalAmount;
	
	/**
	 * 发放张数
	 */
	private Integer creditApplyNum;
	
	/**
	 * 已使用额度
	 */
	private BigDecimal creditUsedAmount;
	
	/**
	 * 信用卡使用率
	 */
	private Double creditUsedRate;
	
	/**
	 * 贷款逾期：逾期总笔数
	 */
	private Integer loanOverdueNum;
	
	/**
	 * 贷款逾期：未逾期笔数
	 */
	private Integer loanNotOverNum;
	
	/**
	 * 贷款逾期：超标笔数
	 */
	private Integer loanOverMarkNum;
	
	/**
	 * 贷款逾期：单笔最高逾期期数
	 */
	private Integer loanMaxOverduePeriods;
	
	/**
	 * 贷款逾期：单笔最高逾期金额 
	 */
	private BigDecimal loanMaxOverAmount;
	
	/**
	 * 贷记卡逾期：逾期总笔数
	 */
	private Integer cardOverdueNum;
	
	/**
	 * 贷记卡逾期：未逾期笔数
	 */
	private Integer cardNotOverNum;
	
	/**
	 * 贷记卡逾期：超标笔数
	 */
	private Integer cardOverMarkNum;
	
	/**
	 * 贷记卡逾期：单笔最高逾期期数
	 */
	private Integer cardMaxOverPeriods;
	
	/**
	 * 贷记卡逾期：单笔最高逾期金额
	 */
	private BigDecimal cardMaxOverAmount;
	
	/**
	 * 对外担保笔数
	 */
	private Integer externalGuaranteeNum;
	
	/**
	 * 对外担保金额
	 */
	private BigDecimal externalGuaranteeAmount;
	
	/**
	 * 一年累计逾期
	 */
	private Integer yearOverNum;
	
	/**
	 * 一年内最高逾期期数
	 */
	private Integer yearMaxOverNum;
	
	/**
	 * 呆账超标
	 */
	private Integer excessiveBadNum;
	
	/**
	 * 冻结超标
	 */
	private Integer excessiveFreezeNum;
	
	/**
	 * 征信超标
	 */
	private Boolean iSExcessiveCredit;

	public CustomerCreditStatisticsVo() {
		super();
	}
	
	/**
	 * 通过实体构造
	 * @param entity
	 */
	public CustomerCreditStatisticsVo(CustomerCreditStatistics entity) {
		super(entity);
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
