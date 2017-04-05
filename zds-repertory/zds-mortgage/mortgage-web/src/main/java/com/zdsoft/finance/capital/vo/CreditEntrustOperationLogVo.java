package com.zdsoft.finance.capital.vo;

import com.zdsoft.finance.capital.entity.CreditEntrustOperationLog;
import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.spi.common.dto.OperationTypeNm;
import com.zdsoft.finance.spi.common.dto.StatusNm;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CreditEntrustOperationLogVo.java
 * @ClassName: CreditEntrustOperationLogVo
 * @Description: 信托计划操作日志Vo
 * @author liuwei
 * @date 2017年3月6日 下午4:19:45
 * @version V1.0
 */
public class CreditEntrustOperationLogVo extends BaseVo<CreditEntrustOperationLog> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7655174447649037010L;

	/**
	 * 操作类型
	 */
	private String operationType;

	private String operationTypeName;

	/**
	 * 操作内容
	 */
	private String operationContent;

	/**
	 * 处理人Cd
	 */
	private String operationEmpCd;

	/**
	 * 处理人Nm
	 */
	private String operationEmpName;

	/**
	 * 操作时间
	 */
	private Long operationDate;

	/**
	 * 操作时间name
	 */
	private String operationDateName;

	/**
	 * 实际时间
	 */
	private Long actualDate;

	/**
	 * 状态
	 */
	private Integer status;
	private String statusName;

	/**
	 * 关联业务标识
	 */
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

	public String getOperationTypeName() {
		return operationTypeName;
	}

	public void setOperationTypeName(String operationTypeName) {
		this.operationTypeName = operationTypeName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getOperationDateName() {
		return operationDateName;
	}

	public void setOperationDateName(String operationDateName) {
		this.operationDateName = operationDateName;
	}

	public CreditEntrustOperationLogVo() {
		super();
	}

	public CreditEntrustOperationLogVo(CreditEntrustOperationLog log) {
		super(log);
	}

	public CreditEntrustOperationLogVo(CreditEntrustOperationLog log, String[] args, String[] simpleArgs) {
		super(log, args, simpleArgs);
		if (ObjectHelper.isNotEmpty(log.getOperationType())) {
			this.setOperationTypeName(OperationTypeNm.getName(log.getOperationType()));
			this.setStatusName(StatusNm.getName(this.getStatus()));
		}
	}

	public CreditEntrustOperationLog toPo() {
		CreditEntrustOperationLog log = new CreditEntrustOperationLog();
		return super.toPo(this, log);
	}

}
