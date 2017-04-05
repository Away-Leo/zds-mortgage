package com.zdsoft.finance.risk.huifa.service.bo;

import java.util.List;

import com.zdsoft.finance.commom.dto.BaseEntityDto;
import com.zdsoft.finance.risk.entity.DaasAlter;
import com.zdsoft.finance.risk.entity.DaasBasic;
import com.zdsoft.finance.risk.entity.DaasCaseInfo;
import com.zdsoft.finance.risk.entity.DaasEntinv;
import com.zdsoft.finance.risk.entity.DaasFiliation;
import com.zdsoft.finance.risk.entity.DaasFrinv;
import com.zdsoft.finance.risk.entity.DaasFrposition;
import com.zdsoft.finance.risk.entity.DaasLiquidation;
import com.zdsoft.finance.risk.entity.DaasMordetail;
import com.zdsoft.finance.risk.entity.DaasMorguaInfo;
import com.zdsoft.finance.risk.entity.DaasPerson;
import com.zdsoft.finance.risk.entity.DaasPunishBreak;
import com.zdsoft.finance.risk.entity.DaasPunished;
import com.zdsoft.finance.risk.entity.DaasShareHolder;
import com.zdsoft.finance.risk.entity.DaasSharesFrost;
import com.zdsoft.finance.risk.entity.DaasSharesImpawn;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BusinessDetailBo.java 
 * @ClassName: BusinessDetailBo 
 * @Description: 风险信息-工商信息详情展示功能所用BusinessObject
 * @author panshm 
 * @date 2017年2月20日 下午3:47:41 
 * @version V1.0
 */
public class BusinessDetailBo extends BaseEntityDto {
	
	/**
	 * 企业历史变更信息列表
	 */
	private List<DaasAlter> alterList;

	/**
	 * 企业照面信息列表
	 */
	private List<DaasBasic> basicList;

	/**
	 * 行政处罚历史信息列表
	 */
	private List<DaasCaseInfo> caseInfoList;
	
	/**
	 * 企业对外投资信息列表
	 */
	private List<DaasEntinv> entinvList;
	
	/**
	 * 企业分支机构信息列表
	 */
	private List<DaasFiliation> filiationList;
	
	/**
	 * 企业法定代表人对外投资信息列表
	 */
	private List<DaasFrinv> frinvList;
	
	/**
	 * 企业法定代表人其他公司任职信息列表
	 */
	private List<DaasFrposition> frpositionList;
	
	/**
	 * 清算信息列表
	 */
	private List<DaasLiquidation> liquidationList;
	
	/**
	 * 动产抵押信息列表
	 */
	private List<DaasMordetail> mordetailList;
	
	/**
	 * 动产抵押物信息列表
	 */
	private List<DaasMorguaInfo> morguaInfoList;
	
	/**
	 * 企业主要管理人员信息列表
	 */
	private List<DaasPerson> personList;
	
	/**
	 * 失信被执行人信息列表
	 */
	private List<DaasPunishBreak> punishBreakList;
	
	/**
	 * 被执行人信息列表
	 */
	private List<DaasPunished> punishedList;
	
	/**
	 * 企业股东及出资信息列表
	 */
	private List<DaasShareHolder> shareHolderList;
	
	/**
	 * 股权冻结历史信息列表
	 */
	private List<DaasSharesFrost> sharesFrostList;
	
	/**
	 * 股权出质历史信息列表
	 */
	private List<DaasSharesImpawn> sharesImpawnList;

	public List<DaasAlter> getAlterList() {
		return alterList;
	}

	public void setAlterList(List<DaasAlter> alterList) {
		this.alterList = alterList;
	}

	public List<DaasBasic> getBasicList() {
		return basicList;
	}

	public void setBasicList(List<DaasBasic> basicList) {
		this.basicList = basicList;
	}

	public List<DaasCaseInfo> getCaseInfoList() {
		return caseInfoList;
	}

	public void setCaseInfoList(List<DaasCaseInfo> caseInfoList) {
		this.caseInfoList = caseInfoList;
	}

	public List<DaasEntinv> getEntinvList() {
		return entinvList;
	}

	public void setEntinvList(List<DaasEntinv> entinvList) {
		this.entinvList = entinvList;
	}

	public List<DaasFiliation> getFiliationList() {
		return filiationList;
	}

	public void setFiliationList(List<DaasFiliation> filiationList) {
		this.filiationList = filiationList;
	}

	public List<DaasFrinv> getFrinvList() {
		return frinvList;
	}

	public void setFrinvList(List<DaasFrinv> frinvList) {
		this.frinvList = frinvList;
	}

	public List<DaasFrposition> getFrpositionList() {
		return frpositionList;
	}

	public void setFrpositionList(List<DaasFrposition> frpositionList) {
		this.frpositionList = frpositionList;
	}

	public List<DaasLiquidation> getLiquidationList() {
		return liquidationList;
	}

	public void setLiquidationList(List<DaasLiquidation> liquidationList) {
		this.liquidationList = liquidationList;
	}

	public List<DaasMordetail> getMordetailList() {
		return mordetailList;
	}

	public void setMordetailList(List<DaasMordetail> mordetailList) {
		this.mordetailList = mordetailList;
	}

	public List<DaasMorguaInfo> getMorguaInfoList() {
		return morguaInfoList;
	}

	public void setMorguaInfoList(List<DaasMorguaInfo> morguaInfoList) {
		this.morguaInfoList = morguaInfoList;
	}

	public List<DaasPerson> getPersonList() {
		return personList;
	}

	public void setPersonList(List<DaasPerson> personList) {
		this.personList = personList;
	}

	public List<DaasPunishBreak> getPunishBreakList() {
		return punishBreakList;
	}

	public void setPunishBreakList(List<DaasPunishBreak> punishBreakList) {
		this.punishBreakList = punishBreakList;
	}

	public List<DaasPunished> getPunishedList() {
		return punishedList;
	}

	public void setPunishedList(List<DaasPunished> punishedList) {
		this.punishedList = punishedList;
	}

	public List<DaasShareHolder> getShareHolderList() {
		return shareHolderList;
	}

	public void setShareHolderList(List<DaasShareHolder> shareHolderList) {
		this.shareHolderList = shareHolderList;
	}

	public List<DaasSharesFrost> getSharesFrostList() {
		return sharesFrostList;
	}

	public void setSharesFrostList(List<DaasSharesFrost> sharesFrostList) {
		this.sharesFrostList = sharesFrostList;
	}

	public List<DaasSharesImpawn> getSharesImpawnList() {
		return sharesImpawnList;
	}

	public void setSharesImpawnList(List<DaasSharesImpawn> sharesImpawnList) {
		this.sharesImpawnList = sharesImpawnList;
	}
}
