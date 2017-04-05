package com.zdsoft.finance.risk.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HuifaDetailVo.java 
 * @ClassName: HuifaDetailVo 
 * @Description: 汇法网明细数据Vo
 * @author panshm 
 * @date 2017年2月18日 下午2:49:12 
 * @version V1.0
 */
public class HuifaDetailVo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 执行公开信息列表
	 */
	private List<HuifaResultInfoVo> publishList;

	/**
	 * 失信老赖列表
	 */
	private List<HuifaResultInfoVo> dishonestList;

	/**
	 * 限制高消费列表
	 */
	private List<HuifaResultInfoVo> restrictedHighConsumeList;
	
	/**
	 * 限制出入境列表
	 */
	private List<HuifaResultInfoVo> restrictedEntryAndExitList;
	
	/**
	 * 民商事裁判文书列表
	 */
	private List<HuifaResultInfoVo> civilJudgmentList;
	
	/**
	 * 民商事审批流程
	 */
	private List<HuifaResultInfoVo> civilApprovalProcessList;
	
	/**
	 * 罪犯及嫌疑人名单列表
	 */
	private List<HuifaResultInfoVo> criminalList;
	
	/**
	 * 行政违法记录列表
	 */
	private List<HuifaResultInfoVo> illegalityList;
	
	/**
	 * 欠税名单列表
	 */
	private List<HuifaResultInfoVo> oweTaxesList;
	
	/**
	 * 纳税非正常户列表
	 */
	private List<HuifaResultInfoVo> abnormalTaxList;
	
	/**
	 * 欠款欠费名单列表
	 */
	private List<HuifaResultInfoVo> arrearageList;

	public List<HuifaResultInfoVo> getPublishList() {
		return publishList;
	}

	public void setPublishList(List<HuifaResultInfoVo> publishList) {
		this.publishList = publishList;
	}

	public List<HuifaResultInfoVo> getDishonestList() {
		return dishonestList;
	}

	public void setDishonestList(List<HuifaResultInfoVo> dishonestList) {
		this.dishonestList = dishonestList;
	}

	public List<HuifaResultInfoVo> getRestrictedHighConsumeList() {
		return restrictedHighConsumeList;
	}

	public void setRestrictedHighConsumeList(List<HuifaResultInfoVo> restrictedHighConsumeList) {
		this.restrictedHighConsumeList = restrictedHighConsumeList;
	}

	public List<HuifaResultInfoVo> getRestrictedEntryAndExitList() {
		return restrictedEntryAndExitList;
	}

	public void setRestrictedEntryAndExitList(List<HuifaResultInfoVo> restrictedEntryAndExitList) {
		this.restrictedEntryAndExitList = restrictedEntryAndExitList;
	}

	public List<HuifaResultInfoVo> getCivilJudgmentList() {
		return civilJudgmentList;
	}

	public void setCivilJudgmentList(List<HuifaResultInfoVo> civilJudgmentList) {
		this.civilJudgmentList = civilJudgmentList;
	}

	public List<HuifaResultInfoVo> getCivilApprovalProcessList() {
		return civilApprovalProcessList;
	}

	public void setCivilApprovalProcessList(List<HuifaResultInfoVo> civilApprovalProcessList) {
		this.civilApprovalProcessList = civilApprovalProcessList;
	}

	public List<HuifaResultInfoVo> getCriminalList() {
		return criminalList;
	}

	public void setCriminalList(List<HuifaResultInfoVo> criminalList) {
		this.criminalList = criminalList;
	}

	public List<HuifaResultInfoVo> getIllegalityList() {
		return illegalityList;
	}

	public void setIllegalityList(List<HuifaResultInfoVo> illegalityList) {
		this.illegalityList = illegalityList;
	}

	public List<HuifaResultInfoVo> getOweTaxesList() {
		return oweTaxesList;
	}

	public void setOweTaxesList(List<HuifaResultInfoVo> oweTaxesList) {
		this.oweTaxesList = oweTaxesList;
	}

	public List<HuifaResultInfoVo> getAbnormalTaxList() {
		return abnormalTaxList;
	}

	public void setAbnormalTaxList(List<HuifaResultInfoVo> abnormalTaxList) {
		this.abnormalTaxList = abnormalTaxList;
	}

	public List<HuifaResultInfoVo> getArrearageList() {
		return arrearageList;
	}

	public void setArrearageList(List<HuifaResultInfoVo> arrearageList) {
		this.arrearageList = arrearageList;
	}

	public HuifaDetailVo(){}
	
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
