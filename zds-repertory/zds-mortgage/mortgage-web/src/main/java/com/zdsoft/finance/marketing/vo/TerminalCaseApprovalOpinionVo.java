package com.zdsoft.finance.marketing.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.marketing.entity.TerminalCaseApprovalOpinion;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: TerminalCaseApprovalOpinionVo.java 
 * @ClassName: TerminalCaseApprovalOpinionVo 
 * @Description: 终端进件审批意见Vo
 * @author xiongpan
 * @date 2017年3月8日 下午4:58:21 
 * @version V1.0 
 */ 
public class TerminalCaseApprovalOpinionVo extends BaseVo<TerminalCaseApprovalOpinion>{

	private static final long serialVersionUID = -5252218535205031079L;

	/**
	 * 审批结果(0.通过;1.拒绝)
	 */
	private String approvalResult;
	
	/**
	 * 拒绝原因
	 */
	private String refuseReason;
	
	/**
	 * 分配机构Cd
	 */
	private String organizationCd; 
	
	/**
	 * 分配机构名称
	 */
	private String organizationName; 
	
	/**
	 * 渠道经理Cd
	 */
	private String channelManagerCd;
	
	/**
	 * 渠道经理姓名
	 */
	private String channelManagerName;
	
	/**
	 * 备注
	 */
	private String mo;
	
	/**
	 * 对应的案件id
	 */
	private String caseApplyId;

	public String getApprovalResult() {
		return approvalResult;
	}

	public void setApprovalResult(String approvalResult) {
		this.approvalResult = approvalResult;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	public String getOrganizationCd() {
		return organizationCd;
	}

	public void setOrganizationId(String organizationCd) {
		this.organizationCd = organizationCd;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getChannelManagerCd() {
		return channelManagerCd;
	}

	public void setChannelManagerId(String channelManagerCd) {
		this.channelManagerCd = channelManagerCd;
	}

	public String getChannelManagerName() {
		return channelManagerName;
	}

	public void setChannelManagerName(String channelManagerName) {
		this.channelManagerName = channelManagerName;
	}

	public String getMo() {
		return mo;
	}

	public void setMo(String mo) {
		this.mo = mo;
	}

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public TerminalCaseApprovalOpinion toPo() {
		TerminalCaseApprovalOpinion po = new TerminalCaseApprovalOpinion();
		return super.toPo(this, po);
	}
	
	
	
}
