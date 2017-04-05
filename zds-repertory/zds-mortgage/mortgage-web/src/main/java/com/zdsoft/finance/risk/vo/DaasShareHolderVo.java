package com.zdsoft.finance.risk.vo;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: DaasShareHolderVo.java
 * @Package com.zdsoft.finance.risk.vo
 * @Description: 企业股东及出资信息
 * @author panshm
 */
public class DaasShareHolderVo {
	
	/**
	 * 出资日期
	 */
	private String condate;

	/**
	 * 出资方式
	 */
	private String conform;

	/**
	 * 国别
	 */
	private String country;

	/**
	 * 股东名称
	 */
	private String shaname;

	/**
	 * 认缴出资币种
	 */
	private String regcapcur;

	/**
	 * 出资比例
	 */
	private String fundedratio;

	/**
	 * 认缴出资额（万元）
	 */
	private String subconam;

	/**
	 * 订单id
	 */
	private String orderId;

	public String getCondate() {
		return condate;
	}

	public void setCondate(String condate) {
		this.condate = condate;
	}

	public String getConform() {
		return conform;
	}

	public void setConform(String conform) {
		this.conform = conform;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getShaname() {
		return shaname;
	}

	public void setShaname(String shaname) {
		this.shaname = shaname;
	}

	public String getRegcapcur() {
		return regcapcur;
	}

	public void setRegcapcur(String regcapcur) {
		this.regcapcur = regcapcur;
	}

	public String getFundedratio() {
		return fundedratio;
	}

	public void setFundedratio(String fundedratio) {
		this.fundedratio = fundedratio;
	}

	public String getSubconam() {
		return subconam;
	}

	public void setSubconam(String subconam) {
		this.subconam = subconam;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


}
