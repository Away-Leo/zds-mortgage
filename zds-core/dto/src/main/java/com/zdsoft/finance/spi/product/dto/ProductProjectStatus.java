package com.zdsoft.finance.spi.product.dto;

/**
 * 产品管理项目状态
 * @author longwei
 * @date 2016/11/15
 * @version 1.0
 */
public enum ProductProjectStatus {

	/**
	 * 正常项目
	 */
	NORMAL("1"),
	
	/**
	 * 不正常项目
	 */
	NOTNORMAL("0");
	
	private String value;
	
	private ProductProjectStatus(String value){
		this.value=value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
