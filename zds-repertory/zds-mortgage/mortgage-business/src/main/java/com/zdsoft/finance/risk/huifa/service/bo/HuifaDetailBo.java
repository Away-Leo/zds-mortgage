package com.zdsoft.finance.risk.huifa.service.bo;

import java.util.List;

import com.zdsoft.finance.risk.entity.HuifaResultInfo;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HuifaDetailBo.java 
 * @ClassName: HuifaDetailBo 
 * @Description: 汇法网明细数据BusinessObject
 * @author panshm 
 * @date 2017年2月18日 下午2:49:12 
 * @version V1.0
 */
public class HuifaDetailBo {

	/**
	 * 执行公开信息列表
	 */
	private List<HuifaResultInfo> publishList;

	/**
	 * 失信老赖列表
	 */
	private List<HuifaResultInfo> dishonestList;

	/**
	 * 限制高消费列表
	 */
	private List<HuifaResultInfo> restrictedHighConsumeList;
	
	/**
	 * 限制出入境列表
	 */
	private List<HuifaResultInfo> restrictedEntryAndExitList;
	
	/**
	 * 民商事裁判文书列表
	 */
	private List<HuifaResultInfo> civilJudgmentList;
	
	/**
	 * 民商事审批流程
	 */
	private List<HuifaResultInfo> civilApprovalProcessList;
	
	/**
	 * 罪犯及嫌疑人名单列表
	 */
	private List<HuifaResultInfo> criminalList;
	
	/**
	 * 行政违法记录列表
	 */
	private List<HuifaResultInfo> illegalityList;
	
	/**
	 * 欠税名单列表
	 */
	private List<HuifaResultInfo> oweTaxesList;
	
	/**
	 * 纳税非正常户列表
	 */
	private List<HuifaResultInfo> abnormalTaxList;
	
	/**
	 * 欠款欠费名单列表
	 */
	private List<HuifaResultInfo> arrearageList;

	public List<HuifaResultInfo> getPublishList() {
		return publishList;
	}

	public void setPublishList(List<HuifaResultInfo> publishList) {
		this.publishList = publishList;
	}

	public List<HuifaResultInfo> getDishonestList() {
		return dishonestList;
	}

	public void setDishonestList(List<HuifaResultInfo> dishonestList) {
		this.dishonestList = dishonestList;
	}

	public List<HuifaResultInfo> getRestrictedHighConsumeList() {
		return restrictedHighConsumeList;
	}

	public void setRestrictedHighConsumeList(List<HuifaResultInfo> restrictedHighConsumeList) {
		this.restrictedHighConsumeList = restrictedHighConsumeList;
	}

	public List<HuifaResultInfo> getRestrictedEntryAndExitList() {
		return restrictedEntryAndExitList;
	}

	public void setRestrictedEntryAndExitList(List<HuifaResultInfo> restrictedEntryAndExitList) {
		this.restrictedEntryAndExitList = restrictedEntryAndExitList;
	}

	public List<HuifaResultInfo> getCivilJudgmentList() {
		return civilJudgmentList;
	}

	public void setCivilJudgmentList(List<HuifaResultInfo> civilJudgmentList) {
		this.civilJudgmentList = civilJudgmentList;
	}

	public List<HuifaResultInfo> getCivilApprovalProcessList() {
		return civilApprovalProcessList;
	}

	public void setCivilApprovalProcessList(List<HuifaResultInfo> civilApprovalProcessList) {
		this.civilApprovalProcessList = civilApprovalProcessList;
	}

	public List<HuifaResultInfo> getCriminalList() {
		return criminalList;
	}

	public void setCriminalList(List<HuifaResultInfo> criminalList) {
		this.criminalList = criminalList;
	}

	public List<HuifaResultInfo> getIllegalityList() {
		return illegalityList;
	}

	public void setIllegalityList(List<HuifaResultInfo> illegalityList) {
		this.illegalityList = illegalityList;
	}

	public List<HuifaResultInfo> getOweTaxesList() {
		return oweTaxesList;
	}

	public void setOweTaxesList(List<HuifaResultInfo> oweTaxesList) {
		this.oweTaxesList = oweTaxesList;
	}

	public List<HuifaResultInfo> getAbnormalTaxList() {
		return abnormalTaxList;
	}

	public void setAbnormalTaxList(List<HuifaResultInfo> abnormalTaxList) {
		this.abnormalTaxList = abnormalTaxList;
	}

	public List<HuifaResultInfo> getArrearageList() {
		return arrearageList;
	}

	public void setArrearageList(List<HuifaResultInfo> arrearageList) {
		this.arrearageList = arrearageList;
	}

	public HuifaDetailBo(){}
	
//	public HuifaDetailVo(QuestionSceneQuery query){
//		BeanUtils.copyProperties(query, this);
//	}
//	
//	public QuestionSceneQuery toPo(){
//		QuestionSceneQuery questionSceneQuery=new QuestionSceneQuery();
//		BeanUtils.copyProperties(this, questionSceneQuery);
//		return questionSceneQuery;
//	}
}
