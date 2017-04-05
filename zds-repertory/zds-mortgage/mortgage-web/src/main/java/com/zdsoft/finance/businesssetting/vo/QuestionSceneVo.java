package com.zdsoft.finance.businesssetting.vo;

import com.zdsoft.finance.businesssetting.entity.QuestionScene;
import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.common.utils.VoUtil;
import org.springframework.beans.BeanUtils;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: QuestionSceneVo.java 
 * @ClassName: QuestionSceneVo 
 * @Description: 场景设置Vo
 * @author longwei 
 * @date 2017年2月6日 上午11:12:42 
 * @version V1.0
 */
public class QuestionSceneVo extends BaseVo<QuestionScene> {

	/**
	 * 场景类型编号
	 */
	private String sceneTypeCode;
	/**
	 * 场景类型名称
	 */
	private String sceneTypeCodeName;
	/**
	 * 场景问题类型 默认、命中、随机
	 */
	private String sceneQuestionType;

	/**
	 * 问题ID
	 */
	private String questionId;

	/**
	 * 问题
	 */
	private String questionContent;

	/**
	 * 结论类型编号
	 */
	private String questionTypeCode;
	/**
	 * 结论类型名称
	 */
	private String questionTypeCodeName;

	/**
	 * 参数值
	 */
	private String paraValue;

	/**
	 * 命中规则
	 */
	private String judgeRule;

	public String getSceneQuestionType() {
		return sceneQuestionType;
	}

	public void setSceneQuestionType(String sceneQuestionType) {
		this.sceneQuestionType = sceneQuestionType;
	}

	public String getSceneTypeCodeName() {
		return sceneTypeCodeName;
	}

	public void setSceneTypeCodeName(String sceneTypeCodeName) {
		this.sceneTypeCodeName = sceneTypeCodeName;
	}

	public String getQuestionTypeCodeName() {
		return questionTypeCodeName;
	}

	public void setQuestionTypeCodeName(String questionTypeCodeName) {
		this.questionTypeCodeName = questionTypeCodeName;
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

	public QuestionSceneVo(){}
	public QuestionSceneVo(QuestionScene questionScene){
		VoUtil.copyPoperties(questionScene,this,false,null,new String[]{"sceneTypeCode","questionTypeCode"});
	}

	public QuestionScene toPo(){
		QuestionScene ques=new QuestionScene();
		BeanUtils.copyProperties(this,ques);
		return ques;
	}

}
