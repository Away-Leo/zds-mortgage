package com.zdsoft.finance.finance.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.finance.entity.RequestCommission;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:RequestCommissionVo.java
 * @Package:com.zdsoft.finance.finance.vo
 * @Description:支佣管理
 * @author: xiangjx
 * @date:2017年3月2日 上午11:12:41
 * @version:v1.0
 */
public class RequestCommissionVo extends BaseVo<RequestCommission> {

	private static final long serialVersionUID = 1L;

	/**
	 * 单据号
	 */
	private String billCode;

	/**
	 * 请款日期
	 */
	private Long reqFundsDate;
	
	/**
	 * 请款金额
	 */
	private  BigDecimal reqFundsAmount;

	/**
	 * 收款银行账户名称
	 */
	private String accountName;

	/**
	 * 收款开户行
	 */
	private String bankName;

	/**
	 * 收款账号
	 */
	private String bankAccount;

	/**
	 * 往来单位
	 */
	private String contactCompany;

	/**
	 * 请款项目
	 */
	private String requestFundsProject;
	
	/**
	 * 是否拨款
	 */
	private Boolean isGrant=false;

	/**
	 * 实际拨款日期
	 */
	private Long grantDate;
	
	/**
	 * 是否付款
	 */
	private Boolean isPayment=false;

	/**
	 * 实际付款日期
	 */
	private Long paymentDate;
	
   /**
	 * 数据状态 0:草稿1:流程中 2:通过 3：不通过
	 */
	private Integer applyState;

	/**
	 * 摘要
	 */
	private String summary;
	
	/**
	 * 机构id
	 */
	private String orgId;
	
	/**
	 * 机构名称
	 */
	private String orgName;

	/**
	 * 备注
	 */
	private String remark;

	private String msg;

	/**
	 * 是否提交
	 */
	private Boolean isSubmit = false;

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public Long getReqFundsDate() {
		return reqFundsDate;
	}

	public void setReqFundsDate(Long reqFundsDate) {
		this.reqFundsDate = reqFundsDate;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getContactCompany() {
		return contactCompany;
	}

	public void setContactCompany(String contactCompany) {
		this.contactCompany = contactCompany;
	}

	public String getRequestFundsProject() {
		return requestFundsProject;
	}

	public void setRequestFundsProject(String requestFundsProject) {
		this.requestFundsProject = requestFundsProject;
	}

	public Long getGrantDate() {
		return grantDate;
	}

	public void setGrantDate(Long grantDate) {
		this.grantDate = grantDate;
	}

	public Long getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Long paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public BigDecimal getReqFundsAmount() {
		return reqFundsAmount;
	}

	public void setReqFundsAmount(BigDecimal reqFundsAmount) {
		this.reqFundsAmount = reqFundsAmount;
	}

	public Boolean getIsGrant() {
		return isGrant;
	}

	public void setIsGrant(Boolean isGrant) {
		this.isGrant = isGrant;
	}

	public Boolean getIsPayment() {
		return isPayment;
	}

	public void setIsPayment(Boolean isPayment) {
		this.isPayment = isPayment;
	}

	public Integer getApplyState() {
		return applyState;
	}

	public void setApplyState(Integer applyState) {
		this.applyState = applyState;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Boolean getIsSubmit() {
		return isSubmit;
	}

	public void setIsSubmit(Boolean isSubmit) {
		this.isSubmit = isSubmit;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public RequestCommissionVo() {
		super();
	}

	public RequestCommissionVo(RequestCommission entity) throws Exception {
		super(entity);
	}

	public RequestCommission toPo() throws Exception {
		RequestCommission entity = new RequestCommission();
		return super.toPo(this, entity);
	}

}
