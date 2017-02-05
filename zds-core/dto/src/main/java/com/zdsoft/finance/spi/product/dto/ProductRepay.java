package com.zdsoft.finance.spi.product.dto;

/**
 * 产品定义还款项
 * @author longwei
 * @date 2016/11/15
 * @version 1.0
 */
public enum ProductRepay {

	/**
	 * 利息
	 */
	LX("lx"),
	
	/**
	 * 本金
	 */
	BJ("bj"),
	
	/**
	 * 复利
	 */
	FL("fl"),
	
	/**
	 * 罚息
	 */
	FX("fx");
	
	private String receivablesItemCd;
	
	private String receivablesItemNm;
	
	private ProductRepay(String receivablesItemCd){
		this.receivablesItemCd=receivablesItemCd;
		switch (receivablesItemCd) {
		case "lx":
			this.receivablesItemNm="利息";
			break;
		case "bj":
			this.receivablesItemNm="本金";
			break;
		case "fl":
			this.receivablesItemNm="复利";
			break;
		case "fx":
			this.receivablesItemNm="罚息";
			break;
		}
	}

	public String getReceivablesItemCd() {
		return receivablesItemCd;
	}

	public void setReceivablesItemCd(String receivablesItemCd) {
		this.receivablesItemCd = receivablesItemCd;
	}

	public String getReceivablesItemNm() {
		return receivablesItemNm;
	}

	public void setReceivablesItemNm(String receivablesItemNm) {
		this.receivablesItemNm = receivablesItemNm;
	}
	
}
