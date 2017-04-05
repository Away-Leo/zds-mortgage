package com.zdsoft.finance.casemanage.promotion.otherproperty.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:AssetsDeposit.java
 * @Package:com.zdsoft.finance.casemanage.promotion.otherproperty.entity
 * @Description:其他资产之存款情况
 * @author: xiongpan
 * @date:2017年2月21日 下午2:10:31
 * @version:v1.0
 */
@Entity
@Table(name = "case_other_assets_deposit")
public class AssetsDeposit extends BaseEntity{

	private static final long serialVersionUID = -4438025709542572237L;
	
	/**
	 * 存款类型(0.定期;1.活期)
	 */
	@Column(length=20)
	private String depositType;
	
	/**
	 * 存款期限
	 */
	@Column
	private Integer depositDeadLine = new Integer(0);
	
	/**
	 * 存款期限单位
	 */
	@Column(length=20)
	private String depositDeadLineUnit;
	
	/**
	 * 存款金额(元)
	 */
	@Column(precision = 18, scale = 2)
	private BigDecimal depositAmount = BigDecimal.ZERO;
	
	/**
	 * 对应的案件ID
	 */
	@Column(length=32)
	private String caseApplyId;

	public String getDepositType() {
		return depositType;
	}

	public void setDepositType(String depositType) {
		this.depositType = depositType;
	}

	public Integer getDepositDeadLine() {
		return depositDeadLine;
	}

	public void setDepositDeadLine(Integer depositDeadLine) {
		this.depositDeadLine = depositDeadLine;
	}

	public String getDepositDeadLineUnit() {
		return depositDeadLineUnit;
	}

	public void setDepositDeadLineUnit(String depositDeadLineUnit) {
		this.depositDeadLineUnit = depositDeadLineUnit;
	}

	public BigDecimal getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(BigDecimal depositAmount) {
		this.depositAmount = depositAmount;
	}

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}
	
	
	
}
