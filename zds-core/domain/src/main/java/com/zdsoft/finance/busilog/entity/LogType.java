package com.zdsoft.finance.busilog.entity;

public enum LogType {

	/**
	 * 自动
	 */
	AUTO("1"),

	/**
	 * 手动
	 */
	MANUAL("2");

	public final String value;

	private LogType(String value) {
		this.value = value;
	}

	public static String getName(String value) {
		switch (Integer.parseInt("" + value)) {
		case 1:
			return "自动";
		case 2:
			return "手动";
		default:
			return "";
		}
	}

	public static LogType getValue(String value) {
		for (LogType e : LogType.values()) {
			if (e.value.equals(value)) {
				return e;
			}
		}
		return null;
	}

}
