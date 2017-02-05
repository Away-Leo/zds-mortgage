package com.zdsoft.finance.spi.product.dto;

/**
 * 产品类别维护
 * @author longwei
 * @date 2016/11/15
 * @version 1.0
 */
public enum ProductCode {

	/**
	 * 项目贷款
	 */
	XEDK("131300"),
	
	/**
	 * 融资担保
	 */
	RZDB("131301"),
	
	/**
	 * 非融资担保
	 */
	FRDB("131302"),
	
	/**
	 * 授信业务
	 */
	SXYW("131304"),
	
	/**
	 * 其他业务
	 */
	QT("simpleCd999");
	
	
	public final String value;
	
	private ProductCode(String value){
		this.value=value;
	}
}
