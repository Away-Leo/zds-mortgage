package com.zdsoft.finance.capital.entity;

import java.math.BigDecimal;
import java.util.List;

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
 * @Title: CreditCostTracking.java
 * @ClassName: CreditCostTracking
 * @Description: 应付费用跟踪
 * @author liuwei
 * @date 2017年2月8日 上午10:22:15
 * @version V1.0
 */
@Entity
@Table(name = "cptl_credit_cost_tracking")
public class CreditCostTracking extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8185209082557096984L;

	/**
	 * 支出类型
	 */
	@Column(length = 32)
	private String expenditureType;

	/**
	 * 名称
	 */
	@Column(length = 256)
	private String costName;

	/**
	 * 摘要
	 */
	@Column(length = 2000)
	private String summary;

	/**
	 * 应付费用
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal totalAmount = BigDecimal.ZERO;

	/**
	 * 应付日期
	 */
	@Column
	private Long payDate;

	/**
	 * 提交日期
	 */
	@Column
	private Long completeDate;

	/**
	 * 提交人
	 */
	@Column(length = 32)
	private String completeEmpCd;
	@Column(length = 64)
	private String completeEmpName;

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
	 * 所属信托计划
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade({ CascadeType.ALL })
	@JoinColumn(name = "creditEntrustId")
	private CreditEntrust creditEntrust;

	/**
	 * 临时对象信托计划费用项
	 */
	private transient List<CreditEntrustFeeItem> creditEntrustFeeItems;

	/**
	 * 临时id
	 */
	@Column(length = 32)
	private String tempUuid;

	public String getExpenditureType() {
		return expenditureType;
	}

	public void setExpenditureType(String expenditureType) {
		this.expenditureType = expenditureType;
	}

	public String getCostName() {
		return costName;
	}

	public void setCostName(String costName) {
		this.costName = costName;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Long getPayDate() {
		return payDate;
	}

	public void setPayDate(Long payDate) {
		this.payDate = payDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Long completeDate) {
		this.completeDate = completeDate;
	}

	public CreditEntrust getCreditEntrust() {
		return creditEntrust;
	}

	public void setCreditEntrust(CreditEntrust creditEntrust) {
		this.creditEntrust = creditEntrust;
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

	public List<CreditEntrustFeeItem> getCreditEntrustFeeItems() {
		return creditEntrustFeeItems;
	}

	public void setCreditEntrustFeeItems(List<CreditEntrustFeeItem> creditEntrustFeeItems) {
		this.creditEntrustFeeItems = creditEntrustFeeItems;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTempUuid() {
		return tempUuid;
	}

	public void setTempUuid(String tempUuid) {
		this.tempUuid = tempUuid;
	}

}
