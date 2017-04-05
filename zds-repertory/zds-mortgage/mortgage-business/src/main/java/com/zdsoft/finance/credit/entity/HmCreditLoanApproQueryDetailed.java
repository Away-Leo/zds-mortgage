package com.zdsoft.finance.credit.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: HmCreditLoanApproQueryDetailed.java
 * @ClassName: HmCreditLoanApproQueryDetailed
 * @Description: 信贷审批查询记录明细
 * @author gufeng
 * @date 2017年2月23日 上午9:38:55
 * @version V1.0
 */
@Entity
@Table(name = "hm_Credit_Loan_Appro_Query_Det")
public class HmCreditLoanApproQueryDetailed extends BaseEntity {

	private static final long serialVersionUID = -4478059998280510985L;
	/**
	 * 查询表ID
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
	 * 顺序
	 */
	@Column(name = "no")
	private Integer no;
	/**
	 * 查询日期
	 */
	@Column(name = "Query_Date")
	private String queryDate;
	/**
	 * 查询操作员查
	 */
	@Column(name = "Query_Operator")
	private String queryOperator;
	/**
	 * 查询操作员查
	 */
	@Column(name = "Query_Reason")
	private String queryReason;

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

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(String queryDate) {
		this.queryDate = queryDate;
	}

	public String getQueryOperator() {
		return queryOperator;
	}

	public void setQueryOperator(String queryOperator) {
		this.queryOperator = queryOperator;
	}

	public String getQueryReason() {
		return queryReason;
	}

	public void setQueryReason(String queryReason) {
		this.queryReason = queryReason;
	}

}
