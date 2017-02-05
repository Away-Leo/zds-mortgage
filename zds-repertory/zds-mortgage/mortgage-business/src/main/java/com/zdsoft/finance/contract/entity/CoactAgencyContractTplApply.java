package com.zdsoft.finance.contract.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 合同报备表
 * 
 * @author wangnengduo
 *
 * 
 *         )
 */
@Entity
@Table(name = "coact_agency_contract_tpl_apply")

public class CoactAgencyContractTplApply  extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	@Column(length = 32)
	private String id;
	/**
	 * 申请编号
	 */
	@Column(length = 32)
	private String applyNo;
	/**
	 * 申请类型
	 */
	@Column(length = 20)
	private String applyType;
	/**
	 * 申请单标题
	 */
	@Column(length = 64)
	private String applyTitle;
	/**
	 * 申请状态
	 */
	@Column(length = 20)
	private String applyState;
	/**
	 * 申请原因
	 */
	@Column(length = 512)
	private String applyReason;
	/**
	 * 申请时间
	 */
	private Long applyDate;
	/**
	 * 申请人ID
	 */
	@Column(length = 32)
	private String applicantId;
	/**
	 * 所属机构
	 */
	@Column(length = 32)
	private String agencyId;

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getApplyNo() {
		return applyNo;
	}
	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}
	public String getApplyType() {
		return applyType;
	}
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	public String getApplyTitle() {
		return applyTitle;
	}
	public void setApplyTitle(String applyTitle) {
		this.applyTitle = applyTitle;
	}
	public String getApplyState() {
		return applyState;
	}
	public void setApplyState(String applyState) {
		this.applyState = applyState;
	}
	public String getApplyReason() {
		return applyReason;
	}
	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}
	public Long getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Long applyDate) {
		this.applyDate = applyDate;
	}
	public String getApplicantId() {
		return applicantId;
	}
	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}
	public String getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
}
