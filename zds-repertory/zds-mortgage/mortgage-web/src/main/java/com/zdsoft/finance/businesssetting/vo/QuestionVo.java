package com.zdsoft.finance.businesssetting.vo;


import com.zdsoft.finance.businesssetting.entity.Question;
import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.common.utils.VoUtil;
import org.springframework.beans.BeanUtils;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: QuestionVo.java 
 * @ClassName: QuestionVo 
 * @Description: 问题定义Vo
 * @author longwei 
 * @date 2017年2月6日 上午11:12:49 
 * @version V1.0
 */
public class QuestionVo extends BaseVo<Question> {

	private static final long serialVersionUID = -6036546291036172617L;
	/**
	 * 问题
	 */
	private String questionContent;

	/**
	 * 结论类型
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

	public String getQuestionTypeCodeName() {
		return questionTypeCodeName;
	}

	public void setQuestionTypeCodeName(String questionTypeCodeName) {
		this.questionTypeCodeName = questionTypeCodeName;
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

	public QuestionVo(){}
	public QuestionVo(Question question){
		VoUtil.copyPoperties(question,this,false,null,new String[]{"questionTypeCode"});
	}

	public Question toPo(){
		Question question=new Question();
		BeanUtils.copyProperties(this,question);
		return question;
	}
}
