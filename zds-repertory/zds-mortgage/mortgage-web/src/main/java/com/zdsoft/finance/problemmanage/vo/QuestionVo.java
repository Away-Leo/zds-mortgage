package com.zdsoft.finance.problemmanage.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.problemmanage.entity.Question;

/**
 * 问题定义Vo
 * @author longwei
 * @date 2017/01/03
 * @version 1.0
 */
public class QuestionVo extends BaseVo<Question> {

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

	public QuestionVo(){}
	
	public QuestionVo(Question question){
		super(question);
	}
	
	public Question toPo(){
		Question question=new Question();
		return super.toPo(this, question);
	}
}
