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
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: SpareCapital.java
 * @ClassName: SpareCapital
 * @Description: 备付资金跟踪
 * @author liuwei
 * @date 2017年2月8日 上午10:24:57
 * @version V1.0
 */
@Entity
@Table(name = "cptl_credit_spare_capital")
public class SpareCapital extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8734339582752480583L;

	/**
	 * 操作类型
	 */
	@Column(length = 32)
	private String operationType;

	/**
	 * 请款金额
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal applyAmount = BigDecimal.ZERO;

	/**
	 * 用款日期
	 */
	@Column
	private Long useDate;

	/**
	 * 提交日期
	 */
	@Column
	private Long completeDate;

	/**
	 * 请款备注
	 */
	@Column(length = 256)
	private String applyRemark;

	/**
	 * 确认到账操作类型
	 */
	@Column(length = 32)
	private String actualOperationType;

	/**
	 * 实际到账日期
	 */
	@Column
	private Long actualArrivalDate;

	/**
	 * 实际到账金额
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal actualAmount = BigDecimal.ZERO;

	/**
	 * 未分配到账金额
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal distributionAmount = BigDecimal.ZERO;

	/**
	 * 确认到账备注
	 */
	@Column(length = 256)
	private String actualRemark;

	/**
	 * 提交状态
	 */
	@Column
	private Integer status;

	/**
	 * 到账状态
	 */
	@Column
	private Integer actualStatus;

	/**
	 * 提交人
	 */
	@Column(length = 32)
	private String completeEmpCd;
	@Column(length = 64)
	private String completeEmpName;

	/**
	 * 信托计划临时id
	 */
	@Column(length = 32)
	private String tempUuid;

	/**
	 * 所属信托计划
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade({ CascadeType.ALL })
	@JoinColumn(name = "creditEntrustId")
	private CreditEntrust creditEntrust;

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public BigDecimal getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(BigDecimal applyAmount) {
		this.applyAmount = applyAmount;
	}

	public Long getUseDate() {
		return useDate;
	}

	public void setUseDate(Long useDate) {
		this.useDate = useDate;
	}

	public String getApplyRemark() {
		return applyRemark;
	}

	public void setApplyRemark(String applyRemark) {
		this.applyRemark = applyRemark;
	}

	public String getActualOperationType() {
		return actualOperationType;
	}

	public void setActualOperationType(String actualOperationType) {
		this.actualOperationType = actualOperationType;
	}

	public Long getActualArrivalDate() {
		return actualArrivalDate;
	}

	public void setActualArrivalDate(Long actualArrivalDate) {
		this.actualArrivalDate = actualArrivalDate;
	}

	public String getActualRemark() {
		return actualRemark;
	}

	public void setActualRemark(String actualRemark) {
		this.actualRemark = actualRemark;
	}

	public CreditEntrust getCreditEntrust() {
		return creditEntrust;
	}

	public void setCreditEntrust(CreditEntrust creditEntrust) {
		this.creditEntrust = creditEntrust;
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

	public BigDecimal getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}

	public BigDecimal getDistributionAmount() {
		return distributionAmount;
	}

	public void setDistributionAmount(BigDecimal distributionAmount) {
		this.distributionAmount = distributionAmount;
	}

	public Integer getActualStatus() {
		return actualStatus;
	}

	public void setActualStatus(Integer actualStatus) {
		this.actualStatus = actualStatus;
	}

	public String getTempUuid() {
		return tempUuid;
	}

	public void setTempUuid(String tempUuid) {
		this.tempUuid = tempUuid;
	}

}
