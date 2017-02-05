package com.zdsoft.finance.marketing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:CaseTail.java
 * @Package:com.zdsoft.finance.marketing.entity
 * @Description:案件跟踪类
 * @author: zhoushichao
 * @date:2017年1月10日 下午9:45:15
 * @version:v1.0
 */
@Entity
@Table(name = "mark_case_tail")
public class CaseTail extends BaseEntity{

	private static final long serialVersionUID = 1L;

	/**
	 * 案件
	 */
	@ManyToOne
    @JoinColumn(name="caseApplyId")
	private CaseApply caseApply;
	/**
	 * 营销人ID
	 */
	@Column(length = 32)
	private String marketingPersonId;
	/**
	 * 跟踪内容
	 */
	@Column(length = 3000)
	private String tailContent;
	
	public CaseApply getCaseApply() {
		return caseApply;
	}
	public void setCaseApply(CaseApply caseApply) {
		this.caseApply = caseApply;
	}
	public String getMarketingPersonId() {
		return marketingPersonId;
	}
	public void setMarketingPersonId(String marketingPersonId) {
		this.marketingPersonId = marketingPersonId;
	}
	public String getTailContent() {
		return tailContent;
	}
	public void setTailContent(String tailContent) {
		this.tailContent = tailContent;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
