package com.zdsoft.finance.common.enums;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @title ENUM_QESTION_SCENE_TYPE.java
 * @className ENUM_QESTION_SCENE_TYPE
 * @description 场景问题设置类型
 * @author LiaoGuoWei
 * @create 2017/3/1 14:39
 * @version V1.0
 **/
public enum ENUM_QESTION_SCENE_TYPE {

	/**
	 * 默认
	 */
	DEFAULTS("0"),

	/**
	 * 命中
	 */
	HITS("1"),

	/**
	 * 随机
	 */
	RANDOMS("2");

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private ENUM_QESTION_SCENE_TYPE(String value) {
		this.value = value;
	}

}
