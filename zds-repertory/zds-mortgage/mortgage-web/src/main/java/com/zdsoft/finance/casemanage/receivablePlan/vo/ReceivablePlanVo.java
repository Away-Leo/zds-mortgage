package com.zdsoft.finance.casemanage.receivablePlan.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlan;
import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 还款计划Vo
 * 
 * @author zhoushichao 
 * 2017-01-06 14:43:50
 */
public class ReceivablePlanVo extends BaseVo<ReceivablePlan> {

	/**
	 * 用一句话描述这个变量表示什么
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 期数
	 */
	private Integer periodsNo;

	/**
	 * 应还日期
	 */
	private Long repaymentDate;

	/**
	 * 本金
	 */
	private BigDecimal repaymentAmount;

	/**
	 * 利息
	 */
	private BigDecimal interestAmount;
	
	/**
	 * 罚息
	 */
	private BigDecimal affirmPenalty = new BigDecimal(0);
	
	/**
	 * 剩余本金
	 */
	private BigDecimal surplusRepaymentAmount;
	
	/**
	 * 服务费
	 */
	private BigDecimal serviceChange;

	/**
	 * 还款计划基本信息ID
	 */
	private String receivableInfoId;
	
	/**
	 * 放款ID
	 */
	private String loanApplyId;
	
	public ReceivablePlanVo(){}
	
	public ReceivablePlanVo(ReceivablePlan po){
		super(po);
	}
	
	public ReceivablePlan toPO(){
		ReceivablePlan po = new ReceivablePlan();
		po=super.toPo(this, po);
		return po;
	}

	public Integer getPeriodsNo() {
		return periodsNo;
	}

	public void setPeriodsNo(Integer periodsNo) {
		this.periodsNo = periodsNo;
	}

	public Long getRepaymentDate() {
		return repaymentDate;
	}

	public void setRepaymentDate(Long repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	public BigDecimal getRepaymentAmount() {
		if (ObjectHelper.isNotEmpty(repaymentAmount)) {
			return repaymentAmount.setScale(2,BigDecimal.ROUND_HALF_UP);
		}
		return repaymentAmount;
	}

	public void setRepaymentAmount(BigDecimal repaymentAmount) {
		this.repaymentAmount = repaymentAmount;
	}

	public BigDecimal getInterestAmount() {
		if (ObjectHelper.isNotEmpty(interestAmount)) {
			return interestAmount.setScale(2,BigDecimal.ROUND_HALF_UP);
		}
		return interestAmount;
	}

	public void setInterestAmount(BigDecimal interestAmount) {
		this.interestAmount = interestAmount;
	}

	public String getReceivableInfoId() {
		return receivableInfoId;
	}

	public void setReceivableInfoId(String receivableInfoId) {
		this.receivableInfoId = receivableInfoId;
	}

	public String getLoanApplyId() {
		return loanApplyId;
	}

	public void setLoanApplyId(String loanApplyId) {
		this.loanApplyId = loanApplyId;
	}

	public BigDecimal getAffirmPenalty() {
		if (ObjectHelper.isNotEmpty(affirmPenalty)) {
			return affirmPenalty.setScale(2,BigDecimal.ROUND_HALF_UP);
		}
		return affirmPenalty;
	}

	public void setAffirmPenalty(BigDecimal affirmPenalty) {
		this.affirmPenalty = affirmPenalty;
	}

	public BigDecimal getSurplusRepaymentAmount() {
		if (ObjectHelper.isNotEmpty(surplusRepaymentAmount)) {
			return surplusRepaymentAmount.setScale(2,BigDecimal.ROUND_HALF_UP);
		}
		return surplusRepaymentAmount;
	}

	public void setSurplusRepaymentAmount(BigDecimal surplusRepaymentAmount) {
		this.surplusRepaymentAmount = surplusRepaymentAmount;
	}

	public BigDecimal getServiceChange() {
		return serviceChange;
	}

	public void setServiceChange(BigDecimal serviceChange) {
		this.serviceChange = serviceChange;
	}
	
}
