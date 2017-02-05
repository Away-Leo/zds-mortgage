package com.zdsoft.finance.capital.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.capital.entity.CreditEntrustFeeItem;
import com.zdsoft.finance.common.base.BaseVo;

/**
 * 所属费用项Vo
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
public class CreditEntrustFeeItemVo extends BaseVo<CreditEntrustFeeItem> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3837630051541118604L;

	/**
	 * 所属业务标识
	 */
	private String businessId;

	/**
	 * 费用项编号
	 */
	private String feeItemCd;

	/**
	 * 费用项名称
	 */
	private String feeItemNm;

	/**
	 * 费用项金额
	 */
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

	public CreditEntrustFeeItemVo() {
		super();
	}

	public CreditEntrustFeeItemVo(CreditEntrustFeeItem creditEntrustFeeItem) {
		super(creditEntrustFeeItem);
	}

	public CreditEntrustFeeItem toPo() {
		CreditEntrustFeeItem feeItem = new CreditEntrustFeeItem();
		return super.toPo(this, feeItem);
	}

}
