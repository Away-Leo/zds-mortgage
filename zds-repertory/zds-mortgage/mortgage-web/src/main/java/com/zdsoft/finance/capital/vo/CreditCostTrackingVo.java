package com.zdsoft.finance.capital.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.capital.entity.CreditCostTracking;
import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.spi.common.dto.StatusNm;

/**
 * 应付费用跟踪Vo
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
public class CreditCostTrackingVo extends BaseVo<CreditCostTracking> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -925362596227170942L;

	/**
	 * 支出类型
	 */
	private String expenditureType;

	/**
	 * 支出类型名称
	 */
	private String expenditureTypeName;

	/**
	 * 名称
	 */
	private String costName;

	/**
	 * 摘要
	 */
	private String summary;

	/**
	 * 应付费用
	 */
	private BigDecimal totalAmountPayable;

	/**
	 * 总金额
	 */
	private BigDecimal totalAmount;

	/**
	 * 应付日期
	 */
	private Long payDate;

	/**
	 * 提交人
	 */
	private String completeEmpCd;
	private String completeEmpName;
	/**
	 * 提交日期
	 */
	private Long completeDate;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 提交状态
	 */
	private Integer status;

	/**
	 * 提交状态名称
	 */
	private String statusName;

	/**
	 * 临时UUID
	 */
	private String tempUuid;

	/**
	 * 信托计划id
	 */
	private String creditEntrustId;

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

	public BigDecimal getTotalAmountPayable() {
		return totalAmountPayable;
	}

	public void setTotalAmountPayable(BigDecimal totalAmountPayable) {
		this.totalAmountPayable = totalAmountPayable;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getExpenditureTypeName() {
		return expenditureTypeName;
	}

	public void setExpenditureTypeName(String expenditureTypeName) {
		this.expenditureTypeName = expenditureTypeName;
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

	public String getTempUuid() {
		return tempUuid;
	}

	public void setTempUuid(String tempUuid) {
		this.tempUuid = tempUuid;
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

	public CreditCostTrackingVo() {
		super();
	}

	public CreditCostTrackingVo(CreditCostTracking creditCostTracking) {
		super(creditCostTracking);
		this.setStatusName(StatusNm.getName(creditCostTracking.getStatus()));
	}

	public CreditCostTrackingVo(CreditCostTracking creditCostTracking, String[] args, String[] simpleArgs) {
		super(creditCostTracking, args, simpleArgs);
		this.setStatusName(StatusNm.getName(creditCostTracking.getStatus()));
	}

	public CreditCostTracking toPo() {
		CreditCostTracking creditCostTracking = new CreditCostTracking();
		return super.toPo(this, creditCostTracking);
	}

}
