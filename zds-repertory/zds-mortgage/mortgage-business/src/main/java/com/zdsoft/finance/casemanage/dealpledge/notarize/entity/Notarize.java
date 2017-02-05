package com.zdsoft.finance.casemanage.dealpledge.notarize.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:Notarize.java
 * @Package:com.zdsoft.finance.casemanage.dealpledge.notarize.entity
 * @Description:公证实体
 * @author: caixiekang
 * @date:2017年1月14日 下午3:47:04
 * @version:v1.0
 */
@Entity
@Table(name = "case_notarize")
public class Notarize extends BaseEntity {

	
	private static final long serialVersionUID = -1132512824862342737L;
	
	/**
	 * 案件实体  多对一 弱关联
	 * 
	 */
	@Column(length = 32)
	private String caseApplyId;
	
	/**
	 * 公证类型
	 */
	@Column(length = 20)
	private String notarizeType;
	
	/**
	 * 公证机关
	 */
	@Column(length = 32)
	private String notarizeOffice;
	
	/**
	 * 办理公证时间
	 */
	@Column
	private Long notarizeDate;
	
	/**
	 * 预计公证书出具时间
	 */
	@Column
	private Long notarizeProvideDate;
	
	/**
	 * 备注
	 */
	@Column(length = 500)
	private String mo;


	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public String getNotarizeType() {
		return notarizeType;
	}

	public void setNotarizeType(String notarizeType) {
		this.notarizeType = notarizeType;
	}

	public String getNotarizeOffice() {
		return notarizeOffice;
	}

	public void setNotarizeOffice(String notarizeOffice) {
		this.notarizeOffice = notarizeOffice;
	}

	public Long getNotarizeDate() {
		return notarizeDate;
	}

	public void setNotarizeDate(Long notarizeDate) {
		this.notarizeDate = notarizeDate;
	}

	public Long getNotarizeProvideDate() {
		return notarizeProvideDate;
	}

	public void setNotarizeProvideDate(Long notarizeProvideDate) {
		this.notarizeProvideDate = notarizeProvideDate;
	}

	public String getMo() {
		return mo;
	}

	public void setMo(String mo) {
		this.mo = mo;
	}
	
	
}
