package com.zdsoft.finance.spi.businesssetting;

import java.io.Serializable;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: QuestionnaireDto.java
 * @ClassName: QuestionnaireDto
 * @Description: 智能问卷答题Dto
 * @author jincheng
 * @date 2017年3月1日 上午10:27:59
 * @version V1.0
 */
public class QuestionnaireDto implements Serializable {

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
	 * 题目的ID
	 */
	private String questionId;

	/**
	 * 题目内容
	 */
	private String questionContent;

	/**
	 * 题目类型
	 */
	private String questionTypeCode;

	/**
	 * 题目选项处理规则 "有:none,无:input,其他:file"
	 */
	private String questionItem;

	/**
	 * 场景问题类型 默认、命中、随机
	 */
	private String sceneQuestionType;

	/**
	 * 默认值
	 */
	private String questionValue;

	/**
	 * 二级级联控件类型
	 */
	private String secondLevelTypeCode;

	/**
	 * 二级级联控制值
	 */
	private String secondLevelValue;

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

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public String getQuestionTypeCode() {
		return questionTypeCode;
	}

	public void setQuestionTypeCode(String questionTypeCode) {
		this.questionTypeCode = questionTypeCode;
	}

	public String getQuestionItem() {
		return questionItem;
	}

	public void setQuestionItem(String questionItem) {
		this.questionItem = questionItem;
	}

	public String getQuestionValue() {
		return questionValue;
	}

	public void setQuestionValue(String questionValue) {
		this.questionValue = questionValue;
	}

	public String getSceneQuestionType() {
		return sceneQuestionType;
	}

	public void setSceneQuestionType(String sceneQuestionType) {
		this.sceneQuestionType = sceneQuestionType;
	}

	public String getSecondLevelTypeCode() {
		return secondLevelTypeCode;
	}

	public void setSecondLevelTypeCode(String secondLevelTypeCode) {
		this.secondLevelTypeCode = secondLevelTypeCode;
	}

	public String getSecondLevelValue() {
		return secondLevelValue;
	}

	public void setSecondLevelValue(String secondLevelValue) {
		this.secondLevelValue = secondLevelValue;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
