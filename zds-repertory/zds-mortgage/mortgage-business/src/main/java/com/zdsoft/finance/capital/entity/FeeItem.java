package com.zdsoft.finance.capital.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: FeeItem.java 
 * @ClassName: FeeItem 
 * @Description: 费用项
 * @author liuwei 
 * @date 2017年2月8日 上午10:24:38 
 * @version V1.0
 */
@Entity
@Table(name = "cptl_fee_item")
public class FeeItem extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1950299407860470618L;

	/*
	 * 费用项cd
	 */
	@Column(length = 32)
	private String feeItemCd;

	/**
	 * 费用项Nm
	 */
	@Column(length = 32)
	private String feeItemNm;

	/**
	 * 费用项归属
	 */
	@Column(length = 32)
	private String attribution;

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

	public String getAttribution() {
		return attribution;
	}

	public void setAttribution(String attribution) {
		this.attribution = attribution;
	}
}
