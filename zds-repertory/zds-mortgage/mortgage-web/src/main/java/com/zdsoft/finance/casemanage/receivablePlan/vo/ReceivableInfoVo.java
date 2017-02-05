package com.zdsoft.finance.casemanage.receivablePlan.vo;

import java.math.BigDecimal;

import javax.persistence.Column;

import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivableInfo;
import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 还款计划信息Vo
 * 
 * @author zhoushichao 2017-01-06 14:43:50
 */
public class ReceivableInfoVo extends BaseVo<ReceivableInfo> {

	private static final long serialVersionUID = 1L;

	/**
	 * 案件Id
	 */
	private String caseApplyId;

	/**
	 * 还款方式
	 */
	private String repaymentType;

	/**
	 * 还款方式2
	 */
	private String repaymentTypeTwo;

	/**
	 * 逾期利率
	 */
	private BigDecimal overdueDailyRate;

	/**
	 * 单位
	 */
	private String overdueDailyRateUnit;

	/**
	 * 贷款利率
	 */
	private BigDecimal loanMonthRate;

	/**
	 * 单位
	 */
	private String loanMonthRateUnit;

	/**
	 * 综合利率
	 */
	private BigDecimal syntheticalRate;

	/**
	 * 单位
	 */
	private String syntheticalRateUnit;

	/**
	 * 利率性质
	 */
	private String rateNature;

	/**
	 * 还款日
	 */
	private Integer repaymentDate;

	/**
	 * 预计放款日期
	 */
	private Long expectLoanDate;

	/**
	 * 预计开始日期
	 */
	private Long expectStartDate;

	/**
	 * 还款计划详细信息
	 */
	private ReceivablePlanVo receivablePlan;

	/**
	 * 备注
	 */
	@Column(length = 500)
	private String mo;

	public ReceivableInfoVo() {
	}

	public ReceivableInfoVo(ReceivableInfo po) {
		super(po);
	}

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public String getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}

	public String getRepaymentTypeTwo() {
		return repaymentTypeTwo;
	}

	public void setRepaymentTypeTwo(String repaymentTypeTwo) {
		this.repaymentTypeTwo = repaymentTypeTwo;
	}

	public BigDecimal getOverdueDailyRate() {
		if (ObjectHelper.isNotEmpty(overdueDailyRate)) {
			return overdueDailyRate.setScale(2);
		}
		return overdueDailyRate;
	}

	public void setOverdueDailyRate(BigDecimal overdueDailyRate) {
		this.overdueDailyRate = overdueDailyRate;
	}

	public BigDecimal getLoanMonthRate() {
		if(ObjectHelper.isNotEmpty(loanMonthRate)){
			return loanMonthRate.setScale(2);
		}else return loanMonthRate;
	}

	public void setLoanMonthRate(BigDecimal loanMonthRate) {
		this.loanMonthRate = loanMonthRate;
	}

	public String getRateNature() {
		return rateNature;
	}

	public void setRateNature(String rateNature) {
		this.rateNature = rateNature;
	}

	public Integer getRepaymentDate() {
		return repaymentDate;
	}

	public void setRepaymentDate(Integer repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	public Long getExpectLoanDate() {
		return expectLoanDate;
	}

	public void setExpectLoanDate(Long expectLoanDate) {
		this.expectLoanDate = expectLoanDate;
	}

	public Long getExpectStartDate() {
		return expectStartDate;
	}

	public void setExpectStartDate(Long expectStartDate) {
		this.expectStartDate = expectStartDate;
	}

	public String getMo() {
		return mo;
	}

	public void setMo(String mo) {
		this.mo = mo;
	}

	public ReceivablePlanVo getReceivablePlan() {
		return receivablePlan;
	}

	public void setReceivablePlan(ReceivablePlanVo receivablePlan) {
		this.receivablePlan = receivablePlan;
	}

	public String getOverdueDailyRateUnit() {
		return overdueDailyRateUnit;
	}

	public void setOverdueDailyRateUnit(String overdueDailyRateUnit) {
		this.overdueDailyRateUnit = overdueDailyRateUnit;
	}

	public String getLoanMonthRateUnit() {
		return loanMonthRateUnit;
	}

	public void setLoanMonthRateUnit(String loanMonthRateUnit) {
		this.loanMonthRateUnit = loanMonthRateUnit;
	}

	public BigDecimal getSyntheticalRate() {
		if (ObjectHelper.isNotEmpty(syntheticalRate)) {
			return syntheticalRate.setScale(2);
		}
		return syntheticalRate;
	}

	public void setSyntheticalRate(BigDecimal syntheticalRate) {
		this.syntheticalRate = syntheticalRate;
	}

	public String getSyntheticalRateUnit() {
		return syntheticalRateUnit;
	}

	public void setSyntheticalRateUnit(String syntheticalRateUnit) {
		this.syntheticalRateUnit = syntheticalRateUnit;
	}

}
