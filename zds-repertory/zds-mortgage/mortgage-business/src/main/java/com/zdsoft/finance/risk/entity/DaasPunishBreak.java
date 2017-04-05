package com.zdsoft.finance.risk.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: DaasPunishBreak.java
 * @Package com.zdsoft.finance.risk.entity
 * @Description: 失信被执行人信息
 * @author panshm
 */
@Entity
@Table(name = "T_DAAS_PUNISHBREAK")
public class DaasPunishBreak extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 执行法院
	 */
	@Column(length=300)
	private String courtname;

	/**
	 * 省份
	 */
	@Column(length=200)
	private String areanameclean;

	/**
	 * 失信被执行人行为具体情形
	 */
	@Column(length=3000)
	private String disrupttypename;

	/**
	 * 未履行（元）
	 */
	@Column(length=300)
	private String unperformpart;

	/**
	 * 年龄
	 */
	@Column(length=50)
	private String ageclean;

	/**
	 * 被执行人姓名/名称
	 */
	@Column(length=300)
	private String inameclean;

	/**
	 * 法定代表人/负责人姓名
	 */
	@Column(length=300)
	private String businessentity;

	/**
	 * 已履行（元）
	 */
	@Column(length=300)
	private String performedpart;

	/**
	 * 立案时间
	 */
	@Column(length=50)
	private String regdateclean;

	/**
	 * 发布时间
	 */
	@Column(length=50)
	private String publishdateclean;

	/**
	 * 被执行人的履行情况
	 */
	@Column(length=3000)
	private String performance;

	/**
	 * 做出执行依据单位
	 */
	@Column(length=300)
	private String gistunit;

	/**
	 * 性别
	 */
	@Column(length=64)
	private String sexyclean;

	/**
	 * 失信人类型
	 */
	@Column(length=64)
	private String type;

	/**
	 * 执行依据文号
	 */
	@Column(length=100)
	private String gistid;

	/**
	 * 案号
	 */
	@Column(length=100)
	private String casecode;

	/**
	 * 身份证号码/工商注册号
	 */
	@Column(length=50)
	private String cardnum;

	/**
	 * 身份证原始发证地
	 */
	@Column(length=300)
	private String ysfzd;
	
	/**
	 * 生效法律文书确定的义务
	 */
	@Lob 
	@Basic(fetch = FetchType.EAGER) 
	@Column(name="duty", columnDefinition="CLOB", nullable=true)
	private String duty;

	/**
	 * 订单id
	 */
	@Column(name="order_id", length=36)
	private String orderId;

	public String getCourtname() {
		return courtname;
	}

	public void setCourtname(String courtname) {
		this.courtname = courtname;
	}

	public String getAreanameclean() {
		return areanameclean;
	}

	public void setAreanameclean(String areanameclean) {
		this.areanameclean = areanameclean;
	}

	public String getDisrupttypename() {
		return disrupttypename;
	}

	public void setDisrupttypename(String disrupttypename) {
		this.disrupttypename = disrupttypename;
	}

	public String getUnperformpart() {
		return unperformpart;
	}

	public void setUnperformpart(String unperformpart) {
		this.unperformpart = unperformpart;
	}

	public String getAgeclean() {
		return ageclean;
	}

	public void setAgeclean(String ageclean) {
		this.ageclean = ageclean;
	}

	public String getInameclean() {
		return inameclean;
	}

	public void setInameclean(String inameclean) {
		this.inameclean = inameclean;
	}

	public String getBusinessentity() {
		return businessentity;
	}

	public void setBusinessentity(String businessentity) {
		this.businessentity = businessentity;
	}

	public String getPerformedpart() {
		return performedpart;
	}

	public void setPerformedpart(String performedpart) {
		this.performedpart = performedpart;
	}

	public String getRegdateclean() {
		return regdateclean;
	}

	public void setRegdateclean(String regdateclean) {
		this.regdateclean = regdateclean;
	}

	public String getPublishdateclean() {
		return publishdateclean;
	}

	public void setPublishdateclean(String publishdateclean) {
		this.publishdateclean = publishdateclean;
	}

	public String getPerformance() {
		return performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}

	public String getGistunit() {
		return gistunit;
	}

	public void setGistunit(String gistunit) {
		this.gistunit = gistunit;
	}

	public String getSexyclean() {
		return sexyclean;
	}

	public void setSexyclean(String sexyclean) {
		this.sexyclean = sexyclean;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGistid() {
		return gistid;
	}

	public void setGistid(String gistid) {
		this.gistid = gistid;
	}

	public String getCasecode() {
		return casecode;
	}

	public void setCasecode(String casecode) {
		this.casecode = casecode;
	}

	public String getCardnum() {
		return cardnum;
	}

	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}

	public String getYsfzd() {
		return ysfzd;
	}

	public void setYsfzd(String ysfzd) {
		this.ysfzd = ysfzd;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
