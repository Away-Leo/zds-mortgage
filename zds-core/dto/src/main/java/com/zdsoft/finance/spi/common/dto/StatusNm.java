package com.zdsoft.finance.spi.common.dto;

/**
 * 信托计划提交状态枚举类
 * 
 * @author liuwei
 *
 */
public enum StatusNm {
	/**
	 * 保存
	 */
	SAVE(0),

	/**
	 * 提交
	 */
	SUBMIT(1),

	/**
	 * 到账确认
	 */
	ARRIVAL(2);

	public final Integer value;

	private StatusNm(Integer value) {
		this.value = value;
	}

	/**
	 * 获取对应描述
	 * 
	 * @param value
	 *            枚举值
	 * @return 描述
	 */
	public static String getName(Integer value) {
		if (value != null) {
			switch (value) {
			case 0:
				return "已保存";
			case 1:
				return "已提交";
			case 2:
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
	public static StatusNm getValue(Integer value) {
		for (StatusNm e : StatusNm.values()) {
			if (e.value == value) {
				return e;
			}
		}
		return null;
	}
}
