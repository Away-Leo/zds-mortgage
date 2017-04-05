package com.zdsoft.finance.contract.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/*
 * 案件合同盖章申请单
 * @author huangdongkui
 * @date 2017-1-16
 * */
@Entity
@Table(name = "con_contract_seal_apply")
public class CoactCaseApplyContractSealApply extends BaseEntity {
	private static final long serialVersionUID = -3768555669980589669L;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCaseContractId() {
		return caseContractId;
	}

	public void setCaseContractId(String caseContractId) {
		this.caseContractId = caseContractId;
	}

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public String getTrackingNoSend() {
		return trackingNoSend;
	}

	public void setTrackingNoSend(String trackingNoSend) {
		this.trackingNoSend = trackingNoSend;
	}

	public String getTrackingNoReceive() {
		return trackingNoReceive;
	}

	public void setTrackingNoReceive(String trackingNoReceive) {
		this.trackingNoReceive = trackingNoReceive;
	}

	public String getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}

	public Long getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Long applyDate) {
		this.applyDate = applyDate;
	}

	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}

	/**
	 * ID
	 */
	@Column(length = 32)
	private String id;

	/**
	 * 案件合同ID
	 */
	@Column(length = 32)
	private String caseContractId;

	/**
	 * 案件ID
	 */
	@Column(length = 32)
	private String caseApplyId;

	/**
	 * 寄出快递单号
	 */
	@Column(length = 64)
	private String trackingNoSend;

	/**
	 * 寄回快递单号
	 */
	@Column(length = 64)
	private String trackingNoReceive;

	/**
	 * 申请人ID
	 */
	@Column(length = 32)
	private String applicantId;

	/**
	 * 申请时间
	 */
	@Column
	private Long applyDate;

	/**
	 * 申请单状态
	 */
	@Column(length = 20)
	private String applyStatus;

}
