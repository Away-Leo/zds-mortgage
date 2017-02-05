package com.zdsoft.finance.capital.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 信托计划本金投入
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
@Entity
@Table(name = "caal_credit_entrust_principal")
public class CreditEntrustPrincipal extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1617570546617520897L;

	/**
	 * 出资方
	 */
	@Column(length = 32)
	private String contribution;

	/**
	 * 出资方类型
	 */
	@Column(length = 32)
	private String contributionType;

	/**
	 * 初始本金
	 */
	@Column(precision = 30, scale = 12)
	private BigDecimal initialPrincipal;

	/**
	 * 本金金额
	 */
	@Column(precision = 16, scale = 4)
	private BigDecimal principalAmount;

	/**
	 * 合同收益率
	 */
	@Column(precision = 12, scale = 6)
	private Double profitRate;

	/**
	 * 追加日期
	 */
	@Column
	private Long addDate;

	/**
	 * 提交日期
	 */
	@Column
	private Long completeDate;

	/**
	 * 使用比率
	 */
	@Column(precision = 12, scale = 6)
	private Double useProp;

	/**
	 * 预计到账日期
	 */
	@Column
	private Long expectDate;

	/**
	 * 实际到账日期
	 */
	@Column
	private Long actualDate;

	/**
	 * 使用额度
	 */
	@Column(precision = 16, scale = 4)
	private BigDecimal usedQuota;

	/**
	 * 备注
	 */
	@Column(length = 256)
	private String remark;

	/**
	 * 提交状态
	 */
	@Column
	private Integer status;

	@Column(length = 32)
	private String completeEmpCd;
	@Column(length = 64)
	private String completeEmpName;

	/**
	 * 派息周期
	 */
	@Column(length = 32)
	private String payoutPeriod;

	/**
	 * 付息日
	 */
	@Column
	private Integer termDay;

	/**
	 * 到期日期
	 */
	@Column
	private Long maturityDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade({ CascadeType.ALL })
	@JoinColumn(name = "creditEntrustId")
	private CreditEntrust creditEntrust;

	@Column(length = 32)
	private String tempUuid;

	public String getContribution() {
		return contribution;
	}

	public void setContribution(String contribution) {
		this.contribution = contribution;
	}

	public String getContributionType() {
		return contributionType;
	}

	public void setContributionType(String contributionType) {
		this.contributionType = contributionType;
	}

	public BigDecimal getPrincipalAmount() {
		return principalAmount;
	}

	public void setPrincipalAmount(BigDecimal principalAmount) {
		this.principalAmount = principalAmount;
	}

	public Double getProfitRate() {
		return profitRate;
	}

	public void setProfitRate(Double profitRate) {
		this.profitRate = profitRate;
	}

	public Long getAddDate() {
		return addDate;
	}

	public void setAddDate(Long addDate) {
		this.addDate = addDate;
	}

	public Double getUseProp() {
		return useProp;
	}

	public void setUseProp(Double useProp) {
		this.useProp = useProp;
	}

	public Long getExpectDate() {
		return expectDate;
	}

	public void setExpectDate(Long expectDate) {
		this.expectDate = expectDate;
	}

	public Long getActualDate() {
		return actualDate;
	}

	public void setActualDate(Long actualDate) {
		this.actualDate = actualDate;
	}

	public BigDecimal getUsedQuota() {
		return usedQuota;
	}

	public void setUsedQuota(BigDecimal usedQuota) {
		this.usedQuota = usedQuota;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public CreditEntrust getCreditEntrust() {
		return creditEntrust;
	}

	public void setCreditEntrust(CreditEntrust creditEntrust) {
		this.creditEntrust = creditEntrust;
	}

	public BigDecimal getInitialPrincipal() {
		return initialPrincipal;
	}

	public void setInitialPrincipal(BigDecimal initialPrincipal) {
		this.initialPrincipal = initialPrincipal;
	}

	public Long getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Long completeDate) {
		this.completeDate = completeDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCompleteEmpCd() {
		return completeEmpCd;
	}

	public void setCompleteEmpCd(String completeEmpCd) {
		this.completeEmpCd = completeEmpCd;
	}

	public String getCompleteEmpName() {
		return completeEmpName;
	}

	public void setCompleteEmpName(String completeEmpName) {
		this.completeEmpName = completeEmpName;
	}

	public String getPayoutPeriod() {
		return payoutPeriod;
	}

	public void setPayoutPeriod(String payoutPeriod) {
		this.payoutPeriod = payoutPeriod;
	}

	public Integer getTermDay() {
		return termDay;
	}

	public void setTermDay(Integer termDay) {
		this.termDay = termDay;
	}

	public Long getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Long maturityDate) {
		this.maturityDate = maturityDate;
	}

	public String getTempUuid() {
		return tempUuid;
	}

	public void setTempUuid(String tempUuid) {
		this.tempUuid = tempUuid;
	}

}
