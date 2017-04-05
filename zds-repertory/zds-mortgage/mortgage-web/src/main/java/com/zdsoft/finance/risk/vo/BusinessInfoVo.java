package com.zdsoft.finance.risk.vo;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BusinessInfoVo.java 
 * @ClassName: BusinessInfoVo 
 * @Description: 工商基本信息Vo
 * @author panshm 
 * @date 2017年2月21日 下午2:51:31 
 * @version V1.0
 */
public class BusinessInfoVo {
	
	/**
	 * 企业名称
	 */
	private String entname;
	
	/**
	 * 统一社会信用代码
	 */
	private String creditcode;
	
	/**
	 * 企业（机构）类型
	 */
	private String enttype;

	/**
	 * 法定代表人/负责人/执行事务合伙人
	 */
	private String frname;

	/**
	 * 注册资本（万元）
	 */
	private String regcap;

	/**
	 * 成立日期
	 */
	private String esdate;

	/**
	 * 经营状态
	 */
	private String entstatus;

	/**
	 * 住址
	 */
	private String dom;
	
	/**
	 * 订单id
	 */
	private String orderId;

	public String getEntname() {
		return entname;
	}

	public void setEntname(String entname) {
		this.entname = entname;
	}

	public String getCreditcode() {
		return creditcode;
	}

	public void setCreditcode(String creditcode) {
		this.creditcode = creditcode;
	}

	public String getEnttype() {
		return enttype;
	}

	public void setEnttype(String enttype) {
		this.enttype = enttype;
	}

	public String getFrname() {
		return frname;
	}

	public void setFrname(String frname) {
		this.frname = frname;
	}

	public String getRegcap() {
		return regcap;
	}

	public void setRegcap(String regcap) {
		this.regcap = regcap;
	}

	public String getEsdate() {
		return esdate;
	}

	public void setEsdate(String esdate) {
		this.esdate = esdate;
	}

	public String getEntstatus() {
		return entstatus;
	}

	public void setEntstatus(String entstatus) {
		this.entstatus = entstatus;
	}

	public String getDom() {
		return dom;
	}

	public void setDom(String dom) {
		this.dom = dom;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
