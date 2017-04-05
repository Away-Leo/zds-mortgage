package com.zdsoft.finance.risk.vo;

import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BusinessDetailVo.java 
 * @ClassName: BusinessDetailVo 
 * @Description: 工商明细数据Vo
 * @author panshm 
 */
public class BusinessDetailVo {

	/**
	 * 企业历史变更信息列表
	 */
	private List<DaasAlterVo> alterList;

	/**
	 * 企业照面信息列表
	 */
	private List<DaasBasicVo> basicList;

	/**
	 * 行政处罚历史信息列表
	 */
	private List<DaasCaseInfoVo> caseInfoList;
	
	/**
	 * 企业对外投资信息列表
	 */
	private List<DaasEntinvVo> entinvList;
	
	/**
	 * 企业分支机构信息列表
	 */
	private List<DaasFiliationVo> filiationList;
	
	/**
	 * 企业法定代表人对外投资信息列表
	 */
	private List<DaasFrinvVo> frinvList;
	
	/**
	 * 企业法定代表人其他公司任职信息列表
	 */
	private List<DaasFrpositionVo> frpositionList;
	
	/**
	 * 清算信息列表
	 */
	private List<DaasLiquidationVo> liquidationList;
	
	/**
	 * 动产抵押信息列表
	 */
	private List<DaasMordetailVo> mordetailList;
	
	/**
	 * 动产抵押物信息列表
	 */
	private List<DaasMorguaInfoVo> morguaInfoList;
	
	/**
	 * 企业主要管理人员信息列表
	 */
	private List<DaasPersonVo> personList;
	
	/**
	 * 失信被执行人信息列表
	 */
	private List<DaasPunishBreakVo> punishBreakList;
	
	/**
	 * 被执行人信息列表
	 */
	private List<DaasPunishedVo> punishedList;
	
	/**
	 * 企业股东及出资信息列表
	 */
	private List<DaasShareHolderVo> shareHolderList;
	
	/**
	 * 股权冻结历史信息列表
	 */
	private List<DaasSharesFrostVo> sharesFrostList;
	
	/**
	 * 股权出质历史信息列表
	 */
	private List<DaasSharesImpawnVo> sharesImpawnList;

	public List<DaasAlterVo> getAlterList() {
		return alterList;
	}

	public void setAlterList(List<DaasAlterVo> alterList) {
		this.alterList = alterList;
	}

	public List<DaasBasicVo> getBasicList() {
		return basicList;
	}

	public void setBasicList(List<DaasBasicVo> basicList) {
		this.basicList = basicList;
	}

	public List<DaasCaseInfoVo> getCaseInfoList() {
		return caseInfoList;
	}

	public void setCaseInfoList(List<DaasCaseInfoVo> caseInfoList) {
		this.caseInfoList = caseInfoList;
	}

	public List<DaasEntinvVo> getEntinvList() {
		return entinvList;
	}

	public void setEntinvList(List<DaasEntinvVo> entinvList) {
		this.entinvList = entinvList;
	}

	public List<DaasFiliationVo> getFiliationList() {
		return filiationList;
	}

	public void setFiliationList(List<DaasFiliationVo> filiationList) {
		this.filiationList = filiationList;
	}

	public List<DaasFrinvVo> getFrinvList() {
		return frinvList;
	}

	public void setFrinvList(List<DaasFrinvVo> frinvList) {
		this.frinvList = frinvList;
	}

	public List<DaasFrpositionVo> getFrpositionList() {
		return frpositionList;
	}

	public void setFrpositionList(List<DaasFrpositionVo> frpositionList) {
		this.frpositionList = frpositionList;
	}

	public List<DaasLiquidationVo> getLiquidationList() {
		return liquidationList;
	}

	public void setLiquidationList(List<DaasLiquidationVo> liquidationList) {
		this.liquidationList = liquidationList;
	}

	public List<DaasMordetailVo> getMordetailList() {
		return mordetailList;
	}

	public void setMordetailList(List<DaasMordetailVo> mordetailList) {
		this.mordetailList = mordetailList;
	}

	public List<DaasMorguaInfoVo> getMorguaInfoList() {
		return morguaInfoList;
	}

	public void setMorguaInfoList(List<DaasMorguaInfoVo> morguaInfoList) {
		this.morguaInfoList = morguaInfoList;
	}

	public List<DaasPersonVo> getPersonList() {
		return personList;
	}

	public void setPersonList(List<DaasPersonVo> personList) {
		this.personList = personList;
	}

	public List<DaasPunishBreakVo> getPunishBreakList() {
		return punishBreakList;
	}

	public void setPunishBreakList(List<DaasPunishBreakVo> punishBreakList) {
		this.punishBreakList = punishBreakList;
	}

	public List<DaasPunishedVo> getPunishedList() {
		return punishedList;
	}

	public void setPunishedList(List<DaasPunishedVo> punishedList) {
		this.punishedList = punishedList;
	}

	public List<DaasShareHolderVo> getShareHolderList() {
		return shareHolderList;
	}

	public void setShareHolderList(List<DaasShareHolderVo> shareHolderList) {
		this.shareHolderList = shareHolderList;
	}

	public List<DaasSharesFrostVo> getSharesFrostList() {
		return sharesFrostList;
	}

	public void setSharesFrostList(List<DaasSharesFrostVo> sharesFrostList) {
		this.sharesFrostList = sharesFrostList;
	}

	public List<DaasSharesImpawnVo> getSharesImpawnList() {
		return sharesImpawnList;
	}

	public void setSharesImpawnList(List<DaasSharesImpawnVo> sharesImpawnList) {
		this.sharesImpawnList = sharesImpawnList;
	}

}
