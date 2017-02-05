package com.zdsoft.finance.spi.common.dto;

/**
 * 信托计划日志操作枚举
 * 
 * @author liuwei
 *
 */
public enum OperationTypeNm {

	/**
	 * 提交
	 */
	SUBMIT("submit"),

	/**
	 * 到账确认
	 */
	ARRIVAL("arrival");

	public final String value;

	private OperationTypeNm(String value) {
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
		if (value != null) {
			switch (value) {
			case "submit":
				return "已提交";
			case "arrival":
				return "到账确认";
			default:
				return "";
			}
		} else {
			return "";
		}

	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static OperationTypeNm getValue(String value) {
		for (OperationTypeNm e : OperationTypeNm.values()) {
			if (e.value.equals(value)) {
				return e;
			}
		}
		return null;
	}
}
