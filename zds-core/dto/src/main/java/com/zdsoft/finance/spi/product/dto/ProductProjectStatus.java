package com.zdsoft.finance.spi.product.dto;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ProductProjectStatus.java 
 * @ClassName: ProductProjectStatus 
 * @Description: 产品管理项目状态
 * @author longwei 
 * @date 2017年2月6日 上午11:19:18 
 * @version V1.0
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
