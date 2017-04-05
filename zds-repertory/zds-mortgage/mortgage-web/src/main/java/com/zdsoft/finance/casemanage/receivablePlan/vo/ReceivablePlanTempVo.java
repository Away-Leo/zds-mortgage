package com.zdsoft.finance.casemanage.receivablePlan.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlanTemp;
import com.zdsoft.finance.common.base.BaseVo;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ReceivablePlanTempVo.java 
 * @ClassName: ReceivablePlanTempVo 
 * @Description: 还款计划(临时)Vo
 * @author jincheng 
 * @date 2017年2月21日 下午6:35:56 
 * @version V1.0
 */
public class ReceivablePlanTempVo extends BaseVo<ReceivablePlanTemp> {

	private static final long serialVersionUID = 1L;

	/**
	 * 期数
	 */
	private Integer periods;

	/**
	 * 当前还款日期
	 */
	private Long planRepayDate;
	
	/**
	 * 当前还款日期(字符)
	 */
	private String planReayDateStr;

	/**
	 * 当前开始日期
	 */
	private Long startDate;

	/**
	 * 当前结束日期
	 */
	private Long endDate;

	/**
	 * 当期本金
	 */
	private BigDecimal planPrincipalAmount ;

	/**
	 * 当期利息
	 */
	private BigDecimal planInterestAmount;

	/**
	 * 当期服务费
	 */
	private BigDecimal planServiceFee;

	/**
	 * 当期剩余本金
	 */
	private BigDecimal remainPrincipal;

	/**
	 * 还款计划基本信息ID
	 */
	private String receivableInfoId;

	/**
	 * 案件id
	 */
	private String caseApplyId;

	/**
	 * 放款ID
	 */
	private String loanApplyId;
	
	/**
	 * 所属机构
	 */
	private String orgId;
	
	/**
	 * 计划类型 1:草稿计划 2：放款时计划 3：变更时计划 4：展期时计划 5：提前部分还款时计划 6：提前结清时计划
	 */
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

	public String getPlanReayDateStr() {
		return planReayDateStr;
	}

	public void setPlanReayDateStr(String planReayDateStr) {
		this.planReayDateStr = planReayDateStr;
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

	public ReceivablePlanTempVo() {
		super();
	}

	public ReceivablePlanTempVo(ReceivablePlanTemp entity) {
		super(entity);
	}

	public ReceivablePlanTemp toPo()  {
		ReceivablePlanTemp entity = new ReceivablePlanTemp();
		return super.toPo(this, entity);
	}

}
