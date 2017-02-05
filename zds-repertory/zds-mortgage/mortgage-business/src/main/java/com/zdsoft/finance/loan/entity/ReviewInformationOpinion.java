package com.zdsoft.finance.loan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/** 	
* 版权所有：重庆正大华日软件有限公司	
* @Title: ReviewInformationOpinion.java 	
* @Package com.zdsoft.finance.loan.entity 	
* @Description: 案件管理：财务  合规复核中的复核意见记录
* @author liuhuan 	
* @date 2017年1月20日 下午4:47:02 	
* @version V1.0 	
*/
@Entity
@Table(name="loan_review_info_opinion")
public class ReviewInformationOpinion extends BaseEntity{

	private static final long serialVersionUID = 4526865257623431910L;
	

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
	 * 责任人明名称
	 */
	@Column(length=128)
	private String personLiableName;
	
	/**
	 * 责任人code
	 */
	@Column(length=128)
	private String personLiableCode;
	
	/**
	 * 复核信息 描述
	 */
	@Column(length=500)
	private String mo;
	
	/**
	 * 复核结果
	 */
	@Column(length=32)
	private String reviewResult;
	
	/**
	 * 案件id
	 */
	@Column(length=32)
	private String caseApplyId;
	
	/**
	 * 案件合同 id
	 */
	@Column(length=32)
	private String caseContractId;

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

	public String getPersonLiableName() {
		return personLiableName;
	}

	public void setPersonLiableName(String personLiableName) {
		this.personLiableName = personLiableName;
	}

	public String getPersonLiableCode() {
		return personLiableCode;
	}

	public void setPersonLiableCode(String personLiableCode) {
		this.personLiableCode = personLiableCode;
	}

	public String getMo() {
		return mo;
	}

	public void setMo(String mo) {
		this.mo = mo;
	}

	public String getReviewResult() {
		return reviewResult;
	}

	public void setReviewResult(String reviewResult) {
		this.reviewResult = reviewResult;
	}

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public String getCaseContractId() {
		return caseContractId;
	}

	public void setCaseContractId(String caseContractId) {
		this.caseContractId = caseContractId;
	}
	
	
	
}
