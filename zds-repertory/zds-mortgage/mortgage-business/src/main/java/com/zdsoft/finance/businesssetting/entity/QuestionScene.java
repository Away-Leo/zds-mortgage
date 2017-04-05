package com.zdsoft.finance.businesssetting.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title QuestionScene.java
 * @className QuestionScene
 * @description 场景问题设置域对象
 * @author LiaoGuoWei
 * @create 2017/3/1 10:21
 * @version V1.0
 **/
@Entity
@Table(name="buss_questionscene")
public class QuestionScene extends BaseEntity{

	private static final long serialVersionUID = 1L;

	/**
	 * 场景类型
	 */
	@Column(length=32)
	private String sceneTypeCode;
	
	/**
	 * 问题ID
	 */
	@Column(length=255)
	private String questionId;

	/**
	 * 场景问题类型 默认、命中、随机
	 */
	@Column(length = 32)
	private String sceneQuestionType;

	/**
	 * 问题
	 */
	private transient String questionContent;

	/**
	 * 结论类型
	 */
	private transient String questionTypeCode;

	/**
	 * 参数值
	 */
	private transient String paraValue;

	/**
	 * 命中规则
	 */
	private transient String judgeRule;
	
	/**
	 * 案件id
	 */
	@Transient
	private String caseApplyId;


	public String getSceneQuestionType() {
		return sceneQuestionType;
	}

	public void setSceneQuestionType(String sceneQuestionType) {
		this.sceneQuestionType = sceneQuestionType;
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

	public String getParaValue() {
		return paraValue;
	}

	public void setParaValue(String paraValue) {
		this.paraValue = paraValue;
	}

	public String getJudgeRule() {
		return judgeRule;
	}

	public void setJudgeRule(String judgeRule) {
		this.judgeRule = judgeRule;
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

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}
	
}
