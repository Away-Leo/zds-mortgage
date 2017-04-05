package com.zdsoft.finance.marketing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CaseTail.java 
 * @ClassName: CaseTail 
 * @Description: 案件跟踪类
 * @author zhoushichao 
 * @date 2017年3月14日 下午5:43:37 
 * @version V1.0
 */
@Entity
@Table(name = "mkt_case_tail")
public class CaseTail extends BaseEntity{

	private static final long serialVersionUID = 1L;

	/**
	 * 案件id
	 */
	@Column(length = 32)
	private String caseApplyId;

	/**
	 * 营销人ID
	 */
	@Column(length = 32)
	private String marketingPersonId;
	
	/**
	 * 营销人姓名 added by caixiekang
	 */
	@Column(length =64)
	private String marketingPersonName;
	
	/**
	 * 跟踪时间 added by caixiekang
	 */
	@Column
	private Long tailDate;
	/**
	 * 跟踪内容
	 */
	@Column(length = 3000)
	private String tailContent;
	
	
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
	public String getCaseApplyId() {
		return caseApplyId;
	}
	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}
	public String getMarketingPersonName() {
		return marketingPersonName;
	}
	public void setMarketingPersonName(String marketingPersonName) {
		this.marketingPersonName = marketingPersonName;
	}
	public Long getTailDate() {
		return tailDate;
	}
	public void setTailDate(Long tailDate) {
		this.tailDate = tailDate;
	}
}
