package com.zdsoft.finance.capital.vo;

import com.zdsoft.finance.capital.entity.FeeItem;
import com.zdsoft.finance.common.base.BaseVo;

/**
 * 费用项Vo
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
public class FeeItemVo extends BaseVo<FeeItem> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4414434078880634807L;

	/*
	 * 费用项cd
	 */
	private String feeItemCd;

	/**
	 * 费用项Nm
	 */
	private String feeItemNm;

	/**
	 * 费用项归属
	 */
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

	public FeeItemVo() {
		super();
	}

	public FeeItemVo(FeeItem feeItem) {
		super(feeItem);
	}

	public FeeItem toPo() {
		FeeItem feeItem = new FeeItem();
		return super.toPo(this, feeItem);
	}

}
