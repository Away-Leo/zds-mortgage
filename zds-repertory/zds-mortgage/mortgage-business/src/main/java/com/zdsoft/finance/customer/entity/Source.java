package com.zdsoft.finance.customer.entity;

/**
 * 黑名单来源
 * @author zhangchao
 *
 */
public enum Source {

	/**
	 * 外贸信托
	 */
    FOREIGNTRADE("1"),

	/**
	 * 渤海信托
	 */
	BOHAI("2"),
	
	/**
	 * 泛华
	 */
	FANHUA("3"),
	
	/**
	 * 第三方
	 */
	THIRDPARTY("4");
	
	public final String value;

	private Source(String value) {
		this.value = value;
	}

	public static String getName(String value) {
		switch (Integer.parseInt("" + value)) {
		case 1:
			return "外贸信托";
		case 2:
			return "渤海信托";
		case 3:
			return "泛华";
		case 4:
			return "第三方";
		default:
			return "";
		}
	}

	public static Source getValue(String value) {
		for (Source e : Source.values()) {
			if (e.value.equals(value)) {
				return e;
			}
		}
		return null;
	}
}
