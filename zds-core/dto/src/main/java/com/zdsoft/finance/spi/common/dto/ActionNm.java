package com.zdsoft.finance.spi.common.dto;

/**
 * 动作名称枚举类
 * 
 * @author liuwei
 *
 */
public enum ActionNm {
	/**
	 * 创建
	 */
	CREATE("CREATE"),

	/**
	 * 修改
	 */
	UPDATE("UPDATE"),
	
	/**
	 * 删除
	 */
	DELETED("DELETED");

	public final String value;

	private ActionNm(String value) {
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
		case "CREATE":
			return "创建";
		case "UPDATE":
			return "修改";
		case "DELETED":
			return "删除";
		default:
			return "";
		}
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static ActionNm getValue(String value) {
		for (ActionNm e : ActionNm.values()) {
			if (e.value == value) {
				return e;
			}
		}
		return null;
	}
}
