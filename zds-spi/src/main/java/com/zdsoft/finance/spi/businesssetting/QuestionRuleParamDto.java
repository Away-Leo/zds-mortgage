package com.zdsoft.finance.spi.businesssetting;

import java.io.Serializable;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: QuestionRuleParamDto.java
 * @ClassName: QuestionRuleParamDto
 * @Description: 问题参数Dto
 * @author jincheng
 * @date 2017年3月1日 下午5:40:11
 * @version V1.0
 */
public class QuestionRuleParamDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 案件ID
	 */
	private String caseApplyId;

	/**
	 * 场景类型
	 */
	private String sceneTypeCode;
	
	/**
	 * 问题
	 */
	private transient String questionContent;
	
	/**
	 * 标示: A/B/C/D/E/F/G/H
	 */
	private String flag="A";

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public String getSceneTypeCode() {
		return sceneTypeCode;
	}

	public void setSceneTypeCode(String sceneTypeCode) {
		this.sceneTypeCode = sceneTypeCode;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
