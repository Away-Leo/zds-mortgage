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
 * 信托计划借方资金
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
@Entity
@Table(name = "caal_credit_entrust_debit")
public class CreditEntrustDebit extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8624927753118837275L;

	/**
	 * 支出类型
	 */
	@Column(length = 32)
	private String debitType;

	/**
	 * 借方类型
	 */
	@Column(length = 32)
	private String debtorType;

	/**
	 * 对象名称
	 */
	@Column(length = 64)
	private String objectName;

	/**
	 * 支出日期
	 */
	@Column
	private Long expenditureDate;

	/**
	 * 实际转出日期
	 */
	@Column
	private Long actualOutDate;

	/**
	 * 提交日期
	 */
	@Column
	private Long completeDate;

	/**
	 * 发生总金额
	 */
	@Column(precision = 30, scale = 12)
	private BigDecimal totalAmount;

	/**
	 * 资金状态
	 */
	@Column(length = 32)
	private String capitalState;

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

	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade({ CascadeType.ALL })
	@JoinColumn(name = "creditEntrustId")
	private CreditEntrust creditEntrust;

	/**
	 * 费用
	 */
	private transient List<CreditEntrustFeeItem> creditEntrustFeeItems;

	@Column(length = 32)
	private String tempUuid;

	public String getDebtorType() {
		return debtorType;
	}

	public void setDebtorType(String debtorType) {
		this.debtorType = debtorType;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public Long getExpenditureDate() {
		return expenditureDate;
	}

	public void setExpenditureDate(Long expenditureDate) {
		this.expenditureDate = expenditureDate;
	}

	public Long getActualOutDate() {
		return actualOutDate;
	}

	public void setActualOutDate(Long actualOutDate) {
		this.actualOutDate = actualOutDate;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getCapitalState() {
		return capitalState;
	}

	public void setCapitalState(String capitalState) {
		this.capitalState = capitalState;
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

	public String getDebitType() {
		return debitType;
	}

	public void setDebitType(String debitType) {
		this.debitType = debitType;
	}

	public List<CreditEntrustFeeItem> getCreditEntrustFeeItems() {
		return creditEntrustFeeItems;
	}

	public void setCreditEntrustFeeItems(List<CreditEntrustFeeItem> creditEntrustFeeItems) {
		this.creditEntrustFeeItems = creditEntrustFeeItems;
	}

	public String getTempUuid() {
		return tempUuid;
	}

	public void setTempUuid(String tempUuid) {
		this.tempUuid = tempUuid;
	}

}
