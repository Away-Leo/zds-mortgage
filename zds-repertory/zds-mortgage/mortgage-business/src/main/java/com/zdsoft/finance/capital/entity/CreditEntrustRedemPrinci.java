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
 * 本金赎回
 * 
 * @createTime:2017年1月11日
 * @author liuwei
 * @version 1.0
 */
@Entity
@Table(name = "caal_credit_redemprinci")
public class CreditEntrustRedemPrinci extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2214152220098183559L;

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
	 * 赎回金额
	 */
	@Column(precision = 30, scale = 12)
	private BigDecimal redemptionAmount;

	/**
	 * 备注
	 */
	@Column(length = 2000)
	private String remark;

	/**
	 * 提交状态
	 */
	@Column
	private Integer status;

	/**
	 * 临时id
	 */
	@Column(length = 32)
	private String tempUuid;

	/**
	 * 提交人
	 */
	@Column(length = 32)
	private String completeEmpCd;
	@Column(length = 64)
	private String completeEmpName;

	/**
	 * 提交日期
	 */
	@Column
	private Long completeDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade({ CascadeType.ALL })
	@JoinColumn(name = "creditEntrustId")
	private CreditEntrust creditEntrust;

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

	public BigDecimal getRedemptionAmount() {
		return redemptionAmount;
	}

	public void setRedemptionAmount(BigDecimal redemptionAmount) {
		this.redemptionAmount = redemptionAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTempUuid() {
		return tempUuid;
	}

	public void setTempUuid(String tempUuid) {
		this.tempUuid = tempUuid;
	}

	public CreditEntrust getCreditEntrust() {
		return creditEntrust;
	}

	public void setCreditEntrust(CreditEntrust creditEntrust) {
		this.creditEntrust = creditEntrust;
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

	public Long getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Long completeDate) {
		this.completeDate = completeDate;
	}

}
