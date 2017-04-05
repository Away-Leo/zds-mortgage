package com.zdsoft.finance.risk.vo;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: DaasPunishedVo.java
 * @Package com.zdsoft.finance.risk.vo
 * @Description: 被执行人信息
 * @author panshm
 */
public class DaasPunishedVo {

	/**
	 * 被执行人姓名/名称
	 */
	private String inameclean;

	/**
	 * 性别
	 */
	private String sexyclean;

	/**
	 * 年龄
	 */
	private String ageclean;

	/**
	 * 省份
	 */
	private String areanameclean;

	/**
	 * 执行法院
	 */
	private String courtname;

	/**
	 * 案号
	 */
	private String casecode;

	/**
	 * 立案时间
	 */
	private String regdateclean;

	/**
	 * 案件状态
	 */
	private String casestate;

	/**
	 * 身份证号码/企业注册号
	 */
	private String cardnumclean;

	/**
	 * 执行标的（元）
	 */
	private String execmoney;
	
	/**
	 * 身份证原始发证地
	 */
	private String ysfzd;
	
	/**
	 * 订单id
	 */
	private String orderId;

	public String getInameclean() {
		return inameclean;
	}

	public void setInameclean(String inameclean) {
		this.inameclean = inameclean;
	}

	public String getSexyclean() {
		return sexyclean;
	}

	public void setSexyclean(String sexyclean) {
		this.sexyclean = sexyclean;
	}

	public String getAgeclean() {
		return ageclean;
	}

	public void setAgeclean(String ageclean) {
		this.ageclean = ageclean;
	}

	public String getAreanameclean() {
		return areanameclean;
	}

	public void setAreanameclean(String areanameclean) {
		this.areanameclean = areanameclean;
	}

	public String getCourtname() {
		return courtname;
	}

	public void setCourtname(String courtname) {
		this.courtname = courtname;
	}

	public String getCasecode() {
		return casecode;
	}

	public void setCasecode(String casecode) {
		this.casecode = casecode;
	}

	public String getRegdateclean() {
		return regdateclean;
	}

	public void setRegdateclean(String regdateclean) {
		this.regdateclean = regdateclean;
	}

	public String getCasestate() {
		return casestate;
	}

	public void setCasestate(String casestate) {
		this.casestate = casestate;
	}

	public String getCardnumclean() {
		return cardnumclean;
	}

	public void setCardnumclean(String cardnumclean) {
		this.cardnumclean = cardnumclean;
	}

	public String getExecmoney() {
		return execmoney;
	}

	public void setExecmoney(String execmoney) {
		this.execmoney = execmoney;
	}

	public String getYsfzd() {
		return ysfzd;
	}

	public void setYsfzd(String ysfzd) {
		this.ysfzd = ysfzd;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


}
