package com.zdsoft.finance.businesssetting.entity;

import com.zdsoft.framework.core.common.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: Questionnaire.java
 * @ClassName: Questionnaire
 * @Description: 智能问卷答题结果
 * @author jincheng
 * @date 2017年2月22日 下午4:46:31
 * @version V1.0
 */
@Entity
@Table(name = "case_questionnaire")
public class Questionnaire extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 案件ID
	 */
	@Column(length = 32)
	private String caseApplyId;

	/**
	 *  场景类型
	 */
	@Column(length = 50)
	private String sceneTypeCode;

	/**
	 * 题目的ID
	 */
	@Column(length = 32)
	private String questionId;

	/**
	 * 题目内容
	 */
	@Column(length = 100)
	private String questionContent;

	/**
	 * 题目类型
	 */
	@Column(length = 10)
	private String questionTypeCode;

	/**
	 * 题目选项处理规则 "有:none,无:input,其他:file"
	 */
	@Column(length = 100)
	private String questionItem;

	/**
	 * 默认值
	 */
	@Column(length = 100)
	private String questionValue;

	/**
	 * 二级级联控件类型
	 */
	@Column(length = 100)
	private String secondLevelTypeCode;

	/**
	 * 二级级联控制值
	 */
	@Column(length = 100)
	private String secondLevelValue;
	
	/**
	 * 是否一致
	 */
	@Transient
	private boolean isSame=false;
	
	/**
	 * 资调结果
	 */
	@Transient
	private String capitalResult;

	/**
	 * 面签结果
	 */
	@Transient
	private String signResult;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public boolean isSame() {
		return isSame;
	}

	public void setSame(boolean isSame) {
		this.isSame = isSame;
	}

	public String getCapitalResult() {
		return this.getQuestionValue();
	}

	public void setCapitalResult(String capitalResult) {
		this.capitalResult = capitalResult;
	}

	public String getSignResult() {
		return this.getQuestionValue();
	}

	public void setSignResult(String signResult) {
		this.signResult = signResult;
	}

}
