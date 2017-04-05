package com.zdsoft.finance.product.entity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ProcessConfigCode.java 
 * @ClassName: ProcessConfigCode 
 * @Description: 流程配置代码code
 * @author gufeng 
 * @date 2017年3月6日 下午6:04:35 
 * @version V1.0
 */
public enum ProcessConfigCode {

	PRODUCT_APPLAY("applayFormCode01"),
	
	MYDRAFT_INFO("mydraft");
	
	private String code;
	
	private String value;
	
	private ProcessConfigCode(String code){
		this.code=code;
		switch (code) {
		case "applayFormCode01":
			this.value = "项目申请";
			break;
		case "mydraft":
			this.value = "草稿详情";
			break;
		default:
			break;
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
