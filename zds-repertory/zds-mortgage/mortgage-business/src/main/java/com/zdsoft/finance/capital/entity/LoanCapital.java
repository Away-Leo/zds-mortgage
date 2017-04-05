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
 * @Title: LoanCapital.java 
 * @ClassName: LoanCapital 
 * @Description: 专户贷方资金跟踪
 * @author liuwei 
 * @date 2017年2月8日 上午10:24:51 
 * @version V1.0
 */
@Entity
@Table(name = "cptl_credit_loan_capital")
public class LoanCapital extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5719010643899903460L;

	/**
	 * 贷方类型
	 */
	@Column(length = 32)
	private String lenderType;

	/**
	 * 贷方名称
	 */
	@Column(length = 64)
	private String lenderName;

	/**
	 * 发生总金额
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal totalAmount = BigDecimal.ZERO;

	/**
	 * 实际发生日期
	 */
	@Column
	private Long happenDate;

	/**
	 * 提交日期
	 */
	@Column
	private Long completeDate;

	/**
	 * 资金状态
	 */
	@Column(length = 32)
	private String capitalState;

	/**
	 * 状态
	 */
	@Column
	private Integer status;

	/**
	 * 备注
	 */
	@Column(length = 500)
	private String remark;

	/**
	 * 提交人
	 */
	@Column(length = 32)
	private String completeEmpCd;
	@Column(length = 64)
	private String completeEmpName;

	/**
	 * 所属信托计划
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade({ CascadeType.ALL })
	@JoinColumn(name = "creditEntrustId")
	private CreditEntrust creditEntrust;

	/**
	 * 对应费用项
	 */
	private transient List<CreditEntrustFeeItem> creditEntrustFeeItems;

	/**
	 * 临时字段
	 */
	@Column(length = 32)
	private String tempUuid;

	public String getLenderType() {
		return lenderType;
	}

	public void setLenderType(String lenderType) {
		this.lenderType = lenderType;
	}

	public String getLenderName() {
		return lenderName;
	}

	public void setLenderName(String lenderName) {
		this.lenderName = lenderName;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Long getHappenDate() {
		return happenDate;
	}

	public void setHappenDate(Long happenDate) {
		this.happenDate = happenDate;
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
