package com.zdsoft.finance.problemmanage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 问题库定义
 * @author longwei
 * @date 2016/01/03
 * @version 1.0
 */
@Entity
@Table(name="zf_question")
public class Question extends BaseEntity{

	/**
	 * 问题
	 */
	@Column(length=50)
	private String title;
	
	/**
	 * 结论类型
	 */
	@Column(length=32)
	private String typeCd;
	
	/**
	 * 结论类型
	 */
	@Column(length=255)
	private String typeNm;
	
	/**
	 * 参数值
	 */
	@Column(length=255)
	private String paramCd;
	
	/**
	 * 参数值
	 */
	@Column(length=500)
	private String paramNm;
	
	/**
	 * 命中规则
	 */
	@Column(length=500)
	private String hitRule;

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
	
}
