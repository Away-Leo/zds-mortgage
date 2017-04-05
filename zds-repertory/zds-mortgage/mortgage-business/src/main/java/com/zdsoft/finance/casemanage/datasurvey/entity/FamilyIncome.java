package com.zdsoft.finance.casemanage.datasurvey.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: FamilyIncome.java 
 * @ClassName: FamilyIncome 
 * @Description: 客户的家庭收入
 * @author liuhuan 
 * @date 2017年2月9日 下午1:40:13 
 * @version V1.0
 */
@Entity
@Table(name="case_family_income")
public class FamilyIncome extends BaseEntity{
	
	private static final long serialVersionUID = -5060522042767653785L;
	
	/**
	 * 案件Id
	 */
	@Column(length=32)
	private String caseApplyId;
	/**
	 * 户主名
	 */
	@Column(length=128)
	private String houseHolder;
	
	/**
	 * 是否为实际用款人流水
	 */
	@Column(length=20)
	private String isMoney;
	
	/**
	 * 月均收入
	 */
	@Column(precision = 18, scale = 2)
	private BigDecimal monthAmount = BigDecimal.ZERO;
	
	/**
	 * 半年合计收入
	 */
	@Column(precision = 18, scale = 2)
	private BigDecimal totalAmount = BigDecimal.ZERO;
	
	/**
	 * 客户ID
	 */
	@Column(length=32)
	private String customerId;

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public String getHouseHolder() {
		return houseHolder;
	}

	public void setHouseHolder(String houseHolder) {
		this.houseHolder = houseHolder;
	}

	public String getIsMoney() {
		return isMoney;
	}

	public void setIsMoney(String isMoney) {
		this.isMoney = isMoney;
	}

	public BigDecimal getMonthAmount() {
		return monthAmount;
	}

	public void setMonthAmount(BigDecimal monthAmount) {
		this.monthAmount = monthAmount;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	
}
