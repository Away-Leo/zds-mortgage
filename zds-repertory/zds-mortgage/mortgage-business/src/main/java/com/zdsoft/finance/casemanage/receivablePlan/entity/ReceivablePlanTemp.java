package com.zdsoft.finance.casemanage.receivablePlan.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ReceivablePlanTemp.java 
 * @ClassName: ReceivablePlanTemp 
 * @Description: 还款计划(临时)
 * @author jincheng 
 * @date 2017年2月16日 上午9:54:23 
 * @version V1.0
 */
@Entity
@Table(name = "fin_repayment_plan_temp")
public class ReceivablePlanTemp extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 期数
	 */
	@Column()
	private Integer periods;

	/**
	 * 当前还款日期
	 */
	@Column()
	private Long planRepayDate;
	
	/**
	 * 当前还款日期(字符)
	 */
	@Column()
	private String planReayDateStr;

	/**
	 * 当前开始日期
	 */
	@Column()
	private Long startDate;

	/**
	 * 当前结束日期
	 */
	@Column()
	private Long endDate;

	/**
	 * 当期本金
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal planPrincipalAmount = BigDecimal.ZERO;

	/**
	 * 当期利息
	 */
	@Column( precision = 18, scale = 6)
	private BigDecimal planInterestAmount = BigDecimal.ZERO;

	/**
	 * 当期服务费
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal planServiceFee = BigDecimal.ZERO;

	/**
	 * 当期剩余本金
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal remainPrincipal = BigDecimal.ZERO;

	/**
	 * 还款计划基本信息ID
	 */
	@Column(length = 32)
	private String receivableInfoId;

	/**
	 * 案件id
	 */
	@Column(length = 32)
	private String caseApplyId;

	/**
	 * 放款ID
	 */
	@Column(length = 32)
	private String loanApplyId;
	
	/**
	 * 所属机构
	 */
	@Column(length=32)
	private String orgId;
	
	/**
	 * 计划类型 1:草稿计划 2：放款时计划 3：变更时计划 4：展期时计划 5：提前部分还款时计划 6：提前结清时计划
	 */
	@Column()
	private Integer planType;
	
	public Integer getPeriods() {
		return periods;
	}

	public void setPeriods(Integer periods) {
		this.periods = periods;
	}

	public Long getPlanRepayDate() {
		return planRepayDate;
	}

	public void setPlanRepayDate(Long planRepayDate) {
		this.planRepayDate = planRepayDate;
	}

	public Long getStartDate() {
		return startDate;
	}

	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getPlanPrincipalAmount() {
		return planPrincipalAmount;
	}

	public void setPlanPrincipalAmount(BigDecimal planPrincipalAmount) {
		this.planPrincipalAmount = planPrincipalAmount;
	}

	public BigDecimal getPlanInterestAmount() {
		return planInterestAmount;
	}

	public void setPlanInterestAmount(BigDecimal planInterestAmount) {
		this.planInterestAmount = planInterestAmount;
	}

	public BigDecimal getPlanServiceFee() {
		return planServiceFee;
	}

	public void setPlanServiceFee(BigDecimal planServiceFee) {
		this.planServiceFee = planServiceFee;
	}

	public BigDecimal getRemainPrincipal() {
		return remainPrincipal;
	}

	public void setRemainPrincipal(BigDecimal remainPrincipal) {
		this.remainPrincipal = remainPrincipal;
	}

	public String getReceivableInfoId() {
		return receivableInfoId;
	}

	public void setReceivableInfoId(String receivableInfoId) {
		this.receivableInfoId = receivableInfoId;
	}

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public String getLoanApplyId() {
		return loanApplyId;
	}

	public void setLoanApplyId(String loanApplyId) {
		this.loanApplyId = loanApplyId;
	}

	public String getPlanReayDateStr() {
		return planReayDateStr;
	}

	public void setPlanReayDateStr(String planReayDateStr) {
		this.planReayDateStr = planReayDateStr;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Integer getPlanType() {
		return planType;
	}

	public void setPlanType(Integer planType) {
		this.planType = planType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
