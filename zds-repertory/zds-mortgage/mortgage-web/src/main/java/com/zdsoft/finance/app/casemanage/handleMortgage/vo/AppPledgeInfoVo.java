package com.zdsoft.finance.app.casemanage.handleMortgage.vo;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AppPledgeInfoVo.java 
 * @ClassName: AppPledgeInfoVo 
 * @Description: App抵押Vo
 * @author zhoushichao 
 * @date 2017年3月2日 下午5:18:24 
 * @version V1.0
 */
public class AppPledgeInfoVo {


	/**
	 * 抵押Id
	 */
	private String id;
	/**
	 * 抵押类型Code
	 */
	private String pledgeType;
	/**
	 * 抵押类型名称
	 */
	private String pledgeTypeName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPledgeType() {
		return pledgeType;
	}
	public void setPledgeType(String pledgeType) {
		this.pledgeType = pledgeType;
	}
	public String getPledgeTypeName() {
		return pledgeTypeName;
	}
	public void setPledgeTypeName(String pledgeTypeName) {
		this.pledgeTypeName = pledgeTypeName;
	}
}
