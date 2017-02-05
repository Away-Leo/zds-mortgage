package com.zdsoft.finance.capital.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 信托计划费用项表
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
@Entity
@Table(name = "caal_credit_fee_item")
public class CreditEntrustFeeItem extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7380133471747057288L;

	/**
	 * 所属业务标识
	 */
	@Column(length = 32)
	private String businessId;

	/**
	 * 费用项编号
	 */
	@Column(length = 32)
	private String feeItemCd;

	/**
	 * 费用项名称
	 */
	@Column(length = 32)
	private String feeItemNm;

	/**
	 * 费用项金额
	 */
	@Column(precision = 30, scale = 12)
	private BigDecimal feeAmount;

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getFeeItemCd() {
		return feeItemCd;
	}

	public void setFeeItemCd(String feeItemCd) {
		this.feeItemCd = feeItemCd;
	}

	public String getFeeItemNm() {
		return feeItemNm;
	}

	public void setFeeItemNm(String feeItemNm) {
		this.feeItemNm = feeItemNm;
	}

	public BigDecimal getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(BigDecimal feeAmount) {
		this.feeAmount = feeAmount;
	}

}
