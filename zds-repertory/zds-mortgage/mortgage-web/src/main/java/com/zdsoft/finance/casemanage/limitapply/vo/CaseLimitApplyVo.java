package com.zdsoft.finance.casemanage.limitapply.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.casemanage.limitapply.entity.CaseLimitApply;
import com.zdsoft.finance.common.base.BaseVo;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:CaseLimitApplyVo.java
 * @Package:com.zdsoft.finance.casemanage.limitapply.vo
 * @Description:案件额度申请Vo
 * @author: xiongpan
 * @date:2017年1月7日 下午10:03:06
 * @version:v1.0
 */
public class CaseLimitApplyVo extends BaseVo<CaseLimitApply>{
	
	private static final long serialVersionUID = -2361725760132354363L;

	/**
	 * 当前申请额度
	 */
	private BigDecimal currentApplyLimit;
	
	/**
	 * 申请额度人id
	 */
	private String limitApplierEmpId;
	
	/**
	 * 额度申请人姓名
	 */
	private String limitApplierEmpName;
	
	/**
	 * 申请时间
	 */
	private Long applyDate;
	
	/**
	 * 分配日期
	 */
	private Long allotDate;
	
	/**
	 * 取消额度人id
	 */
	private String limitCancelEmpId;
	
	/**
	 * 取消额度人姓名
	 */
	private String limitCancelEmpName;
	
	/**
	 * 取消时间
	 */
	private Long cancelDate;
	
	
	/**
	 * 资金计划名称(规则而来)
	 */
	private String fundPlanName;
	
	/**
	 * 更新时间字符串
	 */
	private String updateTimeStr;
		

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

	public String getFundPlanName() {
		return fundPlanName;
	}

	public void setFundPlanName(String fundPlanName) {
		this.fundPlanName = fundPlanName;
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}

	
	public CaseLimitApply toPo(){
		CaseLimitApply po = new CaseLimitApply();
		return super.toPo(this, po);
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
	
	
}
