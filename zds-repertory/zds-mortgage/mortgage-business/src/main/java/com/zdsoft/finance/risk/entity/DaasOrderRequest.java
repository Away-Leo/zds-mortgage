package com.zdsoft.finance.risk.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: DaasOrderRequest.java
 * @Package com.zdsoft.finance.risk.entity
 * @Description: 工商请求数据
 * @author panshm
 */
@Entity
@Table(name = "T_DAAS_ORDER_REQUEST")
public class DaasOrderRequest extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 案件号
	 */
	@Column(name="source_case_no", length=50)
	private int sourceCaseNo;

	/**
	 * 机构名称
	 */
	@Column(name="org_name", length=50)
    private String orgName;

	/**
	 * 公司名称
	 */
	@Column(name="company_name", length=500)
    private String companyName;

	/**
	 * 操作员
	 */
	@Column(length=50)
    private String operator;

	/**
	 * 请求时间
	 */
	@Column(name="req_time")
	@Temporal(TemporalType.TIMESTAMP)
    private Date reqTime;

	/**
	 * 请求类型
	 */
    @Column(name="req_type")
    private int reqType;

	/**
	 * 账号代码
	 */
	@Column(name="u_id", length=50)
    private String uId;

	/**
	 * 账号密码
	 */
	@Column(length=50)
    private String password;

	/**
	 * 键值类型（2:企业名称3:工商注册号4:自然人身份件号码）
	 */
	@Column(length=2)
    private String keytype;

	/**
	 * 数据类型
	 */
	@Column(length=50)
    private String ordertype;

	/**
	 * 订单id
	 */
	@Column(name="order_id", length=36)
    private String orderId;

	/**
	 * 请求URL+参数
	 */
	@Column(name="url_param", length=3500)
    private String urlParam;

	public int getSourceCaseNo() {
		return sourceCaseNo;
	}

	public void setSourceCaseNo(int sourceCaseNo) {
		this.sourceCaseNo = sourceCaseNo;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getReqTime() {
		return reqTime;
	}

	public void setReqTime(Date reqTime) {
		this.reqTime = reqTime;
	}

	public int getReqType() {
		return reqType;
	}

	public void setReqType(int reqType) {
		this.reqType = reqType;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getKeytype() {
		return keytype;
	}

	public void setKeytype(String keytype) {
		this.keytype = keytype;
	}

	public String getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUrlParam() {
		return urlParam;
	}

	public void setUrlParam(String urlParam) {
		this.urlParam = urlParam;
	}

}
