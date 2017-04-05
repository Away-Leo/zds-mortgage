package com.zdsoft.finance.casemanage.promotion.otherproperty.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.casemanage.promotion.otherproperty.entity.AssetsCar;
import com.zdsoft.finance.common.base.BaseVo;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AssetsCarVo.java 
 * @ClassName: AssetsCarVo 
 * @Description: 其他资产之汽车信息Vo
 * @author xiongpan
 * @date 2017年2月25日 上午10:48:53 
 * @version V1.0 
 */ 
public class AssetsCarVo extends BaseVo<AssetsCar>{

	private static final long serialVersionUID = -2839760777118293309L;

	/**
	 * 型号
	 */
	private String carType;
	
	/**
	 * 使用年限
	 */
	private Integer useYear;
	
	/**
	 * 价值(元)
	 */
	private BigDecimal worth;
	
	/**
	 * 权属人
	 */
	private String owner;
	
	/**
	 * 是否在押
	 */
	private String isPledge;
	
	/**
	 * 抵押权人
	 */
	private String pledgePerson;
	
	/**
	 * 抵押金额(元)
	 */
	private BigDecimal pledgeAmount;
	
	/**
	 * 抵押期限
	 */
	private Integer pledgeDeadLine;
	
	/**
	 * 抵押期限单位
	 */
	private String pledgeDeadLineUnit;
	
	/**
	 * 案件ID
	 */
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
