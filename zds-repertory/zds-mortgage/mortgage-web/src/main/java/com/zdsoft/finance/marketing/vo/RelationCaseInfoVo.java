package com.zdsoft.finance.marketing.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/** 
* 版权所有：重庆正大华日软件有限公司
* @Title: RelationCaseInfoVo.java 
* @Package com.zdsoft.finance.marketing.vo 
* @Description: 关联案件信息Vo对象
* @author jingyh 
* @date 2017年2月24日 下午2:20:28 
* @version V1.0 
*/
public class RelationCaseInfoVo implements Serializable {

	private static final long serialVersionUID = 4424795474068790426L;
	
	/**
	 * 案件Id
	 */
	private String caseApplyId;
	
	/**
	 * 案件号
	 */
	private String caseApplyCode;
	
	/**
	 * 主借人Id
	 */
	private String customerId;
	
	/**
	 * 主借人名称
	 */
	private String customerName;
	
	/**
	 * 贷款金额
	 */
	private BigDecimal caseApplyAmount;
	
	/**
	 * 逾期金额
	 */
	private BigDecimal overdueAmount;
	
	/**
	 * 逾期日期
	 */
	private Long overdueDate;
	
	/**
	 * 逾期天数
	 */
	private Integer overdueDays;
	
	/**
	 * 押品Id
	 */
	private String collateralId;
	
	/**
	 * 小区名称
	 */
	private String houseAddress;
	
	/**
	 * 房产属性
	 */
	private String houseProperty;
	
	/**
	 * 房产属性名称
	 */
	private String housePropertyName;
	
	/**
	 * 综合评估价
	 */
	private BigDecimal synthesizePrice;
	
	/**
	 * 楼龄
	 */
	private Integer houseAge;
	
	/**
	 * 面积
	 */
	private String houseArea;
	
	/**
	 * 抵押模式
	 */
	private String pledgeType;
	
	/**
	 * 抵押模式名称
	 */
	private String pledgeTypeName;
	
	/**
	 * 是否有查看详情权限：1：有，0：无
	 */
	private Integer hasViewPower;

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public String getCaseApplyCode() {
		return caseApplyCode;
	}

	public void setCaseApplyCode(String caseApplyCode) {
		this.caseApplyCode = caseApplyCode;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public BigDecimal getCaseApplyAmount() {
		return caseApplyAmount;
	}

	public void setCaseApplyAmount(BigDecimal caseApplyAmount) {
		this.caseApplyAmount = caseApplyAmount;
	}

	public BigDecimal getOverdueAmount() {
		return overdueAmount;
	}

	public void setOverdueAmount(BigDecimal overdueAmount) {
		this.overdueAmount = overdueAmount;
	}

	public Long getOverdueDate() {
		return overdueDate;
	}

	public void setOverdueDate(Long overdueDate) {
		this.overdueDate = overdueDate;
	}

	public Integer getOverdueDays() {
		return overdueDays;
	}

	public void setOverdueDays(Integer overdueDays) {
		this.overdueDays = overdueDays;
	}

	public String getCollateralId() {
		return collateralId;
	}

	public void setCollateralId(String collateralId) {
		this.collateralId = collateralId;
	}

	public String getHouseAddress() {
		return houseAddress;
	}

	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}

	public String getHouseProperty() {
		return houseProperty;
	}

	public void setHouseProperty(String houseProperty) {
		this.houseProperty = houseProperty;
	}

	public String getHousePropertyName() {
		return housePropertyName;
	}

	public void setHousePropertyName(String housePropertyName) {
		this.housePropertyName = housePropertyName;
	}

	public BigDecimal getSynthesizePrice() {
		return synthesizePrice;
	}

	public void setSynthesizePrice(BigDecimal synthesizePrice) {
		this.synthesizePrice = synthesizePrice;
	}

	public Integer getHouseAge() {
		return houseAge;
	}

	public void setHouseAge(Integer houseAge) {
		this.houseAge = houseAge;
	}

	public String getHouseArea() {
		return houseArea;
	}

	public void setHouseArea(String houseArea) {
		this.houseArea = houseArea;
	}

	public String getPledgeType() {
		return pledgeType;
	}

	public void setPledgeType(String pledgeType) {
		this.pledgeType = pledgeType;
	}

	public String getPledgeTypeName() {
		return pledgeTypeName;
	}

	public void setPledgeTypeName(String pledgeTypeName) {
		this.pledgeTypeName = pledgeTypeName;
	}

	public Integer getHasViewPower() {
		return hasViewPower;
	}

	public void setHasViewPower(Integer hasViewPower) {
		this.hasViewPower = hasViewPower;
	}

}
