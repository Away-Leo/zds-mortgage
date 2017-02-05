package com.zdsoft.finance.capital.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.capital.entity.CreditEntrustDebit;
import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.spi.common.dto.StatusNm;

/**
 * 信托计划借方资金Vo
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
public class CreditEntrustDebitVo extends BaseVo<CreditEntrustDebit> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5434124555256007361L;

	/**
	 * 支出类型
	 */
	private String debitType;

	/**
	 * 支出类型名称
	 */
	private String debitTypeName;

	/**
	 * 借方类型
	 */
	private String debtorType;

	/**
	 * 借方类型名称
	 */
	private String debtorTypeName;

	/**
	 * 对象名称
	 */
	private String objectName;

	/**
	 * 支出日期
	 */
	private Long expenditureDate;

	/**
	 * 实际转出日期
	 */
	private Long actualOutDate;

	/**
	 * 提交日期
	 */
	private Long completeDate;

	/**
	 * 发生总金额
	 */
	private BigDecimal totalAmount;
	/**
	 * 资金状态
	 */
	private String capitalState;

	/**
	 * 资金状态Nm
	 */
	private String capitalStateName;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 提交状态
	 */
	private Integer status;
	private String statusName;

	/**
	 * 提交人
	 */
	private String completeEmpCd;
	private String completeEmpName;

	/**
	 * 临时id
	 */
	private String tempUuid;

	/**
	 * 信托计划id
	 */
	private String creditEntrustId;

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

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getDebtorTypeName() {
		return debtorTypeName;
	}

	public void setDebtorTypeName(String debtorTypeName) {
		this.debtorTypeName = debtorTypeName;
	}

	public String getDebitType() {
		return debitType;
	}

	public void setDebitType(String debitType) {
		this.debitType = debitType;
	}

	public String getDebitTypeName() {
		return debitTypeName;
	}

	public void setDebitTypeName(String debitTypeName) {
		this.debitTypeName = debitTypeName;
	}

	public String getCapitalState() {
		return capitalState;
	}

	public void setCapitalState(String capitalState) {
		this.capitalState = capitalState;
	}

	public String getTempUuid() {
		return tempUuid;
	}

	public void setTempUuid(String tempUuid) {
		this.tempUuid = tempUuid;
	}

	public String getCapitalStateName() {
		return capitalStateName;
	}

	public void setCapitalStateName(String capitalStateName) {
		this.capitalStateName = capitalStateName;
	}

	public String getCreditEntrustId() {
		return creditEntrustId;
	}

	public void setCreditEntrustId(String creditEntrustId) {
		this.creditEntrustId = creditEntrustId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public CreditEntrustDebitVo() {
		super();
	}

	public CreditEntrustDebitVo(CreditEntrustDebit creditEntrustDebit) {
		super(creditEntrustDebit);
		this.setStatusName(StatusNm.getName(creditEntrustDebit.getStatus()));
	}

	public CreditEntrustDebitVo(CreditEntrustDebit creditEntrustDebit, String[] args, String[] simpleArgs) {
		super(creditEntrustDebit, args, simpleArgs);
		this.setStatusName(StatusNm.getName(creditEntrustDebit.getStatus()));
	}

	public CreditEntrustDebit toPo() {
		CreditEntrustDebit debit = new CreditEntrustDebit();
		return super.toPo(this, debit);
	}

}
