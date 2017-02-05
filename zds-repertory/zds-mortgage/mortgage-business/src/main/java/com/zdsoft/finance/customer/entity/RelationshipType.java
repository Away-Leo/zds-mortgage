package com.zdsoft.finance.customer.entity;

/**
 * 客户关系
 * @author zhangchao
 *
 */
public enum RelationshipType {

	/**
	 * 配偶
	 */
    SPOUSE("1"),

	/**
	 * 朋友
	 */
	FREND("2"),
	
	/**
	 * 亲戚
	 */
	RELATIVE("3"),
	
	/**
	 * 父母
	 */
	PARENT("4"),
	
	/**
	 * 本人
	 */
	SELF("6"),
	
	/**
	 * 子女
	 */
	CHILDREN("5");
	
	public final String value;

	private RelationshipType(String value) {
		this.value = value;
	}

	public static String getName(String value) {
		switch (Integer.parseInt("" + value)) {
		case 1:
			return "配偶";
		case 2:
			return "朋友";
		case 3:
			return "亲戚";
		case 4:
			return "父母";
		case 5:
			return "子女";
		case 6:
			return "本人";
		default:
			return "";
		}
	}

	public static RelationshipType getValue(String value) {
		for (RelationshipType e : RelationshipType.values()) {
			if (e.value.equals(value)) {
				return e;
			}
		}
		return null;
	}
}
