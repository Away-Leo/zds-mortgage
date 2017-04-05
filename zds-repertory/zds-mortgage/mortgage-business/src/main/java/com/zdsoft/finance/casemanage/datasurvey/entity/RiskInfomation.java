package com.zdsoft.finance.casemanage.datasurvey.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:RiskInfomation.java
 * @Package:com.zdsoft.finance.casemanage.datasurvey.entity
 * @Description:风险信息
 * @author: laijun
 * @date:2017年1月10日 下午9:43:29
 * @version:v1.0
 */
@Entity
@Table(name = "case_risk_infomation")
public class RiskInfomation extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 预计查册时间
	 */
	private String caseApplyId;

	/**
	 * 预计查册时间
	 */
	private Long expectSearchDate;
	/**
	 * 提放类型
	 */
	@Column(length = 20)
	private String putType;
	/**
	 * 是否办理仲裁
	 */
	@Column(length = 20)
	private String isDealArbitration;
	/**
	 * 是否办理委托
	 */
	@Column(length = 20)
	private String isDealEntrust;
	/**
	 * 是否办理债权公证
	 */
	@Column(length = 20)
	private String isDealNotarial;
	/**
	 * 是否办理可交易全委
	 */
	@Column(length = 20)
	private String isDealTradable;
	/**
	 * 预计办理时间
	 */
	private Long expectdealArbitrationDate;
	/**
	 * 
	 */
	private Long expectEntrustDate;
	/**
	 * 预计办理时间
	 */
	private Long expectNotarialDate;
	/**
	 * 抵押物综述
	 */
	@Column(length = 500)
	private String pledgeReview;
	/**
	 * 业务综析
	 */
	@Column(length = 500)
	private String businessAnalysis;
	/**
	 * 特殊情况
	 */
	@Column(length = 500)
	private String specialSituation;
	/**
	 * 经营情况/工作情况
	 */
	@Column(length = 500)
	private String workSituation;
	/**
	 * 其他
	 */
	@Column(length = 500)
	private String remark;

	public Long getExpectSearchDate() {
		return expectSearchDate;
	}

	public void setExpectSearchDate(Long expectSearchDate) {
		this.expectSearchDate = expectSearchDate;
	}

	public String getPutType() {
		return putType;
	}

	public void setPutType(String putType) {
		this.putType = putType;
	}

	public String getIsDealArbitration() {
		return isDealArbitration;
	}

	public void setIsDealArbitration(String isDealArbitration) {
		this.isDealArbitration = isDealArbitration;
	}

	public String getIsDealEntrust() {
		return isDealEntrust;
	}

	public void setIsDealEntrust(String isDealEntrust) {
		this.isDealEntrust = isDealEntrust;
	}

	public String getIsDealNotarial() {
		return isDealNotarial;
	}

	public void setIsDealNotarial(String isDealNotarial) {
		this.isDealNotarial = isDealNotarial;
	}

	public String getIsDealTradable() {
		return isDealTradable;
	}

	public void setIsDealTradable(String isDealTradable) {
		this.isDealTradable = isDealTradable;
	}

	public Long getExpectdealArbitrationDate() {
		return expectdealArbitrationDate;
	}

	public void setExpectdealArbitrationDate(Long expectdealArbitrationDate) {
		this.expectdealArbitrationDate = expectdealArbitrationDate;
	}

	public Long getExpectEntrustDate() {
		return expectEntrustDate;
	}

	public void setExpectEntrustDate(Long expectEntrustDate) {
		this.expectEntrustDate = expectEntrustDate;
	}

	public Long getExpectNotarialDate() {
		return expectNotarialDate;
	}

	public void setExpectNotarialDate(Long expectNotarialDate) {
		this.expectNotarialDate = expectNotarialDate;
	}

	public String getPledgeReview() {
		return pledgeReview;
	}

	public void setPledgeReview(String pledgeReview) {
		this.pledgeReview = pledgeReview;
	}

	public String getBusinessAnalysis() {
		return businessAnalysis;
	}

	public void setBusinessAnalysis(String businessAnalysis) {
		this.businessAnalysis = businessAnalysis;
	}

	public String getSpecialSituation() {
		return specialSituation;
	}

	public void setSpecialSituation(String specialSituation) {
		this.specialSituation = specialSituation;
	}

	public String getWorkSituation() {
		return workSituation;
	}

	public void setWorkSituation(String workSituation) {
		this.workSituation = workSituation;
	}

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


}
