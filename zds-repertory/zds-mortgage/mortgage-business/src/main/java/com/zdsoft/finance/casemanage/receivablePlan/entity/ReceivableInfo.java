package com.zdsoft.finance.casemanage.receivablePlan.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 还款计划基本信息
 * 
 * @author wangrongwei
 * @create 2017-01-05 20:11
 **/
@Entity
@Table(name = "case_receivable_info")
public class ReceivableInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 案件Id
	 */
	@Column(length = 32, nullable = false)
	private String caseApplyId;

	/**
	 * 还款方式
	 */
	@Column(nullable = false, length = 20)
	private String repaymentType;

	/**
	 * 还款方式2
	 */
	@Column(nullable = false, length = 20)
	private String repaymentTypeTwo;

	/**
	 * 逾期利率
	 */
	@Column(nullable = false, precision = 18, scale = 6)
	private BigDecimal overdueDailyRate = new BigDecimal(0);
	
	/**
	 * 单位
	 */
	@Column(length=20)
	private String overdueDailyRateUnit;

	/**
	 * 贷款利率
	 */
	@Column(nullable = false, precision = 18, scale = 6)
	private BigDecimal loanMonthRate = new BigDecimal(0);
	
	/**
	 * 单位
	 */
	@Column(length=20)
	private String loanMonthRateUnit;
	
	/**
	 * 综合利率
	 */
	@Column(nullable = false, precision = 18, scale = 6)
	private BigDecimal syntheticalRate = new BigDecimal(0);
	
	/**
	 * 单位
	 */
	@Column(length=20)
	private String syntheticalRateUnit;

	/**
	 * 利率性质
	 */
	@Column(nullable = false, length = 20)
	private String rateNature;

	/**
	 * 还款日
	 */
	@Column
	private Integer repaymentDate;

	/**
	 * 预计放款日期
	 */
	@Column
	private Long expectLoanDate;

	/**
	 * 预计开始日期
	 */
	@Column
	private Long expectStartDate;

	/**
	 * 还款计划
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "receivableInfoId")
	@LazyCollection(LazyCollectionOption.TRUE)
	private List<ReceivablePlan> receivablePlan = new ArrayList<ReceivablePlan>();

	/**
	 * 备注
	 */
	@Column(length = 500)
	private String mo;

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
		return overdueDailyRate;
	}

	public void setOverdueDailyRate(BigDecimal overdueDailyRate) {
		this.overdueDailyRate = overdueDailyRate;
	}

	public BigDecimal getLoanMonthRate() {
		return loanMonthRate;
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

	public List<ReceivablePlan> getReceivablePlan() {
		return receivablePlan;
	}

	public void setReceivablePlan(List<ReceivablePlan> receivablePlan) {
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

	public String getMo() {
		return mo;
	}

	public void setMo(String mo) {
		this.mo = mo;
	}

}
