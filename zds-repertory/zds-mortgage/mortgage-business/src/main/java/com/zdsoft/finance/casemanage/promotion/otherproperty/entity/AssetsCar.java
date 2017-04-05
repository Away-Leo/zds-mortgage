package com.zdsoft.finance.casemanage.promotion.otherproperty.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:AssetsCar.java
 * @Package:com.zdsoft.finance.casemanage.promotion.otherproperty.entity
 * @Description:其他资产之汽车情况
 * @author: xiongpan
 * @date:2017年2月17日 上午10:20:23
 * @version:v1.0
 */
@Entity
@Table(name = "case_other_assets_car")
public class AssetsCar extends BaseEntity{
	
	private static final long serialVersionUID = -7070297225545891411L;
	
	/**
	 * 型号
	 */
	@Column(length=30)
	private String carType;
	
	/**
	 * 使用年限
	 */
	@Column
	private Integer useYear = new Integer(0);
	
	/**
	 * 价值(元)
	 */
	@Column(precision = 18, scale = 2)
	private BigDecimal worth = BigDecimal.ZERO;
	
	/**
	 * 权属人
	 */
	@Column(length=128)
	private String owner;
	
	/**
	 * 是否在押
	 */
	@Column(length=20)
	private String isPledge;
	
	/**
	 * 抵押权人
	 */
	@Column(length=128)
	private String pledgePerson;
	
	/**
	 * 抵押金额(元)
	 */
	@Column(precision = 18, scale = 2)
	private BigDecimal pledgeAmount = BigDecimal.ZERO;
	
	/**
	 * 抵押期限
	 */
	@Column
	private Integer pledgeDeadLine = new Integer(0);
	
	/**
	 * 抵押期限单位
	 */
	@Column(length = 20)
	private String pledgeDeadLineUnit;
	
	/**
	 * 案件ID
	 */
	@Column(length=32)
	private String caseApplyId;

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public Integer getUseYear() {
		return useYear;
	}

	public void setUseYear(Integer useYear) {
		this.useYear = useYear;
	}

	public BigDecimal getWorth() {
		return worth;
	}

	public void setWorth(BigDecimal worth) {
		this.worth = worth;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getIsPledge() {
		return isPledge;
	}

	public void setIsPledge(String isPledge) {
		this.isPledge = isPledge;
	}

	public String getPledgePerson() {
		return pledgePerson;
	}

	public void setPledgePerson(String pledgePerson) {
		this.pledgePerson = pledgePerson;
	}

	public BigDecimal getPledgeAmount() {
		return pledgeAmount;
	}

	public void setPledgeAmount(BigDecimal pledgeAmount) {
		this.pledgeAmount = pledgeAmount;
	}

	public Integer getPledgeDeadLine() {
		return pledgeDeadLine;
	}

	public void setPledgeDeadLine(Integer pledgeDeadLine) {
		this.pledgeDeadLine = pledgeDeadLine;
	}

	public String getPledgeDeadLineUnit() {
		return pledgeDeadLineUnit;
	}

	public void setPledgeDeadLineUnit(String pledgeDeadLineUnit) {
		this.pledgeDeadLineUnit = pledgeDeadLineUnit;
	}

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}
	
	
	

}
