package com.zdsoft.finance.customer.entity;

public enum CreditAttachmentTypeEnum {
	/**
	 * 借款人或买方征信 (征信报告)
	 */
	CREDIT_REPORT("YWDM001500405"),
	/**
	 * 主借人征信身份证
	 */
	LORD_PEOPLE_CARD("YWDM001500113"),
	/**
	 * 主借人征信授权书
	 */
	LORD_PEOPLE_BOOK("YWDM001500114"),
	/**
	 * 主借人配偶征信身份证
	 */
	LORD_SPOUSE_CARD("YWDM001500115"),
	/**
	 * 主借人配偶征信授权书
	 */
	LORD_SPOUSE_BOOK("YWDM001500116"),
	/**
	 * 共同借款人征信身份证
	 */
	CO_OWNER_CARD("YWDM001500117"),
	/**
	 * 共同借款人征信授权书
	 */
	CO_OWNER_BOOK("YWDM001500118"),
	/**
	 * 共同借款人配偶征信身份证
	 */
	CO_SPOUSE_CARD("YWDM001500119"),
	/**
	 * 共同借款人配偶征信授权书
	 */
	CO_SPOUSE_BOOK("YWDM001500120"),
	/**
	 * 担保人征信身份证
	 */
	GUARANTEE_CARD("YWDM001500121"),
	/**
	 * 担保人征信授权书
	 */
	GUARANTEE_BOOK("YWDM001500122"),
	/**
	 * 担保人配偶偶征信身份证
	 */
	GUARANTEE_SPOUSE_CARD("YWDM001500123"),
	/**
	 * 担保人配偶征信授权书
	 */
	GUARANTEE_SPOUSE_BOOK("YWDM001500124");
	
	public final String value;
	
	private CreditAttachmentTypeEnum(String value) {
		this.value = value;
	}
}
