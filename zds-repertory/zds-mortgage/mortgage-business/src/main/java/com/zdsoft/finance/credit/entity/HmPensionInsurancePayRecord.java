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
 * @Title: HmPensionInsurancePayRecord.java
 * @ClassName: HmPensionInsurancePayRecord
 * @Description: 养老保险金缴存记录
 * @author gufeng
 * @date 2017年2月23日 上午9:40:39
 * @version V1.0
 */
@Entity
@Table(name = "hm_Pension_Insurance_Pay_Recor")
public class HmPensionInsurancePayRecord extends BaseEntity {

	private static final long serialVersionUID = -4698639847652080726L;
	/**
	 * 查询主表id
	 */
	@Column(name = "query_Id")
	private String queryId;
	/**
	 * 证件号
	 */
	@Column(name = "id_card")
	private String idCard;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Timestamp createTime;

	/**
	 * 参保地
	 */
	@Column(name = "Pay_Area")
	private String payArea;

	/**
	 * 参保日期
	 */
	@Column(name = "Pay_Date")
	private String payDate;

	/**
	 * 累计缴费月数
	 */
	@Column(name = "Cumulative_Pay_Months")
	private Integer cumulativePayMonths;

	/**
	 * 参加工作月份
	 */
	@Column(name = "Work_Month")
	private String workMonth;

	/**
	 * 缴费状态
	 */
	@Column(name = "Pay_State")
	private String payState;

	/**
	 * 个人缴费基数
	 */
	@Column(name = "Person_Pay_Base")
	private String personPayBase;

	/**
	 * 本月缴费金额
	 */
	@Column(name = "This_Month_Pay_Amount")
	private BigDecimal thisMonthPayAmount = BigDecimal.ZERO;

	/**
	 * 信息更新日期
	 */
	@Column(name = "Info_Update_Date")
	private String infoUpdateDate;

	/**
	 * 缴费单位
	 */
	@Column(name = "Pay_Company")
	private String payCompany;

	/**
	 * 中断或终止缴费原因
	 */
	@Column(name = "Cancel_Pay_Reason")
	private String cancelPayReason;

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

	public Integer getCumulativePayMonths() {
		return cumulativePayMonths;
	}

	public void setCumulativePayMonths(Integer cumulativePayMonths) {
		this.cumulativePayMonths = cumulativePayMonths;
	}

	public String getWorkMonth() {
		return workMonth;
	}

	public void setWorkMonth(String workMonth) {
		this.workMonth = workMonth;
	}

	public String getPayState() {
		return payState;
	}

	public void setPayState(String payState) {
		this.payState = payState;
	}

	public String getPersonPayBase() {
		return personPayBase;
	}

	public void setPersonPayBase(String personPayBase) {
		this.personPayBase = personPayBase;
	}

	public BigDecimal getThisMonthPayAmount() {
		return thisMonthPayAmount;
	}

	public void setThisMonthPayAmount(BigDecimal thisMonthPayAmount) {
		this.thisMonthPayAmount = thisMonthPayAmount;
	}

	public String getInfoUpdateDate() {
		return infoUpdateDate;
	}

	public void setInfoUpdateDate(String infoUpdateDate) {
		this.infoUpdateDate = infoUpdateDate;
	}

	public String getPayCompany() {
		return payCompany;
	}

	public void setPayCompany(String payCompany) {
		this.payCompany = payCompany;
	}

	public String getCancelPayReason() {
		return cancelPayReason;
	}

	public void setCancelPayReason(String cancelPayReason) {
		this.cancelPayReason = cancelPayReason;
	}

}
