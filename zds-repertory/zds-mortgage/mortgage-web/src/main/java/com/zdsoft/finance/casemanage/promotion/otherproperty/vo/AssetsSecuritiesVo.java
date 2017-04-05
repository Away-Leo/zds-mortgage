package com.zdsoft.finance.casemanage.promotion.otherproperty.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.casemanage.promotion.otherproperty.entity.AssetsSecurities;
import com.zdsoft.finance.common.base.BaseVo;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AssetsSecuritiesVo.java 
 * @ClassName: AssetsSecuritiesVo 
 * @Description: 其他资产之有价证券Vo
 * @author xiongpan
 * @date 2017年2月25日 上午10:49:31 
 * @version V1.0 
 */ 
public class AssetsSecuritiesVo extends BaseVo<AssetsSecurities>{

	private static final long serialVersionUID = 5098332595941263246L;
	
	/**
	 * 证券名称
	 */
	private String securitiesName;
	
	/**
	 * 价值(元)
	 */
	private BigDecimal worth;
	
	/**
	 * 权属人
	 */
	private String owner;
	
	/**
	 * 案件ID
	 */
	private String caseApplyId;

	public String getSecuritiesName() {
		return securitiesName;
	}

	public void setSecuritiesName(String securitiesName) {
		this.securitiesName = securitiesName;
	}

	public BigDecimal getWorth() {
		return worth;
	}

	public void setWorth(BigDecimal worth) {
		this.worth = worth;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}
	
	

}
