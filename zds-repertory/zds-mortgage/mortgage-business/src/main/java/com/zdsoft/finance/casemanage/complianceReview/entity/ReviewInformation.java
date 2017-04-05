package com.zdsoft.finance.casemanage.complianceReview.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ReviewInformation.java 
 * @ClassName: ReviewInformation 
 * @Description: 案件复核资料
 * @author xj 
 * @date 2017年2月20日 下午5:49:25 
 * @version V1.0
 */
@Entity
@Table(name = "case_review_information")
public class ReviewInformation extends BaseEntity{

	/**   
	 * 序列表
	 */ 
	private static final long serialVersionUID = 4103377374825091811L;
	
	/**
	 * 合规复合ID
	 */
	@Column(length=32)
	private String complianceReviewId;
	
	/**
	 * 一级标示
	 */
	@Column(length=20)
	private String firstMarkCode;
	
	/**
	 * 一级标示名称
	 */
	@Column(length=64)
	private String firstMarkName;
	
	/**
	 * 二级标示
	 */
	@Column(length=20)
	private String secondMarkCode;
	
	/**
	 * 二级标示名称
	 */
	@Column(length=64)
	private String secondMarkName;
	
	/**
	 * 三级标示
	 */
	@Column(length=20)
	private String thirdMarkCode;
	
	/**
	 * 三级标示名称
	 */
	@Column(length=64)
	private String thirdMarkName;
	
	/**
	 * 责任人code
	 */
	@Column(length=32)
	private String personLiableCode;
	
	/**
	 * 责任人明名称
	 */
	@Column(length=64)
	private String personLiableName;
	
	public String getComplianceReviewId() {
		return complianceReviewId;
	}
	public void setComplianceReviewId(String complianceReviewId) {
		this.complianceReviewId = complianceReviewId;
	}
	public String getFirstMarkCode() {
		return firstMarkCode;
	}
	public void setFirstMarkCode(String firstMarkCode) {
		this.firstMarkCode = firstMarkCode;
	}
	public String getFirstMarkName() {
		return firstMarkName;
	}
	public void setFirstMarkName(String firstMarkName) {
		this.firstMarkName = firstMarkName;
	}
	public String getSecondMarkCode() {
		return secondMarkCode;
	}
	public void setSecondMarkCode(String secondMarkCode) {
		this.secondMarkCode = secondMarkCode;
	}
	public String getSecondMarkName() {
		return secondMarkName;
	}
	public void setSecondMarkName(String secondMarkName) {
		this.secondMarkName = secondMarkName;
	}
	public String getThirdMarkCode() {
		return thirdMarkCode;
	}
	public void setThirdMarkCode(String thirdMarkCode) {
		this.thirdMarkCode = thirdMarkCode;
	}
	public String getThirdMarkName() {
		return thirdMarkName;
	}
	public void setThirdMarkName(String thirdMarkName) {
		this.thirdMarkName = thirdMarkName;
	}
	public String getPersonLiableCode() {
		return personLiableCode;
	}
	public void setPersonLiableCode(String personLiableCode) {
		this.personLiableCode = personLiableCode;
	}
	public String getPersonLiableName() {
		return personLiableName;
	}
	public void setPersonLiableName(String personLiableName) {
		this.personLiableName = personLiableName;
	}
	
	
}
