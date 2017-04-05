package com.zdsoft.finance.risk.vo;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: DaasCaseInfoVo.java
 * @Package com.zdsoft.finance.risk.vo
 * @Description: 行政处罚历史信息
 * @author panshm
 */
public class DaasCaseInfoVo {

	/**
	 * 处罚机关
	 */
	private String penauth;

	/**
	 * 案由
	 */
	private String casereason;

	/**
	 * 处罚决定书签发日期
	 */
	private String pendeclssdate;

	/**
	 * 处罚依据
	 */
	private String penbasis;

	/**
	 * 处罚结果
	 */
	private String penresult;

	/**
	 * 处罚金额（万元）
	 */
	private String penam;

	/**
	 * 处罚执行情况
	 */
	private String penexest;

	/**
	 * 案件类型
	 */
	private String casetype;

	/**
	 * 主要违法事实
	 */
	private String illegfact;

	/**
	 * 处罚种类
	 */
	private String pentype;

	/**
	 * 案发时间
	 */
	private String casetime;

	/**
	 * 案件结果
	 */
	private String caseresult;

	/**
	 * 执行类别
	 */
	private String exesort;

	/**
	 * 订单id
	 */
	private String orderId;

	public String getPenauth() {
		return penauth;
	}

	public void setPenauth(String penauth) {
		this.penauth = penauth;
	}

	public String getCasereason() {
		return casereason;
	}

	public void setCasereason(String casereason) {
		this.casereason = casereason;
	}

	public String getPendeclssdate() {
		return pendeclssdate;
	}

	public void setPendeclssdate(String pendeclssdate) {
		this.pendeclssdate = pendeclssdate;
	}

	public String getPenbasis() {
		return penbasis;
	}

	public void setPenbasis(String penbasis) {
		this.penbasis = penbasis;
	}

	public String getPenresult() {
		return penresult;
	}

	public void setPenresult(String penresult) {
		this.penresult = penresult;
	}

	public String getPenam() {
		return penam;
	}

	public void setPenam(String penam) {
		this.penam = penam;
	}

	public String getPenexest() {
		return penexest;
	}

	public void setPenexest(String penexest) {
		this.penexest = penexest;
	}

	public String getCasetype() {
		return casetype;
	}

	public void setCasetype(String casetype) {
		this.casetype = casetype;
	}

	public String getIllegfact() {
		return illegfact;
	}

	public void setIllegfact(String illegfact) {
		this.illegfact = illegfact;
	}

	public String getPentype() {
		return pentype;
	}

	public void setPentype(String pentype) {
		this.pentype = pentype;
	}

	public String getCasetime() {
		return casetime;
	}

	public void setCasetime(String casetime) {
		this.casetime = casetime;
	}

	public String getCaseresult() {
		return caseresult;
	}

	public void setCaseresult(String caseresult) {
		this.caseresult = caseresult;
	}

	public String getExesort() {
		return exesort;
	}

	public void setExesort(String exesort) {
		this.exesort = exesort;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
