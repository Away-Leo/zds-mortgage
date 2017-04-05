package com.zdsoft.finance.casemanage.complianceReview.vo;

import com.zdsoft.finance.casemanage.complianceReview.entity.ComplianceReview;
import com.zdsoft.finance.common.base.BaseVo;

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
public class ComplianceReviewVo extends BaseVo<ComplianceReview>{
	
	private static final long serialVersionUID = 451705387440780373L;
	
	/**
	 * 案件id
	 */
	private String caseApplyId;
	
	/**
	 * 复核结果 1：复核通过，2:复核未通过,3:否决
	 */
	private String reviewResult;
	
	/**
	 * 描述
	 */
	private String remark;
	
	public ComplianceReviewVo(){
	}
	
	public ComplianceReviewVo(ComplianceReview po){
		super(po,null,null,null);
	}
	
	public ComplianceReview toPO(){
		ComplianceReview po = new ComplianceReview();
		return super.toPo(this, po);
	}
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

	@Override
	public String toString() {
		return "ComplianceReviewVo [caseApplyId=" + caseApplyId + ", reviewResult=" + reviewResult + ", remark="
				+ remark + "]";
	}
	

}
