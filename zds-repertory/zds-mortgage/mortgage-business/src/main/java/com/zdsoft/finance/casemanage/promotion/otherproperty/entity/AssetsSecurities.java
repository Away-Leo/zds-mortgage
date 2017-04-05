package com.zdsoft.finance.casemanage.promotion.otherproperty.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:AssetsSecurities.java
 * @Package:com.zdsoft.finance.casemanage.promotion.otherproperty.entity
 * @Description:其他资产之邮件证券情况
 * @author: xiongpan
 * @date:2017年2月20日 上午11:47:34
 * @version:v1.0
 */
@Entity
@Table(name = "case_other_assets_securities")
public class AssetsSecurities extends BaseEntity{

	private static final long serialVersionUID = 5627878941594675309L;
	
	/**
	 * 证券名称
	 */
	@Column(length=50)
	private String securitiesName;
	
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
	 * 案件ID
	 */
	@Column(length=32)
	private String caseApplyId;

	public String getSecuritiesName() {
		return securitiesName;
	}

	public void setSecuritiesName(String securitiesName) {
		this.securitiesName = securitiesName;
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

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}
	
	
	

}
