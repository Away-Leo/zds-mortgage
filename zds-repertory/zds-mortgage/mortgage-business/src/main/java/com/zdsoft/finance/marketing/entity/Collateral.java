package com.zdsoft.finance.marketing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: Collateral.java 
 * @ClassName: Collateral 
 * @Description: 押品实体类
 * @author zhoushichao 
 * @date 2017年3月14日 下午5:44:58 
 * @version V1.0
 */
@Entity
@Table(name = "mkt_collateral")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Collateral extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 房产
	 */
	public static final String HOUSE_PROPERTY = "0";
	/**
	 * 设备
	 */
	public static final String EQUIPMENT = "1";
	
	/**
	 * 案件
	 */
	@ManyToOne
    @JoinColumn(name="caseApplyId")
	private CaseApply caseApply;
	
	/**
	 * 押品类型
	 */
	@Column(length = 20)
	private String collateralType;
	
	public CaseApply getCaseApply() {
		return caseApply;
	}
	public void setCaseApply(CaseApply caseApply) {
		this.caseApply = caseApply;
	}
	public String getCollateralType() {
		return collateralType;
	}
	public void setCollateralType(String collateralType) {
		this.collateralType = collateralType;
	}
}
