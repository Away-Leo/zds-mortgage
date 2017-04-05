package com.zdsoft.finance.casemanage.evaluated.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: EvaluatedAppeal.java 
 * @ClassName: EvaluatedAppeal 
 * @Description: 评估价申诉实体
 * @author caixiekang 
 * @date 2017年2月17日 下午5:11:48 
 * @version V1.0
 */
@Entity
@Table(name="case_caseApply_appeal")
public class EvaluatedAppeal extends BaseEntity{
	
	/**
	 * 申诉状态:草稿
	 */
	public static final String DRAFT = "-1";
	/**
	 * 申诉状态:审批中
	 */
	public static final String IN_PROCESS = "0";
	/**
	 * 申诉状态:审批完成
	 */
	public static final String FINISH = "1";
	/**   
	 *  
	 */ 
	private static final long serialVersionUID = -3997493494528678703L;
	
	/**
	 * 房产ID
	 */
	@Column(length = 32)
	private String housePropertyId;
	/**
	 * 风控复议价
	 */
	@Column(precision = 18, scale = 2)
	private BigDecimal appealAprolAmount = BigDecimal.ZERO;
	/**
	 * 申诉人ID
	 */
	@Column(length = 32)
	private String appealEmployeId;
	/**
	 * 申诉人姓名
	 */
	@Column(length = 64)
	private String appealName;
	/**
	 * 申诉时间
	 */
	@Column
	private Long appealDate;
	/**
	 * 有无特殊因素
	 */
	@Column(length = 20)
	private String isSpecificFactor;
	/**
	 * 备注
	 */
	@Column(length = 512)
	private String remark;
	/**
	 * 复议价处理人Id
	 */
	@Column(length = 32)
	private String handleEmployeeId;
	/**
	 * 复议价处理人姓名
	 */
	@Column(length = 64)
	private String handleEmployeeName;
	/**
	 * 复议处理时间
	 */
	@Column
	private Long handleDate;
	/**
	 * 评估价申诉状态
	 */
	@Column(length = 20)
	private String appealStatus;
	
	

	public String getHousePropertyId() {
		return housePropertyId;
	}
	public void setHousePropertyId(String housePropertyId) {
		this.housePropertyId = housePropertyId;
	}

	public BigDecimal getAppealAprolAmount() {
		return appealAprolAmount;
	}
	public void setAppealAprolAmount(BigDecimal appealAprolAmount) {
		this.appealAprolAmount = appealAprolAmount;
	}
	public String getAppealEmployeId() {
		return appealEmployeId;
	}
	public void setAppealEmployeId(String appealEmployeId) {
		this.appealEmployeId = appealEmployeId;
	}
	public String getAppealName() {
		return appealName;
	}
	public void setAppealName(String appealName) {
		this.appealName = appealName;
	}
	public Long getAppealDate() {
		return appealDate;
	}
	public void setAppealDate(Long appealDate) {
		this.appealDate = appealDate;
	}
	public String getIsSpecificFactor() {
		return isSpecificFactor;
	}
	public void setIsSpecificFactor(String isSpecificFactor) {
		this.isSpecificFactor = isSpecificFactor;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getHandleEmployeeId() {
		return handleEmployeeId;
	}
	public void setHandleEmployeeId(String handleEmployeeId) {
		this.handleEmployeeId = handleEmployeeId;
	}
	public String getHandleEmployeeName() {
		return handleEmployeeName;
	}
	public void setHandleEmployeeName(String handleEmployeeName) {
		this.handleEmployeeName = handleEmployeeName;
	}
	public Long getHandleDate() {
		return handleDate;
	}
	public void setHandleDate(Long handleDate) {
		this.handleDate = handleDate;
	}
	public String getAppealStatus() {
		return appealStatus;
	}
	public void setAppealStatus(String appealStatus) {
		this.appealStatus = appealStatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
