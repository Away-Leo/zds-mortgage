package com.zdsoft.finance.credit.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: HmReportBasics.java
 * @ClassName: HmReportBasics
 * @Description: 人行报告的基本情况
 * @author gufeng
 * @date 2017年2月23日 上午9:43:51
 * @version V1.0
 */
@Entity
@Table(name = "hm_report_basics")
public class HmReportBasics extends BaseEntity {

	private static final long serialVersionUID = 4695950556593925955L;
	/**
	 * 查询表ID
	 */
	@Column(name = "query_Id")
	private String queryId;
	/**
	 * 被查询人身份证号
	 */
	@Column(name = "id_card")
	private String idCard;
	/**
	 * 人行报告ID
	 */
	@Column(name = "Report_Id")
	private String reportId;
	/**
	 * 请求查询时间
	 */
	@Column(name = "Report_Req_Time")
	private String reportReqTime;
	/**
	 * 报告时间
	 */
	@Column(name = "Report_Time")
	private String reportTime;
	/**
	 * 证件类型
	 */
	@Column(name = "Query_Cert_Type")
	private String queryCertType;
	/**
	 * 被查询人姓名
	 */
	@Column(name = "Query_Name")
	private String queryName;

	/**
	 * 被查询人身份证号
	 */
	@Column(name = "Query_Cred_Num")
	private String queryCredNum;

	/**
	 * 查询操作员
	 */
	@Column(name = "Query_Operator")
	private String queryOperator;
	/**
	 * 查询原因
	 */
	@Column(name = "Query_Reason")
	private String queryReason;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Timestamp createTime;

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

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getReportReqTime() {
		return reportReqTime;
	}

	public void setReportReqTime(String reportReqTime) {
		this.reportReqTime = reportReqTime;
	}

	public String getReportTime() {
		return reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public String getQueryCredNum() {
		return queryCredNum;
	}

	public void setQueryCredNum(String queryCredNum) {
		this.queryCredNum = queryCredNum;
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

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getQueryCertType() {
		return queryCertType;
	}

	public void setQueryCertType(String queryCertType) {
		this.queryCertType = queryCertType;
	}

}
