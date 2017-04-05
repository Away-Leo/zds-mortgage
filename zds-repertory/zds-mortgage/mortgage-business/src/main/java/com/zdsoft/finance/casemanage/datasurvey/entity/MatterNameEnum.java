package com.zdsoft.finance.casemanage.datasurvey.entity;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MatterNameEnum.java 
 * @ClassName: MatterNameEnum 
 * @Description: 流程中事项名称枚举类
 * @author zhoushichao 
 * @date 2017年3月9日 下午9:51:27 
 * @version V1.0 
 */ 
public enum MatterNameEnum {
	
	/**
	 * 资调
	 */
	DATA_SURVEY("dataSurvey"),
	
	/**
	 * 财务复核
	 */
	FINANCIAL_REVIEW("financialReview"),
	
	/**
	 * 办理抵押
	 */
	HANDLE_MORTGAGE("handleMortgage"),
	
	/**
	 * 查册入押
	 */
	BOOK_ENTRY("bookEntry");
	
	public final String value;

	private MatterNameEnum(String value) {
		this.value = value;
	} 
}
