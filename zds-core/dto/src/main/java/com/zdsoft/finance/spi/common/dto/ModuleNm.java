package com.zdsoft.finance.spi.common.dto;

/**
 * 模块名称枚举类
 * 
 * @author liuwei
 *
 */
public enum ModuleNm {
	/**
	 * 会议
	 */
	MEET("MEET"),
	
	/**
	 * 流贷
	 */
	PROJECT_EVALUATE_LOAN("PROJECT_EVALUATE_LOAN"),

	/**
	 * 合作机构
	 */
	ORGANIZATION("ORGANIZATION"),
	
	/**
	 * 银行
	 */
	BANK("BANK");
	
	public final String value;

	private ModuleNm(String value) {
		this.value = value;
	}

	/**
	 * 获取对应描述
	 * 
	 * @param value
	 *            枚举值
	 * @return 描述
	 */
	public static String getName(String value) {
		switch (value) {
		case "MEET":
			return "会议";
		case "PROJECT_EVALUATE_LOAN":
			return "流贷项目申请";
		case "ORGANIZATION":
			return "合作机构";
		case "BANK":
			return "银行";
		default:
			return "";
		}
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static ModuleNm getValue(String value) {
		for (ModuleNm e : ModuleNm.values()) {
			if (e.value == value) {
				return e;
			}
		}
		return null;
	}
}
