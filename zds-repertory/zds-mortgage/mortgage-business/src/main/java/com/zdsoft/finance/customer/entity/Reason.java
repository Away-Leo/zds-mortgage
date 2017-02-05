package com.zdsoft.finance.customer.entity;

/**
 * 黑名单原因
 * @author zhangchao
 *
 */
public enum Reason {

	/**
	 * 欺诈
	 */
    CHEAT("1"),

	/**
	 * 逾期
	 */
	OVERDUE("2"),
	
	/**
	 * 核销
	 */
	VERIFICATION("3");
	
	public final String value;

	private Reason(String value) {
		this.value = value;
	}

	public static String getName(String value) {
		switch (Integer.parseInt("" + value)) {
		case 1:
			return "欺诈";
		case 2:
			return "逾期";
		case 3:
			return "核销";
		default:
			return "";
		}
	}

	public static Reason getValue(String value) {
		for (Reason e : Reason.values()) {
			if (e.value.equals(value)) {
				return e;
			}
		}
		return null;
	}
}
