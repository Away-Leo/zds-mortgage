package com.zdsoft.finance.casemanage.complianceReview.vo;

import com.zdsoft.finance.casemanage.complianceReview.entity.ReviewInformation;
import com.zdsoft.finance.common.base.BaseVo;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ReviewInformationVo.java 
 * @ClassName: ReviewInformationVo 
 * @Description: 案件复核资料vo
 * @author xj 
 * @date 2017年2月20日 下午7:46:08 
 * @version V1.0
 */
public class ReviewInformationVo extends BaseVo<ReviewInformation>{

	private static final long serialVersionUID = 4103377374825091811L;
	
	/**
	 * 合规复合ID
	 */
	private String complianceReviewId;
	
	/**
	 * 一级标示
	 */
	private String firstMarkCode;
	
	/**
	 * 一级标示名称
	 */
	private String firstMarkName;
	
	/**
	 * 二级标示
	 */
	private String secondMarkCode;
	
	/**
	 * 二级标示名称
	 */
	private String secondMarkName;
	
	/**
	 * 三级标示
	 */
	private String thirdMarkCode;
	
	/**
	 * 三级标示名称
	 */
	private String thirdMarkName;
	
	/**
	 * 责任人code
	 */
	private String personLiableCode;
	
	/**
	 * 责任人明名称
	 */
	private String personLiableName;
	
	public ReviewInformationVo(){
	}
	
	public ReviewInformationVo(ReviewInformation po){
		super(po,null,null,null);
	}
	
	public ReviewInformation toPO(){
		ReviewInformation po = new ReviewInformation();
		return super.toPo(this, po);
	}

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

	@Override
	public String toString() {
		return "ReviewInformationVo [complianceReviewId=" + complianceReviewId + ", firstMarkCode=" + firstMarkCode
				+ ", firstMarkName=" + firstMarkName + ", secondMarkCode=" + secondMarkCode + ", secondMarkName="
				+ secondMarkName + ", thirdMarkCode=" + thirdMarkCode + ", thirdMarkName=" + thirdMarkName
				+ ", personLiableCode=" + personLiableCode + ", personLiableName=" + personLiableName + "]";
	}
	
}
