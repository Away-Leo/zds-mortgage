package com.zdsoft.finance.casemanage.datasurvey.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: FamilyIncomeDetail.java 
 * @ClassName: FamilyIncomeDetail 
 * @Description: 客户的家庭收入详情
 * @author liuhuan 
 * @date 2017年2月9日 下午1:49:39 
 * @version V1.0
 */
@Entity
@Table(name="case_family_income_detail")
public class FamilyIncomeDetail extends BaseEntity{

	private static final long serialVersionUID = -6972790925101830189L;
	
	/**
	 * 家庭收入id
	 */
	@Column(length=32)
	private String familyIncomeId;
	
	/**
	 * 月份
	 */
	@Column
	private Long realMonth;
	
	/**
	 * 贷方发生金额
	 */
	@Column(precision = 18, scale = 2)
	private BigDecimal realAmount = BigDecimal.ZERO;

	public String getFamilyIncomeId() {
		return familyIncomeId;
	}

	public void setFamilyIncomeId(String familyIncomeId) {
		this.familyIncomeId = familyIncomeId;
	}

	public Long getRealMonth() {
		return realMonth;
	}

	public void setRealMonth(Long realMonth) {
		this.realMonth = realMonth;
	}

	public BigDecimal getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
	}
	
}
