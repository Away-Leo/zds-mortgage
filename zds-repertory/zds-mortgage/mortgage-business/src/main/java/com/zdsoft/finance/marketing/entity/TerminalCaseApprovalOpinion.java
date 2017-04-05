package com.zdsoft.finance.marketing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:TerminalCaseApprovalOpinion.java
 * @Package:com.zdsoft.finance.marketing.entity
 * @Description:终端进件的审批意见
 * @author: xiongpan
 * @date:2017年2月13日 上午9:41:24
 * @version:v1.0
 */
@Entity
@Table(name = "mkt_terminal_approval_opinion")
public class TerminalCaseApprovalOpinion extends BaseEntity{

	private static final long serialVersionUID = -7898840314540354448L;

	/**
	 * 审批结果(0.通过;1.拒绝)
	 */
	@Column(length = 20)
	private String approvalResult;
	
	/**
	 * 拒绝原因
	 */
	@Column(length = 20)
	private String refuseReason;
	
	/**
	 * 分配机构Cd
	 */
	@Column(length = 32)
	private String organizationCd; 
	
	/**
	 * 分配机构名称
	 */
	@Column(length = 64)
	private String organizationName; 
	
	/**
	 * 渠道经理Cd
	 */
	@Column(length = 32)
	private String channelManagerCd;
	
	/**
	 * 渠道经理姓名
	 */
	@Column(length = 32)
	private String channelManagerName;
	
	/**
	 * 备注
	 */
	@Column(length = 512)
	private String mo;
	
	/**
	 * 对应的案件id
	 */
	@Column(length = 32)
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

	public void setOrganizationCd(String organizationCd) {
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

	public void setChannelManagerCd(String channelManagerCd) {
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

	
	
}
