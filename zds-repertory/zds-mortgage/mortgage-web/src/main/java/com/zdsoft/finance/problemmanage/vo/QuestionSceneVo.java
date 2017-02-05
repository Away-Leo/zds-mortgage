package com.zdsoft.finance.problemmanage.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.problemmanage.entity.QuestionScene;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 场景设置Vo
 * @author longwei
 * @date 2017/01/03
 * @version 1.0
 */
public class QuestionSceneVo extends BaseVo<QuestionScene>{

	// 场景类型
	private String sceneCd;
	
	// 场景类型
	private String sceneNm;
	
	// 问题
	private QuestionVo questionVo;

	public String getSceneCd() {
		return sceneCd;
	}

	public void setSceneCd(String sceneCd) {
		this.sceneCd = sceneCd;
	}

	public String getSceneNm() {
		return sceneNm;
	}

	public void setSceneNm(String sceneNm) {
		this.sceneNm = sceneNm;
	}

	public QuestionVo getQuestionVo() {
		return questionVo;
	}

	public void setQuestionVo(QuestionVo questionVo) {
		this.questionVo = questionVo;
	}
	
	public QuestionSceneVo(){}
	
	public QuestionSceneVo(QuestionScene questionScene){
		super(questionScene,new String[]{"questionVo"});
		if(ObjectHelper.isNotEmpty(questionScene.getQuestion())){
			this.setQuestionVo(new QuestionVo(questionScene.getQuestion()));
		}
	}
	
	public QuestionScene toPo(){
		QuestionScene questionScene=new QuestionScene();
		questionScene=super.toPo(this, questionScene,new String[]{"questionVo"});
		if(ObjectHelper.isNotEmpty(this.questionVo)){
			questionScene.setQuestion(this.questionVo.toPo());
		}
		return questionScene;
	}
	
}
