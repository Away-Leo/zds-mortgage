package com.zdsoft.finance.casemanage.complianceReview.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ComplianceReview.java 
 * @ClassName: ComplianceReview 
 * @Description: 合规复核
 * @author xj 
 * @date 2017年2月20日 下午5:28:40 
 * @version V1.0
 */
@Entity
@Table(name = "case_compliance_review")
public class ComplianceReview extends BaseEntity{
	
	/**
	 * 复核通过
	 */
	public static String REVIEW_PASS="1";
	
	/**
	 * 复核未通过
	 */
	public static String REVIEW_NOT_PASS="2";
	
	/**
	 * 否决
	 */
	public static String REVIEW_VETO="3";
	
	/**   
	 * 序列化 
	 */ 
	private static final long serialVersionUID = 451705387440780373L;
	
	/**
	 * 案件id
	 */
	@Column(length=32)
	private String caseApplyId;
	
	/**
	 * 复核结果 1：复核通过，2:复核未通过,3:否决
	 */
	@Column(length=1)
	private String reviewResult;
	
	/**
	 * 描述
	 */
	@Column(length=512)
	private String remark;
	
	public String getCaseApplyId() {
		return caseApplyId;
	}
	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}
	public String getReviewResult() {
		return reviewResult;
	}
	public void setReviewResult(String reviewResult) {
		this.reviewResult = reviewResult;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}
