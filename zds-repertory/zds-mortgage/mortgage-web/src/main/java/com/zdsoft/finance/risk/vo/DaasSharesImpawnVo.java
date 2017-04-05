package com.zdsoft.finance.risk.vo;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: DaasSharesImpawnVo.java
 * @Package com.zdsoft.finance.risk.vo
 * @Description: 股权出质历史信息
 * @author panshm
 */
public class DaasSharesImpawnVo {
	  
	/**
	 * 出质批准日期
	 */
	private String impsandate;

	/**
	 * 出质截至日期
	 */
	private String impto;

	/**
	 * 出质金额（万元）
	 */
	private String impam;

	/**
	 * 质权人姓名
	 */
	private String imporg;

	/**
	 * 质权人类别
	 */
	private String imporgtype;

	/**
	 * 出质备案日期
	 */
	private String imponrecdate;

	/**
	 * 出质审批日期
	 */
	private String impexaeep;
	
	/**
	 * 订单id
	 */
	private String orderId;

	public String getImpsandate() {
		return impsandate;
	}

	public void setImpsandate(String impsandate) {
		this.impsandate = impsandate;
	}

	public String getImpto() {
		return impto;
	}

	public void setImpto(String impto) {
		this.impto = impto;
	}

	public String getImpam() {
		return impam;
	}

	public void setImpam(String impam) {
		this.impam = impam;
	}

	public String getImporg() {
		return imporg;
	}

	public void setImporg(String imporg) {
		this.imporg = imporg;
	}

	public String getImporgtype() {
		return imporgtype;
	}

	public void setImporgtype(String imporgtype) {
		this.imporgtype = imporgtype;
	}

	public String getImponrecdate() {
		return imponrecdate;
	}

	public void setImponrecdate(String imponrecdate) {
		this.imponrecdate = imponrecdate;
	}

	public String getImpexaeep() {
		return impexaeep;
	}

	public void setImpexaeep(String impexaeep) {
		this.impexaeep = impexaeep;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


}
