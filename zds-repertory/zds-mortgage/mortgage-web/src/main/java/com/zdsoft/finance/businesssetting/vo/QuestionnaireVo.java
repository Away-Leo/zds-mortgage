package com.zdsoft.finance.businesssetting.vo;

import java.io.Serializable;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: QuestionnaireVo.java
 * @ClassName: QuestionnaireVo
 * @Description: 智能问卷答题结果Vo
 * @author jincheng
 * @date 2017年2月22日 下午4:57:57
 * @version V1.0
 */
public class QuestionnaireVo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 案件ID
	 */
	private String caseApplyId;

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
	 * 资调结果
	 */
	private String capitalResult;

	/**
	 * 面签结果
	 */
	private String signResult;
	
	/**
	 * 题目是否一致
	 */
	private boolean isSame=false;
	
	/**
	 * 答案结果是否一致
	 */
	private boolean isResult=true;

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
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

	public String getCapitalResult() {
		return capitalResult;
	}

	public void setCapitalResult(String capitalResult) {
		this.capitalResult = capitalResult;
	}

	public String getSignResult() {
		return signResult;
	}

	public void setSignResult(String signResult) {
		this.signResult = signResult;
	}

	public boolean isSame() {
		return isSame;
	}

	public void setSame(boolean isSame) {
		this.isSame = isSame;
	}

	public boolean isResult() {
		return isResult;
	}

	public void setResult(boolean isResult) {
		this.isResult = isResult;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
