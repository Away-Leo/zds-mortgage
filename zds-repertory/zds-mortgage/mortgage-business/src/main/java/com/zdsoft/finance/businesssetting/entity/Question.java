package com.zdsoft.finance.businesssetting.entity;

import com.zdsoft.framework.core.common.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: Question.java 
 * @ClassName: Question 
 * @Description: 问题库定义
 * @author longwei 
 * @date 2017年2月6日 上午11:08:42 
 * @version V1.0
 */
@Entity
@Table(name="buss_question")
public class Question extends BaseEntity{

	private static final long serialVersionUID = 6915101667511500482L;
	/**
	 * 问题
	 */
	@Column(length=128)
	private String questionContent;
	
	/**
	 * 结论类型
	 */
	@Column(length=32)
	private String questionTypeCode;
	

	/**
	 * 参数值
	 */
	@Column(length=255)
	private String paraValue;
	

	/**
	 * 命中规则
	 */
	@Column(length=500)
	private String judgeRule;

	/**
	 * 案件ID
	 */
	@Transient
	private String caseApplyId;

	/**
	 * 场景类型
	 */
	@Transient
	private String sceneTypeCode;
	

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
	
	
}
