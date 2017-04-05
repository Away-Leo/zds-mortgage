package com.zdsoft.finance.app.casemanage.handleMortgage.vo;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:PropertyOwnerVo.java
 * @Package:com.zdsoft.finance.marketing.vo
 * @Description:产权人Vo
 * @author: zhoushichao
 * @date:2017年1月13日 下午10:15:44
 * @version:v1.0
 */
public class AppPropertyOwnerVo{


	/**
	 * 产权人ID
	 */
	private String id;
	/**
	 * 产权人姓名
	 */
	private String ownerName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
}
