package com.zdsoft.finance.capital.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 信托计划操作日志
 * 
 * @createTime:2017年1月12日
 * @author liuwei
 * @version 1.0
 */
@Entity
@Table(name = "caal_operation_log")
public class CreditEntrustOperationLog extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7655174447649037010L;

	/**
	 * 操作类型
	 */
	@Column(length = 32)
	private String operationType;

	/**
	 * 操作内容
	 */
	@Column(length = 2000)
	private String operationContent;

	/**
	 * 处理人Cd
	 */
	@Column(length = 32)
	private String operationEmpCd;

	/**
	 * 处理人Nm
	 */
	@Column(length = 64)
	private String operationEmpName;

	/**
	 * 操作时间
	 */
	@Column
	private Long operationDate;

	/**
	 * 实际时间
	 */
	@Column
	private Long actualDate;

	/**
	 * 状态
	 */
	@Column
	private Integer status;

	/**
	 * 关联业务标识
	 */
	@Column(length = 32)
	private String businessId;

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getOperationContent() {
		return operationContent;
	}

	public void setOperationContent(String operationContent) {
		this.operationContent = operationContent;
	}

	public String getOperationEmpCd() {
		return operationEmpCd;
	}

	public void setOperationEmpCd(String operationEmpCd) {
		this.operationEmpCd = operationEmpCd;
	}

	public String getOperationEmpName() {
		return operationEmpName;
	}

	public void setOperationEmpName(String operationEmpName) {
		this.operationEmpName = operationEmpName;
	}

	public Long getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Long operationDate) {
		this.operationDate = operationDate;
	}

	public Long getActualDate() {
		return actualDate;
	}

	public void setActualDate(Long actualDate) {
		this.actualDate = actualDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

}