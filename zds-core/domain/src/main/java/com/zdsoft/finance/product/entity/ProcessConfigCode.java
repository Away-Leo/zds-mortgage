package com.zdsoft.finance.product.entity;

/**
 * 流程配置代码code
 * @author longwei
 * @date 2017/01/16
 * @version 1.0
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
