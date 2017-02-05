package com.zdsoft.finance.customer.entity;

/**
 * 联系类型
 * @author zhangchao
 *
 */
public enum ContactType {

	/**
	 * 手机
	 */
	CELLPHONE("1"),

	/**
	 * 座机
	 */
	PHONE("2");
	
	public final String value;

	private ContactType(String value) {
		this.value = value;
	}

	public static String getName(String value) {
		switch (Integer.parseInt("" + value)) {
		case 1:
			return "手机";
		case 2:
			return "座机";
		default:
			return "";
		}
	}

	public static ContactType getValue(String value) {
		for (ContactType e : ContactType.values()) {
			if (e.value.equals(value)) {
				return e;
			}
		}
		return null;
	}
}
