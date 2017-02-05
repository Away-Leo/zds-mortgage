package com.zdsoft.finance.loan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/** 	
* 版权所有：重庆正大华日软件有限公司	
* @Title: ReviewInformation.java 	
* @Package com.zdsoft.finance.loan.entity 	
* @Description: 案件管理：财务  合规复核中的复核信息	
* @author liuhuan 	
* @date 2017年1月17日 下午10:12:52 	
* @version V1.0 	
*/
@Entity
@Table(name="loan_review_info")
public class ReviewInformation extends BaseEntity{
	
	private static final long serialVersionUID = -709471010059927023L;

	/**
	 * 一级标示
	 */
	@Column(length=128)
	private String firstMark;
	
	/**
	 * 二级标示
	 */
	@Column(length=128)
	private String secondMark;
	
	/**
	 * 三级标示
	 */
	@Column(length=128)
	private String thirdMark;
	
	/**
	 * 责任人
	 */
	@Column(length=128)
	private String personLiable;

	public String getFirstMark() {
		return firstMark;
	}

	public void setFirstMark(String firstMark) {
		this.firstMark = firstMark;
	}

	public String getSecondMark() {
		return secondMark;
	}

	public void setSecondMark(String secondMark) {
		this.secondMark = secondMark;
	}

	public String getThirdMark() {
		return thirdMark;
	}

	public void setThirdMark(String thirdMark) {
		this.thirdMark = thirdMark;
	}

	public String getPersonLiable() {
		return personLiable;
	}

	public void setPersonLiable(String personLiable) {
		this.personLiable = personLiable;
	}
	
	
	
}
