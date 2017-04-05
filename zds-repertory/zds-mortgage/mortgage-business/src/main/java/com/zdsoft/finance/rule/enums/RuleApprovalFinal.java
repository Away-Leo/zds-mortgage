package com.zdsoft.finance.rule.enums;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RuleApprovalFinal.java 
 * @ClassName: RuleApprovalFinal 
 * @Description: 是否走拒绝规则枚举
 * @author zjx 
 * @date 2017年2月27日 下午6:28:27 
 * @version V1.0 
 */ 
public enum RuleApprovalFinal{
	RUN_NORMAL("正常审批(走拒绝规则)", 0),
	RUN_NOREFUSE("不走拒绝规则审批", 1);

	private RuleApprovalFinal(String lable, Integer value) {
		this.lable = lable;
		this.value = value;
	}

	private String lable;
	private Integer value;

	public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}

