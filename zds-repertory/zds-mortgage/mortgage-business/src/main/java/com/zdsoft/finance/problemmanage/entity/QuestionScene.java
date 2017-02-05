package com.zdsoft.finance.problemmanage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 问题场景
 * @author longwei
 * @date 2016/01/03
 * @version 1.0
 */
@Entity
@Table(name="zf_question_scene")
public class QuestionScene extends BaseEntity{

	/**
	 * 场景类型
	 */
	@Column(length=32)
	private String sceneCd;
	
	/**
	 * 场景类型
	 */
	@Column(length=255)
	private String sceneNm;
	
	/**
	 * 问题
	 */
	@ManyToOne
	@JoinColumn(name="question_id")
	private Question question;

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

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}
