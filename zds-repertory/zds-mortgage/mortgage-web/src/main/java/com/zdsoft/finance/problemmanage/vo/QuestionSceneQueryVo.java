package com.zdsoft.finance.problemmanage.vo;

import org.springframework.beans.BeanUtils;

import com.zdsoft.finance.problemmanage.entity.QuestionSceneQuery;

/**
 * 场景设定列表Vo
 * @author longwei
 * @date 2017/01/03
 * @version 1.0
 */
public class QuestionSceneQueryVo {

	// 场景id
	private String id;
	
	// 场景类型
	private String sceneCd;
	
	// 场景类型
	private String sceneNm;
	
	// 问题
	private String title;
	
	// 结论类型
	private String typeCd;
	
	// 结论类型
	private String typeNm;
	
	// 参数值
	private String paramCd;
	
	// 参数值
	private String paramNm;
	
	// 命中规则
	private String hitRule;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTypeCd() {
		return typeCd;
	}

	public void setTypeCd(String typeCd) {
		this.typeCd = typeCd;
	}

	public String getTypeNm() {
		return typeNm;
	}

	public void setTypeNm(String typeNm) {
		this.typeNm = typeNm;
	}

	public String getParamCd() {
		return paramCd;
	}

	public void setParamCd(String paramCd) {
		this.paramCd = paramCd;
	}

	public String getParamNm() {
		return paramNm;
	}

	public void setParamNm(String paramNm) {
		this.paramNm = paramNm;
	}

	public String getHitRule() {
		return hitRule;
	}

	public void setHitRule(String hitRule) {
		this.hitRule = hitRule;
	}

	public QuestionSceneQueryVo(){}
	
	public QuestionSceneQueryVo(QuestionSceneQuery query){
		BeanUtils.copyProperties(query, this);
	}
	
	public QuestionSceneQuery toPo(){
		QuestionSceneQuery questionSceneQuery=new QuestionSceneQuery();
		BeanUtils.copyProperties(this, questionSceneQuery);
		return questionSceneQuery;
	}
}
