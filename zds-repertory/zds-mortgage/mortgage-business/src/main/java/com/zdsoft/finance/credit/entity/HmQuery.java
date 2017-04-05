package com.zdsoft.finance.credit.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: HmQuery.java
 * @ClassName: HmQuery
 * @Description: 征信查询表
 * @author gufeng
 * @date 2017年2月23日 上午9:42:30
 * @version V1.0
 */
@Entity
@Table(name = "hm_query")
public class HmQuery extends BaseEntity {

	private static final long serialVersionUID = -2077652959748872891L;
	/**
	 * 第三方数据Id
	 */
	@Column(name = "object_Id")
	private String objectId;
	/**
	 * 第三方客户ID
	 */
	@Column(name = "object_cust_id")
	private String objectCustId;
	/**
	 * 第三方客户姓名
	 */
	@Column(name = "objcet_cust_name")
	private String objectCustName;
	/**
	 * 第三方客户机构ID
	 */
	@Column(name = "object_org_id")
	private String objectOrgId;
	/**
	 * 第三方客户机构名称
	 */
	@Column(name = "object_org_name")
	private String objectOrgName;
	/**
	 * 链接用户
	 */
	@Column(name = "link_user")
	private String linkUser;
	/**
	 * 1 直接关联征信数据 2间接关联征信数据
	 */
	@Column(name = "hm_type")
	private Integer hmTyep;
	/**
	 * id
	 */
	@Column(name = "hm_Id")
	private String hmId;

	/**
	 * 查询人姓名；
	 */
	@Column(name = "query_Name")
	private String queryName;
	/**
	 * 查询人证件类型
	 */
	@Column(name = "query_Cert_Type")
	private String queryCertType;
	/**
	 * 查询人身份证号
	 */
	@Column(name = "query_Cred_Num")
	private String queryCredNum;
	/**
	 * 身份证复印件
	 */
	@Column(name = "cert_File")
	private String certFile;
	/**
	 * 授权复印件
	 */
	@Column(name = "autho_File")
	private String authoFile;
	/**
	 * 查询时间
	 */
	@Column(name = "query_time")
	private Timestamp queryTime;
	/**
	 * 报告时间
	 */
	@Column(name = "report_time")
	private Timestamp reportTime;
	/**
	 * 送审时间
	 */
	@Column(name = "send_time")
	private Timestamp sendTime;
	/**
	 * xml
	 */
	@Column(name = "report_xml")
	private String reportXml;
	/**
	 * pdf
	 */
	@Column(name = "report_pdf")
	private String reportPdf;
	/**
	 * 文件
	 */
	@Column(name = "report_File")
	private String reportFile;
	/**
	 * 状态
	 */
	@Column(name = "status")
	private Integer status;
	/**
	 * 结果信息
	 */
	@Column(name = "result_Message")
	private String resultMessage;
	/**
	 * 结果编号
	 */
	@Column(name = "result_Code")
	private String resultCode;
	/**
	 * 编号
	 */
	@Column(name = "no")
	private String no;

	public String getLinkUser() {
		return linkUser;
	}

	public void setLinkUser(String linkUser) {
		this.linkUser = linkUser;
	}

	public Timestamp getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(Timestamp queryTime) {
		this.queryTime = queryTime;
	}

	public Timestamp getReportTime() {
		return reportTime;
	}

	public void setReportTime(Timestamp reportTime) {
		this.reportTime = reportTime;
	}

	public Timestamp getSendTime() {
		return sendTime;
	}

	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}

	public String getReportXml() {
		return reportXml;
	}

	public void setReportXml(String reportXml) {
		this.reportXml = reportXml;
	}

	public String getReportPdf() {
		return reportPdf;
	}

	public void setReportPdf(String reportPdf) {
		this.reportPdf = reportPdf;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public String getQueryCertType() {
		return queryCertType;
	}

	public void setQueryCertType(String queryCertType) {
		this.queryCertType = queryCertType;
	}

	public String getQueryCredNum() {
		return queryCredNum;
	}

	public void setQueryCredNum(String queryCredNum) {
		this.queryCredNum = queryCredNum;
	}

	public String getCertFile() {
		return certFile;
	}

	public void setCertFile(String certFile) {
		this.certFile = certFile;
	}

	public String getAuthoFile() {
		return authoFile;
	}

	public void setAuthoFile(String authoFile) {
		this.authoFile = authoFile;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public Integer getHmTyep() {
		return hmTyep;
	}

	public void setHmTyep(Integer hmTyep) {
		this.hmTyep = hmTyep;
	}

	public String getHmId() {
		return hmId;
	}

	public void setHmId(String hmId) {
		this.hmId = hmId;
	}

	public String getObjectCustId() {
		return objectCustId;
	}

	public void setObjectCustId(String objectCustId) {
		this.objectCustId = objectCustId;
	}

	public String getObjectCustName() {
		return objectCustName;
	}

	public void setObjectCustName(String objectCustName) {
		this.objectCustName = objectCustName;
	}

	public String getObjectOrgId() {
		return objectOrgId;
	}

	public void setObjectOrgId(String objectOrgId) {
		this.objectOrgId = objectOrgId;
	}

	public String getObjectOrgName() {
		return objectOrgName;
	}

	public void setObjectOrgName(String objectOrgName) {
		this.objectOrgName = objectOrgName;
	}

	public String getReportFile() {
		return reportFile;
	}

	public void setReportFile(String reportFile) {
		this.reportFile = reportFile;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

}
