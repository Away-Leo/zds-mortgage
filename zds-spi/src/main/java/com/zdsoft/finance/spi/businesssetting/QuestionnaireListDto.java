package com.zdsoft.finance.spi.businesssetting;

import java.io.Serializable;
import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: QuestionnaireListDto.java
 * @ClassName: QuestionnaireListDto
 * @Description: 智能问卷答题组装Dto
 * @author jincheng
 * @date 2017年3月01日 下午4:57:57
 * @version V1.0
 */
public class QuestionnaireListDto implements Serializable {

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
	private List<QuestionnaireDto> questionList;

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

	public List<QuestionnaireDto> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<QuestionnaireDto> questionList) {
		this.questionList = questionList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
