package com.zdsoft.finance.capital.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.capital.entity.SpareCapital;
import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.spi.common.dto.StatusNm;

/**
 * 备付资金跟踪
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
public class SpareCapitalVo extends BaseVo<SpareCapital> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 378595957905983945L;

	/**
	 * 操作类型
	 */
	private String operationType;

	/**
	 * 操作类型名称
	 */
	private String operationTypeName;

	/**
	 * 请款金额
	 */
	private BigDecimal applyAmount;

	/**
	 * 用款日期
	 */
	private Long useDate;

	/**
	 * 请款备注
	 */
	private String applyRemark;

	/**
	 * 确认到账操作类型
	 */
	private String actualOperationType;

	/**
	 * 实际到账日期
	 */
	private Long actualArrivalDate;

	/**
	 * 实际到账金额
	 */
	private BigDecimal actualAmount;

	/**
	 * 未分配到账金额
	 */
	private BigDecimal distributionAmount;

	/**
	 * 提交日期
	 */
	private Long completeDate;

	/**
	 * 确认到账备注
	 */
	private String actualRemark;

	/**
	 * 提交状态
	 */
	private Integer status;
	private String statusName;

	/**
	 * 到账状态
	 */
	private Integer actualStatus;

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

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public BigDecimal getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(BigDecimal applyAmount) {
		this.applyAmount = applyAmount;
	}

	public Long getUseDate() {
		return useDate;
	}

	public void setUseDate(Long useDate) {
		this.useDate = useDate;
	}

	public String getApplyRemark() {
		return applyRemark;
	}

	public void setApplyRemark(String applyRemark) {
		this.applyRemark = applyRemark;
	}

	public String getActualOperationType() {
		return actualOperationType;
	}

	public void setActualOperationType(String actualOperationType) {
		this.actualOperationType = actualOperationType;
	}

	public Long getActualArrivalDate() {
		return actualArrivalDate;
	}

	public void setActualArrivalDate(Long actualArrivalDate) {
		this.actualArrivalDate = actualArrivalDate;
	}

	public String getActualRemark() {
		return actualRemark;
	}

	public void setActualRemark(String actualRemark) {
		this.actualRemark = actualRemark;
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

	public String getOperationTypeName() {
		return operationTypeName;
	}

	public void setOperationTypeName(String operationTypeName) {
		this.operationTypeName = operationTypeName;
	}

	public BigDecimal getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}

	public BigDecimal getDistributionAmount() {
		return distributionAmount;
	}

	public void setDistributionAmount(BigDecimal distributionAmount) {
		this.distributionAmount = distributionAmount;
	}

	public Integer getActualStatus() {
		return actualStatus;
	}

	public void setActualStatus(Integer actualStatus) {
		this.actualStatus = actualStatus;
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

	public SpareCapitalVo() {
		super();
	}

	public SpareCapitalVo(SpareCapital spareCapital) {
		super(spareCapital);
		this.setStatusName(StatusNm.getName(spareCapital.getStatus()));
	}

	public SpareCapitalVo(SpareCapital spareCapital, String[] args, String[] simpleArgs) {
		super(spareCapital, args, simpleArgs);
		this.setStatusName(StatusNm.getName(spareCapital.getStatus()));
	}

	public SpareCapital toPo() {
		SpareCapital spareCapital = new SpareCapital();
		return super.toPo(this, spareCapital);
	}

}
