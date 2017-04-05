package com.zdsoft.finance.casemanage.datasurvey.vo;

import com.zdsoft.finance.casemanage.datasurvey.entity.RiskInfomation;
import com.zdsoft.finance.common.base.BaseVo;
/**
 * 
/**
 * 风险信息VO
 * @author laijun
 * 2017-01-09 10:10:25
 */
public class RiskInformationVo extends BaseVo<RiskInfomation> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 预计查册时间
	 */
	private Long expectSearchDate;
	/**
	 * 提防类型
	 */
	private String putType;
	/**
	 * 提防类型名称
	 */
	private String putTypeName;
	/**
	 * 是否办理仲裁
	 */
	private String isDealArbitration;
	private String isDealArbitrationName;
	/**
	 * 是否办理委托
	 */
	private String isDealEntrust;
	private String isDealEntrustName;
	/**
	 * 是否办理债权公证
	 */
	private String isDealNotarial;
	private String isDealNotarialName;
	/**
	 * 是否办理可交易全委
	 */
	private String isDealTradable;
	
	private String isDealTradableName;
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
	private String pledgeReview;
	/**
	 * 业务综析
	 */
	private String businessAnalysis;
	/**
	 * 特殊情况
	 */
	private String specialSituation;
	/**
	 * 经营情况/工作情况
	 */
	private String workSituation;
	/**
	 * 其他
	 */
	private String remark;

	public Long getExpectSearchDate() {
		return expectSearchDate;
	}

	public RiskInformationVo() {
		super();
	}

	public RiskInformationVo(RiskInfomation po) {
		super(po,null,new String[]{"putType","isDealEntrust","isDealArbitration","isDealTradable","isDealNotarial"});
	}

	public RiskInfomation toPO() {
		RiskInfomation po = new RiskInfomation();
		return super.toPo(this, po);
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setExpectSearchDate(Long expectSearchDate) {
		this.expectSearchDate = expectSearchDate;
	}

	public String getPutTypeName() {
		return putTypeName;
	}

	public void setPutTypeName(String putTypeName) {
		this.putTypeName = putTypeName;
	}

	public String getIsDealArbitrationName() {
		return isDealArbitrationName;
	}

	public void setIsDealArbitrationName(String isDealArbitrationName) {
		this.isDealArbitrationName = isDealArbitrationName;
	}

	public String getIsDealEntrustName() {
		return isDealEntrustName;
	}

	public void setIsDealEntrustName(String isDealEntrustName) {
		this.isDealEntrustName = isDealEntrustName;
	}

	public String getIsDealNotarialName() {
		return isDealNotarialName;
	}

	public void setIsDealNotarialName(String isDealNotarialName) {
		this.isDealNotarialName = isDealNotarialName;
	}

	public String getIsDealTradableName() {
		return isDealTradableName;
	}

	public void setIsDealTradableName(String isDealTradableName) {
		this.isDealTradableName = isDealTradableName;
	}
	
}
