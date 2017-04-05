package com.zdsoft.finance.credit.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: HmHousingReservePayRecord.java
 * @ClassName: HmHousingReservePayRecord
 * @Description: 住房公积金参缴记录
 * @author gufeng
 * @date 2017年2月23日 上午9:39:16
 * @version V1.0
 */
@Entity
@Table(name = "hm_Housing_Reserve_Pay_Record")
public class HmHousingReservePayRecord extends BaseEntity {

	private static final long serialVersionUID = -6358984381905689458L;
	/**
	 * 查询表主键
	 */
	@Column(name = "query_Id")
	private String queryId;
	/**
	 * 身份证
	 */
	@Column(name = "id_card")
	private String idCard;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Timestamp createTime;

	/**
	 * 参缴地
	 */
	@Column(name = "Pay_Area")
	private String payArea;

	/**
	 * 参缴日期
	 */
	@Column(name = "Pay_Date")
	private String payDate;

	/**
	 * 初缴月份
	 */
	@Column(name = "First_Pay_Month")
	private String firstPayMonth;

	/**
	 * 缴至月份
	 */
	@Column(name = "Pay_To_Month")
	private String payToMonth;

	/**
	 * 缴费状态
	 */
	@Column(name = "Pay_State")
	private String payState;

	/**
	 * 月缴存额
	 */
	@Column(name = "Month_Pay_Deposit")
	private BigDecimal monthPayDeposit = BigDecimal.ZERO;

	/**
	 * 个人缴存比例
	 */
	@Column(name = "Person_Pay_Proportion")
	private String personPayProportion;

	/**
	 * 单位缴存比例
	 */
	@Column(name = "Company_Pay_Proportion")
	private String companyPayProportion;

	/**
	 * 缴费单位
	 */
	@Column(name = "Pay_Compan")
	private String payCompany;

	/**
	 * 信息更新日期
	 */
	@Column(name = "Info_Update_Date")
	private String infoUpdateDate;

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getPayArea() {
		return payArea;
	}

	public void setPayArea(String payArea) {
		this.payArea = payArea;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public String getFirstPayMonth() {
		return firstPayMonth;
	}

	public void setFirstPayMonth(String firstPayMonth) {
		this.firstPayMonth = firstPayMonth;
	}

	public String getPayToMonth() {
		return payToMonth;
	}

	public void setPayToMonth(String payToMonth) {
		this.payToMonth = payToMonth;
	}

	public String getPayState() {
		return payState;
	}

	public void setPayState(String payState) {
		this.payState = payState;
	}

	public BigDecimal getMonthPayDeposit() {
		return monthPayDeposit;
	}

	public void setMonthPayDeposit(BigDecimal monthPayDeposit) {
		this.monthPayDeposit = monthPayDeposit;
	}

	public String getPersonPayProportion() {
		return personPayProportion;
	}

	public void setPersonPayProportion(String personPayProportion) {
		this.personPayProportion = personPayProportion;
	}

	public String getCompanyPayProportion() {
		return companyPayProportion;
	}

	public void setCompanyPayProportion(String companyPayProportion) {
		this.companyPayProportion = companyPayProportion;
	}

	public String getPayCompany() {
		return payCompany;
	}

	public void setPayCompany(String payCompany) {
		this.payCompany = payCompany;
	}

	public String getInfoUpdateDate() {
		return infoUpdateDate;
	}

	public void setInfoUpdateDate(String infoUpdateDate) {
		this.infoUpdateDate = infoUpdateDate;
	}

}
