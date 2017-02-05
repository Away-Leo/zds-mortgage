package com.zdsoft.finance.casemanage.limitapply.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.zdsoft.finance.capital.entity.CreditEntrust;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.framework.core.common.domain.BaseEntity;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:CaseLimitApply.java
 * @Package:com.zdsoft.finance.casemanage.limitapply.entity
 * @Description:案件额度申请信息
 * @author: xiongpan
 * @date:2017年1月7日 下午9:56:19
 * @version:v1.0
 */
@Entity
@Table(name="case_case_limit_apply")
public class CaseLimitApply extends BaseEntity{


	private static final long serialVersionUID = 6296944068509957142L;
	
	/**
	 * 当前申请额度
	 */
	@Column(precision=18,scale=2)
	private BigDecimal currentApplyLimit;
	
	
	/**
	 * 申请额度人id
	 */
	@Column(length=32)
	private String limitApplierEmpId;
	
	/**
	 * 额度申请人姓名
	 */
	@Column(length=32)
	private String limitApplierEmpName;
	
	/**
	 * 申请时间
	 */
	@Column
	private Long applyDate;
	
	/**
	 * 分配日期
	 */
	@Column
	private Long allotDate;
	
	/**
	 * 取消额度人id
	 */
	@Column(length=32)
	private String limitCancelEmpId;
	
	/**
	 * 取消额度人姓名
	 */
	@Column(length=32)
	private String limitCancelEmpName;
	
	/**
	 * 取消时间
	 */
	@Column
	private Long cancelDate;
	
	/**
	 * 对应的案件
	 */
	@ManyToOne
	@JoinColumn(name="caseApplyId")
	private CaseApply caseApply;
	
	@Transient
	private String caseApplyId;
	
	/**
	 * 对应的资金计划
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="creditEntrustId", nullable = true, unique = true)
	private CreditEntrust creditEntrust;
	
	
	/**
	 * 资金计划名称(根据"产品+成数"找到具体的资金计划,规则来自于产品定义)
	 */
	@Column(length=32)
	private String fundPlanName;
	
	/**
	 * 生效状态
	 */
	@Column(length = 32)
	private String effectiveStatus;


	public String getCaseApplyId() {
		return caseApplyId;
	}


	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}


	public BigDecimal getCurrentApplyLimit() {
		return currentApplyLimit;
	}


	public void setCurrentApplyLimit(BigDecimal currentApplyLimit) {
		this.currentApplyLimit = currentApplyLimit;
	}


	public String getLimitApplierEmpId() {
		return limitApplierEmpId;
	}


	public void setLimitApplierEmpId(String limitApplierEmpId) {
		this.limitApplierEmpId = limitApplierEmpId;
	}


	public Long getApplyDate() {
		return applyDate;
	}


	public void setApplyDate(Long applyDate) {
		this.applyDate = applyDate;
	}


	public Long getAllotDate() {
		return allotDate;
	}


	public void setAllotDate(Long allotDate) {
		this.allotDate = allotDate;
	}


	public String getLimitCancelEmpId() {
		return limitCancelEmpId;
	}


	public void setLimitCancelEmpId(String limitCancelEmpId) {
		this.limitCancelEmpId = limitCancelEmpId;
	}


	public Long getCancelDate() {
		return cancelDate;
	}


	public void setCancelDate(Long cancelDate) {
		this.cancelDate = cancelDate;
	}


	public CaseApply getCaseApply() {
		return caseApply;
	}


	public void setCaseApply(CaseApply caseApply) {
		this.caseApply = caseApply;
	}


	public String getFundPlanName() {
		return fundPlanName;
	}


	public void setFundPlanName(String fundPlanName) {
		this.fundPlanName = fundPlanName;
	}


	public String getLimitApplierEmpName() {
		return limitApplierEmpName;
	}


	public void setLimitApplierEmpName(String limitApplierEmpName) {
		this.limitApplierEmpName = limitApplierEmpName;
	}


	public String getLimitCancelEmpName() {
		return limitCancelEmpName;
	}


	public void setLimitCancelEmpName(String limitCancelEmpName) {
		this.limitCancelEmpName = limitCancelEmpName;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public CreditEntrust getCreditEntrust() {
		return creditEntrust;
	}


	public void setCreditEntrust(CreditEntrust creditEntrust) {
		this.creditEntrust = creditEntrust;
	}


	public String getEffectiveStatus() {
		return effectiveStatus;
	}


	public void setEffectiveStatus(String effectiveStatus) {
		this.effectiveStatus = effectiveStatus;
	}
	
	
	
	

}
