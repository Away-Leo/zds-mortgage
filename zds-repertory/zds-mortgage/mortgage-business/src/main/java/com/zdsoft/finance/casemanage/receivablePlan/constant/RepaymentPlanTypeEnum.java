package com.zdsoft.finance.casemanage.receivablePlan.constant;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: RepaymentPlanTypeEnum.java
 * @ClassName: RepaymentPlanTypeEnum
 * @Description: 还款计划状态枚举
 * @author jincheng
 * @date 2017年2月22日 上午10:28:12
 * @version V1.0
 */
public enum RepaymentPlanTypeEnum {

	/**
	 * 草稿
	 */
	DRAFT(1),
	/**
	 * 放款时
	 */
	LOAN(2),
	/**
	 * 变更时
	 */
	CHANGE(3),
	/**
	 * 展期时
	 */
	EXTENSION(4),
	/**
	 * 提前部分还款时
	 */
	REPAYMENT_PART(5),
	/**
	 * 提前结清时
	 */
	SETTLEMENT(6);

	private Integer value;

	private RepaymentPlanTypeEnum(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}
