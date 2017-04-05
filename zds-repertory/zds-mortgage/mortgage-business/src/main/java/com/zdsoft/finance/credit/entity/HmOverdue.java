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
 * @Title: HmOverdue.java
 * @ClassName: HmOverdue
 * @Description: 贷记卡概况
 * @author gufeng
 * @date 2017年2月23日 上午9:40:33
 * @version V1.0
 */
@Entity
@Table(name = "Hm_Over_due")
public class HmOverdue extends BaseEntity {

	private static final long serialVersionUID = -4399899007838316085L;
	/**
	 * 查询表主键Id
	 */
	@Column(name = "query_Id")
	private String queryId;
	/**
	 * 身份证号码
	 */
	@Column(name = "id_card")
	private String idCard;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Timestamp createTime;
	/**
	 * 借记卡ID
	 */
	@Column(name = "credit_Id")
	private String creditId;
	/**
	 * 类型
	 */
	@Column(name = "type")
	private String type;
	/**
	 * 逾期月份1
	 */
	@Column(name = "Over_due_Month1")
	private String OverdueMonth1;

	/**
	 * 逾期持续月数1
	 */
	@Column(name = "Over_due_Month_Num1")
	private Integer OverdueMonthNum1;

	/**
	 * 逾期金额1
	 */
	@Column(name = "Over_due_Amount1")
	private BigDecimal OverdueAmount1 = BigDecimal.ZERO;

	/**
	 * 逾期月份2
	 */
	@Column(name = "Over_due_Month2")
	private String OverdueMonth2;

	/**
	 * 逾期持续月数2
	 */
	@Column(name = "Over_due_Month_Num2")
	private Integer OverdueMonthNum2;

	/**
	 * 逾期金额2
	 */
	@Column(name = "Over_due_Amount2")
	private BigDecimal OverdueAmount2 = BigDecimal.ZERO;

	/**
	 * 逾期月份1
	 */
	@Column(name = "over_due_Cont_Months1")
	private String overdueContMonths1;
	/**
	 * 逾期月份2
	 */
	@Column(name = "Over_due_Cont_Months2")
	private String overdueContMonths2;

	public String getOverdueMonth1() {
		return OverdueMonth1;
	}

	public void setOverdueMonth1(String overdueMonth1) {
		OverdueMonth1 = overdueMonth1;
	}

	public Integer getOverdueMonthNum1() {
		return OverdueMonthNum1;
	}

	public void setOverdueMonthNum1(Integer overdueMonthNum1) {
		OverdueMonthNum1 = overdueMonthNum1;
	}

	public BigDecimal getOverdueAmount1() {
		return OverdueAmount1;
	}

	public void setOverdueAmount1(BigDecimal overdueAmount1) {
		OverdueAmount1 = overdueAmount1;
	}

	public String getOverdueMonth2() {
		return OverdueMonth2;
	}

	public void setOverdueMonth2(String overdueMonth2) {
		OverdueMonth2 = overdueMonth2;
	}

	public Integer getOverdueMonthNum2() {
		return OverdueMonthNum2;
	}

	public void setOverdueMonthNum2(Integer overdueMonthNum2) {
		OverdueMonthNum2 = overdueMonthNum2;
	}

	public BigDecimal getOverdueAmount2() {
		return OverdueAmount2;
	}

	public void setOverdueAmount2(BigDecimal overdueAmount2) {
		OverdueAmount2 = overdueAmount2;
	}

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

	public String getCreditId() {
		return creditId;
	}

	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOverdueContMonths1() {
		return overdueContMonths1;
	}

	public void setOverdueContMonths1(String overdueContMonths1) {
		this.overdueContMonths1 = overdueContMonths1;
	}

	public String getOverdueContMonths2() {
		return overdueContMonths2;
	}

	public void setOverdueContMonths2(String overdueContMonths2) {
		this.overdueContMonths2 = overdueContMonths2;
	}

}
