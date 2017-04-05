package com.zdsoft.finance.risk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: DaasPunished.java
 * @Package com.zdsoft.finance.risk.entity
 * @Description: 被执行人信息
 * @author panshm
 */
@Entity
@Table(name = "T_DAAS_PUNISHED")
public class DaasPunished extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 被执行人姓名/名称
	 */
	@Column(length=300)
	private String inameclean;

	/**
	 * 性别
	 */
	@Column(length=64)
	private String sexyclean;

	/**
	 * 年龄
	 */
	@Column(length=50)
	private String ageclean;

	/**
	 * 省份
	 */
	@Column(length=200)
	private String areanameclean;

	/**
	 * 执行法院
	 */
	@Column(length=300)
	private String courtname;

	/**
	 * 案号
	 */
	@Column(length=100)
	private String casecode;

	/**
	 * 立案时间
	 */
	@Column(length=50)
	private String regdateclean;

	/**
	 * 案件状态
	 */
	@Column(length=64)
	private String casestate;

	/**
	 * 身份证号码/企业注册号
	 */
	@Column(length=100)
	private String cardnumclean;

	/**
	 * 执行标的（元）
	 */
	@Column(length=50)
	private String execmoney;
	
	/**
	 * 身份证原始发证地
	 */
	@Column(length=300)
	private String ysfzd;
	
	/**
	 * 订单id
	 */
	@Column(name="order_id", length=36)
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
